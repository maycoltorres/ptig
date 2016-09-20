<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="gruposinactivosView-Template" type="text/template">
<div class="tabbable" id="gruposinactivosMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">Grupos</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Grupos Inactivos <button  id="btnGruposActivos"  class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver grupos activos">Grupos Activos   <i class="icon-eye-open"></i></button>
		    </h1>			
		  <div class="container" id="GruposinactivosListContainer">
		  </div>
		</div>
	</div>
</div>
</script>

<script id="gruposinactivosMenuView-template" type="text/template">
		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
		 		<th>Nombre Grupo</th>
                <th>				
			  </th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(gruposinactivos !==null  && gruposinactivos.length>0 ){ _.each(gruposinactivos, function(item) { ?>
		        <tr>
		          <td><?= item.get('nombre') ?></td>
		          <td>
		            <div>
 					<button type="button" id="btnactivargrupo" data-id="<?= item.id ?>" 
                    class="btn btn-small btn-warning btnLoad mustHidden grupo_activar" data-toggle="tooltip" data-placement="left" title="Activar grupo inactivo">Activar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay grupos inactivos</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>

