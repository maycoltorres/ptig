package com.gamasoft.hps.sab.dto;
/**
 * 
 * @author wospino
 */
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticuloDto {

	private Long id;
	private String nombre;
	private String nombreImpresion;
	private Long idCliente;
	private String codigobarras;
	private String descripcion;
	private boolean venta;
	private boolean inventario;
	private boolean receta;
	private boolean seleccion;
	private boolean empaque;
	private boolean utilizaEmpaque;
	private boolean activo;
	private Double costoEstimado;
	private InventarioDto inventarioEnt;
	private VentaDto ventaEnt;
	private Set<RecetaDto> recetas;
	private Set<PuntoDto> puntos;
	
	
	public Set<RecetaDto> getRecetas() {
		return recetas;
	}
	public void setRecetas(Set<RecetaDto> recetas) {
		this.recetas = recetas;
	}
	public VentaDto getVentaEnt() {
		return ventaEnt;
	}
	public void setVentaEnt(VentaDto ventaEnt) {
		this.ventaEnt = ventaEnt;
	}
	public InventarioDto getInventarioEnt() {
		return inventarioEnt;
	}
	public void setInventarioEnt(InventarioDto inventarioEnt) {
		this.inventarioEnt = inventarioEnt;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public Set<PuntoDto> getPuntos() {
		return puntos;
	}
	public void setPuntos(Set<PuntoDto> puntos) {
		this.puntos = puntos;
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
