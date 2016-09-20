function cargarModuloVenta(id,action){
	window.ventaYaCreada = true;
	if(!window.articulo.get("ventaEnt")){
        window.articulo.set("ventaEnt", {});
        window.ventaYaCreada = false;
    }
    
    if(window.articulo.get("ventaEnt").unidadesVenta){
    	window.unidadesVenta = window.articulo.get("ventaEnt").unidadesVenta.filter(function( obj ) {
            return obj.principal === true;
        });
    	
    	window.unidadesAlternasVenta = window.articulo.get("ventaEnt").unidadesVenta.filter(function( obj ) {
            return obj.principal === false;
        });
    }else{
    	window.unidadesVenta = [];
    	window.unidadesAlternasVenta = [];
    }
    
    if(window.articulo.get("ventaEnt").gruposSeleccion){
    	window.ventaGrupoSeleccion = window.articulo.get("ventaEnt").gruposSeleccion;
    }else{
    	window.ventaGrupoSeleccion = [];
    }
    
    eventosClickVenta(id,action);
    eventosVariosVenta(id,action);
    
    cargaCamposVenta(id, action,
        function(){
            cargaDatosVenta(id,action,
                function(){
                    inicializacionVenta(id,action);
                    cargarTablasVenta();
                    closeChargeStep();
                    createTable_venta_canal_impuesto();
                }
            );
        });
}

//validacion al cargar el modulo despues de cargar los datos
function inicializacionVenta(id,action){
	
	window.canalesImpuestos = [];
	$.grep(window.canales, function (e) {
		var idCanal = e.id;
		var canalImpuesto = undefined;
		
		if(window.articulo.get("ventaEnt").canalesImpuestos){
			canalImpuesto = window.articulo.get("ventaEnt").canalesImpuestos.filter(function( obj ) {
		        return +obj.idCanal === +idCanal;
		    })[0];
		}
		
		if(canalImpuesto){
			window.canalesImpuestos.push({
				"id":canalImpuesto.idCanal,
				"nombreCanal":e.nombre, 
				"idCanal": canalImpuesto.idCanal, 
				"idImpuesto":canalImpuesto.idImpuesto, 
				"nombreImpuesto": null, 
				"nombreRegimen": null, 
				"nombreTarifa":null, 
				"idTarifa":canalImpuesto.idTarifa
			});
		}else{
			window.canalesImpuestos.push({
				"id":e.id,
				"nombreCanal":e.nombre, 
				"idCanal":e.id, 
				"idImpuesto":null, 
				"nombreImpuesto": null, 
				"nombreRegimen": null, 
				"nombreTarifa":null, 
				"idTarifa":null
			});
		}
    });
	
	$.each(window.unidadesVenta, function(){
		this.nombreUnidad = findById(window.unidadesEstandar, this.idUnidad).nombre;
		this.nombreLista = findById(window.listasDePrecios, this.idListaPrecios).nombre;
		if(window.articulo.get("inventarioEnt") && window.articulo.get("inventarioEnt").idUnidadKardex){
			this.equivalencia = convertidor_de_unidades(this.idUnidad, window.articulo.get("inventarioEnt").idUnidadKardex, 1, window.articulo.get("id"));
		}
	});
	
	$.each(window.unidadesAlternasVenta, function(){
		this.nombreUnidad = findById(window.unidadesEstandar, this.idUnidad).nombre;
		this.nombreLista = findById(window.listasDePrecios, this.idListaPrecios).nombre;
		this.equivalencia = convertidor_de_unidades(this.idUnidad, window.unidadesVenta[0].idUnidad, 1, window.articulo.get("id"));
	});
	
	$.each(window.ventaGrupoSeleccion, function(){
		var idGrupoSeleccion = this.idGrupoSeleccion;
		var grupoSeleccion = window.gruposSeleccion.filter(function( obj ) {
	        return +obj.id === +idGrupoSeleccion;
	    })[ 0 ];
		this.nombre = grupoSeleccion.nombre;
    });
    
	//evitar que venta receta este solo
    if($("#basico").find("#check_inventario").is(":checked")==false&&$("#basico").find("#check_venta").is(":checked")==false){
        $("#basico").find("#check_receta").prop("checked",false);
        $("#basico").find("#check_receta").change();
    }

    if(action=="ver"){
        if($(".actions.form-actions.full").hasClass("hidden")&&$("#check_seleccion").prop("checked")){
            $(".actions.form-actions.full").removeClass("hidden");
        }
    }

    fillSelect(window.unidadesEstandar, $("#venta_u"), "nombre");

    //limitar caracteres al digitar
    $("#basico_nombre").limita({validaCon: "numeros,letras",otros:" ,-,_,"});

    //mostrar texto para imprimir
    if($("#basico_nombre").val().length>40){
        $("#nombreMostrar").removeClass("hidden");
    }

    if($(".wstep").nextAll().not(".ignore,.datos_basicos,.inventario,.venta").length>0){
        $(".actions.form-actions.full").find(".next").removeClass("end");
    }
}