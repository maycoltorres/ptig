package com.gamasoft.hps.sab.domain.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Elmer Gomez
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Persistent implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
	  this.id = id;
	}
}
