package com.gustavo.pokemon.ws;

import com.gustavo.pokemon.banco.Pokemon;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

public class PokemonWS {

    public ArrayList<String> buscaTodos(int qtde) {

        String url = "https://pokeapi.co/api/v2/pokemon/{id}";
        ArrayList<String> listaRetorno = new ArrayList<>();

        for( int i=1 ; i<=qtde ; i++ ) {

            RestTemplateBuilder builder = new RestTemplateBuilder();
            RestTemplate restTemplate = builder.build();
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Application");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Pokemon> retorno = restTemplate
                    .exchange(url, HttpMethod.GET,entity,Pokemon.class,i);

            Pokemon pokemon = retorno.getBody();

            listaRetorno.add(pokemon.getName());
        }
        return listaRetorno;
    }
}
