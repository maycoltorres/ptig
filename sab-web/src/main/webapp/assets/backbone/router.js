var rutasDisponibles= {
    clientes: {instacia: "cliente", coleccion: "clientes"},
    puntos: {instacia: "punto", coleccion: "puntos"},
    roles: {instacia: "rol", coleccion: "roles"},
    roles_cliente: {instacia: "rol_cliente", coleccion: "roles_cliente"},
    grupos: {instacia: "grupo", coleccion: "grupos"},
    marcas: {instacia: "marca", coleccion: "marcas"},
    usuarios: {instacia: "usuario", coleccion: "usuarios"},
    inventarios: {instacia: "inventario", coleccion: "inventarios"},
    articulos: {instacia: "articulo", coleccion: "articulos"},
    selecciones:{instacia: "seleccion", coleccion: "selecciones"},
    listas_precios:{instacia: "lista_precios", coleccion: "listas_precios"},
    clases:{instacia: "clase", coleccion: "clases"}
}
window.gama={};
window.comunes={};

window.haveValue=function(obj){//thanks ie, you are a (#$%&)
    var case_={
        "number":function(e){return (e==0)?false:true},
        "string":function(e){return (e.length)?true:false},
        "object":function(e){return (Object.keys(obj).length)?true:false},
        "boolean":function(e){return e}
    };
    return (case_[typeof(obj)])?(case_[typeof(obj)])(obj):false;
};

Backbone.Router.refresh=true;
var router=Backbone.Router.extend({
    routes:{
        "":"openHome",
        "prueba":"prueba",
        ":action/:tabla(/)(:id)":"currentUser",
        ":action/:tablaP(/):tablaH(/)(:id)":"openTableChild",
        '*notFound': 'routeNotFound'
    },
    openHome:function(){
        //console.log("entra a home");
        openSection("home");
    },
    prueba:function(){
      //alert("prueba");
        console.log("prueba");
    },
    currentUser:function(action,tabla,id){
        //console.log("entra a current user " + tabla + ", rutasDisponibles[tabla] " + rutasDisponibles[tabla]);
        var tablaObjeto=rutasDisponibles[tabla];
        if(!existWithVal(tablaObjeto)){
               return this.routeNotFound(tabla);
        }

        listado.render({action:action,tabla:tablaObjeto,id:id});
        /*
        switch(action){
            case "lista":
                $("#principal").html("");
            case "crear":
            case "ver":
                break;
            default:
                alert("accion no permitida");
                break;
        }
        */
    },
    openTableChild:function(action,tablaP,tablaH,id){
        //console.log("llega a child");

        var tablaObjeto=rutasDisponibles[tablaH];
        if(!existWithVal(tablaObjeto)){
            return this.routeNotFound(tablaH);
        }

        var evento;
        var tabla = window[tablaObjeto.instacia];

        if(action=="lista"){
            tabla.traerPorCliente(function(){openSection("puntos",tabla.puntosClientes,action,undefined,id);},id);
            return true;

        }else if(action=="ver"){
            var tabla = window[tabla.instacia];


            evento=function(data){openTable(tabla,tabla.instacia,data,"ver",p.id);};
            tabla.id= p.id;
        }else if(action=="crear"){
            var tabla = window[tabla.instacia];
            var empty=tabla.clone();
            empty.clear();
            console.log("crear");
            openTable(tabla,tabla.instacia,empty.attributes,"crear");
        }

        if (!existWithVal(tabla)){
            showMessage("error","No se encuentra la instancia");
            return;
        }

        tabla.fetch({
            success: evento,
            error:function(a,b,c){
                verifyConnection(function(){showMessage("error","Error consultado la informacion");});
            }
        });
    },
    routeNotFound : function (a,b,c){
        showMessage("error","No se reconoce la direccion web");
    }
});
var router = new router();
Backbone.history.start();

applyRutes();
function applyRutes(){
	$.each(rutasDisponibles,function(index,value){
        var section=index;
        var url="#lista/"+section+"/";
        $(".a_"+section).data("href",url).attr("href",url);
    });
    //(".a_clientes").attr("href");usuario.get("fullName");
}





