package com.gamasoft.hps.sab.webservices.rest;
/**
 * 
 * @author wospino
 */
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
import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.GrupoSeleccionDto;
import com.gamasoft.hps.sab.dto.SeleccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

@Controller
@RequestMapping("/rest/grupoSeleccion")
public class GrupoSeleccionRestController {

	private ServiceManager serviceManager;
	
	@Autowired
	private UserRepository userRepository;
	
	 @Autowired
	    public GrupoSeleccionRestController(ServiceManager serviceManager) {
	        this.serviceManager = serviceManager;
	    }
	    
	 @RequestMapping(value = {"/"}, method = RequestMethod.GET)
	    public @ResponseBody List<GrupoSeleccionDto> getGrupoSeleccion(
	    		@RequestParam(value="mostrar", required=false, defaultValue="activos") String show,
	    		@RequestParam(value="idArticulo", required=false) Long idArticulo) throws ServiceException {
		 
	    if(idArticulo != null){
	    	return serviceManager.getGrupoSeleccionService().getGrupoSeleccionByArticulo(idArticulo);
	    } else{
	    	if("inactivos".equalsIgnoreCase(show)){
		 		return serviceManager.getGrupoSeleccionService().getGrupoSeleccionByCliente(getUserId(), false);
		 	}
		 	
		 	return serviceManager.getGrupoSeleccionService().getGrupoSeleccionByCliente(getUserId(), true);
	    }
	 }
	 
	 @RequestMapping(value = {"/{grupoSeleccionId}"}, method = RequestMethod.GET)
	    public @ResponseBody GrupoSeleccionDto getById(@PathVariable("grupoSeleccionId") long grupoSeleccionId, HttpServletResponse response) throws ServiceException {
	    	GrupoSeleccionDto grupoSeleccionDto = serviceManager.getGrupoSeleccionService().getById(grupoSeleccionId);
	    	
	    	return grupoSeleccionDto;
	    }
	 
	 @RequestMapping(value = {"/"}, method = RequestMethod.POST)
	    public @ResponseBody String createGrupoSeleccion(@RequestBody GrupoSeleccionDto grupoDto, HttpSession session, HttpServletResponse response) throws ServiceException{
		 grupoDto.setIdCliente(getUserId());
		 
		 return serviceManager.getGrupoSeleccionService().create(grupoDto); 
	    }
	 
	 @RequestMapping(value = {"/{grupoSeleccionId}"}, method = RequestMethod.PUT)
	    public @ResponseBody String modificarGrupoSeleccion(@PathVariable("grupoSeleccionId") long grupoSeleccionId, @RequestBody GrupoSeleccionDto grupoDto, 
	    		HttpSession session, HttpServletResponse response) {
		 
		 grupoDto.setId(grupoSeleccionId);
		 grupoDto.setIdCliente(getUserId());
		 
		 try {
			serviceManager.getGrupoSeleccionService().update(grupoDto);
		} catch (RepositoryException e) {
			return "No fue posible actualizar el Grupo de seleccion " + e.getMessage();
		}
		 return "OK";
	    }
	 
	 @RequestMapping(value= "/{grupoSelId}", method = RequestMethod.DELETE)
	    public @ResponseBody String inactivarGrupoSeleccion(@PathVariable("grupoSelId") long grupoSelId, HttpSession session, HttpServletResponse response)throws ServiceException, RepositoryException {
	    	serviceManager.getGrupoSeleccionService().inactivar(grupoSelId);
	    	response.setStatus(HttpStatus.OK.value());
	    	return "GrupoSeleccion inactivado satisfactoriamente";
	    }
	 
	 @RequestMapping(value= "/{grupoSelId}/activar", method = RequestMethod.POST)
	    public @ResponseBody String activarGrupoSeleccion(@PathVariable("grupoSelId") long grupoSelId, HttpSession session, HttpServletResponse response)throws ServiceException, RepositoryException {
	    	serviceManager.getGrupoSeleccionService().activar(grupoSelId);
	    	response.setStatus(HttpStatus.OK.value());
	    	return "GrupoSeleccion activado satisfactoriamente";
	    }
	 
	 @RequestMapping(value = {"/{grupoSelId}/selecciones/"}, method = RequestMethod.GET)
	    public @ResponseBody List<SeleccionDto> getSelecciones(@PathVariable("grupoSelId") long grupoSelId, 
	    		@RequestParam(value="show", required=false, defaultValue="active") String show) throws ServiceException {
		 	
		 	return serviceManager.getGrupoSeleccionService().getSeleccionesByGrupo(grupoSelId);
	    }
	 
	 @RequestMapping(value = {"/{grupoSelId}/selecciones/"}, method = RequestMethod.POST)
	    public @ResponseBody String createSeleccion(@PathVariable("grupoSelId") long grupoSelId,
	    		@RequestBody Set<SeleccionDto> seleccionesDto, HttpSession session, HttpServletResponse response) throws ServiceException{
		 return serviceManager.getGrupoSeleccionService().createSeleccion(grupoSelId, seleccionesDto); 
	    }
	 
	 public Long getUserId(){
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			User currentUser = userRepository.getUserByUsername(name);
			
			return currentUser.getId();
	    }
	
}
