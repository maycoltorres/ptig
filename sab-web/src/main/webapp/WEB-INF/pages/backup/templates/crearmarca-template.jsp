<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="CrearmarcaView-Template" type="text/template">
    <div class="tabbable" id="crearmarcaView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Marcas</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <?if(datos.nombre_marca!=""){?>
                    <h1>Modificar Marcas</h1>
                <?}else{?>
                    <h1>Crear Marcas</h1>
                <?}?>
            </div>
            <div class="container">
                <form id="valida">
                    <div class="panel-heading">
                        <h4 class="alert alert-info">Creacion de Nueva Marca</h4>
                            <div class="section data_long">
                                <div>
                                    <label>Nombre <span class="required">*</span></label>
                                    <input id="nombre" value="<?=datos.nombre_marca?>" type="text" maxlength="100">
                                </div>
                            </div>
                        <div class="section data_long">
                            <div>
                                <label class="float_left">Busque el cliente a asignar</label>
                                <input class="float_left" type="text" data-provide="typeahead" id="NombreCliente" value="<?=datos.nombre_cliente?>">
                                <span id="resultado"></span>
                                </br>
                            </div>
                        </div>
                        <div class="section section-height">
                            <div>
                                <?if(datos.nombre_marca!=""){?>
                                <input type="button" class="btn btn-success button_input mustHidden marca_modificar"  value="Continuar" id="btncrearmarca">
                                <?}else{?>
                                <input type="button" class="btn btn-success button_input mustHidden marca_crear"  value="Continuar" id="btncrearmarca">
                                <?}?>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</script>