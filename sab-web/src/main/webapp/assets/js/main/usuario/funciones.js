function crearUsuario(action,navigate){
    if(!($("#usuario_contra").val()==$("#usuario_contra2").val())){
        showMessage("warning","las contrase�as no coinciden");
        $("#usuario_contra").campoMal({mostrarMensaje:false});
        $("#usuario_contra2").campoMal({mostrarMensaje:false});
        return false;
    }

    if($("#usuario_contra").val()=="********"){
        $("#usuario_contra").val("");
        $("#usuario_contra2").val("");
    }

    $("#usuario_contra").valida({permitirEnBlanco:true,validaciones:"minusculas,mayusculas,numeros,simbolos_basicos",adicionales:"$,#,@,-,_,",longitud:{minimo:8,maximo:50}});
    $("#usuario_contra2").valida({permitirEnBlanco:true,mensajeParticular:"Digite letras, numeros y simbolos basicos",validaciones:"minusculas,mayusculas,numeros,simbolos_basicos",longitud:{minimo:8,maximo:50}});
    $("#usuario_nombre").valida({longitud:{minimo:5,maximo:100},mostrarLongitud:true,mostrarMensaje:true});
    $("#usuario_correo").valida({mensajeParticular:"Digite un correo valido",UsaEspecifica: true, validaciones: "correo", longitud: {minimo: 5, maximo: 100}, agitar: false });//plugin adicional

    if(!$("#usuario_section .campo_mal").length<1){
        $(".campo_mal:eq(0)").focus();
        closeChargeStep();
        return false;
    }else{

        if(($("#usuario_contra").val()==$("#usuario_contra2").val())&&($("#usuario_contra").val()=="")&&!(action=="ver")){
            var usuario = "";
        }else{
            var usuario = $("#usuario_contra").val();
        }

        if($("#usuario_roles_tableCross").find("td.id").length<1){
            showMessage("warning","Debe asignar minimo un rol al usuario");
            return;
        }

        if(window.usuarioRecienCreado==undefined&&usuario==""&&!(action=="ver")){
            $("#usuario_contra").campoMal({mostrarMensaje:false});
            $("#usuario_contra2").campoMal({mostrarMensaje:false});
            $("#usuario_contra").val("");
            $("#usuario_contra2").val("");
            return;
        }

        var id=null;
        var id_grupo=null;
        var id_punto=null;

        if($("#usuario_cliente_tableCross").find("td.nombre").length<1){
            showMessage("warning","Debe seleccionar un cliente");
            return;
        }else{
            var id=$("#usuario_cliente_tableCross").find("td.id").html();
        }

        if($("#usuario_punto_tableCross").find("td.nombre").length>0){
            id_punto=$("#usuario_punto_tableCross").find("td.id").html();
        }

        if($("#usuario_grupo_tableCross").find("td.nombre").length>0){
            id_grupo=$("#usuario_grupo_tableCross").find("td.id").html();
        }

        data={
            email: $("#usuario_correo").val(),
            password: usuario,
            username: $("#usuario_correo").val(),
            fullName: $("#usuario_nombre").val(),
            idGrupo: id_grupo,
            idCliente: id
        };

        if(!(action=="ver")&&(window.usuarioRecienCreado==undefined)){
            data.id=null;

        }else{
            data.id=window.usuarioC.get("id");
        }

        if($.grep(window.usuarios.models,function(object,index){
                if(data.id==object.get("id")){
                    return false;
                }else{
                    return object.get("fullName")==data.fullName;
                }
            }).length>0){
            showMessage("warning","Ya existe un usuario con ese nombre");
            if(!($("#usuario_contra").val()=="")&&!(action=="ver")){
                $("#usuario_contra").val("********");
                $("#usuario_contra2").val("********");
            }
            return;
        }

        if(action=="ver"||window.usuarioRecienCreado){
            var method="PUT";
            var url="./rest/user/"+data.id;
        }else{
            var method="POST";
            var url="./rest/user/";
        }

        $.ajax({
            type:method,
            contentType : 'application/json; charset=utf-8',
            dataType:"JSON",
            url:url,
            data:JSON.stringify(data),
            success:function(response) {

                if(!isNaN(response)){
                    window.usuarioC.set({id:response});
                    window.usuarioRecienCreado=true;
                    showMessage("good","El usuario ha sido guardado");
                    $("#usuario_contra").val("********");
                    $("#usuario_contra2").val("********");
                }


                showChargeStep();
                guardarRoles();
                closeChargeStep();
            },
            error:function(response,xhr,other){

                if(response.responseText=="usuario actualizado"){

                    showMessage("good","El usuario ha sido guardado");
                    $("#usuario_contra").val("********");
                    $("#usuario_contra2").val("********");
                    if(navigate){
                        Backbone.history.navigate("#lista/usuarios/", { trigger: true });
                        return false;
                    }
                    showChargeStep();
                    guardarRoles();
                    /*
                    if(navigate){
                        Backbone.history.navigate("#lista/usuarios/", { trigger: true });
                    }else{
                        $(".actions.form-actions.full").find(".next").addClass("continue").click();
                    }
                    */
                }else if(response.responseText.indexOf("ya")>-1){           //El Nombre
                    showMessage("error","El correo ya existe en la base de datos");  //El usuario ya existe en la base de datos
                }else{
                    showMessage("error","Ha ocurrido un error al guardar el usuario");
                }
            }
        });


    }
}

function showUsuariosClientesTable(ops){

    ops.html = ops.returnControl;
    ops.titulo = "Clientes";
    ops.destino = $("#usuario_cliente_tableCross");

    ops.asignar = function (data) {

    }
    ops.prevDelete=function(me){

        return;
    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {
        /*
         showMessage("good", "Se ha eliminado el usuario");
         showTable({tabla:"usuario_clientes",modo:"tabla",tipo:"save"});
         */
    }
    ops.returnControl = false;

    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "nombre"},
    ];

    if(ops.all==true){
        ops.url="./rest/cliente/";

    }else{
        console.log(window.usuarioCliente);
        ops.url="./rest/cliente/"+window.usuario.get("id");
        ops.workWithObject=true;
        ops.workObject=  [window.usuarioCliente];
    }

    ops.notDelete=true;
    ops.notEdit = true;
    ops.submit = function () {

        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var tds = datatable.$("input:checked");
        if(window.usuarioCliente==undefined){
            window.usuarioCliente={};
        }
        window.usuarioCliente.id=tds[0].id;
        window.usuarioCliente.nombre=tds.closest("tr").find(".nombre").html();
        close_floating();
        showTable({tabla:"usuario_clientes",modo:"tabla",tipo:"save"});


    };
    ops.floating_created = function (floating) {
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(),[window.usuarioCliente],"id","id");
        }
    };
    return ops
}

function showUsuarioAccesoPuntoTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Puntos";
    ops.destino = $("#usuario_punto_tableCross");
    ops.asignar = function (data) {

    }
    ops.prevDelete=function(me){
        return;
    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {
    }
    ops.returnControl = false;

    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "nombre"},
    ];

    if(ops.all==true){
        ops.url="./rest/punto/?idCliente="+window.usuario.get("idCliente");
    }else{
        ops.workWithObject=true;
        ops.workObject=  window.usuarioPunto;
    }
    ops.notDelete=true;
    ops.notEdit = true;
    ops.submit = function () {
        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var tds = datatable.$("input:checked");
        var arr=[];
        $.each(tds,function(){
            arr.push({id: this.id,nombre: $(this).closest("tr").find(".nombre").html()});
        });

        window.usuarioPunto=arr;

        showTable({tabla:"usuario_acceso_punto",modo:"tabla",tipo:"save"});
        close_floating();

    };
    ops.floating_created = function (floating) {
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(),window.usuarioPunto,"id","id");
        }
    };
    return ops
}

function showUsuarioAccesoGrupoTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Grupos";
    ops.destino = $("#usuario_grupo_tableCross");

    ops.asignar = function (data) {

    }
    ops.prevDelete=function(me){
        return;
    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {

    }
    ops.returnControl = false;

    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "nombre"},
    ];

    if(ops.all==true){
        ops.url="./rest/grupo/";

    }else{
        ops.url="./rest/grupo/"+window.usuario.get("id");
        ops.workWithObject=true;
        ops.workObject=  [window.usuarioGrupo];
    }
    ops.notDelete=true;
    ops.notEdit = true;
    ops.submit = function () {
        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var tds = datatable.$("input:checked");
        if(window.usuarioGrupo==undefined){
            window.usuarioGrupo={};
        }
        window.usuarioGrupo.id=tds[0].id;
        window.usuarioGrupo.nombre=tds.closest("tr").find(".nombre").html();
        showTable({tabla:"usuario_acceso_grupo",modo:"tabla",tipo:"save"});
        close_floating();

    };
    ops.floating_created = function (floating) {
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(),[window.usuarioGrupo],"id","id");
        }
    };
    return ops
}

function showRolesTable(ops){
    ops.html = ops.returnControl;
    ops.titulo = "Roles";
    ops.destino = $("#usuario_grupo_tableCross");

    ops.asignar = function (data) {

    }
    ops.prevDelete=function(me){
        return;
    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {

    }
    ops.returnControl = false;

    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "nombre"},
    ];

    if(ops.all==true){
        ops.url="./rest/user/userprofile/"+window.usuario.get("id");

    }else{

        ops.url="./rest/grupo/"+window.usuario.get("id");
        ops.workWithObject=true;
        ops.workObject=  [window.usuarioGrupo];
    }
    ops.notDelete=true;
    ops.notEdit = true;
    ops.submit = function () {
        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var tds = datatable.$("input:checked");
        if(window.usuarioGrupo==undefined){
            window.usuarioGrupo={};
        }
        window.usuarioGrupo.id=tds[0].id;
        window.usuarioGrupo.nombre=tds.closest("tr").find(".nombre").html();
        showTable({tabla:"usuario_acceso_grupo",modo:"tabla",tipo:"save"});
        close_floating();

    };
    ops.floating_created = function (floating) {
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(),[window.usuarioGrupo],"id","id");
        }
    };
    return ops
}

function showUsuariosRolesTable(ops){
    ops.returnControl=false;
    ops.html = ops.returnControl;
    ops.titulo = "Roles";
    ops.destino = $("#usuario_roles_tableCross");

    ops.asignar = function (data) {

    }
    ops.prevDelete=function(me){

        return;
    }
    ops.urlDelete = "#";
    ops.deleteFunction = function () {
        /*
         showMessage("good", "Se ha eliminado el usuario");
         showTable({tabla:"usuario_clientes",modo:"tabla",tipo:"save"});
         */
    }

    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "nombre"},
        {proper: "id_rol_cliente", head: "id_rol_cliente", class: "id_rol_cliente hidden"}

    ];

    if(ops.all==true){
        ops.url="./rest/rolcliente/";

    }else{
        //ops.url=
        ops.workWithObject=true;
        ops.workObject=  window.rolesCliente;
    }

    ops.notDelete=true;
    ops.notEdit = true;


    ops.submit = function () {

        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();

        var tds = datatable.$("input:checked");
        var arr=[];
        $.each(tds,function(){
            arr.push({id: this.id,nombre: $(this).closest("tr").find(".nombre").html(),id_rol_cliente:$(this).closest("tr").find(".id_rol_cliente").html()});
        });

        window.rolesCliente=arr;
        close_floating();
        showTable({tabla:"usuario_roles",modo:"tabla",tipo:"save"});


    };
    ops.floating_created = function (floating) {
    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            console.log(window.rolesCliente);
            seleccionaDeFlo($(".clon_floating").last(),window.rolesCliente,"id","id");
        }
    };
    return ops
}

function guardarRoles(){
    $.ajax({
        type:"POST",
        contentType : 'application/json; charset=utf-8',
        dataType:"JSON",
        url:"./rest/rolcliente/userrol/?id_user=" + window.usuarioC.get("id") + "&id_rolcliente=" + window.rolesCliente[0].id,
        data:JSON.stringify(window.rolesCliente),
        success:function(response) {
            closeChargeStep();
            /*
            if(!(window.usuario.get("id")=="1")){
                Backbone.history.navigate("#lista/usuarios/", { trigger: true });
                return;
            }
            */

            $(".actions.form-actions.full").find(".next").addClass("continue").click();
        },
        error:function(response,xhr,other){

        }
    });
}