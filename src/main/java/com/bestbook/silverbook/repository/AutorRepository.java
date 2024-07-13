package com.bestbook.silverbook.repository;

import com.bestbook.silverbook.model.Autor;
import com.bestbook.silverbook.model.Idioma;
import com.bestbook.silverbook.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    //@Query("SELECT a FROM Autor a JOIN a.libros e WHERE e.titulo ILIKE %:nombreEpisodio%")
    //List<Episodio> episodiosPorNombre(String nombreEpisodio);
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND a.fechaMuerte >= :anio")
    List<Autor> autoresVivos(int anio);
    @Query("SELECT l FROM Libro l WHERE l.idioma = %:idiomab%")
    List<Libro> librosPorIdioma(Idioma idiomab);

    @Query("SELECT l FROM Libro l WHERE l.titulo ILIKE %:titulo%")
    List<Libro> librosPorTitulo(String titulo);

    //@Query("SELECT l FROM Autor a JOIN a.libros l WHERE l.idioma ILIKE %:idioma%")
    //List<Libro> librosPorIdioma(Idioma idioma);
    /*@Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:idiomab%")
    List<Libro> librosPorIdioma(Idioma idiomab);*/
}
