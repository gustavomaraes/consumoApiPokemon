# Consumo da API do pokemon

## TODO list

- refatorar a busca, p/ retornar um obj to tipo pokemon;
- inserir mais dados no obj;
- gravar no banco os dados consumidos da API;
- realizar as buscas no banco;
- criar rotina de atualizacao do banco, comparando dados do banco com dados retornados pela API;
- ampliar qtde de endpoints de buscas, permitindo buscar de outras formas;


## Endpoints

### /pokemons

- Request: Get /pokemons?pokemon=substring ou /pokemons, onde a substring sera pesquisada nos nomes dos pokemons. Caso seja feito o GET sem a substring, serao retornados todos os pokemons;
- Exemplo Response: {"result":[{"highlight":"< pre> pikachu</pre>","name":"pikachu"}]}
