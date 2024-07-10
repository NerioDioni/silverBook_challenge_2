package com.bestbook.silverbook.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
