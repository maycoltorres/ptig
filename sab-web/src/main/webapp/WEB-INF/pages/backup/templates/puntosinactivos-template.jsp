<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="puntosinactivosView-Template" type="text/template">
<div class="tabbable" id="clientesMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">Home</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Puntos Inactivos
	        <button type="button" id="btnPuntosctivos" data-id="17" class="btn btn-small btn-primary btnLoad mustHidden lista_punto_act">Puntos Activos</button>
            </h1>
        <div class="container" id="PuntosinactivosListContainer">
		  </div>
		</div>
	</div>
</div>
</script>

<script id="puntosinactivosMenuView-template" type="text/template">
		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
		        <th>Nombre</th>
		        <th>Telefono</th>
				<th>Direccion</th>
                <th>				
			  </th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(puntosinactivos !==null  && puntosinactivos.length>0 ){ _.each(puntosinactivos, function(item) { ?>
		        <tr>
		          <td><?= item.get('nombre') ?></td>
		          <td><?= item.get('telefono') ?></td>
				  <td><?= item.get('direccion') ?></td>
		          <td>
		            <div>
 					<button type="button" id="btnEliminarCliente" data-id="<?= item.id ?>" 
                    class="btn btn-small btn-warning btnLoad mustHidden punto_activar" data-toggle="tooltip" data-placement="left" title="Activar Punto Inactivo">Activar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay puntos inactivos</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>

