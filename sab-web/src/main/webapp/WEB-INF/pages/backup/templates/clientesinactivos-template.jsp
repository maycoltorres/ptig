<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="clientesinactivosView-Template" type="text/template">
<div class="tabbable" id="clientesMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">Home</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Clientes Inactivos
			    <button type="button" id="btnClientesActivos" data-id="17" class="btn btn-small  btn-info btnLoad"  data-toggle="tooltip" data-placement="left" title="Ver clientes activos">Clientes Activos   <i class="icon-eye-open"></i></button>
			</h1>
		  <div class="container" id="ClientesinactivosListContainer">
		  </div>
		</div>
	</div>
</div>
</script>

<script id="clientesinactivosMenuView-template" type="text/template">
		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
		        <th>Razón Social</th>
		        <th>NIT</th>
				<th>Teléfono</th>
				<th>Nombre Propietario</th>
                <th>				
			  </th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(clientesinactivos !==null  && clientesinactivos.length>0 ){ _.each(clientesinactivos, function(item) { ?>
		        <tr>
		          <td><?= item.get('nombre') ?></td>
		          <td><?= item.get('nit') ?></td>
				  <td><?= item.get('telefono') ?></td>
				  <td><?= item.get('nombreDueno') ?></td>
		          <td>
		            <div>
 					<button type="button" id="btnactivarcliente" data-id="<?= item.id ?>" 
                    class="btn btn-small btn-warning btnLoad pro_BtnInactivarCliente mustHidden cliente_activar" data-toggle="tooltip" data-placement="left" title="Activar cliente inactivo">Activar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay clientes inactivos</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>

