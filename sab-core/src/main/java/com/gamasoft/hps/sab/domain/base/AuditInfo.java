package com.gamasoft.hps.sab.domain.base;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author jose.muguerza
 */
@Embeddable
public class AuditInfo {
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	public String getCreatedBy() {
		return this.createdBy;
	}

	protected void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	protected void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	protected void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	protected void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}
