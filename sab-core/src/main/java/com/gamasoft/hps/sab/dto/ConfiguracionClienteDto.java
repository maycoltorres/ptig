package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ConfiguracionClienteDto {

	private Boolean recetaDiferencialXPunto;
	private Boolean recetaDiferencialXCanal;
	private Boolean empaqueDiferencialXPunto;
	private Boolean empaqueDiferencialXCanal;
	private Boolean unicoImpuestoXCanales;
	
	public Boolean getRecetaDiferencialXPunto() {
		return recetaDiferencialXPunto;
	}
	public void setRecetaDiferencialXPunto(Boolean recetaDiferencialXPunto) {
		this.recetaDiferencialXPunto = recetaDiferencialXPunto;
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
	
}
