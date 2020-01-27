package pokapi.helper.export.version;

import pokapi.entity.PokemonEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokemonsExportVersionLight extends PokemonsExportVersion {
    public PokemonsExportVersionLight(String extension, List<PokemonEntity> pokemonEntities) {
        super(extension, pokemonEntities, "pokemons_light");
    }

    @Override
    public List<String> getPokemonsHeaderColumns() {
        return new ArrayList<>(Arrays.asList("Id", "Nom", "Id Pokédex"));
    }

    @Override
    public List<List<String>> getPokemonsContentRowsAndColumns() {
        List<List<String>> rows = new ArrayList<>();

        this.getPokemonEntities().forEach(pokemonEntity -> {
            List<String> columns = new ArrayList<>();

            columns.add(String.valueOf(pokemonEntity.getId()));
            columns.add(pokemonEntity.getName());
            columns.add(String.valueOf(pokemonEntity.getIdPokedex()));

            rows.add(columns);
        });

        return rows;
    }
}
