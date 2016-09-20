<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="plantilla-listado-de-roles" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Roles</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Roles  <?=titulo?>
                    <? if(titulo == "Activos"){ ?>
                            <button  id="btnCrearRol" class="btn btn-primary mustHidden rol_crear" data-toggle="tooltip" data-placement="left" title="Crear nuevo rol">
                                <i class="icon-plus"></i>
                            </button>
                    <?}?>
                    <a href="<?=url?>" class="mustHidden <?=clase?>">
                        <button class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver Roles <?=contra?>">
                            Roles <?=contra?>
                            <i class="icon-eye-close"></i>
                        </button>
                    </a>
                </h1>
            <div class="container" id="listado-de-roles">
                <table class="table pres-crollable table-hover data_table">
                    <thead>
                      <tr>
                        <th>Nombre</th>
                        <th>Creado por</th>
                        <th>Fecha de Creacion</th>
                        <th>Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <?
                      if(roles!==null && roles.length>0){_.each(roles, function(item) { ?>
                        <tr>
                          <td><?= item.get('nombre') ?></td>
                          <td><?= item.get('createBy') ?></td>
                          <td><?= item.get('createOn') ?></td>
                          <td>
                            <div>
                        <!--	<a href="#modificarcliente">modi</a>-->
                            <button type="button" id="<?=id_boton?>" data-id="<?= item.id ?>" class="btn btn-small btn-primary btnLoad mustHidden <?=claseBoton?>"><?=button?></button>
                            <!--<button type="button" id="btnVerPuntos" data-id="" class="btn btn-small btn-primary btn-warning">Ver puntos</button>-->
                            <? if(titulo == "Activos"){ ?>
                                <button type="button" id="btn-inactivar" data-id="<?= item.id ?>" class="btn btn-small btn-danger btnLoad mustHidden rol_desactivar">Desactivar</button>
                            <?}?>
                            </div>
                          </td>
                        </tr>
                      <?
                       }); }
                        else { ?>
                        <tr>
                          <td colspan="8">No hay roles <?=titulo?></td>
                        </tr>
                      <? } ?>
                    </tbody>
              </table>
              </div>
            </div>
        </div>
    </div>
</script>

<script id="guardar_rol" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Roles</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1><?=titulo?> roles</h1>
                <div class="panel-heading">
                    <h2 class="btn-primary">Datos del rol</h2>
                </div>
                <div class="container" id="campos_de_rol">
                    <form id="valida">
                        <div class="section data_medium">
                            <div class="section">
                                <label>Nombre para el rol</label>
                                <input id="nombreRol" type="text" value="<?=rol.get('nombre')?>" maxlength="50"/>
                            </div>
                            <div class="section">
                                <label></label>
                                <!--<input type="button" value="Asignar permisos"/>-->
                            </div>
                        </div>
                    </form>
                    <div id="transaccion-container">

                    </div>
                    <?if (titulo=="Crear"){ ?>
                    <button type="button" id="btnGuardar" class="btn btn-small btn-success mustHidden rol_crear">Guardar</button>
                    <?}else{?>
                    <button type="button" id="btnGuardar" class="btn btn-small btn-success mustHidden rol_modificar">Guardar</button>
                    <?}?>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="mis_roles" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Roles</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>
                    <?if(titulo=="Activados"){?>
                        Escoger roles
                        <button  id="btnAsignarRol" class="btn btn-primary mustHidden rolCliente_crear" data-toggle="tooltip" title="Asignar nuevos roles">
                            <i class="icon-plus"></i>
                        </button>
                    <?}else{?>
                        Roles Desactivados
                    <?}?>

                    <a href="<?=url?>" class="mustHidden <?=clase?>">
                        <button class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver Roles <?=contra?>">
                            Roles <?=contra?>
                            <i class="icon-eye-close"></i>
                        </button>
                    </a>
                </h1>
                <div class="panel-heading">
                    <?if(titulo=="Activados"){?>
                        <h4 class="alert alert-info">Escoja los roles que van a estar disponibles para asignar a los usuarios</h4>
                    <?}?>
                </div>

                <div class="container" id="campos_de_rol">
                    <table class="table pres-crollable table-hover data_table">
                        <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?
                      if(roles!==null && roles.length>0){_.each(roles, function(item) { ?>
                        <tr>
                            <td><?= item.get('nombre') ?></td>
                            <td>
                                <button type="button" id="<?=id_boton?>" data-id="<?= item.id ?>" class="btn btn-small btn-primary btnLoad mustHidden <?=claseBoton?>"><?=button?></button>
                                <!--<button type="button" id="btnVerPuntos" data-id="" class="btn btn-small btn-primary btn-warning">Ver puntos</button>-->
                                <? if(titulo == "Activados"){ ?>
                                <button type="button" id="desactivaRolCliente" data-id="<?= item.id ?>" class="btn btn-small btn-danger btnLoad mustHidden rolCliente_desactivar">Desactivar</button>
                                <?}?>
                            </td>
                        </tr>
                        <?
                       }); }
                        else { ?>
                        <tr>
                            <td colspan="8">No hay roles</td>
                        </tr>
                        <? } ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="asignar_roles" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Roles</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Escoger roles</h1>
                <div class="panel-heading">
                    <h2 class="btn-primary">Escoja los roles que van a estar disponibles para asignar a los usuarios</h2>
                </div>
                <div class="container" id="campos_de_rol">
                    <table class="table pres-crollable table-hover data_table with_check">
                        <thead>
                        <tr>
                            <th>Seleccione</th>
                            <th>Nombre</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?
                      if(roles!==null && roles.length>0){_.each(roles, function(item) { ?>
                        <tr>
                            <td><input type="checkbox" value="<?= item.id ?>" /></td>
                            <td><?= item.get('nombre') ?></td>
                        </tr>
                        <?
                       }); }
                        else { ?>
                        <tr>
                            <td colspan="8">No hay roles</td>
                        </tr>
                        <? } ?>
                        </tbody>
                    </table>
                </div>
                <div class="section">
                    <button type="button" id="AsignaRoles" class="btn btn-small btn-primary btnLoad mustHidden rolCliente_crear">Escoger estos roles</button>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="modificarRolesCliente-template" type="text/template">
    <div class="tabbable">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Roles</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Modificar rol</h1>
                <div class="panel-heading">
                    <h2 class="btn-primary">Elija los permisos que va a tener el rol</h2>
                </div>
                <div class="container" id="contenedor_de_permisos">
                            <div class="alert alert-info short_data">
                                <div>
                                    <label>Nombre del rol </label>
                                    <input id="nombreRol" type="text" value="<?=rolCliente.get('nombre')?>" maxlength="50"/>
                                </div>
                            </div>
                    <div id="transaccion-container">

                    </div>
                </div>
                <div class="section">
                    <button type="button" id="GuardaRolCliente" class="btn btn-small btn-primary btnLoad mustHidden rolCliente_modificar">Guardar cambios</button>
                </div>
            </div>
        </div>
    </div>
</script>