<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="mainClientesView-Template" type="text/template">
    <div class="tabbable" id="clientesMainView">
        <ul class="nav nav-tabs container-fluid" id="tabHeaders">
            <li class="active"><a href="#home" data-toggle="tab">Clientes</a></li>
        </ul>
        <div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
            <div class="tab-pane active container-fluid" id="home">
                <h1>Clientes  Activos    <a class="pro_BtnCrearClientes mustHidden cliente_crear" href="#crearcliente"><button  class="btn btn-primary" data-toggle="tooltip" data-placement="left" title="Crear nuevo cliente"><i class="icon-plus"></i></button></a>
                    <button id="btnclientesinactivos"  class="btn btn-small btn-info pro_BtnListaClientesInactivos lista_cliente_des mustHidden" data-toggle="tooltip" data-placement="left" title="Ver clientes inactivos">Clientes Inactivos   <i class="icon-eye-close"></i></button>
                </h1>
              <div class="container" id="ClientesListContainer">
              </div>
            </div>
        </div>
    </div>
</script>

<script id="clientesMenuView-template"  type="text/template">
		<table class="table pres-crollable table-hover data_table data_table">
		    <thead>
		      <tr>
		        <th>Razón Social</th>
		        <th>NIT</th>
				<th>Teléfono</th>
				<th>Nombre Propietario</th>
                <th>Acciones</th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(clientes!==null && clientes.length>0){ _.each(clientes, function(item) { ?>
		        <tr>
		          <td><?= item.get('nombre') ?></td>
		          <td><?= item.get('nit') ?></td>
				  <td><?= item.get('telefono') ?></td>
				  <td><?= item.get('nombreDueno') ?></td>
		          <td>
		            <div>
				<!--	<a href="#modificarcliente">modi</a>-->
					<button  type="button" id="btnCargarCliente" data-id="<?= item.id ?>" class="btn btn-small btn-primary btnLoad pro_BtnModificarClientes mustHidden cliente_ver">Ver datos</button>
                    <button  type="button" id="btnVerPuntos" data-id="<?= item.id ?>" class="btn btn-small btn-primary btn-warning pro_BtnListaPuntos mustHidden lista_punto_act">Ver puntos</button>
 					<button  type="button" id="btnEliminarCliente" data-id="<?= item.id ?>" class="btn btn-small btn-danger btnLoad pro_BtnInactivarCliente mustHidden cliente_desactivar">Inactivar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay clientes registrados</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>
