function crearGrupoSeleccion(action){
    var self = this;
    $("#nombre").valida({longitud:{minimo:3,maximo:100},mostrarLongitud:true,mostrarMensaje:true});
    $("#valor").valida({validaCon: "numeros",longitud:{minimo:1,maximo:12},mostrarLongitud:false,mostrarMensaje:true});

    if($('#suma_v').prop('checked')==false&&$('#suma_m').prop('checked')==false&&$('#aplica').prop('checked')==false){
        showMessage("warning","Debe elegir una caracteristica")
        return;
    }

    if($('#sel_abierta').prop('checked')==false&&$('#sel_cerrada').prop('checked')==false){
        showMessage("warning","Debe elegir un tipo de seleccion");
        return;
    }
    
    //Validar que todas las selecciones tengan valor en incremento de precio
    if($("input[name=precio_seleccion]:checked").val() === "1"){
    	var validaIncrementos = true;
    	$.grep(window.seleccionC, function (sel) {
			if(!sel.incrementoPrecio || sel.incrementoPrecio === ""){
				validaIncrementos = false;
			}
        });
    	if(!validaIncrementos){
    		showMessage("warning","Todas las selecciones deben tener el incremento de precio definido");
    		return;
    	}
    }

    if(!$("#seleccion_section .campo_mal").length<1){
        $(".campo_mal:eq(0)").focus();

        return false;
    }else{
        showChargeStep();

        if(action=="ver"){
            var data= _.clone(window.seleccion.attributes);
            if(!validaNombre($("#nombre").val().toLowerCase(),window.seleccion.get("id"))){
                showMessage("warning","Ya existe un grupo de seleccion con ese nombre");
                return;
            }
        }else{
            if(!validaNombre($("#nombre").val().toLowerCase())){
                showMessage("warning","Ya existe un grupo de seleccion con ese nombre");
                return;
            }

            var data= _.clone(window.seleccion.defaults);
            if(window.seleccion.get("id")==undefined||window.seleccion.get("id")==""){
                data.id=null;
            }else{
                data.id=window.seleccion.get("id");
            }
        }

        if($("#seleccion_tableCross").find("td.articuloNombre").length<2){
            showMessage("warning","El grupo de seleccion debe tener minimo 2 articulos");
            return;
        }

        if($('#sel_abierta').prop('checked')==true){
            data.tipoGrupo=1;
        }else{
            data.tipoGrupo=0;
        }

        data.nombre = $("#nombre").val().toLowerCase();
        data.caracteristicaPrecio = $("input[name=precio_seleccion]:checked").val();
        data.incrementoPrecio=$("#valor_de_grupo").val();
        data.tipoGrupo = $("input[name=tipo_grupo]:checked").val();
        
        if(data.id){
        	opType = "PUT";
        	opUrl = "./rest/grupoSeleccion/" + data.id + "/";
        }else{
        	opType = "POST";
        	opUrl = "./rest/grupoSeleccion/";
        }

        $.ajax({
            url:opUrl,
            type:opType,
            contentType: 'application/json',
            data:JSON.stringify(data),
            success:function(response){
                showMessage("good","El grupo seleccion ha sido guardado");

                if(!(action=="ver")){
                    window.seleccion.set({id:response});
                }

                guardarSelecciones();

            },
            error:function(response){
                if(response.responseText.indexOf("modificado")>-1) {
                    guardarSelecciones();

                    Backbone.history.navigate("#lista/selecciones/", { trigger: true });
                }else if(response.responseText.indexOf("existe")>-1){           //
                    showMessage("warning","El grupo seleccion ya existe en la base de datos");  //El cliente ya existe en la base de datos
                }else if(response.responseText.indexOf("no es")>-1){           //El Nombre
                    showMessage("warning","Este grupo seleccion no se puede modificar");  //El cliente ya existe en la base de datos
                }else{
                    showMessage("error","Ha ocurrido un error al guardar el grupo seleccion ");
                }
            }
        });


    }
}

function validaNombre(nombre,id){

    var t=$.grep(window.selecciones.models, function (e) {
        if(id==null||id==undefined){
            return e.attributes.nombre==nombre;
        }else{
            if(e.attributes.id==id&&e.attributes.nombre==nombre){
                return false;
            }else{
                return e.attributes.nombre==nombre;
            }
        }
    });

    if (t.length > 0) {
        return false;
    }
    return true;
}


function guardarSelecciones(){

    $.ajax({
    	url:"./rest/grupoSeleccion/" + window.seleccion.get("id") + "/selecciones/",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(window.seleccionC),
        success:function(){
            closeChargeStep();
            showMessage("good", "El grupo seleccion ha sido guardado");
            Backbone.history.navigate("#lista/selecciones/", { trigger: true });
        },
        error:function(){
            showMessage("error","No fue posible guardar la tabla");
        }
    });

}