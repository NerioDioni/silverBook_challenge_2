package com.bestbook.silverbook.repository;

import com.bestbook.silverbook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro,Long> {
}
