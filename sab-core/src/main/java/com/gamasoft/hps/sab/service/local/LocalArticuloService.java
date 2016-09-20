package com.gamasoft.hps.sab.service.local;
/**
 * 
 * @author wospino
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamasoft.hps.sab.domain.Articulo;
import com.gamasoft.hps.sab.domain.Bodega;
import com.gamasoft.hps.sab.domain.Canal;
import com.gamasoft.hps.sab.domain.Conversion;
import com.gamasoft.hps.sab.domain.Ingrediente;
import com.gamasoft.hps.sab.domain.Inventario;
import com.gamasoft.hps.sab.domain.MaximosMinimos;
import com.gamasoft.hps.sab.domain.Punto;
import com.gamasoft.hps.sab.domain.Receta;
import com.gamasoft.hps.sab.domain.Tarifa;
import com.gamasoft.hps.sab.domain.Unidad;
import com.gamasoft.hps.sab.domain.UnidadVenta;
import com.gamasoft.hps.sab.domain.Venta;
import com.gamasoft.hps.sab.domain.VentaCanalImpuesto;
import com.gamasoft.hps.sab.domain.VentaGrupoSeleccion;
import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.CanalDto;
import com.gamasoft.hps.sab.dto.IngredienteDto;
import com.gamasoft.hps.sab.dto.InventarioDto;
import com.gamasoft.hps.sab.dto.MaximosMinimosDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.dto.RecetaDto;
import com.gamasoft.hps.sab.dto.UnidadDto;
import com.gamasoft.hps.sab.dto.UnidadVentaDto;
import com.gamasoft.hps.sab.dto.VentaCanalImpuestoDto;
import com.gamasoft.hps.sab.dto.VentaDto;
import com.gamasoft.hps.sab.dto.VentaGrupoSeleccionDto;
import com.gamasoft.hps.sab.exception.RepositoryException;
import com.gamasoft.hps.sab.repository.ArticuloRepository;
import com.gamasoft.hps.sab.repository.BodegaRepository;
import com.gamasoft.hps.sab.repository.CanalRepository;
import com.gamasoft.hps.sab.repository.ClaseArticuloRepository;
import com.gamasoft.hps.sab.repository.ConversionRepository;
import com.gamasoft.hps.sab.repository.GrupoArticuloRepository;
import com.gamasoft.hps.sab.repository.GrupoSeleccionRepository;
import com.gamasoft.hps.sab.repository.IngredienteRepository;
import com.gamasoft.hps.sab.repository.InventarioRepository;
import com.gamasoft.hps.sab.repository.ListaPreciosRepository;
import com.gamasoft.hps.sab.repository.PuntoRepository;
import com.gamasoft.hps.sab.repository.RecetaRepository;
import com.gamasoft.hps.sab.repository.RegimenRepository;
import com.gamasoft.hps.sab.repository.TarifaRepository;
import com.gamasoft.hps.sab.repository.UnidadRepository;
import com.gamasoft.hps.sab.repository.UnidadVentaRepository;
import com.gamasoft.hps.sab.repository.VentaRepository;
import com.gamasoft.hps.sab.service.ArticuloService;

@Service
public class LocalArticuloService implements ArticuloService {

	@Autowired
	private ArticuloRepository articuloRepository; 
	
	@Autowired
	private UnidadRepository unidadRepository; 
	
	@Autowired
	private ConversionRepository conversionRepository; 
	
	@Autowired
	private PuntoRepository puntoRepository; 
	
	@Autowired
	private ClaseArticuloRepository claseArticuloRepository; 
	
	@Autowired
	private GrupoArticuloRepository grupoArticuloRepository; 
	
	@Autowired
	private RecetaRepository recetaRepository;
	
	@Autowired
	private UnidadVentaRepository unidadVentaRepository;
	
	@Autowired
	private InventarioRepository inventarioRepository;
	
	@Autowired
	private CanalRepository canalRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private TarifaRepository tarifaRepository;
	
	@Autowired
	private BodegaRepository bodegaRepository;
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private ListaPreciosRepository listaPreciosRepository;
	
	@Autowired
	private GrupoSeleccionRepository grupoSeleccionRepository;
	
	
	@Override
	public List<ArticuloDto> getArticulos() {
		List<Articulo> articulo = articuloRepository.getAll();
		return procesaListaarticulos(articulo);
		
	}
	
	@Override
	public List<ArticuloDto> getArticulosInactivos() {
		List<Articulo> articulo = articuloRepository.getInactivos();
		return procesaListaarticulos(articulo);
		
	}
	
	public List<ArticuloDto> procesaListaarticulos(List<Articulo> articulos){
		List<ArticuloDto> artiDto;	
		if(articulos!=null && !articulos.isEmpty()){
			artiDto=new ArrayList<ArticuloDto>();
				for(Articulo c:articulos){
					artiDto.add(c.toDto());			
					}
				return artiDto;
		}
			return new ArrayList<ArticuloDto>();
	}


	@Override
	public String create(ArticuloDto artic) {
		Articulo a = articuloRepository.getByClienteYNombre(artic.getIdCliente(), artic.getNombre());
		if(a != null){
			return "Ya existe un articulo con el nombre " + artic.getNombre();
		}
		
		Articulo art = new Articulo(artic);
		this.articuloRepository.add(art);
		return Long.toString(art.getId());
	}
	
	@Override
	public ArticuloDto getById(Long id){
		return articuloRepository.getById(id).toFullDto();
	}
	
	
	@Override
	public void inactivar(Long id) {
		Articulo articulo = this.articuloRepository.getById(id);
		articulo.setActivo(false);
		try {
			this.articuloRepository.update(articulo);
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void activar(Long id) {
		Articulo articulo = this.articuloRepository.getById(id);
		articulo.setActivo(true);
		try {
			this.articuloRepository.update(articulo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ArticuloDto art) {
		Articulo a = articuloRepository.getByClienteYNombre(art.getIdCliente(), art.getNombre());
		if(a != null && !a.getId().equals(art.getId())){
			return;
		}
		
		Articulo articulo = this.articuloRepository.getById(art.getId());
		articulo.setCodigobarras(art.getCodigobarras());
		articulo.setDescripcion(art.getDescripcion());
		articulo.setNombre(art.getNombre());
		articulo.setNombreImpresion(art.getNombreImpresion());
		articulo.setInventario(art.isInventario());
		articulo.setVenta(art.isVenta());
		articulo.setSeleccion(art.isSeleccion());
		articulo.setEmpaque(art.isEmpaque());
		articulo.setUtilizaEmpaque(art.isUtilizaEmpaque());
		articulo.setCostoEstimado(art.getCostoEstimado());
		
		if(art.getPuntos() != null){
			Set<Punto> puntos = new HashSet<Punto>();
			for(PuntoDto pDto : art.getPuntos()){
				Punto p = puntoRepository.getById(pDto.getId());
				puntos.add(p);
			}
			articulo.setPuntos(puntos);
		}
		
		if(art.getInventarioEnt() != null){
			if(articulo.getInventarioEnt() == null){
				articulo.setInventarioEnt(new Inventario());
				articulo.getInventarioEnt().setArticulo(articulo);
			}
			procesarParamInventario(articulo.getInventarioEnt(), art.getInventarioEnt());
		}
		
		if(art.getVentaEnt()!=null){
			if(articulo.getVentaEnt() == null){
				Venta venta = new Venta();
				venta.setArticulo(articulo);
				articulo.setVentaEnt(venta);
			}
			procesarParamVenta(articulo.getVentaEnt(), art.getVentaEnt());
		}
		
		if(art.getRecetas() != null){
			for(RecetaDto recetaDto : art.getRecetas()){
				Receta receta = new Receta(recetaDto);
				receta.setArticulo(articulo);
				articulo.getRecetas().add(receta);
			}
		}
		
		try {
			articuloRepository.update(articulo);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void procesarParamInventario(Inventario inv, InventarioDto invDto){
		if(invDto.getIdUnidadKardex()!=null){
			inv.setUnidadKardex(unidadRepository.getById(
				invDto.getIdUnidadKardex()));
		}else{
			inv.setUnidadKardex(null);
		}
		
		if(invDto.getIdUnidadAlterna()!=null){
			inv.setUnidadAlterna(unidadRepository.getById(
				invDto.getIdUnidadAlterna()));
		}else{
			inv.setUnidadAlterna(null);
		}
		
		if(invDto.getIdGrupoArticulo() != null){
			inv.setGrupoArticulo(grupoArticuloRepository.getById(
				invDto.getIdGrupoArticulo()));
		}else{
			inv.setGrupoArticulo(null);
		}
		
		if(invDto.getMaximosMinimos() != null){
			Set<MaximosMinimos> mms;
			if(inv.getMaximosYMinimos() != null){
				mms = inv.getMaximosYMinimos();
			}else{
				mms = new HashSet<MaximosMinimos>();
			}
			
			//Eliminar los mms que ya no aplican
			Iterator<MaximosMinimos> it = mms.iterator();
			while(it.hasNext()){
				MaximosMinimos existingMM = it.next();
				int index = invDto.getMaximosMinimos().indexOf(existingMM.toDto());
				if(index >= 0){
					existingMM.setMaximo(invDto.getMaximosMinimos().get(index).getMaximo());
					existingMM.setMinimo(invDto.getMaximosMinimos().get(index).getMinimo());
					invDto.getMaximosMinimos().remove(index);
				}else{
					it.remove();
				}
			}
			
			//Crear los que faltan
			for(MaximosMinimosDto mmDto : invDto.getMaximosMinimos()){
				MaximosMinimos mm = new MaximosMinimos();
				mm.setInventario(inv);
				mm.setMaximo(mmDto.getMaximo());
				mm.setMinimo(mmDto.getMinimo());
				mm.setPunto(puntoRepository.getById(mmDto.getIdPunto()));
				mms.add(mm);
			}
			
			inv.setMaximosYMinimos(mms);
		}
		
		if(invDto.getUnidadesCompra() != null){
			Set<Unidad> unidades = new HashSet<Unidad>();
			for(UnidadDto uDto : invDto.getUnidadesCompra()){
				unidades.add(unidadRepository.getById(uDto.getId()));
			}
			
			inv.setUnidadesCompra(unidades);
		}
		
		if(invDto.getBodegas() != null){
			Set<Bodega> bodegas = new HashSet<Bodega>();
			for(BodegaDto bDto : invDto.getBodegas()){
				bodegas.add(bodegaRepository.getById(bDto.getId()));
			}
			
			inv.setBodegas(bodegas);
		}
	}
	
	private void procesarParamVenta(Venta venta, VentaDto ventaDto){
		if(ventaDto.getIdGrupoArticulo() != null){
			venta.setGrupoArticulo(grupoArticuloRepository.getById(ventaDto.getIdGrupoArticulo()));
		}
			
		if(ventaDto.getUnidadesVenta() != null){
			Set<UnidadVenta> unidades;
			
			if(venta.getUnidadesVenta() != null){
				unidades = venta.getUnidadesVenta();
			}else{
				unidades = new HashSet<UnidadVenta>();
			}
			
			//Eliminar los unidadesVenta que ya no aplican
			Iterator<UnidadVenta> it = unidades.iterator();
			while(it.hasNext()){
				UnidadVenta existingUnidad = it.next();
				int index = ventaDto.getUnidadesVenta().indexOf(existingUnidad.toDto());
				if(index >= 0){
					existingUnidad.setValor(ventaDto.getUnidadesVenta().get(index).getValor());
					ventaDto.getUnidadesVenta().remove(index);
				}else{
					it.remove();
				}
			}
			
			//Crear las que faltan
			for(UnidadVentaDto uDto : ventaDto.getUnidadesVenta()){
				UnidadVenta unidadVenta = new UnidadVenta();
				unidadVenta.setVenta(venta);
				unidadVenta.setUnidad(unidadRepository.getById(uDto.getIdUnidad()));
				unidadVenta.setListaPrecios(listaPreciosRepository.getById(uDto.getIdListaPrecios()));
				
				unidadVenta.setValor(uDto.getValor());
				unidadVenta.setPrincipal(uDto.getPrincipal());
				
				unidades.add(unidadVenta);
			}

			venta.setUnidadesVenta(unidades);
		}
		
		if(ventaDto.getGruposSeleccion() != null){
			Set<VentaGrupoSeleccion> gruposSeleccion;
			if(venta.getGruposSeleccion() != null){
				gruposSeleccion = venta.getGruposSeleccion();
			}else{
				gruposSeleccion = new HashSet<VentaGrupoSeleccion>();
			}
			
			//Eliminar los gruposSeleccion que ya no aplican
			Iterator<VentaGrupoSeleccion> it = gruposSeleccion.iterator();
			while(it.hasNext()){
				VentaGrupoSeleccion existingGrupo = it.next();
				int index = ventaDto.getGruposSeleccion().indexOf(existingGrupo.toDto());
				if(index >= 0){
					existingGrupo.setCantidad(ventaDto.getGruposSeleccion().get(index).getCantidad());
					ventaDto.getGruposSeleccion().remove(index);
				}else{
					it.remove();
				}
			}
			
			//Crear los que faltan
			for(VentaGrupoSeleccionDto grupoDto : ventaDto.getGruposSeleccion()){
				VentaGrupoSeleccion grupo = new VentaGrupoSeleccion();
				grupo.setVenta(venta);
				grupo.setCantidad(grupoDto.getCantidad());
				grupo.setGrupoSeleccion(grupoSeleccionRepository.getById(grupoDto.getIdGrupoSeleccion()));
				gruposSeleccion.add(grupo);
			}
			
			venta.setGruposSeleccion(gruposSeleccion);
		}
		
		if(ventaDto.getCanalesImpuestos() != null){
			Set<VentaCanalImpuesto> canales;
			if(venta.getCanalesImpuesto() !=null){
				canales = venta.getCanalesImpuesto();
			}else{
				canales = new HashSet<VentaCanalImpuesto>();
			}
			
			//Eliminar los canalesImpuestos que ya no aplican
			Iterator<VentaCanalImpuesto> it = canales.iterator();
			while(it.hasNext()){
				VentaCanalImpuesto existingCanal = it.next();
				int index = ventaDto.getCanalesImpuestos().indexOf(existingCanal.toDto());
				if(index >= 0){
					existingCanal.setTarifa(tarifaRepository.getById(ventaDto.getCanalesImpuestos().get(index).getIdTarifa()));
					
					ventaDto.getCanalesImpuestos().remove(index);
				}else{
					it.remove();
				}
			}
			
			//Crear los que faltan
			for(VentaCanalImpuestoDto canalDto : ventaDto.getCanalesImpuestos()){
				VentaCanalImpuesto canal = new VentaCanalImpuesto();
				canal.setVenta(venta);
				canal.setCanal(canalRepository.getById(canalDto.getIdCanal()));
				canal.setTarifa(tarifaRepository.getById(canalDto.getIdTarifa()));
				
				canales.add(canal);
			}
			
			venta.setCanalesImpuesto(canales);
		}
	}
	
	
	@Override
	public List<ArticuloDto> getArticulosPorClienteYSeleccion(Long idCliente,
			Boolean seleccion) {
		List<Articulo> articulo = articuloRepository.getByClienteYSeleccion(idCliente, seleccion);
		return procesaListaarticulos(articulo);
	}

	@Override
	public List<BodegaDto> getAvailableBodegasByArticulo(Long idArticulo) {
		List<BodegaDto> result = new ArrayList<BodegaDto>();
		List<Bodega> bodegas = articuloRepository.getByArticulo(idArticulo, true);
		
		for(Bodega b : bodegas){
			BodegaDto dto = b.toDto();
			result.add(dto);
		}
		
		return result;
	}

	@Override
	public List<UnidadDto> getUnidadesCompraByArticulo(Long idArticulo) {
		List<UnidadDto> result = new ArrayList<UnidadDto>();
		Set<Unidad> unidades = articuloRepository.getUnidadesCompraByArticulo(idArticulo);
		
		for(Unidad u : unidades){
			result.add(u.toDto());
		}
		
		return result;
	}

	@Override
	public String agregarUnidadesCompra(Long idArticulo, List<UnidadDto> unidades) throws RepositoryException {
		Articulo articulo = articuloRepository.getById(idArticulo);
		for(UnidadDto u : unidades){
			Unidad unidad; 
			if (u.getId() == null){
				unidad = new Unidad(u);
				unidadRepository.add(unidad);
			} else {
				unidad = unidadRepository.getById(u.getId());
			}
			//Add conversion
			Conversion conversion = new Conversion();
			conversion.setUnidadOrigen(unidad);
			conversion.setUnidadDestino(articulo.getInventarioEnt().getUnidadKardex());
			conversion.setArticulo(articulo);
			conversion.setFactor(u.getFactor());
			conversionRepository.add(conversion);
			
			articulo.getInventarioEnt().getUnidadesCompra().add(unidad);
		}
		articuloRepository.update(articulo);
		
		return "OK";
	}

	@Override
	public List<RecetaDto> getRecetasByArticulo(Long idArticulo, String mostrar) {
		ArticuloDto a = getById(idArticulo);
		List<RecetaDto> recetas = new ArrayList<RecetaDto>();
		for(RecetaDto r : a.getRecetas()){
			if(mostrar.equalsIgnoreCase("activos") && r.getActivo()){
				recetas.add(r);
			}else if (mostrar.equalsIgnoreCase("inactivos") && !r.getActivo()){
				recetas.add(r);
			}
		}
		
		return recetas;
	}

	@Override
	public String agregarRecetas(Long idArticulo, List<RecetaDto> recetas){
		Articulo articulo = articuloRepository.getById(idArticulo);
		for(RecetaDto r : recetas){
			Receta receta = new Receta(r);
			receta.setActivo(true);
			Unidad unidad = unidadRepository.getById(r.getIdUnidad());
			receta.setUnidad(unidad);
			receta.setArticulo(articulo);
			
			if(r.getPuntos() != null){
				Set<Punto> puntos = new HashSet<Punto>();
				for(PuntoDto pDto : r.getPuntos()){
					puntos.add(puntoRepository.getById(pDto.getId()));
				}
				receta.setPuntos(puntos);
			}
			
			if(r.getCanales() != null){
				Set<Canal> canales = new HashSet<Canal>();
				for(CanalDto cDto : r.getCanales()){
					canales.add(canalRepository.getById(cDto.getId()));
				}
				receta.setCanales(canales);
			}
			
			if(r.getIngredientes() != null){
				Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
				for(IngredienteDto iDto : r.getIngredientes()){
					Ingrediente ing = new Ingrediente();
					ing.setCantidad(iDto.getCantidad());
					ing.setArticulo(articuloRepository.getById(iDto.getIdArticulo()));
					ing.setUnidad(unidadRepository.getById(iDto.getIdUnidad()));
					ing.setReceta(receta);
					
					ingredientes.add(ing);
				}
				receta.setIngredientes(ingredientes);
			}
			
			recetaRepository.add(receta);
		}
		return "OK";
	}

	@Override
	public List<UnidadVentaDto> getUnidadesVentaByArticulo(Long idArticulo) {
		ArticuloDto a = getById(idArticulo);
		List<UnidadVentaDto> unidadesDto = new ArrayList<UnidadVentaDto>();
		List<UnidadVenta> unidades = unidadVentaRepository.getByArticulo(idArticulo);
		
		for(UnidadVenta uv : unidades){
			UnidadVentaDto uDto = uv.toDto();
			unidadesDto.add(uDto);
		}
		
		return unidadesDto;
	}

	@Override
	public String agregarUnidadesVenta(Long idArticulo,
			List<UnidadVentaDto> unidades) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String actualizarReceta(RecetaDto receta) throws RepositoryException {
		Receta r = recetaRepository.getById(receta.getId());
		r.setCantidad(receta.getCantidad());
		r.setNombre(receta.getNombre());
		r.setPreparacion(receta.getPreparacion());
		
		recetaRepository.update(r);
		
		return "OK";
	}

	@Override
	public String activarReceta(Long recetaId, Boolean activo)
			throws RepositoryException {
		Receta r = recetaRepository.getById(recetaId);
		r.setActivo(activo);
		recetaRepository.update(r);
		
		return "OK";
	}

	@Override
	public List<ArticuloDto> getArticulosByParams(Long idCliente,
			Boolean seleccion, Boolean empaque, Boolean inventario, Boolean venta,
			Boolean receta, Boolean unidadAlternaKardex, 
			Long idUnidadKardex, String mostrar) {
		List<ArticuloDto> articulosDto = new ArrayList<ArticuloDto>();
		List<Articulo> articulos = articuloRepository.getArticulosByParams(idCliente, seleccion, empaque, inventario, venta, receta, 
				unidadAlternaKardex, idUnidadKardex, mostrar);
		
		for(Articulo a : articulos){
			articulosDto.add(a.toDto());
		}
		
		return articulosDto;
	}

	@Override
	public InventarioDto getInventarioByArticulo(Long idArticulo) {
		ArticuloDto a = getById(idArticulo);
		if (a.getInventarioEnt() != null){
			return a.getInventarioEnt();
		}
		
		return null;
	}

	@Override
	public String crearInventario(Long idArticulo, InventarioDto inv) {
		ArticuloDto a = getById(idArticulo);
		if(a.getInventarioEnt() != null){
			return "El inventario para el articulo ya ha sido configurado";
		}
		
		Inventario i = new Inventario();
		i.setArticulo(articuloRepository.getById(idArticulo));
		i.setAliasUnidad(inv.getAliasUnidad());
		
		procesarParamInventario(i, inv);
		
		inventarioRepository.add(i);
		
		return "OK";
	}

	@Override
	public Set<VentaCanalImpuestoDto> getCanalesImpuesto(Long idArticulo) {
		Set<VentaCanalImpuesto> canalesImpuestos = articuloRepository.getCanalImpuestoByArticulo(idArticulo);
		Set<VentaCanalImpuestoDto> canalesImpuestosDto = new HashSet<VentaCanalImpuestoDto>();
		
		for(VentaCanalImpuesto c : canalesImpuestos){
			canalesImpuestosDto.add(c.toDto());
		}
		
		return canalesImpuestosDto;
	}

	@Override
	public String definirCanalesImpuesto(Long idArticulo,
			Set<VentaCanalImpuestoDto> canalesImpuestos) {
		Articulo a = articuloRepository.getById(idArticulo);
		Set<VentaCanalImpuesto> canalesImp = new HashSet<VentaCanalImpuesto>();
		
		for(VentaCanalImpuestoDto c : canalesImpuestos){
			VentaCanalImpuesto ventaCanalImpuesto = new VentaCanalImpuesto(c);
			ventaCanalImpuesto.setVenta(a.getVentaEnt());
			
			Canal canal = canalRepository.getById(c.getIdCanal());
			if(canal != null){
				ventaCanalImpuesto.setCanal(canal);
			}
			
			Tarifa tarifa = tarifaRepository.getById(c.getIdTarifa());
			if(tarifa != null){
				ventaCanalImpuesto.setTarifa(tarifa);
			}
			
			canalesImp.add(ventaCanalImpuesto);
		}
		
		articuloRepository.definirCanalImpuesto(idArticulo, canalesImp);
		
		return "OK";
	}

	@Override
	public String adicionarCanalesImpuesto(Long idArticulo,
			Set<VentaCanalImpuestoDto> canalesImpuestos) {
		Articulo a = articuloRepository.getById(idArticulo);
		Set<VentaCanalImpuesto> canalesImp = new HashSet<VentaCanalImpuesto>();
		
		for(VentaCanalImpuestoDto c : canalesImpuestos){
			VentaCanalImpuesto ventaCanalImpuesto = new VentaCanalImpuesto(c);
			ventaCanalImpuesto.setVenta(a.getVentaEnt());
			
			Canal canal = canalRepository.getById(c.getIdCanal());
			if(canal != null){
				ventaCanalImpuesto.setCanal(canal);
			}
			
			Tarifa tarifa = tarifaRepository.getById(c.getIdTarifa());
			if(tarifa != null){
				ventaCanalImpuesto.setTarifa(tarifa);
			}
			
			canalesImp.add(ventaCanalImpuesto);
		}
		
		articuloRepository.agregarCanalImpuesto(idArticulo, canalesImp);
		return "OK";
	}

	@Override
	public String eliminarCanalImpuesto(Long idArticulo, Long idCanalImpuesto) {
		return articuloRepository.eliminarCanalImpuesto(idArticulo, idCanalImpuesto);
	}

	@Override
	public String actualizarInventario(Long idArticulo, InventarioDto inv) {
		Articulo a = articuloRepository.getById(idArticulo);
		if(a.getInventarioEnt() == null){
			return "El inventario para el articulo no ha sido configurado";
		}
		
		a.getInventarioEnt().setAliasUnidad(inv.getAliasUnidad());
		procesarParamInventario(a.getInventarioEnt(), inv);
		
		try {
			inventarioRepository.update(a.getInventarioEnt());
		} catch (RepositoryException e) {
			e.printStackTrace();
			return "Ocurrio un error al actualizar el inventario";
		}
		
		return "OK";
	}

	@Override
	public VentaDto getVentaByArticulo(Long idArticulo) {
		ArticuloDto a = getById(idArticulo);
		if (a.getVentaEnt() != null){
			return a.getVentaEnt();
		}
		
		return null;
	}

	@Override
	public String crearVenta(Long idArticulo, VentaDto ventaDto) {
		ArticuloDto a = getById(idArticulo);
		if(a.getVentaEnt() != null){
			return "La configuracion de venta para el articulo ya ha sido creada";
		}
		
		Venta venta = new Venta();
		venta.setArticulo(articuloRepository.getById(idArticulo));
		
		procesarParamVenta(venta, ventaDto);
		
		ventaRepository.add(venta);
		
		return "OK";
	}

	@Override
	public String actualizarVenta(Long idArticulo, VentaDto ventaDto) {
		Articulo a = articuloRepository.getById(idArticulo);
		if(a.getVentaEnt() == null){
			return "La configuracion de venta no ha sido creada";
		}
		
		procesarParamVenta(a.getVentaEnt(), ventaDto);
		
		try {
			ventaRepository.update(a.getVentaEnt());
		} catch (RepositoryException e) {
			e.printStackTrace();
			return "Ocurrio un error al actualizar la venta";
		}
		
		return "OK";
	}

	@Override
	public RecetaDto getRecetaById(Long idReceta) {
		Receta receta = recetaRepository.getById(idReceta);
		if(receta != null){
			return receta.toFullDto();
		}
		
		return null;
	}

}	
