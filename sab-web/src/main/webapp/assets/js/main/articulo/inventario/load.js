function cargarModuloInventario(id,action){
    if(!window.articulo.get("inventarioEnt")){
        window.articulo.set("inventarioEnt", {});
        window.inventarioYaCreado = false;
    }else{
    	window.inventarioYaCreado = true;
    }
    
    if(window.articulo.get("inventarioEnt").unidadesCompra){
    	window.unidadesCompra = window.articulo.get("inventarioEnt").unidadesCompra;
    	idUnidadKardex = window.articulo.get("inventarioEnt").idUnidadKardex;
    	$.each(window.unidadesCompra, function (index, unidadCompra) {
            id = unidadCompra.id;
            unidadCompra.nombre = findById(window.unidadesAlternas, unidadCompra.id).nombre;
            unidadCompra.equivalencia = convertidor_de_unidades(id, idUnidadKardex, 1, window.articulo.get("id"));
        });
    }else{
    	window.unidadesCompra = [];
    }
    	
    if(window.articulo.get("inventarioEnt").maximosMinimos){
    	window.maximosMinimos = window.articulo.get("inventarioEnt").maximosMinimos;
    }else{
    	window.maximosMinimos = [];
    }
    	
    if(window.articulo.get("inventarioEnt").bodegas){
    	window.bodegas = window.articulo.get("inventarioEnt").bodegas;
    }else{
    	window.bodegas = [];
    }
    
    eventosClick(id,action);
    eventosVarios(id,action);
    
    cargaCamposInventario(id,action,
        function(){
            cargaDatosInventario(id,action,
                function(){
                    inicializacion(id,action);
                    closeChargeStep();
                    window.pedirEquivalencia=false;
               }
            );
        });
}

//validacion al cargar el modulo despues de cargar los datos
function inicializacion(id,action){
    $("#unidad_o").limita({validaCon: "numeros,letras",otros:" ,-,_,"});
    $("#unidad_alias").limita({validaCon: "numeros,letras",otros:" ,-,_,"});

    if($(".wstep").nextAll().not(".ignore,.datos_basicos,.inventario").length>0){
        $(".actions.form-actions.full").find(".next").removeClass("end");
    }

    if(window.articulo.esIngrediente){
        $("#mensaje_unidad_alterna").removeClass("hidden");
        $("#mensaje_unidad_alterna").find("label").html("Unidad alterna inactivada porque este articulo ha sido seleccionado como ingrediente");
        $("#unidad_a").find("option").not(".empty").remove();
        $("#unidad_a").prop("disabled",true);
    }else{
        $("#mensaje_unidad_alterna").addClass("hidden");
    }
    
    if(window.articulo.get("esSeleccion")){
        $("#mensaje_unidad_alterna2").removeClass("hidden");
        $("#mensaje_unidad_alterna2").find("label").html("Unidad alterna inactivada porque este articulo hara parte de una seleccion");
        $("#unidad_a").find("option").not(".empty").remove();
        $("#unidad_a").prop("disabled",true);
    }else{
        $("#mensaje_unidad_alterna2").addClass("hidden");
    }
}

function showBodegasArticuloTable(ops){
    ops.workWithObject=true;
    if(ops.all){
        ops.workObject=window.bodegasArticulo;//todas las bodegas disponibles
    }else{
        ops.workObject=window.bodegas;//las bodegas del articulo
    }

    ops.html = ops.returnControl;
    ops.titulo = "Bodegas";
    ops.destino = $("#bodegas_tableCross");

    ops.asignar = function (data){
    };


    ops.prevDelete=function(me){
        table=$("#rol_tableCross").find("table");

        var datatable=table.DataTable();
        row=$(me).closest("tr");
        row2=datatable.row(row);
        row2.remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }
    };
    ops.urlDelete = "#";
    ops.deleteFunction = function () {
        /*
         showMessage("good", "Se ha eliminado el grupo");
         showTable({tabla:"grupo_clientes",modo:"tabla",tipo:"save"});
         */
    };
    ops.returnControl = false;
    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "nombre", class: "centrate nombre"}
    ];


    ops.notDelete=true;
    ops.notEdit = true;

    ops.submit = function () {
        var table=$(".clon_floating").last().find("table");
        datatable=table.DataTable();
        var tds = datatable.$("input:checked");
        
        window.bodegas = [];

        tds.each(function(index,value){
            var b={
            	nombre : $(this).closest("tr").find(".nombre").html(),
            	id: $(this).closest("tr").find(".id").html()
            };
            
            window.bodegas.push(b);
        });

        close_floating();

        showTable({tabla:"bodegasArticulo",modo:"tabla",tipo:"save"});
    };
    ops.floating_created = function (floating) {

    };
    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            seleccionaDeFlo($(".clon_floating").last(), window.bodegas, "id", "id");
        }
    };
    return ops;

}