package com.gamasoft.hps.sab.exception;

/**
 * Base Exception class for Datasource's exception handling.
 * 
 * @author leonardo.brambilla(at)globant.com
 *
 */
@SuppressWarnings("serial")
public class DatasourceException extends Exception {

  public DatasourceException() {
    super();
  }

  public DatasourceException(String message) {
    super(message);
  }

  public DatasourceException(Throwable cause) {
    super(cause);
  }

  public DatasourceException(String message, Throwable cause) {
    super(message, cause);
  }
}
