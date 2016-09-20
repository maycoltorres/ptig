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
import com.gamasoft.hps.sab.dto.TransaccionDto;
import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

/**
*
* @author wospino
*/
@Controller
@RequestMapping("/rest/user")
public class UserRestController {

	private ServiceManager serviceManager;
	
	@Autowired
    public UserRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
	
	private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	 @RequestMapping(value = {"/"}, method = RequestMethod.GET)
	 public @ResponseBody List<UserDto> getUsers(HttpServletResponse response) throws Exception {
	     List<UserDto> udto =serviceManager.getUserservice().getUsers(IdUserRequestMapping("cliente"),IdUserRequestMapping("grupo"));
	     if(udto.isEmpty()){
	    	 response.setStatus(HttpStatus.NO_CONTENT.value());
	     }
	     else {
	    	 response.setStatus(HttpStatus.OK.value());
	    	 return udto;
	    	 
	     }
		return udto;
		
	    }
	
	 @RequestMapping(value = {"/inactivos"}, method = RequestMethod.GET)
	    public @ResponseBody List<UserDto> usuarioInactivos(HttpSession session, HttpServletResponse response) throws ServiceException{
	        return serviceManager.getUserservice().getUserInactivos(IdUserRequestMapping("cliente"),IdUserRequestMapping("grupo"));
	    }
	 
	 @RequestMapping(value= {"/{idUser}"}, method = RequestMethod.GET)
	 public @ResponseBody UserDto getuser(@PathVariable("idUser") long idUser, HttpServletResponse response) throws ServiceException{
	        return serviceManager.getUserservice().getuser(idUser);
	    }
	 
	 @RequestMapping(value = {"/"}, method = RequestMethod.POST)
	    public @ResponseBody String CreateUser(@RequestBody UserDto user, HttpSession session, HttpServletResponse response) throws ServiceException{
		
		String respuesta=serviceManager.getUserservice().addUser(user);
		if(respuesta=="Usuario ya Existe"){
			response.setStatus(HttpStatus.CONFLICT.value());   	
		}else{
			response.setStatus(HttpStatus.OK.value());  
		}
		
		return respuesta;
	}
	 
	 @RequestMapping(value = {"/{idUser}"}, method = RequestMethod.PUT)
	    public @ResponseBody String updateUser(@PathVariable("idUser") long idUser, 
	    		@RequestBody UserDto user, HttpServletResponse response) throws ServiceException{
		 response.setStatus(HttpStatus.OK.value());
		 return serviceManager.getUserservice().updateUser(idUser, user, null, null);
	    }
	 
	 @RequestMapping(value = {"/inactivar/{idUser}"}, method = RequestMethod.DELETE)
	    public @ResponseBody String inactivateUser(@PathVariable("idUser") long idUser, HttpServletResponse response) throws ServiceException{
		 response.setStatus(HttpStatus.OK.value());   
		 return serviceManager.getUserservice().inactivateUser(idUser,IdUserRequestMapping("cliente"),IdUserRequestMapping("grupo"));
	    }

	 @RequestMapping(value = {"/activar/{idUser}"}, method = RequestMethod.PUT)
	    public @ResponseBody String activateUser(@PathVariable("idUser") long idUser, HttpServletResponse response) throws ServiceException{
		 response.setStatus(HttpStatus.OK.value());
		 return serviceManager.getUserservice().activateUser(idUser,IdUserRequestMapping("cliente"),IdUserRequestMapping("grupo"));
	    }

	 
	 @RequestMapping(value = {"/userprofile/{idUser}"}, method = RequestMethod.GET)
	 public @ResponseBody List<TransaccionDto> userprofile(@PathVariable("idUser") long idUser, HttpServletResponse response) throws ServiceException{
	        return serviceManager.getUserservice().getUserProfile(idUser,IdUserRequestMapping("cliente"),IdUserRequestMapping("grupo"));
	    } 
	 
	 
	 
	 @RequestMapping(value = {"/detalles"}, method = RequestMethod.GET)
	 public @ResponseBody UserDto userDetails() throws ServiceException{
	        return serviceManager.getUserservice().DatallesUsuario(IdUserRequestMapping("id"));
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
			}if(tipoid.equals("id")){
				return currentUser.getId();
			}
				
					return null;
	    }
	 
}
