package pokapi.helper.export.version;

import org.junit.Test;
import pokapi.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokemonsExportVersionLightTest {

    @Test
    public void testGetters() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);

        pokemonEntities.add(pokemonEntity);

        PokemonsExportVersionLight pokemonsExportVersionLight = new PokemonsExportVersionLight("csv", pokemonEntities);
        assertEquals("pokemons_light", pokemonsExportVersionLight.getFileName());
        assertEquals("csv", pokemonsExportVersionLight.getExtension());
        assertEquals(pokemonEntities, pokemonsExportVersionLight.getPokemonEntities());
    }

    @Test
    public void getPokemonsHeaderColumns() {
        PokemonsExportVersionLight pokemonsExportVersionLight = new PokemonsExportVersionLight("", null);
        List<String> headerColumns = pokemonsExportVersionLight.getPokemonsHeaderColumns();

        assertEquals(headerColumns.get(0), "Id");
        assertEquals(headerColumns.get(1), "Nom");
        assertEquals(headerColumns.get(2), "Id Pokédex");
    }

    @Test
    public void getPokemonsContentRowsAndColumns() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);

        pokemonEntities.add(pokemonEntity);

        PokemonsExportVersionLight pokemonsExportVersionLight = new PokemonsExportVersionLight("", pokemonEntities);
        List<List<String>> rowsAndColumns = pokemonsExportVersionLight.getPokemonsContentRowsAndColumns();

        assertEquals(1, rowsAndColumns.size());
        assertEquals(3, rowsAndColumns.get(0).size());
        assertEquals(pokemonEntity.getId().toString(), rowsAndColumns.get(0).get(0));
        assertEquals(pokemonEntity.getName(), rowsAndColumns.get(0).get(1));
        assertEquals(pokemonEntity.getIdPokedex().toString(), rowsAndColumns.get(0).get(2));
    }
}