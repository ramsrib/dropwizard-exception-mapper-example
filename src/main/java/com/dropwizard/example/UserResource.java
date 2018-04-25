package com.dropwizard.example;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  private final UserDAO userDAO;

  UserResource(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @GET
  @UnitOfWork
  public List<User> getUser() {
    return userDAO.findAll();
  }

  @POST
  @UnitOfWork
  public User createUser(User user) {
    return userDAO.save(user);
  }
}
