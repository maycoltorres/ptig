package com.gamasoft.hps.sab.exception;

/**
 * Repositories base exception.
 * @author leonardo.brambilla(at)globant.com
 *
 */
@SuppressWarnings("serial")
public class RepositoryException extends Exception {

  public RepositoryException() {
    super();
  }

  public RepositoryException(String message) {
    super(message);
  }

  public RepositoryException(Exception e) {
    super(e);
  }

  
}
