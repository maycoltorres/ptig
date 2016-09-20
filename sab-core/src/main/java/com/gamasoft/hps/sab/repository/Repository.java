package com.gamasoft.hps.sab.repository;

import java.util.List;

import com.gamasoft.hps.sab.domain.base.Persistent;
import com.gamasoft.hps.sab.exception.RepositoryException;


/**
 * Base repository operations.
 * 
 * @author adrian.konkolowicz
 * 
 * @param <T>
 */
public interface Repository<T extends Persistent> {
  
  void add(T t);
  void update(T t) throws RepositoryException;

  void remove(long id) throws RepositoryException;

  T getById(long id);

  List<T> getAll();



}
