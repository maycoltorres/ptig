var clase = Backbone.Model.extend({
	defaults: {
        "id":null,
        "nombre":null,
        "venta":null,
        "inventario":null,
        "activo":null,
        "grupos":[]
    },
    urlRoot : "./rest/cliente/clase/"
});

var clases = Backbone.Collection.extend({
	model : clase,
	urlRoot : "./rest/cliente/clase/",
    urlRootDesactivados : "./rest/cliente/clase/?mostrar=inactivos"
});

clase = new clase();
var clases = new clases();