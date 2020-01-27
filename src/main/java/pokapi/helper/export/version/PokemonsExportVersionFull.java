package pokapi.helper.export.version;

import pokapi.entity.EstTypeEntity;
import pokapi.entity.PokemonEntity;
import pokapi.entity.TypeEntity;
import pokapi.model.EstType;
import pokapi.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonsExportVersionFull extends PokemonsExportVersion {
    private List<TypeEntity> typeEntities;
    private List<EstTypeEntity> estTypeEntities;

    public PokemonsExportVersionFull(String extension, List<PokemonEntity> pokemonEntities, List<TypeEntity> typeEntities, List<EstTypeEntity> estTypeEntities) {
        super(extension, pokemonEntities, "pokemons_full");
        this.typeEntities = typeEntities;
        this.estTypeEntities = estTypeEntities;
    }

    @Override
    public List<String> getPokemonsHeaderColumns() {
        return new ArrayList<>(Arrays.asList("Id", "Nom", "Id Pokédex", "Type(s)"));
    }

    @Override
    public List<List<String>> getPokemonsContentRowsAndColumns() {
        List<List<String>> rows = new ArrayList<>();

        this.getPokemonEntities().forEach(pokemonEntity -> {
            List<String> columns = new ArrayList<>();

            columns.add(String.valueOf(pokemonEntity.getId()));
            columns.add(pokemonEntity.getName());
            columns.add(String.valueOf(pokemonEntity.getIdPokedex()));
            columns.add(getTypesOfPokemon(pokemonEntity));

            rows.add(columns);
        });

        return rows;
    }

    private String getTypesOfPokemon(PokemonEntity pokemonEntity) {
        List<EstTypeEntity> estTypeEntityList = this.estTypeEntities.stream()
                .filter(estTypeEntity -> estTypeEntity.getIdPokemon().equals(pokemonEntity.getId()))
                .collect(Collectors.toList());

        List<TypeEntity> typeEntityList = this.typeEntities.stream()
                .filter(typeEntity -> estTypeEntityList.stream().map(EstType::getIdType).collect(Collectors.toList()).contains(typeEntity.getId()))
                .collect(Collectors.toList());

        return typeEntityList.stream().map(Type::getName).collect(Collectors.joining(" - "));
    }
}
