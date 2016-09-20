<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script id="mainPuntosMenuView-Template" type="text/template">
    <div class="tabbable" id="puntosMainView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active">
                <a href="#home" data-toggle="tab">Puntos</a>
            </li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home-puntos">
                <h1>Puntos Activos
                    <button data-idc="<?= id ?>" id="nuevo_punto" type="button"
                            class="btn btn-primary mustHidden punto_crear" title="Crear nuevo punto"><i
                            class="icon-plus"></i></button>
                    <button data-idc="<?= id ?>" id="btnpuntosinactivos" class="btn btn-info mustHidden lista_punto_des"
                            data-toggle="tooltip" data-placement="left" title="Ver Puntos Inactivos">Puntos inactivos <i
                            class="icon-eye-open"></i></button>
                </h1>
                <div class="container" id="PuntosListContainer">
                    <table class="table pres-crollable table-hover data_table">
                        <thead>
                        <tr>
                            <th>Nombre Punto</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?if(puntos!==null && puntos.length > 0 ){ _.each(puntos, function(item) { ?>
                        <tr>
                            <td><?= item.get('nombre') ?></td>
                            <td><?= item.get('telefono') ?></td>
                            <td><?= item.get('direccion') ?></td>
                            <td>
                                <div>
                                    <button type="button" id="btnCargarPunto" data-id="<?= item.id ?>"
                                            class="btn btn-small btn-primary btnLoad mustHidden punto_ver">Ver datos
                                    </button>

                                    <button type="button" id="btnEliminarPunto" data-id="<?= item.id ?>"
                                            class="btn btn-small btn-danger btnLoad mustHidden punto_desactivar">
                                        Inactivar
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <?
                       }); }
                        else { ?>
                        <tr>
                            <td colspan="4">No hay puntos registrados</td>
                        </tr>
                        <? } ?>
                        </tbody>
                    </table>
                    <div id="confirmDelete"></div>
                </div>
            </div>
        </div>
    </div>
</script>

<!--Inicia la creacion del Punto en un modal nuevo-->
<jsp:include page="crearPunto-template.jsp"/>
<!--Finaliza la creacion del Punto en el modal -->


<script id="puntos_por_articulo" type="text/template">
    <div class="tabbable" id="puntosMainView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active">
                <a href="#home" data-toggle="tab">Puntos por Articulo</a>
            </li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home-puntos">
                <h1>Puntos <?=title?>
                    <button data-idc="<?= id ?>" id="<?=id_button?>" class="btn btn-info"
                            data-toggle="tooltip" data-placement="left" title="Ver Puntos Inactivos">Puntos inactivos <i
                            class="icon-eye-open"></i></button>
                </h1>
                <div class="container" id="PuntosListContainer">
                    <h4 class="alert alert-info">Puntos en los que esta el articulo "<span id="articulo_nombre"><?=articulo_nombre?></span>"</h4>
                    <table class="table pres-crollable table-hover data_table">
                        <thead>
                        <tr>
                            <th>Nombre Punto</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <?if(puntos!==null && puntos.length > 0 ){ _.each(puntos, function(item) { ?>
                        <tr>
                            <td><?= item.nombre ?></td>
                            <td>
                                <div>
                                    <?if(title=="Activos"){ ?>
                                        <button type="button" id="btnCargarPunto" data-id="<?= item.id ?>" class="btn btn-small btn-primary btnLoad mustHidden punto_ver">Ver datos</button>
                                    <?}else{?>
                                        <button type="button" id="btn-activar" data-id="<?= item.id ?>" class="btn btn-small btn-warning mustHidden punto_activar">Activar</button>
                                    <?}?>
                                </div>
                            </td>
                        </tr>
                        <?
                           }); }
                            else { ?>
                        <tr>
                            <td colspan="4">No hay puntos registrados</td>
                        </tr>
                        <? } ?>
                        </tbody>
                    </table>
                    <div class="section">
                        <button type="button" id="btnRegresar" class="btn btn-small btn-back">
                            Regresar
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>




