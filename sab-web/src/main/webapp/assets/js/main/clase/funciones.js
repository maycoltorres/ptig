
function showClaseGruposTable(ops){
	ops.html = $("#container_clases_grupo").html();
    ops.titulo = "Grupos";
    ops.destino = $("#claseGrupos_tableCross");

    ops.asignar = function (data) {
        if(ops.all==undefined){
            window.clase.set({grupos:data});
        }
    }
    
    ops.prevDelete=function(me){
        table=$("#claseGrupos_tableCross").find("table");
        
        var index = 0;
    	var foundAt = null;
    	$.grep(window.clase.get("grupos"), function (g) {
            if (g.id == me.id) {
                foundAt = index;
            }
            index++;
        });
        if (foundAt != null) {
        	window.clase.get("grupos").splice(foundAt, 1);
        }

        var datatable=table.DataTable();
        row=$(me).closest("tr");
        row2=datatable.row(row);
        row2.remove();
        row.remove();
        if(table.find("tbody").find("td").not(".dataTables_empty").length>0){
            table.draw();
        }

    }
    ops.deleteFunction = function (me) {
    	
    }
    
    ops.returnControl = true;
    
    ops.cols = [
        {proper: "id", head: "id", class: "hidden id"},
        {proper: "nombre", head: "nombre", class: "centrate nombre"},
        {proper: "descripcion", head: "descripcion", class: "centrate descripcion"},
    ];

    ops.workWithObject=true;
    ops.workObject=window.clase.get('grupos');
    
    if (ops.modo == "tabla") {

    } else {
    	ops.submit = function () {
	    	var self = this;
	    	var flo = $(".floating_form.clon_floating");
	    	
	    	flo.find("#container_nombre").valida({longitud:{minimo:3,maximo:100},mostrarLongitud:true,mostrarMensaje:true});
	    	flo.find("#container_descripcion").valida({longitud:{minimo:3,maximo:100},mostrarLongitud:true,mostrarMensaje:true});
	    	
	    	var validaNombre = true;
	    	if(ops.fila){
	    		$.grep(ops.workObject, function (grupo) {
                    if (grupo.nombre.toUpperCase() === flo.find("#container_nombre").val().toUpperCase() && 
                    		grupo.id !== parseInt(ops.fila.find(".id").html())) {
                        validaNombre = false;
                    }
                });
	    	}else{
	    		$.grep(ops.workObject, function (grupo) {
                    if (grupo.nombre.toUpperCase() === flo.find("#container_nombre").val().toUpperCase()) {
                        validaNombre = false;
                    }
                });
	    	}
	    	
	    	if(!validaNombre){
	    		showMessage("error", "Ya existe un grupo con ese nombre");
	    		return false;
	    	}
	    	
		    if(flo.find(".campo_mal").length>0){
		    	flo.find(".campo_mal").first().focus();
		        return false;
		    }
		    
		    if(ops.fila){
		    	$.grep(ops.workObject, function (grupo) {
                    if (grupo.id === parseInt(ops.fila.find(".id").html())) {
                    	grupo.nombre = flo.find("#container_nombre").val();
                    	grupo.descripcion = flo.find("#container_descripcion").val();
                    }
                });
		    }else{
		    	var c={};
		        c.id=0;
		        c.nombre=flo.find("#container_nombre").val();
		        c.descripcion=flo.find("#container_descripcion").val();
		        
		        ops.workObject.push(c);
		    }
	
	        showTable({tabla: "clase_grupos", modo: "tabla", tipo: "save"});
	        close_floating();
	    };
	}
    
	ops.floating_created = function (floating) {
		if (ops.fila) {
            floating.find("#container_nombre").val(ops.fila.find("td").eq(1).html());
        }
    };

    ops.tableCreated = function (table, datatable, clon) {
        if($(".clon_floating").find(".nombre").length>0){
            console.log(window.clase.get("grupos"));
            seleccionaDeFlo($(".clon_floating").last(),window.grupo.get("grupos"),"id","id");
        }
    };
    
    return ops;
}

