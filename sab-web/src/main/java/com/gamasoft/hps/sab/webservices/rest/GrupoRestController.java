package com.gamasoft.hps.sab.webservices.rest;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.dto.GrupoDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

@Controller
@RequestMapping("/rest/grupo")
public class GrupoRestController {

	private ServiceManager serviceManager;
	@Autowired
	public GrupoRestController(ServiceManager serviceManager){
		this.serviceManager=serviceManager;
	
	}
	
	private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public @ResponseBody List<GrupoDto> getGrupos() throws ServiceException {
        return serviceManager.getGrupoService().getGrupos();
    }
		
	@RequestMapping(value = {"/inactivos"}, method = RequestMethod.GET)
    public @ResponseBody List<GrupoDto> getGruposInactivos() throws ServiceException {
        return serviceManager.getGrupoService().getGruposinactivos();
    }
	 @RequestMapping(value = {"/{grupoId}"}, method = RequestMethod.GET)
	    public @ResponseBody GrupoDto grupoId(@PathVariable("grupoId") long grupoId, HttpServletResponse response) throws ServiceException {
	       return serviceManager.getGrupoService().getById(grupoId);
	    	
	    }
	
	 
	 @RequestMapping(value = {"/grupos/{idCliente}"}, method = RequestMethod.GET)
	    public @ResponseBody List<GrupoDto> gruposIdcliente(@PathVariable("idCliente") long idCliente, HttpServletResponse response) throws ServiceException {
	       return serviceManager.getGrupoService().getGrupoIdCliente(idCliente);
	    	
	    }
	 
	
	 @RequestMapping(value = {"/"}, method = RequestMethod.POST)
	    public @ResponseBody String createGrupo(@RequestBody GrupoDto grupo, HttpSession session, HttpServletResponse response) throws ServiceException {
	     
		 try{  
	    	 response.setStatus(HttpStatus.OK.value());
	    	 return serviceManager.getGrupoService().add(grupo);}
	     catch(Exception e){
	    	 response.setStatus(HttpStatus.CONFLICT.value());
	    	 return "el grupo ya existe";
	     }
	    }
	
	 @RequestMapping(value = "/{grupoId}", method = RequestMethod.PUT)
	    public @ResponseBody String updateGrupo(@PathVariable("grupoId") long grupoId,@RequestBody GrupoDto grupo,HttpSession session, HttpServletResponse response) throws Exception {
		  	
		 return serviceManager.getGrupoService().update(grupo, grupoId);
	        
	        
	    }
	 
	 @RequestMapping(value= "/inactivar/{grupoId}", method = RequestMethod.DELETE)
	    public @ResponseBody String inactivarGrupo(@PathVariable("grupoId") long grupoId, HttpSession session, HttpServletResponse response)throws ServiceException {
		 response.setStatus(HttpStatus.OK.value());	
		 return serviceManager.getGrupoService().inactiveGrupo(grupoId);
	    }
	 
	 @RequestMapping(value= "/activar/{grupoId}", method = RequestMethod.PUT)
	    public @ResponseBody String activarGrupo(@PathVariable("grupoId") long grupoId, HttpSession session, HttpServletResponse response)throws ServiceException {
		 response.setStatus(HttpStatus.OK.value());	
		 return serviceManager.getGrupoService().activateGrupo(grupoId);
	    }
	 
	 @RequestMapping(value = "/grupocliente/{grupoId}", method = RequestMethod.GET)
	 public GrupoDto getByIdGrupo(@PathVariable("grupoId") long grupoId, HttpServletResponse response) throws ServiceException{
		 return serviceManager.getGrupoService().getGrupoCliente(grupoId);
	 }
	  
	 @RequestMapping(value= "/{grupoId}/agregarclientes", method = RequestMethod.POST)
	    public @ResponseBody String agregarClienteAGrupo(@PathVariable("grupoId") long grupoId,@RequestBody List<ClienteDto> clienteDto,HttpSession session, HttpServletResponse response) throws ServiceException{
	    String respuesta=serviceManager.getGrupoService().adicionaCliente(clienteDto,grupoId);
		if(respuesta.equals("este grupo no es modificable")){
			  response.setStatus(HttpStatus.NOT_MODIFIED.value());
		} 
	    return respuesta;
	    }
	 
	 	 
	 @RequestMapping(value="/{grupoId}/agregarcliente/{clienteId}", method =RequestMethod.POST)
	 public String agregarClienteGrupo1(@PathVariable("grupoId") long grupoId, @PathVariable("clienteId") long clienteId, HttpSession session, HttpServletResponse response) throws ServiceException{
		 	return serviceManager.getGrupoService().adicionaCliente(grupoId, clienteId);
			 
	 }
	 
	 @RequestMapping(value="/{grupoId}/quitarcliente/{clienteId}", method =RequestMethod.POST)
	 public void quitarClienteGrupo(@PathVariable("grupoId") long grupoId, @PathVariable("clienteId") long clienteId, HttpSession session, HttpServletResponse response) throws ServiceException{
		 	serviceManager.getGrupoService().quitarCliente(grupoId, clienteId);
	 }
	 
	 @RequestMapping(value= "/{grupoId}/eliminarclientes", method = RequestMethod.POST)
	    public void eliminarClientesDeGrupo(@PathVariable("grupoId") long grupoId,@RequestBody List<ClienteDto> clienteDto,HttpSession session, HttpServletResponse response) throws ServiceException{
	    	serviceManager.getGrupoService().eliminarClientes(clienteDto,grupoId);
	    }
	 
	 
	 @RequestMapping(value="/busqueda/{nombre}", method = RequestMethod.GET)
	    public @ResponseBody List<GrupoDto> buscarGrupoAnyWord(@PathVariable("nombre") String nombre, HttpSession session, HttpServletResponse response) throws ServiceException{
			return serviceManager.getGrupoService().getGrupoPorNombre(nombre, IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
			    }
	 
	 public Long IdUserRequestMapping(String tipoid){
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			User currentUser = userRepository.getUserByUsername(name);
			if(tipoid.equals("cliente")){
			return currentUser.getIdCliente();
				}	
				if(tipoid.equals("grupo")){
				return currentUser.getIdGrupo();
				}if(tipoid.equals("punto")){
						return 0L;
			}
				if(tipoid.equals("id")){
					return currentUser.getId();
				}
					return null;
	    }
	 
}
