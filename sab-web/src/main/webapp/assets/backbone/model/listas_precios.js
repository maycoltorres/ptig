var lista_precios = Backbone.Model.extend({
	defaults: {
        "id":null,
        "nombre":null,
        "activo":null
    },
    url:'./rest/cliente/' + usuario.get("idCliente") + '/listasPrecios/',
    urlRoot : "./rest/cliente/" + usuario.get("idCliente") + "/listasPrecios/",
});

var listas_precios = Backbone.Collection.extend({
	model : lista_precios,
	urlRoot : "./rest/cliente/" + usuario.get("idCliente") + "/listasPrecios",
    urlRootDesactivados : "./rest/cliente/" + usuario.get("idCliente") + "/listasPrecios?mostrar=inactivos"
});

lista_precios = new lista_precios();
var listas_precios = new listas_precios();