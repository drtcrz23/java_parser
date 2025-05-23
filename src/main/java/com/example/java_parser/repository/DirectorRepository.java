package com.example.java_parser.repository;

import com.example.java_parser.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
  Optional<Director> findByNameIgnoreCase(String name);
}
