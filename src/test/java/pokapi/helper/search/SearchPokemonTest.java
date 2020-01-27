package pokapi.helper.search;

import org.junit.Test;
import pokapi.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchPokemonTest {

    @Test
    public void searchScore() {
        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setName("BÛlbizarre");
        PokemonEntity pokemonEntity1 = new PokemonEntity();
        pokemonEntity1.setName("bu   lbizarre");
        PokemonEntity pokemonEntity2 = new PokemonEntity();
        pokemonEntity2.setName("Bûlbizarre");
        PokemonEntity pokemonEntity3 = new PokemonEntity();
        pokemonEntity3.setName("Bulbizarre");
        PokemonEntity pokemonEntity4 = new PokemonEntity();
        pokemonEntity4.setName("bulbizarre");
        PokemonEntity pokemonEntity5 = new PokemonEntity();
        pokemonEntity5.setName("Sulfura");

        List<PokemonEntity> pokemonEntityList = new ArrayList<>();
        pokemonEntityList.add(pokemonEntity);
        pokemonEntityList.add(pokemonEntity1);
        pokemonEntityList.add(pokemonEntity2);
        pokemonEntityList.add(pokemonEntity3);
        pokemonEntityList.add(pokemonEntity4);
        pokemonEntityList.add(pokemonEntity5);

        List<PokemonEntity> results = SearchPokemon.searchScore(pokemonEntityList, "Bulbizarre");
        assertEquals(5, results.size());
        assertEquals(pokemonEntity3.getName(), results.get(0).getName());
        assertEquals(pokemonEntity4.getName(), results.get(1).getName());
        assertEquals(pokemonEntity2.getName(), results.get(2).getName());
        assertEquals(pokemonEntity.getName(), results.get(3).getName());
        assertEquals(pokemonEntity1.getName(), results.get(4).getName());

        assertTrue(SearchPokemon.searchScore(pokemonEntityList, null).isEmpty());
        assertTrue(SearchPokemon.searchScore(pokemonEntityList, "").isEmpty());
        assertTrue(SearchPokemon.searchScore(pokemonEntityList, "  ").isEmpty());
        assertTrue(SearchPokemon.searchScore(null, "Test").isEmpty());
        assertTrue(SearchPokemon.searchScore(new ArrayList<>(), "Test").isEmpty());
    }
}