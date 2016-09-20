package com.gamasoft.hps.sab.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.dto.RolClienteDto;

/**
 *
 * @author wospino
 */
@Entity
@Table(name = "rol_cliente")
public class RolCliente extends AuditablePersistent{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="user_rol_cliente",joinColumns={@JoinColumn(name="id_user")},inverseJoinColumns={@JoinColumn(name="id")})
	private Set<User> user =new HashSet<User>();
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="rol_cliente_transaccion",joinColumns={@JoinColumn(name="id_rol_cliente")},inverseJoinColumns={@JoinColumn(name="transac_id")})
	private Set<Transaccion> transaccion =new HashSet<Transaccion>();
	
//	@Column(name="obligatorio", nullable=true, length=1)
//	private int obligatorio;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@Column(name="activo", nullable=true, length=1)
	private boolean activo;
	
	public RolCliente(){
		
	}
	
	public RolClienteDto toDto() {
		// TODO Auto-generated method stub
		RolClienteDto dto = new RolClienteDto();
		dto.setId(this.getId());
		dto.setId_cliente(this.cliente.getId());
		dto.setId_rol(this.rol.getId());
		dto.setNombre(this.nombre);
		dto.setActivo(this.isActivo());
		return dto;
	}

	public RolCliente(RolClienteDto dto){
		this.nombre=dto.getNombre();
		this.activo=dto.isActivo();
		
		
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public RolCliente(Cliente c, Rol rol2) {
		setCliente(c);
		setRol(rol2);
    }

        public void modify(String name) {
        Validate.notNull(name, "Name cannot be null");
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof RolCliente)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Role: ";
    }

	public String getName() {
		// TODO Auto-generated method stub
		return rol.getName();
	}

	public Set<Transaccion> getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Set<Transaccion> transaccion) {
		this.transaccion = transaccion;
	}


}
