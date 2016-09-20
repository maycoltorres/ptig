<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="mainMarcasView-Template" type="text/template">
<div class="tabbable" id="marcasMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">Marcas</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Marcas Activas <a class="mustHidden marca_crear" href="#crearmarca"><button  class="btn btn-primary" data-toggle="tooltip" data-placement="left" title="Crear nueva Marca"><i class="icon-plus"></i></button></a>
			                  		 <a class="mustHidden lista_marca_des" href="#marcasinactivas"><button   class="btn btn-small btn-info" data-toggle="tooltip" data-placement="left" title="Ver Marcas inactivas">Marcas Inactivas   <i class="icon-eye-close"></i></button></a>
		    </h1>			
		  <div class="container" id="MarcasListContainer">
		  </div>
		</div>
	</div>
</div>
</script>

<script id="marcasMenuView-template" type="text/template">
		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
		        <th>Nombre Marca</th>
                <th>Acciones</th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
		      if(marcas!==null && marcas.length>0){ _.each(marcas, function(item) { ?>
		        <tr>
		          <td><span id="marca_id"><?= item.get('nombre') ?></span></td>
		          <td>
		            <div>
					<button class="btn btn-small btn-primary btnLoad mustHidden marca_ver" type="button" id="btnCargarMarca" data-id="<?= item.id ?>"
                    >Ver datos</button>

 					<button class="btn btn-small btn-danger btnLoad mustHidden marca_desactivar" type="button" id="btneliminarMarca" data-id="<?= item.id ?>">Inactivar</button>
		            </div>
		          </td>
		        </tr>
		      <?
		       }); } 
                else { ?>
                <tr>
		          <td colspan="4">No hay Marcas registrados</td>
                </tr>
              <? } ?>
		    </tbody>
		  </table>
      <div id="confirmDelete"></div>
</script>

