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

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.report.Types;
/**
 *
 * @author vascordoba
 */
@Entity
@Table(name = "widget_filters")
@DiscriminatorValue("widgetFilter")
public class WidgetFilter extends Persistent{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "widget_id", nullable = false)
    private Widget widget;
    
    @Column(nullable = false, length = 30)
    private String filter;
    
    @Column(nullable = false, length = 30)
    private Types type;
    
    @Column(name="column_syntax",nullable = false, length = 200)
    private String syntax;
    
    @Column(nullable = false)
    private Integer position;

    public WidgetFilter() {
    }

    public WidgetFilter(Widget widget, String filter, Types type, String syntax, Integer position) {
        this.widget = widget;
        this.filter = filter;
        this.type = type;
        this.syntax = syntax;
        this.position = position;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder ret=new StringBuilder("WIDGET_FILTER [");
        ret.append("filter: ").append(filter).append(", ");
        ret.append("type: ").append(type.name()).append(", ");
        ret.append("syntax: ").append(syntax).append(", ");
        ret.append("position: ").append(position);
        ret.append("]\n");
        return ret.toString();
    }
    
}
