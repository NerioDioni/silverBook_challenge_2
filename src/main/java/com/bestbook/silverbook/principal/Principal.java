package com.bestbook.silverbook.principal;


import com.bestbook.silverbook.model.DatosBusqueda;
import com.bestbook.silverbook.model.DatosLibro;
import com.bestbook.silverbook.service.ConsumoApi;
import com.bestbook.silverbook.service.ConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "https://gutendex.com/books/?ids=1513,1514";

    public Principal() {}

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = "\n"+"""
                    1 - Buscar libro por id
                    2 - Buscar episodios
                    3 - Mostrar series buscadas
                    4 - Buscar series por titulo
                    5 - Top 5 mejores series
                    6 - Buscar Series por categoría
                    7 - filtrar series por temporadas y evaluación
                    8 - Buscar episodios por titulo
                    9 - Top 5 episodios por Serie
                    0 - Salir
                    """;

            System.out.println(menu);
            try{
                opcion = teclado.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Ocurrió un error:se esperaba un numero de tipo entero ");
                System.out.println(e);
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
                    break;
                case 3:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 4:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 5:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 6:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 7:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 8:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 9:
                    System.out.println("opcion #:"+opcion);
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }
    public void buscarLibroApi(){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        ConvierteDatos conversor=new ConvierteDatos();
        var json=consumoApi.obtenerDatos(URL_BASE);
        System.out.println("json:"+json);

        try {
            jsonNode= objectMapper.readTree(json);
            var nodoResultado=jsonNode.get("results").toString();
            System.out.println("result a string:"+nodoResultado);
            DatosBusqueda libros=conversor.obtenerDatos(json, DatosBusqueda.class);
            System.out.println(libros);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

}
