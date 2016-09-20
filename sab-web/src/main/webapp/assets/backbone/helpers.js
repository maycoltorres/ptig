function creator_tables(data, columnas,dest) {
    if(dest.find("table").length>0){
        var tbody=dest.find("table").find("tbody");
        $(data).each(function () {
            var object = this;
            tbody.append("<tr></tr>");
            var tr = tbody.children().last();
            $(columnas).each(function () {
                var col = this;
                var td = $("<td></td>").appendTo(tr);
                if(existWithVal(col.class)){
                    td.addClass(col.class);
                }
                if(existWithVal(col.proper)){
                    if (one) {
                        theadRow.append($("<th class='centrate'></th>").html(col.head));
                    }
                    if(existWithVal(col.convert)){
                        if(object[col.proper]==0){
                            td.append("No");
                        }else if(object[col.proper]==1){
                            td.append("Si");
                        }else{
                            td.append(object[col.proper]);
                        }
                    }else if(!(col.mask==undefined)){
                        if(object[col.proper]==1){
                            td.append(col.mask['true']);
                        }else{
                            td.append(col.mask['false']);
                        }
                    }else{
                        td.append(object[col.proper]);
                    }
                }else{
                    if (one) {
                        //console.log(existWithVal(col.proper));
                        var th=$("<th class='centrate'></th>");
                        th.html(col.head);
                        th.addClass(col.class);
                        theadRow.append(th);
                    }
                    td.addClass("centrate");
                    var obj=$(col.embed);
                    obj.attr("id",object.id);
                    if(obj.is("checkbox")){
                        if(existWithVal(col.proper)) {
                            if (object[col.proper] > 0) {
                                obj.prop("checked", true);
                            }
                        }
                    }
                    if(existWithVal(col.atributo)) {
                        obj.attr("data-txt",object[col.atributo]);
                    }
                    td.append(obj);
                }
            });
        });
        return;
    }
    if (!existWithVal(data)&&!existWithVal(columnas)) {
        console.log("data ha llegado sin valor");
        return;
    }
    var me = this;
    var clone = $("#table_origin").clone().removeAttr("id");
    clone.attr("class","data_table");
    var tbody = clone.children("tbody");
    var theadRow = clone.children("thead").children();
    var one = true;

    $(data).each(function () {
        tbody.append("<tr></tr>");
        var tr = tbody.children().last();
        var object = this;
        $(columnas).each(function () {
            var col = this;
            var td = $("<td></td>").appendTo(tr);
            if(existWithVal(col.class)){
                td.addClass(col.class);
            }
            if(existWithVal(col.proper)){
                if (one) {
                    var th=$("<th class='centrate'></th>");
                    th.html(col.head);
                    th.addClass(col.class);
                    theadRow.append(th);
                }
                if(existWithVal(col.convert)){
                    if(object[col.proper]==0){
                        td.append("No");
                    }else if(object[col.proper]==1){
                        td.append("Si");
                    }else{
                        td.append(object[col.proper]);
                    }
                }else if(!(col.mask==undefined)){
                    if(object[col.proper]==1){
                        td.append(col.mask['true']);
                    }else{
                        td.append(col.mask['false']);
                    }
                }else{
                    td.append(object[col.proper]);
                }
            }else{
                if (one) {
                    theadRow.append($("<th class='centrate'></th>").html(col.head));
                }
                td.addClass("centrate");
                var obj=$(col.embed);
                obj.attr("id",object.id);
                if(obj.is("checkbox")){
                    if(existWithVal(col.proper)) {
                        if (object[col.proper] > 0) {
                            obj.prop("checked", true);
                        }
                    }
                }
                if(existWithVal(col.atributo)) {
                    obj.attr("data-txt",object[col.atributo]);
                }
                td.append(obj);
            }
        });
        one = false;
    });
    dest.html(clone);
    clone.addClass("dataTables");
    return clone;
}

function showMessage(type,text){
    var message=$("#message");
    if(!(message.children().size()<1)){
        message.children().remove();
    }
    var icon_class="";
    switch(type){
        case "good":
            type="message_good";
            icon_class="i-checkmark-circle";
            break;
        case "info":
            type="message_info";
            icon_class="i-notification";
            break;
        case "warning":
            type="message_warning";
            icon_class="i-warning";
            break;
        case "error":
            type="message_error";
            icon_class="i-warning";
            break;
        default:{
            return;
        }
    }
    var clone=$("#message_origin").clone().removeAttr("id").addClass("message").addClass(type);

    clone.appendTo(message);
    var hijos=clone.children().children();
    hijos.eq(0).addClass(icon_class);
    hijos.eq(1).html(text);
    hijos.eq(2).click(function(){
        clone.remove();
    });
    clone.animate({height:"43px"},400).delay(5000).animate({heigth:"0"},800,function(){
        $(this).animate({height:0},400,function(){
            $(this).remove();
        });
    });
}

function fillFloating(label,titulo,url,columns,save,creator){
    myAjax({
        url:url,
        success:function(data){
            var div=$("<div></div>");
            creator_tables(data,columns,div);
            if(existWithVal(label.data("checks"))){
                var table = div.children().dataTable();
                var ids=label.data("checks");
                for (var x in ids){
                    table.$("#"+ids[x]).attr("checked","checked");
                }
            }

            creatorFloating(titulo,"Guardar seleccion",div.html(),"table",function(){
                var checks=floating_return_check();
                var arrayIds=[];
                var arrayVals=[];


                if(save=="label"){
                    $(checks).each(function(){
                        arrayIds.push(this.id);
                        arrayVals.push($(this).attr("data-txt"));
                    });
                    label.data("checks",arrayIds);
                    arrayVals=arrayVals.join(", ");
                    label.html(arrayVals);
                    if(!existWithVal(label.data("checks"))){
                        label.html("0 Seleccionados");
                    }
                }else if(save=="table"){
                    columns=[];
                    $(checks).each(function(){
                        columns.push({id:this.id,val:$(this).attr("data-txt")});
                        arrayVals.push($(this).attr("data-txt"));
                    });
                    arrayVals=arrayVals.join(", ");
                    label.html(arrayVals);
                    if(!existWithVal(arrayVals)){
                        label.html("0 Seleccionados");
                    }
                    label.data("columns",columns);
                }else if(save=="one_result"){
                    if($(checks).size()>1){
                        $(".clon_floating").addClass("hidden");
                        creatorFloating("Error","Aceptar",'<span class="error_fill">Debe seleccionar solo 1</span>',"form",function(){
                            close_floating();
                            $(".clon_floating").removeClass("hidden");
                        });
                        return;
                    }
                    var other_vals=[];

                    arrayIds.push($(checks)[0].id);
                    arrayVals.push($(checks).attr("data-txt"));
                    $(checks).closest("tr").find("td").each(function(){
                        other_vals.push($(this).html());
                    });
                    label.data("other",other_vals);
                    label.data("checks",arrayIds);
                    arrayVals=arrayVals.join(", ");
                    label.html(arrayVals);
                    if(!existWithVal(label.data("checks"))){
                        label.html("0 Seleccionados");
                    }

                }
                close_floating();
                var floating=$(".clon_floating").last();
                if(other_vals[2]=="Abierta"||other_vals[2]=="abierta"){
                    floating.find(".otro").removeClass("hidden");
                }else{
                    floating.find(".otro").addClass("hidden");
                }

            });
        },
        error:function(){
            verifyConnection(function(){close_floating();$(".clon_floating").remove();showMessage("error","Error al realizar la operacion")});
        }
    });
}

function fillSelect(data,element,property,class_,prex,funcion){
    element.prop("disabled",false).parent().removeClass("loading_control");
    element.html("");
    if(!(existWithVal(data))||!(existWithVal(element))&&!(existWithVal(property))){
        return;
    }

    var html='<option class="empty" selected="selected"></option>';
    if(prex==null||prex==undefined){
        prex="";
    }
    if(class_==null||class_==undefined){
        class_="tipo";
    }
    $(data).each(function(){
        var a="";
        var estandar="";

        if(this.estandar==1){
           estandar=" es_estandar";
        }
        if(!(this[class_]==undefined)&&!(this[class_]==null)&&!(this[class_]=="")){
            a=this[class_];
        }
        html=html+('<option id="opt_'+this.id+'" value='+this.id+' class="'+prex+a+estandar+'">'+this[property]+'</option>');
        //console.log(html);
    });
    element.append(html);
    if(!(funcion==undefined)){
        funcion();
    };
    if(element.find("option").not(".empty").length==1){
        element.find("option").not(".empty").prop("selected",true);
        element.trigger("change");
    }
}

function wizard(validate,submit,id){
    if(!(id=="")){
        $(".wizard").data("id",id);
    }
    var steps=$(".wizard .step:not(.ignore)");
    steps.first().addClass("active");
    $(".wizard .wstep:first").addClass("active");
    if(steps.size(":not(.ignore)")<2){
        $(".form-actions").addClass("hidden");
    }else{
        $(".form-actions").removeClass("hidden");

    }
    $(".form-actions button").off("click");
    $(".form-actions button").click(function(){

        var viables= $(".step:not(.ignore)");
        var active=viables.filter(".active");
        var index=viables.index(active);

        if($(this).hasClass("end")&&$(this).hasClass("next")){
                submit();
                return false;
        }

        if($(this).hasClass("next")){
            if(!validate(index)){
                return false;
            }
        }

        $(this).siblings().removeClass("hidden");
        if($(this).hasClass("back")){

            charge_back(index);
            if(index<2){
                $(this).addClass("hidden");
            }

            active.removeClass("active");
            var new_index=viables.eq(index-1).addClass("active").index();
            $(".wstep.active").removeClass("active");

            $(this).siblings().find("b").html("Siguiente ");
            $(this).siblings().removeClass("end");
        }else{
            if(viables.eq(index+2).length<1){
                $(this).find("b").html("Guardar y terminar");
                $(this).addClass("end");
            }else{
                $(this).find("b").html("Siguiente ");
                $(this).removeClass("end");
            }

            active.removeClass("active");
            var new_index=viables.eq(index+1).addClass("active").index();
            $(".wstep.active").removeClass("active");
        }
        $(".wstep").eq(new_index).addClass("active");
    });

    $(".checks").find("input[type='checkbox']:checked").not(".no_step").each(function(){
        var index=$(this).closest("li").index()+1;
        if($(this).is(":checked")){
            $(".wstep").eq(index).removeClass("ignore");
            $(".step").eq(index).removeClass("ignore");
        }else{
            $(".wstep").eq(index).addClass("ignore");
            $(".step").eq(index).addClass("ignore");
        }
        var steps=$(".wizard .step:not(.ignore)");
        if(steps.size(":not(.ignore)")<2){
            $(".form-actions").addClass("hidden");
        }else{
            $(".form-actions").removeClass("hidden");
            steps.first().addClass("active");
            $(".wizard .wstep:first").addClass("active");
        }
    });
}

function creatorFloating(titulo,btn_text,html,type,submit){
    var clon=$("#content_floating").clone(true,true);
    clon.addClass("clon_floating");
    if($(".clon_floating").length>=2){
        return;
    }
    if(type=="table"){
        clon.removeClass("floating_form").addClass("floating_table");
    }else{
        clon.removeClass("floating_table").addClass("floating_form");
    }
    $("#shadow_black").stop(false,true);
    $("#shadow_black").css({display:"block",opacity:0.7});
    clon.css({display:"block"});
    clon.appendTo("body");
    clon.find(".panel-heading h3").html(titulo);
    var container=clon.find(".panel-body");
    container.html(html);
    var button=$('<input type="button" id="footer_submit" class="btn" value="'+btn_text+'">');
    container.append(button);
    if(!(submit==undefined)&&!(submit==null)){
        button.click(submit);
    }
    clon.table=clon.find("table");
    clon.datatable=clon.find("table").dataTable();
    clon.button=button;
    clon.find(".tableUnits").click(function(){
        showUnits();
    });
    return clon;
}

function close_floating(){
    $(".clon_floating").last().remove();
    if($(".clon_floating").length<1){
        $("#shadow_black").css({display:"none",opacity:0});
    }
}

function floating_return_check(){
    var table=$("#content_floating table").dataTable();
    var checkboxs = table.$('input').filter(":checked");
    return checkboxs;
}

function floating_return_selected(){
    var table=$("#content_floating table").dataTable();
    var selectetboxs = table.$('option').filter(":selected");
    return selectetboxs;
}

function save_floating(urlSave,ids,campos,valores,posSaving){
    if($(".table_in_floating")>1){
        showMessage("error","Debe cerrar primera la tabla");
    }
    if(!existWithVal(ids)){
        var ids=[null];
    }
    //console.log(ids);
    var json=[];
    for(var i=0;i<ids.length;i++){
        var tabla={};
        tabla["id"]=ids[i];
        for(var e=0;e<valores.length;e++){
            tabla[campos[e]]=valores[e];
        }
        json.push(tabla);
    }
    $.ajax({
        url:urlSave,
        type:"POST",
        contentType:"application/json",
        data: JSON.stringify(json),
        success:function(data){
            close_floating();
            posSaving();
        },
        error:function(){
            showMessage("error","No fue posible guardar la tabla");
        }
    });
}

function extraerSeleccionados(el){//solo aplica para checkbox y radios
    var datatable=el.find("table").DataTable();
    var seleccionados = datatable.$("input:checked");
    if(seleccionados.length>0){
        return seleccionados;
    }else{
        seleccionados= []
        return seleccionados;
    }
}

function extraerIds(el,classId,flag_html){//solo aplica para checkbox y radios

    var table=el.find("table").clone();
    el.html();
    datatable=table.DataTable();

    if(flag_html==true){
        var ids = datatable.$("."+classId).html();
    }else{
        var ids = datatable.$("."+classId).attr("id");
    }
    if(ids==undefined){
        ids=[];
    }

    if(ids.length>0){
        return ids;
    }else{
        ids=[];
        return ids;
    }
}

function eliminarDeFlo(flo,ob,properOb,inputAttr){

    var table=flo.find("table").DataTable();

    if(properOb==undefined){
        properOb="id";
    }
    if(inputAttr==undefined){
        inputAttr="id";
    }

    $.each(ob,function(){
        var row=flo.find("input["+inputAttr+"='"+this[properOb]+"']").closest("tr");
        table.row(row).remove();
        row.remove();
    });

    if(flo.find("tbody").find("td").not(".dataTables_empty").length>0){
        table.draw();
    }

}

function extraerIds2(el,classId,flag_html){//solo aplica para checkbox y radios

    var table=el.find("table").clone();
    el.html();
    datatable=table.DataTable();

    if(flag_html==true){
        var tds = datatable.$("."+classId);
        var ids=[];
        tds.each(function(index,value){
            ids.push({id:$(this).html()})
        });
    }else{
        var tds = datatable.$("."+classId);
        var ids=[];
        tds.each(function(index,value){
            ids.push({id:$(this).attr("id")});
        });
    }
    if(ids==undefined){
        ids=[];
    }

    if(ids.length>0){
        return ids;
    }else{
        ids=[];
        return ids;
    }
}

function seleccionaDeFlo(flo,ob,properOb,inputAttr){

    var table=flo.find("table").DataTable();

    if(properOb==undefined){
        properOb="id";
    }
    if(inputAttr==undefined){
        inputAttr="id";
    }

    $.each(ob,function(){
        flo.find("input["+inputAttr+"='"+this[properOb]+"']").prop("checked",true);
        //table.row(row).remove();
        //row.remove();
    });
}

function validaEquivalencia(){
    if(window.articulo.inventario == true && window.articulo.inventario 
    		&& window.articulo.inventario.id_unidad>0 
    		&& window.articulo.venta == true && window.articulo.ventaEnt){
        var result={};
        result.equivalentes=false;

        var unidad_inv = $.grep(window.articulo.inventarioEnt.id_unidad,function(u){
            return u.id == window.articulo.inventarioEnt.id_unidad;
        })[0];
        var unidad_ven = $.grep(window.articulo.ventaEnt.id_unidad,function(u){
            return u.id == window.articulo.ventaEnt.id_unidad;
        })[0];
        result.inventario=unidad_inv;
        result.venta=unidad_ven;

        if(window.articulo.inventario.id_unidad == window.articulo.inventario.id_unidad.unidadId){
            result.equivalentes=true;
            result.equivalencia=1;
            return result;
        }

        if(unidad_inv.type==unidad_ven.type){
           result.equivalentes=true;
           result.equivalencia=convertidor_de_unidades(unidad_inv.id,unidad_ven.id,1);
       }
        return result;
    }
    return false;
}


function charge_back(index){
    if(window.globalModule=="articulo"){
        ///console.log(step+"line 604");
        switch(index){
            case 1:
                cargarModuloBasico(window.articulo.id, window.actionGlobal);

            case 2:
                cargarModuloInventario(window.articulo.id, window.actionGlobal);
                break;
            case 3:
                cargarModuloVenta(window.articulo.id, window.actionGlobal);
                break;
            case 4:
                cargarModuloReceta(window.articulo.id, window.actionGlobal);
                break;
            case 5:
                cargarModuloEmpaque(window.articulo.id, window.actionGlobal);
                break;
        }
    }
}

function round(value, decimals) {
    return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
}
