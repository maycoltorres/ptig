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
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.GrupoSeleccionDto;
import com.gamasoft.hps.sab.dto.SeleccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

@Controller
@RequestMapping("/rest/bodegas")
public class BodegaRestController {

	private ServiceManager serviceManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public BodegaRestController(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	    
	 @RequestMapping(value = {"/"}, method = RequestMethod.GET)
	    public @ResponseBody List<BodegaDto> getBodegas(
	    		@RequestParam(value="idArticulo", required=false) Long idArticulo,
	    		@RequestParam(value="mostrar", required=false, defaultValue="activos") String mostrar) throws ServiceException {
		 	if(idArticulo == null){
		 		return serviceManager.getBodegaService().getBodegasByCliente(getIdCliente(), mostrar);
		 	}else{
		 		return serviceManager.getBodegaService().getBodegasByArticulo(idArticulo, mostrar);
		 	}
		 }
	 	 
	 public Long getUserId(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);
		
		return currentUser.getId();
	 }
	 
	 public Long getIdCliente(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);

		return currentUser.getIdCliente();
	}
}
