package pokapi.helper.export;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import pokapi.entity.PokemonEntity;
import pokapi.helper.export.version.PokemonsExportVersionLight;
import pokapi.helper.search.Search;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExportCSVTest {

    @Test
    public void export() throws IOException {
        List<PokemonEntity> pokemonEntities = new ArrayList<>();

        PokemonEntity pokemonEntity = new PokemonEntity();
        pokemonEntity.setId(1L);
        pokemonEntity.setName("Bulbizarre");
        pokemonEntity.setIdPokedex(1L);
        PokemonEntity pokemonEntity1 = new PokemonEntity();
        pokemonEntity1.setId(2L);
        pokemonEntity1.setName("Carapuce");
        pokemonEntity1.setIdPokedex(2L);

        pokemonEntities.add(pokemonEntity);
        pokemonEntities.add(pokemonEntity1);

        PokemonsExportVersionLight pokemonsExportVersionLight = new PokemonsExportVersionLight("csv", pokemonEntities);
        ByteArrayInputStream inputStream = ExportCSV.export(pokemonsExportVersionLight);
        String csvInput = Search.deAccent(String.join(",", pokemonsExportVersionLight.getPokemonsHeaderColumns())) + "\r\n" +
                "1,Bulbizarre,1" + "\r\n" +
                "2,Carapuce,2" + "\r\n";

        assertEquals(IOUtils.toString(inputStream, StandardCharsets.UTF_8), csvInput);
    }
}