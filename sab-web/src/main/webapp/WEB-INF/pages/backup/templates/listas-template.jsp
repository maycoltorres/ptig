<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="listasMenuView-Template" type="text/template">
<script id="clientsListView-Template" type="text/template">
		<table class="table">
		    <thead>
		      <tr>
		        <th>Nombre</th>
		        <th>Descripcion</th>
				<th>Contacto</th>
                <th>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createClientModal"><i class="icon-plus"></i></button>
                </th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(clients!==null && clients.length>0){ _.each(clients, function(item) { ?>
		        <tr>
		          <td><?= item.get('name') ?></td>
		          <td><?= item.get('description') ?></td>
				  <td><?= item.get('contactName') ?></td>
		          <td>
		            <div>
		              <button type="button" id="Load_<?= item.get('name').split(' ').join('_') ?>" data-dashboard-id="<?= item.id ?>" 
		                  class="btn btn-small btn-primary btnLoad">Cargar</button>
		              <button type="button" id="Delete_<?= item.get('name').split(' ').join('_') ?>" data-dashboard-id="<?= item.id ?>" 
		                  class="btn btn-small btn-danger btnDelete">Borrar</button>
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
