var rol_cliente = Backbone.Model.extend({
    defaults: {
        id: null,
        nombre: null
    },
    urlRoot: './rest/rolcliente/',

    buscar: function (op, nombreArticulo) {
        var me = this;
        if (!existWithVal(nombreArticulo)) {
            console.log("Se necesita dar un nombreArticulo para ejecutar esta funcion");
            return;
        }
        var my_ajax = defineOp(op, {
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/rolcliente/busqueda/' + nombreArticulo, type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    }
});

var roles_cliente = Backbone.Collection.extend({
    model: rol_cliente,
    urlRoot: "./rest/rolcliente/",
    urlRootDesactivados: './rest/rolcliente/inactivos/'
});

rol_cliente = new rol_cliente();
var roles_cliente = new roles_cliente();