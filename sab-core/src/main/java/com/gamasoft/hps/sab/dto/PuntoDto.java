package com.gamasoft.hps.sab.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PuntoDto {

	private long id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String extension;
	private String telefono2;
	private String extension2;
	private int	municipio_id;
	private int tipo_negocio_id;
	private int centro_costo_id;
	private Long cliente_id;
	private boolean activo;
	private List<MarcaDto> marcas;
	private List<BodegaDto> bodegas;
	
	/****Variables de configuracion del punto**/
	private boolean ManNlicencias;
	private boolean opLicNred;
	private boolean fvmenseros;
	private boolean almacenarNpunto;
	private boolean apoyalicrec;
	private boolean autoUsuEliminar;
	private boolean contXdenominacion;
	private boolean factNpunto;
	private boolean cierreocultocaja;
	private boolean claveanular;
	private boolean imprRemotas;
	private boolean autAnularVerificacion;
	private boolean capNpersonasAt;
	private boolean capCodmesero;
	private boolean capCodAutoserv;
	private boolean denoatendidopor;
	private boolean factFresumido;
	private boolean factSinDocVerif;
	private boolean impremotacpfactura;
	private boolean controlconspalabra;
	private boolean impticketAutoSrv;
	private boolean inclivaPventa;
	private boolean claveanularVerifi;
	private boolean tieneBodega;
	
	
	
	/************** Definicion de setther and getter de las variables que tiene el puntoDto ****************/
	
	
	
	public long getId() {
		return id;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getExtension2() {
		return extension2;
	}
	public void setExtension2(String extension2) {
		this.extension2 = extension2;
	}
	public int getMunicipio_id() {
		return municipio_id;
	}
	public void setMunicipio_id(int municipio_id) {
		this.municipio_id = municipio_id;
	}
	public int getTipo_negocio_id() {
		return tipo_negocio_id;
	}
	public void setTipo_negocio_id(int tipo_negocio_id) {
		this.tipo_negocio_id = tipo_negocio_id;
	}
	public int getCentro_costo_id() {
		return centro_costo_id;
	}
	public void setCentro_costo_id(int centro_costo_id) {
		this.centro_costo_id = centro_costo_id;
	}
	public Long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Long cliente_id) {
		this.cliente_id = cliente_id;
	}

	public List<MarcaDto> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaDto> marcas) {
		this.marcas = marcas;
	}

	public boolean isAlmacenarNpunto() {
		return almacenarNpunto;
	}

	public void setAlmacenarNpunto(boolean almacenarNpunto) {
		this.almacenarNpunto = almacenarNpunto;
	}

	public boolean isApoyalicrec() {
		return apoyalicrec;
	}

	public void setApoyalicrec(boolean apoyalicrec) {
		this.apoyalicrec = apoyalicrec;
	}

	public boolean isAutoUsuEliminar() {
		return autoUsuEliminar;
	}

	public void setAutoUsuEliminar(boolean autoUsuEliminar) {
		this.autoUsuEliminar = autoUsuEliminar;
	}

	public boolean isContXdenominacion() {
		return contXdenominacion;
	}

	public void setContXdenominacion(boolean contXdenominacion) {
		this.contXdenominacion = contXdenominacion;
	}

	public boolean isFactNpunto() {
		return factNpunto;
	}

	public void setFactNpunto(boolean factNpunto) {
		this.factNpunto = factNpunto;
	}

	public boolean isManNlicencias() {
		return ManNlicencias;
	}

	public void setManNlicencias(boolean manNlicencias) {
		ManNlicencias = manNlicencias;
	}

	public boolean isOpLicNred() {
		return opLicNred;
	}

	public void setOpLicNred(boolean opLicNred1) {
		opLicNred = opLicNred1;
	}

	public boolean isCierreocultocaja() {
		return cierreocultocaja;
	}

	public void setCierreocultocaja(boolean cierreocultocaja) {
		this.cierreocultocaja = cierreocultocaja;
	}

	public boolean isClaveanular() {
		return claveanular;
	}

	public void setClaveanular(boolean claveanular) {
		this.claveanular = claveanular;
	}

	public boolean isImprRemotas() {
		return imprRemotas;
	}

	public void setImprRemotas(boolean imprRemotas) {
		this.imprRemotas = imprRemotas;
	}

	public boolean isAutAnularVerificacion() {
		return autAnularVerificacion;
	}

	public void setAutAnularVerificacion(boolean autAnularVerificacion) {
		this.autAnularVerificacion = autAnularVerificacion;
	}

	public boolean isCapNpersonasAt() {
		return capNpersonasAt;
	}

	public void setCapNpersonasAt(boolean capNpersonasAt) {
		this.capNpersonasAt = capNpersonasAt;
	}

	public boolean isCapCodmesero() {
		return capCodmesero;
	}

	public void setCapCodmesero(boolean capCodmesero) {
		this.capCodmesero = capCodmesero;
	}

	public boolean isCapCodAutoserv() {
		return capCodAutoserv;
	}

	public void setCapCodAutoserv(boolean capCodAutoserv) {
		this.capCodAutoserv = capCodAutoserv;
	}

	public boolean isDenoatendidopor() {
		return denoatendidopor;
	}

	public void setDenoatendidopor(boolean denoatendidopor) {
		this.denoatendidopor = denoatendidopor;
	}

	public boolean isFactFresumido() {
		return factFresumido;
	}

	public void setFactFresumido(boolean factFresumido) {
		this.factFresumido = factFresumido;
	}

	public boolean isFactSinDocVerif() {
		return factSinDocVerif;
	}

	public void setFactSinDocVerif(boolean factSinDocVerif) {
		this.factSinDocVerif = factSinDocVerif;
	}

	public boolean isFvmenseros() {
		return fvmenseros;
	}

	public void setFvmenseros(boolean Fvmenseros) {
		fvmenseros = Fvmenseros;
	}

	public boolean isImpremotacpfactura() {
		return impremotacpfactura;
	}

	public void setImpremotacpfactura(boolean impremotacpfactura) {
		this.impremotacpfactura = impremotacpfactura;
	}

	public boolean isControlconspalabra() {
		return controlconspalabra;
	}

	public void setControlconspalabra(boolean controlconspalabra) {
		this.controlconspalabra = controlconspalabra;
	}

	public boolean isImpticketAutoSrv() {
		return impticketAutoSrv;
	}

	public void setImpticketAutoSrv(boolean impticketAutoSrv) {
		this.impticketAutoSrv = impticketAutoSrv;
	}

	public boolean isInclivaPventa() {
		return inclivaPventa;
	}

	public void setInclivaPventa(boolean inclivaPventa) {
		this.inclivaPventa = inclivaPventa;
	}

	public boolean isClaveanularVerifi() {
		return claveanularVerifi;
	}

	public void setClaveanularVerifi(boolean claveanularVerifi) {
		this.claveanularVerifi = claveanularVerifi;
	}
	
	public boolean getTieneBodega() {
		return tieneBodega;
	}

	public void setTieneBodega(boolean tieneBodega) {
		this.tieneBodega = tieneBodega;
	}

	public List<BodegaDto> getBodegas() {
		return bodegas;
	}

	public void setBodegas(List<BodegaDto> bodegas) {
		this.bodegas = bodegas;
	}

	/***Getters and setter de configurar punto ****/
	
}
