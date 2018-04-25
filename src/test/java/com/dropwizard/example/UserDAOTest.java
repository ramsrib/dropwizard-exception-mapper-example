package com.dropwizard.example;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDAOTest {

  @ClassRule
  public static final DropwizardAppRule<Config> RULE =
      new DropwizardAppRule<Config>(App.class, ResourceHelpers.resourceFilePath("test-config.yml"));

  final Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

  @Test
  public void shouldThrow409() {
    String userJson = "{\"name\": \"sri\", \"number\": 1}";

    Response response = post(userJson);
    assertThat(response.getStatus()).isEqualTo(200);

    response = post(userJson);
    // throw 409 on second time
    assertThat(response.getStatus()).isEqualTo(409);
  }

  private Response post(String json) {
    return client
        .target(String.format("http://localhost:%d/users/", RULE.getLocalPort()))
        .request()
        .post(Entity.json(json));
  }
}
