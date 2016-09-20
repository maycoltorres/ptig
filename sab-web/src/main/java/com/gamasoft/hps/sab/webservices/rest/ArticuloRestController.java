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
import com.gamasoft.hps.sab.dto.ConfiguracionArticulosDto;
import com.gamasoft.hps.sab.dto.GrupoEmpaqueDto;
import com.gamasoft.hps.sab.dto.InventarioDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.dto.RecetaDto;
import com.gamasoft.hps.sab.dto.UnidadDto;
import com.gamasoft.hps.sab.dto.UnidadVentaDto;
import com.gamasoft.hps.sab.dto.VentaCanalImpuestoDto;
import com.gamasoft.hps.sab.dto.VentaDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.repository.UserRepository;
import com.gamasoft.hps.sab.service.ServiceManager;

@Controller
@RequestMapping("/rest/articulo")
public class ArticuloRestController {

	private ServiceManager serviceManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public ArticuloRestController(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public @ResponseBody List<ArticuloDto> getArticulos(
			@RequestParam(value="seleccion", required=false) Boolean seleccion,
			@RequestParam(value="empaque", required=false) Boolean empaque,
			@RequestParam(value="inventario", required=false) Boolean inventario,
			@RequestParam(value="receta", required=false) Boolean receta,
			@RequestParam(value="venta", required=false) Boolean venta,
			@RequestParam(value="unidadAlternaKardex", required=false) Boolean unidadAlternaKardex,
			@RequestParam(value="idUnidadKardex", required=false) Long idUnidadKardex,
			@RequestParam(value="mostrar", required=false) String mostrar) throws ServiceException {

		return serviceManager.getArticuloservice().getArticulosByParams(getUserId(), seleccion, empaque, inventario, venta, receta, 
				unidadAlternaKardex, idUnidadKardex, mostrar);

	}

	@RequestMapping(value = {"/inactivos"}, method = RequestMethod.GET)
	public @ResponseBody List<ArticuloDto> getArticulosInactivos() throws ServiceException{
		//return serviceManager.getClienteService().getClientsInactivos(IdUserRequestMapping("cliente"), IdUserRequestMapping("grupo"));
		return serviceManager.getArticuloservice().getArticulosInactivos();
	}

	@RequestMapping(value = {"/{articuloId}"}, method = RequestMethod.GET)
	public @ResponseBody ArticuloDto getById(@PathVariable("articuloId") long articuloId, HttpServletResponse response) throws ServiceException {
		ArticuloDto articulo = serviceManager.getArticuloservice().getById(articuloId);

		return articulo;
	}

	@RequestMapping(value = {"/"}, method = RequestMethod.POST)
	public @ResponseBody String createArticulo(@RequestBody ArticuloDto artic, HttpSession session, HttpServletResponse response) throws ServiceException{
		artic.setIdCliente(getUserId());

		return serviceManager.getArticuloservice().create(artic); 
	}

	@RequestMapping(value= "/{articuloId}/inactivar", method = RequestMethod.POST)
	public @ResponseBody String inactivarArticulo(@PathVariable("articuloId") long articuloId, HttpSession session, HttpServletResponse response)throws ServiceException {
		serviceManager.getArticuloservice().inactivar(articuloId);
		response.setStatus(HttpStatus.OK.value());
		return "Articulo inactivado satisfactoriamente";
	}

	@RequestMapping(value= "/{articuloId}/activar", method = RequestMethod.POST)
	public @ResponseBody String activarArticulo(@PathVariable("articuloId") long articuloId, HttpSession session, HttpServletResponse response)throws ServiceException {
		serviceManager.getArticuloservice().activar(articuloId);
		response.setStatus(HttpStatus.OK.value());
		return "Articulo activado satisfactoriamente";
	}

	@RequestMapping(value = "/{articuloId}", method = RequestMethod.PUT)
	public @ResponseBody String updateArticulo(@RequestBody ArticuloDto articulo, HttpSession session, HttpServletResponse response) throws ServiceException {
		serviceManager.getArticuloservice().update(articulo);

		return Long.toString(articulo.getId());
	}

	@RequestMapping(value = "/configuracion", method = RequestMethod.GET)
	public @ResponseBody ConfiguracionArticulosDto consultarConfiguracion(HttpSession session, HttpServletResponse response) throws ServiceException {
		ConfiguracionArticulosDto conf = serviceManager.getConfiguracionArticulosService().getByCliente(getUserId());

		return conf;
	}

	@RequestMapping(value = "/configuracion", method = RequestMethod.POST)
	public @ResponseBody Long crearConfiguracion(@RequestBody ConfiguracionArticulosDto config, HttpSession session, HttpServletResponse response) throws ServiceException {
		config.setIdCliente(getIdCliente());

		return serviceManager.getConfiguracionArticulosService().crearConfiguracion(config);
	}

	@RequestMapping(value = "/configuracion", method = RequestMethod.PUT)
	public @ResponseBody Long actualizarConfiguracion(@RequestBody ConfiguracionArticulosDto config, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException {
		config.setIdCliente(getUserId());

		return serviceManager.getConfiguracionArticulosService().actualizarConfiguracion(config);
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

	@RequestMapping(value = {"/{articuloId}/bodegas"}, method = RequestMethod.GET)
	public @ResponseBody List<BodegaDto> getBodegasByArticulo(@PathVariable("articuloId") long articuloId,
			@RequestParam(value="mostrar", required=false, defaultValue="activas") Boolean showAll) throws ServiceException {

		return serviceManager.getArticuloservice().getAvailableBodegasByArticulo(articuloId);
	}
	
	@RequestMapping(value = {"/{articuloId}/puntos"}, method = RequestMethod.GET)
	public @ResponseBody List<PuntoDto> getPuntosByArticulo(@PathVariable("articuloId") long articuloId ) throws ServiceException {

		return serviceManager.getPuntoService().getPuntosByArticulo(articuloId);
	}

	@RequestMapping(value = {"/{articuloId}/inventario/unidadesCompra"}, method = RequestMethod.GET)
	public @ResponseBody List<UnidadDto> getUnidadesCompraByArticulo(@PathVariable("articuloId") long articuloId) throws ServiceException {
		return serviceManager.getArticuloservice().getUnidadesCompraByArticulo(articuloId);
	}

	@RequestMapping(value = {"/{articuloId}/inventario/unidadesCompra"}, method = RequestMethod.POST)
	public @ResponseBody String agregarUnidadesCompra(@PathVariable("articuloId") long articuloId,
			@RequestBody List<UnidadDto> unidades) throws ServiceException, RepositoryException {
		return serviceManager.getArticuloservice().agregarUnidadesCompra(articuloId, unidades);
	}

	@RequestMapping(value = {"/{articuloId}/recetas"}, method = RequestMethod.GET)
	public @ResponseBody List<RecetaDto> getRecetasByArticulo(@PathVariable("articuloId") long articuloId,
			@RequestParam(value="mostrar", required=false, defaultValue="activos") String mostrar) throws ServiceException {

		return serviceManager.getArticuloservice().getRecetasByArticulo(articuloId, mostrar);
	}
	
	@RequestMapping(value = {"/{articuloId}/recetas/{recetaId}"}, method = RequestMethod.GET)
	public @ResponseBody RecetaDto getRecetaById(@PathVariable("articuloId") long articuloId,
			@PathVariable("recetaId") long recetaId, HttpServletResponse response) throws ServiceException {
		
		return serviceManager.getArticuloservice().getRecetaById(recetaId);
	}

	@RequestMapping(value= "/{articuloId}/recetas", method = RequestMethod.POST)
	public @ResponseBody String agregarReceta(@PathVariable("articuloId") long articuloId,
			@RequestBody List<RecetaDto> recetas, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{

		return serviceManager.getArticuloservice().agregarRecetas(articuloId, recetas);
	}

	@RequestMapping(value= "/{articuloId}/recetas/{recetaId}", method = RequestMethod.PUT)
	public @ResponseBody String actualizarReceta(@PathVariable("articuloId") long articuloId,
			@PathVariable("recetaId") long recetaId, @RequestBody RecetaDto receta, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{

		receta.setId(recetaId);
		return serviceManager.getArticuloservice().actualizarReceta(receta);
	}

	@RequestMapping(value= "/{articuloId}/recetas/{recetaId}/activar", method = RequestMethod.POST)
	public @ResponseBody String activarReceta(@PathVariable("articuloId") long articuloId,
			@PathVariable("recetaId") long recetaId, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{

		return serviceManager.getArticuloservice().activarReceta(recetaId, true);
	}

	@RequestMapping(value= "/{articuloId}/recetas/{recetaId}/inactivar", method = RequestMethod.POST)
	public @ResponseBody String inactivarReceta(@PathVariable("articuloId") long articuloId,
			@PathVariable("recetaId") long recetaId, HttpSession session, HttpServletResponse response) throws ServiceException, RepositoryException{

		return serviceManager.getArticuloservice().activarReceta(recetaId, false);
	}


	@RequestMapping(value = {"/{articuloId}/venta/unidades"}, method = RequestMethod.GET)
	public @ResponseBody List<UnidadVentaDto> getUnidadesVentaByArticulo(@PathVariable("articuloId") long articuloId) throws ServiceException {

		return serviceManager.getArticuloservice().getUnidadesVentaByArticulo(articuloId);
	}

	@RequestMapping(value = {"/{articuloId}/inventario"}, method = RequestMethod.GET)
	public @ResponseBody InventarioDto getInventarioByArticulo(@PathVariable("articuloId") long articuloId) throws ServiceException {

		return serviceManager.getArticuloservice().getInventarioByArticulo(articuloId);
	}

	@RequestMapping(value = {"/{articuloId}/inventario"}, method = RequestMethod.POST)
	public @ResponseBody String  crearInventario(@PathVariable("articuloId") long articuloId,
			@RequestBody InventarioDto inventarioDto) throws ServiceException {

		return serviceManager.getArticuloservice().crearInventario(articuloId, inventarioDto);
	}
	
	@RequestMapping(value = {"/{articuloId}/inventario/"}, method = RequestMethod.PUT)
	public @ResponseBody String  actualizarInventario(@PathVariable("articuloId") long articuloId,
			@RequestBody InventarioDto inventarioDto) throws ServiceException {

		return serviceManager.getArticuloservice().actualizarInventario(articuloId, inventarioDto);
	}
	
	@RequestMapping(value = {"/{articuloId}/venta"}, method = RequestMethod.GET)
	public @ResponseBody VentaDto getVentaByArticulo(@PathVariable("articuloId") long articuloId) throws ServiceException {

		return serviceManager.getArticuloservice().getVentaByArticulo(articuloId);
	}

	@RequestMapping(value = {"/{articuloId}/venta"}, method = RequestMethod.POST)
	public @ResponseBody String  crearVenta(@PathVariable("articuloId") long articuloId,
			@RequestBody VentaDto ventaDto) throws ServiceException {

		return serviceManager.getArticuloservice().crearVenta(articuloId, ventaDto);
	}
	
	@RequestMapping(value = {"/{articuloId}/venta/"}, method = RequestMethod.PUT)
	public @ResponseBody String  actualizarVenta(@PathVariable("articuloId") long articuloId,
			@RequestBody VentaDto ventaDto) throws ServiceException {

		return serviceManager.getArticuloservice().actualizarVenta(articuloId, ventaDto);
	}

	//CRUD CanalImpuesto
	
	@RequestMapping(value = {"/{articuloId}/venta/canalImpuesto"}, method = RequestMethod.GET)
	public @ResponseBody Set<VentaCanalImpuestoDto> getCanalImpuestoByArticulo(@PathVariable("articuloId") long articuloId) throws ServiceException {

		return serviceManager.getArticuloservice().getCanalesImpuesto(articuloId);
	}

	@RequestMapping(value = {"/{articuloId}/venta/canalImpuesto"}, method = RequestMethod.POST)
	public @ResponseBody String definirCanalImpuestoByArticulo(@PathVariable("articuloId") long articuloId, 
			@RequestBody Set<VentaCanalImpuestoDto> canalesImpuestos, HttpSession session, HttpServletResponse response) throws ServiceException {

		return serviceManager.getArticuloservice().definirCanalesImpuesto(articuloId, canalesImpuestos);
	}
	
	@RequestMapping(value = {"/{articuloId}/venta/canalImpuesto"}, method = RequestMethod.PUT)
	public @ResponseBody String adicionarCanalImpuestoByArticulo(@PathVariable("articuloId") long articuloId, 
			@RequestBody Set<VentaCanalImpuestoDto> canalesImpuestos, HttpSession session, HttpServletResponse response) throws ServiceException {

		return serviceManager.getArticuloservice().adicionarCanalesImpuesto(articuloId, canalesImpuestos);
	}
	
	@RequestMapping(value = {"/{articuloId}/venta/canalImpuesto/{canalImpuestoId}"}, method = RequestMethod.DELETE)
	public @ResponseBody String adicionarCanalImpuestoByArticulo(@PathVariable("articuloId") long articuloId, 
			@PathVariable("canalImpuestoId") long canalImpuestoId, HttpSession session, HttpServletResponse response) throws ServiceException {

		return serviceManager.getArticuloservice().eliminarCanalImpuesto(articuloId, canalImpuestoId);
	}
	
	@RequestMapping(value = {"/{articuloId}/grupoEmpaque"}, method = RequestMethod.GET)
	public @ResponseBody List<GrupoEmpaqueDto> getGrupoEmpaqueByArticulo(@PathVariable("articuloId") long articuloId,
			@RequestParam(value="mostrar", required=false, defaultValue="activos") String mostrar) throws ServiceException {
		if(mostrar.equalsIgnoreCase("inactivos")){
			return serviceManager.getGrupoEmpaqueService().getGrupoEmpaqueByArticulo(articuloId, false);
		}
		return serviceManager.getGrupoEmpaqueService().getGrupoEmpaqueByArticulo(articuloId, true);
	}
	
	@RequestMapping(value = {"/{articuloId}/grupoEmpaque"}, method = RequestMethod.POST)
    public @ResponseBody String createGrupoEmpaque(@PathVariable("articuloId") long articuloId, @RequestBody GrupoEmpaqueDto grupoDto, 
    		HttpSession session, HttpServletResponse response) throws ServiceException {
		grupoDto.setIdArticulo(articuloId);
		return serviceManager.getGrupoEmpaqueService().create(grupoDto);
    }
}
