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

		String csvFile = "H:/turbo-octo-tribble-master/furtos.csv";
		BufferedReader br = null;
		LinkedList lista = new LinkedList();
		String line = "";
		String cvsSplitBy = ";";
		String splitBy = "\\.";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			//
			// System.out.println(br.readLine());
			//
			while ((line = br.readLine()) != null) {
				// br.skip(117);
				System.out.println(line);
				String[] div = line.split(cvsSplitBy);
				String[] div2 = div[2].split(splitBy);
				// for (int i = 0; i < div.length; i++) {
				// System.out.print(div[2] + " ");
				// System.out.println(div2.length);
				String[] prefixos = { "RUA", "AV", "BC", "PCA" };
				for (String p : prefixos) {

					if (div[2].startsWith(p)) {
						lista.add("x", div[2].substring(p.length() + 1).trim(),
								p); // div2[0].replaceAll("\\s+"," "));
					}
				}

			}
			// }
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
			// System.out.println(lista.size());
			// System.out.println(lista.getTipo(200));
		}
		System.out.println("lista:");
		System.out.println(lista);
		System.out.println("Done");
	}

}