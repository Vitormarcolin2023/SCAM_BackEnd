package org.scam.model.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.scam.controller.classes.Endereco;

import java.net.URI;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscarCEP {

    Endereco endereco = new Endereco();

    public Endereco getEndereco (String cep) {
        try {

            String urlPesquisa = "https://viacep.com.br/ws/" + cep + "/json/";

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlPesquisa))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            endereco = mapper.readValue(response.body(), Endereco.class);
            System.out.println(endereco.getLocalidade());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return endereco;
    }
}
