package com.dropwizard.example;

import io.dropwizard.jersey.errors.ErrorMessage;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceExceptionMapper.class);

  public Response toResponse(final PersistenceException exception) {
    LOGGER.debug("Persistence failure", exception);
    Response response;
    if (exception.getCause() instanceof ConstraintViolationException) {
      final String details =
          "Violated "
              + ((ConstraintViolationException) exception.getCause()).getConstraintName()
              + " constraint.";
      response =
          Response.status(CONFLICT)
              .type(MediaType.APPLICATION_JSON_TYPE)
              .entity(
                  new ErrorMessage(
                      CONFLICT.getStatusCode(), "Constraint violation failure", details))
              .build();
    } else {
      response =
          Response.status(INTERNAL_SERVER_ERROR)
              .type(MediaType.APPLICATION_JSON_TYPE)
              .entity(
                  new ErrorMessage(INTERNAL_SERVER_ERROR.getStatusCode(), "Persistence failure"))
              .build();
    }

    return response;
  }
}
