//aqui estaran los eventos tratan con ajax
function cargaListaEmpaques(id,action,funcion){
	showTable({tabla:"tablaListadoEmpaques",modo:"tabla",tipo:"save"});
    funcion();
}

function eventosListaEmpaques(){

}


function cargaCamposEmpaque(id,action,funcion){


    /*
    $.ajax({
        url:"assets/Empaque/cantidadBase.json",
        success:function(Empaque){
            $("#Empaque_guardar #nombre").val("");
            $("#Empaque_guardar #Empaque_unidad").html("");
            $("#Empaque_guardar #Empaque_cantidad_base").val("");
            $("#Empaque_guardar #canales_d").html("");
            $("#Empaque_guardar #canales_d").removeData();
            $("#Empaque_puntos_tableCross").html("");
            $("#ingredientes_tableCross").html("");
            $("#grupo_s_tableCross").html("");
            $("#Empaque_lista,#Empaque_limpiar").addClass("hidden");
            $("#Empaque_guardar").removeClass("hidden");
        },
        error:function(){
            verifyConnection(showMessage("error","No ha sido posible la cargar la unidad base"));
        }
    });
      */

}


function cargaDatosEmpaque(id,action,funcion){

}

//guarda la configuracion de la vista
function guardarEmpaquePuntos(funcion){

    var data="";
    $.ajax({
        url:"",
        type:"POST",
        data:JSON.stringify,
        success:function(data){
            showMessage("good","Los puntos se han guardado");
            closeChargeStep();
            close_floating();
            showTable({tabla:"ventaPunto",modo:"tabla",tipo:"save",id:window.articulo.id});
            //funcion();
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible guardar los puntos")});
        }
    });
}

function guardarEmpaqueCanales(funcion){
    $.ajax({
        type:"POST",
        data:JSON.stringify,
        success:function(data){
            showMessage("good","Los puntos se han guardado");
            closeChargeStep();
            close_floating();
            showTable({tabla:"ventaPunto",modo:"tabla",tipo:"save",id:window.articulo.id});
            //funcion();
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible guardar los canales")});
        }
    });
}

function guardarEmpaqueUnidad(funcion){
    $.ajax({
        type:"POST",
        data:JSON.stringify,
        success:function(data){
            showMessage("good","Los puntos se han guardado");
            closeChargeStep();
            close_floating();
            showTable({tabla:"ventaPunto",modo:"tabla",tipo:"save",id:window.articulo.id});
            //funcion();
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible guardar los unidad")});
        }
    });
}


function cargarTablasEmpaque(){
    showTable({tabla:"EmpaquePunto",modo:"tabla",tipo:"save",id:window.articulo.id});
    showTable({tabla:"EmpaqueCanales",modo:"tabla",tipo:"save",id:window.articulo.id});
    showTable({tabla:"EmpaqueUnidad",modo:"tabla",tipo:"save",id:window.articulo.id});
    showTable({tabla:"EmpaqueSeleccion",modo:"tabla",tipo:"save",id:window.articulo.id});
}
