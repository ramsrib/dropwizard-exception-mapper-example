package com.dropwizard.example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {

  UserDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  List<User> findAll() {
    return list(criteria());
  }

  User save(User user) {
    return persist(user);
  }
}
