package pokapi.helper.export;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import pokapi.helper.export.version.PokemonsExportVersion;
import pokapi.helper.search.Search;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class ExportCSV {
    private ExportCSV() {
    }

    public static ByteArrayInputStream export(PokemonsExportVersion pokemonsExportVersion) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(out, StandardCharsets.UTF_8), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(pokemonsExportVersion.getPokemonsHeaderColumns().stream().map(Search::deAccent).collect(Collectors.toList()));

            for (List<String> pokemonsContentRowsAndColumn : pokemonsExportVersion.getPokemonsContentRowsAndColumns()) {
                csvPrinter.printRecord(pokemonsContentRowsAndColumn.stream().map(Search::deAccent).collect(Collectors.toList()));
            }
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
