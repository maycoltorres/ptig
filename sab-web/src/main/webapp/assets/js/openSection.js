function openSection(section, data,action,des,id) {
    window.inSection=section;

    esconder_menu_lateral();

    var titulo_heading = "";
    var icon_miga = "";
    var texto_btn_crear = "";
    var texto_btn_des = "";
    var icon_btn_crear = "i-plus";
    var clases_btn_des= "inactivados";
    var clases_btn_crear="abrir";
    var clases_btn_ver="ver_datos action";
    var clases_btn_desactivar="desactivar action btn-danger";
    var texto_btn_desactivar="Inactivar";
    var section_m=section;
    var data_des="";
    var data_act="";
    var more_buttons="";
    if(des){
        clases_btn_crear="";
        clases_btn_des="inactivados hidden btn-warning";
        clases_btn_ver="hidden";
        clases_btn_desactivar="Activar action";
        texto_btn_desactivar="Activar";
        icon_btn_crear="i-eye"
    }
    $("#miga").removeClass("not_link");
    var columnas=[];

    switch (section){

        case "home":
            $("#breadcrumb li:gt(0)").remove();
            $("#miga").addClass("not_link");
            $("#principal").html($("#home_m").html());
            return;
            break;
        case "clientes":
            var abrirTabla=true;
            titulo_heading = "Listado de clientes activos";
            icon_miga = "i-user-3";
            texto_btn_crear = "Crear un nuevo cliente";
            texto_btn_des = "Ver clientes inactivados";
            columnas=[
                {proper:"tabla_de_clientes",head:"",class:"hidden tabla_de_clientes"},
                {proper:"nombre",head:"Razon social",class:"nombre"},
                {proper:"nit",head:"Nit"},
                {proper:"telefono",head:"Telefono"},
                {proper:"nombreDueno",head:"Nombre del propietario"}
            ];
            data_des="Esto tambien deshabilitara:<br> A todos sus puntos, a sus marcas registradas, los usuarios asosiados a este cliente no podran iniciar sesion";
            data_act="Esto tambien activara:<br> A todos sus puntos, a sus marcas registradas, permitira a los usuarios asosiados a este cliente iniciar sesion";
            if(existWithVal(des)){
                titulo_heading = "Listado de clientes inactivados";
                texto_btn_crear = "Ver clientes activos";
            }else{
                more_buttons='<input type="button" value="Ver puntos" class="btn btn-warning openPuntosCliente">';
            }
            window.cliente.recienCreado=undefined;
            break;
        case "grupos":

            titulo_heading = "Listado de grupos activos";
            icon_miga = "i-people";
            texto_btn_crear = "Crear un nuevo grupo";
            texto_btn_des = "Ver grupos inactivados";
            data_des="Esto tambien deshabilitara:<br> A todos los clientes que hagan parte de este grupo, los usuarios de estos clientes no podran iniciar sesion";
            data_act="Esto tambien activara:<br> A todos los clientes que hagan parte de este grupo, permitira a los usuarios de estos clientes iniciar sesion";
            columnas=[
                {proper:"tabla_de_grupos",head:"",class:"hidden tabla_de_grupos"},
                {proper:"nombre",head:"Nombre"},
                {proper:"updatedBy",head:"Actualizado por"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de grupos inactivados";
                texto_btn_crear = "Ver grupos activos";
            }
            break;
        case "marcas":
            //window.marcas.clear().set(window.marcas.defaults);
            //window.marcas.set(data);


            titulo_heading = "Listado de marcas activas";
            icon_miga = "i-wave";
            texto_btn_crear = "Crear una nueva marca";
            texto_btn_des = "Ver marcas inactivadas";
            data_des="Esto tambien deshabilitara:<br> A todos los puntos asociados a esta marca";
            data_act="Esto tambien activara:<br> A todos los puntos asociados a esta marca";
            columnas=[
                {proper:"tabla_de_marcas",head:"",class:"hidden tabla_de_marcas"},
                {proper:"nombre",head:"Nombre"},
                {proper:"updatedBy",head:"Actualizado por"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de marcas inactivadas";
                texto_btn_crear = "Ver marcas activas";
            }
            break;
        case "puntos":
            //window.puntos.clear().set(window.puntos.defaults);
            //window.puntos.set(data);
            var custombottons=true;
            var msg="Ciente: "+clienteDePuntoNombre+", ";
            if(clienteDePuntoNombre==undefined){
                msg="";
            }
            titulo_heading = msg+" Listado de puntos activos";
            icon_miga = "i-grid-4";
            texto_btn_crear = "Crear un nuevo punto";
            texto_btn_des = "Ver puntos inactivados";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_puntos",head:"",class:"hidden tabla_de_puntos"},
                {proper:"nombre",head:"Nombre"}
            ];
            if(existWithVal(des)){
                titulo_heading = msg+" Listado de puntos inactivos";
                texto_btn_crear = "Ver puntos activos";
            }
            window.puntoRecienCreado=undefined;
            break;
        case "articulos":
            var custombottons="articulos_eventos_de_lista";
            titulo_heading = "Listado de articulos activos";
            icon_miga = "i-cart-6";
            texto_btn_crear = "Crear un nuevo articulo";
            texto_btn_des = "Ver articulos inactivados";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_articulos",head:"",class:"hidden tabla_de_articulos"},
                {proper:"nombre",head:"Nombre"},
                {proper:"unidadKardex",head:"Unidad de<br>Kardex ",mask:{true:"",false:""}},
                {proper:"unidadVenta",head:"Unidad de<br>Venta",mask:{true:"",false:""}},
                {proper:"inventario",head:"Maneja Inventario",convert:true,class:"centrate"},
                {proper:"venta",head:"Es para<br>la venta",convert:true,class:"centrate"},
                {proper:"receta",head:"Tiene receta",convert:true,class:"centrate"},
                {proper:"seleccion",head:"Hace parte de<br> una seleccion",convert:true,class:"centrate"}
            ];

            if(existWithVal(des)){
                titulo_heading = "Listado de articulos inactivados";
                texto_btn_crear = "Ver articulos activos";
            }else{
                more_buttons='<input type="button" value="Ver puntos" class="btn btn-warning openPuntos">';
            }

        break;

        case "roles_cliente":
            //window.roles_cliente.clear().set(window.roles_cliente.defaults);
            //window.roles_cliente.set(data);
            titulo_heading = "Listado de roles de cliente activos";
            icon_miga = "i-users-5";
            texto_btn_crear = "Crear un nuevo rol";
            texto_btn_des = "Ver roles de cliente inactivados";
            section_m="roles de cliente";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_rol_cliente",head:"",class:"hidden tabla_de_rol_cliente"},
                {proper:"nombre",head:"Nombre"},
                {proper:"updatedBy",head:"Actualizado por"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de roles de cliente inactivados";
                texto_btn_crear = "Ver roles de cliente inactivados";
            }
            break;
        case "roles":
            //window.roles.clear().set(window.roles.defaults);
            //window.roles.set(data);
            titulo_heading = "Listado de roles activos";
            icon_miga = "i-shield";
            texto_btn_crear = "Crear un nuevo rol";
            texto_btn_des = "Ver roles inactivados";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_roles",head:"",class:"hidden tabla_de_roles"},
                {proper:"nombre",head:"Nombre"},
                {proper:"updatedBy",head:"Actualizado por"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de roles inactivados";
                texto_btn_crear = "Ver roles activos";
            }
            break;
        case "usuarios":
            //window.usuarios.clear().set(window.usuarios.defaults);
            //window.usuarios.set(data);
            //window.usuarios.clear().set(window.usuarios.defaults);
            //window.rol_cliente.set(data);

            titulo_heading = "Listado de usuarios activos";
            icon_miga = "i-vcard";
            texto_btn_crear = "Crear un nuevo usuario";
            texto_btn_des = "Ver usuarios inactivados";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_usuarios",head:"",class:"hidden tabla_de_usuarios"},
                {proper:"fullName",head:"Nombre"},
                {proper:"username",head:"Nombre de usuario"},
                {proper:"email",head:"Correo"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de usuarios inactivados";
                texto_btn_crear = "Ver usuarios activos";
            }
            break;
        case "selecciones":
            window.selecciones.asignados=true;
            var custombottons="grupoSeleccion_eventos_de_lista";
            titulo_heading = "Listado de grupos de seleccion";
            icon_miga = "i-stack-plus";
            texto_btn_crear = "Crear un grupo seleccion";
            texto_btn_des = "Ver grupos de seleccion inactivados";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_selecciones",head:"",class:"hidden tabla_de_selecciones"},
                {proper:"nombre",head:"Nombre"},
                {proper:"tipoGrupo",head:"Tipo",mask:{true:"Abierta", false:"Cerrada"},class:"centrate"},
                {proper:"ppiv",head:"PPIV",convert:true,class:"centrate"},
                {proper:"mayorValor",head:"Valor mayor",convert:true,class:"centrate"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de grupos de seleccion inactivados";
                texto_btn_crear = "Ver grupos de seleccion activos";
            }
            break;
        case "listas_precios":
            titulo_heading = "Listado de Listas de Precios";
            icon_miga = "i-stack-spades";
            texto_btn_crear = "Crear una lista de precios";
            texto_btn_des = "Ver listas de precios inactivas";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_listas_precios",head:"",class:"hidden tabla_de_listas_precios"},
                {proper:"nombre",head:"Nombre"},
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de listas de precios inactivas";
                texto_btn_crear = "Ver listas de precios activas";
            }
            break;
        case "clases":
            titulo_heading = "Listado de Clases";
            icon_miga = "i-stack-music";
            texto_btn_crear = "Crear una lista de clases";
            texto_btn_des = "Ver listas de clases inactivas";
            data_des="";
            data_act="";
            columnas=[
                {proper:"tabla_de_clases",head:"",class:"hidden tabla_de_clases"},
                {proper:"nombre",head:"Nombre"}
            ];
            if(existWithVal(des)){
                titulo_heading = "Listado de clases inactivas";
                texto_btn_crear = "Ver clases activas";
            }
            break;
    }

    var template = Handlebars.compile($("#miga_m").html());
    $("#breadcrumb").html(template({icon_miga: icon_miga, section_m:section_m}));
    var template = Handlebars.compile($("#section_m").html());
    $("#principal").html(template(
        {titulo_heading: titulo_heading,
            texto_btn_crear: texto_btn_crear,
            texto_btn_des: texto_btn_des,
            clases_btn_des: clases_btn_des,
            section_d:section,
            clases_btn_crear:clases_btn_crear,
            icon_btn_crear:icon_btn_crear
        }));

    columnas.push({head:'Acciones',embed:more_buttons+'<input type="button" value="Ver datos" class="btn btn-primary '+clases_btn_ver+'"   data-sec="'+section+'" />' +
        '<input type="button" value="'+texto_btn_desactivar+'" class="btn  '+clases_btn_desactivar+'"  data-sec="'+section+'" data-des="'+data_des+'" data-act="'+data_act+'" />'});

    creator_tables(data,columnas,$("#principal").find(".panel-body"));

    if(!($(".data_table th").size()>0)){
        $(".data_table thead tr").append("<th></th>");
        $(".data_table tbody").append("<tr><td class='centrate'>No hay resultados</td></tr>");
    }
    $(".data_table").DataTable();


    if(custombottons==undefined){
        $(".action").click(function(){eventos_de_lista($(this))});
    }else if(custombottons=="articulos_eventos_de_lista"){
        $(".action").click(function (){eventoslistaArticulo($(this),id)});
    }else if(custombottons=="grupoSeleccion_eventos_de_lista"){
        $(".action").click(function (){eventoslistaGrupo($(this),id)});
    }else{
        $(".action").click(function(){eventos_de_listaPersonalizados($(this),id)});
    }

    $(".openPuntos").click(function(){
        showTable({tabla: "puntos", tipo: "view", returnControl: $(this),button:"Cerrar",name:"PuntosContieneArticulo"});
    });

    $(".openPuntosCliente").click(function(){
        var id=$(this).attr("id");
        window.clienteDePunto=id;
        window.clienteDePuntoNombre=$(this).closest("tr").find(".nombre").html();
        Backbone.history.navigate("#lista/clientes/puntos/"+id, { trigger: true });
    });
    //esconder_botones();
    permisos_section();
}

function esconder_botones(){
    if(!(window.usuario.get("id")=="1")){
        $(".tabla_de_clientes").closest(".row").find(".btn-primary,.btn-danger,.abrir,.desactivados").addClass("hidden");
        $(".tabla_de_puntos").closest(".row").find(".btn-danger,.abrir,.desactivados").addClass("hidden");
    }
}


function permisos_section(){
    var section=window.inSection;
    (section=="clientes")?section="Clientes":t='';
    (section=="puntos")?section="Puntos":t='';
    (section=="marcas")?section="Marcas":t='';
    (section=="grupos")?section="Grupos corpotativos":t='';
    (section=="roles_cliente")?section="Roles":t='';
    (section=="usuarios")?section="Usuarios":t='';
    (section=="articulos")?section="Articulos":t='';
    (section=="selecciones")?section="Grupos de seleccion":t='';
    (section=="listas_precios")?section="Listas de precios":t='';
    (section=="clases")?section="Clases":t='';
    window.use_section=section;
    /*
    console.log("++++++++++++++++++++++++++++++++++");
    console.log("Puede ver datos de "+section);
    console.log(window.usuario.permisos);
    console.log(window.usuario.permisos.indexOf("Puede ver datos de "+section));
    console.log("++++++++++++++++++++++++++++++++++");
*/
    (window.usuario.permisos.indexOf("Puede activar "+section)==-1)?$(".activar,.Activar").addClass("hidden"):$(".activar,.Activar").removeClass("hidden");
    (window.usuario.permisos.indexOf("Puede desactivar "+section)==-1)?$(".desactivar").addClass("hidden"):$(".desactivar").removeClass("hidden");
    (window.usuario.permisos.indexOf("Puede ver y editar de "+section)==-1)?$(".ver_datos").addClass("hidden"):$(".ver_datos").removeClass("hidden");


    if(window.inSection=="clientes"){
        (window.usuario.permisos.indexOf("Puede listar Puntos")==-1)?$(".openPuntosCliente").addClass("hidden"):$(".openPuntosCliente").removeClass("hidden");
        if(window.usuario.get("idCliente")!=1){
            $(".abrir").addClass("hidden");
            $(".ver_datos").addClass("hidden");
            $(".desactivados").addClass("hidden");
        }

    }else if(window.inSection=="articulos"){
        (window.usuario.permisos.indexOf("Puede listar Puntos")==-1)?$(".openPuntos").addClass("hidden"):$(".openPuntosCliente").removeClass("hidden");
    }else if(window.inSection=="puntos"){
         if(window.usuario.get("idCliente")!=1){
            $(".abrir").addClass("hidden");
             $(".desactivados").addClass("hidden");
        }
    }else if(window.inSection=="grupos"){
         if(window.usuario.get("idCliente")!=1){
            $(".abrir").addClass("hidden");
            $(".ver_datos").addClass("hidden");
             $(".desactivados").addClass("hidden");
        }
    }else if(window.inSection=="marcas"){
         if(window.usuario.get("idCliente")!=1){
            $(".abrir").addClass("hidden");
            $(".ver_datos").addClass("hidden");
             $(".desactivados").addClass("hidden");
        }
    }

}

function esconder_menu_lateral(){
            (window.usuario.permisos.indexOf("Puede listar Clientes")==-1)?$(".lista_cliente_act").addClass("hidden"):$(".lista_cliente_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Grupos corpotativos")==-1)?$(".lista_grupo_act").addClass("hidden"):$(".lista_grupo_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Roles")==-1)?$(".lista_rolCliente_act").addClass("hidden"):$(".lista_rolCliente_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Marcas")==-1)?$(".lista_marca_act").addClass("hidden"):$(".lista_marca_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Usuarios")==-1)?$(".lista_usuario_act").addClass("hidden"):$(".lista_usuario_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Articulos")==-1)?$(".lista_articulo_act").addClass("hidden"):$(".lista_articulo_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Grupos de seleccion")==-1)?$(".lista_seleccion_act").addClass("hidden"):$(".lista_seleccion_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Listas de Precios")==-1)?$(".lista_listas_precios_act").addClass("hidden"):$(".lista_listas_precios_act").removeClass("hidden");
            (window.usuario.permisos.indexOf("Puede listar Clases")==-1)?$(".lista_clases_act").addClass("hidden"):$(".lista_clases_act").removeClass("hidden");
}


function permisos_cliente(){
    //(window.usuario.permisos.indexOf("Puede editar Clientes")==-1)?$(".lista_cliente_act").addClass("hidden"):$(".lista_cliente_act").removeClass("hidden");



}

function permisos_puntos(){

}

function permisos_roles(){

}

function permisos_grupo_co(){

}

function permisos_articulos(){

}

function permisos_grupos(){

}
