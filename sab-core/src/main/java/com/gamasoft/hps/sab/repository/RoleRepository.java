/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.repository;

import com.gamasoft.hps.sab.domain.Rol;

/**
 *
 * @author vascordoba
 */
public interface RoleRepository extends Repository<Rol>{
    Rol getRoleByName(final String name) ;
}
