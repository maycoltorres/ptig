package com.gamasoft.hps.sab.webservices.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.service.ServiceManager;

/**
*
* @author wospino
*/
@Controller
@RequestMapping("/rest/marca")
public class MarcaRestController {


	private ServiceManager serviceManager;
	
	
	 @Autowired
	    public MarcaRestController(ServiceManager serviceManager) {
	        this.serviceManager = serviceManager;
	    }
	    
	 @RequestMapping(value = {"/"}, method = RequestMethod.GET)
	    public @ResponseBody List<MarcaDto> getMarcas() throws Exception {
	        return serviceManager.getMarcaService().getMarcas();
	    }
	   @RequestMapping(value = {"/"}, method = RequestMethod.POST)
	    public @ResponseBody String createMarca(@RequestBody MarcaDto marca,  HttpSession session, HttpServletResponse response) throws Exception {
	
		  String respuesta= serviceManager.getMarcaService().add(marca);
		  if(respuesta=="Marca ya existe"){
			  response.setStatus(HttpStatus.CONFLICT.value()); 
			  return respuesta;
		  }else{
		  response.setStatus(HttpStatus.OK.value());
		  return respuesta;}
	    }
	    
	 @RequestMapping(value = {"/inactivos"}, method = RequestMethod.GET)
	    public @ResponseBody List<MarcaDto> getMarcasInactivas() throws ServiceException {
	        return serviceManager.getMarcaService().getMarcasInactivas();    }
	 
	 @RequestMapping(value = {"/{marcaId}"}, method = RequestMethod.GET)
	    public @ResponseBody MarcaDto marcaId(@PathVariable("marcaId") long marcaId, HttpServletResponse response) throws ServiceException {
	     try{  
		 return serviceManager.getMarcaService().getById(marcaId);}
	     catch(Exception e){
	    	 return null;
	     }
	    }
	 
	 @RequestMapping(value = "/{marcaId}", method = RequestMethod.PUT)
	    public @ResponseBody String  updateMarca(@PathVariable("marcaId") long marcaId,@RequestBody MarcaDto marca,HttpSession session, HttpServletResponse response) throws Exception {
	    
			String respuesta=  serviceManager.getMarcaService().update(marca, marcaId);
	        if(respuesta =="Marca Actualizada"){
	        	response.setStatus(HttpStatus.OK.value());
	        	return respuesta;
	        }else{
	        	response.setStatus(HttpStatus.CONFLICT.value());	
	        	return respuesta;
	        }
			
	    }
	 
	 @RequestMapping(value= "/inactivar/{marcaId}", method = RequestMethod.DELETE)
	    public @ResponseBody String inactivarMarca(@PathVariable("marcaId") long marcaId, HttpSession session, HttpServletResponse response)throws ServiceException {
		 response.setStatus(HttpStatus.OK.value());	
		 return serviceManager.getMarcaService().inactiveMarca(marcaId);
	    }
	 	
	 @RequestMapping(value= "/activar/{marcaId}", method = RequestMethod.PUT)
	    public @ResponseBody String  activarMarca(@PathVariable("marcaId") long marcaId, HttpSession session, HttpServletResponse response)throws ServiceException {
		 response.setStatus(HttpStatus.OK.value());
		 return serviceManager.getMarcaService().activateMarca(marcaId);
	    }
	 
	 @RequestMapping(value = "/marcapunto/{marcaId}", method = RequestMethod.GET)
	 public MarcaDto getByIdMarca(@PathVariable("marcaId") long marcaId, HttpServletResponse response) throws ServiceException{
		 return serviceManager.getMarcaService().getById(marcaId);
	 }
	 
	 @RequestMapping(value= "/{puntoId}/agregarpuntos", method = RequestMethod.POST)
	    public @ResponseBody String agregarPuntoAMarca(@PathVariable("marcaId") long puntoId,@RequestBody List<MarcaDto> marcaDto,HttpSession session, HttpServletResponse response) throws ServiceException{
	    	return serviceManager.getMarcaService().adicionaMarca(marcaDto, puntoId); 
	    }
	 
	 @RequestMapping(value="/{marcaId}/agregarpunto/{puntoId}", method =RequestMethod.POST)
	 public String agregarPuntoMarca1(@PathVariable("marcaId") long marcaId, @PathVariable("puntoId") long puntoId, HttpSession session, HttpServletResponse response) throws ServiceException{
		 	return serviceManager.getMarcaService().adicionaPunto(marcaId, puntoId);	 
	 }
	 
	 @RequestMapping(value="/{marcaId}/quitarpunto/{puntoId}", method =RequestMethod.DELETE)
	 public void quitarPuntoMarca(@PathVariable("marcaId") long marcaId, @PathVariable("puntoId") long puntoId, HttpSession session, HttpServletResponse response) throws ServiceException{
		 	serviceManager.getMarcaService().quitarPunto(marcaId, puntoId);
	 }
	 
	 @RequestMapping(value= "/{marcaId}/actualizar", method = RequestMethod.POST)
	    public void eliminarPuntosDeMarca(@PathVariable("marcaId") long marcaId,@RequestBody List<PuntoDto> puntoDto,HttpSession session, HttpServletResponse response) throws ServiceException{
	    	serviceManager.getMarcaService().eliminarPuntos(puntoDto,marcaId);
	    }
	 
	 @RequestMapping(value="/busqueda/{nombre}", method = RequestMethod.GET)
	    public @ResponseBody List<MarcaDto> buscarMarcaAnyWord(@PathVariable("nombre") String nombre, HttpSession session, HttpServletResponse response) throws ServiceException{
			return serviceManager.getMarcaService().getMarcasProNombre(nombre);
			    }
	 
}
