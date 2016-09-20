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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.Validate;
import org.hibernate.annotations.Type;

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.dto.RolDto;

/**
 *
 * @author vascordoba
 */
@Entity
@Table(name = "rol")
public class Rol extends AuditablePersistent{

	private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

   
    
	@Type(type = "com.gamasoft.hps.sab.domain.base.MyBooleanType")
    @Column(name="activo", nullable=true, length=1)
    private boolean activo;
    
    /*********/
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "rol")
    private Set<RolCliente> rolcliente;
    
    public Rol() {
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="rol_transaccion",joinColumns={@JoinColumn(name="id_rol")},inverseJoinColumns={@JoinColumn(name="id_transaccion")})
	private Set<Transaccion> transac =new HashSet<Transaccion>();
    
    public RolDto toDto() {	
		RolDto dto = new RolDto();
		dto.setNombre(this.name);
		dto.setActivo(this.isActivo());
		dto.setId(this.getId());
		return dto;
	}
    
    
    public Rol(RolDto dto){
    	this.name=dto.getNombre();
    	this.activo=dto.isActivo();
    	this.rolcliente=dto.getRolcliente();
    }
    
    public Rol(String name) {
        Validate.notNull(name, "Name cannot be null");
         this.name = name;
    }

    public String getName() {
        return name;
    }

    public void modify(String name) {
        Validate.notNull(name, "Name cannot be null");
        this.name = name;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) obj;
        return (other.name.equals(name));
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Role: " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Set<RolCliente> getRolcliente() {
		return rolcliente;
	}

	public void setRolcliente(Set<RolCliente> rolcliente) {
		this.rolcliente = rolcliente;
	}


	public Set<Transaccion> getTransac() {
		return transac;
	}


	public void setTransac(Set<Transaccion> transac) {
		this.transac = transac;
	}

	
    
    
}
