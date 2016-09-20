function cargarModuloEmpaque(id,action){
    cargaListaEmpaques(id,action,
        function() {
            eventosListaEmpaques();
            eventosClickEmpaque(id, action);
            eventosVariosEmpaque(id, action);
            inicializacionEmpaque(id, action);
            closeChargeStep();
        }
    );
}

//validacion al cargar el modulo despues de cargar los datos
function inicializacionEmpaque(id,action){
    $(".wstep").removeClass("active");
    $(".wstep.Empaque").removeClass("ignore");
    $(".wstep.Empaque").addClass("active");

    /*
    var clase="unidad";
    if(!(megaConsulta[3][0]==undefined)&&megaConsulta[0][0].inventario==1){
        obj= $.grep(megaConsulta[6],function(unidad){
            return unidad.id==megaConsulta[3][0].id_unidad;
        });
        if(obj.length>0){
            clase=obj[0].type;
        }
    }else if(!(megaConsulta[13][0]==undefined)&&megaConsulta[0][0].venta==1){
        obj= $.grep(megaConsulta[6],function(unidad){
            return unidad.id==megaConsulta[13][0].unidadId;
        });
        if(obj.length>0){
            clase=obj[0].type;
        }
    }

    if($("#Empaque_unidad").find("option").not("option."+clase).length!=$("#Empaque_unidad").find("option").length){
        $("#Empaque_unidad").find("option").not("option."+clase).remove();
    }

    //$("#Empaque_unidad").prop("disabled",true);
    $("#nombre").limita({validaCon: "numeros,letras",otros:" ,-,_,"});
    $("#Empaque_cantidad_base").limita({validaCon: "numeros",otros:""});

    $("#Empaque_label_nombre").remove();

    $("#Empaque_unidad").parent().after('<div id="Empaque_label_nombre" class="col-lg-4 Empaque_nombre">'+
    '<label class="col-lg-4 control-label">'+" de "+megaConsulta[0][0].nombre+'</label>'+
    '</div>');
    */


}