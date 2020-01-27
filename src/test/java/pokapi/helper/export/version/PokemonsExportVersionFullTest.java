package pokapi.helper.export.version;

import org.junit.Test;
import pokapi.entity.EstTypeEntity;
import pokapi.entity.PokemonEntity;
import pokapi.entity.TypeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokemonsExportVersionFullTest {

    @Test
    public void testGetters() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);

        pokemonEntities.add(pokemonEntity);

        PokemonsExportVersionFull pokemonsExportVersionFull = new PokemonsExportVersionFull("csv", pokemonEntities, null, null);
        assertEquals("pokemons_full", pokemonsExportVersionFull.getFileName());
        assertEquals("csv", pokemonsExportVersionFull.getExtension());
        assertEquals(pokemonEntities, pokemonsExportVersionFull.getPokemonEntities());
    }

    @Test
    public void getPokemonsHeaderColumns() {
        PokemonsExportVersionFull pokemonsExportVersionFull = new PokemonsExportVersionFull("", null, null, null);
        List<String> headerColumns = pokemonsExportVersionFull.getPokemonsHeaderColumns();

        assertEquals("Id", headerColumns.get(0));
        assertEquals("Nom", headerColumns.get(1));
        assertEquals("Id Pokédex", headerColumns.get(2));
        assertEquals("Type(s)", headerColumns.get(3));
    }

    @Test
    public void getPokemonsContentRowsAndColumns() {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        List<TypeEntity> typeEntities = new ArrayList<>();
        List<EstTypeEntity> estTypeEntities = new ArrayList<>();

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);

        pokemonEntities.add(pokemonEntity);

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(1L);
        typeEntity.setName("Plante");
        TypeEntity typeEntity1 = new TypeEntity();
        typeEntity1.setId(2L);
        typeEntity1.setName("Poison");

        typeEntities.add(typeEntity);
        typeEntities.add(typeEntity1);

        EstTypeEntity estTypeEntity = new EstTypeEntity();
        estTypeEntity.setId(1L);
        estTypeEntity.setIdPokemon(1L);
        estTypeEntity.setIdType(1L);
        EstTypeEntity estTypeEntity1 = new EstTypeEntity();
        estTypeEntity1.setId(2L);
        estTypeEntity1.setIdPokemon(1L);
        estTypeEntity1.setIdType(2L);

        estTypeEntities.add(estTypeEntity);
        estTypeEntities.add(estTypeEntity1);

        PokemonsExportVersionFull pokemonsExportVersionFull = new PokemonsExportVersionFull("", pokemonEntities, typeEntities, estTypeEntities);
        List<List<String>> rowsAndColumns = pokemonsExportVersionFull.getPokemonsContentRowsAndColumns();

        assertEquals(1, rowsAndColumns.size());
        assertEquals(4, rowsAndColumns.get(0).size());
        assertEquals(pokemonEntity.getId().toString(), rowsAndColumns.get(0).get(0));
        assertEquals(pokemonEntity.getName(), rowsAndColumns.get(0).get(1));
        assertEquals(pokemonEntity.getIdPokedex().toString(), rowsAndColumns.get(0).get(2));
        assertEquals(pokemonsExportVersionFull.getTypesOfPokemon(pokemonEntity), rowsAndColumns.get(0).get(3));
    }

    @Test
    public void getTypesOfPokemon() {
        List<TypeEntity> typeEntities = new ArrayList<>();
        List<EstTypeEntity> estTypeEntities = new ArrayList<>();

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setId(1L);
        typeEntity.setName("Plante");
        TypeEntity typeEntity1 = new TypeEntity();
        typeEntity1.setId(2L);
        typeEntity1.setName("Poison");

        typeEntities.add(typeEntity);
        typeEntities.add(typeEntity1);

        EstTypeEntity estTypeEntity = new EstTypeEntity();
        estTypeEntity.setId(1L);
        estTypeEntity.setIdPokemon(1L);
        estTypeEntity.setIdType(1L);
        EstTypeEntity estTypeEntity1 = new EstTypeEntity();
        estTypeEntity1.setId(2L);
        estTypeEntity1.setIdPokemon(1L);
        estTypeEntity1.setIdType(2L);

        estTypeEntities.add(estTypeEntity);
        estTypeEntities.add(estTypeEntity1);

        PokemonsExportVersionFull pokemonsExportVersionFull = new PokemonsExportVersionFull("", null, typeEntities, estTypeEntities);

        assertEquals("Plante - Poison", pokemonsExportVersionFull.getTypesOfPokemon(pokemonEntity));
    }
}