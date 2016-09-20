/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.Transaccion;
import com.gamasoft.hps.sab.domain.User;
import com.gamasoft.hps.sab.exception.RepositoryException;

/**
*
* @author wospino
*/
public interface UserRepository extends Repository<User>{

  /**
   * 
   * @param email
   * @return User. One element or null. The email must be unique
   */
  User getUserByEmail(final String email);

  /**
   * 
   * @param username
   * @return User. One element or null. The must should be unique
   */
  User getUserByUsername(final String username);

void update(User user) throws RepositoryException;

List<Transaccion> getTransaccionUser(long idUser);

List<User> getAllByGrupo(Long grupo);

List<User> getAllByCliente(Long idCliente);

List<User> getAllinactivos();
//se verifica que el correo no este en la tabla cliente antes de crear un usuario, para evitar crear un usuario con un email que ya existe
boolean verificarcorreo(String mail);


  
}
