var articulo = Backbone.Model.extend({
    defaults: {
        id: null,
        nombre: null,
        codigobarras: null,
        descripcion: null,
        venta: false,
        inventario: false,
        receta: false,
        seleccion: false,
        empaque: false,
        costoEstimado:null,
        idCliente: null,
        puntos:null
    },
    initialize: function () {
	  this.set('puntos', []);
	},
	
    urlRoot: './rest/articulo/',

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
        var ajax = myAjax({url: './rest/articulo/busqueda/' + nombreArticulo, type: 'POST', success: my_ajax.success, error: my_ajax.error});
        return ajax;
    }
});
var articulos = Backbone.Collection.extend({
    model: articulo,
    urlRoot: './rest/articulo/?mostrar=activos',
    urlRootDesactivados: './rest/articulo/?mostrar=inactivos'
});

articulo=new articulo();
var articulos=new articulos();