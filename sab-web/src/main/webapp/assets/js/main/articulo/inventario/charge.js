//aqui estaran los eventos tratan con ajax
function cargaCamposInventario(id,action,funcion){
	var ajax4=$.ajax({
       url:"./rest/cliente/clase/?inventario=true",
       success:function(data){
            fillSelect(data,$("#articulo_section").find("#clase"),"nombre","","", function(){
            	if(window.articulo.get("inventarioEnt").idClaseArticulo){
            		$("#clase option[value='" + window.articulo.get("inventarioEnt").idClaseArticulo +"']").prop("selected", "selected");
            		$("#clase").trigger("change");
            	}
            });
        },
        error:function(){
            $("#clase").closest(".form-group").removeClass("loading_control");
            verifyConnection(function(){showMessage("error","No fue posible consultar la lista de clases")})
        }
    });

    var ajax6=$.ajax({
        url:"./rest/bodegas/?idArticulo=" + window.articulo.id,
        
        success:function(data){
            window.bodegasArticulo = data;
        },
        error:function(){
            verifyConnection(function(){showMessage("error","No fue posible consultar todas las bodegas")})
        }
    });

    funcion();
}

function cargarTablas(id,action,inventarioId){


    showTable({tabla:"max",modo:"tabla",tipo:"save",id:id,inventarioId:inventarioId});
    showTable({tabla:"unidad_c",modo:"tabla",tipo:"save",id:id,inventarioId:inventarioId});
    showTable({tabla:"bodegasArticulo",modo:"tabla",tipo:"save"});
}

function cargaDatosInventario(id,action,funcion){
            inventarioEnt= window.articulo.get("inventarioEnt");
            
            fillSelect(window.unidadesEstandar, $("#articulo_section").find("#unidad_k"), "nombre");
            if(inventarioEnt.idUnidadKardex){
        		$("#unidad_k option[value='" + inventarioEnt.idUnidadKardex +"']").prop("selected", "selected");
        	}
            
            fillSelect(window.unidadesAlternas, $("#articulo_section").find("#unidad_a"), "nombre","type","");
            $("#articulo_section").find("#unidad_a").append('<option class="empty"></option>');
            $("#articulo_section").find("#unidad_a").append('<option value=-2>Nueva unidad</option>');
            if(inventarioEnt.idUnidadAlterna){
        		$("#unidad_a option[value='" + inventarioEnt.idUnidadAlterna +"']").prop("selected", "selected");
        		$("#unidad_a").trigger("change");
        	}

            if(parseInt(inventarioEnt.idUnidadKardex)>0){
            	var unidadKardex = findById(window.unidadesEstandar, inventarioEnt.idUnidadKardex);
                if(unidadKardex.tipo == "Unidad"){
                    $("#unidad_alias").closest(".form-group").removeClass("hidden");
                    $("#unidad_alias").val(inventarioEnt.aliasUnidad);
                }
            }

            if(parseInt(inventarioEnt.idUnidadAlterna)>0){
                $("#unidad_a").find("option[value='"+inventarioEnt.idUnidadAlterna+"']").prop("selected",true);
            }else{
                $("#unidad_a").find(".empty").prop("selected",true);
            }

            cargarTablas(id, action, inventarioEnt.id);
            $("#tableMinMax,#table_unidad_c").attr("data-inventario", inventarioEnt.id);
            funcion();
     }
