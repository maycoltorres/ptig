<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Sab :: Login Page</title>
        <link rel="shortcut icon" href="images/ico/favicon.png">
        <!-- CSS gama-->
        <link rel="stylesheet" type="text/css" href="<c:url value='assets/css/v_de_validacion.css'/>" >
        <!-- template -->
        <script type="text/javascript">
	        //adding load class to body and hide page only if plugin is active
	        document.documentElement.className += 'loading';
	    </script>
	     <!-- Headings -->
	    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,800,700' rel='stylesheet' type='text/css'>
	    <!-- Text -->
	    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css' />
	 
	    <!-- Core stylesheets do not remove -->
	    <link href="<c:url value='assets/css/bootstrap/bootstrap.css'/>" rel="stylesheet" />
	    <link href="<c:url value='assets/css/bootstrap-responsive.css'/>" rel="stylesheet" />
	    <link href="<c:url value='assets/css/icons.css'/>" rel="stylesheet" />
	 
	    <!-- Plugins stylesheets -->
	    <link href="<c:url value='assets/js/plugins/core/perfect-scrollbar/perfect-scrollbar.css'/>" rel="stylesheet" />
	    <link href="<c:url value='assets/js/plugins/forms/uniform/uniform.default.css'/>" rel="stylesheet" />
	 
	    <!-- app stylesheets -->
	    <link href="<c:url value='assets/css/app.css'/>" rel="stylesheet" />
	 
	    <!-- Custom stylesheets ( Put your own changes here ) -->
	    <link href="<c:url value='assets/css/custom.css'/>" rel="stylesheet" />
	 
	    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	    <!--[if lt IE 9]>
	      </script><script src="<c:url value='assets/js/html5shiv.js'/>"></script></script>
	    <![endif]-->
	 
	    <!-- Fav and touch icons 
	    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<c:url value='assets/images/ico/apple-touch-icon-144-precomposed.png'/>">
	    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<c:url value='assets/images/ico/apple-touch-icon-114-precomposed.png'/>">
	    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<c:url value='assets/images/ico/apple-touch-icon-72-precomposed.png'/>">
	    <link rel="apple-touch-icon-precomposed" href="<c:url value='assets/images/ico/apple-touch-icon-57-precomposed.png'/>">
	    -->
        <style>
            #username{
                text-transform:lowercase;
            }
        </style>
    </head>

    <body>
        <div class="container-fluid">
            <div id="login">
                <div class="login-wrapper" data-active="log">
                   <a class="navbar-brand" href="http://www.gamasoftcol.com"><img src="<c:url value='assets/images/gamasoft_logo3.png'/>" alt="logo de gamasoft" class="img-responsive"></a>
                    <div id="log">
                        <!--
                            <div id="avatar">
                                <img src="images/avatars/suggebig.jpg" alt="SuggeElson" class="img-responsive">
                            </div>
                        -->
                        <div class="page-header">

                        	<h3 class="center">Inicie sesion</h3>
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
                                <div class="form-group relative">
                                	 <!--
	                                    <label class="control-label">
	                                        <div class="checker"><span><input type="checkbox" value="1" name="remember"></span></div>
	                                        Remember me ?
	                                    </label>
                                     -->
                                    <button name="Login" tabinex="3" id="loginBtn" type="submit" class="btn btn-primary pull-right col-lg-5">Iniciar sesion</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <c:if test="${not empty error}">
                        <div class="alert alert-error">
                        Fallo en la autenticacion, intente de nuevo.<br />
                        <!-- Caused by: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} -->
                        </div>
                    </c:if>
                    <div id="forgot">
                    	<c:if test="${not empty error}">
                            <div class="alert alert-error">
                           	Fallo en la autenticacion, intente de nuevo.<br />
                            <!-- Caused by: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} -->
                            </div>
                        </c:if>
                        <div class="page-header">
                            <h3 class="center">Olvide mi contrase&#241a</h3>
                        </div>
                        <form class="form-horizontal" method="post" action="#">
                            <div class="row">
                                <div class="form-group relative">
                                    <div class="icon"><i class="icon20 i-envelop-2"></i></div>
                                    <input class="form-control valida_correo" required type="text" name="email" tabinex="1" id="email-field" placeholder="Correo">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-lg btn-block btn-success pull-right">Recuperar contrase&#241a</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div id="bar" data-active="log">
                    <div class="btn-group btn-group-vertical">
                        <a id="log" href="#" class="btn tipR" title="" data-original-title="Login"><i class="icon16 i-key"></i></a>
                        <a id="forgot" href="#" class="btn tipR" title="" data-original-title="Forgout password"><i class="icon16 i-question"></i></a>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
         </div>

        <div id="javascripts">
            <script src="<c:url value='assets/js/libs/jquery-1.11.1.min.js'/>"/></script>
		    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
            <script type="text/javascript" src="<c:url value='assets/js/login/documento_cargado.js'/>"></script>
            <script type="text/javascript" src="<c:url value='assets/js/libs/v_de_validacion.js'/>"></script>
            <script type="text/javascript" src="<c:url value='assets/js/login.js'/>"></script>
            <!-- Important plugins put in all pages -->
		    <script src="<c:url value='assets/js/bootstrap/bootstrap.js'/>"></script>
		    <script src="<c:url value='assets/js/conditionizr.min.js'/>"></script>
		    <script src="<c:url value='assets/js/plugins/core/nicescroll/jquery.nicescroll.min.js'/>"></script>
		    <script src="<c:url value='assets/js/plugins/core/jrespond/jRespond.min.js'/>"></script>
		    <script src="<c:url value='assets/js/jquery.genyxAdmin.js'/>"></script>

		    <!-- Form plugins -->
		    <script src="<c:url value='assets/js/plugins/forms/uniform/jquery.uniform.min.js'/>"></script>
		 
		    <!-- Init plugins -->
		    <script src="<c:url value='assets/js/app.js'/>"></script><!-- Core js functions -->
		    <script src="<c:url value='assets/js/pages/domready.js'/>"></script><!-- Init plugins only for page -->
        </div>
    </body>
</html>
