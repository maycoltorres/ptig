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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ClienteDto;
import com.gamasoft.hps.sab.exception.ServiceException;


@Entity
@Table(name = "cliente")

public class Cliente extends Persistent {
	
	
	private static final long serialVersionUID = 1L;
	
		
	@Column(name = "nombre", nullable = true, length = 100)
	private String nombre;

	@Column(name = "nit", nullable = true, length = 12)
	private String nit;

	@Column(name = "digitoChequeo", nullable = true, length = 1)
	private int digitoChequeo;

	@Column(name = "direccion", nullable = true, length = 40)
	private String direccion;

	@Column(name = "telefono", nullable = true, length = 25)
	private String telefono;

	@Column(name = "extension", nullable = true, length = 5)
	private int extension;
	
	@Column(name = "celular", nullable = true, length = 12)
	private String celular;

	@Column(name="email", nullable=true, length=50)
	private String email;
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Column(name = "regimen_id", nullable = true, length = 10)
	private String regimen_id;
	
	@Column(name = "nombreRepresentante", nullable = true, length = 100)
	private String nombreRepresentante;

	@Column(name = "documentoRepresentante", nullable = true, length = 40)
	private String documentoRepresentante;

	@Column(name = "nombreDueno", nullable = true, length = 100)
	private String nombreDueno;
	
	@Column(name="mailDueno", nullable=true, length=50)
	private String mailDueno;
	
	@Column(name="celularDueno", nullable=true, length=25)
	private String celularDueno;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private Set<RolCliente> roles;
	
	@Column(name="municipio", nullable=true, length=5)
	private int municipio;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "cliente_regimen", joinColumns = { @JoinColumn(name = "id_cliente") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_regimen") })
	private Set<Regimen> regimenes;
	
	
	@Column(name="telefono2", nullable= true, length=25)
	private long telefono2;
	
	@Column(name="extension2", nullable=true, length=5)
	private int extension2;

//	@Column(name="municipio_id", nullable= true, length=11)
//	private int municipio_id;

//	@Column(name="contactoDnegocio", nullable=true, length=40)
//	private String contactoDnegocio;
//	
//	@Column(name="contactoCargo", nullable=true, length=25)
//	private String contactoCargo;
//	
//	@Column(name="contactoMail", nullable=true, length=40)
//	private String contactoMail;
//	
//	@Column(name="contactoCelular", nullable=true, length=25)
//	private String contactoCelular;
//	
		
//	@Column(name="documentoDueno", nullable=true, length=40)
//	private String documentoDueno;
	
//	@Column(name = "nombreDueno1", nullable = true, length = 40)
//	private String nombreDueno1;
//
//	@Column(name="mailDueno1", nullable=true, length=30)
//	private String mailDueno1;
//	
//	@Column(name="celularDueno1", nullable=true, length=25)
//	private String celularDueno1;
//	
//	@Column(name = "nombreDueno2", nullable = true, length = 40)
//	private String nombreDueno2;
//
//	@Column(name="mailDueno2", nullable=true, length=30)
//	private String mailDueno2;
//	
//	@Column(name="celularDueno2", nullable=true, length=25)
//	private String celularDueno2;
	
	@Column(name= "activo", nullable = true, length =1)
	private boolean activo;
	
	@ManyToMany(mappedBy="clientes")
    private Set<Grupo> grupos = new HashSet<Grupo>();
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CanalImpuesto> canalesImpuestos;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "cliente_canal", joinColumns = { @JoinColumn(name = "id_cliente") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_canal") })
	private Set<Canal> canales;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "cliente")
    private ConfiguracionCliente configuracionCliente;
	
	public Set<CanalImpuesto> getCanalesImpuestos() {
		return canalesImpuestos;
	}


	public void setCanalesImpuestos(Set<CanalImpuesto> canalesImpuestos) {
		this.canalesImpuestos = canalesImpuestos;
	}



	public Cliente (){

	}
	
	
	
	public Cliente(ClienteDto dto) throws ServiceException {
		try{
		if(dto.getNombre() == null){
			throw new ServiceException ("El Nombre del cliente (Razon Social) no puede estar vacio");
		}
		if(dto.getNit()==null){
			throw new ServiceException ("El campo Nit no puedo estar vacio");
					}
		if(dto.getDireccion()==null){
			throw new ServiceException ("El campo Direccion es obligatorio");
		}
		if(dto.getTelefono()==null||dto.getTelefono().length()==0){
			throw new ServiceException ("El campo Telefono no puede estar vacio");
		}
		if(dto.getRegimen_id()==null){
			throw new ServiceException ("Se requiere informacion de  Regimen no puede estar vacio");
		}
		if(dto.getNombreDueno()==null){
			throw new ServiceException ("Se requiere informacion de Propietario no puede estar vacio");}
		
		if(dto.getNombre()==null){ 
			throw new ServiceException ("Se requiere informacion de nombre no puede estar vacio");}
		
		if(dto.getNit()==null||(dto.getNit()).length()<5||(dto.getNit()).length()>19){
			throw new ServiceException ("El nit debe estar entre 5 y 15 caracteres");}
				
		if(dto.getNombreRepresentante()==null||dto.getNombreRepresentante().length()<3||dto.getNombreRepresentante().length()>100){
	
			throw new ServiceException ("El campo nombre de representante debe tener entre 3 y 100 caracteres");
		}
		if(dto.getDireccion()==null||dto.getDireccion().length()<1 || dto.getDireccion().length()>30){
			throw new ServiceException ("El campo nombre de representante debe tener entre  y 30 caracteres");
		}
		if(dto.getTelefono()==null||dto.getTelefono().length()<7 || dto.getTelefono().length()>25){
			throw new ServiceException ("El campo nombre de representante debe tener entre 7 y 25 caracteres");
		}
		
		if(Integer.toString(dto.getExtension())==null||Integer.toString(dto.getExtension()).length()<1 || Integer.toString(dto.getExtension()).length()>4 ){
			throw new ServiceException ("El campo nombre de representante debe tener entre 1 y 4 caracteres");
		}
		
		if(dto.getNombreDueno()==null||dto.getNombreDueno().length()<=3||dto.getNombreDueno().length()>100){
			throw new ServiceException ("El nobre del dueÃ±o debe estar entre 3 y 100 caracteres");
		}
		
		if(dto.getCelular()==null||dto.getCelular().length()<5||dto.getCelular().length()>25){
			throw new ServiceException ("El campo nombre de representante debe tener entre 5 y 25 caracteres");
		}
		
		if(dto.getDocumentoRepresentante()==null||dto.getDocumentoRepresentante().length()<5 || dto.getDocumentoRepresentante().length()>15){
			throw new ServiceException ("El documente del representante debe tener entre 6 y 10 caracteres");
		}
		
		if(dto.getEmail()==null||dto.getEmail()==""){
			throw new ServiceException("El email no debe estar vacio");
		}
		if(dto.getEmail().length()<5||dto.getEmail().length()>50){
			throw new ServiceException("El correo debe tener entre 5 y 50 caracteres");
		}
		}catch (ServiceException s){
			s.getMessage();
		}
		this.nombre = dto.getNombre();
		this.nit = dto.getNit();
		this.digitoChequeo = dto.getDigitoChequeo();
		this.direccion = dto.getDireccion();
		this.telefono = dto.getTelefono();
		this.extension = dto.getExtension();
		this.celular = dto.getCelular();
		this.regimen_id = dto.getRegimen_id();
		this.nombreRepresentante = dto.getNombreRepresentante();
		this.documentoRepresentante = dto.getDocumentoRepresentante();
		this.nombreDueno= dto.getNombreDueno();
		this.mailDueno= dto.getMailDueno();
		this.celularDueno = dto.getCelularDueno();
		this.municipio = dto.getMunicipio();
		this.email= dto.getEmail();
		this.activo= dto.isActivo();
//		this.contactoDnegocio = dto.getContactoDnegocio();
//		this.contactoCargo = dto.getContactoCargo();
//		this.municipio_id = dto.getMunicipio_id();
//		this.contactoMail = dto.getContactoMail();
//		this.contactoCelular = dto.getContactoCelular();
//		this.nombreDueno1= dto.getNombreDueno1();
//		this.mailDueno1= dto.getMailDueno1();
//		this.celularDueno1 = dto.getCelularDueno1();
//		this.nombreDueno2= dto.getNombreDueno2();
//		this.mailDueno2= dto.getMailDueno2();
//		this.celularDueno2 = dto.getCelularDueno2();
		
	}

	

	

	public ClienteDto toDto() {
		ClienteDto dto = new ClienteDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setNit(this.nit);
		dto.setDigitoChequeo(this.digitoChequeo);
		dto.setDireccion(this.direccion);
		dto.setTelefono(this.telefono);
		dto.setExtension(this.extension);
		dto.setCelular(this.celular);
		dto.setRegimen_id(this.regimen_id);
		dto.setNombreRepresentante(this.nombreRepresentante);
		dto.setDocumentoRepresentante(this.documentoRepresentante);
		dto.setNombreDueno(this.nombreDueno);
		dto.setMailDueno(this.mailDueno);
		dto.setCelularDueno(this.celularDueno);
		dto.setActivo(this.activo);
		dto.setMunicipio(this.municipio);
		dto.setEmail(this.email);
		dto.setTelefono2(this.telefono2);
		dto.setExtension2(this.extension2);
//		dto.setNombreDueno1(this.nombreDueno1);
//		dto.setMailDueno1(this.mailDueno1);
//		dto.setCelularDueno1(this.celularDueno1);
//		dto.setNombreDueno2(this.nombreDueno2);
//		dto.setMailDueno2(this.mailDueno2);
//		dto.setCelularDueno2(this.celularDueno2);
//		dto.setMunicipio_id(this.municipio_id);
//		dto.setContactoDnegocio(this.contactoDnegocio);
//		dto.setContactoCargo(this.contactoCargo);
//		dto.setContactoMail(this.contactoMail);
//		dto.setContactoCelular(this.contactoCelular);
//		dto.setDocumentoDueno(this.documentoDueno);

		
		return dto;
	}

	
	public ClienteDto toDto2() {
		ClienteDto dto = new ClienteDto();
		dto.setId(this.getId());
		dto.setNombre(this.nombre);
		dto.setNit(this.nit);
		dto.setDigitoChequeo(this.digitoChequeo);
		dto.setDireccion(this.direccion);
		dto.setTelefono(this.telefono);
		dto.setExtension(this.extension);
		dto.setCelular(this.celular);
		dto.setRegimen_id(this.regimen_id);
		dto.setNombreRepresentante(this.nombreRepresentante);
		dto.setDocumentoRepresentante(this.documentoRepresentante);
		dto.setNombreDueno(this.nombreDueno);
		dto.setMailDueno(this.mailDueno);
		dto.setCelularDueno(this.celularDueno);
		dto.setActivo(this.activo);
		dto.setMunicipio(this.municipio);
		dto.setEmail(this.email);
//		dto.setDocumentoDueno(this.documentoDueno);
//		dto.setContactoDnegocio(this.contactoDnegocio);
//		dto.setContactoCargo(this.contactoCargo);
//		dto.setContactoMail(this.contactoMail);
//		dto.setContactoCelular(this.contactoCelular);
//		dto.setMunicipio_id(this.municipio_id);
//		dto.setNombreDueno1(this.nombreDueno1);
//		dto.setMailDueno1(this.mailDueno1);
//		dto.setCelularDueno1(this.celularDueno1);
//		dto.setNombreDueno2(this.nombreDueno2);
//		dto.setMailDueno2(this.mailDueno2);
//		dto.setCelularDueno2(this.celularDueno2);
//		dto.setTelefono2(this.telefono2);
//		dto.setExtension2(this.extension2);

		return dto;
	}



	/************************************ Getters and setter de las variables del cliente ****************************/
	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNit() {
		return nit;
	}



	public void setNit(String nit) {
		this.nit = nit;
	}



	public int getDigitoChequeo() {
		return digitoChequeo;
	}



	public void setDigitoChequeo(int digitoChequeo) {
		this.digitoChequeo = digitoChequeo;
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



	public int getExtension() {
		return extension;
	}



	public void setExtension(int extension) {
		this.extension = extension;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public String getRegimen_id() {
		return regimen_id;
	}



	public void setRegimen_id(String regimen_id) {
		this.regimen_id = regimen_id;
	}



	public String getNombreRepresentante() {
		return nombreRepresentante;
	}



	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}






	public String getDocumentoRepresentante() {
		return documentoRepresentante;
	}



	public void setDocumentoRepresentante(String documentoRepresentante) {
		this.documentoRepresentante = documentoRepresentante;
	}



	public String getNombreDueno() {
		return nombreDueno;
	}



	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}



	public String getMailDueno() {
		return mailDueno;
	}



	public void setMailDueno(String mailDueno) {
		this.mailDueno = mailDueno;
	}



	public String getCelularDueno() {
		return celularDueno;
	}



	public void setCelularDueno(String celularDueno) {
		this.celularDueno = celularDueno;
	}



	public Set<RolCliente> getRoles() {
		return roles;
	}



	public void setRoles(Set<RolCliente> roles) {
		this.roles = roles;
	}



	public boolean isActivo() {
		return activo;
	}



	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	public Set<Grupo> getGrupos() {
		return grupos;
	}



	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}



	public int getMunicipio() {
		return municipio;
	}



	public void setMunicipio(int municipio) {
		this.municipio = municipio;
	}



	public Set<Regimen> getRegimenes() {
		return regimenes;
	}



	public void setRegimenes(Set<Regimen> regimenes) {
		this.regimenes = regimenes;
	}

	
	public Set<Canal> getCanales() {
		return canales;
	}



	public void setCanales(Set<Canal> canales) {
		this.canales = canales;
	}



	public long getTelefono2() {
		return telefono2;
	}



	public void setTelefono2(long telefono2) {
		this.telefono2 = telefono2;
	}



	public int getExtension2() {
		return extension2;
	}



	public void setExtension2(int extension2) {
		this.extension2 = extension2;
	}
	
}