package com.org.frameworkFiles.actionKeywords;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/***************************************************
 * Class Name: ExcelToHashMap
 * Class Description: To read and map excel data
 * Date Created: 28/01/2023
 * @author : Jerry Nkomo
 */
public class ExcelToHashMap {

	public String filePath;
	public String sheetName;
	XSSFWorkbook workbook;
	
	//Initialize Excel
	public ExcelToHashMap(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName =sheetName;
	}
	
	//Get Sheet
	private XSSFSheet getSheet() throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		return sheet;
	}
	
	//Get Excel As Map
	public Map<String, Map<String, String>> getExcelAsMap() throws IOException{
		XSSFSheet sheet = getSheet();
		Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String,String>>();
		List<String> columnHeader = new ArrayList<String>();
		Row row = sheet.getRow(0);
		
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			columnHeader.add(cellIterator.next().getStringCellValue());			
		}
		
		int rowCount = sheet.getLastRowNum();
		int columnCount = row.getLastCellNum();
		for (int i = 1; i <= rowCount; i++) {
			Map<String, String> singleRowData = new HashMap<String, String>();
			Row row1 = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
			
				Cell cell = row1.getCell(j);
				singleRowData.put(columnHeader.get(j), getCellAsString(cell));
				
			}
			completeSheetData.put(String.valueOf(i), singleRowData);
			
		}
		
		return completeSheetData;
	}
	
	//Get Cell Value
	public String getCellAsString(Cell cell) {
		String cellValue = null;
		
		switch (cell.getCellType()) {
		case NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case BLANK:
			cellValue = "BLANK";
			break;
		default:
			cellValue = "DEFAULT";
			break;
		}
		
		return cellValue;
	}
	
	public int getSheetCount() throws Exception {
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		return workbook.getNumberOfSheets();
	}
	
	public int getColumnCount() throws Exception {
		XSSFSheet sheet = getSheet();
		Row row = sheet.getRow(0);
		return row.getLastCellNum();
	}
}
