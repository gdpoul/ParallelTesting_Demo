package readCSVData;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSV {
	static String CSV_PATH = "./resources/demo.csv";

	public static void main(String[] args) throws IOException, CsvException {

		CSVReader reader = new CSVReader(new FileReader(CSV_PATH));
		String data[];

		while ((data = reader.readNext()) != null) {
			
			for(int i=0;i<data.length;i++) {
				System.out.print(data[i]+" ");
			}
			System.out.println(" ");
		}

	}

}
