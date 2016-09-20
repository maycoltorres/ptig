/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.gamasoft.hps.sab.domain.base.AuditablePersistent;
import com.gamasoft.hps.sab.dto.DashboardDto;

/**
 *
 * @author vascordoba
 */
@Entity
@Table(name = "dashboards")
@DiscriminatorValue("dashboard")
public class Dashboard extends AuditablePersistent{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false, length = 150, unique = true)
	private String name;
    
    @Column(name = "description", nullable = false)
	private String description;
    
    @Column(name = "loadedByDefault")
	private Boolean loadedByDefault;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy="dashboard", fetch = FetchType.EAGER, orphanRemoval=true)
    private Set<DashboardWidget> DashboardWidgets;
    
    public Dashboard() {
    }

    public Dashboard(DashboardDto dto) {
        this.setId(dto.getId());
        this.name=dto.getName();
        this.description=dto.getDescription();
    }
    
    public Dashboard(String name, String description) {
        this.name = name;
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DashboardDto toDto(){
        DashboardDto dto=new DashboardDto();
        dto.setId(this.getId());
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setLoadedByDefault(this.getLoadedByDefault());
        
        return dto;
    }

	public Set<DashboardWidget> getDashboardWidgets() {
		return DashboardWidgets;
	}

	public void setDashboardWidgets(Set<DashboardWidget> dashboardWidgets) {
		DashboardWidgets = dashboardWidgets;
	}

	public Boolean getLoadedByDefault() {
		return loadedByDefault;
	}

	public void setLoadedByDefault(Boolean loadedByDefault) {
		this.loadedByDefault = loadedByDefault;
	}
}
