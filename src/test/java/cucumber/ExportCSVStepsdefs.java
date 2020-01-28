package cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.IOUtils;
import pokapi.entity.PokemonEntity;
import pokapi.helper.export.ExportCSV;
import pokapi.helper.export.version.PokemonsExportVersion;
import pokapi.helper.export.version.PokemonsExportVersionLight;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExportCSVStepsdefs {

    private PokemonEntity bulbizarre;
    PokemonsExportVersion exportVersion;
    private ByteArrayInputStream contenuCSV;

    @Given("L'utilisateur veut exporter au format CSV des données contenant le Pokémon Bulbizarre")
    public void l_utilisateur_veut_exporter_au_format_csv_des_donnees_contenant_le_Pokemon_Bulbizarre() {
        bulbizarre = new PokemonEntity();
        bulbizarre.setId(1L);
        bulbizarre.setName("Bulbizarre");
        bulbizarre.setIdPokedex(1L);

        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        pokemonEntities.add(bulbizarre);

        exportVersion = new PokemonsExportVersionLight("csv", pokemonEntities);

        assertThat(pokemonEntities).contains(bulbizarre);
        assertThat(exportVersion.getPokemonEntities()).contains(bulbizarre);
    }

    @When("Une requête d'export en CSV est lancée")
    public void une_requete_d_export_en_CSV_est_lancee() throws IOException {
        contenuCSV = ExportCSV.export(exportVersion);
    }

    @Then("Les données exportées en CSV doivent contenir le Pokémon Bulbizarre")
    public void les_donnees_exportees_en_csv_doivent_contenir_le_Pokemon_Bulbizarre() throws IOException {
        assertThat(IOUtils.toString(contenuCSV, StandardCharsets.UTF_8)).contains(bulbizarre.getName());
    }
}
