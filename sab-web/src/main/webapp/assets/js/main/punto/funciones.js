function crearPunto(action){
    $('#nombrePunto').valida({permitirEnBlanco:false,longitud:{minimo:1,maximo:100}});
    $('#clientes_direccion').valida();
    $('#telefonoPunto').valida({validaciones:"numeros",permitirEnBlanco:false,longitud:{minimo:7,maximo:25}});
    $('#extensionPunto').valida({validaciones:"numeros",permitirEnBlanco:true,longitud:{minimo:1,maximo:4}});
    $('#telefono2Punto').valida({validaciones:"numeros",permitirEnBlanco:true,longitud:{minimo:7,maximo:25}});
    $('#extension2Punto').valida({validaciones:"numeros",permitirEnBlanco:true,longitud:{minimo:1,maximo:4}});
    $('#centro_costo_idPunto').valida({validaciones:"numeros",permitirEnBlanco:true,longitud:{minimo:1,maximo:3}});
    $('#tipo_negocio_idPunto').valida();

    $('#pais,#departamento,#municipio,#tipo_negocio_idPunto').valida();
    if(!$("#punto_section .campo_mal").length<1){
        $(".campo_mal:eq(0)").focus();
        closeChargeStep();
        return false;
    }else{
        var p = _.clone(window.punto);
        if(!(action=="ver")&&(window.puntoRecienCreado==undefined)){
            var p = window.punto.clone();
            p.clear();
            p.set(window.punto.defaults);
        }

        if(!(window.usuario.get("id")=="1")){
            seguridad_puntos_omitir_guardado();
            return;
        }

        p.save({
            cliente_id:window.clienteDePunto,
            activo: true,
            nombre : $("#nombrePunto").val(),
            direccion : $("#clientes_direccion").val(),
            telefono : $("#telefonoPunto").val(),
            extension : parseInt($("#extensionPunto").val()),
            telefono2 : $("#telefono2Punto").val(),
            extension2 : parseInt($("#extension2Punto").val()),
            municipio_id : parseInt($("#municipio").val()),
            tipo_negocio_id : parseInt($("#tipo_negocio_idPunto").find(":selected").val()),
            centro_costo_id : parseInt($("#centro_costo_idPunto").val())
        },{
            success:function(model, response, options) {
                showMessage("good","El punto ha sido guardado");
                if(!isNaN(response)){
                    window.punto.set(p.attributes);
                    window.punto.set({id:response});
                    //console.log(window.punto);
                    window.puntoRecienCreado=true;
                }
                $(".actions.form-actions.full").find(".next").addClass("continue").click();
                closeChargeStep();
            },
            error:function(model, response, options){
                var response=response.responseText.toString();

                if(response=="Punto Actualizado"){
                    showMessage("good","El punto ha sido guardado");
                    $(".actions.form-actions.full").find(".next").addClass("continue").click();
                }else if(response.indexOf("existe")>-1||response.indexOf("nit")>-1){
                    showMessage("error","El nombre del punto ya existe");
                }else{
                    showMessage("error","Ha ocurrido un error al guardar el punto");
                }
                closeChargeStep();
            }
        });
    }
}

function guardarPermisos(obj,NoMostrarM){
    window.punto.set(obj);

    window.punto.save({},{
        success:function(model, response, options) {
           if(NoMostrarM==undefined){
               showMessage("good","El punto ha sido guardado");
           }

            if(!isNaN(response)){
                window.punto.set({id:response});
                window.puntoRecienCreado=true;
                showTable({tabla:"punto_permisos",modo:"tabla",tipo:"save",id:window.articulo.id});
            }
        },
        error:function(model, response, options){
            if(response.responseText=="Punto Actualizado"){
                if(NoMostrarM==undefined){
                    showMessage("good","El punto ha sido guardado");
                }

                showTable({tabla:"punto_permisos",modo:"tabla",tipo:"save",id:window.articulo.id});

            }else{
                showMessage("error","Ha ocurrido un error al guardar el punto");
            }
        }
    });
}


function validaPermisos(obj){
    var ids=extraerIds($("#permisos_tableCross"),"key",true);
    if(ids.length>0){
        $(".actions.form-actions.full").find(".next").addClass("continue").click();
        return true;
    }

    showMessage("warning","Necesita configurar al menos 1 permiso");
}

function activarBodega(me,table){
    $.ajax({
        url:"./rest/" + window.punto.id + "/bodegas/" + me.id + "/activar/",
        type:"POST",
        success:function(){
            dt=table.DataTable();
            dt.row($(me).closest("tr")).remove();
            $(me).closest("tr").remove();
            dt.draw(false);
            showMessage("info","Se ha activado");
        },
        error:function(){
            showMessage("error","No ha sido posible activarla");
        }
    });
}

function desactivarBodega(me,table){
    $.ajax({
        url:"./rest/" + window.punto.id + "/bodegas/" + me.id + "/inactivar/",
        type:"POST",
        success:function(){
            dt=table.DataTable();
            dt.row($(me).closest("tr")).remove();
            $(me).closest("tr").remove();
            dt.draw(false);
            showMessage("info","Se ha inactivado");
        },
        error:function(){
            showMessage("error","No ha sido posible inactivarla");
        }
    });
}