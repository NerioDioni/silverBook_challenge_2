package com.bestbook.silverbook.model;

public enum Idioma {

    ES("es"),
    EN("en"),
    FR("fr"),
    IT("it"),
    PT("pt");

    private String idioma;

    Idioma (String idioma){
        this.idioma = idioma;
    }

    public static Idioma fromString (String clave){

        for (Idioma idioma : Idioma.values()){
            if (idioma.idioma.equalsIgnoreCase(clave)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("No se encontro el idioma indicado:" +
                clave+"\n Ingrese un dato valido");
    }
}
