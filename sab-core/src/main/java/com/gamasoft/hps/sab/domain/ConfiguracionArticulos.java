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
import com.gamasoft.hps.sab.dto.ArticuloDto;
import com.gamasoft.hps.sab.dto.ConfiguracionArticulosDto;
import com.gamasoft.hps.sab.dto.InventarioDto;

@Entity
@Table(name = "configuracionarticulos")
public class ConfiguracionArticulos extends Persistent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "usa_codigo_barras", nullable = true, length = 60)
	private Boolean usaCodigoBarras;

	@Column(name = "usa_unidad_alterna", nullable = true, length = 11)
	private Boolean usaUnidadAlterna;

	@Column(name = "usa_maximos_minimos", nullable = true, length = 20)
	private Boolean usaMaximosMinimos;
	
	@Column(name = "visto", nullable = true)
	private Boolean visto;
	
	@Column(name = "elementos_ocultos", nullable = true, length = 2000)
	private String elementosOcultos;
	
		
	public Boolean getUsaCodigoBarras() {
		return usaCodigoBarras;
	}

	public void setUsaCodigoBarras(Boolean usaCodigoBarras) {
		this.usaCodigoBarras = usaCodigoBarras;
	}

	public Boolean getUsaUnidadAlterna() {
		return usaUnidadAlterna;
	}

	public void setUsaUnidadAlterna(Boolean usaUnidadAlterna) {
		this.usaUnidadAlterna = usaUnidadAlterna;
	}

	public Boolean getUsaMaximosMinimos() {
		return usaMaximosMinimos;
	}

	public void setUsaMaximosMinimos(Boolean usaMaximosMinimos) {
		this.usaMaximosMinimos = usaMaximosMinimos;
	}
	
	public Boolean getVisto() {
		return visto;
	}

	public void setVisto(Boolean visto) {
		this.visto = visto;
	}

	public String getElementosOcultos() {
		return elementosOcultos;
	}

	public void setElementosOcultos(String elementosOcultos) {
		this.elementosOcultos = elementosOcultos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
	

	public ConfiguracionArticulos(){
		
	}
	
	public ConfiguracionArticulos(ConfiguracionArticulosDto dto) {

		this.usaCodigoBarras = dto.getUsaCodigoBarras();
		this.usaUnidadAlterna = dto.getUsaUnidadAlterna();
		this.usaMaximosMinimos = dto.getUsaMaximosMinimos();
		this.visto = dto.getVisto();
		this.elementosOcultos = dto.getElementosOcultos();
	}

	public ConfiguracionArticulosDto toDto() {

		ConfiguracionArticulosDto dto = new ConfiguracionArticulosDto();
		dto.setUsaCodigoBarras(this.getUsaCodigoBarras());
		dto.setUsaUnidadAlterna(this.getUsaUnidadAlterna());
		dto.setUsaMaximosMinimos(this.getUsaMaximosMinimos());
		dto.setVisto(this.getVisto());
		dto.setElementosOcultos(this.getElementosOcultos());

		return dto;
	}
}
