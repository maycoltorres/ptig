function crearRoles(action){
    var self = this;
    $("#roles_cliente_nombre").valida({longitud:{minimo:5,maximo:100},mostrarLongitud:true,mostrarMensaje:true});

    if($("#rolCliente_tableCross").find("td.nombre").length<1){
        showMessage("warning","El usuario debe tener minimo 1 permiso");
        return;
    }

    
    
    
    if(!$("#rolCliente_section .campo_mal").length<1){
        $(".campo_mal:eq(0)").focus();
        closeChargeStep();
        return false;
    }else{
        val=$("#roles_cliente_nombre").val();

        if(action=="crear") {
            var p=$.grep(window.roles_cliente.models,function(e){
                return e.attributes.nombre==val;
            });
        }else{
            id=window.rol_cliente.get("id");
            var p=$.grep(window.roles_cliente.models,function(e){
                if(e.attributes.id==id){
                    return false;
                }
                return e.attributes.nombre==val;
            });
        }

        if (p && p.length > 0) {
            showMessage("warning", "Ya existe un rol con ese nombre");
            return;
        }

        if(action=="ver"){
            data= {
                id:window.rol_cliente.get("id"),
                nombre: $("#roles_cliente_nombre").val(),
                id_cliente: usuario.get("idCliente"),
                id_rol: 1,
                activo: 1
            }
        }else{
            data={
                nombre:$("#roles_cliente_nombre").val(),
                id_cliente: usuario.get("idCliente"),
                id_rol: 1,
                activo: 1
            }
        }

        if($.grep(window.roles_cliente.models,function(e){
            if(e.get("id")==data.id){
                return false;
            }else{
                return e.get("nombre")==data.nombre;
            }
        }).length>0){
            showMessage("warning","Ese nombre de rol ya existe en su cliente");
            return;
        }

        $.ajax({
            data:JSON.stringify(data),
            url:"./rest/rolcliente/",
            type:"POST",
            contentType:"application/json",
            success:function(response) {
                if(!(action=="ver")){

                    window.rol_cliente.set({id:response});
                }
                //window.clienteDePunto=window.cliente.attributes;
                guardarPermisosRoles(window.rol_cliente.get("id"),action);
                closeChargeStep();
            },
            error:function(response,xhr, options){
                //if(response.responseText.indexOf("true")>-1){
                console.log(window.rol_cliente.get("id"));
                guardarPermisosRoles(window.rol_cliente.get("id"),action);
                closeChargeStep();
            }
        });
    }
}

function crearListaPrecios(action){
	//var self = this;
    //$("#lista_precios_nombre").valida({longitud:{minimo:5,maximo:100},mostrarLongitud:true,mostrarMensaje:true});
    window.lista_precios.save();
    
}


function showPermisosTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Permisos";
    ops.destino = $("#rolCliente_tableCross");

    ops.asignar = function (data){
        if(ops.all==undefined){
            window.rolPermisos=data;
        }
    };


    ops.prevDelete=function(me){
        table=$("#rol_tableCross").find("table");

        var datatable=table.DataTable();
        row=$(me).closest("tr");
        row2=datatable.row(row);
        row2.remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }
    };
    ops.urlDelete = "#";
    ops.deleteFunction = function () {
        /*
         showMessage("good", "Se ha eliminado el grupo");
         showTable({tabla:"grupo_clientes",modo:"tabla",tipo:"save"});
         */
    };
    ops.returnControl = false;
    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "nombre", class: "centrate nombre"},
    ];

    if(ops.all==true){
        ops.url="./rest/rolcliente/rolusuario/" + window.usuario.get("id");
    }else{
        ops.workWithObject=true;
        ops.workObject=window.rolPermisos;
    }
    ops.notDelete=true;

    ops.notEdit = true;
    ops.submit = function () {

        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var clientes=[];
        var tds = datatable.$("input:checked");

        tds.each(function(index,value){
            var c={};
            c.id=this.id;
            c.nombre=$(this).closest("tr").find(".nombre").html()
            clientes.push(c);
        });

        window.rolPermisos=clientes;
        close_floating();

        showTable({tabla:"permisos",modo:"tabla",tipo:"save"});
    };
    ops.floating_created = function (floating) {

    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(),window.rolPermisos,"id","id");
        }
    };
    return ops;
}

function guardarPermisosRoles(id,action){

    if(action=="ver"){
        id=window.rol_cliente.get("id");
    }

    if(window.rolPermisos.length<1){
        showMessage("good","Rol guardado");
        Backbone.history.navigate("#lista/roles_cliente/", { trigger: true });
        closeChargeStep();
        return;
    }

    if(isNaN(id)){
        showMessage("error","No fue posible guardar los permisos del rol");
        return;
    }



    url="./rest/controller/rolcliente/" + window.rol_cliente.get("id");
    data=[];

    $.each(window.rolPermisos,function(){
        data.push({
            "id_rol":id,
            "id_transaccion":this.id
        });
    });

    $.ajax({
        data:JSON.stringify(data),
        url:url,
        type:"POST",
        success:function(model, response, options) {
            showMessage("good","rol y permisos guardados");
            Backbone.history.navigate("#lista/roles_cliente/", { trigger: true });

            closeChargeStep();
        },
        error:function(model, response, options){

        }
    });
}


//