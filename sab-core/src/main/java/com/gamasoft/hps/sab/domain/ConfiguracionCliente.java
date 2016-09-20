package com.gamasoft.hps.sab.domain;
/**
 * 
 * @author wospino
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.ConfiguracionClienteDto;

@Entity
@Table(name = "configuracioncliente")
public class ConfiguracionCliente extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "recetaDiferencialXPunto", nullable = true)
	private Boolean recetaDiferenciaXPunto;

	@Column(name = "recetaDiferencialXCanal", nullable = true)
	private Boolean recetaDiferencialXCanal;

	@Column(name = "empaqueDiferencialXPunto", nullable = true)
	private Boolean empaqueDiferencialXPunto;
	
	@Column(name = "empaqueDiferencialXCanal", nullable = true)
	private Boolean empaqueDiferencialXCanal;
	
	@Column(name = "unicoImpuestoXCanales", nullable = true)
	private Boolean unicoImpuestoXCanales;
		
	@OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
	
	public ConfiguracionCliente(){
		
	}
	
	public ConfiguracionCliente(ConfiguracionClienteDto dto) {

		this.recetaDiferenciaXPunto= dto.getRecetaDiferencialXPunto();
		this.recetaDiferencialXCanal = dto.getRecetaDiferencialXCanal();
		this.empaqueDiferencialXPunto = dto.getEmpaqueDiferencialXPunto();
		this.empaqueDiferencialXCanal = dto.getEmpaqueDiferencialXCanal();
		this.unicoImpuestoXCanales = dto.getUnicoImpuestoXCanales();
	}

	public ConfiguracionClienteDto toDto() {

		ConfiguracionClienteDto dto = new ConfiguracionClienteDto();
		dto.setRecetaDiferencialXPunto(this.recetaDiferenciaXPunto);
		dto.setRecetaDiferencialXCanal(this.recetaDiferencialXCanal);
		dto.setEmpaqueDiferencialXPunto(this.empaqueDiferencialXPunto);
		dto.setEmpaqueDiferencialXCanal(this.empaqueDiferencialXCanal);
		dto.setUnicoImpuestoXCanales(this.unicoImpuestoXCanales);
		
		return dto;
	}

	public Boolean getRecetaDiferenciaXPunto() {
		return recetaDiferenciaXPunto;
	}

	public void setRecetaDiferenciaXPunto(Boolean recetaDiferenciaXPunto) {
		this.recetaDiferenciaXPunto = recetaDiferenciaXPunto;
	}

	public Boolean getRecetaDiferencialXCanal() {
		return recetaDiferencialXCanal;
	}

	public void setRecetaDiferencialXCanal(Boolean recetaDiferencialXCanal) {
		this.recetaDiferencialXCanal = recetaDiferencialXCanal;
	}

	public Boolean getEmpaqueDiferencialXPunto() {
		return empaqueDiferencialXPunto;
	}

	public void setEmpaqueDiferencialXPunto(Boolean empaqueDiferencialXPunto) {
		this.empaqueDiferencialXPunto = empaqueDiferencialXPunto;
	}

	public Boolean getEmpaqueDiferencialXCanal() {
		return empaqueDiferencialXCanal;
	}

	public void setEmpaqueDiferencialXCanal(Boolean empaqueDiferencialXCanal) {
		this.empaqueDiferencialXCanal = empaqueDiferencialXCanal;
	}
	
	public Boolean getUnicoImpuestoXCanales() {
		return unicoImpuestoXCanales;
	}

	public void setUnicoImpuestoXCanales(Boolean unicoImpuestoXCanales) {
		this.unicoImpuestoXCanales = unicoImpuestoXCanales;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
