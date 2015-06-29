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
    LinkedList lista = new LinkedList();
    LinkedList listaParadas = new LinkedList();

    public void run() {
        String csvFile = "C:/Users/GustavoPC/turbo-octo-tribble/furtos.csv";
        //String csvFile = "C:/Users/Gustavo_Agnes/Desktop/TF/turbo-octo-tribble-master/furtos.csv";
        String paradas = "C:/Users/GustavoPC/turbo-octo-tribble/taxis.csv";
        BufferedReader br = null;
        JanelaConsulta JC = new JanelaConsulta();
        BufferedReader br2 = null;
        String line = "";
        String line2 = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            //
            br2 = new BufferedReader(new FileReader(paradas));
            //
            while ((line = br.readLine()) != null) {
				// Imprime tudo da linha
                //System.out.println(line);
                String[] div = line.split(cvsSplitBy);
                String[] prefixos = {"RUA", "AV", "BC", "PCA"};
                for (String p : prefixos) {
                    if (div[2].startsWith(p)) {
                        lista.add(div[2].substring(p.length() + 1).trim(), p, ParseDouble(div[11]), ParseDouble(div[12]),0);
                    }
                }
            }
            while ((line2 = br2.readLine())!=null){
                String[] div2 = line2.split(cvsSplitBy);
                listaParadas.add(div2[1], div2[2], ParseDouble(div2[3]), ParseDouble(div2[4]), 0);
                //System.out.println(div2[3]);
                //System.out.println(div2[4]);
                //System.out.println(div2.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
               //////////////////////
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public double getCoordX(int index) {
        return lista.getCoordX(index);
    }
    
    public String getNomeParada(int index) {
    	return (String) listaParadas.getNome(index);
    }
    
    public String getTipoParada(int index) {
    	return (String) listaParadas.getTipo(index);
    }

    public double getCoordY(int index) {
        return lista.getCoordY(index);
    }
    
    public double getLngParada(int index) {
        return listaParadas.getCoordX(index);
    }
    
    public double getLatParada(int index){
        return listaParadas.getCoordY(index);
    }

    public int getSize() {
        return lista.size();
    }
    
    public int getSizeParadas() {
        return listaParadas.size();
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;
            }
        } else {
            return 0;
        }
    }
}