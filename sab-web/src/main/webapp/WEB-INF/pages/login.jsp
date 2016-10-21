<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Sab Inicia sesion</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="GAMASOFT, WEB-DEVELOPER: JONATHAN ZAMORA R." />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta name="application-name" content="SAB" />

        <!-- Core stylesheets do not remove -->
        <link href="<c:url value='assets/css/bootstrap.css'/>" rel="stylesheet" />
        <link href="<c:url value='assets/css/bootstrap-theme.css'/>" rel="stylesheet" />
        <link href="<c:url value='assets/css/icons.css'/>" rel="stylesheet" />

        <!-- app stylesheets -->
        <link href="<c:url value='assets/css/app.css'/>" rel="stylesheet" />

        <!--[if IE 8]>
        <link href="<c:url value='assets/css/ie8.css'/>" rel="stylesheet" type="text/css" />
        <![endif]-->

        <!-- Custom stylesheets ( Put your own changes here ) -->
        <link href="<c:url value='assets/css/custom.css'/>" rel="stylesheet" />

        <!-- Force IE9 to render in normal mode -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
        <script src="<c:url value='assets/js/respond.min.js'/>"></script>
        <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value='assets/images/ico/apple-touch-icon-144-precomposed.png'/>">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value='assets/images/ico/apple-touch-icon-114-precomposed.png'/>">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value='assets/images/ico/apple-touch-icon-72-precomposed.png'/>">
        <link rel="apple-touch-icon-precomposed" href="<c:url value='assets/images/ico/apple-touch-icon-57-precomposed.png'/>">
        <link rel="shortcut icon" href="<c:url value='assets/images/ico/favicon.ico'/>">


        <link rel="stylesheet" href="<c:url value='assets/css/login.css'/>">
        <link rel="stylesheet" href="<c:url value='assets/css/v_de_validacion.css'/>">
    </head>

    <body>
        <div class="container-fluid">
            <div id="login">
                <div id="gama_div">Gamasoft</div>
                <div class="login-wrapper" data-active="log">

                    <div id="log">
                        <div class="page-header">
                            <h3 class="center">Inicia sesion</h3>
                        </div>
                        <form role="form" method="POST" id="login-form" class="form-horizontal" action="<c:url value='j_spring_security_check'/>">
                            <div class="row">
                                <div class="form-group relative">
                                    <div class="icon"><i class="icon20 i-user"></i></div>
                                    <input id="username" class="form-control valida_correo" required type="text" tabinex="1" required name="j_username" id="user" autofocus="autofocus" placeholder="Correo" value="">
                                </div>
                                <div class="form-group relative">
                                    <div class="icon"><i class="icon20 i-key"></i></div>
                                    <input id="contra" class="form-control" type="password" autocomplete="off" tabinex="2" required name="j_password" id="password" placeholder="Contrase&#241a" value="">
                                </div>
                                <button name="Login" id="btn_login" tabinex="3" id="loginBtn" type="submit" class="btn btn-primary pull-right col-lg-5 btn_fix">Iniciar sesion</button>
                            </div>
                        </form>
                        <c:if test="${not empty error}">
                            <div class="alert alert-error">
                                Fallo en la autenticacion, intente de nuevo.<br />
                                <!-- Caused by: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} -->
                            </div>
                        </c:if>
                    </div>
                    <div id="forgot">
                        <div class="page-header">
                            <h3 class="center">Olvide mi contrase&#241a</h3>
                        </div>
                        <form class="form-horizontal">
                            <div class="row">

                                <div class="form-group relative">
                                    <div class="icon"><i class="icon20 i-envelop-2"></i></div>
                                    <input class="form-control" type="text" name="email" id="email-field" placeholder="Correo">
                                </div><!-- End .control-group  -->
                                <button type="submit" id="btn_pass" class="btn btn-block btn-success btn_fix">Recuperar mi contrase&#241a</button>
                            </div><!-- End .row-fluid  -->
                        </form>
                    </div>
                </div>
                <div id="bar" data-active="log">
                    <div class="btn-group btn-group-vertical">
                        <a id="log" href="#" class="btn tipR" title="Login"><i class="icon16 i-key"></i></a>
                        <a id="forgot" href="#" class="btn tipR" title="Forgout password"><i class="icon16 i-question"></i></a>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div id="scripts">
            <!-- Le javascript
       ================================================== -->
            <!-- Important plugins put in all pages -->
            <script src="<c:url value='assets/js/jquery.js'/>"></script>
            <script src="<c:url value='assets/js/bootstrap.js'/>"></script>
            <script src="<c:url value='assets/js/conditionizr.min.js'/>"></script>
            <script src="<c:url value='assets/js/jRespond.min.js'/>"></script>

            <!-- Init plugins -->
            <script src="<c:url value='assets/js/domready.js'/>"></script><!-- Init plugins only for page -->

            <script src="<c:url value='assets/js/v_de_validacion.js'/>"></script>
            <script src="<c:url value='assets/js/ready.js'/>"></script>
        </div>
    </body>
</html>