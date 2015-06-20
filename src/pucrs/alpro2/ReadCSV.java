package pucrs.alpro2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadCSV {

	public static void main(String[] args) {

		ReadCSV obj = new ReadCSV();
		obj.run();

	}

	public void run() {

		String csvFile = "C:/Users/GustavoPC/turbo-octo-tribble/furtos.csv";
		BufferedReader br = null;
		LinkedList lista = new LinkedList();
		String line = "";
		String cvsSplitBy = ";";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// Imprime tudo da linha
				System.out.println(line);
				String[] div = line.split(cvsSplitBy);
				String[] prefixos = { "RUA", "AV", "BC", "PCA" };
				for (String p : prefixos) {
					if (div[2].startsWith(p)) {
						lista.add(div[2].substring(p.length() + 1).trim(), p);
					}
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
		System.out.println("lista:");
		System.out.println(lista.getNome(0));
		System.out.println("Done");
	}
}