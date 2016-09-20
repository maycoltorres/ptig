/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.report.datasource;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.gamasoft.hps.sab.domain.Widget;
import com.gamasoft.hps.sab.report.FilterValueDto;
import com.gamasoft.hps.sab.report.ResultDto;

/**
 *
 * @author vascordoba
 */
public interface Datasource {
    public ResultDto execute(Widget widget, List<FilterValueDto> filters, ApplicationContext ctx);
}
