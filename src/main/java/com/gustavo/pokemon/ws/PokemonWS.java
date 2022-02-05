package com.gustavo.pokemon.ws;

import com.gustavo.pokemon.banco.Pokemon;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

public class PokemonWS {

    private static final String URL = "https://pokeapi.co/api/v2/pokemon/{id}";
    // buscando apenas nos pokemons da primeira geracao
    private static final int QTDE = 151;

    public List<String> buscaTodos() {

        ArrayList<String> listaRetorno = new ArrayList<>();

        for( int i=1 ; i<=QTDE ; i++ ) {
            Pokemon pokemon = busca(i);
            if (pokemon != null && pokemon.getName() != null )
                listaRetorno.add(pokemon.getName());
        }

        return listaRetorno;
    }

    public List<Pokemon> novaBuscaTodos() {
        ArrayList<Pokemon> retorno = new ArrayList<>();

        for( int i=1 ; i<=QTDE ; i++ ) {
            retorno.add(busca(i));
        }

        return retorno;
    }

    private Pokemon busca(int i) {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Pokemon> retornoRest = restTemplate
                .exchange(URL, HttpMethod.GET,entity,Pokemon.class,i);

        if(retornoRest.getBody() != null)
            return retornoRest.getBody();
        else
            return null;
    }
}
