var grupo = Backbone.Model.extend({
    defaults : {
        id : null,
        nombre : null
    },
    urlRoot: './rest/grupo/',
    parametroBusqueda:"",
    urlBuscarPor:{
        nombre:'./rest/grupo/busqueda/',
        cliente:'./rest/grupo/grupos/'
    },
    agregarClientes : function(op,data) {
        var me=this;
        if(!existWithVal(me.id)){
            console.log("Se necesita tener id para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){

            }
        });
        var ajax = myAjax({url:me.urlRoot + me.id + '/agregarclientes/',type:"POST",success:my_ajax.success,error:my_ajax.error,data:data});
        return ajax;

    }
});
var grupos = Backbone.Collection.extend({
    model: grupo,
    urlRootDesactivados : "./rest/grupo/inactivos",
    urlRoot : "./rest/grupo/"
});

grupo=new grupo();
var grupos=new grupos();