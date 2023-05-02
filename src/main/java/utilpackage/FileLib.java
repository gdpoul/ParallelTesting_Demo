package utilpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileLib {

	public static String readExcelData(String EXCELPATH, String SHEETNAME, int rowNo, int cellNo)
			throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SHEETNAME);
		Row r = sh.getRow(rowNo);
		Cell c = r.getCell(cellNo);

		DataFormatter formatter = new DataFormatter();
		String data = formatter.formatCellValue(c);

//		String data = c.getStringCellValue();
		return data;
	}

	public static int getRowCount(String EXCELPATH, String SHEETNAME) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SHEETNAME);
		int rc = sh.getLastRowNum();
		return rc;

	}

	public static void enterDataIntoExcel(String EXCELPATH, String SHEETNAME, int rowCount, int cellCount, String data)
			throws EncryptedDocumentException, FileNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
//		System.out.println(SHEETNAME);
		Sheet sh=null;
		if(wb.getSheet(SHEETNAME)!=null) {
			sh = wb.getSheet(SHEETNAME);
		}
		else {
			sh=wb.createSheet(SHEETNAME);
		}
		
//		Sheet sh = wb.createSheet(SHEETNAME);
		Row r=null;
		if (sh.getRow(rowCount) != null) {
			r = sh.getRow(rowCount);
		} else {
			r = sh.createRow(rowCount);
		}
//		Row r = sh.getRow(rowCount);	
		Cell c = r.createCell(cellCount);
		c.setCellValue(data);

		FileOutputStream fos = new FileOutputStream(EXCELPATH);
		wb.write(fos);

	}

}
