/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.gamasoft.hps.sab.dto.DashboardWidgetRequestDto;
import com.gamasoft.hps.sab.dto.WidgetDto;
import com.gamasoft.hps.sab.exception.ServiceException;
import com.gamasoft.hps.sab.report.ResultDto;

/**
 *
 * @author vascordoba
 */
public interface WidgetService {
    public List<WidgetDto> getWidgets() throws ServiceException;
    public ResultDto addWidgetToDashboard(DashboardWidgetRequestDto req, ApplicationContext ctx) throws ServiceException;
}
