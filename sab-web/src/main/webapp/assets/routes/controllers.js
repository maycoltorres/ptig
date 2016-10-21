// Creación del módulo
var angularRoutingApp = angular.module('angularRoutingApp');

//**********************************pruebas para la ventana modal solo para inactivar elementos*********************************************************

angularRoutingApp.controller('Controller', function($scope, ModalService, $http) {

     $scope.show = function(id,ruta, accion) {
        console.log("Valor de la accion es  "+accion);
        console.log("Valor final ruta "+ruta+id);
        ModalService.showModal({
            templateUrl: 'modal.html',
            controller: "ModalController"
        }).then(function(modal) {
            modal.element.modal();
            modal.close.then(function(result) {
                if(result==="Yes" && accion==="inactivar"){
                    $http.delete(ruta+id);
                    location.reload();
                }if(result==="Yes" && accion==="activar"){
                    $http.put(ruta+id);
                    location.reload();
                }
                else{
                   $scope.message = ""; 
                }
                
            });
        });
    };
    
    
    
});

angularRoutingApp.controller('ModalController', function($scope, close, $http) {
  
    $scope.posts = [];
    $http.get("./rest/cliente/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
 $scope.close = function(result) {
 	close(result, 500); // close, but give 500ms for bootstrap to animate
 };

});


//**********************************fin pruebas********************************************************************************************

// Controlador para las rutas
angularRoutingApp.controller('mainController', function ($scope) {

});
//****************************Controladores para procesar información de Clientes**********************************************************    
angularRoutingApp.controller('clientesController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/cliente/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });

});

angularRoutingApp.controller('buscarclienteController', function ($scope, $http, $stateParams) {

    $scope.posts = [];
    $http.get("./rest/cliente/busqueda/"+$stateParams.nombre)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;


            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });

});

angularRoutingApp.controller('listaclientesinactivosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/cliente/inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});


angularRoutingApp.controller('activarclienteController', function ($scope, $http, $stateParams) {

    $scope.posts = [];
    $http.put("./rest/cliente/activar/"+$stateParams.id)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
                alert("******************creo que lo activó*************************** ");
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
    

});

//****************************Fin Controladores para procesar información de Clientes*******************************************************

//****************************Controladores para procesar información de Grupos Corporativos************************************************  
angularRoutingApp.controller('gruposcorporativosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/grupo/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('listagruposcorporativosinactivosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/grupo/inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('creargrupocorporativoController', function ($scope, $http) {
    $scope.posts = [];
    $scope.formData = {};
    $http.get("./rest/cliente/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
            $scope.processFormGrupo = function() {
     
        console.log($scope.formData);
    };
});

angularRoutingApp.controller('buscargrupoController', function ($scope, $http, $stateParams) {
    $scope.posts = [];
    $http.get("./rest/grupo/busqueda/"+$stateParams.nombre)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;

            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
        console.log(err);
            });
});


//****************************Fin Controladores para procesar información de Grupos Corporativos*******************************************************


//****************************Controladores para procesar información de Marcas************************************************************************  
angularRoutingApp.controller('marcasController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/marca/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('crearmarcaController', function ($scope, $http) {
    $scope.posts = [];
    $scope.formData = {};
    $http.get("./rest/cliente/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
            $scope.processFormMarca = function() {

        console.log($scope.formData);
    };
});

angularRoutingApp.controller('listamarcasinactivasController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/marca/inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('buscarmarcaController', function ($scope, $http, $stateParams) {
    
    $scope.posts = [];
    $http.get("./rest/marca/busqueda/"+$stateParams.nombre)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});



//****************************Fin Controladores para procesar información de Marcas********************************************************************


//****************************Controladores para procesar información de Usuarios*********************************************************************** 
angularRoutingApp.controller('usuariosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/user/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('listausuariosinactivosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/user/inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});



//****************************Fin Controladores para procesar información de Usuarios********************************************************************


//****************************Controladores para procesar información de Roles***************************************************************************
angularRoutingApp.controller('rolesController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/rol/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('crearrolController', function ($scope, $http) {
    $scope.posts = [];
    $scope.formData = {};
    $http.get("./rest/rolcliente/transacciones/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
            $scope.processFormRol = function() {
      
        console.log($scope.formData);
    };
});
angularRoutingApp.controller('listarolesinactivosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/rol/inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});
angularRoutingApp.controller('buscarrolController', function ($scope, $http, $routeParams) {
    $scope.posts = [];
    $http.get("./rest/rol/busqueda/"+$routeParams.nombre)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});



//****************************Fin Controladores para procesar información de Roles***********************************************************************


//****************************Controladores para procesar información de Articulos***********************************************************************
angularRoutingApp.controller('articulosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/articulo/?mostrar=activos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('creararticuloController', function ($scope, $http) {
    $scope.message = 'Esta es la pagina de creararticuloController';
});

angularRoutingApp.controller('listaarticulosinactivosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/articulo/inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('inactivararticuloController', function ($scope, $http, $stateParams) {

    $scope.posts = [];
    $http.delete("./rest/articulo/"+$stateParams.id+"/inactivar")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
                alert("******************creo que lo inactivó*************************** ");
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
    

});

angularRoutingApp.controller('activararticuloController', function ($scope, $http, $stateParams) {

    $scope.posts = [];
    $http.put("./rest/articulo/"+$stateParams.id+"/activar")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
                alert("******************creo que lo inactivó*************************** ");
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
    

});

//****************************Fin Controladores para procesar información de Articulos*******************************************************************

//****************************Controladores para procesar información de Grupos de Selección*************************************************************
angularRoutingApp.controller('gruposdeseleccionController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/grupoSeleccion/?mostrar=activos")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('listagruposeleccioninactivosController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/grupoSeleccion/?mostrar=inactivos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('creargruposdeseleccionController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/articulo/?mostrar=activos/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('buscarseleccionController', function ($scope, $http, $routeParams) {
    $scope.posts = [];
    $http.get("./rest/seleccion/busqueda/"+$routeParams.nombre)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});
//****************************Fin Controladores para procesar información de Grupos de Selección*********************************************************

//****************************Controladores para procesar información de Lista de Precios****************************************************************
angularRoutingApp.controller('listapreciosController', function ($scope, $http) {
    $scope.posts = [];
    var id=1;
    $http.get("./rest/cliente/"+id+"/listasPrecios")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('listapreciosinactivosController', function ($scope, $http) {
    $scope.posts = [];
    var id=1;
    $http.get("./rest/cliente/"+id+"/listasPrecios?mostrar=inactivos")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('crearlistapreciosController', function ($scope, $http) {
    $scope.formData = {};
    $scope.processFormListaPrecios = function() {
        console.log("enviando datos ");
    console.log($scope.formData);
    // ////////////////////////////Acuerdate de quitar el 1/////////////////////////////////////////////
    $http.post("./rest/cliente/"+1+"/listasPrecios",$scope.formData)
            .success(function (data){
              console.log("envio los datos");  
            }).error(function(data){
                console.log("Se quedo en el camino"); 
            }); 
        
    };
});
//****************************Fin Controladores para procesar información de Listas de Precios***********************************************************

//****************************Controladores para procesar información de Listas de Clases****************************************************************
angularRoutingApp.controller('clasesController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/cliente/clase/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});

angularRoutingApp.controller('listaclasesinactivasController', function ($scope, $http) {
    $scope.posts = [];
    $http.get("./rest/cliente/clase/?mostrar=inactivos")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
});


angularRoutingApp.controller('crearclasesController', function ($scope,$http) {
    $scope.formData = {};
    $scope.processFormClases = function() {
        
       console.log("enviando datos ");
    console.log($scope.formData);

    $http.post("./rest/cliente/clase",$scope.formData)
            .success(function (data){
              console.log("envio los datos");  
            }).error(function(data){
                console.log("Se quedo en el camino"); 
            }); 
    };
});
//****************************Fin Controladores para procesar información de Listas de Clases***********************************************************

//****************************Controladores para procesar información de Puntos*************************************************************************

angularRoutingApp.controller('puntosController', function ($scope, $resource) {
    Post = $resource("http://localhost/json/prueba.json");
    $scope.posts = Post.query();
});
angularRoutingApp.controller('listapuntosinactivosController', function ($scope, $resource) {
    Post = $resource("http://localhost/json/prueba.json");
    $scope.posts = Post.query();
});
angularRoutingApp.controller('crearpuntoController', function ($scope, $resource) {
    Post = $resource("http://localhost/json/prueba.json");
    $scope.posts = Post.query();
});

angularRoutingApp.controller('buscarpuntosclienteController', function ($scope, $http, $stateParams) {

    $scope.posts = [];
    $http.get("./rest/punto/?idCliente="+$stateParams.id)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;


            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });

});

angularRoutingApp.controller('buscarpuntosespecificosController', function ($scope, $http, $routeParams) {

    $scope.posts = [];
    $http.get("./rest/punto/busqueda/"+$routeParams.nombre)
            .success(function (data) {

                console.log(data);
                $scope.posts = data;


            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
        console.log(err);
            });

});

// our controller for the form
// =============================================================================
angularRoutingApp.controller('formclienteController', function($scope,$http) {

    $scope.formData = {};
    
    
    $scope.processFormCliente = function() {
        
    console.log("enviando datos");
    console.log($scope.formData);
    $http.get("./rest/cliente/",$scope.formData)
            .success(function (data){
              console.log("envio los datos");  
            }).error(function(data){
                console.log("Se quedo en el camino"); 
            });       
    };
    
});

angularRoutingApp.controller('formusuarioController', function($scope, $http) {
    
     $scope.posts = [];
    $http.get("./rest/cliente/")
            .success(function (data) {

                console.log(data);
                $scope.posts = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
            
            $scope.roles = [];
    $http.get("./rest/rol/")
            .success(function (data) {

                console.log(data);
                $scope.roles = data;
            })
            .error(function (err) {

                alert("Ha fallado la petición HTTP " + err);
            });
            
            
    $scope.formData = {};
    
    
    $scope.processFormUsuario = function() {
        alert('Buscar ruta para guardar usuario!');
        console.log($scope.formData);
    };
    
});

angularRoutingApp.controller('formarticuloController', function($scope) {
    
    
    $scope.formData = {};
    
   
    $scope.processFormArticulo = function() {
        alert('Buscar ruta para guardar articulo!');
        console.log($scope.formData);
    };

});


















