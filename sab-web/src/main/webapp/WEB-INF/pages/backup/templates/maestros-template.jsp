<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script id="maestrosMenuView-Template" type="text/template">
<div class="tabbable" id="clientsMainView">
	<ul class="nav nav-tabs container-fluid" id="tabHeaders">
		<li class="active"><a href="#" data-toggle="tab">Maestros</a></li>
	</ul>
	<!-- Create Puntos Modal Form -->
	<div id="crearPuntoModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	  <h3 id="myModalLabel">Crear Nuevo Punto</h3>
	</div>
	<form>
	  <div class="modal-body">
		<table class="table">
  			<tr class="info">
				<td colspan="3"><h3 align="center">ADMINISTRAR PUNTO</h3></td>
			</tr>
  			<tr>
  				<th>Datos Punto</th>
  				<th>Ubicacion Punto</th>
  				<th>Otros</th>			
		 	</tr>					
           	<tr>
           	   <td>
                 	<label>Nombre<span class="required">*</span> </label>
		            	<input id="nombre" type="text" min="5" max="40" >
					<label>Direccion Punto<span class="required">*</span></label>
		               	<input id="direccion" type="number"text" max="999999999">	
					<label>Telefono 1 Punto<span class="required">*</span></label>
                 		<input id="telefono" type="number" min="0" max="999999999">
					<label>Extension 1 Punto<span class="required">*</span></label>
                 		<input id="extension" type="number" min="0" max="4">				  		  	
					<label>Telefono 2 Punto<span class="required">*</span></label>
                 		<input id="telefono2" type="number" min="0" max="999999999">
					<label>Extension 2 Punto<span class="required">*</span></label>
                 		<input id="extension2" type="number" min="0" max="4">
				</td>
			<td> 
                   	<label>Pais<span class="required">*</span></label>
		           		<select id="regimen_id" class="form-control" type="text">
                       		<option value="1">Colombia</option>
                       		<option value="2">Argentina</option>
                       		<option value="3">Ecuador</option>
                   	</select><br>
		                
			     	<label>Departamento<span class="required">*</span></label>
		           		<select id="regimen_id" class="form-control" type="text">
                       		<option value="1">Simplificado</option>
                        	<option value="2">Comun</option>
                        	<option value="3">Especial</option>
                   	</select><br>
		               		          
                   	<label>Municipio<span class="required">*</span></label>
		           		<select id="regimen_id" class="form-control" type="text">
                       		<option value="1">Simplificado</option>
                       		<option value="2">Comun</option>
                       		<option value="3">Especial</option>
                   	</select><br>      	
				</td>
   				<td>
					 <label>Marca<span class="required">*</span></label>
		           		<select id="regimen_id" class="form-control" type="text">
                       		<option value="1">Simplificado</option>
                       		<option value="2">Comun</option>
                       		<option value="3">Especial</option>
                    	</select><br>

					 <label>Tipo Negocio<span class="required">*</span></label>
		           		<select id="regimen_id" class="form-control" type="text">
                       		<option value="1">Restaurante</option>
                       		<option value="2">Bar</option>
                       		
                     	</select><br>

						 <label>Centro Costos<span class="required">*</span></label>
						 <input id="" type="number" min="0" max="9">
					</td>
						<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
							<div class="tab-pane active container-fluid" id="home">				
					        </div>
						</div>
					</td>	
				</tr>
	 		</table>
	  </div>
</form>

	<div class="modal-footer">
	  <button class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</button>
	  <button id="btnCrearPunto" class="btn btn-primary">Crear</button>
	</div>
	</div>
	
	<div class="tab-content container-fluid tab-dashboard-content" id="tabContent">
		<div class="tab-pane active container-fluid" id="home">
			<table class="table">
				<thead>
				  <tr>
					<th></th>
					<th>Crear</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				  </tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#crearPuntoModal"><i class="icon-plus"></i></button></td>
						<td>Modify</td>
						<td>Delete</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</script>
