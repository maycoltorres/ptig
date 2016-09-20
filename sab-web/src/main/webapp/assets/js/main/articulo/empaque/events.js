function eventosClickEmpaque(id,action){
    $("#empaque_ver").click(function(){
        $("#empaques_listado_table_cross").removeClass("hidden");
        $("#empaques_data").addClass("hidden");
        showTable({tabla:"listaEmpaque",modo:"tabla",tipo:"save",custom:true});
    });

    $("#empaque_importar").click(function(){
        showTable({tabla: "empaque_importar", modo: "form", tipo: "floating"});
    });

    $("#guardar").click(function(){
        validaEmpaque(id,action);
    });

    $("#empaque_limpiar").click(function(){
        $("#empaque_guardar #nombre").val("");
        $("#descripcion").val("");
        $("#empaque_guardar #empaque_cantidad_base").val("");
        $("#empaque_guardar #canales_d").html("");
        $("#empaque_guardar #canales_d").removeData();
        $("#empaque_puntos_tableCross").html("");
        $("#ingredientes_tableCross").html("");
        $("#grupo_s_tableCross").html("");
        $("#empaque_unidad").find(".empty").prop("selected",true);
    });

    $("#empaque_crear").click(function(){
        window.puntosEmpaque=[];
        window.EmpaqueGruposeleccion=[];
        window.EmpaqueIngredientes=[];
        window.EmpaqueCanales=[];
        $("#empaque_limpiar").click();
        $("#empaques_listado_table_cross").addClass("hidden");
        $("#empaques_data").removeClass("hidden");
        window.EmpaqueId=undefined;
        showTable({tabla:"empaque_puntos",modo:"tabla",tipo:"save"});
        showTable({tabla:"empaque_canales",modo:"tabla",tipo:"save"});
        showTable({tabla:"empaque_ingredientes",modo:"tabla",tipo:"save"});
        showTable({tabla:"empaque_grupoSeleccion",modo:"tabla",tipo:"save"});
    });

    $("#empaque_des").click(function(){
        showTable({tabla:"tablaListadoEmpaques",modo:"tabla",tipo:"save",des:true});
        $(this).addClass("hidden");
        $("#empaque_crear").addClass("hidden");
        $("#empaque_act").removeClass("hidden");
        $("#empaque_lista").find(".panel-body").html("");

    });

    $("#empaque_act").click(function(){
        $("#empaque_lista").find(".panel-body").html("");
        $(this).addClass("hidden");
        $("#empaque_crear").removeClass("hidden");
        $("#empaque_des").removeClass("hidden");
        showTable({tabla:"tablaListadoEmpaques",modo:"tabla",tipo:"save"});
    });

    $(".wizard_ver").click(function(){
        $("#empaque_lista").find(".panel-body").children().remove();
        $("#empaque_lista").find(".panel-body").append($("#connection_cargando").clone().removeClass("hidden"));
    });

    $("#elementos_emp").off("click").on("click",function(){
        showTable({tabla:"tablaElementosEmpaque",type: "table",tipo:"select_multiple",returnControl:"",all:true});
    });

    $("#puntos_emp").off("click").on("click",function(){
        showTable({tabla:"tablaPuntosEmpaque",type: "table",tipo:"select_multiple",returnControl:"",all:true});
    });

    $("#canales_emp").off("click").on("click",function(){
        showTable({tabla:"tablaCanalesEmpaque",type: "table",tipo:"select_multiple",returnControl:"",all:true});
    });

    $("#empaque_div_des").off("click").on("click",function(){
        $(this).addClass("hidden");
        $("#empaque_div_crear").addClass("hidden");
        $("#empaque_div_act").removeClass("hidden");
        showTable({tabla:"tablaElementosEmpaque",modo:"tabla",tipo:"save",id:id,des:true});
    });

    $("#empaque_div_act").off("click").on("click",function(){
        $(this).addClass("hidden");
        $("#empaque_div_crear").removeClass("hidden");
        $("#empaque_div_des").removeClass("hidden");
        showTable({tabla:"tablaElementosEmpaque",modo:"tabla",tipo:"save",id:id});
    });

    $("#empaque_div_crear").off("click").on("click",function(){
        showTable({tabla: "tablaElementosEmpaque", modo: "form", tipo: "floating"});
    });

}

function cargarCamposEmpaque(data,parcial){
    if(parcial==undefined){
        $("#nombre").val(data.nombre);
        window.EmpaqueId=data.id;
        $("#empaque_lista").addClass("hidden");
        $("#empaque_guardar").removeClass("hidden");
        $("#empaque_cantidad_base").val(data.cantidad);
        $("#empaque_unidad").find("option[value='"+data.unidadId+"']").prop("selected",true);
    }
    $("#descripcion").val(data.preparacion);
    showTable({tabla:"empaque_puntos",modo:"tabla",tipo:"save"});
    showTable({tabla:"empaque_canales",modo:"tabla",tipo:"save"});
    showTable({tabla:"empaque_ingredientes",modo:"tabla",tipo:"save"});
    showTable({tabla:"empaque_grupoSeleccion",modo:"tabla",tipo:"save"});
}
//change,keyup
function eventosVariosEmpaque(){
    //Evita que se pueda eligiar solamente la caracteristica de Empaque ademas carga el html del paso
    //muestra o esconde el texto para mostrar
}
// Eventos disparados por el programa
function validaEmpaque(id,action){
    if($("#empaque_lista").hasClass("hidden")){
        $("#empaque_unidad").valida();
        $("#nombre").valida({validaCon: "letras", mostrarMensaje:false});
        $("#empaque_cantidad_base").valida({validaCon: "numeros", mostrarMensaje:false});
        $("#descripcion").valida({longitud:{minimo:1,maximo:4000},validaCon: "letras,numeros", mostrarMensaje:true, permitirEnBlanco:true});

        if($("#ingredientes_tableCross").find("td.unidadNombre").length<1){
            showMessage("warning","Necesita al menos un ingrediente");
            return;
        }

        if($("#empaque_puntos_tableCross").find(".id").length<1){
            showMessage("warning","Necesita al menos un punto");
            return;
        }

        if($("#empaque_canales_tableCross").find(".id").length<1){
            showMessage("warning","Necesita al menos un canal configurado");
            return;
        }

        if($("#empaque_guardar").find(".campo_mal").not(".hidden").length>0){
                return;
        }
        var nombre=$("#nombre").val();

        if(window.EmpaqueId==undefined||!(window.EmpaqueId>0)){
            var id_=null;
        }else{
            var id_=window.EmpaqueId;
        }
        if(window.actionGlobal=="ver"){
            var t=$.grep(window.EmpaqueLista,function(e){
                if(e.id==id_){
                    return false;
                }else if(e.nombre==nombre){
                    return true;
                }
            });
            if(t.length>0){
                showMessage("warning","Este nombre ya se encuentra en otra Empaque");
                return false;
            }
        }else{
            var t=$.grep(window.EmpaqueLista,function(e){
                return e.nombre==nombre;
            });
            if(t.length>0){
                showMessage("warning","Este nombre ya se encuentra en otra Empaque");
                return false;
            }
        }

        data={
            id:id_,
            nombre:$("#nombre").val(),
            articuloId:window.articulo.id,
            preparacion:$("#descripcion").val(),
            cantidad:$("#empaque_cantidad_base").val(),
            unidadId:$("#empaque_unidad").find(":selected").val(),
            nueva:false
        }

        if(window.EmpaqueId==undefined||!(window.EmpaqueId>0)){
            data.nueva=true;
        }

        guardarEmpaque(data);


    }else{
        Backbone.history.navigate("#lista/articulos/", { trigger: true });
    }
}

function guardarEmpaque(obj){
    window.receteActual=obj;
    var data={
        Empaque:obj,
        canales:window.EmpaqueCanales,
        ingredientes:window.EmpaqueIngredientes,
        grupoSeleccion:window.EmpaqueGruposeleccion,
        puntos:window.puntosEmpaque
    };

    url= "./rest/articulo/" + window.articulo.get("id") + "/empaques",
    $.ajax({
        url: url,
        type:"POST",
        data:JSON.stringify(data),
        success:function(data){

            //showTable({tabla: "listaEmpaque", modo: "tabla", tipo: "save", custom: true});
            showMessage("good","La Empaque ha sido guardada exitosamente");
            $("#empaque_ver").trigger("click");
            $("#empaque_guardar").find(".validado").removeClass("validado");
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

function salirDeEmpaque(){
    //Backbone.history.navigate("#lista/articulos/", { trigger: true });
    return true;
}

function tablaListadoEmpaques(ops){
    ops.custom=true;
    ops.titulo = "";
    ops.destino = $("#empaques_listado_table_cross div.panel-body");
    ops.html = " ";
    ops.urlDelete = "#";
    ops.deleteMethod=true;
    ops.method = "GET";
    ops.cols = [
        {head: "id", proper: 'id', class: "hidden id"},
        {head: "Por defecto", proper: "predeterminada", class: "pre",mask:{"true":'<input type="radio" name="empaque_check class="form-control" />',"false":'<input type="radio" name="empaque_check" class="form-control" />'}},
        {head: "Nombre", proper: 'nombre', class: "bodegaNombre"}
    ];
    ops.deleteFunction = function() {

    };
    ops.tableCreated = function (table, datatable, clon) {

    };
    ops.asignar = function (data) {
        window.empaques=data;
    };
    if (ops.custom == true) {
        if (ops.des == true) {
            ops.url = "./rest/articulo/" + window.articulo.id + "/grupoEmpaque/?mostrar=inactivos";
            ops.cols.push({head: 'Acciones', embed: '<input type="button" value="Activar" class="btn btn-primary activar" />'});
        } else {
            ops.url = "./rest/articulo/"+ window.articulo.id + "/grupoEmpaque/";
            ops.cols.push({head: 'Acciones', embed: '<input type="button" value="Ver datos" class="btn btn-primary ver" /><input type="button" value="Inactivar" class="btn btn-danger desactivar">'});
        }
    }

    ops.customSet = function () {
        ops.destino.html($("#connection_cargando").clone().removeClass("hidden"));
        ops.container = ops.destino;

        ops.events = function (table) {
            table.find(".desactivar").click(function () {
                var me=this;
                me.seguro=function(){
                    desactivarEmpaque(me,table);

                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                };
                showConfirm(me,"Esta seguro que quiere inactivar el empaque?","Inactivar");
            });
            table.find(".activar").click(function () {
                var me=this;
                me.seguro=function(){
                    activarEmpaque(me,table);
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                };
                showConfirm(me,"Esta seguro que quiere activar el empaque?","Activar");
            });
            table.find(".ver").click(function () {
                $("#empaques_listado_table_cross").addClass("hidden");
                $("#empaques_data").removeClass("hidden");
                cargarDatosEmpaque(this.id);
                $("#empaque_nombre").val($(this).closest("tr").find(".bodegaNombre").html());
                //showTable({tabla: "lista_de_empaques", modo: "form", tipo: "floating",fila:$(this).closest("tr")});
            });
        }
    };
    return ops;
}

function cargarDatosEmpaque(id){
    window.empaqueActual=id;
    showChargeStep();
    $.ajax({
        url:"./rest/grupoEmpaque/"+id+"/empaques/",
        success:function(data) {

            closeChargeStep();
        },
        error:function(){
            verifyConnection("Verifique su conexion",function(){
                showMessage("good","Verifique la conexion");
            });
            closeChargeStep();
        }
    });
    showTable({tabla:"tablaElementosEmpaque",modo:"tabla",tipo:"save",id:id});
    showTable({tabla:"tablaCanalesEmpaque",modo:"tabla",tipo:"save",id:id});
    showTable({tabla:"tablaPuntosEmpaque",modo:"tabla",tipo:"save",id:id});
}

function tablaElementosEmpaque(ops) {
    ops.custom=true;
    ops.titulo = "Empaque";
    ops.destino = $("#empaques_c_tableCross");
    ops.html = $("#empaque_elementos").html();
    ops.urlDelete = "#";
    ops.empaque=true;
    ops.deleteMethod=true;
    ops.method = "GET";
    if(ops.empaque){
        ops.cols = [
            {head: "id", proper: 'id', class: "hidden id"},
            {head: "Articulo", proper: "nombre", class: "articuloNombre"},
            {head: "Unidad", proper: "nombreUnidad", class: "nombreUnidad"},
            {head: "tipoUnidad", proper: "tipoUnidad", class: "tipoUnidad hidden"},
            {head: "Articulo", proper: "idArticulo", class: "pre hidden articuloId"},
            {head: "Unidad", proper: 'idUnidad', class: "unidad hidden"},
            {head: "Cantidad", proper: 'cantidad', class: "cantidad"}
        ];
    }else{
        ops.cols = [
            {head: "id", proper: 'id', class: "hidden id"},
            {head: "Articulo", proper: "nombreArticulo", class: "articuloNombre"},
            {head: "Unidad", proper: "nombreUnidad", class: "nombreUnidad"},
            {head: "tipoUnidad", proper: "tipoUnidad", class: "tipoUnidad hidden"},
            {head: "Articulo", proper: "idArticulo", class: "pre hidden articuloId"},
            {head: "Unidad", proper: 'idUnidad', class: "unidad hidden"},
            {head: "Cantidad", proper: 'cantidad', class: "cantidad"}
        ];
    }

    ops.deleteFunction = function() {

    };

    ops.tableCreated = function (table, datatable, clon) {

    };
    ops.asignar = function (data) {
        window.elementos=data;
    };
    if (ops.custom == true) {
        if (ops.des == true) {
            ops.url = "./rest/grupoEmpaque/"+window.empaqueActual+"/empaques/?mostrar=inactivos";
            ops.cols.push({head: 'Acciones', embed: '<input type="button" value="Activar" class="btn btn-primary activar" />'});
        } else {
            if(ops.url==undefined){
                ops.url = "./rest/grupoEmpaque/"+window.empaqueActual+"/empaques/";
            }
            ops.cols.push({head: 'Acciones', embed: '<input type="button" value="Ver datos" class="btn btn-primary ver" /><input type="button" value="Inactivar" class="btn btn-danger desactivar">'});
        }
    }

    ops.customSet = function () {
        ops.destino.html($("#connection_cargando").clone().removeClass("hidden"));
        ops.container = ops.destino;

        ops.events = function (table) {
            table.find(".desactivar").click(function () {
                var me=this;
                me.seguro=function(){
                    desactivarEmpaque(me,table);
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                };
                showConfirm(me,"Esta seguro que quiere inactivar el empaque?","Inactivar");
            });
            table.find(".activar").click(function () {
                var me=this;
                me.seguro=function(){
                    activarEmpaque(me,table);
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                };
                showConfirm(me,"Esta seguro que quiere activar el empaque?","Activar");
            });
            table.find(".ver").click(function () {
                showTable({tabla: "tablaElementosEmpaque", modo: "form", tipo: "floating",fila:$(this).closest("tr")});
            });
        }
    };
    ops.floating_created=function(){
        var flo=$(".floating_form.floating_form");
        if(flo.length>0){
            flo.find("#empaque_cantidad").limita({validaCon: "numeros"});
            fillSelect(megaConsulta[6], flo.find("#empaque_unidad_k"), "nombre");

            if(!(ops.fila==undefined)){
                flo.find("#receta_articulo").addClass('label_disabled');

                flo.find("#receta_unidad_k").find('option[value="'+ops.fila.find('.id_unidad').html()+'"]').prop('selected',true);
                flo.find("#receta_unidad_k").attr('disabled', 'disabled');
                flo.find("#receta_articulo").html(ops.fila.find('.articuloNombre').html());
                flo.find("#receta_articulo").data('ids',[ops.fila.find('.id_articulo').html()]);
                flo.find("#receta_cantidad").val(ops.fila.find('.cantidad').html());
            }else{
                flo.find("#empaque_articulo").off("click").on("click",function(){
                    showTable({
                        tabla: "tablaElementosEmpaque",
                        tipo: "select_one",
                        modo: "table",
                        type: "table",
                        returnControl: $(this),
                        url:"./rest/articulo/?empaque=true",
                        empaque:true
                    });
                });
            }
            return ops
        }
    }
    return ops;
}

function tablaCanalesEmpaque(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Canales";
    ops.destino = $("#empaque_canales_tableCross");
    ops.cols = [
        {head: "Id", proper: "id",class:"hidden id"},
        {head: "Canal", proper: "nombre",class:"nombre"},
        {head: "id_canal", proper: "id",class:"nombre hidden"}
    ];

    ops.asignar = function (data) {
        if (ops.all==undefined) {
            window.EmpaqueCanales=data;
        }
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
        ops.workObject= megaConsulta[16];
        ops.cols = [
            {head: "id", proper: "id",class:"hidden id"},
            {head: "nombre", proper: "nombre",class:"nombre"},
        ];
    } else {
        //ops.workWithObject=true;
        //ops.workObject= window.EmpaqueCanales;
        ops.url="./rest/grupoEmpaque/"+window.empaqueActual+"/canales";
    }

    ops.notDelete=true;
    ops.notEdit = true;

    ops.submit = function () {

        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var canales=[];
        var tds = datatable.$("input:checked");

        tds.each(function(index,value){
            var c={};
            c.id=this.id;
            canales.push(c);
        });

        $.ajax({
            url:"./rest/grupoEmpaque/"+window.empaqueActual+"/canales",
            method:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(canales),
            success:function(data){
                close_floating();
                window.EmpaqueCanales=canales;
                showTable({tabla:"tablaCanalesEmpaque",modo:"tabla",tipo:"save"});
            },
            error:function(){
                close_floating();
                verifyConnection("Error de conexion",function(){
                    showMessage("error","Error de conexion");
                });
            }
        });
    };
    ops.clon_created = function (clon) {

    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(), window.EmpaqueCanales,"id","id");
        }
    };
    return ops
}

function tablaPuntosEmpaque(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Puntos";
    ops.destino = $("#empaque_puntos_tableCross");
    ops.cols = [
        {head: "Id", proper: "id",class:"hidden id"},
        {head: "Punto", proper: "nombre",class:"nombre"}
    ];

    ops.asignar = function (data) {
        if (ops.all==undefined) {
            window.EmpaquePuntos=data;
        }
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
        ops.workObject= megaConsulta[2];
        ops.cols = [
            {head: "id", proper: "id",class:"hidden id"},
            {head: "nombre", proper: "nombre",class:"nombre"},
            {head: "puntoId", proper: "puntoId",class:"puntoId hidden"}
        ];
    } else {
        ops.url="./rest/grupoEmpaque/"+window.empaqueActual+"/puntos";
        //ops.workWithObject=true;
        //ops.workObject= window.EmpaquePuntos;
    }

    ops.notDelete=true;
    ops.notEdit = true;

    ops.submit = function () {

        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var puntos=[];
        var tds = datatable.$("input:checked");

        tds.each(function(index,value){
            var c={};
            c.id=$(this).closest("tr").find(".puntoId").html();
            puntos.push(c);
        });

        $.ajax({
            url:"./rest/grupoEmpaque/"+window.empaqueActual+"/puntos",
            method:"POST",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(puntos),
            success:function(data){
                close_floating();
                window.EmpaquePuntos=puntos;
                showTable({tabla:"tablaPuntosEmpaque",modo:"tabla",tipo:"save"});
            },
            error:function(){
                close_floating();
                verifyConnection("Error de conexion",function(){
                    showMessage("error","Error de conexion");
                });
            }
        });

    };
    ops.clon_created = function (clon) {

    };
    ops.tableCreated = function (table, datatable, clon) {

        if($(".clon_floating").find(".nombre").length>0){
            $.each(window.EmpaquePuntos,function(index,value){
                clon.find("td:contains('"+value.id+"')").closest("tr").find("input").prop("checked",true);
            });
        }

    };
    return ops
}

function showImportarEmpaque(ops){
    ops.html = $("#empaque_importar_oculto").html();
    ops.titulo = "Importar Empaque";
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
                flo2.find("#empaque_can").val("");
                flo2.find("#empaque_can").closest(".form-group").addClass("hidden");

            }else{
                flo2.find("#empaque_can").closest(".form-group").removeClass("hidden");
                flo2.find("#empaque_can").val("");
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

        if (!existWithVal(flo.find("#importar_Empaque").data("ids"))) {
            flo.find("#importar_Empaque").campoMal({mostrarMensaje: false, mostrarLongitud: false});
            return;
        } else {
            flo.find("#importar_Empaque").campoBien();
        }

        if (flo.find(".campo_mal").not(".hidden").length > 0) {
            return false;
        }

        var articuloId=flo.find("#importar_articulo").data("ids")[0];
        var EmpaqueId=flo.find("#importar_Empaque").data("ids")[0];
        close_floating();
        showChargeStep();
        var ajax = $.ajax({
            url: "./rest/articulo/" + articuloId + "/grupoEmpaque/" + EmpaqueId  + "/",
            type: "GET",
            success: function (data) {
                window.puntosEmpaque = data[3];
                window.EmpaqueGruposeleccion = data[4];
                window.EmpaqueIngredientes = data[1];
                window.EmpaqueCanales = data[2];
                cargarCamposEmpaque(data[0][0],true);
                closeChargeStep();
            },
            error: function () {
                showMessage("error", "No se pudo cargar la Empaque");
                closeChargeStep();
            }
        });
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(), window.EmpaqueGruposeleccion,"id","id");
        }
    };
    ops.floating_created = function (floating) {
        floating.find("#importar_articulo").off("click").on("click",function(){
                showTable({
                    tabla: "empaque_articulos",
                    tipo: "select_one",
                    modo: "table",
                    type: "table",
                    returnControl: $(this),
                    id: ops.id,
                    importar:true
                });
        });

        floating.find("#importar_Empaque").off("click").on("click",function(){
            //console.log(floating.find("#importar_articulo").data("ids"));
            if(floating.find("#importar_articulo").data("ids")==undefined||floating.find("#importar_articulo").data("ids")[0]<1){
                showMessage("warning","Debe primero elegir un articulo para ver sus Empaques");
                return;
            }
            showTable({
                tabla: "listaEmpaque",
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

function activarEmpaque(me,table){
    $.ajax({
        url:"./rest/grupoEmpaque/"+me.id+"/activar",
        type:"POST",
        success:function(){
            dt=table.DataTable();
            dt.row($(me).closest("tr")).remove();
            $(me).closest("tr").remove();
            dt.draw(false);
            showMessage("info","Se ha activado");
        },
        error:function(){
            showMessage("error","No ha sido posible activar el registro");
        }
    });
}

function desactivarEmpaque(me,table){
    $.ajax({
        url:"./rest/grupoEmpaque/"+me.id+"/inactivar",
        type:"POST",
        success:function(){
            dt=table.DataTable();
            dt.row($(me).closest("tr")).remove();
            $(me).closest("tr").remove();
            dt.draw(false);
            showMessage("info","Se ha inactivado");
        },
        error:function(){
            showMessage("error","No ha sido posible inactivar el registro");
        }
    });
}