package writeExcelData;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import utilpackage.FileLib;

public class WriteExcelData {

	static String EXCELPATH = "./resources/testdata.xlsx";
	static String SHEETNAME = "EnterData";

	public static void main(String[] args) throws EncryptedDocumentException, 
	              FileNotFoundException, IOException {
		
		FileLib.enterDataIntoExcel(EXCELPATH, SHEETNAME, 1, 0, "username");
		FileLib.enterDataIntoExcel(EXCELPATH, SHEETNAME, 1, 1, "password");
	}

}
