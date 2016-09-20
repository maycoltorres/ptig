
function crearGrupo(action){
    var self = this;
    $("#grupo_nombre").valida({longitud:{minimo:3,maximo:100},mostrarLongitud:true,mostrarMensaje:true});

    if(action=="ver"){

    }

    if(!$("#grupo_section .campo_mal").length<1){
        $(".campo_mal:eq(0)").focus();
        //closeChargeStep();
        return false;
    }else{
        var nombre=$("#grupo_nombre").val();
        showChargeStep();
        var p = _.clone(window.grupo);

        if(action=="ver"){

            if($.grep(window.grupos.models,function(e){
                    if(e.attributes.id== p.get("id")){
                        return;
                    }else{
                        return e.attributes.nombre==nombre;
                    }

                }).length>0){
                showMessage("warning","El grupo ya existe en la base de datos");
                return;
            }
        }else{
            //showTable({tabla:"punto_franquicias",modo: "table",tipo:"select_multiple",returnControl:""});
            if($.grep(window.grupos.models,function(e){
                    return e.attributes.nombre==nombre;

                }).length>0){
                showMessage("warning","El grupo ya existe en la base de datos");
                return;
            }

            if(p.id==undefined||p.id==""){
                p.id=null;
            }
        }


        if(!validaTablasGrupo()){
            return false;
        }

        p.save({
            nombre : nombre
        },{
            success:function(model, response, options) {
                showMessage("good","El grupo ha sido guardado");

                if(!isNaN(response)){
                    window.grupo.set({id:response});
                    asignarClientes(response);
                }else{
                     //var id_FFFFF
                }
                //window.grupoDePunto=window.grupo.attributes;

                Backbone.history.navigate("#lista/grupos/", { trigger: true });
                closeChargeStep();
            },
            error:function(model, response, options){

                if(response.responseText.indexOf("modificado")>-1) {
                    showMessage("good", "El grupo ha sido guardado");
                    asignarClientes(window.grupo.get("id"));
                    closeChargeStep();
                    Backbone.history.navigate("#lista/grupos/", { trigger: true });
                }else if(response.responseText.indexOf("existe")>-1){           //
                    showMessage("warning","El grupo ya existe en la base de datos");  //El cliente ya existe en la base de datos
                }else if(response.responseText.indexOf("no es")>-1){           //El Nombre
                    showMessage("warning","Este grupo no se puede modificar");  //El cliente ya existe en la base de datos
                }else{
                    showMessage("error","Ha ocurrido un error al guardar el grupo");
                }
                //alert("entro a error");grupo
                //console.log(model);
                //console.log(response);
                //console.log(options);
            }
        });
    }
}

function asignarClientes(id){
    var clientes=(window.grupo.get("clientes"));
    var data=[];
    $.each(clientes,function(){
        data.push({id:this.id});
    });



    $.ajax({
        url:"./rest/grupo/"+id+"/agregarclientes/",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        data:JSON.stringify(data),
        success:function(model, response, options) {
            //showMessage("good","El grupo ha sido guardado");

            closeChargeStep();
        },
        error:function(model, response, options){
            if(response.responseText.indexOf("adicionado")>-1) {
                //showMessage("good", "El grupo ha sido guardado");
                //asignarClientes(window.grupo.get("id"));
                //closeChargeStep();
                //Backbone.history.navigate("#lista/grupos/", { trigger: true });
            }else{
                showMessage("error","No fue posible guardar los clientes");
            }
    }});
}

function validaTablasGrupo(){
    if($("#grupo_clientes_tableCross").find("table").find("tbody").find("td.nombre").length<1){
        showMessage("warning","No pueden haber grupos sin clientes");
        return false;
    }
    return true;
}

function showGrupoClientesTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Clientes";
    ops.destino = $("#grupo_clientes_tableCross");

    ops.asignar = function (data) {
        if(ops.all==undefined){
            window.grupo.set({clientes:data});
        }
    }
    ops.prevDelete=function(me){
        table=$("#grupo_clientes_tableCross").find("table");

        var datatable=table.DataTable();
        row=$(me).closest("tr");
        row2=datatable.row(row);
        row2.remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }

    }
    ops.urlDelete = "./rest/grupoSeleccion/" + window.seleccion.id + "/";
    ops.deleteFunction = function () {
        
    }
    ops.returnControl = false;
    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "nombre", class: "centrate nombre"},
    ];

    if(ops.all==true){
        ops.url="./rest/cliente/";
    }else{
        ops.workWithObject=true;
        ops.workObject=window.grupo.get('clientes');
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

        window.grupo.set({clientes:clientes});
        close_floating();
        showTable({tabla:"grupo_clientes",modo:"tabla",tipo:"save"});

    };
    ops.floating_created = function (floating) {

    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            console.log(window.grupo.get("clientes"));
            seleccionaDeFlo($(".clon_floating").last(),window.grupo.get("clientes"),"id","id");
        }
    };
    return ops
}

