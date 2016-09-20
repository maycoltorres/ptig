<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script id="crearPuntoScript" type="text/template">
<div id="crearPunto">
    <div class="tabbable" id="puntosMainView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active">

                <a href="#home" data-toggle="tab">Puntos</a>
            </li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home-puntos">
                <?if(action=="mod"){?>
                    <h2 class="btn-primary">Modificar Punto</h2>
                <?}else{?>
                    <h2 class="btn-primary">Crear un Punto nuevo</h2>
                <?}?>
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
                        <input class="letra" class="valida_letras" type="text" maxlength="6" placeholder="A bis"/>
                    </div>
                    <div>
                        <label>cardinal</label>
                        <input class="" type="text" class="" maxlength="9" placeholder="sur"/>
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
                        <input class="letra" type="text"class="valida_letras"  maxlength="6" placesholder="A bis"/>
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
                    <div class="panel-heading">
                        <h4 class="alert alert-info">Datos Basicos</h4>
                        <div class="data_medium panel-heading section">
                            <div>
                                <label>Nombre Punto<span class="required">*</span> </label>
                                <input id="nombrePunto" class="valida_numeros" type="text"  maxlength="100" required value="<?=punto.get('nombre') ?>">
                            </div>
                            <div>
                                <label>Direccion Punto<span class="required">*</span></label>
                                <input readonly id="direccionPunto" class="valida_letras" type="text" maxlength="100" required value="<?=punto.get('direccion') ?>">
                            </div>
                            <div>
                                <label>Telefono 1 Punto<span class="required">*</span></label>
                                <input id="telefonoPunto" class="valida_numeros"type="text" maxlength="25" required value="<?=punto.get('telefono') ?>">
                            </div>
                            <div>
                                <label>Extension 1 Punto</label>
                                <input id="extensionPunto" class="valida_numeros" type="text" maxlength="4" value="<?=punto.get('extension') ?>">
                            </div>
                            <div>
                                <label>Telefono 2 Punto</label>
                                <input id="telefono2Punto" class="valida_numeros" type="text" maxlength="25" value="<?=punto.get('telefono2') ?>">
                            </div>
                            <div>
                                <label>Extension 2 Punto</label>
                                <input id="extension2Punto" class="valida_numeros" type="text" maxlength="4" value="<?=punto.get('extension2') ?>">
                            </div>
                            <div>
                                <label>Centro Costos</label>
                                <input id="centro_costo_idPunto" class="valida_numeros" type="text" maxlength="3" value="<?=punto.get('centro_costo_id') ?>">
                            </div>
                            <div>
                                <label>Tipo Negocio<span class="required">*</span></label>
                                <select id="tipo_negocio_idPunto">
                                    <option value="1">Restaurante</option>
                                    <option value="2">Bar</option>
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
                            <div>
                                <label class="float_left" >Marca </label>
                                <input placeholder="Digite la palabra incial, y seleccione un valor" class="float_left adap-left" id="marca" type="text" maxlength="100"/>
                                </br><span id="marcaAdd"></span>
                            </div>
                        </div>
                    </div>
            <!--
                <div class="panel-heading">
                    <h4 class="alert alert-info" >Marcas</h4>
                    <div class="section">
                        <div>
                            <label>Marca<span class="required">*</span></label>
                            <input type="text" data-provide="typeahead" id="NombreMarca"  maxlength="20">
                            <input type="button" class="btn  btn-primary"  return="false" value="Agregar Marca" id="btnagregarmarca" >
                        </div>
                    </div>
                    <div class="section">
                        <div>
                            <h5>Marcas Agregadas al Punto</h5>
                        </div>
                    </div>
                    <div class="section">
                        <div>
                            <input type="checkbox"/>
                            <span>marca 1</span>
                        </div>
                    </div>
                </div>
                -->
                <?if(action=="mod"){?>
                    <div class="panel-heading">
                        <h4 class="alert alert-info" >Parametros del Punto</h4>
                        <div class="section list_check">
                            <div>
                                <input type="checkbox" id="almacenarNpunto" class="form-control" ><label for="almacenarNpunto">Permitir almacenar en el Punto</label>
                            </div>
                            <div>
                                <input type="checkbox" id="apoyalicrec" class="form-control"><label>Permitir apoyo de licencias en Red</label>
                            </div>
                            <div>
                                <input type="checkbox" id="autoUsuEliminar" class="form-control"><label>Autorizar usuario que elimina </label>
                            </div>
                            <div>
                                <input type="checkbox" id="contXdenominacion" class="form-control"><label>Contar dinero por Denominacion </label>
                            </div>
                            <div>
                                <input type="checkbox" id="factNpunto" class="form-control"><label>Permitir facturar en el Punto </label>
                            </div>
                            <div>
                                <input type="checkbox" id="manNlicencias" class="form-control"><label> Manejar Varias Licencias en el Punto</label>
                            </div>
                            <div>
                                <input type="checkbox" id="fvmenseros" class="form-control"><label> Permitir varas formas de venta meseros</label>
                            </div>
                            <div>
                                <input type="checkbox" id="opLicNred" class="form-control"><label> Permitir varas formas de venta meseros</label>
                            </div>
                            <div>
                                <input type="checkbox" id="cierreocultocaja" class="form-control"><label>Realizar cierre oculto caja</label>
                            </div>
                            <div>
                                <input type="checkbox" id="claveanular" class="form-control"><label> Solicitar clave para anular</label>
                            </div>
                            <div>
                                <input type="checkbox" id="imprRemotas" class="form-control"><label>Permitir impresion Remmota </label>
                            </div>
                            <div>
                                <input type="checkbox" id="autAnularVerificacion" class="form-control"><label>Autorizar usuario que anula verificacion</label>
                            </div>
                            <div>
                                <input type="checkbox" id="capNpersonasAt" class="form-control"><label>Capturar Numero de personas atendidas </label>
                            </div>
                            <div>
                                <input type="checkbox" id="capCodmesero" class="form-control"><label>Capturar codigo de mesero</label>
                            </div>
                            <div>
                                <input type="checkbox" id="capCodAutoserv" class="form-control"><label>Capturar codigo autoservicio</label>
                            </div>
                            <div>
                                <input type="checkbox" id="denoatendidopor" class="form-control"><label>denominar persona que atiende mesa</label>
                            </div>
                            <div>
                                <input type="checkbox" id="factFresumido" class="form-control"><label>Facturar en Formato Resumido </label>
                            </div>
                            <div>
                                <input type="checkbox" id="factSinDocVerif" class="form-control"><label>Facturar sin Documento de Verificacion  </label>
                            </div>
                            <div>
                                <input type="checkbox" id="impremotacpfactura"  class="form-control"><label>Generar impresion remota de copia de la Factura </label>
                            </div>
                            <div>
                                <input type="checkbox" id="controlconspalabra" class="form-control"><label>Identificar el control de consumo con la palabra </label>
                            </div>
                            <div>
                                <input type="checkbox" id="impticketAutoSrv" class="form-control"><label>Imprimir tiquete de pedido autoservicio </label>
                            </div>
                            <div>
                                <input type="checkbox" id="inclivaPventa" class="form-control"><label>Incluir IVA en precio de Venta </label>
                            </div>
                            <div>
                                <input type="checkbox" id="claveanularVerifi" class="form-control"><label>Requerir clave para unilar verificacion </label>
                            </div>
                        </div>
                    </div>
                <?}?>
                    <div class="section">
                        <button type="button" data-id="<?= id_cliente ?>" id="cancel" class="btn"  class="btn btn-secondary">Cancelar</button>
                        <?if(action=="mod"){?>
                            <button type="button" id="btnCrearPunto" class="btn btn-primary mustHidden punto_modificar">Guardar</button>
                        <?}else{?>
                            <button type="button" id="btnCrearPunto" class="btn btn-primary mustHidden punto_crear">Guardar</button>
                        <?}?>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</script>