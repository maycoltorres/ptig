/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.dto;

/**
 *
 * @author vascordoba
 */
public class WidgetDto {
    private long id;
    private String name;
    private String type;
    private String description;
	private String title;
	

    public WidgetDto() {
    }

    public WidgetDto(long id, String name, String type, String description, String title) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
    
    
}
