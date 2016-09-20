var parameters = {
    abuelo: null,
    idA: null,
    padre: null,
    idP: null,
    action: null,
    tabla: null,
    id: null
}
var listado = Backbone.View.extend({
    el: "<div></div>",
    template: "",
    events: {

    },
    initialize: function (p) {
    },
    render: function (p) {
        var ruta = $.extend(parameters, p);
        
        if (!existWithVal(ruta.tabla)) {
            showMessage("error","No se reconoce la tabla");
        }
        var evento;
        if(ruta.action=="lista"){

            var tabla = window[ruta.tabla.coleccion];
            evento=function(data){openSection(ruta.tabla.coleccion,data);};

        }else if(ruta.action=="ver"){

            if(ruta.tabla.instacia=="usuarios"||ruta.tabla.instacia=="usuario"){
                var tabla = window.usuarioC;
            }else{
                var tabla = window[ruta.tabla.instacia];
            }

            evento=function(data){openTable(tabla,ruta.tabla.instacia,data,"ver",p.id);};
            tabla.id= p.id;
        }else if(ruta.action=="crear"){
            var tabla = window[ruta.tabla.instacia];
            var empty=tabla.clone();
            empty.clear();
            openTable(tabla,ruta.tabla.instacia,empty.attributes,"crear");
            evento=function(){};
        }

        if (!existWithVal(tabla)){
            showMessage("error","No se encuentra la instacia " + tabla);
            return;
        }

        tabla.fetch({
            success: function(data){evento(data)},
            error:function(a,b,c){
                verifyConnection(function(){showMessage("error","Error consultado la informacion");});
            }
        });
    },
    renderPunto:function(){
    }
});


listado = new listado();
