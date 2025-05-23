package com.example.java_parser.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "directors")
public class Director {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @ManyToMany(mappedBy = "directors")
  private Set<Film> films = new HashSet<>();

  protected Director() {
  }

  public Director(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Film> getFilms() {
    return films;
  }

  public void setName(String name) {
    this.name = name;
  }
}
