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

<<<<<<< HEAD
		String csvFile = "H:/turbo-octo-tribble-master/furtos.csv";
=======
		String csvFile = "C:/Users/GustavoPC/turbo-octo-tribble/furtos.csv";
>>>>>>> fa9d57a5751ba011f29be03e1f6cd21eb1c59009
		BufferedReader br = null;
		LinkedList lista = new LinkedList();
		String line = "";
		String cvsSplitBy = ";";
		String splitBy = "\\.";
		try {
			br = new BufferedReader(new FileReader(csvFile));
<<<<<<< HEAD
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
=======
			//
<<<<<<< HEAD
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
=======
			System.out.println(br.readLine());
			//
			while ((line = br.readLine()) != null) {
				br.skip(117);
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
>>>>>>> b4c74579e44656a1230a71519cc468b4f9ba36b7
			}
>>>>>>> fa9d57a5751ba011f29be03e1f6cd21eb1c59009
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
			// System.out.println(lista.size());
			// System.out.println(lista.getTipo(200));
		}
		System.out.println("lista:");
		System.out.println(lista);
		System.out.println("Done");
	}

}
=======
			System.out.println(lista.size());
			System.out.println(lista.getTipo(200));
>>>>>>> b4c74579e44656a1230a71519cc468b4f9ba36b7
		}
		System.out.println("lista:");
		System.out.println(lista.getNome(0));
		System.out.println("Done");
	}
<<<<<<< HEAD
}
=======

}
>>>>>>> fa9d57a5751ba011f29be03e1f6cd21eb1c59009
>>>>>>> b4c74579e44656a1230a71519cc468b4f9ba36b7
