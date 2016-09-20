function eventosClickBasico(id,action){
    $("#config_buttton").off("click").click(function(){guardarConfiguracion_articulos(id,action)});

    $("#venta_puntos_table").off("click").click(function(){
        showTable({tabla:"ventaPunto",tipo:"select_multiple",returnControl:"",id:id,todosLosPuntos:true});
    });

    $("#check_seleccion").off("click").click(function () {
        $(".wstep").off("click");
        var me=$(this);
        if(!me.is(":checked")){
        	$.ajax({
                url:"./rest/grupoSeleccion/?idArticulo=" + window.articulo.get("id"),
                success:function(data){
                    if(data.length > 0){
                    	showMessage("error", "Este articulo hace parte de " + data.length + " grupos de seleccion");
                    	me.prop("checked", true);
                    	return;
                    }
                },
                error:function(){verifyConnection(function(){
                   showMessage("error","No fue posible consultar los grupos de seleccion");
               })}
            });
        }else{
            if(window.articulo.get("inventario") && window.articulo.get("inventarioEnt") && window.articulo.get("inventarioEnt").idUnidadAlterna){
                showMessage("warning","El articulo posee unidad alterna de inventario");
                me.prop("checked",false);
                me.change();
                return;
            }
        }
        enableSaving();
    });

    $("#check_venta").off("click").click(function () {
        $(".wstep").off("click");
        if($(this).is(":checked")){
            if($("#check_seleccion").is(":checked") && !(window.articulo.get("ventaEnt").idUnidad != 12)){
                $("#check_venta").prop("checked",false);
                $("#check_venta").change();
                showMessage("warning","No puede activar venta porque la unidad de venta no es unidad");
                return false;
            }
        }else{
            if(!($("#check_inventario").is(":checked"))&&!($("#check_seleccion").is(":checked"))&&$("#check_receta").is(":checked")){
                showMessage("warning","Receta no puede ser la unica caracteristica");
                $("#check_venta").prop("checked",true);
                $("#check_venta").change();
                return false;
            }else if(!($("#check_inventario").is(":checked"))&&!($("#check_seleccion").is(":checked"))){
                showMessage("warning","El articulo debe tener al menos una caracteristica");
                $("#check_venta").prop("checked",true);
                $("#check_venta").change();
                return false;
            }
        }
    });

    $("#check_inventario").off("click").click(function () {
        $(".wstep").off("click");
        var me=$(this);
        if($(this).is(":checked")){
            if($("#check_seleccion").is(":checked") && window.articulo.get("inventarioEnt") && window.articulo.get("inventarioEnt").idUnidadAlterna){
                showMessage("warning","No puede activar inventario porque posee unidad alterna");
                me.prop("checked",false);
                me.change();
                return false;
            }
        }else{
            if(!($("#check_venta").is(":checked"))&&!($("#check_seleccion").is(":checked"))&&$("#check_receta").is(":checked")){
                showMessage("warning","Receta no puede ser la unica caracteristica");
                $("#check_inventario").prop("checked",true);
                $("#check_inventario").change();
                return false;
            }else if(!($("#check_venta").is(":checked"))&&!($("#check_seleccion").is(":checked"))){
                showMessage("warning","El articulo debe tener al menos una caracteristica");
                $("#check_inventario").prop("checked",true);
                $("#check_inventario").change();
                return false;
            }
        }
    });

    $("#check_receta").off("click").click(function () {
        $(".wstep").off("click");
        if(window.articulo.recetas && window.articulo.recetas.length > 0){
            $("#check_receta").prop("checked",true);
            $("#check_receta").change();
            showMessage("warning","No puede inactivar receta si el articulo tiene recetas");
            return false;
        }
    });
    
    $("#check_costo_estimado").off("click").click(function () {
    	$("#costo_estimado").prop("disabled", !$(this).is(":checked"));
    });
}

//change,keyup
function eventosVariosBasico(){
    //Evita que se pueda elegir solamente la caracteristica de receta ademas carga el html del paso
    $("#articulo_section").find(".checks").find("input[type='checkbox']").off("change").change(function(){
        if(!$("#check_inventario").is(":checked") && !$("#check_venta").is(":checked") && !$("#check_seleccion").is(":checked")){
            if($("#check_receta").is(":checked")){
                if(this.id=="check_receta"){
                    showMessage("error","Para que este articulo maneje receta debe tener cualquier otra caracteristica habilitada");
                    $("#check_receta").prop("checked",false);
                    $(".wstep.receta").addClass("ignore");
                }
            }
        }
        enable_steps($(this));
        
        if(!$("#check_inventario").is(":checked") && !$("#check_receta").is(":checked") && !$("#check_venta").is(":checked") && !$("#check_seleccion").is(":checked")){
            $(".actions.form-actions.full").find("button").addClass("end");
        }else{
            $(".actions.form-actions.full").find("button").removeClass("end");
        }
    });

    //muestra o esconde el texto para mostrar
    $("#basico_nombre").off("keyup").keyup(function(e){
        if($(this).val().length>60){
            e.preventDefault();
            return false;
        }
        if($(this).val().length>=40){
            $("#nombreMostrar").removeClass("hidden");
            $("#nombreMostrar").find("input").val($(this).val().substr(0, 40));
        }else{
            $("#nombreMostrar").addClass("hidden");
            $("#nombreMostrar").val("");
        }
    });

    if($("#check_seleccion").prop("checked")||$("#check_inventario").prop("checked")||$("#check_venta").prop("checked")){
        $(".actions.form-actions.full").removeClass("hidden");
    }
    closeChargeStep();
}

// Eventos disparados por el programa
function enable_steps(me){
    var index=me.closest("li").index()+1;
    if(me.is(":checked")){
        $(".wstep").eq(index).removeClass("ignore");
        $(".step").eq(index).removeClass("ignore");
    }else{
        $(".wstep").eq(index).addClass("ignore");
        $(".step").eq(index).addClass("ignore");
    }
    var steps=$(".wizard .step:not(.ignore)");
    if(steps.size(":not(.ignore)")>=2){
        steps.first().addClass("active");
        $(".wizard .wstep:first").addClass("active");
    }
    enableSaving();
}

/* 
 * Funcion que controla si el boton de guardar se muestra o no
 * Hay dos condiciones para activar el botón:
 *  1. Se ha seleccionado mas de una de las opciones de Venta, Inventario o Receta
 *  2. Se ha seleccionado la opción "Hará parte de una seleccion"
 */
function enableSaving(){
	var steps=$(".wizard .step:not(.ignore)");
	var esSeleccion = $("#check_seleccion").prop("checked");
    if(steps.size()<2 && !esSeleccion){
        $(".form-actions").addClass("hidden");
    }else{
        $(".form-actions").removeClass("hidden");
    }
}


function validaBasico(id,action){
    $("#basico_nombre").valida({longitud:{minimo:2,maximo:60},mostrarLongitud:true,mostrarMensaje:true});

    if(!$("#check_seleccion").is(":checked")&&!$("#check_inventario").is(":checked")&&!$("#check_receta").is(":checked")&&!$("#check_venta").is(":checked")){
        showMessage("warning","El articulo debe tener almenos una caracteristica");
        return;
    }
    
    if($("#check_costo_estimado").is(":checked") && $("#costo_estimado").val().length == 0){
        showMessage("warning","Debe indicar el costo estimado");
        return;
    }

    if($("#basico").find(".campo_mal").length<1){
        if(action=="ver"){
            var me=$("#check_seleccion");
            if(me.is(":checked")){
                var result=hallarEnSelecciones(me,id,action);
            }else{
                var result=guardarArticulo(id,action);
            }
        }else{
            var result=guardarArticulo(id,action);
        }

        return result;
    }else{
        $("#basico").find(".campo_mal").first().focus();
        closeChargeStep();
        return false;
    }
}

function hallarEnSelecciones(me,id,action){
    var message = "Quitar";
    me.seguro = function () {
        guardarArticulo(id,action);
    };
    me.cancelar = function () {
        $("#shadow_black").css({display:"none",opacity:0});
        $("#confirmar").css({display:"none"});
        close_floating();
        closeChargeStep();
        return false;
    };
    $.ajax({
        url: "./rest/grupoSeleccion/?idArticulo=" + id,
        type: "GET",
        success: function (data) {
            if (parseInt(data) > 0) {
                showConfirm(me, "Si hace esto afectara a " + data + " articulo(s) los cuales tienen a este articulo como parte de su selecciones", message);
                return false;
            }else{
                var result = guardarArticulo(id,action);
            }
        },
        error: function () {
            verifyConnection(function () {
                showMessage("error", "No fue posible hallar las selecciones");
            });
            close_floating();
            closeChargeStep();
            return false;
        }
    });
}

function guardarArticulo(id,action,redirect){
    action=window.globalAction;

    var nombre=$("#basico_nombre").val().toLowerCase().trim();
    var nombreImpresion =$("#basico_nombre_mostrar").val().toLowerCase().trim();
    var id=window.articulo.id;
    
    var a=window.articulos.where({nombre:nombre});

    if(window.globalAction=="crear"){
        var result=$.grep(window.articulos.models,function(obj){
            return obj.attributes.nombre==nombre;
        });

        if(result.length>0){
            showMessage("warning","Ese nombre ya esta configurado");
            closeChargeStep();
            return;
        }
    }else{
        var result=$.grep(window.articulos.models,function(obj){
            if(obj.attributes.id==id){
                return false;
            }
            return obj.attributes.nombre==nombre;
        });

        if(result.length>0){
            showMessage("warning","Ese nombre ya esta configurado");
            closeChargeStep();
            return;
        }
    }
    
    window.articulo.set("nombre", $("#basico_nombre").val());
    window.articulo.set("nombreImpresion", $("#basico_nombre_mostrar").val());
    window.articulo.set("codigobarras", $("#basico_codigo").val());
    window.articulo.set("descripcion", "");
    window.articulo.set("venta", $("#check_venta").prop("checked"));
    window.articulo.set("inventario", $("#check_inventario").prop("checked"));
    window.articulo.set("receta", $("#check_receta").prop("checked"));
    window.articulo.set("seleccion", $("#check_seleccion").prop("checked"));
    window.articulo.set("empaque", $("#es_empaque_check").prop("checked"));
    window.articulo.set("utilizaEmpaque", $("#utliza_empaque_check").prop("checked"));
    window.articulo.set("costoEstimado", $("#costo_estimado").val());

    chargestep=true;
    window.articulo.save(null, {
    	success:function(model, response){
    		if(!(redirect==undefined)){
	            Backbone.history.navigate('#lista/articulos/', { trigger: true });
	            return;
	        }
    		
    		if(!window.articulo.get("id")){
    			if(!isNaN(response) && typeof(response)==="number" && response>0){
                	window.articulo.set("id", response);
                }
    			
	        	if(!window.articuloYaCreado){
	        		window.articuloYaCreado=true;
	                window.articuloYaCreadoId=response;
	                showTable({tabla:"ventaPunto",tipo:"select_multiple",returnControl:false,todosLosPuntos:true});
	        	}
    		} else {
        		cargaCompleto(action,chargestep);
        		chargeStep(1, window.articulo.get("id"), action);
        		closeChargeStep();
        	}
    		
    		return true;
    	},
    	error:function(a,b,c){
    		console.log("error, a, b, c [" + a + ", " + b + ", " + c + "]");
    		closeChargeStep();
	        close_floating();
	        verifyConnection(showMessage("error","No se pudo guardar datos basicos"));
	    }
    });
    
    return false;
}

function guardarVentaPuntos(funcion, action){
    var flo= $(".floating_table");
    _puntos = window.articulo.get("puntos");
    
    var puntos=flo.find(":checked");
    if(puntos.length<1){
        return
    }
    puntos.each(function(){
        if(!this.id){
            return
        }
        idPunto = this.id;
        punto = $.grep(window.puntosCliente, function (u) {
            return u.id == idPunto;
        })[0];
        _puntos.push({
            id: this.id, 
            nombre : punto.nombre
        });
    });

    $.ajax({
        url: "./rest/articulo/" + window.articulo.get("id") + "/",
        type:"PUT",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(window.articulo),
        success:function(data){
        	if(window.globalAction=="crear"){
                cargaCompleto(action);
            }
            closeChargeStep();
            close_floating();

            $("#venta_puntos_table").closest(".form-group").removeClass("hidden");
            $("#venta_puntos_tableCross").closest(".form-group").removeClass("hidden");
            showTable({tabla:"ventaPunto",modo:"tabla",tipo:"save",id:window.articulo.get("id")});
        },
        error:function(){
        	verifyConnection(function(){showMessage("error","No fue posible guardar los puntos")});
            closeChargeStep();
            close_floating();
        }
    });
    
    return true;
}

//Esta funcion se usa en Venta e Inventario para actualizar la conversion entre unidades
function guardarConversion(nuevaConversion){
	conv = $.grep(window.conversiones, function (conversion) {
		if (conversion.idUnidadOrigen === nuevaConversion.idUnidadOrigen && conversion.idUnidadDestino === nuevaConversion.idUnidadDestino) {
			return true;
		}
		return false;
	})[0];
	
	if(conv && conv.factor === nuevaConversion.factor){
		return;
	}
	
	if(conv){
		conv.factor = nuevaConversion.factor;
		tipo = "PUT";
	}else{
		conv = {
			"idUnidadOrigen" : nuevaConversion.idUnidadOrigen,
			"idUnidadDestino" : nuevaConversion.idUnidadDestino,
			"factor" : nuevaConversion.factor,
			"idArticulo" : nuevaConversion.idArticulo
		}
		tipo = "POST";
	}
	
	$.ajax({
		url:"./rest/unidad/" + nuevaConversion.idUnidadOrigen + "/conversiones/",
		type:tipo,
		contentType:"application/json",
		async:false,
		data:JSON.stringify(conv),
		success:function(data){
			if(!conv.id){
				conv.id = data;
				window.conversiones.push(conv);
			}
		},
		error:function(){
			verifyConnection(showMessage("error","No se pudo actualizar la unidad"));
			return false;
		}
	});
}

function cargaCompleto(action,chargestep_){
	navegarPorIconos();
    closeChargeStep();
}

function navegarPorIconos(){
    $(".wstep").off("click").on("click",function() {
        if($(this).hasClass("active")){
            return;
        }

        $("#articulo_section .wrap div.step").removeClass("active");
        $("#articulo_section div.vertical_centrator .wstep").removeClass("active");

        if($(this).hasClass("datos_basicos")){
            $("#basico").addClass("active");
            $("#articulo_section div.vertical_centrator div.datos_basicos").addClass("active");
            showChargeStep();
            $("#articulo_section").find("button.back").addClass("hidden");
            cargarModuloBasico("","");

        }else if($(this).hasClass("venta")){
            $("#step_venta").addClass("active");
            $("#articulo_section div.vertical_centrator div.venta").addClass("active");
            $("#articulo_section").find("button.back").removeClass("hidden");
            cargarModuloVenta("","");

        }else if($(this).hasClass("receta")){
            $("#step_receta").addClass("active");
            $("#articulo_section div.vertical_centrator div.receta").addClass("active");
            $("#articulo_section").find("button.back").removeClass("hidden");
            cargarModuloReceta("","");

        }else if($(this).hasClass("inventario")){
            $("#step_inventario").addClass("active");
            $("#articulo_section div.vertical_centrator div.inventario").addClass("active");
            $("#articulo_section").find("button.back").removeClass("hidden");
            cargarModuloInventario("","");

        }else if($(this).hasClass("empaques")){
            $("#step_empaques").addClass("active");
            $(this).closest(".vertical_centrator").find(".active").removeClass("active");
            $(this).addClass("active");
            $("#articulo_section div.vertical_centrator div.empaques").addClass("active");
            $("#articulo_section").find("button.back").removeClass("hidden");
            cargarModuloEmpaque("","");

        }
    });
}

function eliminar_de_grupos(){
    alert("elimino de grupos");
    $("#shadow_black").css({display:"none",opacity:0});
    $("#confirmar").css({display:"none"});
    $("#confirmar_action").parent().html('Esta seguro de <span id="confirmar_action"></span> este registro?');
}