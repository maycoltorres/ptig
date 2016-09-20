/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.dto.DashboardWidgetDto;

/**
 *
 * @author vascordoba
 */
@Entity
@Table(name = "dashboard_widget")
@DiscriminatorValue("dashboard_widget")
public class DashboardWidget extends AuditablePersistent{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "x", nullable = false)
	private Integer x;
    
    @Column(name = "y", nullable = false)
	private Integer y;
    
    @Column(name = "h", nullable = false)
	private Integer h;
    
    @Column(name = "w", nullable = false)
	private Integer w;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dashboard_id", nullable = false)
    private Dashboard dashboard;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "widget_id", nullable = false)
    private Widget widget;
    
    public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	public DashboardWidget() {
    }

    public DashboardWidget(DashboardWidgetDto dto) {
        this.setX(dto.getX());
        this.setY(dto.getY());
        this.setH(dto.getH());
        this.setW(dto.getW());
    }
       
    public DashboardWidgetDto toDto(){
        DashboardWidgetDto dto = new DashboardWidgetDto();
        
        dto.setId(this.getId());
        dto.setX(this.getX());
        dto.setY(this.getY());
        dto.setH(this.getH());
        dto.setW(this.getW());
        
        return dto;
    }
}
