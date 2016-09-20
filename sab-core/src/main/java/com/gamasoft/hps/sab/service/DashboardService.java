/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.gamasoft.hps.sab.dto.DashboardCompleteDto;
import com.gamasoft.hps.sab.dto.DashboardDto;
import com.gamasoft.hps.sab.exception.ServiceException;

/**
 *
 * @author vascordoba
 */
public interface DashboardService {
    List<DashboardDto> getDashboards(long userId);
    void create(DashboardDto dashbaord, long userId);
    public DashboardCompleteDto getDashboardById(long dashboardId, ApplicationContext ctx);
    public void delete(long dashboardId) throws ServiceException;
    public void save(DashboardCompleteDto dashboard) throws ServiceException;
}
