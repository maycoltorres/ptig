/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.webservices.rest;

/**
 * 
 * @author wospino
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.CanalImpuestoDto;
import com.gamasoft.hps.sab.dto.ClaseArticuloDto;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.ConfiguracionClienteDto;
import com.gamasoft.hps.sab.dto.GrupoArticuloDto;
import com.gamasoft.hps.sab.dto.ImpuestoDto;
import com.gamasoft.hps.sab.dto.ListaPreciosDto;
import com.gamasoft.hps.sab.dto.RegimenDto;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;
import com.gamasoft.hps.sab.service.local.CustomUserDetailsService;
import com.gamasoft.hps.sab.webservices.dto.ErrorResponse;

@Controller
@RequestMapping("/rest/cliente")
public class ClientRestController {

	private ServiceManager serviceManager;
	CustomUserDetailsService Cu = new CustomUserDetailsService();

	@Autowired
	public ClientRestController(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public @ResponseBody List<ClienteDto> getClients() throws ServiceException {
		return serviceManager.getClienteService().getClients(
				IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public @ResponseBody String createClient(@RequestBody ClienteDto client,
			HttpSession session, HttpServletResponse response)
			throws ServiceException {
		if (serviceManager.getClienteService().GetByNit(client.getNit()) == "Continuar") {
			if (serviceManager.getClienteService()
					.getByName(client.getNombre()) != null) {
				return "El cliente ya existe en la base de datos";
			} else {
				//response.setStatus(HttpStatus.OK.value());
				return serviceManager.getClienteService().add(client);
			}
		} else {
			return "El Nit existe en la base de datos";
		}
	}

	@RequestMapping(value = { "/{clientId}" }, method = RequestMethod.GET)
	public @ResponseBody ClienteDto ClienteId(
			@PathVariable("clientId") long clienteId,
			HttpServletResponse response) throws ServiceException {
		ClienteDto cliente = serviceManager.getClienteService().getById(
				clienteId, IdUserRequestMapping("cliente"),
				IdUserRequestMapping("grupo"));
		if (cliente != null) {
			return cliente;
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/{clienteId}", method = RequestMethod.PUT)
	public @ResponseBody String updateCliente(
			@PathVariable("clienteId") long clienteId,
			@RequestBody ClienteDto cliente, HttpSession session,
			HttpServletResponse response) throws ServiceException {
		ClienteDto existingCliente = serviceManager.getClienteService()
				.getByName(cliente.getNombre());
		if (existingCliente != null && existingCliente.getId() != clienteId) {
			System.out.println("CLIENTE YA EXISTE");
			return "El cliente ya existe en la base de datos";
		} else {
			String respuesta = serviceManager.getClienteService().update(
					cliente, clienteId, IdUserRequestMapping("cliente"),
					IdUserRequestMapping("grupo"));
			if (respuesta == "Cliente Modificado") {
				response.setStatus(HttpStatus.OK.value());
				return respuesta;
			} else {
				response.setStatus(HttpStatus.CONFLICT.value());
				return respuesta;
			}
		}
	}

	@RequestMapping(value = "/inactivar/{clienteId}", method = RequestMethod.DELETE)
	public @ResponseBody String inactivarCliente(
			@PathVariable("clienteId") long clienteId, HttpSession session,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().inactiveCliente(clienteId,
				IdUserRequestMapping("cliente"));
	}

	@RequestMapping(value = "/activar/{clienteId}", method = RequestMethod.PUT)
	public @ResponseBody String activarCliente(
			@PathVariable("clienteId") long clienteId, HttpSession session,
			HttpServletResponse response) throws ServiceException {
		response.setStatus(HttpStatus.OK.value());
		return serviceManager.getClienteService().activateCliente(clienteId);
	}

	@RequestMapping(value = { "/inactivos" }, method = RequestMethod.GET)
	public @ResponseBody List<ClienteDto> getClientsInactivos()
			throws ServiceException {
		return serviceManager.getClienteService().getClientsInactivos(
				IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
	}

	@RequestMapping(value = "/busqueda/{nombre}", method = RequestMethod.GET)
	public @ResponseBody List<ClienteDto> buscarClienteAnyWord(
			@PathVariable("nombre") String nombre, HttpSession session,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().getClienteNombreAnyWord(
				nombre, IdUserRequestMapping("cliente"),
				IdUserRequestMapping("grupo"), IdUserRequestMapping("punto"));
	}

	@RequestMapping(value = { "/roles/{clienteId}" }, method = RequestMethod.GET)
	public @ResponseBody List<RolDto> getRolesCliente(
			@PathVariable("clienteId") long clienteId, HttpSession session,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().getRolesCliente(clienteId);
	}

	// CRUD for Impuestos
	@RequestMapping(value = { "/{clientId}/regimenes/" }, method = RequestMethod.GET)
	public @ResponseBody List<RegimenDto> getRegimenes(
			@PathVariable("clientId") long clienteId,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().getRegimenes(clienteId);
	}

	@RequestMapping(value = { "/{clientId}/regimenes/" }, method = RequestMethod.POST)
	public @ResponseBody String addRegimen(
			@PathVariable("clientId") long clienteId,
			@RequestBody List<RegimenDto> regimenes,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().adicionaRegimen(regimenes, clienteId);
	}
	
	@RequestMapping(value = { "/{clientId}/regimenes/{regimenId}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteRegimen(
			@PathVariable("clientId") long clienteId,
			@PathVariable("regimenId") long regimenId,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().eliminaRegimen(clienteId, regimenId);
	}

	// CRUD for CanalImpuesto
	@RequestMapping(value = { "/{clientId}/canalImpuesto/" }, method = RequestMethod.GET)
	public @ResponseBody List<CanalImpuestoDto> getCanalImpuesto(
			@PathVariable("clientId") long clienteId,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().getCanalImpuesto(clienteId);
	}

	@RequestMapping(value = { "/{clientId}/canalImpuesto/" }, method = RequestMethod.POST)
	public @ResponseBody String adicionarCanalImpuesto(
			@PathVariable("clientId") long clienteId,
			@RequestBody CanalImpuestoDto canalImpuesto,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().adicionarCanalImpuesto(
				clienteId, canalImpuesto);
	}

	@RequestMapping(value = { "/{clientId}/canalImpuesto/{canalImpuestoId}" }, method = RequestMethod.PUT)
	public @ResponseBody String actualizarCanalImpuesto(
			@PathVariable("clientId") long clienteId,
			@PathVariable("canalImpuestoId") long canalImpuestoId,
			@RequestBody CanalImpuestoDto canalImpuesto,
			HttpServletResponse response) throws ServiceException {
		canalImpuesto.setId(canalImpuestoId);
		return serviceManager.getClienteService().actualizarCanalImpuesto(
				canalImpuesto);
	}

	@RequestMapping(value = { "/{clientId}/canalImpuesto/{canalImpuestoId}" }, method = RequestMethod.DELETE)
	public @ResponseBody String deleteCic(
			@PathVariable("clientId") long clienteId,
			@PathVariable("canalImpuestoId") long canalImpuestoId,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().eliminarCanalImpuesto(
				canalImpuestoId);
	}

	// CRUD for ListasPrecio
	@RequestMapping(value = { "/{clientId}/listasPrecios" }, method = RequestMethod.GET)
	public @ResponseBody List<ListaPreciosDto> consultarListasPrecios(
			@PathVariable("clientId") long clienteId,
			@RequestParam(value="mostrar", required=false, defaultValue="activos") String mostrar,
			HttpServletResponse response) throws ServiceException {
		
		if(mostrar.equalsIgnoreCase("inactivos")){
			return serviceManager.getClienteService().getListasPreciosPorCliente(clienteId, false);
		}else{
			return serviceManager.getClienteService().getListasPreciosPorCliente(clienteId, true);
		}
	}
	
	@RequestMapping(value = { "/{clientId}/listasPrecios" }, method = RequestMethod.POST)
	public @ResponseBody ErrorResponse agregarListaPrecios(
			@PathVariable("clientId") long clienteId,
			@RequestBody ListaPreciosDto listaPrecios,
			HttpServletResponse response) throws ServiceException {
		String returnMessage = serviceManager.getClienteService().adicionarListaPrecios(clienteId, listaPrecios);
		ErrorResponse result = new ErrorResponse();
		result.setMessage(returnMessage);
		if(returnMessage.equalsIgnoreCase("OK")){
			result.setResponseCode(200);
		}else{
			result.setResponseCode(500);
		}
		
		return result;
	}
	
	@RequestMapping(value = {"/{clientId}/listasPrecios/activar/{listaPreciosId}"}, method = RequestMethod.PUT) 
    public @ResponseBody String activarListaPrecios(
    		@PathVariable("clientId") long clientId, 
    		@PathVariable("listaPreciosId") long listaPreciosId,
    		HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
		serviceManager.getClienteService().activarListaPrecios(listaPreciosId);
		
		return "OK";
    }
	
	@RequestMapping(value = "/{clientId}/listasPrecios/inactivar/{listaPreciosId}", method = RequestMethod.DELETE) 
    public @ResponseBody String inactivarGrupoEmpaque(
    		@PathVariable("clientId") long clientId, 
    		@PathVariable("listaPreciosId") long listaPreciosId,
    		HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
		serviceManager.getClienteService().inactivarListaPrecios(listaPreciosId);
		
		return "OK";
    }
	
	//CRUD for Clases y Grupos
	@RequestMapping(value = "/clase/", method = RequestMethod.GET)
	public @ResponseBody List<ClaseArticuloDto> consultarClasesPorCliente(
			@RequestParam(value="venta", required=false) Boolean venta,
			@RequestParam(value="inventario", required=false) Boolean inventario,
			@RequestParam(value="mostrar", required=false, defaultValue="activos") String mostrar,
			HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException {

		if(mostrar.equalsIgnoreCase("inactivos")){
			return serviceManager.getClienteService().getClaseArticuloByCliente(getUserId(), venta, inventario, false);
		}else{
			return serviceManager.getClienteService().getClaseArticuloByCliente(getUserId(), venta, inventario, true);
		}
	}
	
	@RequestMapping(value = "/clase/{claseId}", method = RequestMethod.GET)
	public @ResponseBody ClaseArticuloDto consultarClasePorId(
			@PathVariable("claseId") long claseId,
			HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException {

		return serviceManager.getClienteService().getClaseArticuloById(claseId);
	}
	
	@RequestMapping(value = "/clase/", method = RequestMethod.POST)
	public @ResponseBody ErrorResponse agregarClase(
			@RequestBody ClaseArticuloDto claseDto,
			HttpServletResponse response) throws ServiceException {
		
		claseDto.setIdCliente(getUserId());
		
		String responseMessage = serviceManager.getClienteService().agregarClaseArticulo(claseDto);
		ErrorResponse er = new ErrorResponse();
		er.setMessage(responseMessage);
		return er;
	}
	
	@RequestMapping(value = "/clase/{claseId}", method = RequestMethod.PUT)
	public @ResponseBody ErrorResponse actualizarClase(
			@RequestBody ClaseArticuloDto claseDto,
			HttpServletResponse response) throws ServiceException {
		
		claseDto.setIdCliente(getUserId());
		
		String responseMessage = serviceManager.getClienteService().actualizarClaseArticulo(claseDto);
		ErrorResponse er = new ErrorResponse();
		er.setMessage(responseMessage);
		return er;
	}
	
	@RequestMapping(value = {"/clase/activar/{claseId}"}, method = RequestMethod.PUT) 
    public @ResponseBody String activarClase(
    		@PathVariable("claseId") long claseId,
    		HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
		
		serviceManager.getClienteService().activarClaseArticulo(claseId, true);
		return "OK";
    }
	
	@RequestMapping(value = "/clase/inactivar/{claseId}", method = RequestMethod.DELETE) 
    public @ResponseBody String inactivarClase(
    		@PathVariable("claseId") long claseId, 
    		HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
		serviceManager.getClienteService().activarClaseArticulo(claseId, false);
		
		return "OK";
    }

	@RequestMapping(value = "/clase/{claseId}/grupos", method = RequestMethod.GET)
	public @ResponseBody List<GrupoArticuloDto> consultarGruposPorClase(@PathVariable("claseId") long claseId, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException {

		return serviceManager.getClienteService().getGrupoArticuloByClase(claseId);

	}

	
	//Auxiliary functions
	public Long IdUserRequestMapping(String tipoid) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);
		if (tipoid.equals("cliente")) {
			return currentUser.getIdCliente();
		}
		if (tipoid.equals("grupo")) {
			return currentUser.getIdGrupo();
		}
		if (tipoid.equals("punto")) {
			return 0L;
		}
		return null;
	}
	
	public Long getUserId(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);

		return currentUser.getId();
	}

	// CRUD for Canales
	@RequestMapping(value = { "/{clientId}/canales/" }, method = RequestMethod.GET)
	public @ResponseBody Set<CanalDto> getCanalesByCliente(
			@PathVariable("clientId") long clienteId,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().getCanalesPorCliente(
				clienteId);
	}

	@RequestMapping(value = { "/{clientId}/canales/" }, method = RequestMethod.POST)
	public @ResponseBody String definirCanales(
			@PathVariable("clientId") long clienteId,
			@RequestBody List<CanalDto> canales, HttpServletResponse response)
			throws ServiceException {
		return serviceManager.getClienteService().definirCanales(clienteId,
				canales);
	}

	@RequestMapping(value = { "/{clientId}/canales/" }, method = RequestMethod.PUT)
	public @ResponseBody String agregarCanales(
			@PathVariable("clientId") long clienteId,
			@RequestBody List<CanalDto> canales, HttpServletResponse response)
			throws ServiceException {
		return serviceManager.getClienteService().adicionarCanales(clienteId,
				canales);
	}

	@RequestMapping(value = { "/{clientId}/canales/{canalId}" }, method = RequestMethod.DELETE)
	public @ResponseBody String eliminarCanales(
			@PathVariable("clientId") long clienteId,
			@PathVariable("canalId") long canalId, HttpServletResponse response)
			throws ServiceException {
		return serviceManager.getClienteService().eliminarCanal(clienteId,
				canalId);
	}

	// CRUD for ConfiguracionCliente
	@RequestMapping(value = { "/{clientId}/configuracion/" }, method = RequestMethod.GET)
	public @ResponseBody ConfiguracionClienteDto getConfiguracionCliente(
			@PathVariable("clientId") long clienteId,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().getConfiguracionCliente(
				clienteId);
	}

	@RequestMapping(value = { "/{clientId}/configuracion/" }, method = RequestMethod.POST)
	public @ResponseBody String definirConfiguracionCliente(
			@PathVariable("clientId") long clienteId,
			@RequestBody ConfiguracionClienteDto configuracionDto,
			HttpServletResponse response) throws ServiceException {
		return serviceManager.getClienteService().definirConfiguracionCliente(
				clienteId, configuracionDto);
	}

}
