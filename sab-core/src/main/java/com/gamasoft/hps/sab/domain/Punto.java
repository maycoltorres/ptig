package com.gamasoft.hps.sab.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.BodegaDto;
import com.gamasoft.hps.sab.dto.MarcaDto;
import com.gamasoft.hps.sab.dto.PuntoDto;
import com.gamasoft.hps.sab.exception.ServiceException;

/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "punto")
public class Punto extends Persistent {

	private static final long serialVersionUID = 1L;

	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;

	@Column(name = "direccion", nullable = true, length = 40)
	private String direccion;

	@Column(name = "telefono", nullable = true, length = 10)
	private String telefono;

	@Column(name = "extension", nullable = true, length = 4)
	private String extension;

	@Column(name = "telefono2", nullable = true, length = 10)
	private String telefono2;

	@Column(name = "extension2", nullable = true, length = 4)
	private String extension2;

	@Column(name = "municipio_id", nullable = true, length = 11)
	private int municipio_id;

	@Column(name = "tipo_negocio_id", nullable = true, length = 11)
	private int tipo_negocio_id;
	
	@ManyToMany(mappedBy="puntos")
    private Set<Articulo> articulos;
	
	@Column(name = "centro_costo_id", nullable = true, length = 11)
	private int centro_costo_id;

	@Column(name = "cliente_id", nullable = true, length = 11)
	private Long cliente_id;
	
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "activo", nullable = true, length = 1)
	private boolean activo;
	
	@Column(name = "tieneBodega", nullable = true, length = 1)
	private boolean tieneBodega;

	/***********************
	 * Configuracion del punto, parametros especificos del funcionamiento del
	 * punto
	 ************************************/
	/* definicion de variables de configuracion del punto */

	/**** Caso de uso perimitir almacenar en el punto */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "almacenarNpunto",nullable = true, length = 1)
	private boolean almacenarNpunto;

	/**** Caso de uso permitir usar apoyo de licencias en red */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "apoyalicrec", nullable = true, length = 1)
	private boolean apoyalicrec;

	/**** Caso de uso permitir autorizar a usuario que elimina */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "autoUsuEliminar", nullable = true, length = 1)
	private boolean autoUsuEliminar;

	/**** Caso de uso perimitir contar dinero por denomiancion */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "contXdenominacion", nullable = true, length = 1)
	private boolean contXdenominacion;

	/**** Caso de uso permitir facturar en el punto */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "factNpunto", nullable = true, length = 1)
	private boolean factNpunto;

	/**** Caso de uso permitir manejo de varias licencias en el punto */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "ManNlicencias", nullable = true, length = 1)
	private boolean ManNlicencias;

	/**** Caso de uso permitir operar licencias en red */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "opLicNred", nullable = true, length = 1)
	private boolean opLicNred;

	/***** Caso de uso permitir cierre oculto de caja */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "cierreocultocaja", nullable = true, length = 1)
	private boolean cierreocultocaja;

	/***** Caso de uso solicitar clave para aunlar */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "claveanular", nullable = true, length = 1)
	private boolean claveanular;

	/***** Caso de uso Utilizar impresiones remotas */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "imprRemotas", nullable = true, length = 1)
	private boolean imprRemotas;

	/***** Caso de uso Autorizar usuario que anula verificacion */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "autAnularVerificacion", nullable = true, length = 1)
	private boolean autAnularVerificacion;

	/***** Caso de uso permitir Capturar numero de personas atendidas */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "capNpersonasAt", nullable = true, length = 1)
	private boolean capNpersonasAt;

	/***** Caso de uso permitir Capturar codigo de mesero */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "capCodmesero", nullable = true, length = 1)
	private boolean capCodmesero;

	/*****
	 * Caso de uso permitir Capturar codigo de autoservicio (numero de mesa,
	 * numero de turno etc)
	 */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "capCodAutoserv", nullable = true, length = 1)
	private boolean capCodAutoserv;

	/***** Caso de uso usar denominacion de quien atiende la mesa (mesero, etc) */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "denoatendidopor", nullable = true, length = 1)
	private boolean denoatendidopor;

	/***** Caso de uso permitir facturar en formato resumido */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "factFresumido", nullable = true, length = 1)
	private boolean factFresumido;

	/***** Caso de uso permitir facturar sin documento de verificacion */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "factSinDocVerif", nullable = true, length = 1)
	private boolean factSinDocVerif;

	/***** Caso de uso cambiar forma de venta meseros */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "fvmenseros", nullable = true, length = 1)
	private boolean fvmenseros;

	/***** Caso de uso permitir generar impresion remota de copia de la factura */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "impremotacpfactura", nullable = true, length = 1)
	private boolean impremotacpfactura;

	/***** Caso de uso permitir identificar el control de consumo con la palabra */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "controlconspalabra", nullable = true, length = 1)
	private boolean controlconspalabra;

	/***** Caso de uso permitir imprimir tiquete de pedido de autoservicio */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "impticketAutoSrv", nullable = true, length = 1)
	private boolean impticketAutoSrv;

	/***** Caso de uso permitir incluir iva en el precio de venta */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "inclivaPventa", nullable = true, length = 1)
	private boolean inclivaPventa;

	/***** Caso de uso Requerir clave para anular verificacion */
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name = "claveanularVerifi", nullable = true, length = 1)
	private boolean claveanularVerifi;

	/*******
	 * Relacion varios a varios entre las tablas, punto y marca, usando la tabla
	 * intermadia punto_marca, para quitar la realcion n-n
	 *****/
	@ManyToMany(cascade = { CascadeType.REMOVE })
	@JoinTable(name = "punto_marca", joinColumns = { @JoinColumn(name = "id_punto") }, inverseJoinColumns = { @JoinColumn(name = "id_marca") })
	private Set<Marca> marcas = new HashSet<Marca>();
	
	@OneToMany(mappedBy = "punto", fetch = FetchType.EAGER)
    private Set<Bodega> bodegas;
	

	/*******************************************************************************************************************************************/

	public Punto(PuntoDto dto) throws ServiceException {
		/**** Validando informacion que viene desde el front ***/
		if (dto.getNombre() == null || dto.getNombre().length() < 3|| dto.getNombre().length() > 100) {
			throw new ServiceException(
					"El nombre debe tener entre  3 o 100 caracteres");
		}
		if (dto.getDireccion() == null || dto.getDireccion().length() < 5|| dto.getDireccion().length() > 100) {
			throw new ServiceException(
					"La direccion debe tener entre 5 y 100 caracteres");
		}
		if (dto.getTelefono().length() < 7|| dto.getTelefono().length() > 25) {
			throw new ServiceException(
					"El telefono debe tener entre 7 y 25 digitos");
		}
		
		
		
		if (Integer.toString(dto.getMunicipio_id())==null ||dto.getMunicipio_id() == 0) {
			throw new ServiceException(
					"Se debe proporcionar el codigo del Municipio");
		}
		if (Integer.toString(dto.getTipo_negocio_id()) == null || dto.getTipo_negocio_id()==0) {
			throw new ServiceException("Se debe proporcionar el id de negocio");
		}
		
		if ( dto.getCliente_id()==null||dto.getCliente_id() == 0) {
			throw new ServiceException(
					"Se debe proporcionar el Id del cliente al que se va a asociar el punto");
		}

		this.nombre = dto.getNombre();
		this.direccion = dto.getDireccion();
		this.telefono = dto.getTelefono();
		this.extension = dto.getExtension();
		this.telefono2 = dto.getTelefono2();
		this.extension2 = dto.getExtension2();
		this.municipio_id = dto.getMunicipio_id();
		this.tipo_negocio_id = dto.getTipo_negocio_id();
		this.centro_costo_id = dto.getCentro_costo_id();
		this.cliente_id = dto.getCliente_id();
		this.tieneBodega = dto.getTieneBodega();
		this.cliente_id = dto.getCliente_id();
}

	public Punto() {

	}

	public void setName(String name) {
		this.nombre = name;
	}

	public PuntoDto toDto() { // se "setean" los valores del punto para ser
								// almacenados en la BD
		PuntoDto dto = new PuntoDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setDireccion(this.direccion);
		dto.setTelefono(this.telefono);
		dto.setExtension(this.extension);
		dto.setTelefono2(this.telefono2);
		dto.setExtension2(this.extension2);
		dto.setMunicipio_id(this.municipio_id);
		dto.setTipo_negocio_id(this.tipo_negocio_id);
		dto.setCentro_costo_id(this.centro_costo_id);
		dto.setCliente_id(this.cliente_id);
		dto.setActivo(this.activo);
		dto.setTieneBodega(this.tieneBodega);
		return dto;
	}

	public PuntoDto toDto2() {
		PuntoDto dto = new PuntoDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setDireccion(this.direccion);
		dto.setTelefono(this.telefono);
		dto.setExtension(this.extension);
		dto.setTelefono2(this.telefono2);
		dto.setExtension2(this.extension2);
		dto.setMunicipio_id(this.municipio_id);
		dto.setTipo_negocio_id(this.tipo_negocio_id);
		dto.setCentro_costo_id(this.centro_costo_id);
		dto.setCliente_id(this.cliente_id);
		dto.setActivo(this.activo);
		dto.setAlmacenarNpunto(this.almacenarNpunto);
		dto.setApoyalicrec(this.apoyalicrec);
		dto.setAutoUsuEliminar(this.autoUsuEliminar);
		dto.setContXdenominacion(this.contXdenominacion);
		dto.setFactNpunto(isFactNpunto());
		dto.setManNlicencias(this.ManNlicencias);
		dto.setOpLicNred(this.opLicNred);
		dto.setCierreocultocaja(this.cierreocultocaja);
		dto.setClaveanular(this.claveanular);
		dto.setImprRemotas(this.imprRemotas);
		dto.setAutAnularVerificacion(this.autAnularVerificacion);
		dto.setCapNpersonasAt(this.capNpersonasAt);
		dto.setCapCodmesero(this.capCodmesero);
		dto.setCapCodAutoserv(this.capCodAutoserv);
		dto.setDenoatendidopor(this.denoatendidopor);
		dto.setFactFresumido(this.factFresumido);
		dto.setFactSinDocVerif(this.factSinDocVerif);
		dto.setFvmenseros(this.fvmenseros);
		dto.setImpremotacpfactura(this.impremotacpfactura);
		dto.setControlconspalabra(this.controlconspalabra);
		dto.setImpticketAutoSrv(this.impticketAutoSrv);
		dto.setInclivaPventa(this.inclivaPventa);
		dto.setClaveanularVerifi(this.claveanularVerifi);
		dto.setTieneBodega(this.tieneBodega);
		if (getMarcas() != null) {
			List<MarcaDto> mcaDto = new ArrayList<MarcaDto>();
			for (Marca c : getMarcas()) {
				mcaDto.add(c.toDto());
			}
			dto.setMarcas(mcaDto);
		}
		
		if (getBodegas() != null) {
			List<BodegaDto> bodegas = new ArrayList<BodegaDto>();
			for (Bodega b : getBodegas()) {
				bodegas.add(b.toDto());
			}
			dto.setBodegas(bodegas);
		}
		
		return dto;
		
	}

	/************** Definicion de setther and getter de las variables que tiene el punto ****************/

	public String getExtension() {
		return extension;
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

	public void setCliente_id(Long idCliente) {
		this.cliente_id = idCliente;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(Set<Marca> marcas) {
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

	public void setOpLicNred(boolean opLicNreD) {
		opLicNred = opLicNreD;
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
	
	public boolean tieneBodega() {
		return tieneBodega;
	}

	public void setTieneBodega(boolean tieneBodega) {
		this.tieneBodega = tieneBodega;
	}
	
	public Set<Bodega> getBodegas() {
		return bodegas;
	}

	public void setBodegas(Set<Bodega> bodegas) {
		this.bodegas = bodegas;
	}

}
