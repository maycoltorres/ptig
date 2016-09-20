$("document").ready(asignarEventos);

if(!(window.usuario.get("id")=="1")){
   $("#mainnav").find(".lista_grupo_act,.lista_marca_act").addClass("hidden");
}

function asignarEventos() {
    $("#check_venta").remove();
    $("body").contents().find("#check_venta").remove();
    //console.log($("#check_venta"),$("body").contents().find("#check_venta"));

    $.extend($.fn.dataTable.defaults, {
        'language':{url:"assets/js/libs/datatables/espanol.json"},
        'aoColumnDefs': [
            {
                'bSortable': false,
                'aTargets': ['no-sort']
            }
        ]});
    $(".nav.nav-list li a").off();

    $(".nav.nav-list li a").on("click", function (e) {
        if($(this).hasClass("selected_section")){
            Backbone.history.navigate(this.href, { trigger: false });
        }else{
            $(".selected_section").removeClass("selected_section");
            $(this).addClass("selected_section");
        }

    });

    hideBox();
    //showMessage("error","prueba");
    $("#content_floating .panel-heading i").click(function CierraFloating(){
        close_floating();
    });
    //creatorFloating("","",function(){alert("ejecuta");},$(".table_container").html(),"table");
    //verifyConnetion(function(){alert("verificado");});

    //creatorFloating("prueba","Cerrar",$(".tableRef").html(),"table",function(){})
    //showUnits();
}

$(".tableUnits").click(function(){
    showUnits();
});

function showUnits(){
    var clon=$(".tableRef").clone();
    clon.appendTo("body");
    clon.find("table").dataTable({"bPaginate": false,bFilter: false, bInfo: false});
    clon.find("i").click(function(){
        clon.remove();
    });
    clon.removeClass("hidden");
}


function ChargeCompleted(){
    var nombre = usuario.get("fullName");
    $("#session_user").html(nombre.charAt(0).toUpperCase() + nombre.toLowerCase().slice(1));
    if(!(usuario.roles==undefined)&&usuario.roles.length>0){
        nombre = $(usuario.roles).first()[0].nombre;
        $("#session_rol").html(nombre.charAt(0).toUpperCase() + nombre.toLowerCase().slice(1));
    }
}

function hideBox() {
    $("#shadow_black,#shadow_white").animate({opacity: 0}, 400, function () {
        $(this).css({display: "none"});
    });
}



