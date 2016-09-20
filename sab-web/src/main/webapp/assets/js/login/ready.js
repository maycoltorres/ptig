$("document").ready(asignarEventos);

function asignarEventos() {

    $("#bar .btn").click(function () {
        btnId = $(this).attr('id');
        $(".login-wrapper").attr("data-active", btnId);
        $("#bar").attr("data-active", btnId);
    });

    $(".tipR").click(function () {
        $("input[type='password'],input[type='text']").stop(true,true);
        $("input[type='password'],input[type='text']").css({marginLeft:0});
        $(".alert-error").hide();
    });

    $("#log").submit(function () {
        $("#log input[type='text']").valida();
        $("#log input[type='password']").valida();
        if ($("#log .campo_mal").size() < 1) {
            return true;
        }
        return false
    });

    $("#username,#email-field").limita({validaCon: "letras,numeros,correo"});
    $("#username,#email-field").blur(function () {
        $(this).valida({UsaEspecifica: true, validaciones: "correo", longitud: {minimo: 5, maximo: 100}});
    });
    $("#username,#email-field").keyup(function () {
        $(this).valida({UsaEspecifica: true, validaciones: "correo", longitud: {minimo: 5, maximo: 100}, agitar: false, mostrarMensaje: false});
    });
    $("#contra").keyup(function () {
        $(this).valida({ agitar: false,mostrarMensaje: false,longitud: {minimo: 8, maximo: 50}});
    });
    $("#contra").blur(function () {
        $(this).valida();
    });

    $("#forgot").submit(function (e) {
        $("#forgot input[type='text']").valida();
        return false;
    });

}
