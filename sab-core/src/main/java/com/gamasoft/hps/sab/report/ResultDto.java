/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gamasoft.hps.sab.chart.Chart;

/**
 *
 * @author vascordoba
 */
public class ResultDto {
    private Long id;
    private String title;
    private Chart type;
    private Integer x;
    private Integer y;
    private Integer w;
    private Integer h;
    private List<String> header;
    private List<Map<String,Object>> rows;
    

    public ResultDto() {
        this.header = new ArrayList<String>();
        this.rows=new ArrayList<Map<String,Object>>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Chart getType() {
        return type;
    }

    public void setType(Chart type) {
        this.type = type;
    }

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

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
