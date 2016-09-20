//aqui estaran los eventos tratan con ajax
//carga la onfiguracion de que se botones  van a estar disponibles
function mostrarConfiguracion_articulos(id,action){
    $.ajax({
        url: "./rest/articulo/configuracion/",
        type:"GET",
        success:function(data){
            var mostrar=true;
            if(data.visto){
	            mostrar=false;
	            var hideE=data.elementosOcultos;
	            $(hideE).val("");
	            $(hideE).closest(".form-group").addClass("configNotShow");
	            if(hideE.indexOf("#basico_codigo")>-1){
	                $("#floating_configuracion").find("#basico_cb").removeAttr("checked");
	            }
	            if(hideE.indexOf("#unidad_a")>-1){
	                $("#floating_configuracion").find("#basico_ua").removeAttr("checked");
	            }
	            if(hideE.indexOf("#maximos_tableCross")>-1){
	                $("#floating_configuracion").find("#basico_mn").removeAttr("checked");
	            }
            }    
                        
            if(mostrar){
                guardarConfiguracion_articulos(id,action);
            }
        },
        error:function(a,b,c){
            console.log(a);
            console.log(b);
            console.log(c);
            verifyConnection(function(){showMessage("error","No fue posible traer la configuracion de vista")});
        }
    });
}

//guarda la configuracion de la vista
function guardarConfiguracion_articulos(id,action){
    var flo=creatorFloating("Configuracion de Articulos","Guardar configuracio&#769;n",$("#floating_configuracion").html(),"table",function(){
        var data={
            idCliente:usuario.get("idCliente"),
            visto:1,
            elementosOcultos:"",
            usaCodigoBarras:flo.find("#basico_cb").is(':checked'),
            usaUnidadAlterna:flo.find("#basico_ua").is(':checked'),
            usaMaximosMinimos:flo.find("#basico_mn").is(':checked')
        };
        var hideElements=[];
        $(".clon_floating").last().find("input[type='checkbox']").each(function(){
            if($(this).is(":checked")){
                $($(this).data("el")).closest(".form-group").removeClass("configNotShow");
                $("#floating_configuracion").find("#"+this.id).attr("checked","checked");
            }else{
                $("#floating_configuracion").find("#"+this.id).removeAttr("checked");
                $($(this).data("el")).closest(".form-group").addClass("configNotShow");
                $($(this).data("el")).val("");
                hideElements.push($(this).data("el"));
            }
        });
        data.elementosOcultos=hideElements.join(",");
        //console.log(data);
        $.ajax({
            url:"./rest/articulo/configuracion/",
            contentType: 'application/json; charset=utf-8',
            type:"POST",
            data:JSON.stringify(data),
            success:function(data){
                showMessage("good","Se ha guardado su configuracio&#769;n");
            },
            error:function(a,b,c){
                console.log(a);
                console.log(b);
                console.log(c);
                verifyConnection(function(){showMessage("error","No fue posible guardar su configuracion")});
            }
        });
        close_floating();
    });
}