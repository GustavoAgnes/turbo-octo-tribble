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

	public void lerTipo() throws IOException {
		BufferedReader br2 = null;
		String arq = "C:/Users/Gustavo Agnes/Documents/GitHub/turbo-octo-tribble/tipo.txt";
		br2 = new BufferedReader(new FileReader(arq));
		String linha = "";
		while ((linha = br2.readLine()) != null) {
			System.out.println("tst" + linha);
		}
		br2.close();
	}

	public void run() {

		String csvFile = "C:/Users/GustavoPC/turbo-octo-tribble/furtos.csv";
		BufferedReader br = null;
		LinkedList lista = new LinkedList();
		String line = "";
		String cvsSplitBy = ";";
		String splitBy = "\\.";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			//
			System.out.println(br.readLine());
			//
			while ((line = br.readLine()) != null) {
				System.out.println();
				String[] div = line.split(cvsSplitBy);
				String[] div2 = div[2].split(splitBy);
				// for (int i = 0; i < div.length; i++) {
				System.out.print(div[2] + " ");
				if (div[2].startsWith("RUA")) {
					lista.add(br.readLine(),div2[1].replaceAll("\\s+"," "), "RUA");                 //div2[0].replaceAll("\\s+"," "));
				}
				if (div[2].startsWith("AV")) {
					lista.add(br.readLine(),div2[1].replaceAll("\\s+"," "), "AV");
				}
				if (div[2].startsWith("BC")) {
					lista.add(br.readLine(),div2[1].replaceAll("\\s+"," "), "BC");
				}
				if (div[2].startsWith("PCA")) {
					lista.add(br.readLine(),div2[1].replaceAll("\\s+"," "), "PCA");
				}
				// AV RUA BC PCA
				// }
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
			System.out.println(lista.size());
			System.out.println(lista.getTipo(200));
		}
		System.out.println("Done");
	}

}
