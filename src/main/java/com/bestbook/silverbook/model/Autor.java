package com.bestbook.silverbook.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private int fechaNacimiento;
    private int fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}
    public Autor(DatosAutor datosAutor) {
        this.nombre=datosAutor.nombre();
        this.fechaNacimiento=Integer.valueOf(datosAutor.fechaNacimiento());
        this.fechaMuerte=Integer.valueOf(datosAutor.fechaMuerte());
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(int fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(e -> e.setAutor(this));
        this.libros = libros;
    }

    //public void adicionarlibro(Libro libro){this.libros.add(libro);}
    @Override
    public String toString() {
        return  "Autor: \n"+
                "--Nombre de autor = " + nombre + "\n" +
                "--Anio de nacimiento = " + fechaNacimiento +"\n" +
                "--Anio de muerte = " + fechaMuerte+"\n";

    }
}
