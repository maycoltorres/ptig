<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script id="CreargrupoView-Template" type="text/template">
    <div class="tabbable" id="creargrupoView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Grupos</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Crear grupos de clientes</h1>
                <hr/>
                <div class="container">
                    <div class="panel panel-info">
                        <div class="panel-heading"></div>
                        <div class="panel-body">
                            <form id="nombrar_grupo">
                                <h3>Crear un nuevo grupo</h3>

                                <div class="container_input">
                                    <label>Nombre<span class="required">*</span> </label>
                                    <input id="nombre" class="valida_alfas" type="text" required maxlength="100"
                                           title="El nombre del grupo puede contener de 1 a 100 caracteres">
                                </div>
                                <div class="container_submit">
                                    <button type="button" id="cancel" class="btn" class="btn btn-secondary">Cancelar</button>
                                    <input type="submit" class="btn btn-primary mustHidden grupo_crear" value="Guardar" id="btncreargrupo">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>		  	          
