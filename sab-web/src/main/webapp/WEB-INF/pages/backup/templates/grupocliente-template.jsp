<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="mainGrupoClienteView-Template" type="text/template">
<div class="tabbable" id="clientesMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#home" data-toggle="tab">Grupos</a></li>
	</ul>
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<h1>Asignar clientes</h1>
		  <div class="container" id="GrupoclienteListContainer">

		  </div>
		</div>
	</div>
</div>
</script>

<script id="grupoclienteMenuView-template" type="text/template">

        <h2 class="notice btn-inverse gap10">Aqui podra relacionar los clientes activos con el grupo creado.</h2>
		<table class="table pres-crollable table-hover data_table">
		    <thead>
		      <tr>
				<th></th>
		        <th>Nombre Cliente</th>
				<th><p align="center">Selecionar Cliente</p></th>
				<th></th>
				<th>
		      </tr>
		    </thead>
		    <tbody>
		      <?
			  counter=0;
		      if(clientes!==null && clientes.length>0){_.each(clientes, function(item) {
				counter=counter+1; ?>
		        <tr>
				  <td></td>
		          <td><?= item.get('nombre') ?></td>
				  <td>
		            <div>
						<p align="center">
                            <input id="clienteCheck<?=counter?>" type="checkbox"  value="<?=item.id?>" >
                         </p>
 					</div>
		          </td>
				  <td></td>
				  <td>
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
        <p align="right">
            <button  id="btnGuardarGrupo"  class="btn  btn-success mustHidden grupo_modificar" data-toggle="tooltip" data-placement="left" title="Guardar lista de clientes">Guardar<i class="icon-ok"></i></button>
            <button  id="btnclienteCancelar"  class="btn  btn-default" data-toggle="tooltip" data-placement="left" title="Salir sin guardar lista">Cancelar<i class="icon-remove"></i></button>
        </p>
      <div id="confirmDelete"></div>
</script>

