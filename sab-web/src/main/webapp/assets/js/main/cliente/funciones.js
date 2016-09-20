function crearCliente(action) {
    var self = this;
    $("#clientes_rs").valida({longitud: {minimo: 2, maximo: 100}, mostrarLongitud: true, mostrarMensaje: true});
    $("#clientes_nit").valida({
        validaciones: "numeros",
        mostrarLongitud: false,
        mensajeParticular: "Ingrese solo numeros, minimo 5 y maximo 15",
        longitud: {minimo: 6, maximo: 19},
        adicionales: "."
    });//plugin adicional
    $("#clientes_dc").valida({validaciones: "numeros", longitud: {minimo: 1, maximo: 1}});
    $("#clientes_telefono").valida({validaciones: "numeros", longitud: {minimo: 7, maximo: 25}});
    $("#clientes_extension").valida({
        validaciones: "numeros",
        permitirEnBlanco: true,
        longitud: {minimo: 1, maximo: 4}
    });
    $("#clientes_celular").valida({validaciones: "numeros", permitirEnBlanco: true, longitud: {minimo: 5, maximo: 25}});
    $('#clientes_correo').valida({UsaEspecifica: true, validaciones: "correo", longitud: {minimo: 5, maximo: 100}});
    $('#clientes_duenon').valida({validaciones: "letras", adicionales: " ", longitud: {minimo: 3, maximo: 100}});
    $('#clientes_dr').valida({validaciones: "numeros", adicionales: " ", longitud: {minimo: 3, maximo: 15}});
    $('#clientes_cd').valida({
        UsaEspecifica: true,
        validaciones: "correo",
        permitirEnBlanco: true,
        longitud: {minimo: 5, maximo: 100}
    });
    $('#clientes_nr').valida({validaciones: "letras", adicionales: " ", longitud: {minimo: 3, maximo: 100}});
    $('#clientes_tp').valida({validaciones: "numeros", permitirEnBlanco: true, longitud: {minimo: 7, maximo: 25}});
    $('#clientes_direccion').valida();

    $('#clientes_pais,#clientes_departamento,#clientes_municipio').valida();

    if (!$("#cliente_section .campo_mal").length < 1) {
        $(".campo_mal:eq(0)").focus();
        closeChargeStep();
        return false;
    } else {
        //console.log(window.cliente);
        //console.log(window.clientes);

        if (action == "ver") {
            if (!(window.cliente == undefined)) {
                id=window.cliente.get("id");
                var t=$.grep(window.clientes.models, function (e) {
                    if(e.attributes.id==id&&e.attributes.email==$("#clientes_correo").val()){
                        return false;
                    }else{
                       if(e.attributes.email==$("#clientes_correo").val()){
                           showMessage("warning", "Este correo ya lo posee otro cliente");
                           return true;
                       }
                    }
                });
            }else{
                showMessage("error", "Error de definicion de cliente");
            }

        } else {
            var t = $.grep(window.clientes.models, function (e) {
                if (e.get("email") == $("#clientes_correo").val()) {
                    showMessage("warning", "Este correo ya lo posee otro cliente");
                    return true;
                }
                return false;
            });
        }


        if (t.length > 0) {
            return false;
        }

        var p = _.clone(window.cliente);
        if (!(action == "ver") && (window.cliente.recienCreado == undefined)) {
            var p = window.cliente.clone();
            p.clear();
            p.set(window.cliente.defaults);
        }

        p.save({
            nit: $("#clientes_nit").val(),
            digitoChequeo: parseInt($("#clientes_dc").val()),
            direccion: $("#clientes_direccion").val(),
            telefono: $("#clientes_telefono").val(),
            celular: $("#clientes_celular").val(),
            celularDueno: $("#clientes_tp").val(),
            extension: parseInt($("#clientes_extension").val()),
            email: $("#clientes_correo").val(),
            nombreRepresentante: $("#clientes_nr").val(),
            documentoRepresentante: parseInt($("#clientes_dr").val()),
            nombreDueno: $("#clientes_duenon").val(),
            mailDueno: $("#clientes_cd").val(),
            nombre: $("#clientes_rs").val(),
            municipio: parseInt($("#clientes_municipio").find(":selected").val())
        }, {
            success: function (model, response, options) {
                showMessage("good", "El cliente ha sido guardado");

                if (!isNaN(response)) {
                    window.clienteDePuntoNombre = window.cliente.get("nombre");
                    window.cliente.set({id: response});
                    window.clienteDePunto = response;
                    window.idClienteEditar = response;
                    window.cliente.recienCreado = true;
                } else {
                    //var id_FFFFF
                }
                //window.clienteDePunto=window.cliente.attributes;
                $(".actions.form-actions.full").find(".next").addClass("continue").click();
                closeChargeStep();
            },
            error: function (model, response, options) {
                if (response.responseText == "Cliente Modificado") {
                    window.clienteDePuntoNombre = window.cliente.get("nombre");
                    showMessage("good", "El cliente ha sido guardado");
                    $(".actions.form-actions.full").find(".next").addClass("continue").click();
                } else if (response.responseText.indexOf("Nit") > -1 || response.responseText.indexOf("nit") > -1) {
                    showMessage("error", "El nit ya existe en la base de datos");
                } else if (response.responseText.indexOf("El cliente") > -1) {           //
                    showMessage("error", "El cliente ya existe en la base de datos");  //El cliente ya existe en la base de datos
                } else if (response.responseText.indexOf("El Nombre") > -1) {           //El Nombre
                    showMessage("error", "El cliente ya existe en la base de datos");  //El cliente ya existe en la base de datos
                } else if (response.responseText.indexOf("Este Email") > -1) {           //El Nombre
                    showMessage("error", "El correo ya existe en la base de datos");  //El cliente ya existe en la base de datos
                } else {
                    showMessage("error", "Ha ocurrido un error al guardar el cliente");
                }
                closeChargeStep();
            }
        });
    }

}

function validaTablasCliente(){
    if($("#cliente_regimen_tableCross").find("table").find("tbody").find("td.idPais").length < 1 ||
        $("#cliente_canal_tableCross").find("table").find("tbody").find("td.id").length < 1){
        showMessage("error","Debe elegir una configuracion para regimen y canal");
        return false;
    }
    return true;
}

function actualizarConfiguracion() {
	var conf = {
		recetaDiferencialXPunto : $("#check_receta_punto").is(":checked"),
		recetaDiferencialXCanal : $("#check_receta_canal").is(":checked"),
		empaqueDiferencialXPunto : $("#check_empaque_punto").is(":checked"),
		empaqueDiferencialXCanal : $("#check_empaque_canal").is(":checked"),
		unicoImpuestoXCanales : $("#imp_eq_ch").is(":checked") 
	};
  
	var t = $.ajax({
        url: "./rest/cliente/" + window.idClienteEditar + "/configuracion/",
        contentType: 'application/json',
        type: "POST",
        data:JSON.stringify(conf),
        success: function () {
            
        },	
        error: function () {
        	showMessage("error", "No fue posible actualizar la configuracion");
        }
    });
};

