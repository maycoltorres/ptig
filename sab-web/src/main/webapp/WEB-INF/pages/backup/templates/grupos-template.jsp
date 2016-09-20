<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="mainGruposView-Template" type="text/template">
<div class="tabbable" id="gruposMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">Grupos</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Grupos Activos <a href="#creargrupo" class="mustHidden grupo_crear"><button  class="btn btn-primary" data-toggle="tooltip" data-placement="left" title="Crear nuevo grupo"><i class="icon-plus"></i></button></a>
			                  		 <a href="#gruposinactivos" class="mustHidden lista_grupo_des"><button   class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver grupos inactivos">Grupos Inactivos   <i class="icon-eye-close"></i></button></a>
		    </h1>			
		  <div class="container" id="GruposListContainer">
		  </div>
		</div>
	</div>
</div>
</script>

<script id="gruposMenuView-template" type="text/template">

		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
		        <th>Nombre Grupo</th>
                <th>Acciones</th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(grupos!==null && grupos.length>0){ _.each(grupos, function(item) { ?>
		        <tr>
		          <td><?= item.get('nombre') ?></td>
		          <td>
		            <div>

					<button type="button" id="btnCargarGrupo" data-id="<?= item.id ?>" 
                    class="btn btn-small btn-primary btnLoad mustHidden grupo_ver">Ver datos</button>

 					<button type="button" id="btneliminarGrupo" data-id="<?= item.id ?>" 
                    class="btn btn-small btn-danger btnLoad mustHidden grupo_desactivar">Inactivar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay grupos registrados</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>

