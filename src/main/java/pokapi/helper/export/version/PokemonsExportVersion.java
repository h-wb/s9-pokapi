package pokapi.helper.export.version;

import pokapi.entity.PokemonEntity;

import java.util.List;

public abstract class PokemonsExportVersion {
    private String extension;
    private String fileName;
    private List<PokemonEntity> pokemonEntities;

    public PokemonsExportVersion(String extension, List<PokemonEntity> pokemonEntities, String fileName) {
        this.extension = extension;
        this.pokemonEntities = pokemonEntities;
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public String getFileName() {
        return fileName;
    }

    public List<PokemonEntity> getPokemonEntities() {
        return pokemonEntities;
    }

    public abstract List<String> getPokemonsHeaderColumns();

    public abstract List<List<String>> getPokemonsContentRowsAndColumns();
}
