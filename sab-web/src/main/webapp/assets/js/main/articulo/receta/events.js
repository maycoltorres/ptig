function eventosClickReceta(id,action){

    $("#receta_ver").click(function(){
        $("#receta_lista").removeClass("hidden");
        $("#receta_guardar").addClass("hidden");
        showTable({tabla:"listaReceta",modo:"tabla",tipo:"save",custom:true});
    });

    $("#receta_importar").click(function(){
        showTable({tabla: "receta_importar", modo: "form", tipo: "floating"});
    });

    $("#guardar").click(function(){
        validaReceta(id,action);
    });

    $("#receta_limpiar").click(function(){
        $("#receta_guardar #nombre").val("");
        $("#descripcion").val("");
        $("#receta_guardar #receta_cantidad_base").val("");
        $("#receta_guardar #canales_d").html("");
        $("#receta_guardar #canales_d").removeData();
        $("#receta_puntos_tableCross").html("");
        $("#ingredientes_tableCross").html("");
        $("#receta_unidad").find(".empty").prop("selected",true);
    });

    $("#receta_crear").click(function(){
        window.puntosReceta=[];
        window.recetaIngredientes=[];
        window.recetaCanales=[];
        $("#receta_limpiar").click();
        $("#receta_lista").addClass("hidden");
        $("#receta_guardar").removeClass("hidden");
        window.recetaId=undefined;
        showTable({tabla:"receta_puntos",modo:"tabla",tipo:"save"});
        showTable({tabla:"receta_canales",modo:"tabla",tipo:"save"});
        showTable({tabla:"receta_ingredientes",modo:"tabla",tipo:"save"});
        showTable({tabla:"ventas_grupoSeleccion",modo:"tabla",tipo:"save"});
    });

    $("#receta_des").click(function(){
        $(this).addClass("hidden");
        $("#receta_act").removeClass("hidden");
        $("#receta_lista").find(".panel-body").html("");
        showTable({tabla:"listaReceta",modo:"tabla",type:"table",tipo:"save",custom:true,des:true});
    });

    $("#receta_act").click(function(){
        $("#receta_lista").find(".panel-body").html("");
        $(this).addClass("hidden");
        $("#receta_des").removeClass("hidden");
        showTable({tabla:"listaReceta",modo:"tabla",tipo:"save",custom:true});
    });

    $(".wizard_ver").click(function(){
        $("#receta_lista").find(".panel-body").children().remove();
        $("#receta_lista").find(".panel-body").append($("#connection_cargando").clone().removeClass("hidden"));
    });

    $("#puntos_ing").off("click").on("click",function(){
        showTable({tabla:"receta_puntos",modo:"tabla",type:"table",tipo:"select_multiple",all:true});
    });

    $("#receta_ing").off("click").on("click",function(){
        showTable({tabla: "receta_ingredientes", modo: "form", tipo: "floating",all:true,custom:true});
    });

    $("#receta_canales").off("click").on("click",function(){
        showTable({tabla:"receta_canales",type: "table",tipo:"select_multiple",returnControl:"",all:true});
    });

    $("#receta_puntos_table").click(function(){
        if(!validateCampsReceta()){
            return
        }
        showTable({tabla: "canal", type: "table", tipo: "select_one", id:window.articulo.id});
    });
}

function cargarCamposReceta(data, parcial){
    if(parcial==undefined){
        $("#nombre").val(data.nombre);
        window.recetaId=data.id;
        $("#receta_lista").addClass("hidden");
        $("#receta_guardar").removeClass("hidden");
        $("#receta_cantidad_base").val(data.cantidad);
        $("#receta_unidad").find("option[value='"+data.unidadId+"']").prop("selected",true);
    }
    $("#descripcion").val(data.preparacion);
    showTable({tabla:"receta_puntos",modo:"tabla",tipo:"save"});
    showTable({tabla:"receta_canales",modo:"tabla",tipo:"save"});
    showTable({tabla:"receta_ingredientes",modo:"tabla",tipo:"save"});
}
//change,keyup
function eventosVariosReceta(){
    //Evita que se pueda eligiar solamente la caracteristica de receta ademas carga el html del paso
    //muestra o esconde el texto para mostrar
}
// Eventos disparados por el programa
function validaReceta(id,action){
    if($("#receta_lista").hasClass("hidden")){
        $("#receta_unidad").valida();
        $("#nombre").valida({validaCon: "letras", mostrarMensaje:false});
        $("#receta_cantidad_base").valida({validaCon: "numeros", mostrarMensaje:false});
        $("#descripcion").valida({longitud:{minimo:1,maximo:4000},validaCon: "letras,numeros", mostrarMensaje:true, permitirEnBlanco:true});

        if($("#ingredientes_tableCross").find("td.unidadNombre").length<1){
            showMessage("warning","Necesita al menos un ingrediente");
            return;
        }

        if($("#receta_puntos_tableCross").find(".id").length<1){
            showMessage("warning","Necesita al menos un punto");
            return;
        }

        if($("#receta_canales_tableCross").find(".id").length<1){
            showMessage("warning","Necesita al menos un canal configurado");
            return;
        }

        if($("#receta_guardar").find(".campo_mal").not(".hidden").length>0){
                return;
        }
        var nombre=$("#nombre").val();

        if(window.recetaId==undefined||!(window.recetaId>0)){
            var id_=null;
        }else{
            var id_=window.recetaId;
        }
        if(window.actionGlobal=="ver"){
            var t=$.grep(window.recetaLista,function(e){
                if(e.id==id_){
                    return false;
                }else if(e.nombre==nombre){
                    return true;
                }
            });
            if(t.length>0){
                showMessage("warning","Este nombre ya se encuentra en otra receta");
                return false;
            }
        }else{
            var t=$.grep(window.recetaLista,function(e){
                return e.nombre==nombre;
            });
            if(t.length>0){
                showMessage("warning","Este nombre ya se encuentra en otra receta");
                return false;
            }
        }

        data={
            id:id_,
            nombre:$("#nombre").val(),
            idArticulo:window.articulo.id,
            preparacion:$("#descripcion").val(),
            cantidad:$("#receta_cantidad_base").val(),
            idUnidad:$("#receta_unidad").find(":selected").val()
        }

        guardarReceta(data);


    }else{
        Backbone.history.navigate("#lista/articulos/", { trigger: true });
    }
}

function guardarReceta(obj){
	obj.puntos = window.puntosReceta;
	obj.canales = window.recetaCanales;
	obj.ingredientes = window.recetaIngredientes;
    
	window.receteActual=obj;
    var data=[obj];

    url= "./rest/articulo/" + window.articulo.get("id") + "/recetas/",
    $.ajax({
        url: url,
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(data),
        success:function(data){

            //showTable({tabla: "listaReceta", modo: "tabla", tipo: "save", custom: true});
            showMessage("good","La receta ha sido guardada exitosamente");
            $("#receta_ver").trigger("click");
            $("#receta_guardar").find(".validado").removeClass("validado");
            closeChargeStep();
            close_floating();
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible guardar los puntos")});
            closeChargeStep();
            close_floating();
        }
    });
}

function salirDeReceta(){
    //Backbone.history.navigate("#lista/articulos/", { trigger: true });
    return true;
}

function showRecetaPuntosTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Puntos";
    ops.destino = $("#receta_puntos_tableCross");
    ops.cols = [
        {head: "Id", proper: "id",class:"hidden id"},
        {head: "Punto", proper: "nombre",class:"puntoNombre"}
    ];

    ops.returnControl=false;
    ops.asignar = function (data) {

    }

    ops.prevDelete=function(me){
        table=$("#receta_puntos_tableCross").find("table");
        var datatable=table.DataTable();
        row=$(me).closest("tr");
        row2=datatable.row(row);
        row2.remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }
    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {

    }
    if (ops.all) {
        ops.url = "./rest/articulo/" + window.articulo.get("id") + "/puntos/";
        ops.cols = [
            {head: "Id", proper: "id",class:"hidden id"},
            {head: "Punto", proper: "nombre",class:"nombre"}
        ];
    } else {
        ops.workWithObject=true;
        ops.workObject=window.puntosReceta;
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
            c.nombre=$(this).closest("tr").find(".nombre").html(),
            c.id=$(this).closest("tr").find(".id").html()
            clientes.push(c);
        });

        window.puntosReceta=clientes;
        close_floating();
        showTable({tabla:"receta_puntos",modo:"tabla",tipo:"save"});

    };
    ops.clon_created = function (clon) {

    };
    ops.tableCreated = function (table, datatable, clon){
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(), window.puntosReceta,"id_punto","id");
        }
    };
    return ops;
}

function showRecetaIngredientesTable(ops) {
    ops.html = $("#receta_ingredientes").html();
    ops.titulo = "Ingredientes";
    ops.destino = $("#ingredientes_tableCross");

    ops.cols = [
        {head: "id", proper: 'id', class: "hidden id"},
        {proper: "nombreArticulo", head: "Articulo",class:"articuloNombre"},
        {proper: "idArticulo", head: "id", class: "hidden id_articulo"},
        {proper: "nombreUnidad", head: "Unidad", class: "centrate unidadNombre"},
        {proper: "idUnidad", head: "idUnidad", class: "hidden idUnidad"},
        {proper: "cantidad", head: "Cantidad", class: "centrate cantidad"}
    ];

    ops.asignar = function (data) {
        if (window.recetaIngredientes == undefined) {
        }

        if (ops.all == undefined) {
        }
    }
    ops.deleteFunction = function (id, button) {

        var name = button.closest("tr").find(".id").html();
        var c = 0;
        var t = null;
        $.grep(window.recetaIngredientes, function (e) {
            if (e != undefined && e.id_articulo == name) {
                t = c;
            }
            c++;
        });
        if (t != null) {
            window.recetaIngredientes.splice(t, 1);
        }
        showTable({tabla:"receta_ingredientes",modo:"tabla",tipo:"save"});
    }
    ops.prevDelete = function (me) {
    }
    ops.urlDelete = "#";
    ops.workObject = window.recetaIngredientes;
    if (!(ops.all==undefined)) {
        ops.url = "./rest/articulo/?mostrar=activos";
    } else {
        ops.workWithObject = true;
        ops.workObject = window.recetaIngredientes;
    }


    ops.submit = function () {

        var flo = $(".floating_form.clon_floating");

        if (!existWithVal(flo.find("#receta_articulo").data("ids"))) {
            flo.find("#receta_articulo").campoMal({mostrarMensaje: false, mostrarLongitud: false});
            return;
        } else {
            flo.find("#receta_articulo").campoBien();
        }

        flo.find("#receta_cantidad").valida({validaCon: "numeros", mostrarMensaje: false});
        flo.find("#receta_unidad_k").valida();

        if (flo.find(".campo_mal").not(".hidden").length > 0) {
            return false;
        }

        if (!(ops.fila == undefined)) {
            id = ops.fila.find('.id').html();
        } else {
            var id = null;
        }

        object = {
            'id': id,
            "nombreArticulo": flo.find("#receta_articulo").html(),
            "idArticulo": flo.find("#receta_articulo").data("ids")[0],
            "nombreUnidad": flo.find("#receta_unidad_k").find(':selected').html(),
            "idUnidad": flo.find("#receta_unidad_k").find(':selected').val(),
            "cantidad": flo.find("#receta_cantidad").val()
        }
        var articuloNombre=object.nombre;
        var c=0;
        var t=null;
        $.grep(window.recetaIngredientes, function (e) {
            if (e != undefined && e.nombre === articuloNombre) {
                t = c;
            }
            c++;
        });

        if (t != null) {
            window.recetaIngredientes.splice(t, 1);
        }
        window.recetaIngredientes.push(object);
        showTable({tabla: "receta_ingredientes", modo: "tabla", tipo: "save"});
        close_floating();
    };


    ops.floating_created = function (clon) {

        clon.find("#receta_cantidad").limita({validaCon: "numeros"});
        function a() {
            showTable({
                tabla: "receta_articulos",
                tipo: "select_one",
                modo: "table",
                type: "table",
                returnControl: $(this),
                id: ops.id
            });
        }
        fillSelect(window.unidadesEstandar, clon.find("#receta_unidad_k"), "nombre");
        if(!(ops.fila==undefined)){
            clon.find("#receta_articulo").addClass('label_disabled');

            clon.find("#receta_unidad_k").find('option[value="'+ops.fila.find('.id_unidad').html()+'"]').prop('selected',true);
            clon.find("#receta_unidad_k").attr('disabled', 'disabled');
            clon.find("#receta_articulo").html(ops.fila.find('.articuloNombre').html());
            clon.find("#receta_articulo").data('ids',[ops.fila.find('.id_articulo').html()]);
            clon.find("#receta_cantidad").val(ops.fila.find('.cantidad').html());
        }else{
            clon.find("#receta_articulo").click(a);
        }



        return ops
    }
}

function showRecetaCanalesTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Canales";
    ops.destino = $("#receta_canales_tableCross");
    ops.cols = [
        {head: "Id", proper: "id",class:"hidden id"},
        {head: "Canal", proper: "nombre",class:"nombre"}
    ];

    ops.asignar = function (data) {
        //if (ops.all==undefined) {
        //    window.recetaCanales=data;
        //}
    }

    ops.prevDelete=function(me){
        table= $("#canales_tableCross").find("table");

        var datatable=table.DataTable();
        row=$(me).closest("tr");
        row2=datatable.row(row);
        row2.remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }

    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {

    }
    if (ops.all) {
        ops.workWithObject=true;
        ops.workObject= window.canales;
        ops.cols = [
            {head: "id", proper: "id",class:"hidden id"},
            {head: "nombre", proper: "nombre",class:"nombre"},
        ];
    } else {
        ops.workWithObject=true;
        ops.workObject= window.recetaCanales;
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

        window.recetaCanales=clientes;
        close_floating();

        showTable({tabla:"receta_canales",modo:"tabla",tipo:"save"});

    };
    ops.clon_created = function (clon) {

    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(), window.recetaCanales,"id_canal","id");
        }
    };
    return ops
}

function showImportarReceta(ops){
    ops.html = $("#receta_importar_oculto").html();
    ops.titulo = "Importar receta";
    ops.destino = ops.returnControl;
    ops.cols = [];
    ops.asignar = function (data) {}
    ops.prevDelete=function(id,button){}
    ops.urlDelete = "#";
    ops.deleteFunction = function () {}
    ops.workWithObject=true;
    ops.workObject=[{}];
    ops.submitOfSelection = function () {

        var flo = $(".floating_table.clon_floating");
        var flo2 = $(".floating_form.clon_floating");
        var sel = extraerSeleccionados(flo);

        if(!(sel==undefined)&&sel.length>0){
            flo2.find("#grupo_tipo").val($(sel).closest("tr").find(".tipo").html());
            if($(sel).closest("tr").find(".tipo").html()=="1"){
                flo2.find("#receta_can").val("");
                flo2.find("#receta_can").closest(".form-group").addClass("hidden");

            }else{
                flo2.find("#receta_can").closest(".form-group").removeClass("hidden");
                flo2.find("#receta_can").val("");
            }
        }
    }
    ops.submit = function () {
        var flo = $(".floating_form.clon_floating");

        if (!existWithVal(flo.find("#importar_articulo").data("ids"))) {
            flo.find("#importar_articulo").campoMal({mostrarMensaje: false, mostrarLongitud: false});
            return;
        } else {
            flo.find("#importar_articulo").campoBien();
        }

        if (!existWithVal(flo.find("#importar_receta").data("ids"))) {
            flo.find("#importar_receta").campoMal({mostrarMensaje: false, mostrarLongitud: false});
            return;
        } else {
            flo.find("#importar_receta").campoBien();
        }

        if (flo.find(".campo_mal").not(".hidden").length > 0) {
            return false;
        }

        var articuloId=flo.find("#importar_articulo").data("ids")[0];
        var recetaId=flo.find("#importar_receta").data("ids")[0];
        close_floating();
        showChargeStep();
        var ajax = $.ajax({
            url: "./rest/articulo/" + articuloId + "/recetas/" + recetaId  + "/",
            type: "GET",
            success: function (data) {
                window.puntosReceta = data[3];
                window.recetaGruposeleccion = data[4];
                window.recetaIngredientes = data[1];
                window.recetaCanales = data[2];
                cargarCamposReceta(data[0][0],true);
                closeChargeStep();
            },
            error: function () {
                showMessage("error", "No se pudo cargar la receta");
                closeChargeStep();
            }
        });
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(), window.recetaGruposeleccion,"id","id");
        }
    };
    ops.floating_created = function (floating) {
        floating.find("#importar_articulo").off("click").on("click",function(){
                showTable({
                    tabla: "receta_articulos",
                    tipo: "select_one",
                    modo: "table",
                    type: "table",
                    returnControl: $(this),
                    id: ops.id,
                    importar:true
                });
        });

        floating.find("#importar_receta").off("click").on("click",function(){
            //console.log(floating.find("#importar_articulo").data("ids"));
            if(floating.find("#importar_articulo").data("ids")==undefined||floating.find("#importar_articulo").data("ids")[0]<1){
                showMessage("warning","Debe primero elegir un articulo para ver sus recetas");
                return;
            }
            showTable({
                tabla: "listaReceta",
                tipo: "select_one",
                modo: "table",
                type: "table",
                returnControl: $(this),
                id: ops.id,
                importar:true,
                articuloId:floating.find("#importar_articulo").data("ids")[0]
            });
        });
    };
    return ops;
}