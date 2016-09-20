<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="content" class="isCollapse">
    <div id="dialog-direction" class="hidden">
        <div class="form-group">
            <label class="col-lg-12 control-label" >Nomenclatura</label>
            <div class="col-lg-12">
                <select id="type_dir" class="col-lg-12 form-control ui-wizard-content" requiered>
                    <option></option>
                    <option value="cl">Calle</option>
                    <option value="kr">Carrera</option>
                    <option value="tr">Transversal</option>
                    <option value="dg">Diagonal</option>
                    <option  value="va">Variante</option>
                    <option value="vi">Via</option>
                    <option value="k">Kilometro</option>
                    <option value="otra">Otra nomenclatura</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="hidden col-lg-12 control-label" >Especifique cual</label>
            <div class="col-lg-12">
                <input class="hidden form-control ui-wizard-content" id="otra_1" type="text" placeholder="vereda" maxlength="40" title="Ingrese cual" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Numero</label>
            <div class="col-lg-12">
                <input id="direccion_1" class="form-control ui-wizard-content valida_numeros" type="text" placeholder="numero" maxlength="40" title="Ingrese unicamente digitos" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Letra</label>
            <div class="col-lg-12">
                <input class="letra form-control ui-wizard-content valida_letras" type="text" placeholder="A bis" maxlength="6" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Cardinal</label>
            <div class="col-lg-12">
                <input class="cardinal form-control ui-wizard-content valida_letras" type="text" placeholder="sur" maxlength="9" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Con nomenclatura</label>
            <div class="col-lg-12">
                <select id="type_dir_2" class="col-lg-12 form-control ui-wizard-content" requiered>
                    <option></option>
                    <option value="cl">Calle</option>
                    <option value="kr">Carrera</option>
                    <option value="tr">Transversal</option>
                    <option value="dg">Diagonal</option>
                    <option  value="va">Variante</option>
                    <option value="vi">Via</option>
                    <option value="k">Kilometro</option>
                    <option value="otra">Otra nomenclatura</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="hidden col-lg-12 control-label">Especifique cual</label>
            <div class="col-lg-12">
                <input id="otra_2" class="hidden cardinal form-control ui-wizard-content valida_letras" type="text" placeholder="vereda" maxlength="40" title="Ingrese cual" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Numero</label>
            <div class="col-lg-12">
                <input id="direccion_2" class="form-control ui-wizard-content valida_numeros" type="text" placeholder="numero" maxlength="40" title="Ingrese unicamente digitos" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Letra</label>
            <div class="col-lg-12">
                <input class="letra form-control ui-wizard-content valida_letras" type="text" placeholder="A bis" maxlength="6" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Cardinal</label>
            <div class="col-lg-12">
                <input class="cardinal form-control ui-wizard-content valida_letras" type="text" placeholder="sur" maxlength="9" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Numero de placa</label>
            <div class="col-lg-12">
                <input id="placa" class="form-control ui-wizard-content valida_letras" type="text" placeholder="34 a bis-45 g norte" maxlength="10" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-lg-12 control-label">Otras indicaciones</label>
            <div class="col-lg-12">
                <textarea id="indicaciones" class="form-control ui-wizard-content" type="text" placeholder="Al frente de la alcadia" maxlength="4000"></textarea>
            </div>
        </div>
    </div>
    <div class="wrapper">
        <div class="crumb">
            <ul id="breadcrumb" class="breadcrumb">
                <li><a id="miga" data-href="#" class="not_link"><i class="icon16 i-home-4"></i>Inicio</a></li>
            </ul>
        </div>
        <div id="orgin_resources" class="hidden">
            <div id="miga_m">
                <li><a id="miga" href="#"><i class="icon16 i-home-4"></i>Inicio</a></li>
                <li><a class="not_link"><i class="icon16 {{icon_miga}}"></i>{{section_m}}</a></li>
            </div>
            <table id="table_origin" class="table_copy">
                <thead><tr></tr></thead>
                <tbody></tbody>
                <tfoot></tfoot>
            </table>
            <div id="content_origin" class="panel panel-default section_container">
                <div id="message_origin">
                    <span>
                        <i class="icon32"></i>
                        <label></label>
                        <i class="icon32  i-close-3 pull-right"></i>
                    </span>
                </div>
                <script type="text/html" id="home_m">
                    <div class="row">

                        <div id="welcome">
                            <div id="home" class="section_container current_section">
                                <span>Bienvenido</span>
                                <div>
                                    <div><label>Escoge una opcion del menu</label></br>
                                        <img src="<c:url value='assets/images/arrow_left.png'/>"/>
                                    </div>
                                    <div>
                                        <noscript>
                                            <span>Debe tener activado Javascript para utilizar esta pagina</span>
                                        </noscript>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="section_m">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{titulo_heading}}</h1>
                    </div>
                    <div class="row">
                        <div id="{{id_section_m}}" class="panel panel-default">
                            <div class="panel-heading">
                                <div data-sec="{{section_d}}"
                                     class="icon btn btn-info btn-sm {{clases_btn_crear}} action"><i
                                        class="icon20 {{icon_btn_crear}}"> {{texto_btn_crear}}</i></div>
                                <div data-sec="{{section_d}}" data-dest="des"
                                     class="icon btn btn-sm desactivados hover_action {{clases_btn_des}} action"><i
                                        class="icon20 i-eye-blocked"> {{texto_btn_des}}</i></div>
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                            </div>
                        </div>
                    </div>
                </script>

                <script type="text/html" id="clientes_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{accion}}</h1>
                    </div>
                    <div class="row">
                        <div id="cliente_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="wizard form-horizontal wizard-vertical">
                                    <div class="msg"></div>

                                    <div class="wizard-steps show container_absolute">
                                        <div class="vertical_centrator">
                                            <div class="wstep active">
                                                <div class="donut"><i class="icon64 i-man"></i></div>
                                                <span class="txt">Datos basicos</span>
                                            </div>
                                            <div class="wstep">
                                                <div class="donut"><i class="icon64 i-cog"></i></div>
                                                <span class="txt">Ajustes</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="wrap">
                                        <div class="step  active" data-step-title="Datos basicos" >
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Razo&#769;n Social</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_rs" maxlength="100" class="form-control ui-wizard-content" name="firstname" type="text" value="{{cliente.nombre}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Nit</label>


                                                <div class="col-lg-10">
                                                    <input id="clientes_nit" maxlength="19" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.nit}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Di&#769;gito de chequeo</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_dc" maxlength="1" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.digitoChequeo}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Tele&#769;fono</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_telefono" maxlength="25"  class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.telefono}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Extensio&#769;n</label>
                                                <div class="col-lg-10">
    {{#ifm cliente.extension}}
    <input id="clientes_extension" maxlength="4" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.extension}}">
                                                    {{else}}
                                                    <input id="clientes_extension" maxlength="4"  class="form-control ui-wizard-content" name="lastname" type="text" value="">
                                                    {{/ifm}}
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Celular</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_celular" maxlength="25"  class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.celular}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Correo</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_correo" maxlength="100"  class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.email}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Direccio&#769;n</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_direccion" readonly maxlength="100" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.direccion}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Propietario</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_duenon" maxlength="100" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.nombreDueno}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Correo del propietario</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_cd" maxlength="100" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.mailDueno}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Telefono del propietario</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_tp" maxlength="25" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.celularDueno}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Nombre del representante legal</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_nr" maxlength="100"  class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.nombreRepresentante}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Documento del representante</label>
                                                <div class="col-lg-10">
                                                    <input id="clientes_dr" maxlength="15" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.documentoRepresentante}}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Pai&#769;s</label>
                                                <div class="col-lg-10 loading_control">
                                                    <select id="clientes_pais" class="charge_select form-control ui-wizard-content " name="lastname" type="text" disabled="disabled">
                                                    </select>
                                                    <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Departamento</label>
                                                <div class="col-lg-10 loading_control" >
                                                    <select id="clientes_departamento" class="charge_select form-control ui-wizard-content" name="lastname" type="text" disabled="disabled"></select>
                                                    <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Municipio</label>
                                                <div class="col-lg-10 loading_control">
                                                    <select id="clientes_municipio" class="charge_select form-control ui-wizard-content " name="lastname" type="text" disabled="disabled"></select>
                                                    <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="step " data-step-title="Configuracion" >

                                            <div class="form-group ">
                                                <ul class="padding_li_check">
                                                    <li class="checks_cliente_li_2">
                                                        <label class="control-label">Puede manejar receta diferencial por punto</label>
                                                        <input id="check_receta_punto" class="form-control " type="checkbox">
                                                    </li>
                                                    <li class="checks_cliente_li_2">
                                                        <label class="control-label">Puede manejar receta diferencial por canal</label>
                                                        <input id="check_receta_canal" class="form-control " type="checkbox">
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="form-group ">
                                            <ul class="padding_li_check" >
                                            <li class="checks_cliente_li_2">
                                            <label class="control-label">Puede manejar empaque diferencial por punto</label>
                                            <input id="check_empaque_punto" class="form-control " type="checkbox">
                                            </li>
                                            <li class="checks_cliente_li_2">
                                            <label class="control-label">Puede manejar empaque diferencial por canal</label>
                                            <input id="check_empaque_canal" class="form-control " type="checkbox">
                                            </li>
                                            </ul>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-lg-12 centrate label_p2">
                                                    <label>Impuesto y regimen que utiliza el cliente</label><i id="cliente_regimenTable" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="cliente_regimen_tableCross"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12 centrate label_p">
                                                    <label>Asignar canales al cliente</label><i id="canales_config_cliente" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="cliente_canales_config_tableCross"></div>
                                                </div>
                                            </div>




                                            <div class="form-group">
                                                <div class="col-lg-12 centrate label_p">
                                                    <label>Como opera el impuesto</label><i id="cliente_canalTable" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">

                                                <div class="col-lg-12 centrate">
                                                    <div class="container_check_label2">
                                                        <input type="checkbox" id="imp_eq_ch" class="form-control ">
                                                        <label class="label_with_check">El impuesto es igual para todos los canales</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="cliente_canal_tableCross"></div>
                                                </div>
                                            </div>
                                        </div>


                                        <div id="container_cliente_regimen" class="hidden">
                                            <form>
                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label" >Pais</label>
                                                    <div class="col-lg-12">
                                                        <select id="container_cliente_pais" class="form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label" >Impuesto</label>
                                                    <div class="col-lg-12">
                                                        <select id="container_cliente_impuesto" class="form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label" >Regimen</label>
                                                    <div class="col-lg-12">
                                                        <select id="container_regimen" class="form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <div id="container_cliente_canal" class="hidden">
                                            <form>
                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label" >Canal</label>
                                                    <div class="col-lg-12">
                                                        <select id="container_canal" class="form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label" >Impuesto</label>
                                                    <div class="col-lg-12">
                                                        <select id="container_canal_impuesto" class="form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label centrate" >Este es el impuesto mas usado</label>
                                                    <div class="col-lg-12">
                                                        <input id="imp_mas_usado" type="checkbox" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-lg-12 control-label centrate" >Maneja unica tarifa por canal</label>
                                                    <div class="col-lg-12">
                                                        <input id="unica_tarifa" type="checkbox" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group hidden">
                                                    <label class="col-lg-12 control-label hidden" >Tarifa</label>
                                                    <div class="col-lg-12">
                                                        <select id="tarifa_select" class="form-control ui-wizard-content"></select>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </form>
                                <div class="actions form-actions full">
                                    <button class="btn pull-left ui-wizard-content ui-formwizard-button back hidden" type="button" value="Back"><i class="icon16 i-arrow-left-2"> <b>Atras</b></i></button>
                                    <button data-last="Finish" class="btn pull-right ui-wizard-content ui-formwizard-button next" type="button" value="Next"><b>Siguiente </b><i class="icon16 i-arrow-right-3"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="puntos_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{accion}}</h1>
                    </div>
                    <div class="row">
                        <div id="chargindDiv" class="hidden">
                            <div>
                                <label>Cargando</label>
                                <img id="connection_cargando" src="assets/images/loader.gif">
                            </div>
                        </div>

                        <div id="punto_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4>

                                </h4>
                            </div>
                            <div class="panel-body">
                                <form class="wizard form-horizontal wizard-vertical">
                                    <div class="msg"></div>
                                        <div class="wizard-steps show container_absolute">
                                            <div class="vertical_centrator">
                                                <div class="wstep">
                                                    <div class="donut"><i class="icon32  i-man"></i></div>
                                                    <span class="txt">Datos ba&#769;sicos</span>
                                                </div>
                                                <div class="wstep">
                                                    <div class="donut"><i class="icon32   i-menu-6"></i></div>
                                                    <span class="txt">Configuracion</span>
                                                </div>
                                                <div class="wstep">
                                                    <div class="donut"><i class="icon32 i-tag-8"></i></div>
                                                    <span class="txt">Marcas y bodegas</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrap">
                                            <div id="basico" class="step">
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Nombre del punto</label>
                                                    <div class="col-lg-10">
                                                        <input value="{{punto.nombre}}" id="nombrePunto" maxlength="100" required class="form-control ui-wizard-content valida_numeros" type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Direccion</label>
                                                    <div class="col-lg-10">
                                                        <input value="{{punto.direccion}}" readonly id="clientes_direccion" maxlength="100" required class="form-control ui-wizard-content valida_letras" type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Telefono 1</label>
                                                    <div class="col-lg-2">
                                                        <input  value="{{punto.telefono}}" id="telefonoPunto"  maxlength="25" required class="form-control ui-wizard-content valida_numeros" type="text">
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label class="col-lg-3 control-label" >Ext</label>
                                                        <input value="{{punto.extension}}" id="extensionPunto" maxlength="4" required class=" ui-wizard-content valida_numeros col-lg-3" type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Telefono 2</label>
                                                    <div class="col-lg-2">
                                                        <input  value="{{punto.telefono2}}" id="telefono2Punto" maxlength="25" required class="form-control ui-wizard-content valida_numeros valida_numeros" type="text">
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label class="col-lg-3 control-label" >Ext</label>
                                                        <input value="{{punto.extension2}}" id="extension2Punto" maxlength="4" required class="col-lg-3  ui-wizard-content valida_numeros col-lg-3" type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Centro Costos</label>
                                                    <div class="col-lg-10">
                                                        <input value="{{punto.centro_costo_id}}" id="centro_costo_idPunto" maxlength="3" required class="form-control ui-wizard-content valida_numeros" type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Tipo Negocio</label>
                                                    <div class="col-lg-10">
                                                        <select class="form-control ui-wizard-content" id="tipo_negocio_idPunto">
                                                            <option class="empty"></option>
                                                            <option value="1">Bar</option>
                                                            <option value="2">Restaurante</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Pais</label>
                                                    <div class="col-lg-10">
                                                        <select id="pais" class="charge_select  form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Departamento</label>
                                                    <div class="col-lg-10">
                                                        <select id="departamento" class="charge_select  form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Municipio</label>
                                                    <div class="col-lg-10">
                                                        <select id="municipio" class="charge_select  form-control ui-wizard-content" disabled="disabled"></select>
                                                    </div>
                                                </div>

                                                <div id="floating_direccion" class="hidden">
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Nomenclatura</label>
                                                        <div class="col-lg-10">
                                                            <select id="type_dir" requiered>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Cual</label>
                                                        <div class="col-lg-10">
                                                            <input class="form-control ui-wizard-content hidden" id="otra_1" type="text" placeholder="vereda" maxlength="40" title="Ingrese cual" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Numero</label>
                                                        <div class="col-lg-10">
                                                            <input id="direccion_1" class="form-control ui-wizard-content valida_numeros" placeholder="numero" maxlength="40" title="Ingrese unicamente digitos" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Letra</label>
                                                        <div class="col-lg-10">
                                                            <input placeholder="A bis" id="dir_letra" class="form-control ui-wizard-content valida_letras"  maxlength="6" title="Ingrese unicamente digitos" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Cardinal</label>
                                                        <div class="col-lg-10">
                                                            <input placeholder="Sur" id="dir_cardinal" class="form-control ui-wizard-content valida_letras"  maxlength="9" title="Ingrese unicamente digitos" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Nomenclatura</label>
                                                        <div class="col-lg-10">
                                                            <select id="type_dir_2" requiered>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Cual</label>
                                                        <div class="col-lg-10">
                                                            <input class="form-control ui-wizard-content hidden" id="otra_2" type="text" placeholder="vereda" maxlength="40" title="Ingrese cual" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Numero</label>
                                                        <div class="col-lg-10">
                                                            <input id="direccion_2" class="form-control ui-wizard-content valida_numeros" placeholder="numero" maxlength="40" title="Ingrese unicamente digitos" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Letra</label>
                                                        <div class="col-lg-10">
                                                            <input placeholder="A bis" id="dir_letra_2" class="form-control ui-wizard-content valida_letras"  maxlength="6" title="Ingrese unicamente digitos" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Cardinal</label>
                                                        <div class="col-lg-10">
                                                            <input placeholder="Norte" id="dir_cardinal_2" class="form-control ui-wizard-content valida_letras"  maxlength="9" title="Ingrese unicamente digitos" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Numero de placa</label>
                                                        <div class="col-lg-10">
                                                            <input placeholder="23-56" id="placa" class="form-control ui-wizard-content valida_letras"  maxlength="10" />
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Otras indicaciones</label>
                                                        <div class="col-lg-10">
                                                            <input placeholder="Dentro de una plazoleta" id="indicaciones" class="form-control ui-wizard-content valida_letras"  maxlength="10" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="step_venta" class="step">
                                                <div class="form-group">
                                                    <div class="col-lg-10 centrate">
                                                        <label>Configuraciones en el punto</label><i id="puntos_permisos" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="permisos_tableCross"></div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="step_receta" class="step">
                                                <div class="form-group">
                                                    <div class="col-lg-10 centrate">
                                                        <label>Marcas</label><i id="puntos_franquicias" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="franquicias_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div id="punto_bodegas_label" class="form-group">
                                                    <div class="col-lg-10 centrate">
                                                        <label>Bodegas</label><!--<i id="puntos_bodegas" class="icon32 i-plus-circle green"></i>-->
                                                    </div>
                                                </div>
                                                <div class="form-group">

                                                </div>
                                                <div id="lista_de_bodegas_embebida">
                                                    <div id="bodega_lista">
                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">
                                                                <div id="bodega_crear" class="icon btn btn-info btn-sm">
                                                                    <i id="puntos_bodegas" class="icon20 i-plus"> Crear</i>
                                                                </div>
                                                                <div id="bodega_des" class="icon btn btn-sm desactivados">
                                                                    <i class="icon20 i-eye-blocked"> Ver inactivados</i>
                                                                </div>
                                                                <div id="bodega_act" class="icon btn btn-info btn-sm hidden">
                                                                    <i class="icon20 i-eye"> Ver activados</i>
                                                                </div>

                                                                <h4></h4>
                                                            </div>
                                                            <div class="panel-body">
                                                                <div id="bodegas_tableCross"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div id="ver_de_bodegas_embebidas">

                                                    </div>
                                                </div>


                                                <div id="container_bodegas_del_punto" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-10 control-label" >Nombre</label>
                                                            <div class="col-lg-10">
                                                                <input id="container_bodega" class="form-control ui-wizard-content"  type="text"/>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">

                                                    </div>
                                                </div>

                                            </div>
                                    </div>
                                </form>
                                <div class="actions form-actions full">
                                    <button class="btn pull-left ui-wizard-content ui-formwizard-button back hidden" type="button" value="Back"><i class="icon16 i-arrow-left-2"> <b>Atras</b></i></button>
                                    <button data-last="Finish" class="btn pull-right ui-wizard-content ui-formwizard-button next" type="button" value="Next"><b>Guardar y continuar </b><i class="icon16 i-arrow-right-3"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </script>


                    <script type="text/html" id="articulos_section">
                        <div id="heading" class="page-header">
                            <h1 id="titulo_seccion">{{action}}</h1>
                        </div>
                        <div class="row">
                        <div id="chargindDiv" class="hidden">
                            <div>
                                <label>Cargando</label>
                                <img id="connection_cargando" src="assets/images/loader.gif">
                            </div>
                        </div>

                            <div id="articulo_section" class="panel panel-default">
                                <div class="panel-heading">
                                    <h4>
                                        <div id="config_buttton" class="icon btn btn-info btn-sm "><i class="icon20 i-cog"> Configuracio&#769;n</i></div>
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <form class="wizard form-horizontal wizard-vertical">
                                        <div class="msg"></div>
                                        <div class="wizard-steps show container_absolute">
                                            <div class="vertical_centrator">
                                                <div class="wstep datos_basicos">
                                                    <div class="donut"><i class="icon32  i-man"></i></div>
                                                    <span class="txt">Datos ba&#769;sicos</span>
                                                </div>
                                                <div class="wstep ignore inventario">
                                                    <div class="donut"><i class="icon32   i-menu-6"></i></div>
                                                    <span class="txt">Inventario</span>
                                                </div>
                                                <div class="wstep ignore venta">
                                                    <div class="donut"><i class="icon32 i-tag-8"></i></div>
                                                    <span class="txt">Venta</span>
                                                </div>
                                                <div class="wstep ignore receta">
                                                    <div class="donut"><i class="icon32  i-atom-2"></i></div>
                                                    <span class="txt">Receta</span>
                                                </div>
                                                <div class="wstep ignore empaques">
                                                    <div class="donut"><i class="icon32   i-bag-3"></i></div>
                                                    <span class="txt">Empaques</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrap">
                                            <div id="basico" class="step">
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Nombre del articulo</label>
                                                    <div class="col-lg-10">
                                                        <input maxlength="100" value="{{articulo.nombre}}" id="basico_nombre" class="form-control ui-wizard-content" name="firstname" type="text">
                                                    </div>

                                                </div>
                                                <div class="form-group hidden" id="nombreMostrar">
                                                    <label class="col-lg-2 control-label" >Texto para impresion</label>
                                                    <div class="col-lg-10">
                                                        <input value="{{articulo.nombreImpresion}}" id="basico_nombre_mostrar" maxlength="30" class="form-control ui-wizard-content" type="text">
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Co&#769;digo de barras</label>

                                                    <div class="col-lg-10">
                                                        <input maxlength="20" value="{{articulo.codigobarras}}" id="basico_codigo" class="form-control ui-wizard-content" name="lastname" type="text">
                                                    </div>
                                                </div>
                                                <!--
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Descripcio&#769;n</label>
                                                    <div class="col-lg-10">
                                                        <textarea id="basico_des" class="form-control ui-wizard-content elastic" name="lastname">{{articulo.descripcion}}</textarea>
                                                    </div>
                                                </div>
                                                    -->
                                                <div class="form-group">
                                                    <label class="control-label" style="float: left;padding-left: 30px;" >Hara&#769; parte de una seleccio&#769;n</label>
                                                    <div class="col-lg-1">
                                                        {{#ifm articulo.seleccion}}
                                                            <input id="check_seleccion" class="form-control no_step" name="lastname" type="checkbox" checked="checked">
                                                        {{else}}
                                                            <input id="check_seleccion" class="form-control no_step" name="lastname" type="checkbox" >
                                                        {{/ifm}}
                                                    </div>
                                                    <label class="col-lg-2 control-label" checked="checked">Es un empaque</label>
                                                    <div class="col-lg-1">
                                                        {{#ifm articulo.empaque}}
                                                        <input class="form-control" id="es_empaque_check" type="checkbox" checked="checked">
                                                        {{else}}
                                                        <input class="form-control" id="es_empaque_check" type="checkbox">
                                                        {{/ifm}}
                                                    </div>
                                                </div>
												<div class="form-group">
													<label class="col-lg-2 control-label">Usa costo estimado</label>
                                                    <div class="col-lg-1">
														<input class="form-control" id="check_costo_estimado" type="checkbox" {{bindAttr checked="articulo.seleccion"}}>
                                                    </div>
													<label class="col-lg-2 control-label">Costo estimado</label>
                                                    <div class="col-lg-1">
                                                        <input class="form-control" id="costo_estimado" value="{{articulo.costoEstimado}}" type="textbox" disabled>
                                                    </div>
												</div>
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Caracteri&#769;sticas</label>
                                                    <div class="col-lg-12">&lrm;</div>
                                                </div>
                                                <div class="form-group checks">
                                                    <ul>
                                                        <li style="margin-left: -55px;">
                                                            <label class="col-lg-1 control-label">Inventario</label>
    {{#ifm articulo.inventario}}
    <input id="check_inventario" class="form-control" name="lastname" type="checkbox" checked="checked">
                                                            {{else}}
                                                                <input id="check_inventario" class="form-control" name="lastname" type="checkbox" >
                                                            {{/ifm}}
                                                        </li>
                                                        <li>
                                                            <label class="col-lg-1 control-label">Venta</label>
    {{#ifm articulo.venta}}
    <input id="check_venta" class="form-control" name="lastname" type="checkbox" checked="checked">
                                                            {{else}}
                                                                <input id="check_venta" class="form-control" name="lastname" type="checkbox">
                                                            {{/ifm}}

                                                        </li>
                                                        <li>
                                                            <label class="col-lg-1 control-label">Receta</label>
                                                            {{#ifm articulo.receta}}
                                                                <input id="check_receta" class="form-control" name="lastname" type="checkbox" checked="checked">
                                                            {{else}}
                                                                <input id="check_receta" class="form-control" name="lastname" type="checkbox" >
                                                            {{/ifm}}
                                                        </li>
                                                        <li>
                                                            <label class="col-lg-1 control-label">Utiliza empaques</label>
                                                            {{#ifm articulo.utilizaEmpaque}}
                                                            <input id="utliza_empaque_check" checked="checked" class="form-control"  type="checkbox">
                                                            {{else}}
                                                            <input id="utliza_empaque_check" class="form-control"  type="checkbox">
                                                            {{/ifm}}
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12 centrate label_p">
                                                        <label>Estara  en los siguientes puntos</label><i id="venta_puntos_table" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="venta_puntos_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div id="floating_configuracion" class="hidden">
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Mostrar co&#769;digo de barras</label>
                                                        <div class="col-lg-1">
                                                            <input id="basico_cb" checked="checked" class="form-control ui-wizard-content" type="checkbox" data-el="#basico_codigo"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Mostrar unidad alterna</label>
                                                        <div class="col-lg-1">
                                                            <input id="basico_ua"  checked="checked" class="form-control ui-wizard-content" type="checkbox" data-el="#unidad_a,#unidad_o"/>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-3 control-label" >Mostrar opcion de maximos y minimos</label>
                                                        <div class="col-lg-1">
                                                            <input id="basico_mn"  checked="checked" class="form-control ui-wizard-content" type="checkbox" data-el="#useMax,#tableMinMax,#maximos_tableCross"/>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div id="step_inventario" class="step ignore">
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Clase de inventario</label>
                                                    <div class="col-lg-10 loading_control">
                                                        <select id="clase" class="form-control ui-wizard-content" disabled="disabled">
                                                        </select>
                                                        <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Grupo de inventario</label>
                                                    <div class="col-lg-10">
                                                        <select id="grupo" class="form-control ui-wizard-content" disabled="disabled"> </select>
                                                        <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Unidad de kardex</label>
                                                    <div class="col-lg-10 loading_control" >
                                                        <select id="unidad_k" class="charge_select form-control ui-wizard-content" name="lastname" type="text" disabled="disabled">
                                                        </select>
                                                        <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                    </div>

                                                </div>

                                                <div class="form-group alias hidden">
                                                    <label class="col-lg-2 control-label">Nombre de unidad</label>
                                                    <div class="col-lg-10" >
                                                        <input id="unidad_alias" maxlength="50" class="form-control ui-wizard-content"  type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group unidad_a">
                                                    <label class="col-lg-2 control-label" >Unidad alterna</label>
                                                    <div class="col-lg-10 loading_control" >
                                                        <select id="unidad_a" class="form-control ui-wizard-content" name="lastname" type="text" disabled="disabled"></select>
                                                        <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                    </div>
                                                    <div id="mensaje_unidad_alterna" class="col-lg-10 hidden"><label class="center" style="padding-left: 180px;color:darkred"></label></div>
                                                    <div id="mensaje_unidad_alterna2" class="col-lg-10 hidden"><label class="center" style="padding-left: 180px;color:darkred"></label></div>
                                                </div>

                                                <div class="form-group unidad_o hidden">
                                                    <label class="col-lg-2 control-label">Nombre de la nueva unidad</label>
                                                    <div class="col-lg-10" >
                                                        <input id="unidad_o" maxlength="50" class="form-control ui-wizard-content"  type="text">
                                                    </div>
                                                </div>

                                                <div class="form-group otra_unidad">
                                                    <label class="col-lg-2 control-label">Usar maximos y minimos</label>
                                                    <div class="col-lg-10 loading_control row_checkbox" >
                                                        <input id="useMax" type="checkbox" class="form-control ui-wizard-content" checked="checked">
                                                    </div>
                                                </div>
                                                <div class="form-group min_max">
                                                    <div class="col-lg-12 centrate label_p">
                                                        <label>Maximos y minimos</label><i id="tableMinMax" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group min_max">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="maximos_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12 centrate label_p">
                                                        <label>Unidad de compra</label><i id="table_unidad_c" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="unidad_c_tableCross"></div>
                                                        <select id="equivalencia" class="form-control ui-wizard-content hidden"></select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12 centrate label_p">
                                                        <label>bodegas</label><i id="bodegas_articulo" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="bodegas_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div id="container_min_max" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Aplica para estos Puntos</label>
                                                            <div class="col-lg-12">
                                                                <label id="puntos_min_max" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Minimos</label>
                                                            <div class="col-lg-12">
                                                                <input id="min_value" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.nombreRepresentante}}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Maximos</label>
                                                            <div class="col-lg-12">
                                                                <input id="max_value" class="form-control ui-wizard-content" name="lastname" type="text" value="{{cliente.nombreRepresentante}}">
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div id="container_unidad_de_venta" class="hidden">
                                                    <div class="form-group">
                                                        <label class="col-lg-12 control-label" >Equivalencia con unidad de venta</label>
                                                        <div class="col-lg-12">
                                                            <input id="equiv_venta" class="form-control ui-wizard-content">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="container_clase_grupo" class="hidden">
                                                    <div class="form-group">
                                                        <label class="col-lg-12 control-label" >Nombre</label>
                                                        <div class="col-lg-12">
                                                            <input class="form-control ui-wizard-content c_clase">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-12 control-label" >Solamente puede crear 20 Clases y Grupos</label>
                                                    </div>
                                                </div>

                                                <div id="container_unidad_compra" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Unidad</label>
                                                            <div class="col-lg-12">
                                                                <select id="venta_unidad_a" class="form-control ui-wizard-content"></select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group otro hidden">
                                                            <label class="col-lg-12 control-label" >Nombre de la nueva unidad</label>
                                                            <div class="col-lg-12">
                                                                <input id="unidad_a_nuevaUnidad" class="form-control ui-wizard-content">
                                                            </div>
                                                        </div>
                                                        <div class="form-group hidden">
                                                            <label class="col-lg-12 control-label" >Equivalencia con</label>
                                                            <div class="col-lg-12">
                                                                <input id="venta_equivalencia" class="form-control ui-wizard-content">
                                                            </div>
                                                        </div>
                                                        <a class="tableUnits">Ver la tabla de unidades y sus conversiones</a>
                                                    </form>
                                                </div>
                                                <div id="container_equivalencia" class="hidden">
                                                    <label class="col-lg-12" >Establezca una equivalencia con su unidad de venta</label>
                                                    <div class="form-group">
                                                        <label class="col-lg-12 control-label" >Equivalencia</label>
                                                        <div class="col-lg-12">
                                                            <input id="equivalencia_con_venta" class="form-control ui-wizard-content">
                                                        </div>
                                                    </div>
                                                    <label id="equivalencia_label" class="col-lg-12 control-label" >1 <span id="unidad_de_k"></span> = <span id="unidad_cant"> </span><span id="unidad_de_v"></span></label>
                                                </div>
                                            </div>

                                            <div id="step_venta" class="step ignore">
                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Clase</label>
                                                    <div class="col-lg-10 loading_control">
                                                        <select id="venta_clase" class="form-control ui-wizard-content" disabled="disabled">
                                                        </select>
                                                        <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-lg-2 control-label" >Grupo</label>
                                                    <div class="col-lg-10 loading_control">
                                                        <select id="venta_grupo" class="form-control ui-wizard-content" disabled="disabled"></select>
                                                        <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                    </div>
                                                </div>

                                                <h2 class="center">Asigne el articulo a un canal y defina el impuesto y tarifa</h2>
                                                <div class="form-group">

                                                    <div class="col-lg-12 centrate">
                                                        <div class="centrate container_check_label">
                                                            <input type="checkbox" id="tax_all_ch" class="form-control ">
                                                            <label class="label_with_check">Impuesto aplica para todos los canales</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12 centrate">
                                                        <div class="centrate container_check_label">
                                                            <input type="checkbox" id="rate_all_ch" class="form-control ">
                                                            <label class="label_with_check">La tarifa aplica para todos los canales</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="ch_tax_rate_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12 centrate label_p">
                                                        <label>Unidad principal</label><i id="venta_unidad" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="unidad_p_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-12 centrate label_p">
                                                        <label>Unidades alternas</label><i id="venta_u_a" class="icon32 i-plus-circle green"></i>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="venta_u_a_tableCross"></div>
                                                    </div>
                                                </div>
												<div class="form-group">
                                                   <div class="col-lg-12 centrate label_p">
                                                 		<label>Grupo de seleccion</label><i id="grupo_sel" class="icon32 i-plus-circle green"></i>
                                                  	</div>
                                               	</div>
                                                <div class="form-group">
                                                	<div class="col-lg-2"></div>
                                                    <div class="col-lg-10">
                                                        <div id="grupo_s_tableCross"></div>
                                                    </div>
                                                </div>
                                                <div id="container_impuestos" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Canal</label>
                                                            <div class="col-lg-12">
                                                                <label id="venta_canal" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Impuesto</label>
                                                            <div class="col-lg-12">
                                                                <label id="venta_impuesto" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Tarifa</label>
                                                            <div class="col-lg-12">
                                                                <label id="venta_tarifa" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div id="venta_form" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Unidad de venta</label>
                                                            <div class="col-lg-12 loading_control">
                                                                <select id="venta_u" class="form-control ui-wizard-content"></select>
                                                                <img class="loading_img" src="<c:url value='assets/template/images/preloaders/blue/2.gif'/>">
                                                            </div>
                                                        </div>
                                                        <div class="form-group venta_otro hidden">
                                                            <label class="col-lg-12 control-label" >Nombre de la nueva unidad</label>
                                                            <div class="col-lg-12">
                                                                <input id="venta_otro" class="form-control ui-wizard-content">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" id="venta_label_equiv">Equivalencia</label>
                                                            <div class="col-lg-12">
                                                                <input id="venta_eq" class="form-control ui-wizard-content">
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Lista de precios</label>
                                                            <div class="col-lg-12">
                                                                <label id="venta_lista_p" class="form-control ui-wizard-content">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Valor de la venta</label>
                                                            <div class="col-lg-12">
                                                                <input id="venta_valor" class="form-control ui-wizard-content">
                                                            </div>
                                                        </div>
                                                        <a class="tableUnits">Ver la tabla de unidades y sus conversiones</a>
                                                    </form>
                                                </div>
                                            </div>
                                            <div id="step_receta" class="step ignore">
                                                <div id="receta_lista">
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div id="receta_crear" class="icon btn btn-info btn-sm">
                                                                <i class="icon20 i-plus"> Crear</i>
                                                            </div>
                                                            <div id="receta_des" class="icon btn btn-sm desactivados">
                                                                <i class="icon20 i-eye-blocked"> Ver inactivados</i>
                                                            </div>
                                                            <div id="receta_act" class="icon btn btn-info btn-sm hidden">
                                                                <i class="icon20 i-eye"> Ver activados</i>
                                                            </div>

                                                            <h4></h4>
                                                        </div>
                                                        <div class="panel-body">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="receta_guardar" class="hidden">
                                                    <div class="other_button other_button_two">
                                                        <div class="form-group">
                                                            <div id="receta_ver" class="col-lg-2 icon btn btn-info btn-sm">
                                                                <i class="icon20 i-eye"> Ver listado de recetas</i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="other_button other_button_tree">
                                                        <div class="form-group">
                                                            <div id="receta_importar" class="col-lg-2 icon btn btn-primary btn-sm">
                                                                <i class="icon20 i-stack-4"> Importar receta</i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group other_button other_button_four hidden">
                                                        <div id="receta_limpiar" class="col-lg-2 icon btn btn-primary btn-sm">
                                                            <i class="icon20 i-file-remove"> Borrar receta</i>
                                                        </div>
                                                    </div>
                                                    
													<div class="form-group centrate label_p">
                                                        <label class="col-lg-12 control-label" >Estara en los puntos</label><i id="puntos_ing" class="icon32 i-plus-circle green"></i>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-lg-2"></div>
                                                        <div class="col-lg-10">
                                                            <div id="receta_puntos_tableCross"></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-lg-12 centrate label_p">
                                                            <label>Canales</label><i id="receta_canales" class="icon32 i-plus-circle green"></i>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-2"></div>
                                                        <div class="col-lg-10">
                                                            <div id="receta_canales_tableCross"></div>
                                                        </div>
                                                    </div>

													<div class="form-group">
                                                        <label class="col-lg-2 control-label" >Nombre</label>
                                                        <div class="col-lg-10">
                                                            <input id="nombre" class="form-control ui-wizard-content"  type="text">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-lg-2 control-label" >Receta para</label>
                                                        <div class="col-lg-2">
                                                            <input id="receta_cantidad_base" class="form-control ui-wizard-content"  placeholder="Cantidad" type="text">
                                                        </div>
                                                        <div class="col-lg-2">
                                                            <select id="receta_unidad" class="form-control ui-wizard-content"  type="text">
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-lg-12 centrate label_p">
                                                            <label>Ingredientes</label><i id="receta_ing" class="icon32 i-plus-circle green"></i>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-2"></div>
                                                        <div class="col-lg-10">
                                                            <div id="ingredientes_tableCross"></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-2 control-label" >Preparacion</label>
                                                        <div class="col-lg-10">
                                                            <textarea id="descripcion"  maxlength="4000" class="form-control ui-wizard-content elastic" ></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="actions hidden">
                                                        <button  id="guardar" class="btn pull-right ui-wizard-content ui-formwizard-button next" type="button" value="Guardar"><b>Guardar</b></button>
                                                    </div>
                                                </div>

                                                <div id="receta_importar_oculto" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Articulo</label>
                                                            <div class="col-lg-12">
                                                                <label id="importar_articulo" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Receta</label>
                                                            <div class="col-lg-12">
                                                                <label id="importar_receta" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>

                                                <div id="receta_ingredientes" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Articulo</label>
                                                            <div class="col-lg-12">
                                                                <label id="receta_articulo" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Cantidad</label>
                                                            <div class="col-lg-12">
                                                                <input id="receta_cantidad" class="form-control ui-wizard-content"  type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Unidad</label>
                                                            <div class="col-lg-12">
                                                                <select id="receta_unidad_k" class="form-control ui-wizard-content"></select>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>

                                                <div id="venta_seleccion" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Seleccion</label>
                                                            <div class="col-lg-12">
                                                                <label id="venta_seleccion_e" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group hidden otro">
                                                            <label class="col-lg-12 control-label" >Cantidad de articulos</label>
                                                            <div class="col-lg-12">
                                                                <input id="venta_can" class="form-control ui-wizard-content"  type="text">
                                                            </div>
                                                            <input id="grupo_tipo" class="form-control ui-wizard-content"  type="hidden">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <div id="step_empaques" class="step ignore">
                                                <div id="empaques_listado_table_cross">
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div id="empaque_crear" class="icon btn btn-info btn-sm">
                                                                <i class="icon20 i-plus"> Crear</i>
                                                            </div>
                                                            <div id="empaque_des" class="icon btn btn-sm desactivados">
                                                                <i class="icon20 i-eye-blocked"> Ver inactivados</i>
                                                            </div>
                                                            <div id="empaque_act" class="icon btn btn-info btn-sm hidden">
                                                                <i class="icon20 i-eye"> Ver activados</i>
                                                            </div>

                                                            <h4></h4>
                                                        </div>
                                                        <div class="panel-body">

                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="empaques_data" class="hidden">

                                                    <div class="other_button other_button_two">
                                                        <div class="form-group">
                                                            <div id="empaque_ver" class="col-lg-2 icon btn btn-info btn-sm">
                                                                <i class="icon20 i-eye"> Ver lista de empaques</i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="other_button other_button_tree">
                                                        <div class="form-group">
                                                            <div id="empaque_importar" class="col-lg-2 icon btn btn-primary btn-sm">
                                                                <i class="icon20 i-stack-4">Importar empaque</i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group other_button other_button_four hidden">
                                                        <div id="empaque_limpiar" class="col-lg-2 icon btn btn-primary btn-sm">
                                                            <i class="icon20 i-file-remove"> Limpiar campos y tablas</i>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <label class="col-lg-2 control-label">Nombre</label>
                                                        <div class="col-lg-10">
                                                            <input id="empaque_nombre" value="" class="form-control ui-wizard-content" type="text"/>
                                                        </div>
                                                    </div>

                                                    <div class="form-group centrate label_p">
                                                        <label class="col-lg-12 control-label" >Estara en los puntos</label><i id="puntos_emp" class="icon32 i-plus-circle green"></i>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-lg-2"></div>
                                                        <div class="col-lg-10">
                                                            <div id="empaque_puntos_tableCross"></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-lg-12 centrate label_p">
                                                            <label class="col-lg-12 control-label" >Elementos del empaque</label><i id="empaque_div_crear" class="icon32 i-plus-circle green"></i>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-lg-2"></div>
                                                        <div class="col-lg-10">
                                                            <div id="empaques_c_tableCross"></div>
                                                        </div>
                                                    </div>


                                                    <!--
                                                    <div id="empaque_div_lista">

                                                        <div class="panel panel-default">
                                                            <div class="panel-heading">

                                                                <div id="empaque_div_crear" class="icon btn btn-info btn-sm">
                                                                    <i id="" class="icon20 i-plus"> Crear</i>
                                                                </div>
                                                                <div id="empaque_div_des" class="icon btn btn-sm desactivados">
                                                                    <i class="icon20 i-eye-blocked"> Ver inactivados</i>
                                                                </div>
                                                                <div id="empaque_div_act" class="icon btn btn-info btn-sm hidden">
                                                                    <i class="icon20 i-eye"> Ver activados</i>
                                                                </div>

                                                                <h4></h4>
                                                            </div>
                                                            <div class="panel-body">

                                                                <div id="empaques_c_tableCross"></div>

                                                            </div>

                                                        </div>
                                                    </div>
                                                    -->



                                                    <div class="form-group">
                                                        <div class="col-lg-12 centrate label_p">
                                                            <label>Canales</label><i id="canales_emp" class="icon32 i-plus-circle green"></i>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-2"></div>
                                                        <div class="col-lg-10">
                                                            <div id="empaque_canales_tableCross"></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="empaque_elementos" class="hidden">
                                                    <form>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Articulo</label>
                                                            <div class="col-lg-12">
                                                                <label id="empaque_articulo" class="form-control ui-wizard-content table_floating">0 seleccionados</label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Cantidad</label>
                                                            <div class="col-lg-12">
                                                                <input id="empaque_cantidad" class="form-control ui-wizard-content"  type="text">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-lg-12 control-label" >Unidad</label>
                                                            <div class="col-lg-12">
                                                                <select id="empaque_unidad_k" class="form-control ui-wizard-content"></select>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <div class="actions form-actions full">
                                        <button class="btn pull-left ui-wizard-content ui-formwizard-button back hidden" type="button" value="Back"><i class="icon16 i-arrow-left-2"> <b>Atras</b></i></button>
                                        <button data-last="Finish" class="btn pull-right ui-wizard-content ui-formwizard-button next" type="button" value="Next"><b>Guardar y continuar </b><i class="icon16 i-arrow-right-3"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                </script>

                <script type="text/html" id="grupos_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="grupo_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">
                                            <input maxlength="100" id="grupo_nombre" value="{{grupo.nombre}}" class="form-control ui-wizard-content"  type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-12 centrate">
                                            <label>Clientes que pertenecen a este grupo</label><i id="grupo_clientes_button" class="icon32 i-plus-circle green"></i>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-10">
                                            <div id="grupo_clientes_tableCross"></div>
                                        </div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
                </script>

                <script type="text/html" id="marcas_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="marca_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">
                                            <input maxlength="100" id="marca_nombre" class="form-control ui-wizard-content"  value="{{marca.nombre}}" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-12 centrate">
                                            <label>Propietario de la marca</label><i id="marca_clientes_button" class="icon32 i-plus-circle green"></i>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-10">
                                            <div id="marca_clientes_tableCross"></div>
                                        </div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
                </script>

                <script type="text/html" id="roles_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="rol_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">
                                            <input id="roles_nombre" maxlength="100" class="form-control ui-wizard-content" value="{{rol.nombre}}" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-12 centrate">
                                            <label>Permisos del rol</label><i id="rol_button" class="icon32 i-plus-circle green"></i>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-10">
                                            <div id="rol_tableCross"></div>
                                        </div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
                </script>

                <script type="text/html" id="roles_cliente_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="rolCliente_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">
                                            <input maxlength="100" id="roles_cliente_nombre" class="form-control ui-wizard-content"  value="{{rol.nombre}}" type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-12 centrate">
                                            <label>Permisos del rol</label><i id="rolCliente_button" class="icon32 i-plus-circle green"></i>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-10">
                                            <div id="rolCliente_tableCross"></div>
                                        </div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
                </script>

                <script type="text/html" id="usuarios_section">
                    <div id="heading" class="page-header">
                        <h1 id="usuario_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="usuario_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="wizard form-horizontal wizard-vertical">
                                    <div class="wizard-steps show container_absolute">
                                        <div class="vertical_centrator">
                                            <div class="wstep">
                                                <div class="donut"><i class="icon32  i-man"></i></div>
                                                <span class="txt">Datos basicos</span>
                                            </div>
                                            <div class="wstep">
                                                <div class="donut"><i class="icon32   i-menu-6"></i></div>
                                                <span class="txt">Ajustes</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="wrap">
                                        <div class="step  active" data-step-title="Datos basicos" >
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Nombre</label>
                                                <div class="col-lg-10">
                                                    <input id="usuario_nombre" class="form-control ui-wizard-content" maxlength="100" value="{{usuario.fullName}}" type="text">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Correo</label>
                                                <div class="col-lg-10">
                                                    <input id="usuario_correo" maxlength="100" class="form-control ui-wizard-content" value="{{usuario.email}}" type="text">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Contrase�a</label>
                                                <div class="col-lg-10">
                                                    <input id="usuario_contra" class="form-control ui-wizard-content" maxlength="50" value="********" type="password">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-2 control-label" >Repita la contrase�a</label>
                                                <div class="col-lg-10">
                                                    <input id="usuario_contra2" maxlength="50" class="form-control ui-wizard-content" value="********" type="password">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12 centrate">
                                                    <label>Cliente</label><i id="usuario_cliente_button" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="usuario_cliente_tableCross"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12 centrate">
                                                    <label>Roles</label><i id="usuario_roles_button" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="usuario_roles_tableCross"></div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="step" data-step-title="Ajustes" >
                                            <div class="form-group">
                                                <div class="col-lg-12 centrate">
                                                    <label>Acceso al grupo</label><i id="usuario_grupo_button" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="usuario_grupo_tableCross"></div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-12 centrate">
                                                    <label>Acceso al punto</label><i id="usuario_punto_button" class="icon32 i-plus-circle green"></i>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-2"></div>
                                                <div class="col-lg-10">
                                                    <div id="usuario_punto_tableCross"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div class="actions form-actions full">
                                    <button class="btn pull-left ui-wizard-content ui-formwizard-button back hidden" type="button" value="Back"><i class="icon16 i-arrow-left-2"> <b>Atras</b></i></button>
                                    <button data-last="Finish" class="btn pull-right ui-wizard-content ui-formwizard-button next" type="button" value="Next"><b>Siguiente </b><i class="icon16 i-arrow-right-3"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </script>

                <script type="text/html" id="selecciones_section">
                    <div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="seleccion_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">

                                            <input id="nombre" maxlength="100" value="{{seleccion.nombre}}"  class="form-control ui-wizard-content"  type="text">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-12 control-label centrate" >Caracteristicas del precio</label>
                                        <div class="col-lg-12">
                                            &lrm;
                                        </div>
                                    </div>
                                    <div class="form-group radios">
                                        <label class="control-label"></label>
                                        <div class="col-lg-12 center centrator_radios">
                                            <div class="inline-block centrator_radios_child2">
                                                <ul>
                                                    <li>
                                                        <label class="control-label">Incremento por articulo</label>
                                                        <input id="inc_articulo" class="form-control" name="precio_seleccion" value="1" type="radio">
                                                    </li>
                                                    <li>
                                                        <label class="control-label">Incremento por grupo</label>
                                                        <input id="inc_grupo" class="form-control" name="precio_seleccion" value="2" type="radio">
                                                    </li>
													<li>
                                                        <label class="control-label">El precio mas alto</label>
                                                        <input id="inc_mayor_val" class="form-control" name="precio_seleccion" value="3" type="radio">
                                                    </li>
                                                    <li>
                                                        <label class="control-label">No aplica</label>
                                                        <input id="inc_no_aplica" class="form-control" name="precio_seleccion" value="4" type="radio">
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <label class="control-label"></label>
                                    <label class="control-label"></label>
                                    <div class="form-group hidden">
                                        <label class="col-lg-2 control-label">Incremento de grupo</label>
                                        <div class="col-lg-10">
                                            <input id="valor_de_grupo" value="{{seleccion.incrementoPrecio}}" class="form-control ui-wizard-content" type="text"/>
                                        </div>
                                    </div>
                                    <label class="control-label"></label>
                                    <label class="control-label"></label>
                                    <div class="form-group">
                                        <label class="col-lg-12 control-label centrate" >Tipo de seleccion</label>
                                        <div class="col-lg-12">
                                            &lrm;
                                        </div>
                                    </div>
                                    <div class="form-group radios">
                                        <div class="col-lg-10 center centrator_radios">
                                            <div class="inline-block centrator_radios_child2">
                                                <ul>
    												<li>
                                                        <label class="control-label">Abierta</label>
                                                        <input id="sel_abierta" class="form-control" name="tipo_grupo" value="1" type="radio">
                                                    </li>
                                                    <li>
                                                        <label class="control-label">Cerrada</label>
                                                        <input id="sel_cerrada" class="form-control" name="tipo_grupo" value="2" type="radio">
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="seleccion_form" class="hidden">
                                        <form>
                                            <div class="form-group">
                                                <label class="col-lg-12 control-label" >Articulos</label>
                                                <div class="col-lg-12">
                                                    <label id="seleccion_articulos" class="form-control ui-wizard-content">0 seleccionados</label>
                                                </div>
                                            </div>
                                            <div class="form-group hidden">
                                                <label class="col-lg-12 control-label" >Descarga de kardex</label>
                                                <div class="col-lg-12">
                                                    <input id="descarga" class="form-control ui-wizard-content" />
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-lg-12 control-label" >Unidad</label>
                                                <div class="col-lg-12">
                                                    <select id="unidad" class="form-control ui-wizard-content" ></select>
                                                </div>
                                            </div>
                                            <div class="form-group hidden">
                                                <label class="col-lg-12 control-label" >Incrementa</label>
                                                <div class="col-lg-12">
                                                    <input id="incrementa" class="form-control ui-wizard-content" />
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-12 centrate">
                                            <label>Seleccion</label><i id="seleccion_button" class="icon32 i-plus-circle green"></i>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-10">
                                            <div id="seleccion_tableCross"></div>
                                        </div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
                </script>
            	<script type="text/html" id="lista_precios_section">
					<div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
                    <div class="row">
                        <div id="listaPrecios_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">
                                            <input maxlength="100" id="lista_precios_nombre" class="form-control ui-wizard-content"  value="{{window.lista_precios.nombre}}" type="text">
                                        </div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
				</script>
				<script type="text/html" id="clases_section">
					<div id="heading" class="page-header">
                        <h1 id="titulo_seccion">{{action}}</h1>
                    </div>
					<div id="chargindDiv" class="hidden">
                    	<div>
                        	<label>Cargando</label>
                            <img id="connection_cargando" src="assets/images/loader.gif">
						</div>
					</div>
                    <div class="row">
                        <div id="clase_section" class="panel panel-default">
                            <div class="panel-heading">
                                <h4></h4>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal">
                                    <div class="msg"></div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" >Nombre</label>
                                        <div class="col-lg-10">
                                            <input maxlength="100" id="clase_nombre" class="form-control ui-wizard-content"  value="{{clase.nombre}}" type="text">
                                        </div>
										<label class="col-lg-2 control-label" >Usada para Venta</label>
                                        <div class="col-lg-10">
											<input id="check_venta" class="form-control" type="checkbox" {{#if clase.venta}}checked="checked"{{/if}} >
                                        </div>
										<label class="col-lg-2 control-label" >Usada para Inventario</label>
                                        <div class="col-lg-10">
											<input id="check_inventario" class="form-control" type="checkbox" {{#if clase.inventario}}checked="checked"{{/if}} >
                                        </div>
										<div class="form-group">
                                        	<div class="col-lg-12 centrate">
                                            	<label>Grupos</label><i id="grupos_button" class="icon32 i-plus-circle green"></i>
                                        	</div>
                                    	</div>
                                    	<div class="form-group">
                                        	<div class="col-lg-2"></div>
                                        	<div class="col-lg-10">
                                            	<div id="claseGrupos_tableCross"></div>
                                        	</div>
                                    	</div>
                                    </div>
                                </form>
                                <input  id="btn-guardar" type="button" class="btn pull-right ui-wizard-content ui-formwizard-button btn-success" value="Guardar" />
                            </div>
                        </div>
                    </div>
					<div id="container_clases_grupo" class="hidden">
						<form>
                        	<div class="form-group">
                            	<label class="col-lg-12 control-label" >Nombre</label>
                                <div class="col-lg-12">
                                	<input name="container_nombre" id="container_nombre" class="form-control ui-wizard-content" type="text" ></input>
                              	</div>
                        	</div>
                   		</form>
        			</div>
				</script>
            </div>
        </div>
        <div id="principal" class="container-fluid">

        </div>
    </div>
</section>