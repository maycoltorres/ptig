var cliente_ = Backbone.Model.extend({
    urlRoot: './rest/cliente/',
    defaults: {
        id: null,
        nombre: null,
        nit: null,
        digitoChequeo: null,
        direccion: null,
        telefono: null,
        extension: null,
        celular: null,
        email: null,
        regimen_id: null,
        nombreRepresentante: null,
        documentoRepresentante: null,
        nombreDueno: null,
        mailDueno: null,
        celularDueno: null,
        //pais : null,
        municipio: null
        //departamento : null
    },
    clonarRoles: function (op) {
        var url = './rest/rolcliente/eligeroles/?idCliente=' + this.id;
        $.ajax(
            {
                type: "POST",
                data: op.data,
                success: op.success,
                error: op.error,
                url: url,
                contentType: 'application/json'
            }
        );
    },
    buscar: function (op) {
        var url = './rest/cliente/busqueda/' + op.nombreCliente;
        $.ajax(
            {
                type: "GET",
                success: op.success,
                error: op.error,
                url: url,
                contentType: 'application/json'
            }
        );
    }
});

var clientes_ = Backbone.Collection.extend({
    urlRoot : "./rest/cliente/",
    urlRootDesactivados: './rest/cliente/inactivos/',
    model: cliente_
});
var cliente=new cliente_();
var clientes=new clientes_();


