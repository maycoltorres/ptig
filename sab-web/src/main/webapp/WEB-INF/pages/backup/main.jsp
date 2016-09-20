<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head profile="http://www.w3.org/2005/10/profile">
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Admin - gama</title>
        <!-- Browser Tab and Favorites icon -->
        <link rel="shortcut icon" href="images/ico/favicon.png">
        <jsp:include page="generic-css-includes.jsp" />
        <jsp:include page="generic-js-includes.jsp" />
    </head>
    <body>
            <div id="background_opacity">
            </div>
            <div id="prompt">
                <div>
                    <span class="prompt_message aditional_text"></span></br>
                </div>
            </div>
            <div id="showErrorGamaDiv">
                <div id="enmarcador">
                    <label class="error_gama">Gamasoft</label>
                    <img class="error_internet" src="<c:url value='assets/images/web.png'/>"/>
                    <hr>
                    <span class="error_gama" >No hay conexion con el servidor de Gamasoft</span>
                    <span class="error_internet" >No hay conexion a Internet</span>
                    <div>
                        <button onClick="window.location.reload()">Reintentar</button>
                    </div>
                </div>
            </div>
            <div id="content_ligth" class="box_show">
                <div class="progress_charge"></div>
                <label id="gama_label">Gamasoft</label>
                <label id="charge_label">Cargando</label>
                <span id="charge_span"></span>
                <span id="charge_gama"></span>
                <div id="background_charge_gama"></div>
            </div>
            <div id="content_background" class="box_show"></div>
        <!-- Menu -->
        <jsp:include page="general/bar_top.jsp"/>
        <!-- Main page -->
         <div id="message_div">
            <div id="result_positive" class="result_action btn-success">
                <div class="margint5">
                    <i class="icon32   i-checkmark-3 position_icon_show"></i>
                    <span class="message_result"></span>
                </div>
                <i class="icon32 i-close pull-right i-right_1 close_it"></i>
            </div>
            <div id="result_info" class="result_action btn-info">
                <div class="margint5">
                    <i class="icon32   i-info position_icon_show"></i>
                    <span class="message_result"></span>
                </div>
                <i class="icon32 i-close pull-right i-right_1 close_it"></i>
            </div>
            <div id="result_error" class="result_action btn-danger">
                <div class="margint5">
                    <i class="icon32  i-warning position_icon_show "></i>
                    <span class="message_result"></span>
                </div>
                <i class="icon32 i-close pull-right i-right_1 close_it"></i>
            </div>
        </div>
        <div class="main">
        	<aside id="sidebar" class="isCollapse">
        		<jsp:include page="side_bar/menu.jsp"/>
        	</aside>
        	<section id="content" class="isCollapse">
                <div id="sabMain">

                </div>
		        <!-- View templates -->
				<jsp:include page="templates/grupos-template.jsp"/>
				<jsp:include page="templates/gruposinactivos-template.jsp"/>
				<jsp:include page="templates/grupocliente-template.jsp"/>
		        <jsp:include page="templates/clientes-template.jsp"/>
		        <jsp:include page="templates/crearcliente-template.jsp"/>
		        <jsp:include page="templates/modificarcliente-template.jsp"/>
		        <jsp:include page="templates/puntos-template.jsp"/>
		        <jsp:include page="templates/filter-templates.jsp" />
		        <jsp:include page="templates/clientesinactivos-template.jsp" />
		        <jsp:include page="templates/puntosinactivos-template.jsp" />
		        <jsp:include page="templates/creargrupo-template.jsp"/>
		        <jsp:include page="templates/crearmarca-template.jsp"/>
		        <jsp:include page="templates/marcasinactivas-template.jsp"/>
				<jsp:include page="templates/marcas-template.jsp"/>
                <jsp:include page="templates/usuarios.jsp"/>
                <jsp:include page="templates/roles.jsp"/>
                <jsp:include page="templates/permisos.jsp"/>
                <jsp:include page="templates/articulos.jsp"/>
			</section>
		</div>
        <!-- footer -->
        <div id="footer-height">
        </div>
        <div id="footer">
            <footer>
                <p class="container-fluid pull-left">Copyright Gamasoft 2014</p>
            </footer>
        </div>
        <!-- end footer -->
        <script type="text/javascript">

        </script>
        <jsp:include page="js/basic.js.jsp"/>
        <script type="text/javascript" src="<c:url value='assets/js/main/documento_cargado.js'/>"></script>
    </body>
</html>
