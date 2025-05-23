package com.example.java_parser.repository;

import com.example.java_parser.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
  Optional<Film> findByUrl(String url);
}
