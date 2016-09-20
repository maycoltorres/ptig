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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.dto.RolDto;
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;
/**
*
* @author wospino
*/
@Controller
@RequestMapping("/rest/rol")
public class RolRestController {
 
		private ServiceManager serviceManager;
		
		@Autowired
	    public RolRestController(ServiceManager serviceManager) {
	        this.serviceManager = serviceManager;
	    }
	
		private UserRepository userRepository;
	    @Autowired
	    public void setUserRepository(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
		
		@RequestMapping(value = {"/"}, method = RequestMethod.POST)
	    public @ResponseBody String createRol(@RequestBody RolDto rol, HttpSession session, HttpServletResponse response) throws ServiceException{
			
			return serviceManager.getRolservice().add(rol);
	    
		}
			
		@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	    public @ResponseBody List<RolDto> getRoles() throws ServiceException {
	        return serviceManager.getRolservice().getRoles();
	    }
		
		
		@RequestMapping(value = {"/cliente/{idCliente}"}, method = RequestMethod.GET)
	    public @ResponseBody List<RolDto> getRolesByIdCliente(@PathVariable("idCliente") long rolId, HttpServletResponse response){
	        return serviceManager.getRolservice().getRolesByIdCliente(rolId);
	    }
		
		@RequestMapping(value = {"/{rolId}"}, method = RequestMethod.GET)
		    public @ResponseBody RolDto getRolById(@PathVariable("rolId") long rolId, HttpServletResponse response) throws ServiceException {
		        RolDto rol=serviceManager.getRolservice().getById(rolId, IdUserRequestMapping("grupo"));
		    	if(rol!=null){
		        return rol;}else{return null;}
		    }
		
		 @RequestMapping(value = "/{rolId}", method = RequestMethod.PUT)
		    public  @ResponseBody String updateRol(@PathVariable("rolId") long rolId,@RequestBody RolDto rol,HttpSession session, HttpServletResponse response) throws ServiceException {
			 response.setStatus(HttpStatus.OK.value());  	
		    	return serviceManager.getRolservice().modificar(rol,rolId, IdUserRequestMapping("grupo"));  
		    }
		 @RequestMapping(value= "/inactivar/{rolId}", method = RequestMethod.DELETE)
		    public @ResponseBody String inactivarRol(@PathVariable("rolId") long rolId, HttpSession session, HttpServletResponse response)throws ServiceException {
		    	return serviceManager.getRolservice().inactivar(rolId, IdUserRequestMapping("grupo"));
		    }
		
		 @RequestMapping(value= "/activar/{rolId}", method = RequestMethod.PUT)
		    public @ResponseBody String activarRol(@PathVariable("rolId") long rolId, HttpSession session, HttpServletResponse response)throws ServiceException {
		    	return serviceManager.getRolservice().activar(rolId);
		    }
		 @RequestMapping(value= "/inactivos", method = RequestMethod.GET)
		    public  @ResponseBody List<RolDto> Rolesinactivos()throws ServiceException {
		    	return serviceManager.getRolservice().inactivos();
		    }
		 
		 @RequestMapping(value="/busqueda/{nombre}", method = RequestMethod.GET)
		    public @ResponseBody List<RolDto> buscarRolAnyWord(@PathVariable("nombre") String nombre, HttpSession session, HttpServletResponse response) throws ServiceException{
				return serviceManager.getRolservice().getRolPorNombre(nombre, IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
				    }
		 

		 @RequestMapping(value= "/addtransac", method = RequestMethod.POST)
		    public @ResponseBody String agregartransaccionesRol(@RequestParam(value="idRol", required=true, defaultValue="0")Long idRol,@RequestBody List<TransaccionDto> transac, HttpServletResponse response) throws ServiceException{
			// System.out.println("rolId "+idRol + " clienteId "+idCliente);
			 return serviceManager.getRolservice().adicionaTrasaccionRol(idRol, transac);
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
