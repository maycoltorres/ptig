/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author wospino
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private Long idCliente;
    private Long idGrupo;
    private Set<Long> puntos;
	private boolean activo;
    
    public UserDto() {
        // Empty constructor to be called by reflection.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
    	
    	return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Set<Long> getPuntos() {
		return puntos;
	}

	public void setPuntos(Set<Long> puntos) {
		this.puntos = puntos;
	}
	   
}
