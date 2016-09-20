function eventos_de_lista(me){
    var sec=me.data("sec");
    var navigate;
    if(me.hasClass("abrir")){
        navigate="#crear/"+sec+"/";
    }else if(me.hasClass("ver_datos")){
        if(sec=="clientes"){
            window.clienteDePunto=me[0].id;
        }
        navigate="#ver/"+sec+"/"+me[0].id;
    }else if(me.hasClass("desactivados")){
        window[me.data("sec")].desactivados(
            {
                success: function (data) {
                    openSection(sec, data, "lista", true);
                },
                error:function(){
                    verifyConnection(showMessage("error","No se encuentra la ruta"));
                }
            });
        return;
    }else if(me.hasClass("activar")||me.hasClass("Activar")){
        var me=me;
        me.seguro=function(){
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            model.activar(
                {success: function (data) {
                    showMessage("info","El registro ha sido activado correctamente");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                    var table=$(".data_table").DataTable();
                    var row=table.row(me.closest("tr"));
                    row.remove();
                    table.draw();

                },error:function(a,b,c){
                    showMessage("error","No fue posible activar el registro");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});

                    //console.log(c);
                }});
        };
        showConfirm(me,me.data("act"),"Activar");
        return;
    }else if(me.hasClass("desactivar")){
        var me=me;
        me.seguro=function(){

            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            model.desactivar(
                {success: function (data) {
                    showMessage("info","El registro se ha inactivado correctamente");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                    var table=$(".data_table").DataTable();
                    var row=table.row(me.closest("tr"));
                    row.remove();
                    table.draw();

                },error:function(a,b,c){
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                    verifyConnection(function(){
                        showMessage("error","No fue posible inactivar el registro");
                    });
                }});
        };
        showConfirm(me,me.data("des"),"Inactivar");
        return;

    }else{
        window[me.data("sec")].fetch(
            {success: function (data) {
                openSection(sec, data, "lista");
            }});
        return;
    }
    Backbone.history.navigate(navigate, { trigger: true });
}

function eventos_de_listaPersonalizados(me,idCliente){

    var sec=me.data("sec");

    var navigate;
    if(me.hasClass("abrir")){
        console.log("entra a abrir");
        navigate="#crear/"+sec+"/";

    }else if(me.hasClass("ver_datos")){
        navigate="#ver/"+sec+"/"+me[0].id;
    }else if(me.hasClass("desactivados")){
        console.log(idCliente);

        window[me.data("sec")].desactivadosPorCliente(function(){
            openSection(sec, window[me.data("sec")].puntosInactivosClientes, "lista", true,idCliente);
        },idCliente);

        return;
    }else if(me.hasClass("activar")||me.hasClass("Activar")){
        var me=me;
        me.seguro=function(){
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            model.activar(
                {success: function (data) {
                    showMessage("info","El registro ha sido activado correctamente");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                    var table=$(".data_table").DataTable();
                    var row=table.row(me.closest("tr"));
                    row.remove();
                    table.draw()

                },error:function(a,b,c){
                    showMessage("error","No fue posible activar el registro");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});

                    console.log(c);
                }});
        };
        showConfirm(me,me.data("act"),"Activar");
        return;
    }else if(me.hasClass("desactivar")){
        var me=me;
        me.seguro=function(){
            //alert("desactiva");
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            model.desactivar(
                {success: function (data) {
                    showMessage("info","El registro se ha inactivado correctamente");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                    var table=$(".data_table").DataTable();
                    var row=table.row(me.closest("tr"));
                    row.remove();
                    table.draw();

                },error:function(a,b,c){
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                    verifyConnection(function(){
                        showMessage("error","No fue posible inactivar el registro");
                    });
                }});
        };
        showConfirm(me,me.data("des"),"Inactivar");
        return;
    }else{
        var model =new window[me.data("sec")].model();
        model.traerPorCliente(function(){openSection("puntos",model.puntosClientes,"lista",undefined,idCliente);},idCliente);
        return;
    }
    //console.log(navigate);
    Backbone.history.navigate(navigate, { trigger: true });
}

function eventoslistaGrupo(me,idUsuario){
    var sec=me.data("sec");
    var navigate;
    if(me.hasClass("abrir")){
        navigate="#crear/"+sec+"/";
    }else if(me.hasClass("ver_datos")){
        navigate="#ver/"+sec+"/"+me[0].id;
    }else if(me.hasClass("desactivados")){
        window[me.data("sec")].desactivados(
            {
                success: function (data) {
                    openSection(sec, data, "lista", true);
                },
                error:function(){
                    verifyConnection(showMessage("error","No se encuentra la ruta"));
                }
            });
        return;
    }else if(me.hasClass("activar")||me.hasClass("Activar")){
        var me=me;
        me.seguro=function(){
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            $.ajax({
                url: "./rest/grupoSeleccion/" + me[0].id + "/activar/",
                type:"POST",
                success:function(){
                    table=me.closest("table");
                    dt=table.DataTable();
                    dt.row($(me).closest("tr")).remove();
                    $(me).closest("tr").remove();
                    dt.draw(false);
                    showMessage("info","Se ha activado");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                },
                error:function(){
                    showMessage("error","No ha sido posible activarlo");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                }
            });

        };
        showConfirm(me,me.data("act"),"Activar");
        return;
    }else if(me.hasClass("desactivar")){
        var me=me;
        me.seguro=function(){
            //alert("desactiva");
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            $.ajax({
                url: "./rest/grupoSeleccion/"+ me[0].id + "/",
                type:"DELETE",
                success:function(){
                    table=me.closest("table");
                    dt=table.DataTable();
                    dt.row($(me).closest("tr")).remove();
                    $(me).closest("tr").remove();
                    dt.draw(false);
                    showMessage("info","Se ha inactivado");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                },
                error:function(){
                    showMessage("error","No ha sido posible inactivarlo");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                }
            });

        };
        showConfirm(me,me.data("des"),"Inactivar");
        return;
    }else{
        window[me.data("sec")].fetch(
            {success: function (data) {
                openSection(sec, data, "lista");
            }});
        return;
    }
    Backbone.history.navigate(navigate, { trigger: true });
}

function eventoslistaArticulo(me,idUsuario){
    var sec=me.data("sec");
    var navigate;
    if(me.hasClass("abrir")){
        navigate="#crear/"+sec+"/";
    }else if(me.hasClass("ver_datos")){
        navigate="#ver/"+sec+"/"+me[0].id;
    }else if(me.hasClass("desactivados")){
        window[me.data("sec")].desactivados(
            {
                success: function (data) {
                    openSection(sec, data, "lista", true);
                },
                error:function(){
                    verifyConnection(showMessage("error","No se encuentra la ruta"));
                }
            });
        return;
    }else if(me.hasClass("activar")||me.hasClass("Activar")){
        var me=me;
        me.seguro=function(){
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            $.ajax({

                url : "./rest/articulo/" + me[0].id + "/activar",
                type:"POST",
                success:function(){
                    table=me.closest("table");
                    dt=table.DataTable();
                    dt.row($(me).closest("tr")).remove();
                    $(me).closest("tr").remove();
                    dt.draw(false);
                    showMessage("info","Se ha activado");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                },
                error:function(){
                    showMessage("error","No ha sido posible activarlo");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                }
            });
            /*
             model.activar(
             {success: function (data) {
             showMessage("info","El registro ha sido activado correctamente");
             $("#shadow_black").css({display:"none",opacity:0});
             $("#confirmar").css({display:"none"});
             var table=$(".data_table").DataTable();
             var row=table.row(me.closest("tr"));
             row.remove();
             table.draw()

             },error:function(a,b,c){
             showMessage("error","No fue posible activar el registro");
             $("#shadow_black").css({display:"none",opacity:0});
             $("#confirmar").css({display:"none"});

             console.log(c);
             }});
             */
        };
        showConfirm(me,me.data("act"),"Activar");
        return;
    }else if(me.hasClass("desactivar")){
        var me=me;
        me.seguro=function(){
            //alert("desactiva");
            me.parent().addClass("hidden");
            var model =new window[me.data("sec")].model();
            model.id=me[0].id;
            $.ajax({
                url : "./rest/articulo/" + me[0].id + "/inactivar",
                type:"POST",
                success:function(){
                    table=me.closest("table");
                    dt=table.DataTable();
                    dt.row($(me).closest("tr")).remove();
                    $(me).closest("tr").remove();
                    dt.draw(false);
                    showMessage("info","Se ha inactivado");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});

                },
                error:function(){
                    showMessage("error","No ha sido posible inactivarlo");
                    $("#shadow_black").css({display:"none",opacity:0});
                    $("#confirmar").css({display:"none"});
                }
            });
            /*
             /*
             model.desactivar(
             {success: function (data) {
             showMessage("info","El registro se ha inactivado correctamente");
             $("#shadow_black").css({display:"none",opacity:0});
             $("#confirmar").css({display:"none"});
             var table=$(".data_table").DataTable();
             var row=table.row(me.closest("tr"));
             row.remove();
             table.draw();

             },error:function(a,b,c){
             $("#shadow_black").css({display:"none",opacity:0});
             $("#confirmar").css({display:"none"});
             verifyConnection(function(){
             showMessage("error","No fue posible inactivar el registro");
             });
             }});
             */
        };
        showConfirm(me,me.data("des"),"Inactivar");
        return;
    }else{
        window[me.data("sec")].fetch(
            {success: function (data) {
                openSection(sec, data, "lista");
            }});
        return;
    }
    Backbone.history.navigate(navigate, { trigger: true });
}