/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.report;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author vascordoba
 */
public class FilterValueDto implements Comparable<FilterValueDto>{
    private String key;
    private Types type;
    private Integer position;
    private List<String> values;

    public FilterValueDto() {
        this.values=new ArrayList<String>();
    }

    public FilterValueDto(String key, Types type, Integer position, List<String> values) {
        this.key = key;
        this.type = type;
        this.position =position;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
    
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public int compareTo(FilterValueDto o) {
        int comparePosition = o.getPosition(); 
 
		//ascending order
		return this.position - comparePosition;
        //descending order
		//return comparePosition - this.position;
    }
    
    
}
