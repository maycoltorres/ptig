function showConfirm(el,text,confirmar_action,cancelar,ttr2){
    $("#confirmar").css({display:"block"});
    $("#shadow_black").css({display:"block",opacity:0.7});
    $("#footer_btn").removeClass("hidden");
    var h=$(el).offset().top-$(window).scrollTop();
    var w=$(el).offset().left-$(window).scrollLeft()-100;
    $("#confirmar_cancelar,#confirmar_seguro").off("click");
    if(cancelar==undefined||cancelar==null){
        $("#confirmar_cancelar").on("click",function(){
            $("#shadow_black").css({display:"none",opacity:0});
            $("#confirmar").css({display:"none"});
        });
    }else{
        $("#confirmar_cancelar").on("click",cancelar);
    }

    if(ttr2!=undefined){
        $("#prompt_message").html("");
        $("#confirmar_action").parent().html('<span id="confirmar_action">'+text+'</span>');

    }else{
        $("#prompt_message").html(text);
        $("#confirmar_action").html(confirmar_action);


    }

    $("#confirmar_seguro").on("click",el.seguro);

    $("#confirmar").css({top:h,left:w});
}

function verifyConnection(funcion){
    if(window.navigator.onLine) {
        var ajax = $.ajax({
            cache: false,
            type: 'GET',
            url: 'rest/user/detalles/',
            success: function (xhr, textStatus) {
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#connection").css({display:"none"});
            },
            error: function (a,b,c) {
                if(a.responseText.indexOf("authentication.ui.DefaultLoginPageGenerating")>0){
                    $("#connection_cargando").addClass("hidden");
                    errorConnetion("La sesion a caducado vuelva a iniciar sesion","login");
                }else if(!(a.status==500)){
                    errorConnetion("Error en conexion con el servidor GAMASOFT","Recargar la pagina");
                }else{
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#sesion_div").css({display:"none"});
                    showMessage("Error de servidor contactese con gamasoft");
                }
            }
        });
    }else{
        $("#connection_image").removeClass("hidden");
        $("#connection_cargando").addClass("hidden");
        errorConnetion("No se detecta conexion a internet","open");
    }
}

function errorConnetion(message,action){

    $("#confirmar").css({display:"none"});
    $("#tryConnection").attr("Verificar conexion");
    $("#tryConnection").removeClass("login").removeClass("refresh");
    $("#connection_text").html(message);
    $("#shadow_black").stop(true,true);
    $("#shadow_black").css({display:"block",opacity:0.9});
    $("#connection").css({display:"block"});
    if(action=="open"){

    }else if(action=="login"){

        $("#tryConnection").addClass("login");
        $("#tryConnection").attr("value","Iniciar sesion");
    }else{
        $("#tryConnection").addClass("refresh");
        $("#tryConnection").attr("value",action);
    }
}

$("#tryConnection").click(function(){

    if($(this).hasClass("login")){
        showLogin();
    }else if($(this).hasClass("refresh")) {
        window.location.reload();
    }else{
        $("#connection_image").addClass("hidden");
        $("#connection_cargando").removeClass("hidden");
        $("#connection_text").html("verificando. . .");
        window.setTimeout(function(){verifyConnection(function(){showMessage("error","Repita la operacion que intento realizar")});},2000);
    }
});

function showLogin(){
    $("#shadow_black").css({display:"block",opacity:0.9});
    $("#sesion_div").css({display:"block"});
    $("#connection").css({display:"none"});

    $("#iframe_sesion").on("load",function(){
        if(verifyLoadSession($(this))){
            close_floating();
            closeChargeStep();
            showMessage("error","Repita la accion que trato de realizar");
            return;
        }
        $(this).contents().find('#gama_div').remove();
        $(this).contents().find('#login').css("margin-top","50px");
        $("body").append('<script id="script_iframe" type="text/javascript" src="./assets/js/login/ready.js"></script>');
    });
    $("#iframe_sesion").attr("src","./login");
    var el = $("#iframe_sesion").contents();
    if (el.length != 1) {
        setTimeout(verifyConnection(showMessage("error","Repita la operacion que intento realizar")), 100);
        return;
    }
}
function verifyLoadSession(iframe){
    try{
        var url= iframe.contents().get(0).location.href;
    }catch(e){

    }

    // http://localhost:8080/sab/login
    // http://localhost:8080/sab/main

    if(typeof(url)=="string"&&url.indexOf("main")>-1){
        $(iframe).off("load");
        $(iframe).removeAttr("src");
        $("#script_iframe").remove();
        $("#shadow_black").css({display:"none",opacity:0});
        $("#sesion_div").css({display:"none"});
        $("#connection").css({display:"none"});
        return true;
    }
}