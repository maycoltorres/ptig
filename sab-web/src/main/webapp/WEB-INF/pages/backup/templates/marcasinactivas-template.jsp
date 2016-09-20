<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="marcasinactivasView-Template" type="text/template">
<div class="tabbable" id="marcasinactivosMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">marcas</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Marcas inactivas<button  id="btnmarcasActivas"  class="btn btn-small btn-info mustHidden lista_marca_act" data-toggle="tooltip" data-placement="left" title="Ver marcas activos">marcas Activos   <i class="icon-eye-open"></i></button>
		    </h1>			
		  <div class="container" id="MarcasinactivasListContainer">
		  </div>
		</div>
	</div>
</div>
</script>

<script id="marcasinactivasMenuView-template" type="text/template">
		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
		 		<th>Nombre Marca</th>
                <th>				
			  </th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(marcasinactivas !==null  && marcasinactivas.length>0 ){ _.each(marcasinactivas, function(item) { ?>
		        <tr>
		          <td><?= item.get('nombre') ?></td>
		          <td>
		            <div>
 					<button type="button" id="btnactivarMarca" data-id="<?= item.id ?>" class="btn btn-small btn-warning btnLoad marca_activar mustHidden" data-toggle="tooltip" data-placement="left" title="Activar Marca inactivo">Activar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay marcas inactivas</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>

