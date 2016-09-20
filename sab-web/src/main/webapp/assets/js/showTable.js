function showTable(ops) {
    var defaults = {
        ids: null,
        type: "form",
        events: function () {
        },
        btn_text: "Establecer",
        returnControl: false,
        submit: function () {
        },
        floating_created: function () {
        },
        edit: function () {
        },
        remove: function () {
        },
        error: function () {
            verifyConnection(function () {
                showMessage("error", "No fue posible consultar la tabla")
            })
        },
        tableCreated: function (a, b, c) {
        },
        editMe: function () {
        },
        validateMe: function () {
            return {validate: true}
        }
    };

    var ops = $.extend({}, ops);
    ops.edit = function (row, id) {

        if (!(ops.validateMe == undefined)) {
            var result = ops.validateMe(ops);
            if (!result.validate) {
                showMessage("error", result.message);
                return;
            }
        }
        showTable({tabla: ops.tabla, modo: "form", tipo: "floating", fila: row, id: id});
    };

    ops.minus = function (funcion) {
        funcion();
    };

    switch (ops.tabla) {
        case "grupo_clientes":
        	showGrupoClientesTable(ops);
            break;

        case "permisos":
            showPermisosTable(ops);
            break;

        case "marca_clientes":
            showMarcasClientesTable(ops);
            break;
        case "usuario_clientes":
            showUsuariosClientesTable(ops);
            break;
        case "usuario_roles":
            showUsuariosRolesTable(ops);
            break;
        case "usuario_acceso_punto":
            showUsuarioAccesoPuntoTable(ops);
            break;
        case "usuario_acceso_grupo":
            showUsuarioAccesoGrupoTable(ops);
            break;

        case "receta_puntos":
            showRecetaPuntosTable(ops);
            break;

        case "receta_ingredientes":
            showRecetaIngredientesTable(ops);
            break;

        case "venta_grupoSeleccion":
            showVentaGrupoSeleccionTable(ops);
            break;

        case "bodegasArticulo":
            showBodegasArticuloTable(ops);
            break;

        case "receta_canales":
            showRecetaCanalesTable(ops);
            break;
        case "receta_importar":
            showImportarReceta(ops);
            break;
        case "nueva_tabla_de_canales":
            mostrar_nueva_tabla_de_canales(ops);
            break;
        case "tablaListadoEmpaques":
            tablaListadoEmpaques(ops);
            break;
        case "tablaElementosEmpaque":
            tablaElementosEmpaque(ops);
        break;
        case "tablaPuntosEmpaque":
            tablaPuntosEmpaque(ops);
        break;
        case "tablaCanalesEmpaque":
            tablaCanalesEmpaque(ops);
        break;


        case "max":
            ops.workWithObject = true;
            ops.notAjax = true;
            ops.workObject = window.maximosMinimos;
            ops.html = $("#container_min_max").html();
            ops.titulo = "Maximos y minimos";
            ops.destino = $("#maximos_tableCross");
            ops.cols = [
                {head: "Punto", proper: "nombrePunto", class: "nombrePunto"},
                {head: "PuntoId", proper: "idPunto", class: "hidden puntoId"},
                {head: "Minimos", proper: "minimo", "class": "centrate"},
                {head: "Maximos", proper: "maximo", "class": "centrate"}
            ];

            ops.validateMe = function (ops) {
                if ($("#unidad_k").find(":selected").val() < 1 || $("#grupo").find(":selected").val() < 1 || $("#clase").find(":selected").val() < 1) {
                    return {validate: false, message: "Elija una clase, un grupo y una unidad de Kardex"};
                }
                return {validate: true};
            };

            ops.deleteFunction = function (id, button) {
                var name = button.closest("tr").find(".nombrePunto").html();
                var c = 0;
                var t = null;
                $.grep(window.maximosMinimos, function (e) {
                    if (e != undefined && e.nombrePunto == name) {
                        t = c;
                    }
                    c++;
                });
                if (t != null) {
                    window.maximosMinimos.splice(t, 1);
                }
                showTable({
                    tabla: ops.tabla,
                    modo: "table",
                    tipo: "save",
                    id: ops.id,
                    inventarioId: window.articulo.get("inventarioEnt").id,
                    workWithObject: true
                });
            };

            ops.method = "GET";
            if (ops.modo == "tabla") {

            } else {
                ops.submit = function () {

                    var flo = $(".floating_form.clon_floating");
                    
                    if (existWithVal(flo.find("#puntos_min_max").data("ids"))) {
                        flo.find("#puntos_min_max").campoBien();
                        flo.find("#min_value").valida({
                            validaciones: "numeros",
                            longitud: {minimo: 1, maximo: 32},
                            mostrarLongitud: false,
                            mensajeParticular: "Ingrese un dato numerico"
                        });
                        flo.find("#max_value").valida({
                            validaciones: "numeros",
                            longitud: {minimo: 1, maximo: 32},
                            mostrarLongitud: false,
                            mensajeParticular: "Ingrese un dato numerico"
                        });

                        if (parseInt(flo.find("#min_value").val()) > parseInt(flo.find("#max_value").val())) {
                            flo.find("#min_value").campoMal({
                                mensajeParticular: "El limite menor no puede superar al mayor",
                                mostrarLongitud: false
                            });
                        }
                        
                        if (!ops.fila) {
                            var c = 0;
                            var t = null;
                            $.grep(window.maximosMinimos, function (e) {
                                if (e != undefined && e.nombrePunto == flo.find("#puntos_min_max").html()) {
                                    t = c;
                                }
                                c++;
                            });
                            if (t != null) {
                            	flo.find("#puntos_min_max").campoMal({
                                    mensajeParticular: "Ya ha configurado maximos y minimos en el punto",
                                    mostrarLongitud: false
                                });
                            }
                        }


                        ops.tableCreated = function () {
                        };

                        if (flo.find(".campo_mal").not(".hidden").length > 0) {
                            flo.find(".campo_mal").first().focus();

                            return;
                        }

                        var ids = flo.find("#puntos_min_max").data("ids");
                        var min = parseInt(flo.find("#min_value").val());
                        var max = parseInt(flo.find("#max_value").val());
                        var data = [];
                        var nombres = flo.find("#puntos_min_max").html().split(",");
                        if (!(ops.fila == undefined)) {
                            var c = 0;
                            var t = null;
                            $.grep(window.maximosMinimos, function (e) {
                                if (e != undefined && e.nombrePunto == flo.find("#puntos_min_max").html()) {
                                    t = c;
                                }
                                c++;
                            });
                            if (t != null) {
                            	window.maximosMinimos.splice(t, 1);
                            }
                        }
                        for (var i = 0; i < ids.length; i++) {
                            window.maximosMinimos.push({
                                nombrePunto: nombres[i],
                                idPunto: ids[i],
                                minimo: min,
                                maximo: max,
                                idInventario: window.articulo.get("inventarioEnt").id
                            });
                        }

                        $(".clon_floating").remove();

                        close_floating();
                        showTable({
                            tabla: "max",
                            modo: "tabla",
                            tipo: "save",
                            id: ops.id,
                            inventarioId: window.articulo.get("inventarioEnt").id,
                            workWithObject: true
                        });

                    } else {
                        flo.find("#puntos_min_max").campoMal({
                            mensajeParticular: "Debe seleccionar minimo 1 punto",
                            mostrarLongitud: false
                        });
                    }

                };

                ops.floating_created = function (floating) {
                    floating.find("#min_value").limita({validaCon: "numeros", otros: "_,#, ,-,."});
                    floating.find("#max_value").limita({validaCon: "numeros", otros: "_,#, ,-,."});

                    if (existWithVal(ops.fila)) {
                        if (ops.destino.find("tbody tr").length > 0) {
                            var zero = 0;
                            ops.destino.find("tbody tr").each(function () {
                                if (zero == 0) {
                                    floating.find("#puntos_min_max").data("id", $(this).find("i")[0].id);
                                    floating.find("#puntos_min_max").html($(this).find(".puntoId").html());
                                } else {

                                }
                            });
                        }
                    }

                    if (!(ops.fila == "")) {
                        floating.find("#puntos_min_max").data("ids", [ops.fila.find("td").eq(1).html()]);
                        floating.find("#puntos_min_max").html(ops.fila.find("td").eq(0).html());
                        floating.find("#min_value").val(ops.fila.find("td").eq(2).html());
                        floating.find("#max_value").val(ops.fila.find("td").eq(3).html());
                        floating.find("#puntos_min_max").addClass("label_disabled");

                    } else {
                        floating.find("#puntos_min_max").click(function () {
                            showTable({
                                tabla: "puntos",
                                tipo: "select_multiple",
                                tableContainer: ops.destino,
                                returnControl: $(this),
                                id: ops.id
                            });
                        });
                    }


                };
            }
            break;
        case "unidad_c":
        	ops.notEdit = true;
            ops.workWithObject = true;
            ops.notAjax = true;
            ops.workObject = window.unidadesCompra;
            ops.html = $("#container_unidad_compra").html();
            ops.titulo = "Unidad de compra";
            ops.destino = $("#unidad_c_tableCross");

            ops.cols = [
                {head: "id", proper: 'id', class: "hidden id"},
                {head: "idUnidad", proper: 'id', class: "hidden idSelect"},
                {head: "Unidad", proper: 'nombre', class: "unidad_table"},
                {head: "tipo", proper: 'tipo', class: "hidden type"},
                {head: "estandar", proper: 'estandar', class: "hidden es_estandar"},
                {
                    head: "Equivalencia con<br>" + $("#unidad_k option:selected").text(),
                    proper: 'equivalencia',
                    class: "centrate equivalencia"
                }
            ];

            ops.asignar = function (data) {
            	unidadesSeleccionadas = [];
            	$.each(data, function(key, value){
            		unidadesSeleccionadas.push({
            			"id" : value.id
            		});
            	});
                window.articulo.get("inventarioEnt").unidadesCompra = unidadesSeleccionadas;
            };

            ops.validateMe = function (ops) {
                if ($("#unidad_k").find(":selected").val() < 1 || $("#grupo").find(":selected").val() < 1 || $("#clase").find(":selected").val() < 1) {
                    return {validate: false, message: "Elija una clase, un grupo y una unidad de Kardex"};
                }
                return {validate: true};
            };

            ops.deleteFunction = function (id, button) {
                var name = button.closest("tr").find(".unidad_table").html();
                var c = 0;
                var t = null;
                $.grep(window.unidadesCompra, function (e) {
                    if (e != undefined && e.nombre == name) {
                        t = c;
                    }
                    c++;
                });
                if (t != null) {
                    window.unidadesCompra.splice(t, 1);
                }
                showTable({
                    tabla: ops.tabla,
                    modo: "table",
                    tipo: "save",
                    id: ops.id,
                    inventarioId: window.articulo.get("inventarioEnt").id,
                    workWithObject: true
                });
            };

            ops.method = "GET";
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table, datatable, clon) {

                }
            } else {
            	ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                    flo.find("#venta_equivalencia").val(flo.find("#venta_equivalencia").val());
                    flo.find("#venta_unidad_a").valida({mensajeParticular: "Selecciona una unidad"});
                    if (!flo.find("#unidad_a_nuevaUnidad").closest(".form-group").hasClass("hidden")) {
                        flo.find("#unidad_a_nuevaUnidad").valida({
                            mostrarLongitud: false,
                            validaciones: "numeros,letras",
                            adicionales: "_,#, ,-,.",
                            longitud: {minimo: 1, maximo: 50},
                            mensajeParticular: "Escriba un nombre"
                        });

                        if ($.grep(window.unidadesAlternas, function (e) {
                                return e.nombre.toLowerCase() == floating.find("#unidad_a_nuevaUnidad").val().toLowerCase();
                            }).length > 0) {
                            showMessage("error", "Ya existe un nombre con esa unidad");
                            return;
                        }
                    } else {
                        floating.find("#unidad_a_nuevaUnidad").val("");
                        floating.find("#unidad_a_nuevaUnidad").campoBien({resaltarCampo: false});
                    }
                    
                    flo.find("#venta_equivalencia").valida({
                        mostsrarLongitud: false,
                        validaciones: "numeros",
                        longitud: {minimo: 1, maximo: 32},
                        mensajeParticular: "Ingrese un dato numerico"
                    });
                    
                    if (round(flo.find("#venta_equivalencia").val()) < 0.1) {
                        flo.find("#venta_equivalencia").campoMal({
                            mensajeParticular: "Equivalencia incompatible",
                            mostrarLongitud: false
                        });
                    }
                    buscarEnUnidadesCompra = $.grep(window.unidadesCompra, function (e) {
                        if (e.id == flo.find("#venta_unidad_a").val()) {
                            return true;
                        }
                        return false;
                    });
                    if (buscarEnUnidadesCompra.length > 0) {
                    	showMessage("error", "Ya esta incluida esa unidad en la lista de unidades de compra para el articulo");
                        return;
                    }
                    
                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        flo.find(".campo_mal").first().focus();
                        return;
                    }

                    if (!(flo.find("#venta_equivalencia").closest(".form-group").hasClass("hidden")) && isNaN(parseFloat(flo.find("#venta_equivalencia").val()))) {
                        showMessage("error", "Formato de numero no permitido");
                        flo.find("#venta_equivalencia").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                        return;
                    }
                    
                    //Si es necesario, se crea la unidad
                    if(flo.find("#venta_unidad_a").val() == -2){
                    	var factor = parseFloat(flo.find("#venta_equivalencia").val().replace(',', '.'));
                    	var nuevaUnidad = {
                        	"nombre" : floating.find("#unidad_a_nuevaUnidad").val(),
                            "tipo" : "",
                            "estandar" : false,
                            "conversiones" : [{
                            	"idUnidadDestino" : $("#unidad_k").val(), 
                            	"factor" : factor,
                            	"idArticulo" : window.articulo.get("id"),
                            	}]
                        };
                        
                       	$.ajax({
                            url:"./rest/unidad/",
                            type:"POST",
                            contentType:"application/json",
                            async:false,
                            data:JSON.stringify(nuevaUnidad),
                            success:function(data){
                            	nuevaUnidad.id = data;
                            	window.unidadesAlternas.push(nuevaUnidad);
                            	window.conversiones.push({
                        			"idUnidadOrigen" : data,
                            		"idUnidadDestino" : parseInt($("#unidad_k").val()), 
                                	"factor" : factor, 
                                	"idArticulo" : window.articulo.get("id"),
                                });
                            	
                            	unidadSeleccionada = {
	                                "id": data,
	                                "id_unidad": data,
	                                "equivalencia": factor,
	                                "es_estandar": 0,
	                                "id_inventario": window.articulo.get("inventarioEnt").id,
	                                "nombre": floating.find("#unidad_a_nuevaUnidad").val(),
	                                "type": 1
                            	};               	
                            },
                            error:function(){
                                verifyConnection(showMessage("error","No se pudo guardar la  nueva unidad"));
                                return false;
                            }
                        });
                    }else {
                    	if(equivalenciaActualizada){
                    		idUnidadOrigen = parseInt(flo.find("#venta_unidad_a").val());
                    		idUnidadDestino = parseInt($("#unidad_k").val());
                    		factor = parseFloat(flo.find("#venta_equivalencia").val().replace(',', '.'));
                    		conv = $.grep(window.conversiones, function (conversion) {
                    	        if (conversion.idUnidadOrigen === idUnidadOrigen && conversion.idUnidadDestino === idUnidadDestino) {
                    	        	return true;
                    	        }
                    	        return false;
                    	    })[0];
                    		
                    		if(conv){
                    			conv.factor = factor;
                    			tipo = "PUT";
                    		}else{
                    			conv = {
                    				"idUnidadOrigen" : idUnidadOrigen,
                    				"idUnidadDestino" : idUnidadDestino,
                    				"factor" : factor,
                    				"idArticulo" : window.articulo.get("id")
                    			}
                    			tipo = "POST";
                    		}
                    		
                    		$.ajax({
                                url:"./rest/unidad/" + flo.find("#venta_unidad_a").val() + "/conversiones/",
                                type:tipo,
                                contentType:"application/json",
                                async:false,
                                data:JSON.stringify(conv),
                                success:function(data){
                                	if(!conv.id){
                                		conv.id = data;
                                		window.conversiones.push(conv);
                                	}
                                },
                                error:function(){
                                    verifyConnection(showMessage("error","No se pudo actualizar la unidad"));
                                    return false;
                                }
                            });
                    	}
                    	
                    	unidadSeleccionada = {
                            "id": flo.find("#venta_unidad_a").val(),
                            "id_unidad": flo.find("#venta_unidad_a").val(),
                            "equivalencia": factor,
                            "es_estandar": 1,
                            "id_inventario": window.articulo.get("inventarioEnt").id,
                            "nueva_unidad": null,
                            "nombre": flo.find("#venta_unidad_a").find(":Selected").html(),
                            "type": flo.find("#venta_unidad_a").find(":Selected").attr("class")
                        };
                    }
                    
                    window.unidadesCompra.push(unidadSeleccionada);
                    close_floating();
                    $(".clon_floating").remove();
                    showTable({
                        tabla: "unidad_c",
                        modo: "tabla",
                        tipo: "save",
                        id: ops.id,
                        inventarioId: window.articulo.get("inventarioEnt").id,
                        workWithObject: true
                    });
                };

                ops.floating_created = function (floating) {
                    var equivalencia = floating.find("#venta_equivalencia");
                    var select = floating.find("#venta_unidad_a");
                    var unidadKardex = $("#unidad_k");
                    equivalenciaActualizada = false;

                    function deleteSelectIdsOfTable(destino, select, idClass, type) {
                        var table = destino.find("table").DataTable();
                        var ids = table.$("td." + idClass);
                        if (ids.length > 0) {
                            if (ids.length == 1 && type == "edit") {
                                return;
                            }
                            ids.each(function () {
                                var type = $(this).siblings(".es_estandar");
                                if ($(type).html() == 0) {
                                    select.find("option").not("option[class~='es_estandar']").filter("option[value='" + $(this).html() + "']").remove();
                                } else {
                                    select.find("option").filter("option[value='" + $(this).html() + "']").filter("option[class~='es_estandar']").remove();
                                }
                            });
                        }
                    }

                    function setEqquiv(me) {
                        if (me.val() == unidadKardex.val()) {
                            equivalencia.val("");
                            equivalencia.val("1").closest(".form-group").addClass("hidden");
                        } else if(me.val() == -2){
                        	me.closest("form").find(".otro").removeClass("hidden");
                        	equivalencia.val("");
                        	equivalencia.closest(".form-group").removeClass("hidden");
                        }else{
                        	me.closest("form").find(".otro").addClass("hidden");
                        	unidadAlterna = findById( window.unidadesAlternas, me.val());
                        	factor = convertidor_de_unidades(me.val(), unidadKardex.val(), 1, window.articulo.get("id"));
                            equivalencia.val(factor); 
                        	
                            if(factor > 0 && unidadAlterna.estandar){
                            	equivalencia.closest(".form-group").addClass("hidden");
                            }else{
                            	equivalencia.closest(".form-group").removeClass("hidden");
                            }
                        }
                    }

                    equivalencia.closest(".form-group").find("label").append(" " + unidadKardex.find(":selected").text());
                    equivalencia.limita({
                        validaCon: "numeros",
                        otros: [44],
                        mensajeParticular: "Ingrese un dato numerico"
                    });
                    
                    select.change(function () {
                        var me = $(this);
                        setEqquiv(me);
                    });
                    
                    equivalencia.change(function(){
                    	equivalenciaActualizada = true;
                    });

                    fillSelect(window.unidadesAlternas, select, "nombre");
                    select.append('<option value=-2>Nueva unidad</option>');
                    if (!(ops.fila == "")) {
                        equivalencia.val(ops.fila.find(".equiv").html());
                        if (ops.fila.find(".es_estandar").html() == 1) {
                            equivalencia.prop("disabled", true);
                            select.find("option").filter("option[value='" + ops.fila.find(".idSelect").html() + "']").filter("option[class~='es_estandar']").prop("selected", true);

                        } else {
                            select.find("option").not("option[class~='es_estandar']").filter("option[value='" + ops.fila.find(".idSelect").html() + "']").prop("selected", true);
                        }
                        select.prop("disabled", true);
                    } 
                };
            }
            break;
        case "puntos":
            ops.destino = ops.returnControl;
            if (existWithVal(ops.name)) {
                if (ops.name == "PuntosContieneArticulo") {
                    ops.url = "./rest/articulo/" + ops.returnControl.attr("id")  + "/puntos/";
                }
            } else {
                ops.url = './rest/punto/?idCliente=' + usuario.get("idCliente");
            }
            ops.asignar = function (data) {
                //window.articulo.puntos = data;
            };
            ops.titulo = "Puntos";
            ops.type = "table";
            ops.submit = function (table, datatable) {
                close_floating();
            };
            ops.cols = [
                {head: "Punto", proper: "nombre"},
                {head: "id", proper: "id", class: "hidden"},
                {head: "puntoId", proper: "puntoId", class: "hidden puntoId"}
            ];

            ops.tableCreated = function (table, datatable, clon) {
                if (!(ops.tableContainer == undefined)) {
                    chargeIds(clon, ops.tableContainer, "puntoId");
                }

                if (!(ops.returnControl == false)) {
                    var ids = ops.returnControl.data().ids;
                    if (!(ids == null) && !(ids == undefined)) {
                        for (var i = 0; i < ids.length; i++) {
                            clon.find("input[id='" + ids[i] + "']").prop("checked", true).prop("disabled", false);
                        }
                    }

                    if (ops.editable == true) {
                        clon.find("input[id='" + ops.puntoId + "']").prop("disabled", false);
                        if (ids.length < 1) {
                            clon.find("input[id='" + ops.puntoId + "']").prop("checked", false);
                        }
                    }
                }
            };

            break;
        case "ventaPunto":
            ops.type = "table";
            ops.notEdit = true;
            
            ops.workWithObject = true;
            ops.notAjax = true;
            
            ops.titulo = "Este articulo estara en estos puntos";
            ops.destino = $("#venta_puntos_tableCross");
            ops.cols = [
                {head: "Punto", proper: "nombre"},
                {head: "Id", proper: "id", class: "hidden id"},
                {head: "puntoId", proper: "puntoId", class: "hidden puntoId"}
            ];

            if (ops.todosLosPuntos == true) {
            	ops.workObject = window.puntosCliente;
            } else {
            	ops.workObject = window.articulo.get("puntos");
            }
            
            ops.deleteFunction = function (id, row) {
            	ops.workObject.splice(row, 1);
                showTable({tabla: "ventaPunto", modo: "tabla", tipo: "save"});
            };
            ops.validateMe = function (ops) {

            };
            
            ops.asignar = function (data) {
                if (!(ops.todosLosPuntos)) {
                	//window.articulo.set({"puntos" : data});
                }
            };
            
            ops.tableCreated = function (table, datatable, clon) {
                if ($(".clon_floating").find(".id").length > 0) {
                    var flo = $(".clon_floating").last();
                    var table = flo.find("table").DataTable();
                    _puntos = window.articulo.get("puntos");
                    if (_puntos && _puntos.length > 0) {
                        $.each(_puntos, function (index, punto) {
                            id = flo.find("input[id='" + punto.id + "']");
                            if (id.length > 0) {
                                row = table.row(id.closest("tr"));
                                row.remove();
                                id.closest("tr").remove();
                            }
                        });
                    }
                    table.draw();
                }
            };

            if (ops.modo == "tabla") {
            	
            } else {
                ops.submit = function () {
                    guardarVentaPuntos();
                };
                ops.floating_created = function (floating) {

                };
            }
            break;
        case "canalesImpuestos":

            ops.html = $("#container_impuestos").html();
            ops.titulo = "Canales, Impuestos y tarifas";
            ops.destino = $("#cip_tableCross");
            ops.workWithObject = true;
            ops.workObject = window.canalesImpuestos;

            ops.url = "#";
            ops.urlDelete = "#";
            ops.deleteFunction = function (id, button) {
                var canal = button.closest("tr").find(".canalId").html();
                var impuesto = button.closest("tr").find(".impuestoId").html();
                var c = 0;
                var t = null;
                $.grep(window.canales, function (e) {
                    if (e != undefined && e.canalId == canal && e.impuestoId == impuesto) {
                        t = c;
                    }
                    c++;
                });
                if (t != null) {
                    window.canales.splice(t, 1);
                }
                showTable({
                    tabla: "canalesImpuestos",
                    modo: "tabla",
                    tipo: "save",
                    id: window.articulo.get("id"),
                    workWithObject: true
                });
            };
            ops.method = "GET";
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden id"},
                {proper: "nombreCanal", head: 'Canal', class: " nombre"},
                {proper: "idCanal", head: 'canalId', class: "hidden canalId"},
                {proper: "idImpuesto", head: 'impuestoId', class: "hidden impuestoId"},
                {proper: "nombreImpuesto", head: 'Impuesto', class: "impuesto centrate"},
                {proper: "nombreRegimen", head: 'Regimen', class: "regimen centrate"},
                {proper: "nombreTarifa", head: 'Tarifa', class: "centrate valor"},
                {proper: "idTarifa", head: 'tarifaId', class: "hidden tarifaId"}
            ];
            ops.asignar = function (data) {
//                window.articulo.get("ventaEnt").canalesImpuestos = canalesSeleccionados;
            };
            
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table) {
                }
            } else {
                ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                    if (!existWithVal(flo.find("#venta_canal").data("ids"))) {
                        flo.find("#venta_canal").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                    } else {
                        flo.find("#venta_canal").campoBien();
                    }
                    if (!existWithVal(flo.find("#venta_impuesto").data("ids"))) {
                        flo.find("#venta_impuesto").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                    } else {
                        flo.find("#venta_impuesto").campoBien();
                    }
                    if (!existWithVal(flo.find("#venta_tarifa").data("ids"))) {
                        flo.find("#venta_tarifa").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                    } else {
                        flo.find("#venta_tarifa").campoBien();
                    }
                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        return;
                    }

                    if (!(ops.fila == undefined)) {

                    } else {
                        var coincidencia = $.grep(window.canales,
                            function (e) {
                                return e.impuesto == flo.find("#venta_impuesto").html().split(",")[0] && e.canalId == flo.find("#venta_canal").data("ids")[0];
                            });

                        if (coincidencia.length > 0) {
                            showMessage("error", "Solo puede elegir un impuesto por canal, ese impuesto ya se ha configurado en ese canal");
                            flo.find("#venta_canal").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                            flo.find("#venta_impuesto").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                            return;
                        }
                    }

                    var id = null;
                    if (!(ops.fila == undefined)) {
                        id = ops.fila.find(".id").html();
                        var canal = flo.find("#venta_canal").data("ids")[0];
                        var impuesto = flo.find("#venta_impuesto").data("ids")[0];
                        var c = 0;
                        var t = null;
                        $.grep(window.canales, function (e) {
                            if (e != undefined && e.canalId == canal && e.impuestoId == impuesto) {
                                t = c;
                            }
                            c++;
                        });
                        if (t != null) {
                            window.canales.splice(t, 1);
                        }
                    }

                    var id_ = null;
                    if (!(id == null) && !(id == undefined) && !(id < 1) && !(id.length < 1) && !(id == "")) {
                        id_ = id;
                    }
                    
                    idCanal = flo.find("#venta_canal").data("ids")[0];
                    nombreCanal = flo.find("#venta_canal").html();
                    
                    idImpuesto = flo.find("#venta_impuesto").data("ids")[0];
                    nombreImpuesto = flo.find("#venta_impuesto").html();
                    
                    idTarifa = flo.find("#venta_tarifa").data("ids")[0];
                    nombreTarifa = flo.find("#venta_tarifa").html();
                    
                    var data = {
                    		"id" : null,
                			"idVenta" : window.articulo.get("ventaEnt").id,
                			"idCanal" : idCanal,
                			"idImpuesto" : idImpuesto,
                			"idRegimen" : null,
                			"idTarifa" : idTarifa,
                			"nombre": nombreCanal,
                			"impuesto" : nombreImpuesto,
                			"valor" : nombreTarifa
                    };

                    window.canalesImpuestos.push(data);
                    close_floating();
                    $(".clon_floating").remove();

                    showTable({
                        tabla: "canalesImpuestos",
                        modo: "tabla",
                        tipo: "save",
                        id: window.articulo.get("id"),
                        workWithObject: true
                    });

                };
                ops.floating_created = function (floating) {

                    var canal = floating.find("#venta_canal");
                    var impuesto = floating.find("#venta_impuesto");
                    var tarifa = floating.find("#venta_tarifa");
                    tarifa.click(function () {
                        if (impuesto.data("ids") == undefined || impuesto.data("ids").length < 1) {
                            showMessage("warning", "Primero debe elegir un impuesto");
                            return;
                        }
                        showTable({
                            tabla: "tarifa",
                            impuestoId: impuesto.data("ids")[0],
                            type: "table",
                            tipo: "select_one",
                            returnControl: $(this),
                            id: ops.id
                        });
                    });
                    impuesto.click(function () {
                        showTable({
                            tarifa: tarifa,
                            tabla: "impuesto",
                            type: "table",
                            tipo: "select_one",
                            returnControl: $(this),
                            id: ops.id
                        });
                    });
                    if (existWithVal(ops.fila)) {
                        canal.addClass("label_disabled");
                        canal.html(ops.fila.find(".nombre").html()).data("ids", [ops.fila.find(".canalId").html()]);
                        impuesto.html(ops.fila.find(".impuesto").html()).data("ids", [ops.fila.find(".impuestoId").html()]);
                        tarifa.html(ops.fila.find(".valor").html()).data("ids", [ops.fila.find(".tarifaId").html()]);
                    } else {
                        canal.click(function () {
                            showTable({
                                impuesto: impuesto,
                                tabla: "canal",
                                type: "table",
                                tipo: "select_one",
                                returnControl: $(this),
                                id: ops.id
                            });
                        });
                    }
                    if (!(ops.fila == undefined)) {
                        canal.off("click").addClass("label_disabled");
                        impuesto.off("click").addClass("label_disabled");
                    }
                };
            }
            break;

        case "canal":
            ops.workWithObject = true;
            ops.workObject = window.canales;

            ops.html = $("#container_unidad_compra").html();
            ops.titulo = "Canales de facturacion";
            ops.destino = $("#unidad_c_tableCross");
            
            ops.asignar = function (data) {
//                window.canal = data;
            };
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden"},
                {head: "Nombre", proper: 'nombre'}
            ];
            if (ops.modo == "tabla") {
                	ops.tableCreated = function (table) {
                }
            } else {
                ops.submit = function () {
                    close_floating();
                };
            }
            break;
        case "tarifa":
            ops.html = $("#container_unidad_compra").html();
            ops.titulo = "Canales";
            ops.destino = $("#unidad_c_tableCross");
            
            idImpuesto = $(".floating_form.clon_floating").find("#venta_impuesto").data("ids")[0];
            var imp = $.grep(window.impuestos, function (u) {
                return u.id == idImpuesto;
            })[0];
            
            ops.url = "./rest/common/tarifas/?idRegimen=" + imp.regimenes[0].id + "";
            ops.showResults = true;
            ops.zeroResults = "Hay 0 tarifas para el impuesto elegido";
            ops.method = "GET";
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden"},
                {head: "Tarifa", proper: 'valor', class: "centrate"}
            ];
            ops.asignar = function (data) {
                window.tarifa = data;
            };
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table) {
                    if ($("#unidad_k").find(":selected").val() == table.find("th.idSelect").html()) {
                        table.find(".unidad_table").addClass("hidden");
                        table.find(".otra_unidad_table").removeClass("hidden");
                        table.find(".equivalencia_table").removeClass("hidden");
                    }
                }
            } else {
                ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                    flo.find("#venta_unidad_a").valida({mensajeParticular: "Selecciona una unidad"});
                    if (!flo.find("#venta_unidad_nueva").closest(".form-group").hasClass("hidden")) {
                        flo.find("#venta_unidad_nueva").valida({
                            mostrarLongitud: false,
                            validaciones: "numeros,letras",
                            adicionales: "_,#, ,-,.",
                            longitud: {minimo: 1, maximo: 50},
                            mensajeParticular: "Escriba un nombre"
                        });
                    } else {
                        floating.find("#venta_unidad_nueva").val("");
                        floating.find("#venta_unidad_nueva").campoBien({resaltarCampo: false});
                    }
                    flo.find("#venta_equivalencia").valida({
                        mostrarLongitud: false,
                        validaciones: "numeros",
                        longitud: {minimo: 1, maximo: 32},
                        mensajeParticular: "Ingrese un dato numerico"
                    });

                    if (round(flo.find("#venta_equivalencia").val()) < 0.01) {
                        flo.find("#venta_equivalencia").campoMal({
                            mensajeParticular: "Equivalencia incompatible",
                            mostrarLongitud: false
                        });
                    }

                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        //showMessage("error","Llene los campos correctamente");
                        return;
                    }

                    save_floating(ops.url, flo.data("ids"), ["idU", "otro", "equivalencia"], [
                        flo.find("#venta_unidad_a").val(),
                        flo.find("#venta_unidad_nueva").val(),
                        flo.find("#venta_equivalencia").val()
                    ], function () {
                        showTable({tabla: "unidad_c", modo: "tabla", tipo: "save", id: ops.id});
                    });
                };

                ops.floating_created = function (floating) {
                    floating.find("#venta_unidad_nueva").limita({validaCon: "numeros,letras", otros: "_,#, ,-,."});
                    floating.find("#venta_equivalencia").limita({
                        validaCon: "numeros",
                        otros: ".",
                        mensajeParticular: "Ingrese un dato numerico"
                    });

                    if (!(ops.fila == "")) {
                        floating.data("ids", ops.fila.find("td").eq(0).html());
                        floating.find("#venta_unidad_a").find("option[value='" + ops.fila.find("td").eq(0).html() + "']");
                        floating.find("#venta_unidad_nueva").val(ops.fila.find("td").eq(1).html());
                        floating.find("#venta_equivalencia").val(ops.fila.find("td").eq(2).html());
                        if ($("#unidad_k").val() == ops.fila.find("td").eq(0).html()) {
                            floating.find("#venta_equivalencia").val("1").addClass("hidden");
                        }
                    }

                    floating.find("#venta_unidad_a").change(function () {
                    	if (this.val() > 0) {
                            floating.data("idU", this.val());
                        }
                        if (this.val() == $(this).find("#unidad_k").val()) {
                            floating.find("#venta_equivalencia").addClass("hidden");
                        }
                        if (this.val() == -2) {
                            floating.find("#venta_unidad_nueva").removeClass("hidden");
                            floating.find(".otro").removeClass("hidden");
                        } else {
                        	var factor = convertidor_de_unidades(this.val(), $(this).find("#unidad_k").val(), 1, window.articulo.get("id"));
                        	if(factor > 0){
                        		floating.find("#venta_equivalencia").addClass("hidden");
                        	}else{
                        		floating.find("#venta_equivalencia").removeClass("hidden");
                        	}
                        }
                    });
                };
            }
            break;
        case "impuesto":
            ops.workWithObject = true;
            ops.workObject = window.impuestos;
            ops.html = $("#container_unidad_compra").html();
            ops.titulo = "Impuestos";
            ops.destino = $("#unidad_c_tableCross");
            
            ops.asignar = function (data) {
                window.impuesto = data;
            };

            ops.saveSel = function () {
                if (!jQuery.isEmptyObject(ops.tarifa.data())) {
                    ops.tarifa.removeData().html("0 seleccionados");
                }
            };

            ops.method = "GET";
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden"},
                {head: "Nombre", proper: 'nombre'},
                {head: "Regimen", proper: 'impuestoNombre'},
                {head: "Regimen", proper: 'impuestoId', class: "hidden"}
            ];

            if (ops.modo == "tabla") {
                ops.tableCreated = function (table) {
                }
            } else {
                ops.submit = function () {
                    close_floating();
                };
            }
            break;

        case "unidadPrincipal":
            ops.workWithObject = true;
            ops.workObject = window.unidadesVenta;
            ops.html = $("#venta_form").html();
            ops.titulo = "Unidad Principal";
            ops.destino = $("#unidad_p_tableCross");
            ops.url = "#";
            ops.urlDelete = "#";
            var type_inventario = "";


            ops.deleteFunction = function (id, button) {
                var idLista = button.closest("tr").find(".idLista").html();
                var c = 0;
                var t = null;
                $.grep(window.unidadesVenta, function (e) {
                    if (e.idListaPrecios == idLista) {
                        t = c;
                    }
                    c++;
                });
                if (t != null) {
                	window.unidadesVenta.splice(t, 1);
                }
                showTable({tabla: "unidadPrincipal", modo: "tabla", tipo: "save", workWithObject: true});
                if (window.unidadesVenta.length < 1) {
                	window.unidadesAlternasVenta = [];
                    showTable({tabla: "unidadAlternativa", modo: "tabla", tipo: "save", workWithObject: true});
                }
            };

            ops.asignar = function (data) {

            };
            ops.method = "GET";

            var msg = "";
            var msg_c = "hidden";

            var e = validaEquivalencia();
            if (window.articulo.get("inventarioEnt")){
                var unidad_inv = findById(window.unidadesEstandar, window.articulo.get("inventarioEnt").idUnidadKardex);
                var msg = "Equivalencia con " + unidad_inv.nombre;
                var msg_c = "";
            }

            ops.cols = [
                {head: "idUnidad", proper: 'idUnidad', class: "hidden id"},
                {proper: "nombreUnidad", head: "Unidad", class: "unidad nombreUnidad"},
                {proper: "equivalencia", head: msg, class: "centrate equiv " + msg_c},
                {proper: "valor", head: "Valor de venta", class: "centrate valor"},
                {proper: "idListaPrecios", head: "idLista", class: "hidden idLista"},
                {proper: "nombreLista", head: "Lista de precios", class: "nombre listaNombre"},
                {proper: "tipo", head: "Tipo", class: "hidden type"}

            ];

            if (ops.modo == "tabla") {

            } else {
                ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                    flo.find("#venta_u").valida();
                    flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(",", '.'));

                    if (flo.find("#venta_u").find(":selected").val() > 0 && window.articulo.get("inventario") && window.articulo.get("inventarioEnt").idUnidadKardex > 0) {
                        var unidad_venta_id = parseInt(flo.find("#venta_u").val());
                        var unidad_inv = window.articulo.get("inventarioEnt").idUnidadKardex;
                        var nombreUnidadKardex = findById(window.unidadesEstandar, unidad_inv).nombre;

                        if (unidad_inv === unidad_venta_id) {
                            flo.find("#venta_eq").closest(".form-group").addClass("hidden");
                            flo.find("#venta_eq").val("1");
                            flo.find("#venta_eq").prop("disabled", true);
                        } else {
                        	factor = convertidor_de_unidades(unidad_inv, unidad_venta_id, 1, window.articulo.get("id"));
                        	if(factor === 0){
	                            flo.find("#venta_label_equiv").html("Equivalencia con 1 " + nombreUnidadKardex);
	                            flo.find("#venta_eq").prop("disabled", false);
	                            flo.find("#venta_eq").closest(".form-group").removeClass("hidden");
                        	}
                        }
                    }

                    if (!flo.find("#venta_eq").closest(".form-group").hasClass("hidden")) {
                        flo.find("#venta_eq").valida({
                            validaciones: "numeros",
                            mensajeParticular: "Digite un numero",
                            mostrarLongitud: false
                        });
                    } 

                    if (!flo.find("#venta_lista_p").prop("disabled") && !existWithVal(flo.find("#venta_lista_p").data("ids"))) {
                        flo.find("#venta_lista_p").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                    } else {
                        flo.find("#venta_lista_p").campoBien();
                    }

                    flo.find("#venta_valor").valida({
                        validaciones: "numeros",
                        mensajeParticular: "Digite un numero",
                        mostrarLongitud: false
                    });

                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(".", ","));
                        return false;
                    }

                    if (!(flo.find("#venta_eq").closest(".form-group").hasClass("hidden")) && isNaN(parseFloat(flo.find("#venta_eq").val()))) {
                        showMessage("error", "Formato de numero no permitido");
                        flo.find("#venta_eq").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                        flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(".", ","));
                        return;
                    }


                    var coincidencia = [];

                    var id = null;
                    var idListaPrecios;
                    if(ops.fila){
                    	idListaPrecios = parseInt(ops.fila.find(".idLista").html());
                    }else{
                    	idListaPrecios = parseInt(flo.find("#venta_lista_p").data("ids")[0]);
                    }
                    if (ops.fila) {
                        id = ops.fila.find(".id").html();
                        var c = 0;
                        var t = null;
                        $.grep(window.unidadesVenta, function (e) {
                            if (e.idListaPrecios == idListaPrecios) {
                                t = c;
                            }
                            c++;
                        });
                        if (t != null) {
                        	window.unidadesVenta.splice(t, 1);
                        }
                    } else {
                        coincidencia = $.grep(window.unidadesVenta, function (e) {
                            return e.idListaPrecios == idListaPrecios;
                        });

                        if (coincidencia.length > 0) {
                            showMessage("error", "Solo puede configurar un precio por unidad en cada Lista de precios");
                            flo.find("#venta_lista_p").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                            flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(".", ","));
                            return;
                        }
                    }
                    
                    //Si es necesario, se actualiza la conversion
                    if(!flo.find("#venta_eq").closest(".form-group").hasClass("hidden")){
	                    nuevaConversion = {
	                    	"idUnidadOrigen" : parseInt(flo.find("#venta_u").val()),
	                    	"idUnidadDestino" : unidad_inv,
	                    	"factor" : parseFloat(flo.find("#venta_eq").val().replace(".", ",")),
	                    	"idArticulo" : window.articulo.get("id")
	                    }
	                    guardarConversion(nuevaConversion);
                    }
                    
                    var data_ = {
                        id: flo.find("#venta_u").val(),
                        "idUnidad": flo.find("#venta_u").val(),
                        "nombreUnidad": flo.find("#venta_u").find(":selected").html(),
                        "nombreLista": flo.find("#venta_lista_p").html(),
                        "equivalencia": parseFloat(flo.find("#venta_eq").val().replace(",", ".")),
                        "valor": flo.find("#venta_valor").val(),
                        "margen": "",
                        "idListaPrecios": idListaPrecios,
                        "tipo": flo.find("#venta_u").find(":selected").attr("class").split(" ")[0]
                    };

                    window.unidadesVenta.push(data_);

                    close_floating();
                    $(".clon_floating").remove();

                    showTable({tabla: "unidadPrincipal", modo: "tabla", tipo: "save", workWithObject: true});
                    showTable({tabla: "unidadAlternativa", modo: "tabla", tipo: "save", workWithObject: true});
                };
            }

            ops.floating_created = function (floating) {
                var equiv = floating.find("#venta_eq");

                if (window.unidadesVenta.length > 0) {
                	floating.find("#venta_u").prop("disabled", true);
                	floating.find("#venta_u option[value=" + window.unidadesVenta[0].idUnidad + "]").prop("selected", "selected");
                	floating.find("#venta_eq").val(window.unidadesVenta[0].equivalencia);
                }

                equiv.limita({validaCon: "numeros", otros: [44]});
                equiv.closest(".form-group").addClass("hidden");
                floating.find("#venta_valor").limita({validaCon: "numeros", otros: ""});
                
                var table = ops.destino.find("table");

                if (ops.fila == undefined) {
                    floating.find("#venta_lista_p").click(function () {
                        showTable({
                            tabla: "listasDePrecios",
                            tipo: "one_multiple",
                            modo: "table",
                            type: "table",
                            returnControl: $(this),
                            id: ops.id
                        });
                    });
                } else {
                    floating.find("#venta_lista_p").html(window.articulo.get("ventaEnt").nombre).prop("disabled", true).addClass("label_disabled");

                }
                var texto_comp = "";
                if (window.articulo.get("inventarioEnt")) {
                    var unidad_inv = findById(window.unidadesEstandar, window.articulo.get("inventarioEnt").idUnidadKardex);
                    texto_comp = unidad_inv.nombre;

                    floating.find("#venta_label_equiv").html("Equivalencia con 1 " + texto_comp);

                    floating.find("#venta_u").change(function () {
                        if (this.value > 0 && window.articulo.get("inventarioEnt").idUnidadKardex > 0) {
                            var idUnidadVenta = parseInt(this.value);

                            if (parseInt(window.articulo.get("inventarioEnt").idUnidadKardex) === idUnidadVenta) {
                                floating.find("#venta_eq").closest(".form-group").addClass("hidden");
                                floating.find("#venta_eq").val("1");
                                floating.find("#venta_eq").prop("disabled", true);
                            } else {
                                var unidadVenta = findById(window.unidadesEstandar, idUnidadVenta);
                                factor = convertidor_de_unidades(idUnidadVenta, unidad_inv.id, 1, window.articulo.get("id"));

                                if (factor>0){
                                	floating.find("#venta_eq").val(factor);
                                	if(unidadVenta.estandar){
                                		floating.find("#venta_eq").closest(".form-group").addClass("hidden");
                                		floating.find("#venta_eq").prop("disabled", true);
                                	}else{
                                		floating.find("#venta_eq").closest(".form-group").removeClass("hidden");
                                		floating.find("#venta_eq").prop("disabled", false);
                                	}
                                } else{
                                	floating.find("#venta_label_equiv").html("Equivalencia con 1 " + unidadVenta.nombre);
                                    floating.find("#venta_eq").prop("disabled", false);
                                    floating.find("#venta_eq").val(0);
                                    floating.find("#venta_eq").closest(".form-group").removeClass("hidden");
                                }
                            }

                        }
                    });
                } else {
                    equiv.val("");
                    equiv.closest(".form-group").addClass("hidden");
                }

                if (!(ops.fila == "") && !(ops.fila == undefined)) {
                    floating.find("#venta_u").find("option[value='" + ops.fila.find(".unidadId").html() + "']").prop("disabled", true).prop("selected", true);
                    floating.find("#venta_eq").val(ops.fila.find(".equiv").html());

                    floating.find("#venta_lista_p").data("ids", ops.fila.find(".listaId").html());
                    floating.find("#venta_lista_p").html(ops.fila.find(".listaNombre").html());
                    floating.find("#venta_valor").val(ops.fila.find(".valor").html());

                    if (ops.fila.find(".equiv").html() != "" && parseFloat(ops.fila.find(".equiv").html().replace(",", ".")) > 0) {
                        equiv.closest(".form-group").removeClass("hidden");
                        equiv.val(ops.fila.find(".equiv").html());
                        equiv.prop("disabled", true);
                    } else {
                        equiv.closest(".form-group").addClass("hidden");
                        equiv.val("");
                        equiv.prop("disabled", true);

                    }

                } else {
                    floating.find("#venta_eq").closest(".form-group").addClass("hidden");
                    equiv.keyup(function () {
                        floating.find("#venta_label_equiv").html(equiv.val() + " " + floating.find("#venta_u").find(":selected").html() + " = " + " 1 " + texto_comp);
                    });
                }

                if (window.equivalencias != undefined && (window.equivalencias + "").length > 0) {
                    equiv.closest(".form-group").removeClass("hidden");
                    equiv.val(megaConsulta[13][0].equivalencia);
                    equiv.prop("disabled", true);
                } else {
                    equiv.closest(".form-group").addClass("hidden");
                    equiv.val("");
                    equiv.prop("disabled", true);

                }

                if (!(window.limitaPrincipal == undefined) && window.limitaPrincipal == true) {
                    floating.find("#venta_u").find("option").not("." + window.limitaA).remove();
                }

                if (window.articulo.seleccion == true) {
                    floating.find("#venta_u").find("option").not("option[value='" + 12 + "']").remove();
                }
            };
            break;

        case "listasDePrecios":
            //ops.html = $("#venta_form").html();
            ops.workWithObject = true;
            ops.workObject = window.listasDePrecios;
            ops.titulo = "Listas de precios";
            ops.destino = $("#unidad_p_tableCross");
            ops.url = "#";

            ops.asignar = function (data) {
                window.lista = data;
            };

            ops.method = "GET";
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden"},
                {proper: "nombre", head: "Nombre"}
            ];

            ops.tableCreated = function (table, datatable, clon) {
                if (!(ops.returnControl == false) && !(ops.returnControl == undefined)) {
                    var ids = ops.returnControl.data().ids;
                    if (!(ids == undefined)) {
                        for (var i = 0; i < ids.length; i++) {
                            clon.find("input[id='" + ids[i] + "']").prop("checked", true);
                        }
                    }
                }
            };

            if (ops.modo == "tabla") {

            } else {
                ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                };
            }
            break;

        case "unidadAlternativa":
            ops.workWithObject = true;
            ops.workObject = window.unidadesAlternasVenta;
            ops.html = $("#venta_form").html();
            ops.titulo = "Unidad Alternativa";
            ops.destino = $("#venta_u_a_tableCross");
            ops.url = "#";
            ops.urlDelete = '#';

            ops.deleteFunction = function (id, button) {
                var idLista = button.closest("tr").find(".idLista").html();
                var idUnidad = button.closest("tr").find(".idUnidad").html();
                var c = 0;
                var t = null;
                $.grep(window.unidadesAlternasVenta, function (e) {
                    if (e.idListaPrecios == idLista && e.idUnidad == idUnidad) {
                        t = c;
                    }
                    c++;
                });
                if (t != null) {
                	window.unidadesAlternasVenta.splice(t, 1);
                }
                showTable({tabla: "unidadAlternativa", modo: "tabla", tipo: "save", workWithObject: true});
            };
            ops.asignar = function (data) {
            };
            ops.method = "GET";
            
            var idUnidadVenta=0;
            var nombreUnidadVenta = "";
            $.grep(window.unidadesVenta, function(){
            	idUnidadVenta = this.id;
            });
            
            if(idUnidadVenta>0){
            	nombreUnidadVenta = findById(window.unidadesEstandar, idUnidadVenta).nombre;
            }
            
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden id"},
                {proper: "nombreUnidad", head: "Unidad", class: "unidad nombreUnidad"},
                {proper: "equivalencia",head: "Equivalencia con " + nombreUnidadVenta, class: "hidden",class: "centrate equiv"},
                {proper: "valor", head: "Valor de venta", class: "centrate valor"},
                {proper: "idUnidad", head: "idUnidad", class: "hidden idUnidad"},
                {proper: "idListaPrecios", head: "idLista", class: "hidden idLista"},
                {proper: "nombreLista", head: "Lista de precios", class: "nombre listaNombre"}
            ];

            if (ops.modo == "tabla") {

            } else {
                ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                    flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(",", '.'));

                    flo.find("#venta_u").valida();
                    flo.find("#venta_valor").valida({
                        validaciones: "numeros",
                        mensajeParticular: "Digite un numero",
                        mostrarLongitud: false
                    });

                    if (!flo.find("#venta_eq").closest(".form-group").hasClass("hidden")) {
                        flo.find("#venta_eq").valida({
                            validaciones: "numeros",
                            mensajeParticular: "Digite un numero",
                            mostrarLongitud: false
                        });
                    } else {
                        flo.find("#venta_eq").removeClass("hidden");
                    }

                    if (!flo.find("#venta_lista_p").prop("disabled") && !existWithVal(flo.find("#venta_lista_p").data("ids"))) {
                        flo.find("#venta_lista_p").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                    } else {
                        flo.find("#venta_lista_p").campoBien();
                    }

                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(".", ","));
                        return false;
                    }

                    if (!(flo.find("#venta_eq").closest(".form-group").hasClass("hidden")) && isNaN(parseFloat(flo.find("#venta_eq").val()))) {
                        showMessage("error", "Formato de numero no permitido");
                        flo.find("#venta_eq").campoMal({mostrarMensaje: false, mostrarLongitud: false});
                        flo.find("#venta_eq").val(flo.find("#venta_eq").val().replace(".", ","));
                        return;
                    }
                    
                    var idListaPrecios;
                    if(ops.fila){
                    	idListaPrecios = parseInt(ops.fila.find(".idLista").html());
                    }else{
                    	idListaPrecios = parseInt(flo.find("#venta_lista_p").data("ids")[0]);
                    }
                    
                    var idUnidad = parseInt(flo.find("#venta_u").val());
                    
                    if (!ops.fila) {
                    	var yaExiste = false;
                    	var idUnidad = parseInt(flo.find("#venta_u").val());
                        $.grep(window.unidadesAlternasVenta, function (e) {
                            if (e.idUnidad == idUnidad && e.idListaPrecios == idListaPrecios) {
                            	yaExiste = true;
                            	return;
                            }
                        });
                        if (yaExiste){
                        	showMessage("error", "Ya está asignada la unidad y lista de precios");
                        	return;
                        }
                    }

                    var id = null;

                    if (ops.fila) {
                        id = ops.fila.find(".id").html();
                        var c = 0;
                        var t = null;
                        
                        if (flo.find("#venta_otro").closest(".form-group").hasClass("hidden")) {
                            var nombre_unidad = flo.find("#venta_u").find(":selected").html();
                        } else {
                            var nombre_unidad = flo.find("#venta_otro").val();
                        }
                        $.grep(window.unidadesAlternasVenta, function (e) {
                            if (e.idUnidad == idUnidad && e.idListaPrecios == idListaPrecios) {
                                t = c;
                            }
                            c++;
                        });
                        if (t != null) {
                        	window.unidadesAlternasVenta.splice(t, 1);
                        }
                    } else {
                        if (flo.find("#venta_otro").closest(".form-group").hasClass("hidden")) {
                            var nombre_unidad = flo.find("#venta_u").find(":selected").html();
                        } else {
                            var nombre_unidad = flo.find("#venta_otro").val();
                        }
                    }
                    var eq = flo.find("#venta_eq").html();
                    if (eq == undefined || eq == '') {
                        eq == null
                    }
                    
                    if (flo.find("#venta_u").val() == "-2"){
                    	var nombreUnidad = flo.find("#venta_otro").val();
                    }else{
                    	var nombreUnidad = flo.find("#venta_u").find(":selected").html();
                    }
                    
                    //Si es necesario, se crea la unidad
                    idUnidad = parseInt(flo.find("#venta_u").val());
                    if(idUnidad == -2){
                    	var nuevaUnidad = {
                        	"nombre" : nombreUnidad,
                            "tipo" : "",
                            "estandar" : false,
                            "conversiones" : [{
                            	"idUnidadDestino" : window.unidadesVenta[0].id, 
                            	"factor" : eq, 
                            	"idArticulo" : window.articulo.get("id"),
                            	}]
                        };
                        
                       	$.ajax({
                            url:"./rest/unidad/",
                            type:"POST",
                            contentType:"application/json",
                            async:false,
                            data:JSON.stringify(nuevaUnidad),
                            success:function(data){
                            	nuevaUnidad.id = data;
                            	idUnidad = data;
                            	window.unidadesAlternas.push(nuevaUnidad);
                            	window.conversiones.push({
                        			"idUnidadOrigen" : data,
                            		"idUnidadDestino" : window.unidadesVenta[0].idUnidad, 
                                	"factor" : eq, 
                                	"idArticulo" : window.articulo.get("id"),
                                });
                            	
                            	unidadSeleccionada = {
	                                "id": data,
	                                "id_unidad": data,
	                                "equivalencia": eq,
	                                "es_estandar": 0,
	                                "id_inventario": window.articulo.get("inventarioEnt").id,
	                                "nombre": nombreUnidad,
	                                "type": 1
                            	};               	
                            },
                            error:function(){
                                verifyConnection(showMessage("error","No se pudo guardar la  nueva unidad"));
                                return false;
                            }
                        });
                    }else if(!flo.find("#venta_eq").prop("disabled")){
                    	nuevaConversion = {
	                    	"idUnidadOrigen" : parseInt(flo.find("#venta_u").val()),
	                    	"idUnidadDestino" : window.unidadesVenta[0].idUnidad,
	                    	"factor" : parseFloat(flo.find("#venta_eq").val().replace(".", ",")),
	                    	"idArticulo" : window.articulo.get("id")
	                    }
	                    guardarConversion(nuevaConversion);
                    }
                  
                    var data_ = {
                        "idUnidad": idUnidad,
                        "nombreUnidad": nombreUnidad,
                        "nombreLista": flo.find("#venta_lista_p").html(),
                        "equivalencia": eq,
                        "valor": flo.find("#venta_valor").val(),
                        "margen": "",
                        "idListaPrecios": idListaPrecios,
                        "equivalencia": flo.find("#venta_eq").val()
                    };

                    window.unidadesAlternasVenta.push(data_);
                    close_floating();
                    $(".clon_floating").remove();

                    showTable({tabla: "unidadAlternativa", modo: "tabla", tipo: "save", workWithObject: true});
                };
            }
            ops.floating_created = function (floating) {
                var unidadTipo = $("#unidad_p_tableCross").find("td.type").first().html();
                var equiv = floating.find("#venta_eq");
                var select = floating.find("#venta_u");
                var ventaP = floating.find("#venta_lista_p");
                var ventaValor = floating.find("#venta_valor");
                
                fillSelect(window.unidadesEstandar, select, "nombre");
                select.append('<option value=-2 class="">Nueva unidad</option>');
                select.find("option").find(".empty").prop("selected", true);
                
                floating.find("#venta_label_equiv").html("Equivalencia con " + window.unidadesVenta[0].nombreUnidad);

                floating.find("#venta_valor").limita({validaCon: "numeros", otros: ""});
                equiv.limita({validaCon: "numeros", otros: [44]});
                floating.find("#venta_otro").limita({validaCon: "letras,numeros", otros: "_,#, ,-,/,"});

                if (ops.fila) {
                	var idUnidadPrincipal = parseInt(window.unidadesVenta[0].idUnidad);
                	var unidad = findById(window.unidadesEstandar, ops.fila.find(".idUnidad").html());
                	
                	equiv.val(ops.fila.find(".equiv").html());
                	equiv.prop('disabled', !esConversionEditable(unidad.id, idUnidadPrincipal));
                	
                    select.find("option[value='" + ops.fila.find(".idUnidad").html() + "']").prop("selected", true);
                    select.prop('disabled', true);
                    
                    ventaP.html(ops.fila.find(".listaNombre").html());
                    ventaP.prop('disabled', true);
                    ventaP.addClass('label_disabled');
                    
                    ventaValor.val(ops.fila.find(".valor").html());
                } 
                
                if (!ventaP.hasClass('label_disabled')) {
                    ventaP.click(function () {
                        showTable({
                            tabla: "listasDePrecios",
                            tipo: "one_multiple",
                            modo: "table",
                            type: "table",
                            returnControl: $(this),
                            id: ops.id
                        });
                    });
                }

                select.change(function () {
                    var idUnidad = parseInt($(this).val());
                    var idUnidadPrincipal = parseInt(window.unidadesVenta[0].idUnidad);
                    	
                    if(idUnidad == idUnidadPrincipal){
                    	select.closest("form").find(".venta_otro").addClass("hidden");
                    	floating.find("#venta_eq").closest(".form-group").addClass("hidden");
                    }else if(idUnidad == -2){
                    	select.closest("form").find(".venta_otro").removeClass("hidden");
                    	floating.find("#venta_eq").closest(".form-group").removeClass("hidden");
                        floating.find("#venta_eq").val("");
                    }else{
                    	select.closest("form").find(".venta_otro").addClass("hidden");
                        select.closest("form").find("#venta_otro").val("");
                        
                        var unidadAlterna = findById(window.unidadesEstandar, idUnidad);
                        var unidadPrincipal = findById(window.unidadesEstandar, idUnidadPrincipal)
                        var factor = convertidor_de_unidades(idUnidad, idUnidadPrincipal, 1, window.articulo.get("id"));
                        
                        if(factor > 0 && unidadAlterna.estandar && unidadPrincipal.estandar){
                        	floating.find("#venta_eq").closest(".form-group").addClass("hidden");
                            floating.find("#venta_eq").val(factor);
                        }else{
                        	floating.find("#venta_eq").closest(".form-group").removeClass("hidden");
                        	floating.find("#venta_eq").val(factor);
                        }
                    }
                 });
            };
            break;

        case "listaReceta":
            ops.titulo = "Recetas";
            ops.destino = $("#step_receta").find(".panel-body");
            ops.destino.html("");

            ops.cols = [
                {head: "id", proper: 'id', class: "hidden id"},
                {head: "Por defecto", proper: "predeterminada", class: "pre",mask:{"true":'<input type="radio" name="receta_check class="form-control" />',"false":'<input type="radio" name="receta_check" class="form-control" />'}},
                {head: "Nombre", proper: "nombre", class: "nombre"}
            ];
            ops.urlDelete = "./rest/articulo/" + window.articulo.get("id") + "/recetas/" + "1" + "/inactivar";
            ops.deleteFunction = function () {
                showMessage("good", "Se ha eliminado la receta");
                showTable({tabla: "listaReceta", modo: "tabla", tipo: "save", id: window.articulo.get("id")});
            };
            ops.asignar = function (data) {
                window.recetaLista = data;
            };
            ops.url = "./rest/articulo/" + window.articulo.get("id") + "/recetas/";
            if (ops.importar) {
                ops.url = "./rest/articulo/" + ops.articuloId + "/recetas/";
            }


            if (ops.custom == true) {
                if (ops.des == true) {
                    ops.url = "./rest/articulo/" + window.articulo.get("id") + "/recetas/?mostrar=inactivos";
                    ops.cols.push({
                        head: 'Acciones',
                        embed: '<input type="button" value="Activar" class="btn btn-primary activar" />'
                    });
                } else {
                    ops.cols.push({
                        head: 'Acciones',
                        embed: '<input type="button" value="Ver datos" class="btn btn-primary ver" /><input type="button" value="Inactivar" class="btn btn-danger desactivar">'
                    });
                }
            }

            ops.method = "GET";
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table, datatable, clon) {

                    table.find(".desactivar").off("click").on("click", function () {
                        me = $(this);

                        me.seguro = function () {
                            var ajax = $.ajax({
                                url: "./rest/articulo/" + window.articulo.get("id") + "/recetas/" + me[0].id + "/inactivar/",
                                type: "POST",
                                success: function () {
                                    $("#shadow_black").css({display: "none", opacity: 0});
                                    $("#confirmar").css({display: "none"});
                                    showMessage("info", "El registro ha sido inactivado correctamente");
                                    $("#receta_lista").find(".panel-body").html("");
                                    showTable({
                                        tabla: "listaReceta",
                                        modo: "tabla",
                                        type: "table",
                                        tipo: "save",
                                        custom: true
                                    });

                                },
                                error: function () {
                                    $("#shadow_black").css({display: "none", opacity: 0});
                                    $("#confirmar").css({display: "none"});
                                    showMessage("error", "El registro No ha sido inactivado");

                                }
                            });
                        };
                        showConfirm(me, "", "Desactivar");
                    });

                    table.find(".ver").off("click").on("click", function () {
                        var me = this;
                        $("#receta_limpiar").click();
                        window.puntosReceta = [];
                        window.recetaGruposeleccion = [];
                        window.recetaIngredientes = [];
                        window.recetaCanales = [];

                        var ajax = $.ajax({
                            url: "./rest/articulo/" + window.articulo.get("id") + "/recetas/" + me.id + "/",
                            type: "GET",
                            success: function (receta) {

                                window.receteActual = receta;

                                window.puntosReceta = receta.puntos;
                                window.recetaIngredientes = receta.ingredientes;
                                window.recetaCanales = receta.canales;
                                $("#shadow_black").css({display: "none", opacity: 0});
                                $("#confirmar").css({display: "none"});
                                cargarCamposReceta(receta);
                            },
                            error: function () {
                                showMessage("error", "No se pudo cargar la receta");
                                $("#shadow_black").css({display: "none", opacity: 0});
                                $("#confirmar").css({display: "none"});
                            }
                        });
                    });

                    table.find(".activar").off("click").on("click", function () {
                        me = $(this);


                        me.seguro = function () {
                            $.ajax({
                                url: "./rest/articulo/" + window.articulo.get("id") + "/recetas/" + me[0].id + "/activar/",
                                type: "POST",
                                success: function () {
                                    $("#shadow_black").css({display: "none", opacity: 0});
                                    $("#confirmar").css({display: "none"});
                                    showMessage("info", "El registro ha sido activado correctamente");
                                    $("#receta_lista").find(".panel-body").html("");
                                    showTable({
                                        tabla: "listaReceta",
                                        modo: "tabla",
                                        tipo: "save",
                                        custom: true,
                                        des: true
                                    });

                                },
                                error: function () {
                                    $("#shadow_black").css({display: "none", opacity: 0});
                                    $("#confirmar").css({display: "none"});
                                    showMessage("error", "El registro No ha sido activado");
                                }
                            });
                        };
                        showConfirm(me, "", "Activar");
                    });
                }
            } else {
                ops.submit = function (flo) {

                    var flo = $(".floating_table.clon_floating");
                    var sel = extraerSeleccionados(flo);
                    var ajax = $.ajax({
                        url: "./rest/articulo/" + window.articulo.get("id") + "/recetas/" + sel[0].id + "/",
                        type: "GET",
                        success: function (data) {
                            cargarCamposReceta(data[0]);
                        },
                        error: function () {
                            showMessage("error", "No se pudo cargar la receta");
                        }
                    });
                    close_floating();

                };

                ops.floating_created = function (floating) {


                };
                ops.tableCreated = function (table, datatable, clon) {
                    if ($(".clon_floating").find(".nombre").length > 0) {
                        //eliminarDeFlo($(".clon_floating").last(),window.punto.punto_franquicias,"id_marca","id");

                    }
                }
            }
            ops.customSet = function () {
                ops.destino.html($("#connection_cargando").clone().removeClass("hidden"));
                ops.container = ops.destino;

                ops.events = function (table, datatable) {
                    table.find(".activar").click(function () {
                        showTable({tabla: "listaReceta", modo: "tabla", tipo: "save", custom: true, id: ops.id});
                        showMessage("good", "Receta activada");
                    });
                    table.find(".ver").click(function () {

                    });
                }
            };
            break;

        case "receta_articulos":
            ops.titulo = "Articulos";
            ops.destino = ops.returnControl;
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden"},
                {proper: "nombre", head: "Articulo", class: "nombre"}
            ];
            ops.submit = function (flo) {
                //showMessage("good", "La tabla ha sido guardada");
                //close_floating();
                //showChargeStep();
            };
            ops.submitOfSelection = function () {
                var flo = $(".floating_table.clon_floating");


                clon1 = $(".clon_floating").first();
                var sel = extraerSeleccionados(flo);
                var ids = [];

                if (!(sel == undefined)) {
                    clon = $(".loading_img").first().clone();

                    clon1.find("#receta_unidad_k").closest("div").addClass("loading_control");
                    clon1.find("#receta_unidad_k").closest("div").append(clon);
                    clon1.find("#receta_unidad_k").find("option.empty").prop("selected", true);
                    
                    if(window.articulo.get("inventarioEnt") && window.articulo.get("inventarioEnt").idUnidadKardex === 12){
                    	clon1.find("#receta_unidad_k").find("option").not("option." + clase).remove();
                        clon1.find("#receta_unidad_k").closest("div").removeClass("loading_control");
                    }
                }
            };
            if (!ops.importar) {
                ops.url = "./rest/articulo/?mostrar=activos";
            } else {
                ops.url = "./rest/articulo/?mostrar=activos";
            }

            ops.method = "GET";
            ops.tableCreated = function (table, datatable, clon) {
                if (clon.find("td.nombre").not(".empty").length > 0) {
                    var flo = $(".floating_table.clon_floating");
                    datatable = flo.find("table").DataTable();


                    if (ops.importar == undefined) {
                        $.grep(window.recetaIngredientes, function (e) {
                            //console.log(datatable.$("input[id='"+ e.id_articulo+"']"));
                            var td = datatable.$("input[id='" + e.id_articulo + "']");
                            var row_ = td.closest("tr");
                            row = datatable.row(row_);
                            row.remove();
                            row_.remove();
                        });
                        var td = datatable.$("input[id='" + window.articulo.id + "']");
                        var row_ = td.closest("tr");
                        row = datatable.row(row_);
                        row.remove();
                        row_.remove();
                    } else {

                    }
                }
            };
            ops.floating_created = function () {

            };
            if (ops.modo == "tabla") {

            } else {


            }
            break;

        case "venta_seleccion":
            ops.html = $("#venta_seleccion").html();
            ops.titulo = "Grupo de seleccion";
            ops.destino = $("#grupo_s_tableCross");
            ops.cols = [
                {proper: "id", head: "id", class: "hidden"},
                {proper: "nombreSeleccion", head: "Nombre"},
                {proper: "idSeleccion", head: "id", class: "hidden"},
                {proper: "tipoSeleccion", head: "Tipo", class: "centrate"},
                {proper: "caracteristica", head: "Caracteristica", class: "centrate"},
                {proper: "unidadNombre", head: "Unidad", class: "centrate"},
                {proper: "unidadId", head: "id", class: "hidden"},
                {proper: "cantidad", head: "cantidad"}
            ];

            ops.url = "./assets/grupoSeleccion/receta/1.json";
            ops.method = "GET";
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table) {

                }
            } else {
                ops.submit = function () {
                    showMessage("good", "La tabla ha sido guardada");
                    close_floating();
                };
            }
            
            ops.floating_created = function (floating) {
                console.log(ops.fila);
                if (ops.fila != undefined) {
                    floating.find("#venta_seleccion_e").click(function () {
                        showTable({
                            tabla: "selecciones",
                            tipo: "select_one",
                            modo: "table",
                            type: "table",
                            returnControl: $(this),
                            id: ops.id
                        });
                    });
                } else {
                    floating.find("#venta_seleccion_e").addClass("label_disabled");
                }

            };
            break;

        case "selecciones_art":
            ops.html = "";
            ops.urlDelete = "#";
            ops.asignar = function () {
            };
            ops.deleteFunction = function () {
            };

            ops.titulo = "Articulos";
            //ops.destino = ops.returnControl;
            ops.cols = [
                {proper: "id", head: "id", class: "hidden"},
                {proper: "nombre", head: "Nombre", class: "centrate nombreArticulo"},
                {proper: "inventarioUnidad", head: "inventarioUnidad", class: "hidden centrate inventarioUnidad"},
                {proper: "ventaUnidad", head: "ventaUnidad", class: "hidden centrate ventaUnidad"},
                {proper: "estado_receta", head: "estado_receta", class: "hidden centrate estado_receta"},
                {proper: "inventario", head: "inventario", class: "hidden centrate inventario"},
            ];
            ops.url = "./rest/articulo/?seleccion=true";
            ops.tableCreated = function (table) {
                flo = $(".clon_floating").last();
                if (flo.find("td.nombreArticulo").length > 0) {

                    $.each(window.seleccionC, function () {
                        flo.find("input[id='" + this.id_articulo + "']").closest("tr").addClass("hidden");
                    });
                }
            };
            ops.submitOfSelection = function () {
                var flo = $(".floating_table.clon_floating");
                var sel = extraerSeleccionados(flo);
                var ids = [];
                
                sel.each(function () {
                    ids.push({id: $(this).val()});
                });

                unidad = $(".floating_form").find("#unidad");
                descarga = $(".floating_form").find("#descarga");
                if (sel.closest("tr").find(".inventario").html() == "true") {
                    descarga.closest(".form-group").removeClass("hidden");
                    descarga.val("");
                    detalleArticuloUrl="./rest/articulo/" + sel.closest("tr").find(".hidden").html() + "/";
                    $.ajax({
                    	url: detalleArticuloUrl,
                    	async: false,
                        type: "GET",
                        success: function (data) {
                        	if(data.inventario && data.inventarioEnt && data.inventarioEnt.idUnidadKardex){
	                        	var clase = data.inventarioEnt.idUnidadKardex;
	                        	if(clase){
	                        		unidad.find("option#opt_" + clase).removeClass("hidden");
	                        		unidad.find("option#opt_" + clase).prop("selected", true);
	                        	}
                        	}else{
                        		showMessage("warning", "El articulo seleccionado no tiene configurada la unidad de kardex");
                        	}
                        },
                        error: function () {
                            verifyConnection(function () {
                                showMessage("error", "No fue posible consultar los detalles del articulo");
                            });
                        }
                    });
                } else {
                	descarga.closest(".form-group").addClass("hidden");
                    descarga.val("");
                    unidad.find("option").removeClass("hidden");
                    unidad.find("option").not(".Unidad").addClass("hidden");
                    unidad.find("option.Unidad").prop("selected", true);
                }
            };
            ops.floating_created = function (floating) {


            };

            break;

        case "selecciones":
            ops.html = $("#seleccion_form").html();
            ops.titulo = "Articulo para la seleccion";
            ops.destino = $("#seleccion_tableCross");
            ops.urlDelete = "#";
            ops.asignar = function (data) {
                window.seleccionC = data;
            };
            ops.workObject = window.seleccionC;
            ops.deleteFunction = function (id, button) {
                var name = button.closest("tr").find(".articuloNombre").html();
                var c = 0;
                $.grep(window.seleccionC, function (e) {
                    if (e.nombreArticulo == name) {
                        window.seleccionC.splice(c, 1);
                    }
                    c++;
                });

                showTable({tabla: "selecciones", modo: "tabla", tipo: "save", workWithObject: true});
            };
            ops.cols = [
                {proper: "id", head: "id", class: "hidden id"},
                {proper: "idArticulo", head: "articuloId", class: "hidden articuloId"},
                {proper: "nombreArticulo", head: "Articulo", class: "articuloNombre"},
                {proper: "idUnidad", head: "unidadId", class: "hidden unidadId"},
                {proper: "nombreUnidad", head: "Unidad", class: "unidadNombre"},
                {proper: "incrementoPrecio", head: "Incrementa", class: "centrate incremento"},
                {proper: "cantidadADescargar", head: "Descarga de kardex", class: "centrate kardex"},
                {proper: "mayorValor", head: "Mayor Valor", class: "centrate kardex"}
            ];

            ops.url = "./rest/grupoSeleccion/" + window.seleccion.id + "/selecciones/";
            ops.method = "GET";
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table) {

                }

            } else {
                ops.submit = function () {
                    var flo = $(".clon_floating").last();

                    var ids = flo.find("#seleccion_articulos").data("ids");
                    if (ids == undefined || ids.length < 1) {
                        showMessage("warning", "debe elegir al menos un articulo");
                    }

                    flo.find("#unidad").valida();
                    flo.find("#descarga").valida({validaCon: "numeros", mensajeParticular: "Ingrese un numero"});
                    flo.find("#incrementa").valida({validaCon: "numeros", mensajeParticular: "Ingrese un numero"});
                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        flo.find(".campo_mal").first().focus();
                        return;
                    }

                    var data = {
                        cantidadADescargar: flo.find("#descarga").val(),
                        idUnidad: flo.find("#unidad").find(":selected").val(),
                        incrementoPrecio: flo.find("#incrementa").val(),
                        idArticulo: ids[0],
                        idGrupoSeleccion: window.seleccion.get("id"),
                        nombreArticulo: flo.find("#seleccion_articulos").html(),
                        nombreUnidad: flo.find("#unidad").find(":selected").html()
                    };

                    if (!(ops.fila == undefined)) {
                        data.id = ops.fila.find(".id").html();
                    }
                    var c = 0;
                    var first = true;
                    var array = _.clone(window.seleccionC);
                    $.grep(window.seleccionC, function (e) {
                        if (e.nombreArticulo == data.nombreArticulo) {
                            array.splice(c, 1);
                        }
                        c++;
                    });
                    window.seleccionC = array;

                    window.seleccionC.push(data);

                    showTable({tabla: "selecciones", modo: "tabla", tipo: "save", workWithObject: true});

                    close_floating();
                    $(".clon_floating").remove();
                };
                ops.floating_created = function (floating) {
                    var flo = $(".clon_floating").last();
                    if ($("#inc_articulo").prop("checked") == true) {
                        flo.find("#incrementa").closest(".form-group").removeClass("hidden")
                    }

                    flo.find("#incrementa").limita({validaCon: "numeros", otros: "."});
                    flo.find("#descarga").limita({validaCon: "numeros", otros: "."});
                    
                    var ajax5 = $.ajax({
                        url: "./rest/unidad/",
                        success: function (data) {
                            fillSelect(data, flo.find("#unidad"), "nombre");
                            if (!(ops.fila == undefined)) {
                                data.id = ops.fila.find(".id").html();
                                flo.find("#seleccion_articulos").data("ids", [ops.fila.find(".articuloId").html()]);
                                flo.find("#seleccion_articulos").html(ops.fila.find(".articuloNombre").html());
                                flo.find("#unidad").find("option[value='" + ops.fila.find(".unidadId").html() + "']").prop("selected", true);
                                flo.find("#incrementa").val(ops.fila.find(".incremento").html());
                                flo.find("#descarga").val(ops.fila.find(".kardex").html());
                                if (flo.find("#descarga").val().length > 0) {
                                    flo.find("#descarga").closest(".form-group").removeClass("hidden");
                                }
                                flo.find("#unidad").prop("disabled", true);
                            }

                            flo.find("#unidad").find("option").addClass("hidden");
                        },
                        error: function () {
                            verifyConnection(function () {
                                showMessage("error", "No fue posible consultar las unidades estandar");
                            });
                        }
                    });

                    if (ops.fila == undefined) {
                        flo.find("#seleccion_articulos").click(function () {
                            showTable({
                                tabla: "selecciones_art",
                                tipo: "select_one",
                                type: "table",
                                returnControl: $(this)
                            });
                        });
                    } else {
                        flo.find("#seleccion_articulos").addClass("label_disabled");
                    }

                };
            }
            break;
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        case "cliente_regimen":
            ops.html = $("#container_cliente_regimen").html();
            ops.titulo = "Configuracion de regimen";
            ops.destino = $("#cliente_regimen_tableCross");
            ops.cols = [
                {head: "id", proper: 'id', class: "hidden id"},
                {head: "idPais", proper: 'idPais', class: "hidden idPais"},
                {head: "Pais", proper: 'pais', class: "nombrePais"},
                {head: "idImpuesto", proper: 'idImpuesto', class: "hidden idImpuesto"},
                {head: "Impuesto", proper: 'impuesto', class: "nombreImpuesto"},
                {head: "idRegimen", proper: 'id', class: "hidden idRegimen"},
                {head: "Regimen", proper: 'nombre', class: "nombreRegimen"}
            ];

            ops.asignar = function (data) {
                window.cliente.regimenes = data;
            };

            ops.url = "./rest/cliente/" + window.idClienteEditar + "/regimenes/";
            ops.urlDelete = "./rest/cliente/" + window.idClienteEditar + "/regimenes";

            ops.deleteMethod = true;
            ops.deleteFunction = function () {
                showMessage("good", "Se ha eliminado el registro");
                showTable({tabla: "cliente_regimen", modo: "tabla", tipo: "save"});
                showTable({tabla: "cliente_canal", modo: "tabla", tipo: "save"});
            };
            
            ops.prevDelete = function (me) {
                $.ajax({
                    url:"./rest/cliente/" + window.idClienteEditar + "/regimenes/"+me.id,
                    type: "DELETE",
                    success:function(){
                    	showMessage("good", "Se ha eliminado el registro");
                        showTable({tabla: "cliente_regimen", modo: "tabla", tipo: "save"});
                        showTable({tabla: "cliente_canal", modo: "tabla", tipo: "save"});
                    },
                    error:function(){
                        showMessage("error","No fue posible eliminar la configuracion verifique su conexion y sesion");
                    }
                });
            };

            ops.method = "GET";
            if (ops.modo == "tabla") {
                ops.tableCreated = function (table, datatable, clon) {

                }
            } else {
                ops.submit = function () {
                    var flo = $(".floating_form.clon_floating");
                    flo.find("#container_cliente_pais").valida();
                    flo.find("#container_regimen").valida();
                    flo.find("#container_cliente_impuesto").valida();

                    if (flo.find(".campo_mal").not(".hidden").length > 0) {
                        flo.find(".campo_mal").first().focus();
                        return;
                    }

                    idRegimen = flo.find("#container_regimen").find(":selected").val();
                    idImpuesto = flo.find("#container_cliente_impuesto").find(":selected").val();

                    var msg = "";
                    if (ops.fila) {
                        var t = $.grep(window.cliente.regimenes, function (e) {
                            if ((e.impuesto == flo.find("#container_cliente_impuesto").find(":selected").html() ) && (e.idRegimen == idRegimen)) {
                                msg = "Esta configuracion ya esta en la tabla";
                                if (ops.fila.find(".id").html() == e.id) {
                                    return false;
                                }
                                return true;
                            } else if (e.impuesto == flo.find("#container_cliente_impuesto").find(":selected").html()) {
                                msg = "Este impuesto ya se encuentra configurado en otro regimen";
                                if (ops.fila.find(".id").html() == e.id) {
                                    return false;
                                }
                                return true;
                            }
                        });
                    } else {
                        var t = $.grep(window.cliente.regimenes, function (e) {
                            if ((e.impuesto == flo.find("#container_cliente_impuesto").find(":selected").html() ) && (e.idRegimen == idRegimen)) {
                                msg = "Esta configuracion ya esta en la tabla";
                                return true;
                            } else if (e.impuesto == flo.find("#container_cliente_impuesto").find(":selected").html()) {
                                msg = "Este impuesto ya se encuentra configurado en otro regimen";
                                return true;
                            }
                        });
                    }
                    if (window.cliente.regimenes.length > 0) {
                        if (t.length > 0) {
                            showMessage("error", msg);
                            return;
                        }
                    }

                    var data = [{
                    	id: idRegimen,
                        idImpuesto: idImpuesto,
                        idPais: flo.find("#container_cliente_pais").find(":selected").val()
                    }];

                    if (!(ops.id == undefined)) {
                        data.id = ops.id;
                    }
                    
                    if(ops.fila){
                    	$.ajax({
                            url:"./rest/cliente/" + window.idClienteEditar + "/regimenes/" + ops.fila.find(".id").html(),
                            type: "DELETE",
                            success:function(){
                            },
                            error:function(){
                                showMessage("error","No fue posible eliminar la configuracion verifique su conexion y sesion");
                            }
                        });
                    }

                    var t = $.ajax({
                        url: "./rest/cliente/" + window.idClienteEditar + "/regimenes/",
                        contentType:"application/json; charset=utf-8",
                        data: JSON.stringify(data),
                        type: "POST",

                        success: function (data) {
                            showTable({tabla: "cliente_regimen", modo: "tabla", tipo: "save"});
                            close_floating();
                        },
                        error: function () {
                            showMessage("error", "No fue posible guardar los datos verifique su conexion y sesion");
                            close_floating();
                        }
                    });
                };

                ops.floating_created = function (floating) {
                    var flo = $(".floating_form.clon_floating");
                    
                    flo.find("#container_cliente_pais").change(function () {
                        flo.find("#container_regimen").find("option").not(".empty").remove();
                        flo.find("#container_cliente_impuesto").find("option").not(".empty").remove();
                        $.ajax({
                            url: "./rest/common/impuestos/?idPais=" + $(this).find(":selected").val(),
                            success: function (data) {
                                if (data.length > 0) {
                                    fillSelect(data, flo.find("#container_cliente_impuesto"), "nombre");
                                } else {
                                    showMessage("info", "0 resultados");
                                }
                            },
                            error: function () {
                                verifyConnection(function () {
                                    showMessage("error", "No fue posible consultar la tabla");
                                });
                            }
                        });
                    });
                    
                    flo.find("#container_cliente_impuesto").change(function () {
                        flo.find("#container_regimen").find("option").not(".empty").remove();
                        $.ajax({
                            url: "./rest/common/regimenes/?idImpuesto=" + $(this).find(":selected").val(),
                            success: function (data) {
                                if (data.length > 0) {
                                    fillSelect(data, flo.find("#container_regimen"), "nombre");
                                } else {
                                    showMessage("info", "0 resultados");
                                }
                            },
                            error: function () {
                                verifyConnection(function () {
                                    showMessage("error", "No fue posible consultar la tabla");
                                });
                            }
                        });
                    });

                    if (ops.fila) {
                        $.ajax({
                            url: "./rest/common/paises",
                            success: function (data) {
                                var pais = flo.find("#container_cliente_pais");
                                var regimen = flo.find("#container_regimen");
                                var impuesto = flo.find("#container_cliente_impuesto");
                                fillSelect(data, pais, "nombre", undefined, undefined, function () {
                                    pais.find("option[value='" + ops.fila.find(".idPais").html() + "']").prop("selected", true);
                                    $.ajax({
                                        url: "./rest/common/impuestos/?idPais=" + pais.find(":selected").val(),
                                        success: function (data) {
                                            if (data.length < 1) {
                                                regimen.prop("disabled", true);
                                                impuesto.prop("disabled", true);
                                                return;
                                            }
                                            fillSelect(data, impuesto, "nombre", undefined, undefined, function () {
                                                impuesto.find("option[value='" + ops.fila.find(".idImpuesto").html() + "']").prop("selected", true);
                                                $.ajax({
                                                    url: "./rest/common/regimenes/?idImpuesto=" + impuesto.find(":selected").val(),
                                                    success: function (data) {
                                                        if (data.length < 1) {
                                                            regimen.prop("disabled", true);
                                                            return;
                                                        }
                                                        fillSelect(data, regimen, "nombre", undefined, undefined, function () {
                                                            regimen.find("option[value='" + ops.fila.find(".idRegimen").html() + "']").prop("selected", true);
                                                        });
                                                    }
                                                });
                                            });
                                        }
                                    });
                                });
                            }
                        });
                    } else {
                        $.ajax({
                            url: "rest/common/paises",
                            success: function (data) {
                                fillSelect(data, flo.find("#container_cliente_pais"), "nombre");
                            }
                        });
                    }
                };
            }
            break;

        case "cliente_canal":
            ops.html = $("#container_cliente_canal").html();
            var check = $("#imp_eq_ch");
            if (check.is(":checked")) {
                ops.titulo = "seleccione el impuesto para</h3><h3>todos los canales";
            } else {
                ops.titulo = "seleccione el impuesto</h3><h3>para el canal";
            }

            ops.destino = $("#cliente_canal_tableCross");
            ops.method = "GET";
            ops.cols = [
                {head: "id", proper: 'id', class: " hidden id"},
                {head: "canalId", proper: 'idCanal', class: "hidden canalId"},
                {head: "Canal", proper: 'canal', class: "canalNombre"},
                {head: "impuestoId", proper: 'impuestoId', class: "hidden impuestoId"},
                {head: "Impuesto", proper: 'impuesto', class: "impuestoNombre"},
                {head: "Regimen", proper: 'regimen', class: "regimenNombre"},
                {proper: "idImpuesto", head: "Impuesto", class: "centrate idImpuesto hidden"},
                {proper: "idRegimen", head: "Regimen", class: "centrate idRegimen hidden"},
                {proper: "idTarifa", head: "Tarifa", class: "centrate idTarifa hidden"},
                {proper: "tarifa", head: "Tarifa", class: "centrate tarifa hidden"},
                {proper: "predeterminado", head: "Predeterminado", class: "centrate predeterminado hidden",mask:{true:"1",false:"0"}},
            ];

            ops.asignar = function (data) {
                //window.cliente.cliente_impuestos = data;
            };

            ops.deleteMethod = true;
            ops.url = "./rest/cliente/" + window.idClienteEditar + "/canalImpuesto/";
            ops.urlDelete = "#Eliminar";

            ops.prevDelete = function (me) {
                $.ajax({
                    url:"./rest/cliente/" + window.idClienteEditar + "/canalImpuesto/"+me.id,
                    type: "DELETE",
                    success:function(){
                        showTable({tabla: "cliente_canal", modo: "tabla", tipo: "save"});
                        showMessage("good","Se ha eliminado la configuracion");
                        close_floating();
                    },
                    error:function(){
                        showMessage("error","No fue posible guardar los datos verifique su conexion y sesion");
                        close_floating();
                    }
                });
            };


                ops.tableCreated = function (table, datatable, clon) {
                    /*
                    if (!($(".clon_floating").find(".id").length > 0)) {
                        $("#cliente_canal_tableCross").find("td.canalId-Nombre").each(function () {
                            this.innerHTML = window.catalogo_canalesCliente[this.innerHTML];
                        });
                    }
                    */
                }
                if (ops.modo == "tabla") {
                } else {
                    ops.submit = function () {
                        var flo = $(".floating_form.clon_floating");
                        flo.find("#container_canal").valida();
                        flo.find("#container_canal_impuesto").valida();
                        flo.find("#tarifa_select").valida();
                        if (flo.find(".campo_mal").not(".hidden").length > 0) {
                            flo.find(".campo_mal").first().focus();
                            return;
                        }
                        /*
                        var idTarifa=flo.find("#tarifa_select").find(":selected").val();
                        if(!flo.find("#tarifa_select").is(":checked")){

                        }*/
                        /*
                        if (window.cliente.cliente_canal.length > 0) {
                            if ($.grep(window.cliente.cliente_canal, function (e) {
                                    return e.canalId == flo.find("#container_canal").find(":selected").val() &&
                                        e.impuestoId == flo.find("#container_canal_impuesto").find(":selected").val();
                                }).length > 0) {
                                showMessage("error", "El canal ya tiene ese impuesto configurado");
                                return;
                            }
                        }
                        */
                        var data={
                            "predeterminado": flo.find("#imp_mas_usado").prop("checked"),
                            "idCanal": flo.find("#container_canal").find(":selected").val(),
                            "idCliente": window.idClienteEditar,
                            "idImpuesto": flo.find("#container_canal_impuesto").find(":selected").attr("class").split("_")[1],
                            "idRegimen": flo.find("#container_canal_impuesto").find(":selected").val(),
                            "idTarifa":flo.find("#tarifa_select").find(":selected").val()
                        }

                        if (ops.id != undefined) {
                            data.id = ops.id;
                        }

                        if(flo.find("#container_canal").find(":selected").val()!=null && flo.find("#container_canal").find(":selected").val()!=""){
                        	var idCanal = parseInt(flo.find("#container_canal").find(":selected").val());
                        	var idImpuestoRegimen = parseInt(flo.find("#container_canal_impuesto").find(":selected").val());
                        	
                        	var canalExistente = window.cliente.cliente_impuestos.filter(function( element ) {
                    	        return +element.idCanal === +idCanal && +element.idRegimen === idImpuestoRegimen;
                    	    })[ 0 ];
                        	
                            if(!ops.fila && canalExistente){
                                showMessage("warning","El canal ya ha sido configurado");
                                return;
                            }

                            var method="POST";
                            if(ops.fila!=undefined){
                                ops.url+="/"+ops.id;
                                method="PUT";
                            }

                            $.ajax({
                                url:ops.url,
                                data: JSON.stringify(data),
                                contentType:"application/json;charset=UTF-8",
                                type: method,
                                success: function (data) {
                                    showTable({tabla: "cliente_canal", modo: "tabla", tipo: "save"});
                                    close_floating();
                                },
                                error: function () {
                                    showMessage("error", "No fue posible guardar los datos verifique su conexion y sesion");
                                    close_floating();
                                }
                            });
                        }else{
                            showChargeStep();
                            var c=0;
                            for(var i=0;i<window.catalogo_canalesCliente.length;i++){
                                if(i == window.catalogo_canalesCliente.length){
                                    var final=true;
                                }

                                if(JSON.search(window.cliente.cliente_impuestos, '//*[idCanal=' + window.catalogo_canalesCliente[i].id + ']').length<1){
                                    data.idCanal =  window.catalogo_canalesCliente[i].id;

                                    var method="POST";
                                    if(ops.fila!=undefined){
                                        ops.url+="/"+ops.id;
                                        method="PUT";
                                    }

                                    $.ajax({
                                        url:ops.url,
                                        data: JSON.stringify(data),
                                        contentType:"application/json;charset=UTF-8",
                                        type: method,
                                        success: function (data,final) {
                                            if(final){
                                                showTable({tabla: "cliente_canal", modo: "tabla", tipo: "save"});
                                                close_floating();
                                                closeChargeStep();
                                            }
                                        },
                                        error: function () {
                                            showMessage("error", "No fue posible guardar los datos verifique su conexion y sesion");
                                            close_floating();
                                        }
                                    });
                                }else{
                                    c++;
                                }

                            }
                            if(c==window.catalogo_canalesCliente.length){
                                showMessage("warning","No hay mas canales disponibles");
                                close_floating();
                            }
                        }
                    };
                    ops.floating_created = function (floating) {
                        var flo = $(".floating_form.clon_floating");
                        var check = $("#imp_eq_ch");
                        if (check.is(":checked")) {
                            flo.find("#container_canal").closest(".form-group").addClass("hidden");
                        } else {
                            flo.find("#container_canal").closest(".form-group");
                        }

                        flo.find("#unica_tarifa").change(function(){
                            if(flo.find("#unica_tarifa").prop("checked")){
                                flo.find("#tarifa_select").removeClass("hidden");
                                flo.find("#tarifa_select").closest(".form-group").removeClass("hidden");
                            }else{
                                flo.find("#tarifa_select").addClass("hidden");
                                flo.find("#tarifa_select").find(".empty").prop("selected",true);
                                flo.find("#tarifa_select").closest(".form-group").addClass("hidden");
                            }
                        });

                        flo.find("#container_canal_impuesto").change(function(){
                            if($(this).find(":selected").val()!=""){
                                $.ajax({
                                    url: "./rest/common/tarifas/?idRegimen=" + $(this).val(),
                                    success: function (data) {
                                        if (data.length > 0) {
                                            fillSelect(data, flo.find("#tarifa_select"), "valor","","",function(){
                                                if(ops.fila!=undefined){
                                                    flo.find("#tarifa_select").find("option[value='"+ops.fila.find(".idTarifa").html()+"']").prop("selected",true);
                                                    if(ops.fila.find(".tarifa").html()!=""&&ops.fila.find(".tarifa").html()!=" "){
                                                        flo.find("#tarifa_select").closest(".form-group").removeClass("hidden");
                                                        flo.find("#tarifa_select").removeClass("hidden");
                                                        flo.find("#unica_tarifa").prop("checked",true);
                                                        flo	.find("#container_canal").find("option[value='"+ops.fila.find(".idTarifa").html()+"']").prop("selected",true);
                                                    }
                                                }
                                            });
                                        }else{
                                            showMessage("info", "No hay tarifas");
                                        }
                                    },
                                    error: function () {
                                        //verifyConnection("Ha ocurrido un error de conexion", function () {
                                            showMessage("error", "No fue posible consultar el impuesto");
                                        //});
                                    }
                                });
                            }
                        });
                        var select_impuesto=[];

                        if( window.cliente.cliente_canal_lista.length>0){
                            fillSelect(window.cliente.cliente_canal_lista._canales, flo.find("#container_canal"), "canalNombre");
                        }

                        $.each(window.cliente.regimenes,function(index,value){
                            select_impuesto.push({id:value.id,nombre:value.impuesto+" "+value.nombre,idImpuesto:value.idImpuesto});
                        });

                        if(window.cliente.regimenes.length>0){
                            fillSelect(select_impuesto, flo.find("#container_canal_impuesto"), "nombre","impuestoId","impuestoId_");
                            if(flo.find("#container_canal_impuesto").find(":selected").val()!=""){
                                flo.find("#container_canal_impuesto").change();
                            }
                        }

                        if(ops.fila!=undefined){
                            flo.find("#imp_mas_usado").prop("checked",ops.fila.find(".predeterminado").html()==1);
                            flo.find("#container_canal").find("option[value='"+ops.fila.find(".canalId").html()+"']").prop("selected",true);
                            flo.find("#container_canal_impuesto").find("option[value='"+ops.fila.find(".idRegimen").html()+"']").prop("selected",true);
                            flo.find("#container_canal_impuesto").change();
                        }
                    };
                }
                break;

                case
                "punto_permisos"
                :
                ops.workWithObject = true;
                if (ops.allPer == undefined) {
                    var obj = _.clone(window.punto.attributes);
                    obj.cliente_id = null;
                    obj.id = null;
                    obj.nombre = null;
                    obj.direccion = null;
                    obj.telefono = null;
                    obj.extension = null;
                    obj.telefono2 = null;
                    obj.extension2 = null;
                    obj.municipio_id = null;
                    obj.tipo_negocio_id = null;
                    obj.centro_costo_id = null;
                    obj.marcas = null;
                    ops.notEdit = true;
                    var object_ = [];
                    for (var key in obj) {
                        if (obj[key] != null && window.gama.mapeo[key] != undefined) {
                            if (obj[key] == true || obj[key] == "true" || obj[key] == 1) {
                                object_.push({
                                    configuracion: window.gama.mapeo[key],
                                    key: key
                                });
                            }
                        }
                    }

                    ops.workObject = object_;
                } else {
                    var object_ = [];
                    for (var key in window.gama.mapeo) {
                        object_.push({
                            configuracion: window.gama.mapeo[key],
                            key: key
                        });
                    }

                    ops.workObject = object_;
                }

                ops.html = ops.returnControl;
                ops.titulo = "Configuracion";
                ops.destino = $("#permisos_tableCross");

                ops.cols = [
                    {head: "configuracion", proper: 'configuracion', class: "configuracion"},
                    {head: "key", proper: 'key', class: "key hidden"}
                ];

                ops.asignar = function (data) {

                    window.punto.punto_permisos = data;
                };
                ops.validateMe = function (ops) {
                    if ($("#unidad_k").find(":selected").val() < 1 || $("#grupo").find(":selected").val() < 1 || $("#clase").find(":selected").val() < 1) {
                        return {validate: false, message: "Elija una clase, un grupo y una unidad de Kardex"};
                    }
                    return {validate: true};
                };

                ops.urlDelete = "#";
                ops.prevDelete = function (obj) {
                    var properties = {};
                    properties[$(obj).parent().siblings(".key").html()] = false;
                    guardarPermisos(properties, true);
                };
                ops.deleteFunction = function (id) {
                };

                ops.method = "GET";
                ops.tableCreated = function (table, datatable, clon) {
                    if ($(".clon_floating").find(".key").length > 0) {
                        var flo = $(".clon_floating").last();
                        var table = flo.find("table").DataTable();
                        $.each(window.gama.mapeo, function (index, value) {
                            if (window.punto.attributes[index] == "true" || window.punto.attributes[index] == true) {
                                table.row(flo.find(".key:contains('" + index + "')").parent()).remove();
                                flo.find(".key:contains('" + index + "')").parent().remove();
                            }
                        });
                        if (flo.find("thead").find("tr").length < 1) {
                            flo.find("thead").append("<th class='hidden'></th>");
                        }
                        if (flo.find("tbody").find("tr").length < 1) {
                            flo.find("thead").append("<td class='hidden'></td>");
                        }
                        table.draw();
                    }
                };
                ops.floating_created = function (floating) {

                };
                if (ops.modo == "tabla") {

                } else {
                    ops.submit = function () {
                        var flo = $(".clon_floating").last();
                        var datatable = flo.find("table").DataTable();
                        var checkeds = datatable.$("input:checked");
                        var properties = {};

                        checkeds.each(function () {
                            properties[$(this).parent().siblings(".key").html()] = true;
                        });
                        guardarPermisos(properties, true);
                        close_floating();
                    };
                }
                break;

                case "punto_franquicias":
	                ops.type = "table";
	                ops.notEdit = true;
	                ops.titulo = "Marcas";
	                ops.destino = $("#franquicias_tableCross");
	
	                ops.asignar = function (data) {
	                    if (ops.all == undefined) {
	                        window.punto.punto_franquicias = data;
	                    }
	                };
	
	                ops.validateMe = function (ops) {
	                    return true;//jjjhh
	                };
	
	                if (ops.all) {
	                    ops.url = './rest/marca/';
	                    ops.cols = [
	                        {head: "id", proper: 'id', class: "hidden marcaId"},
	                        {head: "Nombre", proper: 'nombre', class: "marcaNombre"},
	                        {head: "id_marca", proper: 'id_marca', class: "id_marca hidden"},
	
	                    ];
	                } else {
	                    ops.url = "./rest/punto/" + window.punto.id + "/marcas/";
	                    ops.cols = [
	                        {head: "id", proper: 'id', class: "hidden id"},
	                        {head: "marcaId", proper: 'marcaId', class: "hidden marcaId"},
	                        {head: "Nombre", proper: 'nombre', class: "marcaNombre"},
	                        {head: "id_marca", proper: 'id_marca', class: "id_marca hidden"},
	                    ];
	                }

	                ops.prevDelete = function (me) {
	                	$.ajax({
	                        url : "./rest/punto/" + window.punto.id + "/marcas/" + me.id,
	                        type: "DELETE",
	                        success:function(){
	                        	showMessage("good", "La marca se ha eliminado");
	    	                    showTable({tabla: "punto_franquicias", modo: "tabla", tipo: "save", id: window.punto.id});
	                        },
	                        error:function(){
	                            showMessage("error","No fue posible eliminar la marca verifique su conexion y sesion");
	                        }
	                    });
	                };
	                
	                ops.deleteMethod = true;
	                ops.deleteFunction = function () {
	                	showMessage("good", "La marca se ha eliminado");
	                    showTable({tabla: "punto_franquicias", modo: "tabla", tipo: "save", id: window.punto.id});
	                };
	
	                ops.method = "GET";
	                
	                ops.tableCreated = function (table, datatable, clon) {
	                    if ($(".clon_floating").find(".marcaId").length > 0) {
	                    	seleccionaDeFlo($(".clon_floating").last(), window.punto.punto_franquicias, "id", "id");
	                    }
	                };
	                
	                if (ops.modo == "tabla") {
	
	                } else {
	                    ops.submit = function () {
	                        var flo = $(".clon_floating").last();
	                        ids = flo.find("input:checked").not(".all_check");
	                        if (ids.length > 0) {
	                            var t = [];
	                            ids.each(function () {
	                                t.push({
	                                    "id": this.id
	                                });
	                            });
	                        }
	                        $.ajax({
	                            url: "./rest/punto/" + window.punto.id + "/marcas/",
	                            type: "POST",
	                            contentType: 'application/json',
	                            data: JSON.stringify(t),
	                            success: function () {
	                                showMessage("good", "La marca se ha guardado");
	                                showTable({
	                                    tabla: "punto_franquicias",
	                                    modo: "tabla",
	                                    tipo: "save",
	                                    id: window.punto.id
	                                });
	                                close_floating();
	                            },
	                            error: function () {
	                                showMessage("error", "No fue posible guardar la tabla");
	                            }
	                        });
	                    };
	
	                }
	                break;

                case
                "marca_clientes"
                :
                ops.titulo = "Clientes";
                ops.destino = $("#marca_clientes_tableCross");

                ops.cols = [
                    {head: "id", proper: 'id', class: "hidden id"},
                    {head: "Nombre", proper: 'nombre', class: "hidden nombre"}
                ];

                ops.asignar = function (data) {
                    window.marca.marca_clientes = data;
                };

                ops.url = window.Jservicios + usuario.get("idCliente") + "/rest/controller/punto/" + window.punto.id + "/puntoBodega/listar/";

                ops.urlDelete = window.Jservicios + usuario.get("idCliente") + "/rest/controller/punto/" + window.punto.id + "/puntoBodega/eliminar";
                ops.deleteMethod = true;
                ops.deleteFunction = function () {
                    showMessage("good", "La bodega se ha eliminado");
                    showTable({tabla: "punto_bodegas", modo: "tabla", tipo: "save", id: window.punto.id});
                };

                ops.method = "GET";
                if (ops.modo == "tabla") {
                    ops.tableCreated = function (table, datatable, clon) {

                    }
                } else {
                    ops.submit = function () {

                    };
                    ops.floating_created = function (floating) {
                        var flo = $(".clon_floating").last();
                        flo.find("#container_bodega").val();
                        console.log(ops.fila);
                        if (!(ops.fila == "")) {
                            flo.find("#container_bodega").val(ops.fila.find(".bodegaNombre").html());
                        }
                    };
                }
                break;

                case "punto_bodegas":
	                ops.custom = true;
	                ops.titulo = "Bodegas";
	                ops.destino = $("#bodegas_tableCross");
	
	                ops.html = $("#container_bodegas_del_punto").html();
	                ops.cols = [
	                    {head: "id", proper: 'id', class: "hidden id"},
	                    {head: "Nombre", proper: 'nombre', class: "bodegaNombre"}
	                ];
	                if (ops.custom == true) {
	                    if (ops.des == true) {
	                        ops.url = "./rest/punto/" + window.punto.id + "/bodegas/?mostrar=inactivos";
	                        ops.cols.push({
	                            head: 'Acciones',
	                            embed: '<input type="button" value="Activar" class="btn btn-primary activar" />'
	                        });
	                    } else {
	                        ops.url = "./rest/punto/" + window.punto.id + "/bodegas/?mostrar=activos";
	                        ops.cols.push({
	                            head: 'Acciones',
	                            embed: '<input type="button" value="Ver datos" class="btn btn-primary ver" /><input type="button" value="Inactivar" class="btn btn-danger desactivar">'
	                        });
	                    }
	                }
	                ops.asignar = function (data) {
	                    if (ops.des) {
	                        window.punto.punto_bodegas_des = data;
	                        return
	                    }
	                    window.punto.punto_bodegas = data;
	                };
	
	                ops.customSet = function () {
	                    ops.destino.html($("#connection_cargando").clone().removeClass("hidden"));
	                    ops.container = ops.destino;
	
	                    ops.events = function (table) {
	                        table.find(".desactivar").click(function () {
	                            var me = this;
	                            me.seguro = function () {
	                                desactivarBodega(me, table);
	                                $("#shadow_black").css({display: "none", opacity: 0});
	                                $("#confirmar").css({display: "none"});
	                            };
	                            showConfirm(me, "Esta seguro que quiere inactivar la bodega?", "Inactivar");
	                        });
	                        table.find(".activar").click(function () {
	                            var me = this;
	                            me.seguro = function () {
	                                activarBodega(me, table);
	                                $("#shadow_black").css({display: "none", opacity: 0});
	                                $("#confirmar").css({display: "none"});
	                            };
	                            showConfirm(me, "Esta seguro que quiere activar la bodega?", "Activar");
	                        });
	                        table.find(".ver").click(function () {
	                            showTable({
	                                tabla: "punto_bodegas",
	                                modo: "form",
	                                tipo: "floating",
	                                fila: $(this).closest("tr")
	                            });
	                        });
	                    }
	                };
	
	                ops.urlDelete = "./rest/punto/" + window.punto.id + "/bodegas/";
	                ops.deleteMethod = true;
	                ops.deleteFunction = function () {
	                    showMessage("good", "La bodega se ha eliminado");
	                    showTable({tabla: "punto_bodegas", modo: "tabla", tipo: "save", id: window.punto.id});
	                };
	
	                ops.method = "GET";
	
	                ops.tableCreated = function (table, datatable, clon) {
	                    if ($(".clon_floating").find(".bodegaNombre").length > 0) {
	                        clon.find("#container_bodega").val(ops.fila.find(".bodegaNombre").html());
	                    }
	                };
	
	                if (ops.modo == "tabla") {
	
	                } else {
	
	                    ops.submit = function () {
	                        var flo = $(".floating_form.clon_floating");
	                        var nombre = flo.find("#container_bodega").val();
	
	                        if (!(nombre.length > 0)) {
	                            showMessage("error", "Ingrese un nombre a bodega");
	                            return;
	                        }
	
	                        var bodegas_des = window.punto.punto_bodegas_des;
	                        if (bodegas_des == undefined || bodegas_des == "" || bodegas_des.length < 1) {
	                            bodegas_des = [];
	                        }
	                        var bodegas_act = window.punto.punto_bodegas;
	                        if (bodegas_act == undefined || bodegas_act == "" || bodegas_act.length < 1) {
	                            bodegas_act = [];
	                        }
	                        var bodegas = bodegas_des.concat(bodegas_act);
	                        var puntoId = window.punto.get("id");
	                        var t = {nombre: nombre};
	                        if (ops.fila == undefined) {
	                            if (bodegas.length > 0) {
	                                if ($.grep(bodegas, function (e) {
	                                        return e.puntoId == puntoId && e.nombre == nombre;
	                                    }).length > 0) {
	                                    showMessage("warning", "Esta bodega ya ha sido asignada");
	                                    return;
	                                }
	                            }
	                        } else {
	                            id = ops.fila.find(".id").html();
	                            if (bodegas.length > 0) {
	                                if ($.grep(bodegas, function (e) {
	                                        if (id == e.id) {
	                                            return false;
	                                        }
	                                        return e.puntoId == puntoId && e.nombre == nombre;
	                                    }).length > 0) {
	                                    showMessage("warning", "Esta bodega ya ha sido asignada");
	                                    return;
	                                }
	                            }
	                            t.id = id;
	                        }
	
	                        $.ajax({
	                            url: "./rest/punto/" + window.punto.id + "/bodegas/",
	                            type: "POST",
	                            contentType: "application/json",
	                            data: JSON.stringify(t),
	                            success: function () {
	                                showMessage("good", "Se ha guardado la bodega");
	                                showTable({tabla: "punto_bodegas", modo: "tabla", tipo: "save", id: window.punto.id});
	                            },
	                            error: function (response, xhr, others) {
	                                var response = response.responseText.toString();
	                                if (response.indexOf("existe") > -1) {
	                                    showMessage("error", "Este punto ya tiene una bodega con ese nombre");
	                                } else {
	                                    showMessage("error", "No fue posible guardar la tabla");
	                                }
	                            }
	                        });
	                        close_floating();
	                    };
	                    ops.floating_created = function (floating) {
	
	                        if ($(".clon_floating").find(".marcaId").length > 0) {
	                            eliminarDeFlo($(".clon_floating").last(), window.punto.punto_bodegas, "id_marca", "id");
	                        }
	
	                        var flo = $(".clon_floating").last();
	                        flo.find("#container_bodega").val();
	                        if (!(ops.fila == undefined)) {
	                            flo.find("#container_bodega").val(ops.fila.find(".bodegaNombre").html());
	                        }
	                    };
	                }
	                break;
                
                case "articulo_bodegas":
	                ops.workWithObject = true;
	                ops.workObject = window.bodegasArticulo;
	                ops.type = "table";
	                ops.notEdit = true;
	                ops.titulo = "Bodegas";
	                ops.destino = ops.returnControl;
	
	                ops.cols = [
	                    {head: "id", proper: 'id', class: "hidden id"},
	                    {head: "Nombre", proper: 'nombre', class: "nombre"}
	                ];
	                ops.asignar = function (data) {
	
	                    window.bodegas = data;
	                };
	
	                ops.validateMe = function (ops) {
	
	                };
	                ops.url = "./rest/bodegas/?idArticulo="+window.articulo.get("id");
	                
	                ops.urlDelete = "";
	                ops.deleteFunction = function () {
	
	                };
	
	                ops.method = "GET";
	                if (ops.modo == "tabla") {
	                    ops.tableCreated = function (table, datatable, clon) {
	
	                    }
	                } else {
	                    ops.submit = function () {
	
	                        close_floating();
	                    };
	                    ops.floating_created = function (floating) {
	
	                    };
	                }
	                break;
                
                case "clase_grupos":
                	showClaseGruposTable(ops);
                    break;
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
            if (existWithVal(ops.destino)) {
                //.destino.closest(".form-group").removeClass("hidden");
            }

            if (ops.tipo == "floating") {
                var floating = creatorFloating(ops.titulo, "Guardar", ops.html, ops.type, ops.submit);
                ops.floating_created(floating);
                return;
            }
            ops.container = $("<div></div>");
            ops.events = function (table) {
                $(".check_me").children().click(function (e) {
                    e.stopPropagation();
                });
                $(".check_me").click(function (e) {
                    $(this).find("input").prop('checked', !$(this).find("input").prop("checked"));
                });
                $(".all_check").click(function (e) {
                    table = $(this).closest("table").dataTable();
                    table.$('input').not("input[disabled='disabled']").prop("checked", $(this).prop("checked"));
                });

                $(".viewRecord").click(function (e) {
                    close_floating();
                    Backbone.history.navigate('#ver/puntos/' + $(this).parent().siblings(".puntoId").html(), {trigger: true});
                });

            };
            //console.log(ops.cols);
            switch (ops.tipo) {
                case "select_multiple":
                    ops.cols.unshift({
                        head: '<input class="all_check check_me form-control ui-wizard-content" type="checkbox"> Seleccionar todos',
                        embed: '<input class="form-control ui-wizard-content" type="checkbox"/>',
                        class: "check_me"
                    });
                    break;
                case "save":
                    if (!(ops.custom == true)) {
                        //console.log(ops);
                        ops.destino.html($("#connection_cargando").clone().removeClass("hidden"));
                        ops.container = ops.destino;
                        if (existWithVal(ops.notEdit)) {
                            if (ops.notDelete == true) {

                            } else {
                                ops.cols.push({
                                    head: 'Acciones',
                                    embed: '<i class="icon32 i-minus-circle-2 minus_delete green"></i>'
                                });
                            }
                        } else if (existWithVal(ops.onlyEdit)) {
                            ops.cols.push({
                                head: 'Acciones',
                                embed: '<i class="icon icon32 i-pencil-3 pencil_edit green"></i>'
                            });
                        } else {
                            ops.cols.push({
                                head: 'Acciones',
                                embed: '<i class="icon icon32 i-pencil-3 pencil_edit green"></i><i class="icon32 i-minus-circle-2 minus_delete green"></i>'
                            });
                        }

                        ops.events = function (table, datatable) {

                            table.find(".pencil_edit").off("click").on("click", function (e) {
                                ops.edit($(this).closest("tr"), this.id);
                            });

                            table.find(".minus_delete").off("click").on("click", function () {
                                var button = $(this);
                                if (!(ops.prevDelete == undefined)) {
                                    ops.prevDelete(this);
                                    if (ops.prevDelete == undefined) {
                                        return;
                                    }

                                }

                                var id = this.id;

                                var method = "GET";

                                if (ops.deleteMethod == true) {
                                    method = "POST";
                                }

                                if (!(ops.multipleRecords == undefined)) {
                                    var url = ops.urlDelete + "/" + id + "/eliminar/";
                                } else {
                                    var url = ops.urlDelete + "/" + id + "/";
                                }


                                if (ops.notAjax == undefined) {
                                    var t = $.ajax({
                                        url: url,
                                        method: method,
                                        //crossDoamin: true,
                                        success: function () {
                                            ops.deleteFunction(id, button);

                                        },
                                        error: function () {
                                            verifyConnection(function () {
                                                showMessage("error", "No fue posible eliminar el registro de la tabla");
                                            })
                                        }
                                    });
                                } else {
                                    ops.deleteFunction(id, button);
                                }

                                //console.log(t);
                            });
                        }
                    } else {
                        ops.customSet();
                    }
                    break;
                case "view":
                    //ops.cols.push({head: 'Accion', embed: '<input class="btn btn-primary viewRecord" value="Ver datos" type="button"/>'});
                    break;
                case "new_view":
                    ops.destino.html($("#connection_cargando").clone().removeClass("hidden"));
                    ops.container = ops.destino;
                    ops.cols.unshift({
                        head: '<input class="all_check check_me form-control ui-wizard-content" type="checkbox"> Seleccionar todos',
                        embed: '<input class="form-control ui-wizard-content" type="checkbox"/>',
                        class: "check_me"
                    });
                    break;
                    break;
                case "select_one":
                default:
                    ops.cols.unshift({
                        head: 'Elija',
                        embed: '<input name="radio" class="form-control ui-wizard-content" type="radio"/>',
                        class: "check_me"
                    });
                    break;
            }

        function success_(data) {

            if ((data == undefined || data == null || data.length == undefined) && (ops.custom == null || ops.custom == undefined)) {
                if (!(ops.asignar == undefined)) {
                    if (typeof(data) == "array" || !(ops.forceAssig == "undefined")) {
                        closeChargeStep();
                        ops.asignar([]);
                    }
                }
                ops.container.html("");
                if (jQuery.isEmptyObject(data)) {
                    if (ops.showResults == true) {
                        showMessage("info", ops.zeroResults);
                        //closeChargeStep();
                        return false;
                    }
//
                    console.log("--data no convence--" + ops.url, ops);
                    return false;
                }

                console.log("_____________________________");
                console.log("data no convence");
                console.log(data);
                console.log(this);
                console.log(ops);
                console.log("_____________________________");
                ops.container.html("");
                closeChargeStep();
                return false;
            }

            if (!(ops.asignar == undefined)) {
                ops.asignar(data);
            }

            if (typeof(data) == "string") {
                //console.log("es string");
                closeChargeStep();
                //var data=jQuery.parseJSON(data);
            }

            creator_tables(data, ops.cols, ops.container);

            if (data.length < 1 || data.length == null || data.length == undefined) {
                ops.container.html(
                    '<table class="data_table dataTables dataTable no-footer" id="DataTables_Table_15" role="grid" aria-describedby="DataTables_Table_15_info">' +
                    '<thead><tr role="row"><th class="sorting_asc" tabindex="0" aria-controls="DataTables_Table_15" rowspan="1" colspan="1" aria-sort="ascending" aria-label=": Activar para ordenar la columna de manera ascendente" style="width: 1239px;"></th></tr></thead>' +
                    '<tbody><tr role="row" class="odd"><td class="centrate sorting_1">No hay resultados</td></tr></tbody>' +
                    '<tfoot></tfoot>' +
                    '</table>');
            }
            //console.log(ops.container.html());
            var table = ops.container.find("table");
            var clon = "";
            if (!(ops.tipo == "save")) {
                if (existWithVal(ops.button)) {
                    clon = creatorFloating(ops.titulo, ops.button, ops.container.html(), ops.type, function () {
                    });
                } else {
                    ops.button = "";
                    clon = creatorFloating(ops.titulo, "Guardar", ops.container.html(), ops.type, function () {
                    });
                }

                //console.log(clon);
                clon.button.click(function () {
                    if (ops.returnControl == false) {
                        ops.submit(clon.table, clon.datatable);
                    } else {
                        var checks = clon.datatable.$('input').not("input[disabled='disabled']").filter(":checked").map(function () {
                            return this.id;
                        }).get();
                        var names = clon.datatable.$('input').not("input[disabled='disabled']").filter(":checked").parent().siblings().not(".hidden").map(function () {
                            return $(this).html();
                        }).get().join(", ");
                        if (checks.length > 0) {
                            ops.returnControl.html(names);
                            ops.returnControl.data("ids", checks);
                        } else {
                            ops.returnControl.html("0 seleccionados");
                            ops.returnControl.data("ids", []);
                        }
                        if (!(ops.saveSel == undefined)) {
                            ops.saveSel();
                        }
                        if (!(ops.submitOfSelection == undefined)) {
                            ops.submitOfSelection(clon);
                        }
                        close_floating();
                    }
                });
            }
            //console.log(table.html());
            var datatable = table.dataTable();
            ops.events(table);
            if (!(ops.tableCreated == undefined)) {
                ops.tableCreated(table, datatable, clon);
            }

        }

        function error_(a, b, c) {
            console.log(a);
            console.log(b);
            console.log(c);
            verifyConnection(showMessage("error", "No fue posible realizar esta carga"));
            close_floating();
            closeChargeStep();
        }

            if (ops.workWithObject == undefined) {

                $.ajax({
                    url: ops.url,

                    success: function (data) {
                        success_(data);
                    },
                    error: function (a, b, c) {
                        error_(a, b, c);
                    }
                });
            } else {
                success_(ops.workObject);
            }
    }
