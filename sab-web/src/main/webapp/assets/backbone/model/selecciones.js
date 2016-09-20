var seleccion = Backbone.Model.extend({
    defaults: {
        "id":null,
        "nombre":null,
        "caracteristicaPrecio":"1",
        "activo":null,
        "tipoGrupo":"1",
        "incrementoPrecio": null
    },
    urlRoot:"./rest/grupoSeleccion/",
    buscar: function (op,nombreArticulo) {
        var me = this;
        if (!existWithVal(nombreArticulo)) {
            console.log("Se necesita dar un nombreArticulo para ejecutar esta funcion");
            return;
        }
        var my_ajax = defineOp(op, {
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/seleccion/busqueda/' + nombreArticulo, type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    },
    activar: function(){
    	var my_ajax = defineOp({
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/grupoSeleccion/' + id + "/activar", type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    },
    inactivar: function(){
    	var my_ajax = defineOp({
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/grupoSeleccion/' + id + "/inactivar", type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    }
});

var selecciones = Backbone.Collection.extend({
    model: seleccion,
    urlRoot: "./rest/grupoSeleccion/?mostrar=activos",
    urlRootDesactivados: "./rest/grupoSeleccion/?mostrar=inactivos"
});

seleccion=new seleccion();
var selecciones=new selecciones();