package com.bestbook.silverbook.principal;


import com.bestbook.silverbook.model.*;
import com.bestbook.silverbook.repository.AutorRepository;
import com.bestbook.silverbook.service.ConsumoApi;
import com.bestbook.silverbook.service.ConvierteDatos;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private AutorRepository autorRepositorio;
    private List<Autor> autores;
    private List<Libro> libros;


    public Principal() {}

    public Principal(AutorRepository autorR) {
        this.autorRepositorio=autorR;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu ="""
                    +++++++++++++++MENU+++++++++++++++
                    1 - Buscar libro por titulo en Api
                    2 - Mostrar libros buscados
                    3 - Mostrar libros por Lenguaje
                    4 - Mostrar Autores
                    5 - Buscar autores vivos en determinado anio
                    0 - Salir
                    ++++++++++++++++++++++++++++++++++
                    """;

            System.out.println(menu);
            try{
                opcion = teclado.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Ocurrió un error:se esperaba un numero de tipo entero ");
                opcion=-1;
            }
            teclado.nextLine();


            switch (opcion) {
                case 1:
                    System.out.println("opcion #:"+opcion);
                    buscarLibroApi();

                    break;
                case 2:
                    System.out.println("opcion #:"+opcion);
                    mostrarlibros();
                    break;
                case 3:
                    System.out.println("opcion #:"+opcion);
                    buscarlibrosPorIdioma();
                    break;
                case 4:
                    System.out.println("opcion #:"+opcion);
                    mostrarAutores();
                    break;
                case 5:
                    System.out.println("opcion #:"+opcion);
                    autoresVivos();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void mostrarlibros() {
        llamarAutores();
        System.out.println("------Historial de libros buscados-------\n"+"Agrupados por autor");
        autores.forEach(s ->System.out.println(s.getLibros()+"\n"+
                        s+
                        "*************************************"));

    }
    public void buscarLibroApi(){
        ObjectMapper objectMapper = new ObjectMapper();
        DatosBusqueda datosBusqueda;
        ConvierteDatos conversor=new ConvierteDatos();
        String buscar;
        Autor autor;
        Libro libro;
        Optional<DatosLibro> libroEncontrado;
        llamarAutores();
        //********************************
        System.out.println("Ingrese nombre de libro a buscar");
        buscar=teclado.nextLine();
        var json=consumoApi.obtenerDatos(URL_BASE+buscar.replace(" ", "+"));

        if (json==null){
            libroEncontrado=Optional.empty();
        }else {
            datosBusqueda = conversor.obtenerDatos(json, DatosBusqueda.class);
            libroEncontrado = datosBusqueda.librosEncontrados().stream().findFirst();
        }
        if (libroEncontrado.isPresent()){
            System.out.println(libroEncontrado);
            //buscando autor existente en base de datos
            Optional<Autor> autorOptional = autores.stream()
                    .filter(s -> s.getNombre().toLowerCase().
                            contains(libroEncontrado.get().autores().get(0)
                                    .nombre().toLowerCase())).findFirst();

            if(autorOptional.isPresent()){
                autor=autorOptional.get();
            }else {
            autor= new Autor(libroEncontrado.get().autores().get(0));
            }
            libro=new Libro(libroEncontrado.get());
            List<Libro> libros=new ArrayList<>();
            libros.add(libro);
            autor.setLibros(libros);
            try {
                autorRepositorio.save(autor);
                System.out.println("Libro guardado exitosamente:");
                System.out.println(autor.getLibros().get(0)+""+autor);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Error: libro ya está almacenado en la base de datos, intenta con otro libro.\n");
            }
        }else {
            System.out.println("libro no encontrado");
        }

    }
    public void llamarAutores(){
        autores=autorRepositorio.findAll();

    }
    public void autoresVivos(){
        System.out.println("Ingrese anio para buscar");

        try {
            int buscar=teclado.nextInt();
            autores = autorRepositorio.autoresVivos(buscar);
            System.out.println("------Autores vivos segun anio-------");
            if (autores.isEmpty()){
                System.out.println("no se encontraron autores vivos en ese anio");
            }else {
                autores.forEach(s ->
                        System.out.println(s +
                                "***********************************"));
            }

             }catch ( InputMismatchException e) {
             System.out.println("Ingreso caracteres no validos, ingrese un anio");

        }

    }
    public void buscarlibrosPorIdioma(){
        System.out.println("""
                Escriba el la clave segun el idioma a buscar:
                ES: Español
                EN: Ingles
                FR: Frances
                """);
        String buscarIdioma=teclado.nextLine();
        try {
            Idioma idioma = Idioma.fromString(buscarIdioma);
            libros = autorRepositorio.librosPorIdioma(idioma);
            System.out.println("------Libro segun idioma-------");
            if (libros.isEmpty()){
                System.out.println("no se encontraron libros con ese idioma");
            }else {
                libros.forEach(l ->
                        System.out.println(l.getAutor() + "" +
                                l +
                                "***********************************"));
                }
        }catch (IllegalArgumentException e) {
            System.out.println("Ingreso inadecuado de caracteres no validos, ingrese una clave valida");
        }

    }
    private void mostrarAutores() {
        llamarAutores();
        System.out.println("------Autores registrados-------\n"+"Ordenados por nombre");
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(a ->System.out.println(a+
                        "*************************************"));

    }
}
