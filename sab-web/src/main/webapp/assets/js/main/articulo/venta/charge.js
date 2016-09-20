//aqui estaran los eventos tratan con ajax
function cargaCamposVenta(id,action,funcion){
    var a=$.ajax({
    	url:"./rest/cliente/clase/?venta=true",
    	success:function(data){
            fillSelect(data,$("#venta_clase"),"nombre","","", function(){
	            if(window.articulo.get("ventaEnt").idClaseArticulo){
	        		$("#venta_clase option[value='" + window.articulo.get("ventaEnt").idClaseArticulo +"']").prop("selected", "selected");
	        		$("#venta_clase").trigger("change");
	        	}
            });
        },
        error:function(){
        	verifyConnection(function(){
        		showMessage("error","No fue posible consultar la lista de clases");
    		})
    	}
    });
    
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
    
    var impuestos = $.ajax({
        url : "./rest/common/impuestos/",
        type:"GET",
        async: false,
        success:function(data){
        	window.impuestos = data;
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible cargar todos los impuestos")});
        }
    });
    
    var canalesImpuestosCliente = $.ajax({
        url : "./rest/cliente/" + usuario.get("idCliente") + "/canalImpuesto/",
        type:"GET",
        async: false,
        success:function(data){
        	window.impuestosCliente = data;
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible cargar la configuracion de impuestos por canal para el cliente")});
        }
    });
    
    var listasPrecios = $.ajax({
        url : "./rest/cliente/" + usuario.get("idCliente") + "/listasPrecios?mostrar=activos",
        type:"GET",
        async:false,
        success:function(data){
            window.listasDePrecios = data;
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible cargar las listas de precios")});
        }
    });
    
    var gruposSeleccion = $.ajax({
        url : "./rest/grupoSeleccion/?mostrar=activos",
        type:"GET",
        async:false,
        success:function(data){
            window.gruposSeleccion = data;
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible cargar los grupos de seleccion")});
        }
    });
    
    funcion();
}

function cargarTablasVenta(){
    showTable({tabla:"unidadPrincipal", modo:"tabla", tipo:"save"});
    showTable({tabla:"venta_grupoSeleccion",modo:"tabla",tipo:"save"});
    showTable({tabla:"unidadAlternativa", modo:"tabla", tipo:"save"});
}

//carga la onfiguracion de que se botones  van a estar disponibles
//guarda la configuracion de la vista

function cargaDatosVenta(id,action,funcion){
    funcion();
}
