/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.domain;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.chart.Chart;
import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.dto.WidgetDto;
/**
 *
 * @author vascordoba
 */
@Entity
@Table(name = "widget")
@DiscriminatorValue("widget")
public class Widget extends Persistent{
    private static final long serialVersionUID = 1L;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
	@Column(name = "type", nullable = true, length = 30)
    private Chart type;
    
	@Column(name = "description", nullable = true)
    private String description;
	
	@Column(nullable = true, length = 100)
	private String title;
    
    @Column(nullable = true)
	private String query;
    
    @Column(nullable = true, length = 50)
	private String report;
    
    @Column(nullable = true)
	private String datasource;
    
    @Column(name = "defaultX", nullable = true)
	private int defaultX;
    
    @Column(name = "defaultY", nullable = true)
	private int defaultY;
    
    @Column(name = "defaultH", nullable = true)
	private int defaultH;
    
    @Column(name = "defaultW", nullable = true)
	private int defaultW;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "widget")
    private Set<WidgetFilter> filters;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "widget")
    private Set<WidgetField> fields;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="widget")
    private Set<DashboardWidget> dashboardWidgets;
    
    public Widget() {
    }

    public Widget(String name, Chart type, String description, String title, String query, String report, String datasource) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.title = title;
        this.query = query;
        this.report = report;
        this.datasource = datasource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chart getType() {
        return type;
    }

    public void setType(Chart type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
    
    public Integer  getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(Integer defaultX) {
        this.defaultX = defaultX;
    }

    public Integer getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(Integer defaultY) {
        this.defaultY = defaultY;
    }
    
    public Integer getDefaultH() {
        return defaultH;
    }

    public void setDefaultH(Integer defaultH) {
        this.defaultH = defaultH;
    }
    
    public Integer getDefaultW() {
        return defaultW;
    }

    public void setDefaultW(Integer defaultW) {
        this.defaultW = defaultW;
    }
    
    public Set<WidgetFilter> getFilters() {
        return filters;
    }

    public void setFilters(Set<WidgetFilter> filters) {
        this.filters = filters;
    }

    public Set<WidgetField> getFields() {
        return fields;
    }

    public void setFields(Set<WidgetField> fields) {
        this.fields = fields;
    }
    
    public WidgetDto toDto(){
        WidgetDto w  = new WidgetDto(this.getId(),this.name,this.type.name(),this.description,title);
        
        return w;
    }

    @Override
    public String toString() {
        StringBuilder ret=new StringBuilder("WIDGET [");
        ret.append("name: ").append(name).append(", ");
        ret.append("description: ").append(description).append(", ");
        ret.append("title: ").append(title).append(", ");
        ret.append("type: ").append(type).append(", ");
        ret.append("query: ").append(query).append(", ");
        ret.append("datasource: ").append(datasource).append(", ");
        ret.append("defaultX: ").append(defaultX).append(", ");
        ret.append("defaultY: ").append(defaultY).append(", ");
        ret.append("defaultH: ").append(defaultH).append(", ");
        ret.append("defaultW: ").append(defaultW).append(", ");
        
        ret.append("filters: ");
        if(filters!=null && !filters.isEmpty()){
            ret.append(Arrays.toString(filters.toArray()));
        }
        ret.append(" ");
        ret.append("fields: ");
        if(fields!=null && !fields.isEmpty()){
            ret.append(Arrays.toString(fields.toArray()));
        }
        ret.append(" ");
        ret.append("]");
        return ret.toString();
    }

	public Set<DashboardWidget> getDashboardWidgets() {
		return dashboardWidgets;
	}

	public void setDashboardWidgets(Set<DashboardWidget> dashboardWidgets) {
		this.dashboardWidgets = dashboardWidgets;
	}
    
    
}
