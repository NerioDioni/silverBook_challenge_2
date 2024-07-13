package com.bestbook.silverbook.model;

import jakarta.persistence.*;

import java.util.Optional;
@Entity
@Table(name = "libros")
public class Libro {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private int numeroDescargas;

    public Libro(DatosLibro datosLibro) {
        //Autor autor1 = new Autor(datosLibro.autores().get(0));
        //this.autor = autor;
        this.numeroDescargas = Integer.valueOf(datosLibro.numeroDescargas());
        this.idioma = Idioma.fromString(datosLibro.idiomas().get(0));

        this.titulo = datosLibro.titulo();
    }

    public Libro() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
    @Override
    public String toString() {
        return  "Libro: \n"+
                "--Titulo = " + titulo + "\n" +
                "--Idioma = " + idioma +"\n" +
                "--Numero de veces descargado = " + numeroDescargas +"\n";

    }

}
