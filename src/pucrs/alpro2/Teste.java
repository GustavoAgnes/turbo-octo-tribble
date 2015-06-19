package pucrs.alpro2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Teste {

	public static void main(String[] args) throws IOException {
		BufferedReader br2 = null;
		String arq = "C:/Users/Gustavo Agnes/Documents/GitHub/turbo-octo-tribble/tipo.txt";
		br2 = new BufferedReader(new FileReader(arq));
		String linha = "";
		while ((linha = br2.readLine()) != null) {
			System.out.println("tst"+linha);
		}
		br2.close();
}
}
