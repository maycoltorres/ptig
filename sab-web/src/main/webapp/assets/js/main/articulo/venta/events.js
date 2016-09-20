function eventosClickVenta(id,action){
    function validateCamps(){
        if($("#venta_clase").find(":selected").val()<1||$("#venta_grupo").find(":selected").val()<1){
            showMessage("warning","Elija una clase y un grupo de venta");
            return false;
        }
        return true;
    }

    $("#venta_cip").off("click").click(function() {
        if(!validateCamps()){
            return
        }
        showTable({tabla:"canalesImpuestos",modo:"form",tipo:"floating",id:id});
    });
    
    $("#grupo_sel").off("click").on("click",function(){
        showTable({tabla: "venta_grupoSeleccion", modo: "form", tipo: "floating",all:true,custom:true});
    });

    $("#venta_unidad").off("click").click(function(){
        if(!validateCamps()){
            return;
        }
        showTable({tabla:"unidadPrincipal",modo:"form",tipo:"floating",id:id});
    });

    $("#venta_u_a").off("click").click(function(){
        if(!validateCamps()){
            return
        }
        if(window.unidadesVenta.length<=0){
            showMessage("error","Primero debe establecer una unidad principal");
            return;
        }
        showTable({tabla:"unidadAlternativa",modo:"form",tipo:"floating",id:id});
    });
    
    $("#tax_all_ch").off("click").click(function () {
    	var checked = $(this).is(":checked");
		 $('#ch_tax_rate_tableCross tr').each(function(){
			 var tr=$(this).closest("tr");
			 if(tr.index() == 0){
				 tr.find(".impuestos_config select").prop("disabled", false);
			 }else{
				 tr.find(".impuestos_config select").prop("disabled", checked);
			 }
		 });
    });
    
    $("#rate_all_ch").off("click").click(function () {
    	var checked = $(this).is(":checked");
		 $('#ch_tax_rate_tableCross tr').each(function(){
			 var tr=$(this).closest("tr");
			 if(tr.index() == 0){
				 tr.find(".tarifas select").prop("disabled", false);
			 }else{
				 tr.find(".tarifas select").prop("disabled", checked);
			 }
		 });
    });
}

function showVentaGrupoSeleccionTable(ops){
    ops.html = $("#venta_seleccion").html();
    ops.titulo = "Grupo seleccion";
    ops.destino = $("#grupo_s_tableCross");
    ops.workWithObject=true;
    
    if(ops.tipo=="save"){
        ops.cols = [
            {head: "id", proper: "idGrupoSeleccion",class:"hidden grupoSeleccionId"},
            {proper:"nombre",head:"Nombre",class:"grupoNombre"},
            {proper:"tipoGrupo",head:"Tipo",mask:{true:"1",false:"0"},class:"centrate hidden tipo"},
            {proper:"cantidad",head:"cantidad de articulos",class:"centrate cantidad"}            
        ];
    }else{
        ops.cols = [
            {head: "id", proper: "idGrupoSeleccion",class:"hidden grupoSeleccionId"},
            {proper:"nombre",head:"Nombre",class:"grupoNombre"},
            {proper:"tipoGrupo",head:"Tipo",mask:{true:"1",false:"0"},class:"centrate tipo hidden"}
        ];
    }

    ops.asignar = function (data) {
    }
    
    if(ops.all){
    	ops.workObject=window.gruposSeleccion;
    }else{
    	ops.workObject=window.ventaGrupoSeleccion;
    }

    ops.prevDelete=function(id,button){

    }
    ops.urlDelete = "#";
    ops.deleteFunction = function (id, button) {

        var name = button.closest("tr").find(".grupoNombre").html();
        var c = 0;
        var t = null;
        $.grep(window.ventaGrupoSeleccion, function (e) {
            if (e.nombre == name) {
                t = c;
            }
            c++;
        });
        if (t != null) {
            window.ventaGrupoSeleccion.splice(t, 1);
        }
        showTable({tabla:"venta_grupoSeleccion",modo:"tabla",tipo:"save"});
    }
    
    ops.submitOfSelection = function () {

        var flo = $(".floating_table.clon_floating");
        var flo2 = $(".floating_form.clon_floating");
        var sel = extraerSeleccionados(flo);

        if(!(sel==undefined)&&sel.length>0){
            flo2.find("#grupo_tipo").val($(sel).closest("tr").find(".tipo").html());
            if($(sel).closest("tr").find(".tipo").html()=="1"){
                flo2.find("#venta_can").val("");
                flo2.find("#venta_can").closest(".form-group").addClass("hidden");

            }else{
                flo2.find("#venta_can").closest(".form-group").removeClass("hidden");
                flo2.find("#venta_can").val("");
            }
        }
    }
    ops.submit = function () {
        var flo = $(".floating_form.clon_floating");
        
        if (!existWithVal(flo.find("#venta_seleccion_e").data("ids"))) {
            flo.find("#venta_seleccion_e").campoMal({mostrarMensaje: false, mostrarLongitud: false});
            return;
        } else {
            flo.find("#venta_seleccion_e").campoBien();
        }

        flo.find("#venta_can").valida({longitud:{minimo:1,maximo:4},validaCon: "numeros", mostrarLongitud:false,mostrarMensaje:false, permitirEnBlanco:false});

        if (flo.find(".campo_mal").not(".hidden").length > 0) {
            return false;
        }

        if(parseInt(flo.find("#venta_can").val())<1){
            flo.find("#venta_can").campoMal({mostrarLongitud:false,mensajeParticular:"Digite cantidad mayor a 0"});
            return false;
        }

        var idGrupoSeleccion=parseInt(flo.find("#venta_seleccion_e").data("ids")[0]);
        
        if(!ops.fila){
        	var encontrado=false;
        	$.grep(window.ventaGrupoSeleccion, function (e) {
                if (e.idGrupoSeleccion === idGrupoSeleccion) {
                	showMessage("error","El grupo seleccionado ya esta incluido en la configuracion de Venta");
                	encontrado = true;
                }
            });
        	if(encontrado){
        		return false;
        	}
        }

        if (ops.fila) {
            idGrupoSeleccion = parseInt(ops.fila.find('.grupoSeleccionId').html());
            var c = 0;
            var t = null;
            $.grep(window.ventaGrupoSeleccion, function (e) {
                if (e.idGrupoSeleccion === idGrupoSeleccion) {
                    t=c;
                }
                c++;
            });
            if(t != null){
            	window.ventaGrupoSeleccion.splice(t, 1);
            }
        } 
        
        object = {
            'id': idGrupoSeleccion,
            "nombre": flo.find("#venta_seleccion_e").html(),
            "tipoGrupo": flo.find("#tipo_grupo").val(),
            "cantidad": flo.find("#venta_can").val(),
            "idGrupoSeleccion":idGrupoSeleccion
        }
        window.ventaGrupoSeleccion.push(object);

        close_floating();
        showTable({tabla:"venta_grupoSeleccion",modo:"tabla",tipo:"save"});

    };
    ops.tableCreated = function (table, datatable, clon) {

        if($(".clon_floating").find(".nombre").length>0){
            datatable=clon.find("table").DataTable();
            $.each(window.ventaGruposeleccion,function(){
                console.log(clon.find("td.nombre:contains('"+this.grupoNombre+"')"));
                tr=clon.find("td.nombre:contains('"+this.grupoNombre+"')").closest("tr");
                row=datatable.row(tr);
                row.remove();
                tr.remove();
                //tr.addClass("hidden");
            });
        }
    };
    ops.floating_created = function (floating) {
        floating.find("#venta_can").limita({validaCon: "numeros"});
        if(ops.fila!=undefined){
            if(!(ops.fila.find('.cantidad').html()=='')){
                floating.find("#venta_can").closest('.form-group').removeClass('hidden');
                floating.find("#venta_can").val(ops.fila.find('.cantidad').html());
            }
            floating.find("#venta_seleccion_e").addClass('label_disabled');
            floating.find("#venta_seleccion_e").html(ops.fila.find('.grupoNombre').html());
            floating.find("#venta_seleccion_e").data('ids',[ops.fila.find('.grupoSeleccionId').html()]);
        }else{
            floating.find("#venta_seleccion_e").click(function () {
                showTable({tabla:"venta_grupoSeleccion",type: "table",tipo:"select_one",returnControl:$(this),all:true});
            });
        }
    };
    return ops;
}

//change,keyup
function eventosVariosVenta(){
	$("#articulo_section").find("#venta_clase").change(function(){
    	if (!existWithVal($(this).find(":selected").attr("value"))) {
            return;
        }
        var ajax2=$.ajax({
            url:"./rest/cliente/clase/" + this.value + "/grupos",
            success:function(data){
                fillSelect(data,$("#articulo_section").find("#venta_grupo"),"nombre","type","", function(){
                	$("#venta_grupo").append('<option class="empty"></option>');
                	if(window.articulo.get("ventaEnt").idGrupoArticulo){
                		$("#venta_grupo option[value='" + window.articulo.get("ventaEnt").idGrupoArticulo +"']").prop("selected", "selected"); 
                	}
                });
            },
            error:function(){verifyConnection(function(){
               showMessage("error","No fue posible consultar las unidades alternativas");
           })}
        });
        
    });
    
}

// Eventos disparados por el programa
function validaVenta(id,action){
    $("#venta_clase").valida();
    $("#venta_grupo").valida();

    if($("#ch_tax_rate_tableCross").find("tbody").find("tr").find(".check_config").filter(":checked").length<1){
        showMessage("error","Debe seleccionar y completar la informacion de canales, impuestos y tarifas");
        return;
    }

    if($("#unidad_p_tableCross").find("tbody").find("td.nombreUnidad").not(".empty").length<1){
        showMessage("error","Debe configurar minimo 1 unidad de venta");
        return;
    }
    if(validarCanalesImpuestos()===false){
        showMessage("warning","Debe completar la informacion de las filas seleccionadas");
        return;
    }
    if($("#step_venta").find(".campo_mal").length<1) {
        guardarVenta();
    }else{
    	showMessage("warning","Debe completar la informacion requerida");
        $(".campo_mal").first().focus();
        return false;
    }
}


function guardarVenta(id,action,redirect) {
    showChargeStep();
    
    window.articulo.get("ventaEnt").idArticulo = window.articulo.get("id");
    window.articulo.get("ventaEnt").idGrupoArticulo = $("#venta_grupo").val();
    
    window.articulo.get("ventaEnt").canalesImpuestos = [];
    $("#ch_tax_rate_tableCross").find("tbody").find("tr").find(".check_config").filter(":checked").each(function(){
        var tr=$(this).closest("tr");
        window.articulo.get("ventaEnt").canalesImpuestos.push({
    		"idCanal": tr.find(".id").html(),
    		"idRegimen": 1,
    		"idTarifa": tr.find(".tarifas select").val(),
    	});
    });
        
    window.articulo.get("ventaEnt").unidadesVenta = [];
    $.each(window.unidadesVenta, function(){
    	window.articulo.get("ventaEnt").unidadesVenta.push({
    		"idUnidad" : this.idUnidad,
    		"idListaPrecios" : this.idListaPrecios,
    		"valor" : this.valor,
    		"principal" : true
    	});
    });
    
    $.each(window.unidadesAlternasVenta, function(){
    	window.articulo.get("ventaEnt").unidadesVenta.push({
    		"idUnidad" : this.idUnidad,
    		"idListaPrecios" : this.idListaPrecios,
    		"valor" : this.valor,
    		"principal" : false
    	});
    });
    
    window.articulo.get("ventaEnt").gruposSeleccion = [];
    $.each(window.ventaGrupoSeleccion, function(){
    	window.articulo.get("ventaEnt").gruposSeleccion.push({
    		"idGrupoSeleccion" : this.idGrupoSeleccion,
    		"cantidad" : this.cantidad
    	});
    });
    
    if(window.ventaYaCreada){
    	operacion = "PUT";
    }else{
    	operacion = "POST"
    }
    
    url= "./rest/articulo/" + window.articulo.id + "/venta/";
    $.ajax({
        url: url,
        type: operacion,
        contentType: "application/json",
        data: JSON.stringify(window.articulo.get("ventaEnt")),
        success: function (data) {
            showMessage("good", "La venta del articulo se ha guardado");
            window.ventaYaCreada = true;
            if (!(redirect == undefined)) {
                Backbone.history.navigate('#lista/articulos/', {trigger: true});
                return;
            }
            chargeStep(3, window.articulo.get("id"), action);
            close_floating();
            closeChargeStep();
        },
        error: function () {
            $("#articulo_section").find("button.back").click();
            verifyConnection(showMessage("error", "No se pudo guardar venta"));
            close_floating();
            return false;
        }
    });
}

function createTable_venta_canal_impuesto(){
    var tarifas_html="<option class='empty'>&nbsp;</option>";
    var impuestos_html="<option class='empty'>&nbsp;</option>";
    var regimenes_nombres=[];
    var regimenes_ids=[];
    
    var cols = [
                {embed: '<input type="checkbox" class="check_config form-control">', head: '', class: "check_canal"},
                {proper: "id", head: 'id', class: "id hidden"},
                {proper: "nombreCanal", head: 'Canal', class: "canal"},
                {embed: "<select id='select_impuesto'>" + impuestos_html + "</select>", head: 'Impuesto', class: "impuestos_config"},
                {embed: '<label class="regimen_config"></label>', head: 'Regimen', class: "regimen_config"},
                {embed: "<select class='tarifa_config' >"+tarifas_html+"</select>", head: 'Tarifa', class: "tarifas"}
            ];
    
    $("#ch_tax_rate_tableCross").html("");
    var table = creator_tables(window.canalesImpuestos, cols,$("#ch_tax_rate_tableCross"));

    function asignarRegimen(tr){
    	var idCanal=parseInt(tr.find("td.id").html());
        var idImpuesto=parseInt(tr.find("td.impuestos_config").find("select").val());
        var canalImpuesto;
        
        if(isNaN(idImpuesto)){
        	tr.find("td.regimen_config").html("");
        	tr.find("td.tarifas").find("select").children().remove().end().append("");
        	return;
        }
        
        var nombreRegimen = "";
        var idRegimen = 0;
        $.grep(window.impuestosCliente, function(e){
        	if(e.idCanal === idCanal && e.idImpuesto === idImpuesto){
        		idRegimen = e.idRegimen;
        		nombreRegimen = e.regimen;
        	}
        });
        tr.find("td.regimen_config").html(nombreRegimen);
        
        tarifas_html = "<option class='empty'>&nbsp;</option>";
        impuesto = findById(window.impuestos, idImpuesto);
        regimen = findById(impuesto.regimenes, idRegimen);
        
        if(window.canalesImpuestos){
        	canalImpuesto = findById(window.canalesImpuestos, idCanal);
        }
        
        $.grep(regimen.tarifas, function(t){
        	if(canalImpuesto && canalImpuesto.idTarifa === t.id){
        		tarifas_html+='<option value="' + t.id+ '" selected="true">' + t.valor+ '</option>';
        	}else{
        		tarifas_html+='<option value="' + t.id+ '">' + t.valor+ '</option>';
        	}
        });
        
        tr.find("td.tarifas").find("select").children().remove().end().append(tarifas_html);
    }
    
    function adicionarImpuestos(tr){
        var idCanal=parseInt(tr.find("td.id").html());
        var canalImpuesto;
        
        if(window.canalesImpuestos){
        	canalImpuesto = findById(window.canalesImpuestos, idCanal);
        }
        
        if(canalImpuesto && canalImpuesto.idImpuesto){
			tr.find("td.check_canal").find(".check_config").prop("checked", true);
        }
        
        impuestos_html = "<option class='empty'>&nbsp;</option>";

        $.grep(window.impuestosCliente, function(e){
        	if(e.idCanal === idCanal){
        		if(canalImpuesto && canalImpuesto.idImpuesto === e.idImpuesto){
        			impuestos_html+='<option value="' + e.idImpuesto + '" selected="true">' + e.impuesto+ '</option>';
        		}else{
        			impuestos_html+='<option value="' + e.idImpuesto + '">' + e.impuesto+ '</option>';
        		}
        	}
        });
        
        tr.find("td.impuestos_config").find("select").children().remove().end().append(impuestos_html);
    }
    
    $('#ch_tax_rate_tableCross tr').each(function(){
    	adicionarImpuestos($(this));
    	asignarRegimen($(this));
    });

    table.find("td.impuestos_config select").off("change").on('change',function(){
        asignarRegimen($(this).closest('tr'));
        
        //Reflejar este cambio en todos los demás select
    	if($("#tax_all_ch").is(":checked") && !$(this).prop("disabled")){
        	var idImpuesto = $(this).val();
        	var index = $(this).closest('tr').index();
        	$("#ch_tax_rate_tableCross").find("tbody").find("tr").each(function(){
        		if($(this).index() !== index){
	        		$(this).find(".impuestos_config select").val(idImpuesto);
	        		asignarRegimen($(this));
        		}
        	});
        }
    });
    
    table.find("td.tarifas select").off("change").on('change',function(){
        if($("#rate_all_ch").is(":checked")){
        	//Reflejar este cambio en todos los demás select
        	var idTarifa = $(this).val();
        	$("#ch_tax_rate_tableCross").find("tbody").find("tr").find(".tarifas select").each(function(){
        		$(this).val(idTarifa);
        	});
        }
    });
    
}

function validarCanalesImpuestos(){
    var notify=false;
    $("#ch_tax_rate_tableCross").find("tbody").find("tr").find(".check_config").filter(":checked").each(function(){
        var tr=$(this).closest("tr");
        if(isNaN(parseInt(tr.find(".impuestos_config select").val())) || isNaN(parseInt(tr.find(".tarifas select").val()))){
            notify=true;
        }
    });
    if(notify){
        return false;
    }
    return true;
}