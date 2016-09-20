var rol = Backbone.Model.extend({
    defaults: {
        id: null,
        nombre: null
    },
    urlRoot: './rest/rol/',

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
        var ajax = myAjax({url: './rest/rol/busqueda/' + nombreArticulo, type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    },
    atarCliente:function(op,idCliente){
        var me = this;
        if (!existWithVal(idCliente)) {
            console.log("Se necesita dar un idCliente para ejecutar esta funcion");
            return;
        }
        var my_ajax = defineOp(op, {
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/rolcliente/agregarol/?idCliente=' + idCliente+'&idRol='+me.id, type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    },
    agregarPermisos : function(op,data) {
        var me = this;
        if (!existWithVal(data)) {
            console.log("Se necesita dar un idCliente para ejecutar esta funcion");
            return;
        }
        var my_ajax = defineOp(op, {
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/rolcliente/addtransac/?idRol='+me.id, data:data,type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    },
    traerPermisos:function(op){

        var me = this;
        if (!existWithVal(data)) {
            console.log("Se necesita dar un idCliente para ejecutar esta funcion");
            return;
        }
        var my_ajax = defineOp(op, {
            success: function (data) {
            }
        });
        var ajax = myAjax({url: './rest/rolcliente/transacciones/'+me.id, data:data,type: 'GET', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    }
});

var roles = Backbone.Collection.extend({
    model: rol,
    urlRoot: "./rest/rol/",
    urlRootDesactivados: './rest/rol/inactivos/'
});

rol = new rol();
var roles = new roles();