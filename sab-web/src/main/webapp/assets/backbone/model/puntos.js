var punto = Backbone.Model.extend({
    defaults : {
        cliente_id : null,
        id : null,
        nombre : null,
        direccion : null,
        telefono : null,
        extension : null,
        telefono2 : null,
        extension2 : null,
        municipio_id : null,
        tipo_negocio_id : null,
        centro_costo_id : null,
        almacenarNpunto : false,
        apoyalicrec :false,
        autoUsuEliminar :false,
        contXdenominacion :false,
        factNpunto :false,
        manNlicencias :false,
        opLicNred :false,
        cierreocultocaja :false,
        claveanular :false,
        imprRemotas :false,
        autAnularVerificacion :false,
        capNpersonasAt :false,
        capCodmesero :false,
        capCodAutoserv :false,
        denoatendidopor :false,
        factFresumido :false,
        factSinDocVerif :false,
        fvmenseros :false,
        impremotacpfactura :false,
        controlconspalabra :false,
        impticketAutoSrv :false,
        inclivaPventa :false,
        claveanularVerifi :false
    },
    urlRoot: './rest/punto/',
    traerPorCliente: function (op,idCliente) {
        var me=this;
        if(!existWithVal(idCliente)){
            console.log("Se necesita dar un idCliente para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){
                me.puntosClientes=data;
                op();
            }
        });
        var ajax = myAjax({url:'./rest/punto/?idCliente=' + idCliente,success:my_ajax.success,error:my_ajax.error});
        return ajax;
    },
    desactivadosPorCliente: function (op,idCliente) {
        var me=this;
        if(!existWithVal(idCliente)){
            console.log("Se necesita dar un idCliente para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){
                me.puntosInactivosClientes=data;
                op();
            }
        });
        var ajax = myAjax({url:'./rest/punto/inactivos/?idCliente=' + idCliente,success:my_ajax.success,error:my_ajax.error});
        return ajax;
    },
    agregar : function(op) {
        var me=this;
        if(!existWithVal(me.id)){
            console.log("Se necesita dar un id para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){
            }
        });
        var ajax = myAjax({url:'./rest/punto/'+ me.id+'/agregarmarcas/',type: 'POST',success:my_ajax.success,error:my_ajax.error});
        return ajax;
    },
    buscar : function(op) {
        var me=this;
        if(!existWithVal(me.id)){
            console.log("Se necesita tener un id para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){
            }
        });
        var ajax = myAjax({url:'./rest/punto/busqueda/' + op.nombrePunto,type: 'POST',success:my_ajax.success,error:my_ajax.error});
        return ajax;
    }
}) ;

var puntos = Backbone.Collection.extend({
    model: punto,
    urlRoot : "./rest/punto/",
    urlRootDesactivados: './rest/punto/inactivos/',
    desactivadosPorCliente: function (op,idCliente) {
        var me=this;
        if(!existWithVal(idCliente)){
            console.log("Se necesita dar un idCliente para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){
                me.puntosInactivosClientes=data;
                op();
            }
        });
        var ajax = myAjax({url:'./rest/punto/inactivos/?idCliente=' + idCliente,success:my_ajax.success,error:my_ajax.error});
        return ajax;
    }
});

punto=new punto();
var puntos=new puntos();

//
