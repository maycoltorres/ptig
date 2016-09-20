var validate_step=function(){return true;};
window.globalAction=false;
window.globalModule=false;

function openTable(tabla,section,data,action,urlId) {

    window.globalAction=action;
    window.globalModule=section;
    var template_html;
    var actionIO=action;
    var vars= {};

    var submit=function(){};
    var id="";

    switch (section) {
        case "cliente":
        /*
                variables globales que usa:
                    TODAS

                variables internas que usa:
        */

            if(window.clientes==undefined||window.clientes.length<1&&action=="ver"){
                Backbone.history.navigate('#lista/clientes/', { trigger: true });
                return false;
            }
            
            window.clienteActual = window.usuario.get(("idCliente"));
            window.idClienteEditar = urlId;
            
            window.cliente.clear().set(window.cliente.defaults);
            window.cliente.set(data);
            window.cliente.regimenes=[];
            window.cliente.cliente_canal=[];
            window.cliente.cliente_canal_lista=[];
            window.cliente.cliente_impuestos=[];
            window.cliente.recienCreado=undefined;
            window.articulo.puntos=[];

            submit=function(){
                if(validaTablasCliente()==true){
                	actualizarConfiguracion();
                    window.cliente.recienCreado = undefined;
                    Backbone.history.navigate("#lista/clientes/puntos/"+window.idClienteEditar, { trigger: true });
                }
            }
            
            template_html=$("#clientes_section").html();
            validate_step=function(step){
                var ac=actionIO;
                if($(".actions.form-actions.full").find(".next").hasClass("continue")){
                    $(".actions.form-actions.full").find(".next").removeClass("continue");
                    return true;
                }
                switch(step){
                    case 0:
                        showChargeStep();
                        crearCliente(ac);
                        return false;
                        break;
                    case 1:
                        return false;
                        break;
                }
                return false;
            }

            if(action=="ver"){
                actionm="Ver datos del cliente "+data.nombre;
                id=tabla.id;
                tabla.municipios({
                    success:function(data_1){
                        tabla.departamentos(
                            {
                                success:function(data_2){
                                    var paisId=data_2.pais_id;
                                    tabla.paises(
                                        {
                                            success:function(data_3){
                                                //console.log(data_3);
                                                fillSelect(data_3,$("#clientes_pais"),"nombre");
                                                $("#opt_"+paisId).prop("selected",true);
                                                //console.log(paisId);
                                                tabla.departamentos({
                                                    success:function(data_3){
                                                        //$("#clientes_departamento").prop("disabled",false).parent().removeClass("loading_control");
                                                        //$("#clientes_municipio").prop("disabled",false).parent().removeClass("loading_control");
                                                        var id=$("#clientes_departamento").find(":selected").val();
                                                        fillSelect(data_3,$("#clientes_departamento"),"nombre");

                                                        $("#clientes_departamento").find("option[value='"+id+"']").prop("selected",true);
                                                        tabla.municipios({
                                                            success:function(data_1){
                                                                var id=$("#clientes_municipio").find(":selected").val();
                                                                fillSelect(data_1,$("#clientes_municipio"),"nombre");
                                                                $("#clientes_municipio").find("option[value='"+id+"']").prop("selected",true);
                                                            }
                                                        },undefined,id);
                                                    }
                                                },undefined,paisId);
                                                //console.log($("#clientes_municipio").html());

                                            }
                                        });
                                    fillSelect(data_2,$("#clientes_departamento"),"nombre");
                                    $("#clientes_departamento").find("option").not(".empty").prop("selected","selected");

                                }
                            },
                            data_1.departamento_id);
                        fillSelect(data_1,$("#clientes_municipio"),"nombre");
                        $("#clientes_municipio").find("option").not(".empty").prop("selected","selected");
                    }},data.municipio);
            }else{
                actionm="Crear un nuevo cliente";
                tabla.paises({
                    success:function(data_3){
                        $("#clientes_departamento").prop("disabled",false).parent().removeClass("loading_control");
                        $("#clientes_municipio").prop("disabled",false).parent().removeClass("loading_control");
                        fillSelect(data_3,$("#clientes_pais"),"nombre");
                    }
                });
            }

            vars={
                cliente:data,
                accion:actionm
            };
            $.ajax({
                url:"",
                success:function(data){

                },
                error:function(x,h,r){

                }
            });
        break;
        case "punto":
            window.punto.clear().set(window.punto.defaults);
            window.punto.set(data);
            window.punto.set({id:window.clienteActual});
            window.punto.punto_franquicias={};
            window.punto.punto_permisos={};
            window.punto.punto_franquicias={};
            if(window.clienteDePunto==undefined){
                Backbone.history.navigate("#lista/clientes/", { trigger: true });
                return;
            }
            submit=function(){
                window.puntoRecienCreado=undefined;
                Backbone.history.navigate("#lista/clientes/puntos/"+window.clienteDePunto, { trigger: true });
            }
            validate_step=function(step){
                var ac=actionIO;
                if($(".actions.form-actions.full").find(".next").hasClass("continue")){
                    $(".actions.form-actions.full").find(".next").removeClass("continue");
                    return true;

                    //ASD344--___998877656677s
                }
                switch(step){
                    case 0:
                        showChargeStep();
                        crearPunto(ac);
                        return false;
                        break;
                    case 1:
                        validaPermisos();
                        //console.log(window.punto.get("autAnularVerificacion"));
                        if(window.punto.get("autAnularVerificacion")>0||window.punto.get("autAnularVerificacion")){
                            $("#lista_de_bodegas_embebida,#punto_bodegas_label").removeClass("hidden");
                        }else{
                            $("#lista_de_bodegas_embebida,#punto_bodegas_label").addClass("hidden");
                        }

                        return false;
                        break;
                }
                return false;
            }

            template_html=$("#puntos_section").html();
            window.gama.mapeo= {
                autAnularVerificacion: "Maneja bodegas",//
                claveanularVerifi: "Requerir clave para unilar verificacion",
                inclivaPventa: "Incluir IVA en precio de Venta",
                impticketAutoSrv: "Imprimir tiquete de pedido autoservicio",
                controlconspalabra: "Identificar el control de consumo con la palabra",
                impremotacpfactura: "Generar impresion remota de copia de la Factura",
                fvmenseros: "Permitir varas formas de venta meseros",
                factSinDocVerif: "Facturar sin Documento de Verificacion",
                factFresumido: "Facturar en Formato Resumido",
                denoatendidopor: "Denominar persona que atiende mesa",
                capCodAutoserv: "Capturar codigo autoservicio",
                capCodmesero: "Capturar codigo de mesero",
                capNpersonasAt: "Capturar Numero de personas atendidas",
                imprRemotas: "Permitir impresion Remmota",
                claveanular: "Solicitar clave para anular",
                cierreocultocaja: "Realizar cierre oculto caja",
                opLicNred: "Permitir varas formas de venta meseros",
                manNlicencias: "Manejar Varias Licencias en el Punto",
                factNpunto: "Permitir facturar en el Punto",
                contXdenominacion: "Contar dinero por Denominacion",
                autoUsuEliminar: "Autorizar usuario que elimina",
                apoyalicrec: "Permitir apoyo de licencias en Red",
                almacenarNpunto: "Permitir almacenar en el Punto"
            }

            var msg="Ciente: "+clienteDePuntoNombre+", ";
            if(clienteDePuntoNombre==undefined){
                msg="";
            }


            if(action=="ver"){

                actionm=msg+"ver datos del punto "+data.nombre;
                id=tabla.id;

                tabla.municipios({
                    success:function(data_1){
                        tabla.departamentos(
                            {
                                success:function(data_2){
                                    var paisId=data_2.pais_id;
                                    tabla.paises(
                                        {
                                            success:function(data_3){
                                                //console.log(data_3);
                                                fillSelect(data_3,$("#pais"),"nombre");
                                                $("#opt_"+paisId).prop("selected","selected");
                                                tabla.departamentos({
                                                    success:function(data_3){
                                                        //$("#clientes_departamento").prop("disabled",false).parent().removeClass("loading_control");
                                                        //$("#clientes_municipio").prop("disabled",false).parent().removeClass("loading_control");
                                                        var id=$("#departamento").find(":selected").val();
                                                        fillSelect(data_3,$("#departamento"),"nombre");
                                                        if(!(window.usuario.get("id")=="1")){
                                                            seguridad_puntos_deshabilitar_campos();
                                                        }

                                                        $("#departamento").find("option[value='"+id+"']").prop("selected",true);
                                                        tabla.municipios({
                                                            success:function(data_1){
                                                                var id=$("#municipio").find(":selected").val();
                                                                fillSelect(data_1,$("#municipio"),"nombre");
                                                                $("#municipio").find("option[value='"+id+"']").prop("selected",true);
                                                                if(!(window.usuario.get("id")=="1")){
                                                                    seguridad_puntos_deshabilitar_campos();
                                                                }
                                                            }
                                                        },undefined,id);
                                                    }
                                                },undefined,paisId);

                                            }
                                        });
                                    fillSelect(data_2,$("#departamento"),"nombre");
                                    $("#departamento").find("option").not(".empty").prop("selected","selected");
                                }
                            },
                            data_1.departamento_id);
                        fillSelect(data_1,$("#municipio"),"nombre");
                        $("#municipio").find("option").not(".empty").prop("selected","selected");
                    }},data.municipio_id);
            }else{
                actionm=msg+"crear un nuevo punto";
                tabla.paises({
                    success:function(data_3){
                        $("#clientes_departamento").prop("disabled",false).parent().removeClass("loading_control");
                        $("#clientes_municipio").prop("disabled",false).parent().removeClass("loading_control");
                        fillSelect(data_3,$("#pais"),"nombre");
                    }
                });
            }
            window.punto.set(data);

            vars={
                punto:data,
                accion:actionm
            };
        break;
        case "articulo":

            if(window.articulos.models.length<1&&action=="ver"){
                Backbone.history.navigate("#lista/articulos/", { trigger: true });
                return;
            }

            showChargeStep();
            window.articulo.clear().set(window.articulo.defaults);
            window.articulo.set("puntos", []);
            window.articulo.set(data);
            
            window.canalesImpuestos = [];
            window.regimenes = [];
            window.bodegasArticulo = [];
            window.articuloYaCreado=undefined;
            
            template_html=$("#articulos_section").html();
            if(action=="ver") {
                id=tabla.id;
                var action2 = "Ver el articulo " + data.nombre;
            }else{
                closeChargeStep();
                var action2="Crear un nuevo articulo";
                var id=null;
                window.articuloYaCreado=undefined;
            }

            var inventario, receta, venta;

            vars={
                articulo:data,
                action:action2,
                accion:action2,
                venta:window.articulo.ventaEnt,
                //inventario:window.articulo.inventarioEnt,
                receta:receta
            };
            submit=function(action){
                if($("#basico").hasClass("active")){
                    $(".actions.form-actions.full").removeClass("hidden");
                    $("#basico_nombre").valida({longitud:{minimo:2,maximo:100},mostrarLongitud:true,mostrarMensaje:true});

                    if(!$("#check_seleccion").is(":checked")&&!$("#check_inventario").is(":checked")&&!$("#check_receta").is(":checked")&&!$("#check_venta").is(":checked")){
                        showMessage("warning","El articulo debe tener al menos una caracteristica");
                        return;
                    }
                    if($("#basico").find(".campo_mal").length<1) {
                        guardarArticulo(id,action,true);
                    }else{
                        return;
                    }
                }
                if($("#step_inventario").hasClass("active")){
                	
                    $("#clase").valida();
                    $("#grupo").valida();
                    $("#unidad_k").valida();

                    if($("#unidad_a").find(":selected").attr("value")=="-2"&&!$("#unidad_a").hasClass(".configNotShow")){
                        var val=$("#unidad_o").val();
                        //console.log($("#unidad_o").parent().parent());
                        if(!(val.length>0)&&!$("#unidad_o").closest(".form-group").hasClass(".hidden")){
                            $("#unidad_o").campoMal({mensajeParticular:"Debe escribir una unidad alternativa",mostrarLongitud:false});
                            $("#unidad_o").focus();

                            return false;
                        }else{
                            var unidades=window.unidadesEstandar.concat(window.unidadesAlternas);
                            if($.grep(unidades, function(e){ return e.nombre.toLowerCase()==val.toLowerCase();}).length>0){
                                showMessage("error","Ya existe un nombre con esa unidad");
                                $("#unidad_o").focus();
                                return;
                            }
                        }
                    }

                    $("#unidad_o").valida({longitud:{minimo:3,maximo:50},mostrarLongitud:true,mostrarMensaje:true});
                    $("#unidad_alias").valida({longitud:{minimo:3,maximo:50},mostrarLongitud:true,mostrarMensaje:true});


                    if($("#unidad_c_tableCross").find("tbody").find("td.idSelect").length<1){
                        showMessage("warning","Debe tener al menos una unidad de compra");
                        return;
                    }
                    if($("#step_inventario").find(".campo_mal").not(".hidden").length<1){
                        validaInventario(id,action,"data","prueba","redirect");
                    }else{
                        return;
                    }

                }
                if($("#step_venta").hasClass("active")){


                    $("#venta_clase").valida();
                    $("#venta_grupo").valida();

                    if($("#unidad_p_tableCross").find("tbody").find("td.nombreUnidad").not(".empty").length<1){
                        showMessage("error","Debe configurar minimo 1 unidad de venta");
                        return;
                    }
                    if($("#step_venta").find(".campo_mal").length<1) {
                        validaVenta(id,action,"redirect");
                    }else{
                        return;
                    }

                }
                if($("#step_receta").hasClass("active")){
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
                            return
                        }
                        validaReceta(id,action);
                    }else{
                        Backbone.history.navigate("#lista/articulos/", { trigger: true });
                    }
                }
                if($("#step_empaques").hasClass("active")){
                    //hacer click en el paso final

                }

            }

            validate_step=function(step){
                if($(".actions.form-actions.full").find(".next").hasClass("continue")){
                    $(".actions.form-actions.full").find(".next").removeClass("continue");
                    return true;
                }

                if($("#basico").hasClass("active")) {
                    showChargeStep();
                    validaBasico(id, action);
                }else if($("#step_inventario").hasClass("active")) {

                    if ($("#check_inventario").is(":checked")) {
                        validaInventario(id, action, data);
                    } else if ($("#check_venta").is(":checked")) {
                        validaVenta(id, action, data);
                    } else if ($("#check_receta").is(":checked")) {
                        validaReceta(id, action);//validaReceta(id,action,data);
                    }
                    //return false;
                }else if($("#step_venta").hasClass("active")) {
                    //console.debug();
                    if ($("#check_venta").is(":checked")) {
                        validaVenta(id, action, data);
                    } else if ($("#check_receta").is(":checked")) {
                        validaReceta(id, action);//validaReceta(id,action,data);
                    }
                    //return false;
                }else if($("#step_receta").hasClass("active")){
                        validaReceta(id,action);//validaReceta(id,action,data);
                        //return false;
                }else if($("#step_empaques").hasClass("active")){
                        validaEmpaque(id,action);
                }
                return false;
            }
            break;
        case "grupo":

            window.grupo.clear().set(window.grupo.defaults);
            window.grupo.set(data);

            template_html=$("#grupos_section").html();
            if(action=="ver") {
                id=tabla.id;
                actionm = "Ver el grupo " + data.nombre;
            }else{
                actionm="Crear un nuevo grupo";
            }
            vars={
                action:actionm,
                grupo:data
            };

            break;
        case "marca":
            window.marca.clear().set(window.marca.defaults);
            window.marca.set(data);
            //console.log(window.marca);

            template_html=$("#marcas_section").html();
            if(action=="ver") {
                id=tabla.id;
                actionm = "Ver la marca " + data.nombre;
            }else{
                actionm="Crear un nueva marca";
            }
            vars={
                action:actionm,
                marca:data
            };

            break;
        case "rol":


            break;
        case "rol_cliente":
            window.rol_cliente.clear().set(window.rol_cliente.defaults);
            window.rol_cliente.set(data);

            if(window.roles_cliente.models.length<1&&action=="ver"){
                Backbone.history.navigate("#lista/roles_cliente/", { trigger: true });
                return;
            }


            template_html=$("#roles_cliente_section").html();
            if(action=="ver") {
                id=tabla.id;
                actionm = "Ver el rol " + data.nombre;
            }else{
                actionm="Crear un nuevo rol";
            }
            vars={
                action:actionm,
                rol:data
            };

            break;
        case "usuario":
            window.usuarioPunto=[];
            window.usuarioCliente=[];
            window.usuarioGrupo=[];
            window.usuarioRecienCreado=undefined;
            window.usuarioC.clear().set(window.usuarioC.defaults);
            window.usuarioC.set(data);

            if(window.usuario.get("id")=="1"&&usuario.get("idCliente")=="1"){

            }

            template_html=$("#usuarios_section").html();


            submit=function(){
                crearUsuario(action,true);
            }

            validate_step=function(step){
                var ac=actionIO;
                if($(".actions.form-actions.full").find(".next").hasClass("continue")){
                    $(".actions.form-actions.full").find(".next").removeClass("continue");
                    return true;
                }
                switch(step){
                    case 0:
                        showChargeStep();
                        esconder_acceso();
                        crearUsuario(ac);
                        return false;
                    break;
                    case 1:
                        //alert("case1");
                        //showChargeStep();
                        //crearUsuario(ac);
                        //return false;
                        break;
                }
                return false;
            }
            if(action=="ver") {
                id=tabla.id;
                actionm = "Ver el usuario " + data.fullName;
            }else{
                actionm="Crear un nuevo usuario";
            }
            vars={
                action:actionm,
                usuario:data
            };
            break;
        case "seleccion":
            if(!window.selecciones.asignados==true){
                Backbone.history.navigate("#lista/selecciones/", { trigger: true });
                return;
            }
            window.seleccion.clear().set(window.seleccion.defaults);
            window.seleccion.set(data);
            window.seleccionC=[];
            template_html=$("#selecciones_section").html();
            if(action=="ver") {
                id=tabla.id;
                actionm = "Ver el grupo de seleccion " + data.nombre;

            }else{
                actionm="Crear un grupo de seleccion";
            }
            vars={
                action:actionm,
                seleccion:data
            };
            break;
        case "lista_precios":
            window.lista_precios.clear().set(window.lista_precios.defaults);
            window.lista_precios.set(data);
            window.listaPreciosC=[];
            template_html=$("#lista_precios_section").html();
            if(action=="ver") {
                id=tabla.id;
                actionm = "Ver la lista de precios " + data.nombre;

            }else{
                actionm="Crear una lista de precios";
            }
            vars={
                action:actionm,
                seleccion:data
            };
            break;
        case "clase":
            window.clase.clear().set(window.clase.defaults);
            window.clase.set(data);
            window.claseC=[];
            template_html=$("#clases_section").html();
            if(action=="ver") {
            	id=tabla.id;
                actionm = "Ver la lista de clases " + data.nombre;

            }else{
                actionm="Crear una lista de clases";
            }
            vars={
                action:actionm,
                clase:data
            };
        break;

    }
    var template = Handlebars.compile(template_html);
    $("#principal").html(template(vars));
    wizard(validate_step,submit,id);
    openTableEvents(section,tabla,action,id,actionIO);
}

function seguridad_cliente(){

}

function seguridad_punto(){


}

function seguridad_puntos_deshabilitar_campos(){
    $("#punto_section #nombrePunto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #clientes_direccion").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #telefonoPunto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #extensionPunto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #telefono2Punto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #extension2Punto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #centro_costo_idPunto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #tipo_negocio_idPunto").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #pais").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #departamento").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #municipio").prop("disabled",true).addClass("label_disabled");
    $("#punto_section #puntos_franquicias").addClass("hidden");
    $("#punto_section #franquicias_tableCross .minus_delete").addClass("hidden");


}

function seguridad_puntos_omitir_guardado(){
    $(".actions.form-actions.full").find(".next").addClass("continue").click();
    closeChargeStep();
}

function seguridad_rol(){

}

function seguridad_grupo(){

}

function seguridad_usuario(){

}

function seguridad_grupoSel(){

}

