/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author vascordoba
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardDto {
    private Long id;
    private String name;
    private String description;
    private Boolean loadedByDefault;
    private List<DashboardWidgetDto> dashboardWidgets;

    public List<DashboardWidgetDto> getDashboardWidgets() {
		return dashboardWidgets;
	}

	public void setDashboardWidgets(List<DashboardWidgetDto> dashboardWidgets) {
		this.dashboardWidgets = dashboardWidgets;
	}

	public DashboardDto() {
    }

    public DashboardDto(Long id, String name, String description, Boolean loadedByDefault) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.loadedByDefault = loadedByDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Boolean getLoadedByDefault() {
		return loadedByDefault;
	}

	public void setLoadedByDefault(Boolean loadedByDefault) {
		this.loadedByDefault = loadedByDefault;
	}
    
}
