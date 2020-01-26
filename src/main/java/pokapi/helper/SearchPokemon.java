package pokapi.helper;

import org.apache.commons.lang3.StringUtils;
import pokapi.entity.PokemonEntity;

import java.util.*;
import java.util.stream.Collectors;

public class SearchPokemon {
    private SearchPokemon() {
    }

    public static List<PokemonEntity> searchScore(List<PokemonEntity> allPokemons, String exp) {
        if (StringUtils.isBlank(exp) || allPokemons == null || allPokemons.isEmpty()) {
            return new ArrayList<>();
        }

        List<Map.Entry<PokemonEntity, Integer>> pokemonsScored = new ArrayList<>();
        allPokemons.forEach(pokemonEntity -> {
            Search.searchScoreEntity(pokemonsScored, pokemonEntity, pokemonEntity.getName(), exp);
        });

        pokemonsScored.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        return pokemonsScored.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
