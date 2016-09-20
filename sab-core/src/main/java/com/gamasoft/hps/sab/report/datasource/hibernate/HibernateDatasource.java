/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.report.datasource.hibernate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.gamasoft.hps.sab.domain.Widget;
import com.gamasoft.hps.sab.domain.WidgetField;
import com.gamasoft.hps.sab.domain.WidgetFilter;
import com.gamasoft.hps.sab.report.FilterValueDto;
import com.gamasoft.hps.sab.report.ResultDto;
import com.gamasoft.hps.sab.report.datasource.Datasource;
/**
 *
 * @author vascordoba
 */
public class HibernateDatasource extends JdbcDaoSupport implements Datasource{

    @SuppressWarnings("unused")
	private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    public ResultDto execute(Widget widget, List<FilterValueDto> filters, ApplicationContext ctx) {
        
        ResultDto results=new ResultDto();
        try {
            String query=this.replaceFields(widget.getQuery(), widget.getFields());
            query=this.replaceFilters(query, widget.getFilters(),filters);
            System.err.println("QUERY");
            System.err.println(query);
            System.err.println("END QUERY");
            List<Map<String,Object>> rows=getJdbcTemplate().queryForList(query);
            results.setRows(rows);
            
            List<String> headers=this.getHeaders(widget.getFields());
            results.setHeader(headers);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return results;
    }
    
    private String replaceFields(String query, Set<WidgetField> fields) {
        String parsedQuery=new String(query);
        Iterator<WidgetField> itFields=fields.iterator();
        while(itFields.hasNext()){
            WidgetField field=itFields.next();
            parsedQuery=new String(parsedQuery.replace("#"+field.getField()+"#", field.getSyntax()+" as "+field.getAlias()));
            parsedQuery=new String(parsedQuery.replace("#"+field.getField()+"G#", field.getSyntax()));
        }
        return parsedQuery;
    }
    
    private String replaceFilters(String query, Set<WidgetFilter> filters, List<FilterValueDto> values){
        String parsedQuery=new String(query);
        Iterator<WidgetFilter> itFilter=filters.iterator();
        while(itFilter.hasNext()){
            WidgetFilter filter=itFilter.next();
            FilterValueDto filterValues=this.getFilterValues(values, filter.getFilter());
            if(filterValues==null || filterValues.getValues()==null || filterValues.getValues().isEmpty()){
                parsedQuery=new String(parsedQuery.replace("#"+filter.getFilter()+"#", ""));
            } else {
                parsedQuery=this.replaceFilterValues(parsedQuery, filter, filterValues.getValues());
            }
        }
        return parsedQuery;
    }
    
    private FilterValueDto getFilterValues(List<FilterValueDto> values, String search){
        if(values!=null && !values.isEmpty()){
            for(FilterValueDto filter:values){
                if(filter.getKey().equals(search)){
                    return filter;
                }
            }
        }
        return null;
    }
    
    
    
    private List<String> getHeaders(Set<WidgetField> headers){
        List<String> heads=new LinkedList<String>();
        Object [] headsArr = headers.toArray();
        Arrays.sort(headsArr);
        for(Object field:headsArr){
            heads.add(((WidgetField)field).getAlias());
        }
        return heads;
    }

    private String replaceFilterValues(String query, WidgetFilter filter, List<String> values) {
        String parsedQuery=new String(query);
        
        StringBuilder replacement=new StringBuilder();
        String rep=null;
        switch(filter.getType()){
            case STRING:
                
                if(values.size()>1){
                    for(String value:values){
                        replacement.append("'").append(value).append("'").append(",");
                    }
                    replacement.deleteCharAt(replacement.length()-1);
                }
                else{
                    replacement.append("'").append(values.get(0)).append("'");
                }
                rep=new String(filter.getSyntax().replace(":"+filter.getFilter(), replacement.toString()));
                parsedQuery=new String(parsedQuery.replace("#"+filter.getFilter()+"#", " AND "+rep));
                break;
            case INT:
            case DOUBLE:
                if(values.size()>1){
                    for(String value:values){
                        replacement.append(value).append(",");
                    }
                    replacement.deleteCharAt(replacement.length()-1);
                }
                else{
                    replacement.append(values.get(0));
                }
                rep=new String(filter.getSyntax().replace(":"+filter.getFilter(), replacement.toString()));
                parsedQuery=new String(parsedQuery.replace("#"+filter.getFilter()+"#", " AND "+rep));
                break;
            case DATE:
                if(values.size()==2){
                    rep=new String(filter.getSyntax().replace(":DATE_FROM", "'"+values.get(0)+"'"));
                    rep=new String(rep.replace(":DATE_TO", "'"+values.get(1)+"'"));
                }
                else if(values.size()==1){
                    rep=new String(filter.getSyntax().replace(":"+filter.getFilter(), "'"+values.get(0)+"'"));
                }
                parsedQuery=new String(parsedQuery.replace("#"+filter.getFilter()+"#", " AND "+rep));
                break;
        }
        
        
        return parsedQuery;
    }
}
