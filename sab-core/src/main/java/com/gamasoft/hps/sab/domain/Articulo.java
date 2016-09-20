package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.dto.RecetaDto;

@Entity
@Table(name = "articulo")
public class Articulo extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nombre", nullable = true, length = 60)
	private String nombre;
	
	@Column(name = "nombreimpresion", nullable = true, length = 50)
	private String nombreImpresion;

	@Column(name = "id_cliente", nullable = true, length = 11)
	private Long idCliente;

	@Column(name = "codigobarras", nullable = true, length = 20)
	private String codigobarras;

	@Column(name = "descripcion", nullable = true, length = 1000)
	private String descripcion;

	@Column(name = "venta", nullable = true, length = 1)
	private boolean venta;

	@Column(name = "inventario", nullable = true, length = 1)
	private boolean inventario;

	@Column(name = "receta", nullable = true, length = 1)
	private boolean receta;

	@Column(name = "seleccion", nullable = true, length = 1)
	private boolean seleccion;
	
	@Column(name = "empaque", nullable = true, length = 1)
	private boolean empaque;
	
	@Column(name = "utilizaempaque", nullable = true, length = 1)
	private boolean utilizaEmpaque;

	@Column(name="activo", nullable=true, length=1)
	private boolean activo;
	
	@Column(name = "costoestimado", nullable = true)
	private Double costoEstimado;

	
	@OneToOne(mappedBy = "articulo")
    private Inventario inventarioEnt;
	
	@OneToOne(mappedBy = "articulo")
    private Venta ventaEnt;
	
	@OneToMany(mappedBy = "articulo")
    private Set<Receta> recetas;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="articulo_punto",joinColumns={@JoinColumn(name="id_articulo")},inverseJoinColumns={@JoinColumn(name="id_punto")})
	private Set<Punto> puntos;
	
	public Set<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(Set<Receta> recetas) {
		this.recetas = recetas;
	}

	public Venta getVentaEnt() {
		return ventaEnt;
	}

	public void setVentaEnt(Venta ventaEnt) {
		this.ventaEnt = ventaEnt;
	}

	public Inventario getInventarioEnt() {
		return inventarioEnt;
	}

	public void setInventarioEnt(Inventario inventarioEnt) {
		this.inventarioEnt = inventarioEnt;
	}
	
	public Set<Punto> getPuntos() {
		return puntos;
	}

	public void setPuntos(Set<Punto> puntos) {
		this.puntos = puntos;
	}
	
	public Articulo() {

	}

	public Articulo(ArticuloDto dto) {

		this.nombre = dto.getNombre();
		this.nombreImpresion = dto.getNombreImpresion();
		this.idCliente = dto.getIdCliente();
		this.codigobarras = dto.getCodigobarras();
		this.descripcion = dto.getDescripcion();
		this.venta = dto.isVenta();
		this.inventario = dto.isInventario();
		this.receta = dto.isReceta();
		this.seleccion = dto.isSeleccion();
		this.activo = dto.isActivo();
		this.costoEstimado = dto.getCostoEstimado();
	}

	public ArticuloDto toDto() {

		ArticuloDto dto = new ArticuloDto();
		dto.setId(this.getId());
		dto.setCodigobarras(this.getCodigobarras());
		dto.setNombre(this.nombre);
		dto.setNombreImpresion(this.nombreImpresion);
		dto.setIdCliente(this.idCliente);
		dto.setDescripcion(this.descripcion);
		dto.setInventario(this.isInventario());
		dto.setVenta(this.isVenta());
		dto.setReceta(this.isReceta());
		dto.setSeleccion(this.seleccion);
		dto.setEmpaque(this.empaque);
		dto.setUtilizaEmpaque(this.utilizaEmpaque);
		dto.setActivo(this.isActivo());
		dto.setCostoEstimado(this.getCostoEstimado());
		return dto;
	}
	
	public ArticuloDto toFullDto() {

		ArticuloDto dto = toDto();
		if (this.getInventarioEnt() != null){
			dto.setInventarioEnt(this.getInventarioEnt().toDto());
		}
		
		if (this.getVentaEnt() != null){
			dto.setVentaEnt(this.getVentaEnt().toDto());
		}
		
		if (this.recetas != null){
			Set<RecetaDto> recetasDtos = new HashSet<RecetaDto>();
			for(Receta r : recetas){
				RecetaDto recetaDto = r.toDto();
				recetasDtos.add(recetaDto);
			}
			dto.setRecetas(recetasDtos);
		}
		
		if (this.puntos != null){
			Set<PuntoDto> puntoDtos = new HashSet<PuntoDto>();
			for(Punto p : puntos){
				PuntoDto puntoDto = p.toDto();
				puntoDtos.add(puntoDto);
			}
			
			dto.setPuntos(puntoDtos);
		}
		
		return dto;
	}

	/******* Getters And Setters para Articulo *********/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getCodigobarras() {
		return codigobarras;
	}

	public void setCodigobarras(String codigobarras) {
		this.codigobarras = codigobarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isVenta() {
		return venta;
	}

	public void setVenta(boolean venta) {
		this.venta = venta;
	}

	public boolean isInventario() {
		return inventario;
	}

	public void setInventario(boolean inventario) {
		this.inventario = inventario;
	}

	public boolean isReceta() {
		return receta;
	}

	public void setReceta(boolean receta) {
		this.receta = receta;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}
	
	public boolean isEmpaque() {
		return empaque;
	}

	public void setEmpaque(boolean empaque) {
		this.empaque = empaque;
	}
	
	public boolean isUtilizaEmpaque() {
		return utilizaEmpaque;
	}

	public void setUtilizaEmpaque(boolean utilizaEmpaque) {
		this.utilizaEmpaque = utilizaEmpaque;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Double getCostoEstimado() {
		return costoEstimado;
	}

	public void setCostoEstimado(Double costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public String getNombreImpresion() {
		return nombreImpresion;
	}

	public void setNombreImpresion(String nombreImpresion) {
		this.nombreImpresion = nombreImpresion;
	}
	
}
