<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="plantilla-listado-de-transacciones" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Transacciones</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Permisos  <?=titulo?>
                    <? if(titulo == "Activos"){ ?>
                            <button  id="btnCrearTransaccion" class="btn btn-primary" data-toggle="tooltip" data-placement="left" title="Crear nuevo cliente">
                                <i class="icon-plus"></i>
                            </button>
                    <?}?>
                    <a href="<?=url?>">
                        <button class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver Transacciones <?=contra?>">
                            Transacciones <?=contra?>
                            <i class="icon-eye-close"></i>
                        </button>
                    </a>
                </h1>
            <div class="container" id="listado-de-transacciones">
                <table class="table pres-crollable table-hover data_table">
                    <thead>
                      <tr>
                        <th>Nombre</th>
                        <th>url</th>
                        <th>operacion</th>
                        <th>Cliente Asignado</th>
                        <th>Grupo</th>
                        <th>Rol</th>
                        <th>Punto</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <?
                      if(transacciones!==null && transacciones.length>0){_.each(transacciones, function(item) { ?>
                        <tr>
                          <td><?= item.get('username') ?></td>
                          <td><?= item.get('fullName') ?></td>
                          <td><?= item.get('email') ?></td>
                          <td><?= item.get('id_cliente') ?></td>
                          <td><?= item.get('id_grupo') ?></td>
                          <td>rol</td>
                          <td>punto</td>
                          <td>
                            <div>
                        <!--	<a href="#modificarcliente">modi</a>-->
                            <button type="button" id="<?=id_boton?>" data-id="<?= item.id ?>" class="btn btn-small btn-primary btnLoad"><?=button?></button>
                            <!--<button type="button" id="btnVerPuntos" data-id="" class="btn btn-small btn-primary btn-warning">Ver puntos</button>-->
                            <? if(titulo == "Activos"){ ?>
                                <button type="button" id="btn-inactivar" data-id="<?= item.id ?>" class="btn btn-small btn-danger btnLoad">Desactivar</button>
                            <?}?>
                            </div>
                          </td>
                        </tr>
                      <?
                       }); }
                        else { ?>
                        <tr>
                          <td colspan="8">No hay transacciones <?=titulo?></td>
                        </tr>
                      <? } ?>
                    </tbody>
              </table>
              </div>
            </div>
        </div>
    </div>
</script>

<script id="guardar_transaccion" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Transacciones</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1><?=titulo?> transacciones</h1>
                <div class="panel-heading">
                    <h2 class="btn-primary">Datos del transaccion</h2>
                </div>
                <div class="container" id="campos_de_transaccion">
                    <form id="valida">
                        <div class="section data_medium">
                            <div class="section">
                                <label>Nombre</label>
                                <input value="<?=transaccion.get('fullName')?>"/>
                            </div>
                            <div class="section">
                                <label>Nombre de transaccion</label>
                                <input value="<?=transaccion.get('username')?>"/>
                            </div>
                            <div class="section">
                                <label>Correo</label>
                                <input value="<?=transaccion.get('email')?>"/>
                            </div>
                            <div class="section">
                                <label>Contraseña</label>
                                <input value="<?=contraseña?>"/>
                            </div>
                            <div class="section">
                                <label>Repita la contraseña</label>
                                <input value="<?=contraseña?>"/>
                            </div>
                            <div class="section">
                                <label>Rol</label>
                                <input value="<?=rol.get('name')?>"/>
                            </div>
                            <div class="section">
                                <button type="button" id="btnGuardar" class="btn btn-small btn-success">Guardar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="seleccion_de_transacciones" type="text/template">
    <div class="table_container contra-height">
        <h1>Permisos</h1>
        <div class="container contra-height" id="listado-de-transacciones">
            <table class="table pres-crollable table-hover data_table">
                <thead>
                <tr>
                    <th>Seleccion</th>
                    <th>Nombre</th>
                    <th>Descripcion</th>
                </tr>
                </thead>
                <tbody>
                <?
              if(transacciones!==null && transacciones.length>0)
                {_.each(transacciones, function(item) { ?>
                    <tr>
                        <td><input type="checkbox" value="<?= item.id ?>"></td>
                        <td><?= item.get('nombre') ?></td>
                        <td>Descripcion</td>
                    </tr>
                <?}); }else { ?>
                    <tr>
                        <td colspan="8">No hay transacciones</td>
                    </tr>
                <? } ?>
                </tbody>
            </table>
        </div>
    </div>

</script>