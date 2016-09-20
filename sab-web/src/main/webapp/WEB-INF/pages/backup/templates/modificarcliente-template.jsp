<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="modificarClienteView-Template" type="text/template">
    <div id="error_div" class="alert alert-error">
        <i id="close_div" class="icon32  i-close-4"></i>
        <div id="error_title" class="error btn btn-danger gap2">Ha ocurrido un error</div>
        <div id="error_content" class="panel-body"></div>
    </div>
    <div class="tabbable" id="crearclienteView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab"><b>Clientes</b></a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <div class="container">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h2 class="btn-primary">Modificar cliente</h2>
                        </div>
                        <div class="panel-body">
                            <div id="dialog-direction" class="">
                                <i class="icon16 i-cancel-circle-2 pull-right i-right_1 direction_close_it"></i>
                                <div>
                                    <label>Nomenclatura</label>
                                    <select id="type_dir" requiered>
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
                                    <label class="hidden">Especifique cual</label>
                                    <input class="hidden" id="otra_1" type="text" placeholder="vereda" maxlength="40" title="Ingrese cual" />
                                </div>
                                <div>
                                    <label>Numero</label>
                                    <input id="direccion_1" class="valida_numeros" type="text" placeholder="numero" maxlength="40" title="Ingrese unicamente digitos" />
                                </div>
                                <div>
                                    <label>Letra</label>
                                    <input class="letra" class="valida_letras" type="text" maxlength="6" placeholder="a bis"/>
                                </div>
                                <div>
                                    <label>cardinal</label>
                                    <input class="cardinal" type="text" class="valida_letras" maxlength="9" placeholder="sur"/>
                                </div>
                                <div>
                                    <label>con nomenclatura</label>
                                    <select id="type_dir_2" requiered>
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
                                    <label class="hidden">Especifique cual</label>
                                    <input class="hidden" id="otra_2" type="text" placeholder="vereda" maxlength="40" title="Ingrese cual" />
                                </div>
                                <div>
                                    <label>Numero</label>
                                    <input id="direccion_2" class="valida_numeros" type="text" placeholder="numero" maxlength="40" title="Ingrese unicamente digitos" />
                                </div>
                                <div>
                                    <label>Letra</label>
                                    <input class="letra" type="text"class="valida_letras"  maxlength="4" placesholder="a bis"/>
                                </div>
                                <div>
                                    <label>cardinal</label>
                                    <input class="cardinal" type="text" class="valida_letras" maxlength="9" placeholder="sur"/>
                                </div>
                                <div>
                                    <label>Numero de placa</label>
                                    <input id="placa" type="text" maxlength="10" placeholder="34 a bis-45 g norte"/>
                                </div>
                                <div>
                                    <label>Otras indicaciones</label>
                                    <textarea id="indicaciones" type="text" placeholder="Al frente de la alcadia"></textarea>
                                </div>
                                <input type="button" class="btn btn-primary gap-right10" value="Establecer direccion" id="btn-direccion"/>
                            </div>
                            <form id="valida">
                                <div class="section data_short">
                                    <h4 class="alert alert-info">Datos Basicos</h4>
                                    <div>
                                        <label>Razón Social <span class="">*</span></label>
                                        <input id="razon_social" value="<?=clienteMod.get('nombre')?>" maxlength="100" class="valida_letras" type="text" title="Ingrese minimo 1 caracter">
                                    </div>
                                    <div>
                                        <label>NIT <span class="required">*</span></label>
                                        <input value="<?=clienteMod.get('nit')?>"  id="nit" type="text" maxlength="19" title="Ingrese minimo 4 digitos" placeholder=" Ej:233.234.234">
                                    </div>
                                    <div>
                                        <label>Digito Chequeo<span class="required">*</span></label>
                                        <input value="<?=clienteMod.get('digitoChequeo')?>"   id="digitoChequeo" type="text" maxlength="1" title="Ingrese un digito">
                                    </div>
                                    <div>
                                        <label>Dirección</label>
                                        <input  value="<?=clienteMod.get('direccion')?>" id="direccion" type="text" maxlength="30" placeholder="cl 34 n. 35 a bis-23 occidente"/>
                                    </div>
                                    <div>
                                        <label>Teléfono <span class="">*</span></label>
                                        <input value="<?=clienteMod.get('telefono')?>" id="telefono" type="text"  maxlength="25" title="El telefono debe tener minimo 7 digitos">
                                    </div>
                                    <div>
                                        <label>Correo <span class="required">*</span></label>
                                        <input id="email" type="text" class="valida_correo" maxlength="50" title="El email debe tener minimo 5 caracteres" value="<?=clienteMod.get('email')?>">
                                    </div>
                                    <div>
                                        <label>Extensión</label>
                                        <input value="<?=clienteMod.get('extension')?>" id="extension" type="text" maxlength="4" title="La extenxion debe tener de 1 a 4  digitos">
                                    </div>
                                    <div>
                                        <label>Celular</label>
                                        <input value="<?=clienteMod.get('celular')?>" id="celular" type="text" maxlength="25" title="Ingrese unicamente digitos">
                                    </div>
                                    <div>
                                        <label>Régimen <span class="required">*</span></label>
                                        <select id="regimen_id" type="text">
                                            <?if (clienteMod.get('regimen_id') == "1"){?>
                                                <option></option>
                                                <option value="1" selected="selected">Simplificado</option>
                                                <option value="2">Comun</option>
                                                <option value="3">Especial</option>
                                            <?}else if(clienteMod.get('regimen_id') == "2"){?>
                                                <option></option>
                                                <option value="1">Simplificado</option>
                                                <option value="2" selected="selected">Comun</option>
                                                <option value="3">Especial</option>
                                            <?}else if(clienteMod.get('regimen_id') == "3"){?>
                                                <option></option>
                                                <option value="1">Simplificado</option>
                                                <option value="2">Comun</option>
                                                <option value="3" selected="selected">Especial</option>
                                            <?}else{?>
                                                <option></option>
                                                <option value="1">Simplificado</option>
                                                <option value="2">Comun</option>
                                                <option value="3" selected="selected">Especial</option>
                                            <?}?>
                                        </select>
                                    </div>
                                    <div>
                                        <label>Impuesto <?=clienteMod.get('impuesto_id')?><span class="required">*</span></label>
                                        <select id="impuesto_id" type="text">
                                            <?if (clienteMod.get('impuesto_id') == "1"){?>
                                                <option></option>
                                                <option value="1" selected="selected">Iva</option>
                                                <option value="2">Impoconsumo</option>
                                            <?}else if(clienteMod.get('impuesto_id') == "2"){?>
                                                <option></option>
                                                <option value="1">Iva</option>
                                                <option value="2" selected="selected">Impoconsumo</option>
                                            <?}else{?>
                                                <option></option>
                                                <option value="1">Iva</option>
                                                <option value="2">Impoconsumo</option>
                                       		 <?}?>
                                        </select>
                                    </div>
									<div>
                                        <label>Pais<span class="required">*</span></label>
                                        <select id="pais">
                                            <?if(paises!=null && paises.length > 0 ){
                                                var c=0;
                                                _.each(paises, function(item) {
                                                    if(c==0){
                                                    ?><option></option><?
                                                    }
                                                    c++;
                                                    ?>
                                                    <option value="<?= item.id ?>"><?= item.get('nombre') ?></option>
                                                    <?}
                                                )
                                            } ?>
                                        </select>
                                    </div>
                                    <div>
                                        <label>Departamento<span class="required">*</span></label>
                                        <select id="departamento">
                                            <?if(departamentos!=null && departamentos.length > 0 ){
                                                var c=0;
                                                _.each(departamentos, function(item) {
                                                    if(c==0){
                                                    ?><option></option><?
                                                    }
                                                    c++;
                                                    ?>
                                                    <option value="<?= item.id ?>"><?= item.get('nombre') ?></option>
                                                    <?}
                                                )
                                            } ?>
                                        </select>
                                    </div>
                                    <div>
                                        <label>Municipio<span class="required">*</span></label>
                                        <select id="municipio">
											<?if(municipios!=null && municipios.length > 0 ){
                                                var c=0;
                                                _.each(municipios, function(item) {
                                                    if(c==0){
                                                    ?><option></option><?
                                                    }
                                                    c++;
                                                    ?>
                                                    <option value="<?= item.id ?>"><?= item.get('nombre') ?></option>
                                                    <?}
                                                )
                                            } ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="section data_long">
                                    <h4 class="alert alert-info">Datos Representante Legal y Propietario</h4>
                                    <div>
                                        <label>Nombre Representante Legal <span class="required">*</span></label>
                                        <input value="<?=clienteMod.get('nombreRepresentante')?>" id="nombreRepresentante" type="text" maxlength="100" placeholder="Nombre" title="Ingrese unicamente letras">
                                    </div>
                                    <div>
                                        <label>Cédula Representante Legal <span class="required">*</span></label>
                                        <input value="<?=clienteMod.get('documentoRepresentante')?>" id="documentoRepresentante" type="text" maxlength="15" title="El documento debe tener maximo 15 digitos">
                                    </div>
                                    <div>
                                        <label>Nombre Propietario <span class="required">*</span></label>
                                        <input value="<?=clienteMod.get('nombreDueno')?>" id="nombreDueno" type="text"  maxlength="100" title="Ingrese unicamente letras">
                                    </div>
<!--
                                    <div>
                                        <label>Cédula Propietario <span class="required">*</span></label>
                                        <input id="documentoDueno" type="text" maxlength="10" title="El documento debe tener maximo 10 digitos">
                                    </div>
-->
                                    <div>
                                        <label>Email de propietario</label>
                                        <input value="<?=clienteMod.get('mailDueno')?>" id="emailDueno" type="text" class="valida_correo" maxlength="50" title="El email debe tener minimo 5 caracteres">
                                    </div>
									<div>
                                        <label>Telefono del propietario</label>
                                        <input value="<?=clienteMod.get('celularDueno')?>" id="telefonoDueno" type="text" maxlength="25" title="El telefono debe tener minimo 7 digitos">
                                    </div>
                                </div>
                                <button type="button" id="cancel" class="btn" class="btn btn-secondary">Cancelar</button>
                                <button id="btnmodificarcliente" type="submit" class="btn btn-primary pull-right col-lg-2 mustHidden cliente_modificar">Guardar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>		  	          
