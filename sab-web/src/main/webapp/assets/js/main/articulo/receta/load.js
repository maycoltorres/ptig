function cargarModuloReceta(id,action){
     cargaCamposListaReceta(id,action,
        function(){
            cargaDatosReceta(id,action,
                function(){
                    eventosClickReceta(id,action);
                    eventosVariosReceta(id,action);
                    inicializacionReceta(id,action);
                    closeChargeStep();
                    $(".wstep").removeClass("active");
                    $(".wstep.receta").removeClass("ignore");
                    $(".wstep.receta").addClass("active");
                });
           // );
        });
}

//validacion al cargar el modulo despues de cargar los datos
function inicializacionReceta(id,action){

    var clase="unidad";
    nombreUnidad = window.articulo.get("nombre");
    
    if(window.articulo.get("inventarioEnt") && window.articulo.get("inventarioEnt").idUnidadKardex){
    	window.idUnidadParaReceta = parseInt(window.articulo.get("inventarioEnt").idUnidadKardex);
    } else if(window.articulo.get("ventaEnt") && window.articulo.get("ventaEnt").unidadesVenta){
    	window.idUnidadParaReceta = parseInt(window.unidadesVenta[0].idUnidad);
    }

    if($("#receta_unidad").find("option").not("option."+clase).length!=$("#receta_unidad").find("option").length){
        $("#receta_unidad").find("option").not("option."+clase).remove();
    }

    $("#nombre").limita({validaCon: "numeros,letras",otros:" ,-,_,"});
    $("#receta_cantidad_base").limita({validaCon: "numeros",otros:""});

    $("#receta_label_nombre").remove();

    $("#receta_unidad").parent().after('<div id="receta_label_nombre" class="col-lg-4 receta_nombre">'+
    '<label class="col-lg-4 control-label">'+" de " + nombreUnidad +'</label>'+
    '</div>');
    
    fillSelect(filterByList(window.unidadesEstandar, buscarUnidadesCompatibles(
    		window.conversiones.slice(), window.idUnidadParaReceta)), $("#receta_unidad"), "nombre");
    
}