/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;
import org.hibernate.annotations.Type;

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.service.local.CustomUserDetailsService;

/**
 * 
 * @author wospino
 */
@Entity
@Table(name = "user")
@DiscriminatorValue("user")
public class User extends AuditablePersistent{

	private static final long serialVersionUID = 1L;

	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "fullname", nullable = false, length = 50)
	private String fullName;

	// Length must be 512 because the password is encrypted
	@Column(name = "password", nullable = false, length = 512)
	private String password;

	@Column(name = "email", nullable = false, length = 100, unique = true)
	private String email;

	@Column(name="id_cliente", nullable=false, length=11)
	private Long idCliente;
	
	@Column(name="id_grupo", nullable=true, length=11)
	private Long idGrupo;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="usuario_punto",joinColumns={@JoinColumn(name="id_usuario")},inverseJoinColumns={@JoinColumn(name="id_punto")})
	private Set<Punto> puntos;
	
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
	@Column(name="activo", nullable =false, length=1)
	private boolean activo;
	
	@ManyToMany(mappedBy="transaccion")
    private Set<RolCliente> Rolcliente = new HashSet<RolCliente>();

    
	public User() {
		this("", ""); // WTF!?
	}

    public User(UserDto dto) {
    	CustomUserDetailsService cud = new CustomUserDetailsService();
    	this.username = dto.getUsername();
        this.fullName = dto.getFullName();
        this.password = cud.Encripcion(dto.getPassword());
        this.email = dto.getEmail();
        this.idCliente = dto.getIdCliente();
        this.idGrupo = dto.getIdGrupo();
        this.activo =dto.isActivo();
    }
    
    public UserDto toDto(){
        UserDto dto=new UserDto();
        dto.setId(getId());
        dto.setFullName(getFullName());
        dto.setEmail(getEmail());
        dto.setPassword(getPassword());
        dto.setUsername(getEmail());
        dto.setIdCliente(this.getIdCliente());
        dto.setIdGrupo(this.getIdGrupo());
        dto.setActivo(this.isActivo());
        /*
        if(puntos != null){
        	Set<Long> idPuntos = new HashSet<Long>();
        	for(Punto p : puntos){
        		idPuntos.add(p.getId());
        	}
        	dto.setPuntos(idPuntos);
        }*/
       
        
        return dto;
    }
    
    public UserDto toFullDto(){
        UserDto dto=toDto();
        return dto;
    }
    
    

	public User(final String username, final String email) {
		Validate.notNull(username, "The user name cannot be null");
		Validate.notNull(email, "The user email cannot be null");
		this.username = username;
		this.email = email;
	}

	public void modify(final String newUsername, final String newEmail) {
		Validate.notNull(newUsername, "The user name cannot be null");
		Validate.notNull(newEmail, "The user email cannot be null");
		username = newUsername;
		email = newEmail;
	}

	/**
	 * Changes the user password.
	 * 
	 * @param newPassword
	 *            The new user password. It cannot be null.
	 * 
	 *            TODO Decide how to manage the password restrictions. Should
	 *            this decision be delegated to a strategy?
	 */
	public void changePassword(final String newPassword) {
		Validate.notNull(newPassword, "The password cannot be null");
		CustomUserDetailsService cud = new CustomUserDetailsService();
		password = cud.Encripcion(newPassword);
	}

	/**
	 * Validates the password of the user.
	 * 
	 * @param thePassword
	 *            The password to validate.
	 * 
	 * @return Returns true if the provided password matches the user password,
	 *         false otherwise.
	 */
	public boolean validatePassword(final String thePassword) {
		return password.equals(thePassword);
	}

	/**
	 * Returns the password of the user.
	 * 
	 * @return Returns the password. Never returns null.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the email of the user.
	 * 
	 * @return the email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the roles of the user.
	 * 
	 * @return the roles
	 */
	public Set<RolCliente> getRolCliente() {
		return Rolcliente;
	}

	public boolean hasRole(String roleName) {
		for (RolCliente role : this.getRolCliente()) {
			if (role.getName().equals(roleName)) {
				return true;
			}
		}
		return false;
	}

	public void setRoleCliente(RolCliente role) {
		this.Rolcliente.clear();
		this.Rolcliente.add(role);
	}

	public void setEmail(String email) {
		Validate.notNull(email, "The email cannot be null");
		this.email = email;
	}

	public void setPassword(String password) {
		Validate.notNull(password, "The password cannot be null");
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;

		return true;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(final String fullName) {
		Validate.notNull(fullName, "The user fullname cannot be null");
		this.fullName = fullName;
	}

	public void setRolCliente(Set<RolCliente> roles) {
		this.Rolcliente = roles;
	}

	public void setUsername(String username) {
		Validate.notNull(username, "The username cannot be null");
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	

    public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<RolCliente> getRolcliente() {
		return Rolcliente;
	}

	public void setRolcliente(Set<RolCliente> rolcliente) {
		Rolcliente = rolcliente;
	}

	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Set<Punto> getPuntos() {
		return puntos;
	}

	public void setPuntos(Set<Punto> puntos) {
		this.puntos = puntos;
	}

}