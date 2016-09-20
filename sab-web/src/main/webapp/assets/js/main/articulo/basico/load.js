function cargarModuloBasico(id,action){
    cargarData();
    mostrarConfiguracion_articulos();
    inicializacionBasico(id,action);
    eventosClickBasico(id,action);
    eventosVariosBasico(id,action);
}

//validacion al cargar el modulo despues de cargar los datos
function inicializacionBasico(id,action){

    //evitar que venta receta este solo
    if($("#basico").find("#check_seleccion").is(":checked")==false&&$("#basico").find("#check_inventario").is(":checked")==false&&$("#basico").find("#check_venta").is(":checked")==false){
        $("#basico").find("#check_receta").prop("checked",false);
        $("#basico").find("#check_receta").change();
    }

    if(action=="ver"||window.articuloYaCreado||window.actionGlobal=="ver"){

        if(window.articulo.get("nombre") && window.articulo.get("nombre").length >= 40){
            $("#nombreMostrar").removeClass("hidden");
        }

        $("#venta_puntos_table").closest(".form-group").removeClass("hidden");
        $("#venta_puntos_tableCross").closest(".form-group").removeClass("hidden");
        showTable({tabla:"ventaPunto",modo:"tabla",tipo:"save",id:window.articulo.get("id")});

    }else{
        $("#venta_puntos_table").closest(".form-group").addClass("hidden");
        $("#venta_puntos_tableCross").closest(".form-group").addClass("hidden");
    }

    if($(".wstep").nextAll().not(".ignore,.datos_basicos").length>0){
        $(".actions.form-actions.full").find(".next").removeClass("end");
    }


/*
    //forzar la consecucion en la creacion
    if(action=="crear"){
        if($("#check_inventario").prop("checked")==false){
            $("#check_venta").prop("checked",false);
            $("#check_venta").change();
            $("#check_receta").prop("checked",false);
            $("#check_receta").change();
        }
    }
*/
    //limitar caracteres al digitar
    //$("#basico_nombre").limita({validaCon: "numeros,letras",otros:" ,-,_,"});

    //mostrar texto para imprimir
   /*
    if($("#basico_nombre").val().length>40){
        $("#nombreMostrar").removeClass("hidden");
    }
    */
}

function cargarData(){
	var ajax=$.ajax({
       url : "./rest/punto/?idCliente=" + usuario.get("idCliente"),
       success:function(data){
            window.puntosCliente=data;
        },
        error:function(){verifyConnection(function(){
            showMessage("error","No fue posible consultar todos los puntos del cliente");
        })}
    });
	
	var ajaxUnidadesEstandar=$.ajax({
       url:"./rest/unidad/?mostrar='estandar'",
       success:function(data){
            window.unidadesEstandar=data;
        },
        error:function(){verifyConnection(function(){
            showMessage("error","No fue posible consultar las unidades");
        })}
    });

    var ajaxUnidadesAlternas=$.ajax({
        url:"./rest/unidad/?mostrar='todas'",
        success:function(data){
            window.unidadesAlternas=data;
        },
        error:function(){verifyConnection(function(){
           showMessage("error","No fue posible consultar las unidades alternativas");
       })}
    });
    
    var ajaxConversiones=$.ajax({
        url:"./rest/unidad/conversiones/",
        success:function(data){
            window.conversiones = data;
        },
        error:function(){verifyConnection(function(){
           showMessage("error","No fue posible consultar las conversiones de unidad");
       })}
    });

	
    if(window.actionGlobal=="ver"){
    }else{
        $("#basico_nombre").val("");
        $("#basico_nombre_mostrar").val("");
        $("#basico_codigo").val("");
        $("#check_venta").prop("checked",false);
        $("#check_inventario").prop("checked",false);
        $("#check_receta").prop("checked",false);
        $("#check_seleccion").prop("checked",false);
    }
}