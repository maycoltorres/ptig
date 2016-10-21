<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html ng-app='angularRoutingApp'>  
    <head>  
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>AppPtig</title>
     
        <!-- Bootstrap Core CSS -->

        <link href="<c:url value='assets/css/bootstrap.min.css'/>" rel="stylesheet" />

        <link href="<c:url value='assets/css/metisMenu.min.css'/>" rel="stylesheet" />
        <!-- Custom CSS -->

        <link href="<c:url value='assets/css/sb-admin-2.css'/>" rel="stylesheet" />

        <!-- Custom Fonts -->

        <link href="<c:url value='assets/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet" />
        <link href="<c:url value='assets/css/style.css'/>" rel="stylesheet" />
           
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>  
    <body>  

        <!--*****************************************************************************************************************************************************************************************************-->


        <div id="wrapper">

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>                    

                    <a class="navbar-brand" href="main.html">GamaSoft Versión de Prueba</a>
                </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>Módulo de Compras</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>Módulo Reportes</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>Módulo Otro</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Read All Messages</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                       
                    </li>
                    
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-tasks">
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 1</strong>
                                            <span class="pull-right text-muted">40% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                <span class="sr-only">40% Complete (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 2</strong>
                                            <span class="pull-right text-muted">20% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                                <span class="sr-only">20% Complete</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 3</strong>
                                            <span class="pull-right text-muted">60% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                <span class="sr-only">60% Complete (warning)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 4</strong>
                                            <span class="pull-right text-muted">80% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                                <span class="sr-only">80% Complete (danger)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Tasks</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                   
                    </li>
                   
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-comment fa-fw"></i> New Comment
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> Message Sent
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-tasks fa-fw"></i> New Task
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Alerts</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-alerts -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                            </li>
                        </ul>
                       
                    </li>
                  
                </ul>
                

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li class="sidebar-search">
                                <div class="input-group custom-search-form">
                                    <input type="text" class="form-control" placeholder="Search...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- /input-group -->
                            </li>
                            <li>
                                <a href="#/home"><i class="fa fa-fw fa-home"></i> Inicio</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user-md"></i> Clientes<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/crearclientes">Crear Clientes</a>
                                    </li>
                                    <li>
                                        <a href="#/clientes">Lista de Clientes Activos</a>
                                    </li>
                                    <li>
                                        <a href="#/listaclientesinactivos">Lista de Clientes Inactivos</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-group"></i> Grupos Corporativos<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/creargrupocorporativo">Crear Grupos Corporativos</a>
                                    </li>
                                    <li>
                                        <a href="#/gruposcorporativos">Lista de Grupos Corporativos Activos</a>
                                    </li>
                                    <li>
                                        <a href="#/listagruposcorporativosinactivos">Lista de Grupos Corporativos Inactivos</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-paperclip"></i> Marcas<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/crearmarca">Crear Marcas</a>
                                    </li>
                                    <li>
                                        <a href="#/marcas">Lista de Marcas Activas</a>
                                    </li>
                                    <li>
                                        <a href="#/listamarcasinactivas">Lista de Marcas Inactivas</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-user"></i> Usuarios<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/crearusuario">Crear Usuarios</a>
                                    </li>
                                    <li>
                                        <a href="#/usuarios">Lista de Usuarios Activos</a>
                                    </li>
                                    <li>
                                        <a href="#/listausuariosinactivos">Lista Usuarios Inactivos</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-unlock"></i> Roles<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/crearrol">Crear Roles</a>
                                    </li>
                                    <li>
                                        <a href="#/roles">Lista de Roles Activos</a>
                                    </li>
                                    <li>
                                        <a href="#/listarolesinactivos">Lista de Roles Inactivos</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-archive"></i> Articulos<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/creararticulo">Crear Articulos</a>
                                    </li>
                                    <li>
                                        <a href="#/articulos">Lista de Articulos Activos</a>
                                    </li>
                                    <li>
                                        <a href="#/listaarticulosinactivos">Lista de Articulos Inactivos</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-check"></i> Grupos de Selección<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/creargruposdeseleccion">Crear Grupos de Selección</a>
                                    </li>
                                    <li>
                                        <a href="#/gruposdeseleccion">Lista de Grupos de Selección Activos</a>
                                    </li>
                                    <li>
                                        <a href="#/listagruposeleccioninactivos">Lista de Grupos de Selección Inactivos</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-dollar"></i> Lista de Precios<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/crearlistaprecios">Crear Lista de Precios</a>
                                    </li>
                                    <li>
                                        <a href="#/listaprecios">Lista de Precios Activas</a>
                                    </li>
                                    <li>
                                        <a href="#/listapreciosinactivos">Lista de Precios Inactivas</a>
                                    </li>
                                </ul>
                                <!-- /.nav-second-level -->
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-fw fa-list"></i> Clases<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li>
                                        <a href="#/crearclases">Crear Clases</a>
                                    </li>
                                    <li>
                                        <a href="#/clases">Lista de Clases Activas</a>
                                    </li>
                                    <li>
                                        <a href="#/listaclasesinactivas">Lista de Clases Inactivas</a>
                                    </li>
                                </ul>
                                
                            </li>

                        </ul>
                    </div>
                   
                </div>
               
            </nav>



            <div class="container-fluid">

             
                <div class="row">

                   <div ui-view></div>
                   
                </div>

            </div>
        


         

        </div>
        
         
   
        <!--******************************************************************************************************************************************************************************************************-->
        <!-- jQuery -->

        <script src="<c:url value='assets/js/jquery.js'/>"></script>

        <!-- Bootstrap Core JavaScript -->

        <script src="<c:url value='assets/js/bootstrap.min.js'/>"></script>



        <script src="<c:url value='assets/js/angular.min.js'/>"></script>

       <!-- <script src="https://code.angularjs.org/1.5.8/angular-route.min.js"></script>-->
        <script src="assets/js/angular-route.min.js"></script>
        <!--<script src="https://code.angularjs.org/1.5.8/angular-resource.min.js"></script>-->
        <script src="assets/js/angular-resource.min.js"></script>
        <script src="<c:url value='assets/routes/main.js'/>"></script>
        <script src="<c:url value='assets/routes/controllers.js'/>"></script>
        <script src="assets/js/metisMenu.min.js"></script>
        <script src="assets/js/sb-admin-2.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.10/angular-ui-router.min.js"></script>

        <script src="<c:url value='https://code.angularjs.org/1.5.8/angular-animate.js'/>"></script>
        <script src="<c:url value='https://code.angularjs.org/1.5.8/angular-sanitize.js'/>"></script>
        <script src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.2.0/ui-bootstrap.js'/>"></script>
        
<script src="https://rawgit.com/dwmkerr/angular-modal-service/master/dst/angular-modal-service.js"></script>
        

 
    </body>
</html>