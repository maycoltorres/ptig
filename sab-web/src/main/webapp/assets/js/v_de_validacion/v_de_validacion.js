(function($){
    $.fn.campoMal=function(opciones){
        var op = $.extend({}, $.fn.valida.defaults, opciones);
        this.removeClass("validado");
        this.removeClass("campo_mal");
        if(this.is( ":hidden" )){
            return true;
        }
        $("#mensaje_de_error_" + $('input').index(this)).remove();

        if(op.resaltarCampo==true){
            this.addClass("campo_mal");
            //this.focus();
        }else{
            this.removeClass("campo_mal");
        }

        if(op.mostrarMensaje==true){
            var elemento = $(this);
            if ($(this).nextAll().filter(":first").is("label")){
                elemento = $(this).nextAll().filter(":first");
            }
            var final = " minimo " + op.longitud.minimo + " y maximo " + op.longitud.maximo + " caracteres";
            if(!op.mensajeParticular==""){
                mensaje=op.mensajeParticular;
            }else{
                mensaje = op.mensaje + op.validacionesEnMensaje;
            }

            if(op.mostrarLongitud==true){
                mensaje = mensaje + final;
            }

            elemento.after('<span id="mensaje_de_error_' + $('input').index(this) + '" class="mensaje_de_error img_validado">' + mensaje + '</span>');
        }
        if(op.agitar==true){
            agitar(this,5);
        }
    }

    $.fn.campoBien=function(opciones){
        var op = $.extend({}, $.fn.valida.defaults, opciones);
        this.removeClass("campo_mal");
        $("#mensaje_de_error_"+$('input').index(this)).remove();

        if(op.resaltarCampo==true){
            this.addClass("validado");
        }else{
            this.removeClass("validado");
        }
    }


    function agitar(elemento,move){

        elemento.animate({marginLeft:"-="+move},100);
        elemento.animate({marginLeft:"+="+move},100);
        elemento.animate({marginLeft:"+="+move},100);
        elemento.animate({marginLeft:"-="+move},100);
        elemento.animate({marginLeft:"-="+move},100);
        elemento.animate({marginLeft:"+="+move},100);
    }

    $.fn.valida = function (opciones) {
        var op = $.extend({}, $.fn.valida.defaults, opciones);
        this.each(function () {
            var tagtypes = ["form", "div", "ul", "li", "tr", "iframe"];
            if (jQuery.inArray($(this).prop('tagName').toLowerCase(), tagtypes)>=0) {
                $(this).children().valida(op);
                console.log("retorna 59");
                return;
            }
            var tagtypes = ["input", "select", "textarea"];
            if (jQuery.inArray($(this).prop('tagName').toLowerCase(), tagtypes)<0) {
                console.log("retorna 64");
                return;
            }
            
            var valor = $(this).val();
            var type=$(this).attr('type')
            if(type=="checkbox"||type=="radio"){
                try {
                    valor = $(this).find(":checked").eq(0).attr("value");
                }catch(e){
                    valor="";
                }
            }
            if($(this).is("select")){
                op.mensajeParticular=" ";
                op.mostrarLongitud=false;
                try {
                    valor = $(this).find(":selected").val();
                }catch(e){
                    valor="";
                }
            }
            if(valor==""||valor==null||valor==undefined){
                if(op.permitirEnBlanco==false){
                    $(this).campoMal(op);
                    return;
                }else{
                    $(this).campoBien(op);
                    return;
                }
            }
            if(!(type=="text")&&!($(this).is("textarea"))&&!(type=="password")){
                $(this).campoBien(op);
                return
            }

            var exp = "^((.){"+op.longitud.minimo+","+op.longitud.maximo+"})$";
            if(!valor.match(new RegExp(exp))){
                console.log("No cumple los maximos y minimos ");
                /*
                if(op.mostrarLongitud=="si"){
                    op.mostrarLongitud=true;
                }
                */
                $(this).campoMal(op);
                return
            }

            if (jQuery.isEmptyObject(op.validaciones)) {
                //console.log("el elemento no tiene validaciones a aplicar");
                $(this).campoBien(op);
                return;
            }

            var validaciones = op.validaciones.split(",");
            exp = "";
            if(op.UsaEspecifica==false) {
                $(validaciones).each(function () {
                    exp = exp + op[this];
                    if(op[this]==""||op[this]==null|| op[this]==undefined){
                        console.log("No se encuentra este indice en el objecto de opciones:"+ op[this]);
                        return
                    }
                });
                exp = "^([" + op.adicionales + exp + "]{" + op.longitud.minimo + "," + op.longitud.maximo + "})$";
            }else{
                exp=op.validacionesEspeficas[op.validaciones];
                if(exp==""||exp==null||exp==undefined){
                    console.log("No se encuentra este indice en el objecto de opciones: "+op.validaciones);
                    return
                }
                op.mensajeParticular="Correo invalido";
            }
            if(exp==""||exp==null||exp==undefined){
                return
            }
            var reg_exp = new RegExp(exp);
            //console.log(reg_exp);
           // console.log(valor.match(reg_exp));
            if(valor.match(reg_exp)){
                $(this).campoBien(op);
            }else{
                $(this).campoMal(op);
            }

        });
    }
    $.fn.valida.defaults={
        autoFocus:false,
        mostrarLongitud:true,
        mayusculas: "A-Z",
        minusculas: "a-z",
        letras: "\\u00D1\\u00F1A-Za-z",
        numeros: "0-9",
        simbolos_basicos: "\\-\\.\\_#",
        alfanumericos: "A-Za-záéíóúÁÉÍÓÚñÑ0-9",
        otros_simbolos: ",:()?$@",
        adicionales:"",
        longitud:{minimo:1,maximo:100},
        validacionesEnMensaje:"",
        mensaje:"Digite en este campo ",
        mensajeParticular:"",
        permitirEnBlanco:false,
        resaltarCampo:true,
        mostrarMensaje:true,
        agitar:true,
        UsaEspecifica:false,
        validacionesEspeficas:
        {
            correo:"^[-_a-z0-9A-Z-#]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-_]+(\.[_a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,3})$"
        },
        validaciones:[],
        validacionPropia:[{
            selector:[{
                object:"",
                validacion:"",
                configuracion:{}
            }]
        }]
    };

    $.fn.limita = function (opciones) {
        var op = $.extend({}, $.fn.limita.defaults, opciones);
        op.validaCon+=",otros";

        this.each(function(){
            $(this).on("keypress",function(ev){

                if(typeof(op.validaCon)=="string"){
                    op.validaCon=op.validaCon.split(",");
                    if(typeof(op.validaCon)=="string"){
                        op.validaCon=[];
                    }
                }

                if(typeof(op.otros)=="string"){
                    op.otros=arrayDeCodigos(op.otros);
                    
                }

                if(!typeof(op.otros)=="array"){
                    op.otros=[];
                }
                op.otros.push(8);
                var tecla = jQuery.event.fix(ev).which;
                if(jQuery.isEmptyObject(op.validaCon)){
                    console.log("vacio");
                    var limita=$(this).data("limita");
                    if(!limita==null||!limita==undefined||!limita==""){
                        return false;
                    }
                    op.validaCon=arrayDeCodigos(limita);
                    var adicionales=$(this).data("adicionales");
                    if(!adicionales==null||!adicionales==undefined||!adicionales==""){
                        op.validaCon=$.merge(arrayDeCodigos(adicionales), op.validaCon);
                    }
                    if(jQuery.isEmptyObject(op.validaCon)){
                        return false;
                    }
                }

                var result=false;
                $(op.validaCon).each(function(){
                    try {
                        if (jQuery.inArray(tecla, op[this]) > -1) {
                            result = true;
                        }
                    }catch(e){}
                });
                if(result){
                    return true
                }else{
                    return false
                }
            });
        });

        function arrayDeCodigos(cadena){
            var cadenas=cadena.split(",");
            cadena=[];
            $(cadenas).each(function(){
                cadena.push(this.charCodeAt(0));
            });
            return cadena;
        }
    }

    $.fn.limita.defaults = {
        validaCon:[],
        letras:[65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,209,241],
        numeros:[48,49,50,51,52,53,54,55,56,57],
        simbolos_texto:[32,44,45,46,58,59,63,64,95],
        correo:[46,45,64,95,35,43],
        parrafo:[9,10,13],
        alfas:[35,44,45,95,32],
        otros:[]
    };

}( jQuery ));