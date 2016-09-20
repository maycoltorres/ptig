/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import com.gamasoft.hps.sab.report.ResultDto;

/**
 *
 * @author vascordoba
 */
public class DashboardWidgetDto {
	private DashboardDto dashboard;
	private WidgetDto widget; 
	private ResultDto result;
	
	public DashboardDto getDashboard() {
		return dashboard;
	}
	public void setDashboard(DashboardDto dashboard) {
		this.dashboard = dashboard;
	}
	public WidgetDto getWidget() {
		return widget;
	}
	public void setWidget(WidgetDto widget) {
		this.widget = widget;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public ResultDto getResult() {
		return result;
	}
	public void setResult(ResultDto result) {
		this.result = result;
	}

	private Long id;
	
	private int x; 
	private int y; 
	private int h;
	private int w;
	
}
