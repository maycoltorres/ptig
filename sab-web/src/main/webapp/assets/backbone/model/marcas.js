var marca = Backbone.Model.extend({
    defaults: {
        id: null,
        nombre: null,
        idCliente: null
    },
    urlRoot: './rest/marca/',

    buscar: function (op, nombreMarca) {
        var me = this;
        if (!existWithVal(nombreMarca)) {
            console.log("Se necesita dar un id para ejecutar esta funcion");
            return;
        }
        var my_ajax = defineOp(op, {
            success: function (nombreMarca) {
            }
        });
        var ajax = myAjax({url: './rest/marca/busqueda/' + nombreMarca, success: my_ajax.success, error: my_ajax.error});
        return ajax;
    }
});

var marcas = Backbone.Collection.extend({
    model: marca,
    urlRoot: "./rest/marca/",
    urlRootDesactivados: './rest/marca/inactivos/'
});
marca=new marca();
var marcas=new marcas();