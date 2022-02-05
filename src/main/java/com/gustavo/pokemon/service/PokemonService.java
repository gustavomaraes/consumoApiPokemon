package com.gustavo.pokemon.service;

import com.gustavo.pokemon.banco.Pokemon;
import com.gustavo.pokemon.ws.PokemonWS;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PokemonService {

    PokemonWS ws;

    public PokemonService() {
        ws = new PokemonWS();
    }

    public JSONObject buscaPokemons(String entrada) {
        /*
        * 1 passo: montar objeto de retorno vazio
        * 2 passo: buscar todos os pokemons
        * 3 passo: filtrar de acordo com a entrada
        * 4 passo: dar o highlight no trecho localizado pela busca
        * passos 3 e 4 executam somente se existir entrada
        * */
        JSONObject json = new JSONObject();

        List<String> todos = ws.buscaTodos();
        List<String> filtrados = new ArrayList<>();

        if( entrada != null )
            filtrados.addAll(filtrar(todos, entrada));
        else
            filtrados.addAll(todos);

        List<JSONObject> retorno = preparaRetorno(filtrados, entrada);

        json.put("result",retorno);

        return json;
    }

    private List<String> filtrar(List<String> todos, String busca) {
        List<String> aux = new ArrayList<>();
        for( String pokemon : todos ) {
            if ( pokemon.contains(busca) )
                aux.add(pokemon);
        }
        return aux;
    }

    private List<JSONObject> preparaRetorno(List<String> filtrados, String busca) {
        List<JSONObject> retorno = new ArrayList<>();
        for( String pokemon : filtrados ) {
            JSONObject item = new JSONObject();
            item.put("name", pokemon);
            item.put("highlight", destaca(pokemon, busca));
            retorno.add(item);
        }
        return retorno;
    }

    private String destaca(String pokemon, String busca) {
        /*
        * retorno com 5 "pedacos"
        * 1 - pedaco do nome antes de encontrar a busca(pode ser null)
        * 2 - a tag <pre>
        * 3 - busca
        * 4 - fechando a tag </pre>
        * 5 - pedaco do nome depois de encontrar a busca(pode ser null)
        * */
        int index;
        int tamBusca = 0;
        if (busca == null) {
            index = -1;
        } else {
            index = pokemon.indexOf(busca);
            tamBusca = busca.length();
        }
        return index == -1 ? pokemon :
                pokemon.substring(0, index) +
                        "<pre>" +
                        busca +
                        "</pre>" +
                        pokemon.substring(index+tamBusca);
    }

    public List<Pokemon> novaBuscaPokemons(String nome){
        List<Pokemon> retorno;
        List<Pokemon> busca;
        busca = ws.novaBuscaTodos();

        retorno = filtrarPokemon(busca, nome);
        return retorno;
    }

    private List<Pokemon> filtrarPokemon(List<Pokemon> lista, String nome) {
        List<Pokemon> retorno = new ArrayList<>();
        if ( nome != null ){
            for( Pokemon pokemon : lista ) {
                if (pokemon.getName().equals(nome))
                    retorno.add(pokemon);
            }
        } else {
            retorno.addAll(lista);
        }
        return retorno;
    }
}
