//***************************Creación del módulo**********************************************************************************
var angularRoutingApp = angular.module('angularRoutingApp', ['ngRoute', 'ngAnimate', 'ui.router', 'angularModalService', 'ngSanitize', 'ui.bootstrap']);



// =============================================================================

angularRoutingApp.config(function ($stateProvider, $urlRouterProvider) {

    $stateProvider

            .state('/', {
                url: '/',
                templateUrl: 'assets/pages/home.html',
                controller: 'mainController'
            })
//****************************Rutas para procesar información de Clientes**********************************************************           
            .state('/clientes', {
                url: '/clientes',
                templateUrl: 'assets/pages/clientes.html',
                controller: 'clientesController'
            })

            .state('/listaclientesinactivos', {
                url: '/listaclientesinactivos',
                templateUrl: 'assets/pages/listaclientesinactivos.html',
                controller: 'listaclientesinactivosController'
            })

            .state('buscarcliente', {
                url: '/buscarcliente/:nombre',
                templateUrl: 'assets/pages/datoscliente.html',
                controller: 'buscarclienteController'
            })

            .state('crearclientes', {
                url: '/crearclientes',
                templateUrl: 'assets/pages/crearclientes.html',
                controller: 'formclienteController'
            })


            .state('crearclientes.datosbasicos', {
                url: '/datosbasicos',
                templateUrl: 'assets/pages/form-datosbasicos.html'
            })


            .state('crearclientes.ajustescliente', {
                url: '/ajustescliente',
                templateUrl: 'assets/pages/form-ajustescliente.html'
            })


            .state('crearclientes.canalesimp', {
                url: '/canalesimp',
                templateUrl: 'assets/pages/form-canalesimp.html'
            })


//****************************Fin de las rutas para procesar información de Clientes***************************************************  


//****************************Rutas para procesar información de Grupos Corporativos***************************************************  
            .state('gruposcorporativos', {
                url: '/gruposcorporativos',
                templateUrl: 'assets/pages/gruposcorporativos.html',
                controller: 'gruposcorporativosController'
            })

            .state('listagruposcorporativosinactivos', {
                url: '/listagruposcorporativosinactivos',
                templateUrl: 'assets/pages/listagruposcorporativosinactivos.html',
                controller: 'listagruposcorporativosinactivosController'
            })

            .state('/creargrupocorporativo', {
                url: '/creargrupocorporativo',
                templateUrl: 'assets/pages/creargrupocorporativo.html',
                controller: 'creargrupocorporativoController'
            })

            .state('buscargrupo', {
                url: '/buscargrupo/:nombre',
                templateUrl: 'assets/pages/datosgrupo.html',
                controller: 'buscargrupoController'
            })
//****************************Fin de las rutas para procesar información de Grupos Corporativos****************************************  

            //****************************Rutas para procesar información de Marcas****************************************************************   

            .state('/marcas', {
                url: '/marcas',
                templateUrl: 'assets/pages/marcas.html',
                controller: 'marcasController'
            })

            .state('/crearmarca', {
                url: '/crearmarca',
                templateUrl: 'assets/pages/crearmarca.html',
                controller: 'crearmarcaController'
            })

            .state('/listamarcasinactivas', {
                url: '/listamarcasinactivas',
                templateUrl: 'assets/pages/listamarcasinactivas.html',
                controller: 'listamarcasinactivasController'
            })

            .state('buscarmarca', {
                url: '/buscarmarca/:nombre',
                templateUrl: 'assets/pages/datosmarca.html',
                controller: 'buscarmarcaController'
            })

//****************************Fin de las rutas para procesar información de Marcas***************************************************** 

//****************************Rutas para procesar información de Usuarios**************************************************************   
            .state('/usuarios', {
                url: '/usuarios',
                templateUrl: 'assets/pages/usuarios.html',
                controller: 'usuariosController'
            })

            .state('/listausuariosinactivos', {
                url: '/listausuariosinactivos',
                templateUrl: 'assets/pages/listausuariosinactivos.html',
                controller: 'listausuariosinactivosController'
            })

            .state('crearusuario', {
                url: '/crearusuario',
                templateUrl: 'assets/pages/crearusuario.html',
                controller: 'formusuarioController'
            })

            .state('crearusuario.datosbasicosusuarios', {
                url: '/datosbasicosusuarios',
                templateUrl: 'assets/pages/form-datosbasicos-usuario.html'
            })

            .state('crearusuario.clienteusuario', {
                url: '/clienteusuario',
                templateUrl: 'assets/pages/form-cliente-usuario.html'
            })

            .state('crearusuario.rolesusuario', {
                url: '/rolesusuario',
                templateUrl: 'assets/pages/form-roles-usuario.html'
            })

            .state('crearusuario.ajustesusuario', {
                url: '/ajustesusuario',
                templateUrl: 'assets/pages/form-ajustes-usuario.html'
            })
//****************************Fin de las rutas para procesar información de Usuarios****************************************************

//****************************Rutas para procesar información de Roles******************************************************************
            .state('/roles', {
                url: '/roles',
                templateUrl: 'assets/pages/roles.html',
                controller: 'rolesController'
            })

            .state('/crearrol', {
                url: '/crearrol',
                templateUrl: 'assets/pages/crearrol.html',
                controller: 'crearrolController'
            })

            .state('/listarolesinactivos', {
                url: '/listarolesinactivos',
                templateUrl: 'assets/pages/listarolesinactivos.html',
                controller: 'listarolesinactivosController'
            })

            .state('/buscarrol/:nombre', {
                url: '/buscarrol/:nombre',
                templateUrl: 'assets/pages/datosrol.html',
                controller: 'buscarrolController'
            })
            

//****************************Fin de las rutas para procesar información de Roles********************************************************  

//****************************Rutas para procesar información de Articulos***************************************************************
            .state('/articulos', {
                url: '/articulos',
                templateUrl: 'assets/pages/articulos.html',
                controller: 'articulosController'
            })

            .state('/listaarticulosinactivos', {
                url: '/listaarticulosinactivos',
                templateUrl: 'assets/pages/listaarticulosinactivos.html',
                controller: 'listaarticulosinactivosController'
            })

            .state('creararticulo', {
                url: '/creararticulo',
                templateUrl: 'assets/pages/creararticulo.html',
                controller: 'formarticuloController'
            })

            .state('creararticulo.datosbasicosarticulo', {
                url: '/datosbasicosarticulo',
                templateUrl: 'assets/pages/form-datosbasicos-articulo.html'
            })

            .state('creararticulo.inventarioarticulo', {
                url: '/inventarioarticulo',
                templateUrl: 'assets/pages/form-inventario-articulo.html'
            })

            .state('creararticulo.ventaarticulo', {
                url: '/ventaarticulo',
                templateUrl: 'assets/pages/form-venta-articulo.html'
            })

            .state('creararticulo.recetaarticulo', {
                url: '/recetaarticulo',
                templateUrl: 'assets/pages/form-receta-articulo.html'
            })

            .state('creararticulo.empaquearticulo', {
                url: '/empaquearticulo',
                templateUrl: 'assets/pages/form-empaque-articulo.html'
            })



//****************************Fin de las rutas para procesar información de Articulos**************************************************** 


//****************************Rutas para procesar información de Grupos de Selección*****************************************************
            .state('/gruposdeseleccion', {
                url: '/gruposdeseleccion',
                templateUrl: 'assets/pages/gruposdeseleccion.html',
                controller: 'gruposdeseleccionController'
            })

            .state('/listagruposeleccioninactivos', {
                url: '/listagruposeleccioninactivos',
                templateUrl: 'assets/pages/listagruposeleccioninactivos.html',
                controller: 'listagruposeleccioninactivosController'
            })

            .state('/creargruposdeseleccion', {
                url: '/creargruposdeseleccion',
                templateUrl: 'assets/pages/creargruposdeseleccion.html',
                controller: 'creargruposdeseleccionController'
            })

            .state('/buscarseleccion/:nombre', {
                url: '/buscarseleccion/:nombre',
                templateUrl: 'assets/pages/datosseleccion.html',
                controller: 'buscarseleccionController'
            })

//****************************Fin de las rutas para procesar información de Grupos de Selección*******************************************


//****************************Rutas para procesar información de Lista de Precios*********************************************************
            .state('/listaprecios', {
                url: '/listaprecios',
                templateUrl: 'assets/pages/listaprecios.html',
                controller: 'listapreciosController'
            })

            .state('/crearlistaprecios', {
                url: '/crearlistaprecios:id',
                templateUrl: 'assets/pages/crearlistaprecios.html',
                controller: 'crearlistapreciosController'
            })

            .state('/listapreciosinactivos', {
                url: '/listapreciosinactivos',
                templateUrl: 'assets/pages/listapreciosinactivos.html',
                controller: 'listapreciosinactivosController'
            })

//****************************Fin de las rutas para procesar información de Lista de Precios**********************************************


//****************************Rutas para procesar información de Lista de Clases*********************************************************
            .state('/clases', {
                url: '/clases',
                templateUrl: 'assets/pages/clases.html',
                controller: 'clasesController'
            })

            .state('/listaclasesinactivas', {
                url: '/listaclasesinactivas',
                templateUrl: 'assets/pages/listaclasesinactivas.html',
                controller: 'listaclasesinactivasController'
            })

            .state('/crearclases', {
                url: '/crearclases',
                templateUrl: 'assets/pages/crearclases.html',
                controller: 'crearclasesController'
            })

//****************************Fin de las rutas para procesar información de Lista de Clases**********************************************		


//****************************Rutas para procesar información de Puntos******************************************************************    
            .state('/puntos', {
                url: '/puntos',
                templateUrl: 'assets/pages/puntos.html',
                controller: 'puntosController'
            })
            .state('/listapuntosinactivos', {
                url: '/listapuntosinactivos',
                templateUrl: 'pages/listapuntosinactivos.html',
                controller: 'listapuntosinactivosController'
            })
            .state('/crearpunto', {
                url: '/crearpunto',
                templateUrl: 'assets/pages/crearpunto.html',
                controller: 'crearpuntoController'
            })
            .state('buscarpuntoscliente', {
                url: '/buscarpuntoscliente/:id',
                templateUrl: 'assets/pages/puntoscliente.html',
                controller: 'buscarpuntosclienteController'
            })
            .state('buscarpuntoespecifico', {
                url: '/buscarpuntoespecifico/:nombre',
                templateUrl: 'assets/pages/puntosespecificos.html',
                controller: 'buscarpuntosespecificosController'
            });

    $urlRouterProvider.otherwise('/');
});







