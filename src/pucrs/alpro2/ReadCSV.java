package pucrs.alpro2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadCSV {
	
	public ReadCSV() {
	}
	public static void main(String[] args) {
		 
		ReadCSV obj = new ReadCSV();
		String nome = null;
		obj.run(nome);
	  }
	public void run(String nomeArq) {
		String csvFile = "H:/turbo-octo-tribble/"+nomeArq+".csv";
		BufferedReader br = null;
		BufferedReader br2 = null;
		int count=0;
		String line = "";
		String cvsSplitBy = ";";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			br.skip(117);
			while ((line = br.readLine()) != null) {
	 System.out.println();
	String[] teste = line.split(cvsSplitBy);
	//teste
	File arquivo = new File("H:/turbo-octo-tribble/tipo.txt");
	arquivo.createNewFile();
	FileWriter fw = new FileWriter(arquivo);
	BufferedWriter bw = new BufferedWriter(fw);
	//String linha = br.readLine();
	if(line.contains("RUA")){
	//	System.out.println("rua");
		count++;
		bw.write("Rua");
		bw.newLine();
	}
	if(line.contains("AV")){
		count++;
		bw.write("Avenida");
		bw.newLine();
	}
	if(line.contains("ESTR")){
		count++;
		bw.write("Estrada");
		bw.newLine();
	}
	if(line.contains("TV")){
		count++;
		bw.write("TV");
		bw.newLine();
	}
	if(line.contains("PCA")){
		count++;
		bw.write("PCA");
		bw.newLine();
	}
	System.out.println(count);
	bw.close();
	for(int i=0;i<teste.length;i++){
		br2 = new BufferedReader(new FileReader(arquivo));
		System.out.println(br2.readLine());
		// System.out.print(teste[i]+" ");
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
