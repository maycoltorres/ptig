/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

/**
 *
 * @author vascordoba
 */
public class DashboardWidgetRequestDto {
    private Long widgetId;
    
    private Integer x;
    private Integer y;
    

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

	public DashboardWidgetRequestDto() {
    }

    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long widgetId) {
        this.widgetId = widgetId;
    }
    
}
