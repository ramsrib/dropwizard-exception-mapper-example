package com.dropwizard.example;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_info")
class User {

  @Id @GeneratedValue UUID id;

  @NotNull
  @Column(unique = true)
  String name;

  Integer number;
}
