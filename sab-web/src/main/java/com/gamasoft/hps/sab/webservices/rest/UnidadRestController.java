/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.webservices.rest;
/**
 * 
 * @author wospino
 */
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.gamasoft.hps.sab.dto.ConversionDto;
import com.gamasoft.hps.sab.dto.UnidadDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;
import com.gamasoft.hps.sab.service.local.CustomUserDetailsService;

@Controller
@RequestMapping("/rest/unidad")

public class UnidadRestController  {
	@Autowired
	private ServiceManager serviceManager;
	
	@Autowired
	private UserRepository userRepository;
	
	CustomUserDetailsService Cu = new CustomUserDetailsService();
    
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
       
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public @ResponseBody List<UnidadDto> getUnidades(
    		@RequestParam(value="mostrar", required=false, defaultValue="estandar") String mostrar,
    		@RequestParam(value="tipo", required=false) String tipo) throws ServiceException {
    	
        	return serviceManager.getUnidadService().getUnidadesByCriteria(getUserId(), tipo, mostrar);
    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public @ResponseBody Long crearUnidad(@RequestBody UnidadDto config, HttpSession session, HttpServletResponse response) throws ServiceException {
	 	config.setIdCliente(getUserId());
	 	
	 	return serviceManager.getUnidadService().crearUnidad(config);
    }
    
    @RequestMapping(value = {"/{idUnidad}/conversiones"}, method = RequestMethod.GET)
    public @ResponseBody List<ConversionDto> getConversiones(@PathVariable("idUnidad") Long idUnidad) throws ServiceException {
        return serviceManager.getUnidadService().getAllByUnidadOrigen(idUnidad);
    }
    
    @RequestMapping(value = {"/{idUnidad}/conversiones/"}, method = RequestMethod.POST)
    public @ResponseBody Long adicionarConversion(@PathVariable("idUnidad") Long idUnidad,
    		@RequestBody ConversionDto conversion) throws ServiceException {
        return serviceManager.getUnidadService().adicionarConversion(idUnidad, conversion);
    }
    
    @RequestMapping(value = {"/{idUnidad}/conversiones/"}, method = RequestMethod.PUT)
    public @ResponseBody String actualizarConversion(@PathVariable("idUnidad") Long idUnidad,
    		@RequestBody ConversionDto conversion) throws ServiceException {
        return serviceManager.getUnidadService().actualizarConversion(idUnidad, conversion);
    }
    
    @RequestMapping(value = {"/conversiones"}, method = RequestMethod.GET)
    public @ResponseBody List<ConversionDto> getAllConversiones(
    		@RequestParam(value="idArticulo", required=false) Long idArticulo) throws ServiceException {
        return serviceManager.getUnidadService().getAllByClienteYArticulo(getUserId(), idArticulo);
    }
    
  
    public Long getUserId(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);
		
		return currentUser.getId();
    }
}
