package pokapi.helper;

import org.apache.commons.lang3.StringUtils;
import pokapi.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchPokemon {
    private SearchPokemon() {
    }

    /**
     * Méthode permettant d'effectuer une recherche scorée sur une liste de Pokémons
     *
     * @param allPokemons liste des Pokémons sur laquelle la recherche a lieu
     * @param exp         expression recherchée
     * @return liste des Pokémons correspondants à la recherche, classée dans l'ordre descendant du score obtenu
     */
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
