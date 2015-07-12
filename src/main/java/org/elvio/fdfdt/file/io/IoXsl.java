package org.elvio.fdfdt.file.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.elvio.fdfdt.file.conf.meta.ClassConfiguration;
import org.elvio.fdfdt.file.conf.meta.Information;
import org.elvio.fdfdt.util.CellFormatUtilities;

public class IoXsl {

	public static List<List<String>> importXsl(Path absolutePath) {

		List<List<String>> values = new ArrayList<List<String>>();
		ArrayList<String> rowValues;

		try {
			FileInputStream fileInputStream = new FileInputStream(absolutePath.toFile());
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet worksheet = workbook.getSheetAt(0);

			for (int i = 0; i <= worksheet.getLastRowNum(); i++) {
				rowValues = new ArrayList<String>();

				for (Iterator<Cell> cells = worksheet.getRow(i).cellIterator(); cells.hasNext();) {
					rowValues.add(CellFormatUtilities.toString(cells.next()));
				}

				values.add(rowValues);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return values;
	}

	public static void exportXsl(String absolutePath, String extension, Information configOut) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook();
			FileOutputStream file = new FileOutputStream(absolutePath + "." + extension);

			for (Map.Entry<String, ClassConfiguration> entry : configOut.getConfigurations().entrySet()) {
				produceSheet(workbook.createSheet(entry.getKey()), entry.getValue());
			}
			produceSummarySheet(workbook);

			workbook.write(file);
			file.flush();
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void produceSummarySheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("résumé");
		int sheetNbr = workbook.getNumberOfSheets();
		HSSFRow xlsRow;
		HSSFCell xlsCellName;
		HSSFCell xlsCellValue;
		
		for(int i = 0 ; i < (sheetNbr-1) ; i++){
			xlsRow = sheet.createRow(i);
			xlsCellName = xlsRow.createCell(0);
			xlsCellName.setCellValue(workbook.getSheetAt(i).getSheetName());
			
			xlsCellValue = xlsRow.createCell(1);
			CellStyle style = xlsCellValue.getRow().getSheet().getWorkbook().createCellStyle();
	        DataFormat df = xlsCellValue.getRow().getSheet().getWorkbook().createDataFormat();
	        style.setDataFormat(df.getFormat("[h]:mm:ss;@"));
			
	        xlsCellValue.setCellStyle(style);
	        xlsCellValue.setCellType(Cell.CELL_TYPE_NUMERIC);
	        int ligne = getLastPositionInLColumn(workbook.getSheetAt(i));
	        String name = "'"+workbook.getSheetAt(i).getSheetName()+"'!L"+ligne;
	        xlsCellValue.setCellFormula(name);			
		}
	}

	private static int getLastPositionInLColumn(HSSFSheet sheetAt) {
		int result = sheetAt.getLastRowNum();
		return result+1;
	}

	private static void produceSheet(HSSFSheet worksheet, ClassConfiguration configOut) {
		HSSFRow xlsRow;
		HSSFCell xlsCell;

		for (int rCount = 0; rCount < configOut.countRows(); rCount++) {
			xlsRow = worksheet.createRow(rCount);
			for (int cCount = 0; cCount < configOut.cellSize(rCount); cCount++) {
				xlsCell = xlsRow.createCell(cCount);
				configOut.getCell(rCount, cCount).process(xlsCell);
			}
		}
	}

}
