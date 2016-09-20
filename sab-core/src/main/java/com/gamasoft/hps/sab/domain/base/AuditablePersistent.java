package com.gamasoft.hps.sab.domain.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * 
 * @author jose.muguerza
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AuditablePersistent extends Persistent {
	private AuditInfo auditInfo;

	public AuditablePersistent() {
		this.auditInfo = new AuditInfo();
	}

	public AuditInfo getAuditInfo() {
		return this.auditInfo;
	}

	@PrePersist
	protected void persistAuditInfo() {
		this.auditInfo.setCreatedBy(AuditContext.getUser());
		this.auditInfo.setCreatedOn(new Date());
	}

	@PreUpdate
	protected void updateAuditInfo() {
		this.auditInfo.setUpdatedBy(AuditContext.getUser());
		this.auditInfo.setUpdatedOn(new Date());
	}
}