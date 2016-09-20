/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.webservices.rest;

import java.util.ArrayList;
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
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

/**
 *
 * @author wospino
 */
@Controller
@RequestMapping("/rest/punto")

public class PuntoRestController {
    private ServiceManager serviceManager;

    @Autowired
    public PuntoRestController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
    
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public @ResponseBody List<PuntoDto> getPuntos(@RequestParam(value="idCliente", required=true, defaultValue="0") Long idCliente) throws Exception {
        return serviceManager.getPuntoService().getPuntos(idCliente,IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"), IdUserRequestMapping("punto"));
    }
   

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public @ResponseBody String createPunto(@RequestBody PuntoDto punto, HttpSession session, HttpServletResponse response) throws Exception {
    	if(punto.getCliente_id() == null){
    		punto.setCliente_id(IdUserRequestMapping("cliente"));
    	}
    	
    	String respuesta= serviceManager.getPuntoService().add(punto,IdUserRequestMapping("cliente"));
     	if(respuesta!="este punto ya existe"){
     	response.setStatus(HttpStatus.OK.value());
     		return respuesta;}
     	response.setStatus(HttpStatus.CONFLICT.value());
     	return respuesta;
    	}
   
    @RequestMapping(value = {"/{puntoId}/marcas/"}, method = RequestMethod.GET)
    public @ResponseBody List<MarcaDto> getMarcas(@PathVariable("puntoId") long puntoId) throws Exception {
        return serviceManager.getPuntoService().getMarcasPorPunto(puntoId);
    }
    
    @RequestMapping(value= "/{puntoId}/marcas/", method = RequestMethod.POST)
    public @ResponseBody String agregarMarcasAPunto(@PathVariable("puntoId") long puntoId,@RequestBody List<MarcaDto> marcaDto,HttpSession session, HttpServletResponse response) throws ServiceException{
    	return serviceManager.getPuntoService().adicionaMarcas(marcaDto,puntoId);
    }
    
    @RequestMapping(value= "/{puntoId}/marcas/{marcaId}", method = RequestMethod.DELETE)
    public @ResponseBody String removerMarca(@PathVariable("puntoId") long puntoId,
    		@PathVariable("marcaId") long marcaId,
    		HttpSession session, HttpServletResponse response) throws ServiceException{
    	return serviceManager.getPuntoService().eliminarMarca(marcaId, puntoId);
    }
    
    @RequestMapping(value = {"/{puntoId}/bodegas/"}, method = RequestMethod.GET)
    public @ResponseBody List<BodegaDto> getBodegas(@PathVariable("puntoId") long puntoId) throws Exception {
        return serviceManager.getPuntoService().getBodegasPorPunto(puntoId);
    }
    
    @RequestMapping(value= "/{puntoId}/bodegas/", method = RequestMethod.POST)
    public @ResponseBody String agregarBodegaAPunto(@PathVariable("puntoId") long puntoId,@RequestBody BodegaDto bodega,
    		HttpSession session, HttpServletResponse response) throws ServiceException{
    	
    	List<BodegaDto> bodegas = new ArrayList<BodegaDto>();
    	bodegas.add(bodega);
    	String result = serviceManager.getPuntoService().adicionaBodegas(bodegas,puntoId);
    	return result;
    }
    
    @RequestMapping(value= "/{puntoId}/bodegas/{bodegaId}", method = RequestMethod.PUT)
    public @ResponseBody String actualizarBodega(@PathVariable("puntoId") long puntoId,@PathVariable("bodegaId") long bodegaId, 
    		@RequestBody BodegaDto bodegaDto,HttpSession session, HttpServletResponse response) throws ServiceException{
    	bodegaDto.setId(bodegaId);
    	return serviceManager.getPuntoService().actualizarBodega(bodegaDto);
    }
    
    @RequestMapping(value= "/{puntoId}/bodegas/{bodegaId}/activar", method = RequestMethod.POST)
    public @ResponseBody String activarBodega(@PathVariable("puntoId") long puntoId,@PathVariable("bodegaId") long bodegaId
    		,HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
    	return serviceManager.getPuntoService().activarBodega(bodegaId);
    }
    
    @RequestMapping(value= "/{puntoId}/bodegas/{bodegaId}/desactivar", method = RequestMethod.POST)
    public @ResponseBody String desactivarBodega(@PathVariable("puntoId") long puntoId,@PathVariable("bodegaId") long bodegaId
    		,HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
    	return serviceManager.getPuntoService().desactivarBodega(bodegaId);
    }
    
    @RequestMapping(value = "/{puntoId}", method = RequestMethod.GET)
    public @ResponseBody PuntoDto GetPuntoId(@PathVariable("puntoId") long puntoId, HttpServletResponse response) throws Exception {
        
    	PuntoDto p =serviceManager.getPuntoService().getById(puntoId);
        response.setStatus(HttpStatus.OK.value());
		return p;
    }
  
   @RequestMapping(value ="/cliente/{clienteId}", method = RequestMethod.GET)
    public @ResponseBody List<PuntoDto> getPuntoIdCliente(@PathVariable("clienteId") Long clienteId, HttpServletResponse response)throws Exception{
		List<PuntoDto> p=serviceManager.getPuntoService().getPuntos(clienteId,IdUserRequestMapping("cliente"),IdUserRequestMapping("grupo"),IdUserRequestMapping("punto"));
		response.setStatus(HttpStatus.OK.value());
		return p;
   	}
   

    @RequestMapping(value = "/{puntoId}", method = RequestMethod.PUT)
    public @ResponseBody  String updatePunto(@PathVariable("puntoId") long puntoId,@RequestBody PuntoDto punto,HttpSession session, HttpServletResponse response) throws Exception {
    	
    	String respuesta = serviceManager.getPuntoService().update(punto, puntoId);
    	if(respuesta!="Punto ya existe"){
         	response.setStatus(HttpStatus.OK.value());
         		return respuesta;}
         	response.setStatus(HttpStatus.CONFLICT.value());
         	return respuesta;
        }
    
    @RequestMapping(value= "/inactivar/{puntoId}", method = RequestMethod.DELETE)
    public @ResponseBody String inactivarPunto(@PathVariable("puntoId") long puntoId, HttpSession session, HttpServletResponse response)throws Exception {
    	try{
    	return serviceManager.getPuntoService().inactivePunto(puntoId);}
    	catch(Exception e){
    	return "No se ha inactivado";
    	}
		
    }
    
    @RequestMapping(value= "/activar/{puntoId}", method = RequestMethod.PUT)
    public @ResponseBody String  activarPunto(@PathVariable("puntoId") long puntoId, HttpSession session, HttpServletResponse response)throws Exception {
    	return serviceManager.getPuntoService().activatePunto(puntoId);
    }
    
    @RequestMapping(value = {"/inactivos"}, method = RequestMethod.GET)
    public @ResponseBody List<PuntoDto> getPuntosInactivos(@RequestParam(value="idCliente", required=true, defaultValue="0") Long idCliente) throws Exception {
        return serviceManager.getPuntoService().getPuntosInactivos(idCliente, IdUserRequestMapping("grupo"));
    }
    
    @RequestMapping(value="/busqueda/{nombre}", method = RequestMethod.GET)
    public @ResponseBody List<PuntoDto> buscarPuntoanyWord(@PathVariable("nombre") String nombre, HttpSession session, HttpServletResponse response) throws ServiceException{
		return serviceManager.getPuntoService().getPuntPorNombre(nombre, IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"),IdUserRequestMapping("id"));
		    }
    public Long IdUserRequestMapping(String tipoid){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);
		if(name != null){
			if(tipoid.equals("cliente")){
				return currentUser.getIdCliente();
			}	
			
			if(tipoid.equals("grupo")){
				return currentUser.getIdGrupo();
			}
			if(tipoid.equals("punto")){
				if(currentUser.getPuntos() != null && currentUser.getPuntos().size() > 0){
					return 0L;
				}
			}
		}
	
		return null;
}}
