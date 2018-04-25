package com.dropwizard.example;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<Config> {

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void initialize(Bootstrap<Config> bootstrap) {
    bootstrap.addBundle(hibernate);
  }

  @Override
  public void run(Config configuration, Environment environment) {
    final UserDAO dao = new UserDAO(hibernate.getSessionFactory());
    //    environment.jersey().getResourceConfig().register(MyExceptionMapper.class);
    environment.jersey().register(com.dropwizard.example.MyExceptionMapper.class);
    environment.jersey().register(new UserResource(dao));
  }

  private final HibernateBundle<Config> hibernate =
      new HibernateBundle<Config>(User.class) {
        public DataSourceFactory getDataSourceFactory(Config configuration) {
          return configuration.getDatabase();
        }
      };
}
