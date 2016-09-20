var usuario_=Backbone.Model.extend({
    defaults: {
        id: null,
        username: '',
        fullName: '',
        password: null,
        email: null,
        idCliente: null,
        idGrupo: null
    },
    urlRoot:'./rest/user/',
    usuarioActual:function(op){
    var me=this;
    var my_ajax=defineOp(op,{
        success:function(data){
            me.set(data);
        }
    });
    var ajax = myAjax({url:this.urlRoot+'detalles/',async:false,success:my_ajax.success,error:my_ajax.error});
    return ajax;
    },
    traerPermisos:function(op){
        var me=this;
        if(!existWithVal(this.id)){
            console.log("Se necesita tener id para ejecutar esta funcion");
            return;
        }
        me.permisos=["Puede ver y editar de Marcas","Puede desactivar Marcas","Puede activar Marcas","Puede listar Marcas","Puede ver y editar de Usuarios","Puede desactivar Usuarios","Puede activar Usuarios","Puede listar Usuarios","Puede editar Grupos de seleccion","Puede ver y editar de Grupos de seleccion","Puede desactivar Grupos de seleccion","Puede activar Grupos de seleccion","Puede listar Grupos de seleccion","Puede ver y editar de Articulos","Puede desactivar Articulos","Puede activar Articulos","Puede listar Articulos","Puede ver y editar de Roles","Puede desactivar Roles","Puede activar Roles","Puede listar Roles","Puede ver y editar de Grupos corpotativos","Puede desactivar Grupos corpotativos","Puede activar Grupos corpotativos","Puede listar Grupos corpotativos","Puede ver y editar de Puntos","Puede desactivar Puntos","Puede activar Puntos","Puede listar Puntos","Puede ver y editar de Clientes","Puede desactivar Clientes","Puede activar Clientes","Puede listar Clientes", "Puede listar Listas de precios", "Puede desactivar Listas de precios", "Puede activar Listas de precios", "Puede ver y editar de Clases", "Puede listar Clases", "Puede activar Clases", "Puede desactivar Clases"];
        /*var my_ajax=defineOp(op,{
            success:function(data){

                console.log("---------------------------");
                console.log(data);
                console.log("---------------------------");



                me.permisos=data;
            }
        });
        var ajax = myAjax({url:window.Jservicios + usuario.get("idCliente") +"/rest/controller/rolCliente/permisos/listar/"+this.id+"/lista_como_array",async:false,success:my_ajax.success,error:my_ajax.error});
        return ajax;*/
    }, 
    agregarRoles: function (op,data) {
        var me=this;
        if(!existWithVal(data)&&!existWithVal(me.id)){
            console.log("Se necesita tener un id y dar un json para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){

            }
        });
        var ajax = myAjax({url:'./rest/rolcliente/addrolesuser/?idUser=' + me.id,type: "POST",success:my_ajax.success,error:my_ajax.error});
        return ajax;
    },
    traerRoles: function (op) {
        var me=this;
        if(!existWithVal(me.id)){
            console.log("Se necesita tener id para ejecutar esta funcion");
            return;
        }
        var my_ajax=defineOp(op,{
            success:function(data){
                me.roles=data;
            }
        });
        var ajax=myAjax({url:'./rest/rolcliente/rolusuario/'+this.id,async:false,success:my_ajax.success,error:my_ajax.error});
        return ajax;
    }
});

var usuarios_ = Backbone.Collection.extend({
    model: usuario_,
    urlRoot:'./rest/user/',
    urlRootDesactivados : './rest/user/inactivos'
});

var usuario=new usuario_();
var usuarios=new usuarios_();
window.usuarioC=new usuario_();
charge_catalogs();
usuario.usuarioActual({
    success:function(){
        usuario.traerRoles({
            success:function(){
                usuario.traerPermisos();
            },error:function(){
                //console.log(usuario);
                errorConnetion("No es posible consultar su usuario","Refrescar la pagina");
            }
        });
    }
});

function charge_catalogs(){
    $.ajax({
        url:"./rest/common/canales/",
        success:function(data){
            if(data.length>0){
                window.catalogo_canalesCliente=data;
            }else{
                window.catalogo_canalesCliente="";
            }
        },error:function(){
            verifyConnection("Ha ocurrido un error de conexion",function(){
                showMessage("error","No fue posible consultar los canales");
            });
        }
    });
    $.ajax({
        url:"./rest/common/impuestos/",
        success:function(data){
            if(data.length>0){
                window.catalogo_impuestosCliente=data;
            }else{
                window.catalogo_impuestosCliente="";
            }
            if(window.catalogo_impuestosCliente==undefined){
               window.catalogo_impuestosCliente=[];
            }
            $.each(data,function(index,obj){
                $.each(obj.regimenes,function(index,obj){
                     window.catalogo_impuestosCliente.push(obj);
                });
            });

        },error:function(){
            verifyConnection("Ha ocurrido un error de conexion",function(){
                showMessage("error","No fue posible consultar los canales");
            });
        }
    });
    $.ajax({
        url:"./rest/common/tarifas/",
        success:function(data){
            if(data.length>0){
                window.catalogo_tarifasCliente=data;
            }else{
                window.catalogo_tarifasCliente="";
            }
        },error:function(){
            verifyConnection("Ha ocurrido un error de conexion",function(){
                showMessage("error","No fue posible consultar los canales");
            });
        }
    });
}