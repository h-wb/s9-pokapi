package pokapi.helper.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pokapi.helper.export.version.PokemonsExportVersion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportXLSX {

    public static ByteArrayInputStream export(PokemonsExportVersion pokemonsExportVersion) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet("Pokémons");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        List<String> headerColumns = pokemonsExportVersion.getPokemonsHeaderColumns();

        for (int i = 0; i < headerColumns.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerColumns.get(i));
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (List<String> pokemonsContentRowsAndColumn : pokemonsExportVersion.getPokemonsContentRowsAndColumns()) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < pokemonsContentRowsAndColumn.size(); i++) {
                row.createCell(i).setCellValue(pokemonsContentRowsAndColumn.get(i));
            }
        }

        for (int i = 0; i < headerColumns.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }
}
