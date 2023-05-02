package readExcelData;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;

import utilpackage.FileLib;


public class ReadInvoiceData {

	static String ExcelPath="./resources/invoice.xlsx";
	static String SheetName="Sheet1";
	
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		String data = FileLib.readExcelData(ExcelPath, SheetName, 20, 4);
		System.out.println(data);
	}
}
