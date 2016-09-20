/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.domain;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.report.Types;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vascordoba
 */
@Entity
@Table(name = "widget_fields")
@DiscriminatorValue("widgetField")
public class WidgetField extends Persistent implements Comparable<WidgetField>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "widget_id", nullable = false)
    private Widget widget;
    
    @Column(nullable = false, length = 30)
    private String field;
    
    @Column(nullable = false, length = 30)
    private Types type;
    
    @Column(name = "column_syntax", nullable = false, length = 100)
    private String syntax;
    
    @Column(name = "column_alias", nullable = false, length = 100)
    private String alias;
    
    @Column(nullable = false)
    private Integer position;

    public WidgetField() {
    }

    public WidgetField(Widget widget, String field, Types type, String syntax, String alias, Integer position) {
        this.widget = widget;
        this.field = field;
        this.type = type;
        this.syntax = syntax;
        this.alias = alias;
        this.position = position;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

     @Override
    public String toString() {
        StringBuilder ret=new StringBuilder("WIDGET_FILTER [");
        ret.append("field: ").append(field).append(", ");
        ret.append("type: ").append(type.name()).append(", ");
        ret.append("position: ").append(position).append(", ");
        ret.append("syntax: ").append(syntax).append(", ");
        ret.append("alias: ").append(alias);
        ret.append("]\n");
        return ret.toString();
    }

    public int compareTo(WidgetField o) {
        int comparePosition = o.getPosition();
        return this.position - comparePosition;
    }
}
