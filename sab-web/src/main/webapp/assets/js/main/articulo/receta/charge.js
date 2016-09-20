//aqui estaran los eventos tratan con ajax
function cargaCamposListaReceta(id,action,funcion){
	var canalesCliente = $.ajax({
        url : "./rest/cliente/" + usuario.get("idCliente") + "/canales/",
        type:"GET",
        async: false,
        success:function(data){
        	window.canales = data;
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible cargar los canales que aplican al cliente")});
        }
    });
	showTable({tabla:"listaReceta", modo:"tabla", tipo:"save", custom:true});
    funcion();
}

function cargaCamposReceta(id,action,funcion){

    /*
    $.ajax({
        url:"assets/receta/cantidadBase.json",
        success:function(receta){
            $("#receta_guardar #nombre").val("");
            $("#receta_guardar #receta_unidad").html("");
            $("#receta_guardar #receta_cantidad_base").val("");
            $("#receta_guardar #canales_d").html("");
            $("#receta_guardar #canales_d").removeData();
            $("#receta_puntos_tableCross").html("");
            $("#ingredientes_tableCross").html("");
            $("#grupo_s_tableCross").html("");
            $("#receta_lista,#receta_limpiar").addClass("hidden");
            $("#receta_guardar").removeClass("hidden");
        },
        error:function(){
            verifyConnection(showMessage("error","No ha sido posible la cargar la unidad base"));
        }
    });
      */

}


function cargaDatosReceta(id,action,funcion){

    if(action=="ver"){
        $.ajax({
            url:"./rest/articulo/" + window.articulo.id + "/recetas/",
            type:"GET",
            success:function(data){
                window.recetaLista=data;
                funcion();
            },
            error:function(){
                close_floating();
                close_floating();
                verifyConnection(function(){showMessage("error","No fue posible cargar los datos de la receta")});
            }
        });
    }else{
        funcion();
    }
}

//guarda la configuracion de la vista
function guardarRecetaPuntos(funcion){

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

function guardarRecetaCanales(funcion){
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

function guardarRecetaUnidad(funcion){
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


function cargarTablasReceta(){
    showTable({tabla:"recetaPunto",modo:"tabla",tipo:"save",id:window.articulo.id});
    showTable({tabla:"recetaCanales",modo:"tabla",tipo:"save",id:window.articulo.id});
    showTable({tabla:"recetaUnidad",modo:"tabla",tipo:"save",id:window.articulo.id});
    showTable({tabla:"recetaSeleccion",modo:"tabla",tipo:"save",id:window.articulo.id});
}
