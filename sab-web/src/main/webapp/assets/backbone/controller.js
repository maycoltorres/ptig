function convertidor_de_unidades($unidad_inicial, $unidad_final, $valor_inicial, $idArticulo){
	unidadOrigen = parseInt($unidad_inicial);
	unidadDestino = parseInt($unidad_final);
	
	if($idArticulo){
		var conversiones = window.conversiones.filter(function( obj ) {
	        return (!obj.idArticulo || obj.idArticulo === $idArticulo);
	    });
	}else{
		var conversiones = window.conversiones;
	}
	
	//Buscar unidad_origen y unidad_destino
	var factor = 0;
	$.grep(conversiones, function (conversion) {
        if (conversion.idUnidadOrigen === unidadOrigen && conversion.idUnidadDestino === unidadDestino) {
            factor = conversion.factor;
        }
    });
	
	if(factor === 0){
		//Buscar en sentido contrario, unidad destino y unidad origen
		$.grep(conversiones, function (conversion) {
	        if (conversion.idUnidadOrigen === unidadDestino && conversion.idUnidadDestino === unidadOrigen) {
	            factor =  1/conversion.factor;
	        }
		});
	}
	
	return ($valor_inicial * factor);
}

/*
 * Funcion que determina si el factor de conversion entre dos unidades es modificable por el usuario
*/
function esConversionEditable($idUnidadOrigen, $idUnidadDestino){
	var unidadOrigen = findById(window.unidadesEstandar, parseInt($idUnidadOrigen));
	var unidadDestino = findById(window.unidadesEstandar, parseInt($idUnidadDestino));
	
	if(unidadOrigen.estandar && unidadDestino.estandar){
		if(unidadOrigen.tipo === "Unidad" || unidadDestino.tipo === "Unidad"){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}

function filterByList(data, filter){
	var result = [];
	$.grep(filter, function(id){
		result.push(findById(window.unidadesEstandar, id));
	});
	return result;
}

function buscarUnidadesCompatibles(conversiones, idUnidad){
    var results = [];
    
    $.grep(conversiones, function (conversion, index) {
    	var compatible = false;
    	if (conversion && conversion.idUnidadOrigen === idUnidad && conversion.factor > 0) {
    		var idUnidadCompatible = conversion.idUnidadDestino;
            compatible = true;
        } else if(conversion && conversion.idUnidadDestino === idUnidad && conversion.factor > 0) {
    		var idUnidadCompatible = conversion.idUnidadOrigen;
            compatible = true;
        }
    	if(compatible){
    		results.push(idUnidadCompatible);
    	}
    });
    return results;
}

function buscarTodasUnidadesCompatibles(conversiones, idUnidad){
    var results = [];
    
    $.grep(conversiones, function (conversion, index) {
    	var compatible = false;
    	if (conversion && conversion.idUnidadOrigen === idUnidad && conversion.factor > 0) {
    		var idUnidadCompatible = conversion.idUnidadDestino;
            compatible = true;
        } else if(conversion && conversion.idUnidadDestino === idUnidad && conversion.factor > 0) {
    		var idUnidadCompatible = conversion.idUnidadOrigen;
            compatible = true;
        }
    	if(compatible){
    		results.push(idUnidadCompatible);
            conversiones.splice(index, 1);
            results.push.apply(results, buscarUnidadesCompatibles(conversiones, idUnidadCompatible));
    	}
    });
    return results;
}

function showChargeStep(){
    $("#chargindDiv").removeClass("hidden");
}

function closeChargeStep(){
    $("#chargindDiv").addClass("hidden");

}

function chargeStep(step,id,action){
	$(".actions.form-actions.full").find(".next").addClass("continue").click();    switch(step){
        case 1:
            if($("#check_inventario").is(":checked")){
                cargarModuloInventario(id,action);
            }else if($("#check_venta").is(":checked")){
                cargarModuloVenta(id,action);
            }else if($("#check_receta").is(":checked")) {
                cargarModuloReceta(action);
            }else if($("#utliza_empaque_check").is(":checked")){
                cargarModuloEmpaque(action);
            }else{
                Backbone.history.navigate("#lista/articulos/", { trigger: true });
            }

            break;
        case 2:
            if($("#check_venta").is(":checked")){
                cargarModuloVenta(action);
            }else if($("#check_receta").is(":checked")) {
                cargarModuloReceta(action);
            }else{
                Backbone.history.navigate("#lista/articulos/", { trigger: true });
            }

            break;
        case 3:
            if($("#check_receta").is(":checked")) {
                cargarModuloReceta(action);

            }else{
                Backbone.history.navigate("#lista/articulos/", { trigger: true });
            }

            break;
        default:
            Backbone.history.navigate("#lista/articulos/", { trigger: true });
            break;
    }
}

function quitOfTable(flo,table,selector,message,type,funcion){
    var table=table.dataTable();
    
    var ids=table.$("td");
    
    if(ids.length<1){
        return
    }

    //console.log(flo.find("input[id='"+$(this).html()+"']"));
    //console.log(type==""||type==undefined||type==null);
    //alert("pregonta");
    if(type==""||type==undefined||type==null){
        //alert("llego a");
        var inputs=flo.find("tbody").find("input");
        //console.log(inputs);
        //alert("llego e");
        ids.each(function(){
            if($(this).hasClass(selector)){
                inputs.filter("input[id='"+$(this).html()+"']").parent().parent().addClass("hidden").remove();
      //        console.log($(this).attr("id"));
            }
        //  console.log(inputs.filter("input[id='"+$(this).html()+"']"));

        });
        //console.log(inputs);
        inputs=flo.find("tbody").find("input");
        if(inputs.length<1){
            showMessage("warning",message);
            close_floating();
        }
    }else{
        //alert("entro a otro");
        funcion(ids);
    }

}

function chargeIds(clon,containerTable,tdClass){
    var table=containerTable.find("table").DataTable();
    var ids=table.$("td."+tdClass);
    ids.each(function(){
        clon.find("input[id='" + $(this).html() + "']").prop("checked", true).prop("disabled",true);
    });
}

function findById(source, id) {
    return source.filter(function( obj ) {
        return +obj.id === +id;
    })[ 0 ];
}

