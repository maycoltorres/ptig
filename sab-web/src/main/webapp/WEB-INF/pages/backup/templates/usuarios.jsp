<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="plantilla-listado-de-usuarios" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Usuarios</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Usuarios  <?=titulo?>
                    <? if(titulo == "Activos"){ ?>
                            <button  id="btnCrearUsuario" class="btn btn-primary mustHidden usuario_crear" data-toggle="tooltip" data-placement="left" title="Crear nuevo cliente">
                                <i class="icon-plus"></i>
                            </button>
                    <?}?>
                    <a class="mustHidden <?=clase?>" href="<?=url?>">
                        <button class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver Usuarios <?=contra?>">
                            Usuarios <?=contra?>
                            <i class="icon-eye-close"></i>
                        </button>
                    </a>
                </h1>
            <div class="container" id="listado-de-usuarios">
                <table class="table pres-crollable table-hover data_table">
                    <thead>
                      <tr>
                        <th>Nombre de usuario</th>
                        <th>Email</th>
                        <th>Cliente Asignado</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <?
                      if(usuarios!==null && usuarios.length>0){_.each(usuarios, function(item) { ?>
                        <tr>
                          <td><?= item.get('fullName') ?></td>
                          <td><?= item.get('email') ?></td>
                          <td><?= item.get('id_cliente') ?></td>
                          <td>
                            <div>
                        <!--	<a href="#modificarcliente">modi</a>-->
                            <button type="button" id="<?=id_boton?>" data-id="<?= item.id ?>" class="btn btn-small btn-primary btnLoad mustHidden <?=claseBoton?>"><?=button?></button>
                            <!--<button type="button" id="btnVerPuntos" data-id="" class="btn btn-small btn-primary btn-warning">Ver puntos</button>-->
                            <? if(titulo == "Activos"){ ?>
                                <button type="button" id="btn-inactivar" data-id="<?= item.id ?>" class="btn btn-small btn-danger btnLoad mustHidden usuario_desactivar">Desactivar</button>
                            <?}?>
                            </div>
                          </td>
                        </tr>
                      <?
                       }); }
                        else { ?>
                        <tr>
                          <td colspan="8">No hay usuarios <?=titulo?></td>
                        </tr>
                      <? } ?>
                    </tbody>
              </table>
              </div>
            </div>
        </div>
    </div>
</script>

<script id="guardar_usuario" type="text/template">
    <div class="tabbable" id="crearclienteView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab"><b>Clientes</b></a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1><?=titulo?> usuarios</h1>
                <div class="container">
                    <div class="panel panel-primary">
                        <div class="panel-body">
                            <form id="valida">
                                <div class="section data_medium">
                                    <h4 class="alert alert-info">Datos Basicos</h4>
                                    <div>
                                        <label>Nombre de usuario</label>
                                        <input type="text" id="nombreUsuario" class="valida_letras" maxlength="50" value="<?=usuario.get('fullName')?>"/>
                                    </div>
                                    <div>
                                        <label>Correo</label>
                                        <input type="text" id="correoUsuario" class="valida_correo" maxlength="50" value="<?=usuario.get('email')?>"/>
                                    </div>
                                    <div>
                                        <label>Contraseña</label>
                                        <input type="password" id="contrasenaUsuario" class="valida_claves" maxlength="20" value="<?=contraseña?>"/>
                                    </div>
                                    <div>
                                        <label>Repita la contraseña</label>
                                        <input type="password" id="contrasenaUsuario2" class="valida_claves" maxlength="20" value="<?=contraseña?>"/>
                                    </div>
                                    <div>
                                        <label>Rol</label>
                                        <input type="text" id="rolUsuario" value=""/>
                                    </div>
                                     <div>
                                        <label>Cliente</label>
                                        <input type="text" id="cliente" value=""/>
                                    </div>
                                </div>
                                <div class="section data_medium">
                                    <h4 class="alert alert-info">Configuracion de  acceso</h4>
                                    <div>
                                        <label>Grupo</label>
                                        <input type="text" id="grupoUsuario" value=""/>
                                    </div>
                                    <div>
                                        <label>Punto</label>
                                        <input type="text" id="puntoUsuario" value=""/>
                                    </div>
                                </div>
                                <div class="section">
                                    <?if (titulo=="Crear"){ ?>
                                        <button type="button" id="btnGuardarUsuario" class="btn btn-small btn-success pull-right mustHidden usuario_crear">Guardar</button>
                                    <?}else{?>
                                        <button type="button" id="btnGuardarUsuario" class="btn btn-small btn-success pull-right mustHidden usuario_modificar">Guardar</button>
                                    <?}?>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>                     
