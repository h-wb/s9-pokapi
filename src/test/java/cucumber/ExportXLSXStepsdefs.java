package cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import pokapi.entity.PokemonEntity;
import pokapi.helper.export.ExportXLSX;
import pokapi.helper.export.version.PokemonsExportVersion;
import pokapi.helper.export.version.PokemonsExportVersionLight;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExportXLSXStepsdefs {

    private PokemonEntity bulbizarre;
    PokemonsExportVersion exportVersion;
    private ByteArrayInputStream contenuXLSX;

    @Given("L'utilisateur veut exporter au format XLSX des données contenant le Pokémon Bulbizarre")
    public void l_utilisateur_veut_exporter_au_format_xlsx_des_donnees_contenant_le_Pokemon_Bulbizarre() {
        bulbizarre = new PokemonEntity();
        bulbizarre.setId(1L);
        bulbizarre.setName("Bulbizarre");
        bulbizarre.setIdPokedex(1L);

        List<PokemonEntity> pokemonEntities = new ArrayList<>();
        pokemonEntities.add(bulbizarre);

        exportVersion = new PokemonsExportVersionLight("xlsx", pokemonEntities);

        assertThat(pokemonEntities).contains(bulbizarre);
        assertThat(exportVersion.getPokemonEntities()).contains(bulbizarre);
    }

    @When("Une requête d'export en XLSX est lancée")
    public void une_requete_d_export_en_XLSX_est_lancee() throws IOException {
        contenuXLSX = ExportXLSX.export(exportVersion);
    }

    @Then("Les données exportées en XLSX doivent contenir le Pokémon Bulbizarre")
    public void les_donnees_exportees_en_xlsx_doivent_contenir_le_Pokemon_Bulbizarre() throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(contenuXLSX);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(1);

        assertThat(cell.getStringCellValue()).contains(bulbizarre.getName());
    }
}
