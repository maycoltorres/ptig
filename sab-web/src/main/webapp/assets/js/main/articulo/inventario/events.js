convertidor_de_unidades(1,2,3);
function eventosClick(id,action){
    $("#tableMinMax").off("click").click(function(){
        if(comprobarCamposBasicos()){
            showTable({tabla:"max",modo:"form",tipo:"floating",fila:"",id:id,inventarioId:$(this).attr("data-inventario")});
        }
    });

    $("#table_unidad_c").off("click").click(function() {
        if(comprobarCamposBasicos()){
            showTable({tabla:"unidad_c",modo:"form",tipo:"floating",fila:"",id:id,inventarioId:$(this).attr("data-inventario")});
        }
    });

    $("#bodegas_articulo").off("click").click(function() {
        if(comprobarCamposBasicos()){
            showTable({tabla: "bodegasArticulo", type:"table",modo: "table", tipo: "select_multiple",all:true});
        }
    });
}


function eventosVarios(id,action){
    $("#articulo_section").find(".otra_unidad").find("input[type='checkbox']").off("change").change(function enable_min_max() {
        if($(this).is(":checked")){
            $(".min_max").removeClass("hidden");
        }else{
            $(".min_max").addClass("hidden");
        }
    });
    
    $("#articulo_section").find("#unidad_k").off("change").change(function enable_other() {
        window.pedirEquivalencia=true;
        showTable({tabla: "unidad_c", modo: "tabla", tipo: "save",inventarioId:window.articulo.get("inventarioEnt").id,id:id});
        
        $("#articulo_section").find("#unidad_a").find( ":hidden" ).show();
        $("#articulo_section").find("#unidad_a").children('option[value=' + this.value + ']').hide();
        
        if(this.value > 0){
        	var unidadKardex = findById(window.unidadesEstandar, this.value);
            if(unidadKardex.tipo == "Unidad"){
                $("#unidad_alias").closest(".form-group").removeClass("hidden");
                $("#unidad_alias").val("");
            }else{
            	$("#unidad_alias").closest(".form-group").addClass("hidden");
            }
        }else{
        	$("#unidad_alias").closest(".form-group").addClass("hidden");
        }
    });

    $("#articulo_section").find("#unidad_a").off("change").change(function enable_other() {
        if(this.value === "-2"){
            $(".unidad_o").removeClass("hidden");
            $("#articulo_section").find("#unidad_o").focus();
        }else{
            $(".unidad_o").find("input").removeClass("campo_mal");
            $(".unidad_o").find("input").val("");
            $(".unidad_o").addClass("hidden");
        }
    });
    
    $("#articulo_section").find("#clase").change(function () {
    	if (!existWithVal($(this).find(":selected").attr("value"))) {
            return;
        }
        var ajax2=$.ajax({
            url:"./rest/cliente/clase/" + this.value + "/grupos",
            success:function(data){
                fillSelect(data,$("#articulo_section").find("#grupo"),"nombre","type","", function(){
                	if(window.articulo.get("inventarioEnt").idGrupoArticulo){
                		$("#grupo option[value='" + window.articulo.get("inventarioEnt").idGrupoArticulo +"']").prop("selected", "selected"); 
                	}
                });
                $("#articulo_section").find("#unidad_a").append('<option class="empty"></option>');
            },
            error:function(){verifyConnection(function(){
               showMessage("error","No fue posible consultar las unidades alternativas");
           })}
        });
    });
}


function validaInventario(id,action,data,prueba,redirect){

    $("#clase").valida();
    $("#grupo").valida();
    $("#unidad_k").valida();

    if($("#unidad_a").find(":selected").attr("value")==-2 && !$("#unidad_a").hasClass(".configNotShow")){
        var val=$("#unidad_o").val();
        //console.log($("#unidad_o").parent().parent());
        if(!(val.length>0)&&!$("#unidad_o").closest(".form-group").hasClass(".hidden")){
            $("#unidad_o").campoMal({mensajeParticular:"Debe escribir una unidad alternativa",mostrarLongitud:false});
            $("#unidad_o").focus();

            return false;
        }else{
            var unidades=window.unidadesEstandar.concat(window.unidadesAlternas);
            if($.grep(unidades, function(e){ return e.nombre.toLowerCase()==val.toLowerCase();}).length>0){
                showMessage("error","Ya existe una unidad con ese nombre");
                $("#unidad_o").focus();
                return;
            }
        }
    }

    $("#unidad_o").valida({longitud:{minimo:3,maximo:50},mostrarLongitud:true,mostrarMensaje:true});
    $("#unidad_alias").valida({longitud:{minimo:3,maximo:50},mostrarLongitud:true,mostrarMensaje:true});


    if($("#unidad_c_tableCross").find("tbody").find("td.idSelect").length<1){
        showMessage("warning","Debe tener al menos una unidad de compra");
        return;
    }

    if($("#step_inventario").find(".campo_mal").not(".hidden").length<1){
        showChargeStep();
        var idUnidadAlterna = $("#unidad_a").val();
        //Si es necesario, se crea la unidad
        if(idUnidadAlterna === "-2"){
        	var nuevaUnidad = {
            	"nombre" : $("#unidad_o").val(),
                "tipo" : "",
                "estandar" : false
            };
            
           	$.ajax({
                url:"./rest/unidad/",
                type:"POST",
                contentType:"application/json",
                async:false,
                data:JSON.stringify(nuevaUnidad),
                success:function(data){
                	nuevaUnidad.id = data;
                	window.unidadesAlternas.push(nuevaUnidad);
                	idUnidadAlterna = data;
                	
                	unidadSeleccionada = {
                        "id": data,
                        "id_unidad": data,
                        "equivalencia": null,
                        "es_estandar": 0,
                        "id_inventario": window.articulo.get("inventarioEnt").id,
                        "nombre": $("#unidad_o").val(),
                        "type": 1
                	};               	
                },
                error:function(){
                    verifyConnection(showMessage("error","No se pudo guardar la  nueva unidad"));
                    return false;
                }
            });
        }

        var data={
            id:window.articulo.get("inventarioEnt").id,
            idClaseArticulo : $("#clase").val(),
            idGrupoArticulo : $("#grupo").val(),
            aliasUnidad : $("#unidad_alias").val(),
            idUnidadKardex : $("#unidad_k").val(),
            idUnidadAlterna : idUnidadAlterna,
    		maximosMinimos : window.maximosMinimos,
    	    unidadesCompra : window.articulo.get("inventarioEnt").unidadesCompra,
    	    bodegas: window.bodegas
        }
        var funcion="";
        guardarInventario(id, action, data, redirect);
    }else{
        $(".campo_mal").first().focus();
        return false;
    }
}

function guardarInventario(id, action, inventario, redirect){
	id=window.articulo.id;
    url="./rest/articulo/"+ id + "/inventario/";
    if(window.inventarioYaCreado){
    	tipo = "PUT";
    }else{
    	tipo = "POST";
    }
    
   	$.ajax({
        url:url,
        type:tipo,
        contentType:"application/json",
        data:JSON.stringify(inventario),
        success:function(data){
            showMessage("good","El inventario del articulo se ha guardado");
            window.articulo.set("inventarioEnt", inventario);
            window.inventarioYaCreado = true;
            if(!(redirect==undefined)){
                Backbone.history.navigate('#lista/articulos/', { trigger: true });
                return;
            }
            chargeStep(2, window.articulo.get("id"), action);
            close_floating();
            closeChargeStep();
        },
        error:function(){
            $("#articulo_section").find("button.back").click();
            verifyConnection(showMessage("error","No se pudo guardar inventario"));
            close_floating();
            return false;
        }
    });
}

function comprobarCamposBasicos(){
    if($("#unidad_k").find(":selected").val()<1||$("#grupo").find(":selected").val()<1||$("#clase").find(":selected").val()<1){
        showMessage("warning","Elija una clase, un grupo y una unidad de Kardex");
        return false;
    }
    if($("#unidad_a").find(":selected").val() === -2){
        if(!existWithVal($("#unidad_o").val())){
            $("#unidad_o").campoMal({mensajeParticular:"Debe escribir una unidad alternativa",mostrarLongitud:false});
            $(".campo_mal:last").focus();
            return false;
        }
    }

    return true;
}
