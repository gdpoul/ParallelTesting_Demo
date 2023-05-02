package writeCsvData;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import com.opencsv.CSVWriter;
import utilpackage.FileLib;

public class StoreDataIntoCSVFormat {

	static String ExcelPath = "./resources/invoice.xlsx";
	static String SheetName = "Sheet1";
	static String csv_path = "./resources/demo1.csv";

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		List<String[]> list = new ArrayList<String[]>();
		
		for (int i = 28; i <= 32; i++) {
			String srNo = FileLib.readExcelData(ExcelPath, SheetName, i, 1);
			String value = FileLib.readExcelData(ExcelPath, SheetName, i, 2);
			list.add(new String[] {srNo, value});
		}

     
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");  
		LocalDateTime now = LocalDateTime.now();  
	    
	    FileWriter file=new FileWriter("./resources/"+dtf.format(now)+".csv");
		CSVWriter writer=new CSVWriter(file);
		writer.writeAll(list);
		writer.close();
		
		
	}

}
