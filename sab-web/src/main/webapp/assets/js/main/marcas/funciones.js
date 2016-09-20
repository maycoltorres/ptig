
function crearMarcas(action){

    var self = this;
    $("#marca_nombre").valida({longitud:{minimo:3,maximo:100},mostrarLongitud:true,mostrarMensaje:true});

    if(action=="ver"){

    }

    if(!$("#marca_section .campo_mal").length<1){
        $(".campo_mal:eq(0)").focus();
        //closeChargeStep();
        return false;
    }else{
        showChargeStep();



        var p = _.clone(window.marca);
        delete p.attributes.clienteNombre;
        if(action=="ver"){

        }else{
            //showTable({tabla:"punto_franquicias",modo: "table",tipo:"select_multiple",returnControl:""});

            if(p.id==undefined||p.id==""){
                p.id=null;
            }
        }

        p.save({
            nombre : $("#marca_nombre").val()
        },{
            success:function(model, response, options) {
                showMessage("good","La marca ha sido guardada");

                if(!isNaN(response)){
                    window.marca.set({id:response});
                }else{
                    //var id_FFFFF
                }
                //window.marcaDePunto=window.marca.attributes;

                Backbone.history.navigate("#lista/marcas/", { trigger: true });
                closeChargeStep();
            },
            error:function(model, response, options){

                if(response.responseText.indexOf("Actualizada")>-1) {
                    showMessage("good", "La marca ha sido guardada");
                    closeChargeStep();
                    Backbone.history.navigate("#lista/marcas/", { trigger: true });
                    Backbone.history.navigate("#lista/marcas/", { trigger: true });
                }else if(response.responseText.indexOf("existe")>-1){           //
                    showMessage("warning","La marca ya existe en la base de datos");  //La cliente ya existe en la base de datos
                }else if(response.responseText.indexOf("Creada")>-1){           //La Nombre
                    showMessage("good", "La marca ha sido guardada");
                    Backbone.history.navigate("#lista/marcas/", { trigger: true });
                    closeChargeStep();
                }else{
                    showMessage("error","Ha ocurrido un error al guardar la marca");
                }
                //alert("entro a error");marca
                //console.log(model);
                //console.log(response);
                //console.log(options);
            }
        });
    }
}


/*
 function validaTablasMarcas(){
 if($("#marca_clientes_tableCross").find("table").find("tbody").find("td.paisId").length<1||
 $("#marca_clientes_tableCross").find("table").find("tbody").find("td.id").length<1){
 showMessage("error","Debe elegir una configuracion para regimen y canal");
 return false;
 }
 return true;
 }
 */

function showMarcasClientesTable(ops){

    ops.html = ops.returnControl;
    ops.titulo = "Marcas";
    ops.destino = $("#marca_clientes_tableCross");

    ops.asignar = function (data) {
        if(!(ops.all==true)){
            window.marca.set(data[0]);
        }

    }
    ops.prevDelete=function(me){
        /*
        table=$("#marca_clientes_tableCross").find("table");

        var datatable=table.DataTable();
        row=$(me).closest("tr");
        datatable.row(row).remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }

        /*
         var count=0;
         $.grep(window.marca.get("clientes"), function (e) {
         try {
         if (e.id == me.id) {
         delete window.marca.attributes.clientes[count];
         }
         }catch(e){

         }
         count++;
         });
         console.log(window.marca.get("clientes"));
         */
        return;
    }
    ops.urlDelete = "./rest/marcas/" + window.seleccion.id + "/";
    ops.deleteFunction = function () {
        /*
         showMessage("good", "Se ha eliminado el marca");
         showTable({tabla:"marca_clientes",modo:"tabla",tipo:"save"});
         */
    }
    ops.returnControl = false;


    if(ops.all==true){
        ops.url="./rest/cliente/";
        ops.cols = [
            {proper: "id", head: "id", class: "hidden id"},
            {proper: "nombre", head: "Nombre", class: "centrate nombre"},
        ];
    }else{
        //ops.url="./rest/marca/marcas/1/";
        ops.workWithObject=true;
        var r=[];
        if(!(window.marca.attributes["clienteNombre"]==undefined)){
            r.push(window.marca.attributes);
        }

        ops.workObject= r;

        ops.cols = [
            {proper: "id", head: "id", class: "hidden id"},
            {proper: "clienteNombre", head: "Nombre", class: "centrate nombre"},
        ];
    }
    ops.notDelete=true;
    ops.notEdit = true;
    ops.submit = function () {
        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var tds = datatable.$("input:checked");
        if(!(tds[0].id==undefined)&&tds[0].id>0) {

            window.marca.attributes.idCliente=tds[0].id;
            setClient();
        }
        close_floating();
        showTable({tabla:"marca_clientes",modo:"tabla",tipo:"save"});

    };
    ops.floating_created = function (floating) {

    };
    ops.tableCreated = function (table, datatable, clon) {

        if($(".clon_floating").find(".nombre").length>0){
            var r=[];
            if(!(window.marca.attributes["clienteNombre"]==undefined)){
                r.push(window.marca.attributes);
                seleccionaDeFlo($(".clon_floating").last(),r,"idCliente","id");
            }

        }
    };
    return ops

}

function setClient(){
    console.log(window.clientes);
    console.log(window.marca);
    console.log(window.clientes.where({id: parseInt(window.marca.attributes.idCliente)}));
    window.marca.attributes.clienteNombre=window.clientes.where({id: parseInt(window.marca.attributes.idCliente)})[0].attributes.nombre;
}
