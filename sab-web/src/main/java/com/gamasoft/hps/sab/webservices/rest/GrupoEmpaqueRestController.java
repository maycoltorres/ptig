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
import com.gamasoft.hps.sab.dto.EmpaqueDto;
import com.gamasoft.hps.sab.dto.GrupoEmpaqueDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

@Controller
@RequestMapping("/rest/grupoEmpaque")
public class GrupoEmpaqueRestController {

	private ServiceManager serviceManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public GrupoEmpaqueRestController(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}"}, method = RequestMethod.PUT) 
    public @ResponseBody String updateGrupoEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, 
    		@RequestBody GrupoEmpaqueDto grupoDto, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException {
		grupoDto.setId(grupoEmpaqueId);
	 	return serviceManager.getGrupoEmpaqueService().actualizar(grupoDto);
    }
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/activar"}, method = RequestMethod.POST) 
    public @ResponseBody String activarGrupoEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, 
    		HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
		serviceManager.getGrupoEmpaqueService().activar(grupoEmpaqueId);
		
		return "OK";
    }
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/inactivar"}, method = RequestMethod.POST) 
    public @ResponseBody String inactivarGrupoEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, 
    		HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{
		serviceManager.getGrupoEmpaqueService().inactivar(grupoEmpaqueId);
		
		return "OK";
    }
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/empaques"}, method = RequestMethod.GET)
	public @ResponseBody List<EmpaqueDto> getEmpaques(
			@PathVariable("grupoEmpaqueId") long grupoEmpaqueId,
			@RequestParam(value="mostrar", required=false, defaultValue="activos") String mostrar) throws ServiceException {
		if(mostrar.equalsIgnoreCase("inactivos")){
			return serviceManager.getGrupoEmpaqueService().getEmpaquesByGrupo(grupoEmpaqueId, false);
		}else{
			return serviceManager.getGrupoEmpaqueService().getEmpaquesByGrupo(grupoEmpaqueId, true);
		}
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/empaques"}, method = RequestMethod.POST)
	public @ResponseBody String createEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId,
			@RequestBody EmpaqueDto empaqueDto, HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().createEmpaque(grupoEmpaqueId, empaqueDto);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/empaques/{empaqueId}"}, method = RequestMethod.PUT)
	public @ResponseBody String actualizarEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, @PathVariable("empaqueId") long empaqueId,
			@RequestBody EmpaqueDto empaqueDto, HttpSession session, HttpServletResponse response) throws ServiceException {
		empaqueDto.setId(empaqueId);
		return serviceManager.getGrupoEmpaqueService().updateEmpaque(empaqueDto);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/empaques/{empaqueId}"}, method = RequestMethod.DELETE)
	public @ResponseBody String eliminarEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, @PathVariable("empaqueId") long empaqueId, 
			HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().eliminarEmpaque(empaqueId);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/empaques/{empaqueId}/activar"}, method = RequestMethod.POST)
	public @ResponseBody String activarEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, @PathVariable("empaqueId") long empaqueId,
			HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().activarEmpaque(empaqueId);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/empaques/{empaqueId}/inactivar"}, method = RequestMethod.POST)
	public @ResponseBody String inactivarEmpaque(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, @PathVariable("empaqueId") long empaqueId,
			HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().inactivarEmpaque(empaqueId);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/canales"}, method = RequestMethod.GET)
	public @ResponseBody Set<CanalDto> getCanales(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().getCanalesByGrupo(grupoEmpaqueId);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/canales"}, method = RequestMethod.POST)
	public @ResponseBody String definirCanales(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId,
			@RequestBody Set<CanalDto> canalesDto, HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().definirCanales(grupoEmpaqueId, canalesDto);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/canales"}, method = RequestMethod.PUT)
	public @ResponseBody String adicionarCanales(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId,
			@RequestBody Set<CanalDto> canalesDto, HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().adicionarCanales(grupoEmpaqueId, canalesDto);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/canales/{canalId}"}, method = RequestMethod.DELETE)
	public @ResponseBody String eliminarCanales(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, 
			@PathVariable("canalId") long canalId) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().eliminarCanal(grupoEmpaqueId, canalId);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/puntos"}, method = RequestMethod.GET)
	public @ResponseBody Set<PuntoDto> getPuntos(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().getPuntosByGrupo(grupoEmpaqueId);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/puntos"}, method = RequestMethod.POST)
	public @ResponseBody String definirPuntos(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId,
			@RequestBody Set<PuntoDto> puntosDto, HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().definirPuntos(grupoEmpaqueId, puntosDto);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/puntos"}, method = RequestMethod.PUT)
	public @ResponseBody String adicionarPuntos(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId,
			@RequestBody Set<PuntoDto> puntosDto, HttpSession session, HttpServletResponse response) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().adicionarPuntos(grupoEmpaqueId, puntosDto);
	}
	
	@RequestMapping(value = {"/{grupoEmpaqueId}/puntos/{puntoId}"}, method = RequestMethod.DELETE)
	public @ResponseBody String eliminarPuntos(@PathVariable("grupoEmpaqueId") long grupoEmpaqueId, 
			@PathVariable("puntoId") long puntoId) throws ServiceException {
		return serviceManager.getGrupoEmpaqueService().eliminarPunto(grupoEmpaqueId, puntoId);
	}
	

	public Long getUserId(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userRepository.getUserByUsername(name);

		return currentUser.getId();
	}
}
