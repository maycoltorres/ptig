var defaultsAjaxModel={
    url:"",
    type:"GET",
    async:true,
    contentType:'application/json;charset=UTF-8',
    success:function(){},
    error:function(){},
    data:null
}

Backbone.Model.urlDesactivar = "";
Backbone.Model.urlActivar = "";
Backbone.Model.rest='';
Backbone.Model.urlGuardar = "";
Backbone.Collection.urlRoot = "";
Backbone.Collection.urlDesactivados = '';
Backbone.Collection.nombre = '';

Backbone.Model.prototype.fetch = function (op,async) {
    var me=this;
    if(!existWithVal(me.id)){

        console.log("Se necesita tener un id para ejecutar esta funcion");
        return;
    }
    var my_ajax=defineOp(op,{
        success:function(data){
            me.set(data);
        }
    });
    if(!existWithVal(async)){
        async=true;
    }
    var ajax = myAjax({url:me.urlRoot+me.id,async:false,success:my_ajax.success,error:my_ajax.error});
    return ajax;
}

Backbone.Collection.prototype.fetch = function (op,async) {
    var me=this;
    var my_ajax=defineOp(op,{
        success:function(data){
            me.add(data);
        },
        error:function(){

        }
    });
    if(!existWithVal(async)){
        async=true;
    }
    var ajax = myAjax({url:me.urlRoot,async:false,success:my_ajax.success,error:my_ajax.error});
    return ajax;
}
Backbone.Collection.prototype.desactivados = function (op) {
    var me=this;
    var my_ajax=defineOp(op, {
        success: function (data) {
        }
    });

    var ajax = myAjax({url:me.urlRootDesactivados,type:"GET",success:my_ajax.success,error:my_ajax.error});
    return ajax;
}
Backbone.Model.prototype.desactivar = function (op) {
    var me=this;
    if(!existWithVal(me.id)){
        console.log("Se necesita terner id para ejecutar esta funcion");
        return;
    }
    var my_ajax=defineOp(op, {
        success: function (data) {
        }
    });
    var ajax = myAjax({url:me.urlRoot+"inactivar/"+me.id,type:"DELETE",success:my_ajax.success,error:my_ajax.error});
    return ajax;
}
Backbone.Model.prototype.activar = function (op) {
    var me=this;
    if(!existWithVal(me.id)){
        console.log("Se necesita tener id para ejecutar esta funcion");
        return;
    }
    var my_ajax=defineOp(op, {
        success: function (data) {
        }
    });
    var ajax = myAjax({url:me.urlRoot+"activar/"+me.id,type:"PUT",success:my_ajax.success,error:my_ajax.error});
    return ajax;
}
Backbone.Model.prototype.guardar = function (op, data) {
    var me=this;

    var my_ajax=defineOp(op, {
        success: function (data) {
        }
    });
    var type="PUT";
    if(!existWithVal(this.id)){
        type="POST"
    }
    var ajax = myAjax({url:me.urlGuardar,type:type,success:my_ajax.success,error:my_ajax.error,data:data});
    return ajax;
}

Backbone.Model.prototype.consultar = function (op, id) {
    var me=this;
    if(!existWithVal(id)){
        console.log("Se necesita dar un id para ejecutar esta funcion");
        return;
    }
    var my_ajax=defineOp(op, {
        success: function (data) {

        }
    });

    var ajax = myAjax({url:me.rest+id,success:my_ajax.success,error:my_ajax.error,data:data});
    return ajax;
}

Backbone.Model.prototype.buscar = function (op,parametro,tipo) {
    var me=this;
    if(!existWithVal(parametro)&&!existWithVal(tipo)){
        console.log("Se necesita dar un parametro de busqueda y tipo de busqueda para ejecutar esta funcion");
        return;
    }
    var url=this.urlBuscarPor[tipo];
    if(!existWithVal(url)){
        console.log("No se encuentra ese tipo de buscqueda");
        return;
    }

    var my_ajax=defineOp(op, {
        success: function (data) {

        }
    });

    var ajax = myAjax({url:url+parametro,success:my_ajax.success,error:my_ajax.error,data:data});
    return ajax;
}

Backbone.Model.prototype.paises = function (op,idPais) {
    var me=this;
    var url= "./rest/common/paises";
    if(existWithVal(idPais)){
        url= url+"/"+idPais;
    }

    var my_ajax=defineOp(op, {
        success: function (data) {
            me.pas=data;
        },
        error:function(){
            verifyConnection(function(){});
        }
    });

    var ajax = myAjax({url:url,success:my_ajax.success,error:my_ajax.error});
    return ajax;
}

Backbone.Model.prototype.departamentos = function (op,idDep,idPais) {
    var me=this;
    var url= "./rest/common/dptos/";
    if(existWithVal(idDep)){
        url= url+idDep;
    }else if(existWithVal(idPais)){
        url= url+"?idPais="+idPais;
    }else{
        console.log("Debe pasar un id");
        return;
    }
    var my_ajax=defineOp(op, {
        success: function (data) {
            me.deps=data;
        },
        error:function(){
            verifyConnection(function(){});
        }
    });

    var ajax = myAjax({url:url,success:my_ajax.success,error:my_ajax.error});
    return ajax;
}

Backbone.Model.prototype.municipios = function (op,idMun,idDep) {
    var me=this;
    var url= "./rest/common/municipios/";
    if(existWithVal(idMun)){
        url= url+idMun;
    }
    if(existWithVal(idDep)){
        url= url+"?idDepto="+idDep;
    }
    var my_ajax=defineOp(op, {
        success: function (data) {
            me.muns=data;
        },
        error:function(){
            verifyConnection(function(){});
        }
    });

    var ajax = myAjax({url:url,success:my_ajax.success,error:my_ajax.error});
    return ajax;
}

function existWithVal(variable) {
    if(variable===false||variable===0){
        return true;
    }
    if (variable === undefined||variable === null||variable == "") {
        return false;
    }
    return true;
}

function myAjax(obj) {
    var a=$.extend({},defaultsAjaxModel,obj);
    if (existWithVal(a.url)&&existWithVal(a.type)&&existWithVal(a.async)) {
        var ajax = $.ajax({
            url: a.url,
            type: a.type,
            data: a.data,
            success: function(data){a.success(data);},
            error: function(x,y,z){a.error(x,y,z);},
            async: a.async
        });
        return ajax;
    }else{
        console.log("No se ha ejecutado el ajax porque faltan los parametros type, url, y async");
        console.debug();
    }
    return undefined;
}

function defineOp(op,work){
    var ajax={}
    if(!existWithVal(op)){
        var op={};
    }
    if(!existWithVal(op.success)){
        op.success=function(){};
    }
    if(!existWithVal(op.error)){
        op.error=function(){};
    }
    if(!existWithVal(work)){
        var work={};
    }
    if(!existWithVal(work.success)){
        work.success=function(){};
    }
    if(!existWithVal(work.error)){
        work.error=function(){};
    }
    ajax.success=function(data){
        work.success(data);
        op.success(data);
    }
    ajax.error=function(x,y,z){
        work.error(x,y,z);
        op.error(x,y,z);
    }
    return ajax;
}





