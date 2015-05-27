package pucrs.alpro2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
	
	public static void main(String[] args) {
		 
		ReadCSV obj = new ReadCSV();
		obj.run();
	  }
	public void run() {
		 
		String csvFile = "/home/PORTOALEGRE/12105755/turbo-octo-tribble/furtos.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 System.out.println();
				String[] teste = line.split(cvsSplitBy);
	 for(int i=0;i<teste.length;i++){
		 System.out.print(teste[i]+" ");
	 }
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		System.out.println("\nDone");
	  }
	 
	}
