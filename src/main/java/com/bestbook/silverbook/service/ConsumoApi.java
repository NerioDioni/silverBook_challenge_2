package com.bestbook.silverbook.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
    public String obtenerDatos(String url){
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        String json=null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IllegalArgumentException e) {
            System.out.println("Ingreso inadecuado de caracteres no validos, ingrese un nombre valido");
            return json;
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        json = response.body();
        //System.out.println("response:"+response);
        return json;
    }
}
