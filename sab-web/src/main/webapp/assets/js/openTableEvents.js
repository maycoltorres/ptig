function openTableEvents(section, tabla, action, id, actionIO) {
    window.actionGlobal = actionIO;

    switch (section) {
        case "cliente":
            $("#clientes_rs").limita({validaCon: "letras,numeros,correo", otros: " ,.-_"});
            $("#clientes_nit").limita({validaCon: "numeros", otros: "."});
            $("#clientes_telefono").limita({validaCon: "numeros"});
            $("#clientes_extension").limita({validaCon: "numeros"});
            $("#clientes_dc").limita({validaCon: "numeros"});
            $("#clientes_duenon").limita({validaCon: "letras", otros: " "});
            $("#clientes_celular").limita({validaCon: "numeros"});
            $('#clientes_correo').limita({validaCon: "letras,numeros,correo"});
            $('#clientes_nr').limita({validaCon: "letras", otros: " "});
            $('#clientes_dr').limita({validaCon: "numeros"});
            $('#clientes_cd').limita({validaCon: "letras,numeros,correo"});
            $('#clientes_tp').limita({validaCon: "numeros"});
            $('#clientes_direccion').limita({validaCon: "letras,numeros", otros: " -_,."});
            $(".charge_select").change(function select_charge() {
                if (!existWithVal($(this).find(":selected").attr("value"))) {
                    return;
                }
                if (this.id == "clientes_pais") {
                    $("#clientes_departamento").prop("disabled", true).parent().addClass("loading_control").children().children().remove();
                    $("#clientes_municipio").prop("disabled", true).parent().addClass("loading_control").children().children().remove();
                    tabla.departamentos(
                        {
                            success: function (data) {
                                fillSelect(data, $("#clientes_departamento"), "nombre");
                                $("#clientes_municipio").prop("disabled", false).parent().removeClass("loading_control");
                            }
                        }, "", $(this).find(":selected").attr("value"));
                } else if (this.id == "clientes_departamento") {
                    $("#clientes_municipio").prop("disabled", true).parent().addClass("loading_control").children().children().remove();
                    tabla.municipios(
                        {
                            success: function (data) {
                                fillSelect(data, $("#clientes_municipio"), "nombre");
                            }
                        }, "", $(this).find(":selected").attr("value"));
                }
            });

            $("#clientes_nit").mask('000.000.000.000.000.000.000', {reverse: true});
            $("#clientes_direccion").off("click").on("click", function (prueba) {
                creatorFloating("Ingrese la direccion", "Guardar", $("#dialog-direction").html(), "form", function (prueba) {
                    var flo = $(".floating_form.clon_floating");

                    flo.find("#type_dir").valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });
                    flo.find("#type_dir_2").valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });
                    flo.find('.cardinal:eq(0)').valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });
                    flo.find('#direccion_1').valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });

                    if (!flo.find(".campo_mal").size() < 1) {
                        $(".campo_mal:eq(0)").focus();
                        return false;
                    } else {
                        var dir = "";
                        var add = false;
                        $(prueba.target).parent().find("input,select,textarea").not("i,.btn").each(function () {
                            var val = $(this).val();

                            if (add && val.length > 0) {
                                dir = dir + " ";
                            } else {
                                add = true;
                            }

                            if (val.length > 0) {
                                dir = dir + $(this).val();
                            }
                        });
                        $("#clientes_direccion").val(dir);
                        close_floating();
                    }
                })
            });

            $("#cancel").off("click").on("click", function () {
                Backbone.history.navigate("#clientes", {trigger: true});
            });

            if (actionIO == "ver") {
                showTable({tabla: "cliente_regimen", modo: "tabla", tipo: "save"});
                cargarConfiguracion();
                showTable({tabla: "cliente_canal", modo: "tabla", tipo: "save"});
                showTable({tabla: "nueva_tabla_de_canales", modo: "tabla", tipo: "save", all: false});
            }

            $("#cliente_regimenTable").off("click").on("click", function () {
                showTable({tabla: "cliente_regimen", modo: "form", tipo: "floating"});
            });

            $("#cliente_canalTable").off("click").on("click", function () {
                showTable({tabla: "cliente_canal", modo: "form", tipo: "floating"});
            });

            $("#canales_config_cliente").off("click").on("click", function () {
                showTable({
                    tabla: "nueva_tabla_de_canales",
                    modo: "tabla",
                    type: "table",
                    tipo: "select_multiple",
                    all: true
                });
            });


            break;
        case "punto":

            $('#clientes_direccion').limita({validaCon: "letras,numeros", otros: " ,.-_"});
            $('#telefonoPunto').limita({validaCon: "numeros"});
            $('#extensionPunto').limita({validaCon: "numeros"});
            $('#telefono2Punto').limita({validaCon: "numeros"});
            $('#extension2Punto').limita({validaCon: "numeros"});
            $('#centro_costo_idPunto').limita({validaCon: "numeros"});
            $('#tipo_negocio_idPunto').find("option[value='" + tabla.attributes.tipo_negocio_id + "']").prop("selected", true);

            $("#cancel").off("click").on("click", function () {
                Backbone.history.navigate("#clientes", {trigger: true});
            });

            $("#clientes_direccion").off("click").on("click", function (prueba) {

                creatorFloating("Ingrese la direccion", "Guardar", $("#dialog-direction").html(), "form", function (prueba) {
                    var flo = $(".floating_form.clon_floating");

                    flo.find("#type_dir").valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });
                    flo.find("#type_dir_2").valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });
                    flo.find('.cardinal:eq(0)').valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });
                    flo.find('#direccion_1').valida({
                        mostrarLongitud: false,
                        mostrarMensaje: false,
                        mensajeParticular: " "
                    });

                    if (!flo.find(".campo_mal").size() < 1) {
                        $(".campo_mal:eq(0)").focus();
                        return false;
                    } else {
                        var dir = "";
                        var add = false;
                        $(prueba.target).parent().find("input,select,textarea").not("i,.btn").each(function () {
                            var val = $(this).val();

                            if (add && val.length > 0) {
                                dir = dir + " ";
                            } else {
                                add = true;
                            }

                            if (val.length > 0) {
                                dir = dir + $(this).val();
                            }
                        });
                        $("#clientes_direccion").val(dir);
                        close_floating();
                    }
                })
            });

            $(".charge_select").change(function select_charge() {
                if (!existWithVal($(this).find(":selected").attr("value"))) {
                    return;
                }
                if (this.id == "pais") {
                    $("#departamento").prop("disabled", true).parent().addClass("loading_control").children().children().remove();
                    $("#municipio").prop("disabled", true).parent().addClass("loading_control").children().children().remove();
                    tabla.departamentos(
                        {
                            success: function (data) {
                                fillSelect(data, $("#departamento"), "nombre");
                                $("#municipio").prop("disabled", false).parent().removeClass("loading_control");
                                if (!(window.usuario.get("id") == "1")) {
                                    seguridad_puntos_deshabilitar_campos();
                                }
                            }
                        }, "", $(this).find(":selected").attr("value"));
                } else if (this.id == "departamento") {
                    $("#municipio").prop("disabled", true).parent().addClass("loading_control").children().children().remove();
                    tabla.municipios(
                        {
                            success: function (data) {
                                fillSelect(data, $("#municipio"), "nombre");
                                if (!(window.usuario.get("id") == "1")) {
                                    seguridad_puntos_deshabilitar_campos();
                                }
                            }
                        }, "", $(this).find(":selected").attr("value"));
                }
            });

            $("#puntos_permisos").off("click").on("click", function () {
                showTable({
                    tabla: "punto_permisos",
                    tipo: "select_multiple",
                    returnControl: "",
                    type: "table",
                    id: window.punto.id,
                    allPer: true
                });
            });

            $("#puntos_franquicias").off("click").on("click", function () {
                showTable({
                    tabla: "punto_franquicias",
                    modo: "table",
                    tipo: "select_multiple",
                    returnControl: "",
                    type: "table",
                    id: window.punto.id,
                    all: true
                });
            });

            $("#puntos_bodegas").off("click").on("click", function () {
                showTable({tabla: "punto_bodegas", modo: "form", tipo: "floating", all: true, custom: true});
            });

            if (actionIO == "ver") {
                showTable({tabla: "punto_permisos", modo: "tabla", tipo: "save", id: window.articulo.id});
                showTable({tabla: "punto_franquicias", modo: "tabla", tipo: "save", id: window.articulo.id});
                showTable({tabla: "punto_bodegas", modo: "tabla", tipo: "save", id: window.articulo.id, custom: true});
            }

            $("#bodega_des").off("click").on("click", function () {
                $(this).addClass("hidden");
                $("#bodega_act").removeClass("hidden");
                $("#bodega_crear").addClass("hidden");

                showTable({
                    tabla: "punto_bodegas",
                    modo: "tabla",
                    tipo: "save",
                    id: window.articulo.id,
                    custom: true,
                    des: true
                });
            });

            $("#bodega_act").off("click").on("click", function () {
                $(this).addClass("hidden");
                $("#bodega_crear").removeClass("hidden");
                $("#bodega_des").removeClass("hidden");
                showTable({tabla: "punto_bodegas", modo: "tabla", tipo: "save", id: window.articulo.id, custom: true});
            });

            if (!(window.usuario.get("id") == "1")) {
                seguridad_puntos_deshabilitar_campos();
            }
            break;

        case "marca":
            showChargeStep();
            if (window["showMarcasClientesTable"] == undefined) {
                closeChargeStep();
                Backbone.history.navigate("#lista/marcas/", {trigger: true});
                closeChargeStep();
                return;
            }

            clienteId = window.marca.get("idCliente");

            $.ajax({
                url: "./rest/cliente/",
                success: function (data) {
                    window.clientes.set(data);
                    if (!(clienteId == undefined) && clienteId > 0) {
                        setClient();
                    }

                    $("#marca_clientes_button").off("click").on("click", function () {
                        showTable({
                            tabla: "marca_clientes",
                            modo: "tabla",
                            type: "table",
                            tipo: "select_one",
                            all: true
                        });
                    });

                    if (actionIO == "ver") {
                        showTable({tabla: "marca_clientes", modo: "tabla", tipo: "save", id: window.marca.id});
                    }
                },
                error: function () {
                    showMessage("error", "No fue posible consultar los clientes compruebe su conexion");
                    Backbone.history.navigate("#lista/marcas/", {trigger: true});
                    closeChargeStep();

                }
            });


            $("#btn-guardar").off("click").on("click", function () {
                crearMarcas(action);
            });

            break;
        case "grupo":
            if (window["showGrupoClientesTable"] == undefined) {
                Backbone.history.navigate("#lista/grupos/", {trigger: true});
                return;
            }

            $("#grupo_clientes_button").off("click").on("click", function () {
                //showTable({tabla:"grupo_clientes",modo:"tabla",type:"table",tipo:"select_multiple",all:true});
                showTable({tabla: "grupo_clientes", modo: "tabla", type: "table", tipo: "select_multiple", all: true});
            });

            if (actionIO == "ver") {
                showTable({tabla: "grupo_clientes", modo: "tabla", tipo: "save"});
            }

            $("#btn-guardar").off("click").on("click", function () {
                crearGrupo(action);
            });

            break;


        case "usuario":
            window.rolesCliente = [];
            if (window["showUsuariosClientesTable"] == undefined) {
                Backbone.history.navigate("#lista/usuarios/", {trigger: true});
                return;
            }

            $("#usuario_nombre").limita({validaCon: "letras,numeros", otros: " ._-#"});
            $("#usuario_correo").limita({validaCon: "letras,numeros,correo"});


            $("#usuario_cliente_button").off("click").on("click", function () {
                showTable({tabla: "usuario_clientes", type: "table", modo: "table", tipo: "select_one", all: true});
            });

            $("#usuario_roles_button").off("click").on("click", function () {
                showTable({tabla: "usuario_roles", type: "table", modo: "table", tipo: "select_multiple", all: true});
            });

            $("#usuario_grupo_button").off("click").on("click", function () {
                showTable({tabla: "usuario_acceso_grupo", modo: "tabla", type: "table", tipo: "select_one", all: true});
            });

            $("#usuario_punto_button").off("click").on("click", function () {
                showTable({tabla: "usuario_acceso_punto", modo: "tabla", type: "table", tipo: "select_one", all: true});
            });

            if (actionIO == "ver") {

                if (!isNaN(window.usuarioC.get("idCliente")) && window.usuarioC.get("idCliente") > 0) {
                    $.ajax({
                        type: "GET",
                        url: "./rest/cliente/" + window.usuarioC.get("idCliente"),
                        success: function (data) {

                            if (window.usuarioCliente == undefined) {
                                window.usuarioCliente = {};
                            }
                            window.usuarioCliente.nombre = data.nombre;
                            window.usuarioCliente.id = data.id;
                            showTable({tabla: "usuario_clientes", modo: "tabla", tipo: "save"});
                            showTable({tabla: "usuario_roles_button", modo: "tabla", tipo: "save"});
                        },
                        error: function (response, xhr, other) {
                            errorConnetion("Ha ocurrido un error de conexion", function () {
                                showMessage("error", "No fue posible consultar");
                            })
                        }
                    });
                }

                $.ajax({
                    type: "GET",
                    url: "./rest/rolcliente/rolusuario/"  + window.usuarioC.get("id") + "/",
                    success: function (data) {

                        if (window.rolesCliente == undefined) {
                            window.rolesCliente = [];
                        }

                        window.rolesCliente = data;

                        showTable({tabla: "usuario_roles", modo: "tabla", tipo: "save"});
                    },
                    error: function (response, xhr, other) {
                        errorConnetion("Ha ocurrido un error de conexion", function () {
                            showMessage("error", "No fue posible consultar");
                        })
                    }
                });


                if (!isNaN(window.usuarioC.get("id_punto")) && window.usuarioC.get("id_punto") > 0) {
                    $.ajax({
                        type: "GET",
                        url: "./rest/punto/" + window.usuarioC.get("id_punto"),
                        success: function (data) {

                            window.usuarioPunto.push({nombre: data.nombre, id: data.id});
                            showTable({tabla: "usuario_acceso_punto", modo: "tabla", tipo: "save"});
                        },
                        error: function (response, xhr, other) {
                            errorConnetion("Ha ocurrido un error de conexion", function () {
                                showMessage("error", "No fue posible consultar");
                            })
                        }
                    });
                }

                if (!isNaN(window.usuarioC.get("id_grupo")) && window.usuarioC.get("id_grupo") > 0) {
                    $.ajax({
                        type: "GET",
                        url: "./rest/grupo/" + window.usuarioC.get("id_grupo"),
                        success: function (data) {

                            if (window.usuarioGrupo == undefined) {
                                window.usuarioGrupo = {};
                            }
                            window.usuarioGrupo.nombre = data.nombre;
                            window.usuarioGrupo.id = data.id;
                            showTable({tabla: "usuario_acceso_grupo", modo: "tabla", tipo: "save"});
                        },
                        error: function (response, xhr, other) {
                            errorConnetion("Ha ocurrido un error de conexion", function () {
                                showMessage("error", "No fue posible consultar");
                            })
                        }
                    });
                }

            } else {
                $("#usuario_contra").val("");
                $("#usuario_contra2").val("");
            }

            break;

        case "rol":

            break;
        case "rol_cliente":

            $("#roles_cliente_nombre").limita({validaCon: "letras,numeros", otros: " "});

            $("#rolCliente_button").off("click").on("click", function () {
                showTable({tabla: "permisos", type: "table", modo: "table", tipo: "select_multiple", all: true});
            });

            $("#btn-guardar").click(function () {
                crearRoles(action);
            });

            if (action == "ver") {
                $.ajax({
                    type: "GET",
                    url: "./rest/rolcliente/" + window.rol_cliente.get("id"),
                    success: function (data) {
                        window.rolPermisos = data;
                        showTable({tabla: "permisos", modo: "tabla", tipo: "save"});
                    },
                    error: function (response, xhr, other) {
                        verifyConnection("Ha ocurrido un error de conexion", function () {
                            showMessage("error", "No fue posible consultar");
                        });
                    }
                });
            } else {
                window.rolPermisos = [];
            }
            break;

        case "articulo":

            if (actionIO == "ver") {
                cargaCompleto();
            }

            cargarModuloBasico(id, action);
            break;
        case "seleccion":
            var incremento=tabla.get("incrementoPrecio");
            
            if(incremento){
                $("#valor_de_grupo").removeClass("hidden");
                $("#valor_de_grupo").closest(".form-group").removeClass("hidden");
            }
            
            if(tabla.get("tipoGrupo")=="1"){
            	$('#sel_abierta').attr('checked', true);
            }else{
            	$('#sel_cerrada').attr('checked', true);
            }
            
            
            if(tabla.get("caracteristicaPrecio")=="1"){
            	$('#inc_articulo').attr('checked', true);
            }else if (tabla.get("caracteristicaPrecio")=="2") {
            	$('#inc_grupo').attr('checked', true);
            } else if (tabla.get("caracteristicaPrecio")=="3") {
            	$('#inc_mayor_val').attr('checked', true);
            } else {
            	$('#inc_no_aplica').attr('checked', true);
            }	

            $("#seleccion_button").off("click").on("click", function () {
                showTable({tabla: "selecciones", modo: "form", tipo: "floating"});
            });

            if (actionIO == "ver") {
                showTable({tabla: "selecciones", modo: "tabla", tipo: "save"});
            } else {
                $(".centrator_radios_child3").find("input").prop('checked', false);
            }

            $("#btn-guardar").off("click").on("click", function () {
                crearGrupoSeleccion(actionIO);
            });

            $("#inc_articulo, #inc_grupo, #inc_mayor_val, #inc_no_aplica").change(function(){
            	if($("input[name=precio_seleccion]:checked").val()=="2"){
            		$("#valor_de_grupo").removeClass("hidden");
                    $("#valor_de_grupo").closest(".form-group").removeClass("hidden");
                }else{
                	$("#valor_de_grupo").val("");
                    $("#valor_de_grupo").addClass("hidden");
                    $("#valor_de_grupo").closest(".form-group").addClass("hidden");
                }
            	
            	if($("input[name=precio_seleccion]:checked").val() !== "1"){
            		$.grep(window.seleccionC, function (sel) {
            			sel.incrementoPrecio =  null;
                    });
            		$("#seleccion_tableCross td.incremento").html("");
            	}
            });
            break;
       case "lista_precios":

            $("#btn-guardar").click(function () {
            	window.lista_precios.set("nombre", $("#lista_precios_nombre").val());
            	window.lista_precios.save({},{
            		success: function (model, response) {
            			showMessage("good", "La lista de precios ha sido guardada");
                        Backbone.history.navigate("#lista/listas_precios/", { trigger: true });
                    },
                    error: function (response, xhr, other) {
                    	showMessage("error", "No fue posible guardar la lista");
                    	console.log(response);
                    }
            	});
            });

            break;
       case "clase":
    	   $("#grupos_button").off("click").on("click", function () {
               showTable({tabla: "clase_grupos", modo: "form", tipo: "floating"});
    	   });
    	   
           $("#btn-guardar").click(function () {
        	   	var self = this;
        	    $("#clase_nombre").valida({longitud:{minimo:3,maximo:100},mostrarLongitud:true,mostrarMensaje:true});
        	    
        	    if(!$("#clase_section .campo_mal").length<1){
        	        $(".campo_mal:eq(0)").focus();
        	        return false;
        	    }
	        	window.clase.set("nombre", $("#clase_nombre").val());
	           	window.clase.set("venta", $("#check_venta").is(':checked'));
	           	window.clase.set("inventario", $("#check_inventario").is(':checked'));
	           	window.clase.save({},{
           		success: function (model, response) {
           			   showMessage("good", "La clase ha sido guardada");
                       Backbone.history.navigate("#lista/clases/", { trigger: true });
                   },
                   error: function (response, xhr, other) {
                	   	showMessage("error", "No fue posible guardar la clase");
		               	console.log(response);
                   }
	           	});
           });

           if (action == "ver") {
        	   showTable({tabla: "clase_grupos", modo: "tabla", tipo: "save"});
           } 
           
           break;
    }
}

function esconder_acceso() {
    if (!(window.usuario.get("id") == "1")) {
        $("#usuario_grupo_button").closest(".form-group").addClass("hidden");
        $("#usuario_grupo_tableCross").closest(".form-group").addClass("hidden");
    }
}

function mostrar_nueva_tabla_de_canales(ops) {
    if (ops.all) {
        ops.url = "./rest/common/canales/";
    } else {
        ops.url = "./rest/cliente/" + window.idClienteEditar + "/canales/";
    }
    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "centrate nombre"},
    ];
    ops.urlDelete = "#Eliminar";
    ops.html = ops.returnControl;
    ops.titulo = "Canales";
    ops.destino = $("#cliente_canales_config_tableCross");
    ops.notDelete=true;
    ops.notEdit = true;
    ops.returnControl = false;

    ops.prevDelete = function (me) {

    };
    ops.submit = function () {
        var flo = $(".clon_floating");
        sel=extraerSeleccionados(flo);
        var data=[];
        $.each(sel,function(index,value){
            data.push({id:value.id});
        });

        var t = $.ajax({
            url: "./rest/cliente/"+window.idClienteEditar+"/canales/",
            contentType:"application/json;charset=UTF-8",
            data:JSON.stringify(data),
            type: "POST",
            success: function (data) {
                showTable({tabla: "nueva_tabla_de_canales", modo: "tabla", tipo: "save"});
            },
            error: function () {
                verifyConnection("Ha ocurrido un error de conexion", function () {
                    showMessage("error", "No fue posible eliminar el registro");
                });
            }
        });

        close_floating();
    };
    ops.floating_created = function (floating) {
    };
    ops.asignar = function (data) {
        if (!ops.all) {
            window.cliente.cliente_canal_lista = data;
            window.cliente.cliente_canal_lista._canales = [];
            $.each(window.cliente.cliente_canal_lista, function (index, value) {
                window.cliente.cliente_canal_lista._canales.push({
                    id: value.id,
                    canalId: value.id,
                    canalNombre: JSON.search(window.catalogo_canalesCliente, '//*[id=' + value.id + ']')[0].nombre
                });
            });
        }
    }

    ops.deleteFunction = function (me) {
        //console.log(me);
        //console.log(this);
        /*
        var t = $.ajax({
            url: "./rest/cliente/" + window.clienteActual + "/canales/" + me,
            type: "DELETE",
            success: function (data) {
                showTable({tabla: "nueva_tabla_de_canales", modo: "tabla", tipo: "save"});
            },
            error: function () {
                verifyConnection("Ha ocurrido un error de conexion", function () {
                    showMessage("error", "No fue posible eliminar el registro");
                });
            }
        });
        */
    };

    ops.tableCreated = function (table, datatable, clon) {
        if ($(".clon_floating").find(".id").length > 0) {
            if (clon.find(".id").length > 0) {
                var table = clon.find("table").DataTable();
                $.each(window.cliente.cliente_canal_lista, function (index, value) {
                    clon.find(".id:contains('" + value.id + "')").parent().find(".check_me").find("input").prop("checked",true);
                });
            }
        }
    };
    return ops;
}


function mostrar_grupos(ops) {

   ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "Nombre", class: "centrate nombre"},
        {proper: "descripcion", head: "Descrippcion", class: "centrate descripcion"}
    ];
    ops.urlDelete = "#Eliminar";
    ops.html = ops.returnControl;
    ops.titulo = "Grupos";
    ops.destino = $("#grupos_tableCross");
    ops.notDelete=true;
    ops.notEdit = true;
    ops.returnControl = false;

    ops.prevDelete = function (me) {

    };
    ops.submit = function () {
        var flo = $(".clon_floating");
        sel=extraerSeleccionados(flo);
        var data=[];
        $.each(sel,function(index,value){
            data.push({id:value.id});
        });

        close_floating();
    };
    ops.floating_created = function (floating) {
    };
    
    ops.asignar = function (data) {
        if (ops.all == undefined) {
            window.cliente.cliente_canal_lista = data;
            window.cliente.cliente_canal_lista._canales = [];
            $.each(window.cliente.cliente_canal_lista, function (index, value) {
                window.cliente.cliente_canal_lista._canales.push({
                    id: value.id,
                    canalId: value.id,
                    canalNombre: JSON.search(window.catalogo_canalesCliente, '//*[id=' + value.id + ']')[0].nombre
                });
            });
        }
    }

    ops.deleteFunction = function (me) {
     };

    ops.tableCreated = function (table, datatable, clon) {
        if ($(".clon_floating").find(".id").length > 0) {
            if (clon.find(".id").length > 0) {
                var table = clon.find("table").DataTable();
                $.each(window.cliente.cliente_canal_lista, function (index, value) {
                    clon.find(".id:contains('" + value.id + "')").parent().find(".check_me").find("input").prop("checked",true);
                });
            }
        }
    };
    return ops;
}

function cargarConfiguracion() {
    var t = $.ajax({
        url: "./rest/cliente/" + window.idClienteEditar + "/configuracion/",
        type: "GET",
        success: function (data) {
            $("#check_receta_punto").prop("checked", data.recetaDiferencialXPunto);
            $("#check_receta_canal").prop("checked", data.recetaDiferencialXCanal);
            $("#check_empaque_punto").prop("checked", data.empaqueDiferencialXPunto);
            $("#check_empaque_canal").prop("checked", data.empaqueDiferencialXCanal);
            $("#imp_eq_ch").prop("checked", data.unicoImpuestoXCanales);
        },
        error: function () {
            verifyConnection("Ha ocurrido un error de conexion", function () {
                showMessage("error", "No fue posible consultar la configuracion");
            });
        }
    });
}
