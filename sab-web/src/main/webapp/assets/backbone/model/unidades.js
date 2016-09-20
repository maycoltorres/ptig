var unidad = Backbone.Model.extend({
    defaults: {
        id: null,
        nombre: null,
        estandar: null,
        factor: null,
        tipo: null
    },
    urlRoot: './rest/unidad/'
});
var unidades = Backbone.Collection.extend({
    model: unidad,
    urlRoot: './rest/unidad/',
    urlRootDesactivados: './rest/unidad/'
});

unidad=new unidad();
var unidades=new unidades();