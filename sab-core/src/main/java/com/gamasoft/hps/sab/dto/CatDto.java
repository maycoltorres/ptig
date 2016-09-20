package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author wospino
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CatDto {

	private static final long serialVersionUID = 1L;
	
	private long idCic;
	private long idArticulo;
	private long idTarifa;
	public long getIdCic() {
		return idCic;
	}
	public void setIdCic(long idCic) {
		this.idCic = idCic;
	}
	public long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public long getIdTarifa() {
		return idTarifa;
	}
	public void setIdTarifa(long idTarifa) {
		this.idTarifa = idTarifa;
	}

}
