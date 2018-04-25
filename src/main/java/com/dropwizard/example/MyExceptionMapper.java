package com.dropwizard.example;

import io.dropwizard.jersey.validation.JerseyViolationException;
import io.dropwizard.jersey.validation.JerseyViolationExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionMapper implements ExceptionMapper<JerseyViolationException> {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(JerseyViolationExceptionMapper.class);

  public Response toResponse(final JerseyViolationException exception) {
    LOGGER.debug("Handled by custom exception mapper", exception);
    return Response.status(409).build();
  }
}
