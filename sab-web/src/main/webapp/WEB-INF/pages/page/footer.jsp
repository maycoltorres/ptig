<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="javascritps">
    <!-- Le javascript
    ================================================== -->
    <!-- Important plugins put in all pages -->
    <script type="text/javascript">
        <jsp:include page="../../../assets/js/libs/miLoader.js"/>
        var charged = 0;
        window.Jservicios='../gama/servicios.php?text=Af_4L32Kj-23fGs/';

        var chargeFiles = [
            "assets/js/libs/jquery-1.11.1.min.js",
            "assets/js/libs/validate_charge.js",
            "assets/js/libs/jquery-ui.js",
            "assets/js/libs/underscore.js",
            "assets/js/libs/backbone.js",
            "assets/backbone/overwrite.js",
            "assets/js/libs/handlebars.js",
            "assets/backbone/model/usuarios.js",
            "assets/backbone/model/roles.js",
            "assets/backbone/model/roles_cliente.js",
            "assets/backbone/model/grupos.js",
            "assets/backbone/model/clientes.js",
            "assets/backbone/model/marcas.js",
            "assets/backbone/model/puntos.js",
            "assets/backbone/model/articulos.js",
            "assets/backbone/model/selecciones.js",
            "assets/backbone/model/listas_precios.js",
            "assets/backbone/model/clases.js",
            "assets/js/libs/datatables/jquery.dataTables.min.js",
            "assets/js/libs/datatables/jquery.dataTables.min.css",
            "assets/js/libs/TableTools.ShowSelectedOnly.js",
            "assets/js/libs/jquery.mask.min.js",
            "assets/js/v_de_validacion/v_de_validacion.css",
            "assets/js/v_de_validacion/v_de_validacion.js",
            "assets/template/js/conditionizr.min.js",
            "assets/template/js/excanvas.min.js",
            "assets/template/js/plugins/core/jrespond/jRespond.min.js",
            "assets/template/js/respond.min.js",
            "assets/template/js/bootstrap/bootstrap.min.js",
            "assets/template/js/jquery.mousewheel.js",
            "assets/template/js/jquery-ui-sliderAccess.js",
            "assets/template/js/jquery-ui-timepicker-addon.js",
            "assets/template/js/plugins/forms/select2/select2.css",
            "assets/template/js/plugins/forms/select2/select2.js",
            "assets/template/js/plugins/forms/select2/locale/select2_locale_es.js",
            "assets/template/js/jquery.genyxAdmin.js",
            "assets/template/js/plugins/forms/uniform/jquery.uniform.min.js",
            "assets/template/js/app.js",
            "assets/js/libs/defiant.min.js",

            "assets/backbone/helpers.js",
            "assets/js/showTable.js",
            "assets/js/eventos_de_lista.js",
            "assets/js/openSection.js",
            "assets/js/openTable.js",
            "assets/js/openTableEvents.js",
            "assets/backbone/controller.js",

            "assets/js/main/cliente/funciones.js",
            "assets/js/main/usuario/funciones.js",
            "assets/js/main/grupo/funciones.js",
            "assets/js/main/grupoSeleccion/funciones.js",
            "assets/js/main/marcas/funciones.js",
            "assets/js/main/misRoles/funciones.js",
            "assets/js/main/roles/funciones.js",
            "assets/js/main/punto/funciones.js",
            "assets/js/main/clase/funciones.js",
            "assets/js/main/articulo/basico/charge.js",
            "assets/js/main/articulo/basico/events.js",
            "assets/js/main/articulo/basico/load.js",
            "assets/js/main/articulo/inventario/charge.js",
            "assets/js/main/articulo/inventario/events.js",
            "assets/js/main/articulo/inventario/load.js",
            "assets/js/main/articulo/venta/charge.js",
            "assets/js/main/articulo/venta/events.js",
            "assets/js/main/articulo/venta/load.js",
            "assets/js/main/articulo/receta/charge.js",
            "assets/js/main/articulo/receta/events.js",
            "assets/js/main/articulo/receta/load.js",
            "assets/js/main/articulo/empaque/charge.js",
            "assets/js/main/articulo/empaque/events.js",
            "assets/js/main/articulo/empaque/load.js",
            "assets/backbone/views.js",
            "assets/backbone/router.js",
            "assets/js/main/ready.js"
        ];

        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE ");
        if (msie > 0) {
            chargeFiles.push("assets/js/libs/PIE.js");
        }

        //chargeFiles.push("assets/js/main/ready.js");
        var total = chargeFiles.length;
        function progress() {
            ++charged;
            document.getElementById("charge_label_number").innerHTML = parseInt((100 / total) * charged) + " %";
        }

        assetsLoader(chargeFiles,
                function (src) {
                    progress();
                    if (src == "assets/js/libs/PIE.js") {
                        $('.rounded').each(function () {
                            PIE.attach(this);
                        });
                    }
                },
                function (a) {
                    //console.log("No fue posible cargar: " + a.src.match(/[^/]*\..*/));
                    errorConnetion("Ha ocurrido un error al cargar los archivos", "Recargar la pagina");
                    completed = true;
                },
                function () {
                    completed = true;
                    ChargeCompleted();
                }
        );

    </script>
</div>


