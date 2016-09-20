package com.gamasoft.hps.sab.exception;

import org.json.JSONException;

/**
 * Base exception class for Decorators.
 * 
 * @author leonardobrambilla
 *
 */
@SuppressWarnings("serial")
public class DecoratorException extends Exception {

  public DecoratorException(String errorMsg, JSONException e) {
    super(errorMsg, e);
  }

  public DecoratorException() {
    super();
  }

  public DecoratorException(String msg) {
    super(msg);
  }
}
