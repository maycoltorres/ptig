/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.List;
/**
 *
 * @author vascordoba
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DashboardCompleteDto extends DashboardDto{
    private List<DashboardFilterDto> filters;
    
    public DashboardCompleteDto() {
        super();
    }

    public DashboardCompleteDto(DashboardDto d) {
        super(d.getId(), d.getName(), d.getDescription(), d.getLoadedByDefault());
        setDashboardWidgets(d.getDashboardWidgets());
    }
    
    public DashboardCompleteDto(Long id, String name, String description, Boolean loadedByDefault, List<DashboardFilterDto> filters, List<DashboardWidgetDto> widgets) {
        super(id, name, description, loadedByDefault);
        this.filters = filters;
        this.setDashboardWidgets(widgets);
    }

    public List<DashboardFilterDto> getFilters() {
        return filters;
    }

    public void setFilters(List<DashboardFilterDto> filters) {
        this.filters = filters;
    }

}
