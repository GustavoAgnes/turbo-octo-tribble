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

<<<<<<< HEAD
	}

	LinkedList lista = new LinkedList();

	public void run() {
		String csvFile = "C:/Users/GustavoPC/turbo-octo-tribble/furtos.csv";
		BufferedReader br = null;

		String line = "";
		String cvsSplitBy = ";";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// Imprime tudo da linha
				// System.out.println(line);
				String[] div = line.split(cvsSplitBy);
				String[] prefixos = { "RUA", "AV", "BC", "PCA" };
				for (String p : prefixos) {
					if (div[2].startsWith(p)) {
						lista.add(div[2].substring(p.length() + 1).trim(), p,
								ParseDouble(div[11]), ParseDouble(div[12]));
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
		// funcao adicionar ponto na outra classe(deve dar certo) -> -> -> ->
	//	System.out.println("lista:");
	//	System.out.println(lista.getNome(0));
	//	System.out.println(lista.getTipo(0));
	//	System.out.println(lista.getCoordX(0));
	//	System.out.println(lista.getCoordY(0));
	//	System.out.println("Done");
	}

	public double getCoordX(int index) {
		return lista.getCoordX(index);
	}

	public double getCoordY(int index) {
		return lista.getCoordY(index);
	}
	

	public int getSize() {
		return lista.size();
	}
	
	double ParseDouble(String strNumber) {
=======
    }
    LinkedList lista = new LinkedList();

    public void run() {
        //String csvFile = "C:/Users/GustavoPC/turbo-octo-tribble/furtos.csv";
<<<<<<< HEAD
        String csvFile = "H:/turbo-octo-tribble-master/furtos.csv";
=======
        String csvFile = "C:/Users/Gustavo_Agnes/Desktop/TF/turbo-octo-tribble-master/furtos.csv";
>>>>>>> f21521d6f0453a7c4a479d6fdcf4cb95e9d1f642
        BufferedReader br = null;

        String line = "";
        String cvsSplitBy = ";";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
				// Imprime tudo da linha
                //System.out.println(line);
                String[] div = line.split(cvsSplitBy);
                String[] prefixos = {"RUA", "AV", "BC", "PCA"};
                for (String p : prefixos) {
                    if (div[2].startsWith(p)) {
                        lista.add(div[2].substring(p.length() + 1).trim(), p, ParseDouble(div[11]), ParseDouble(div[12]));
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
        //funcao adicionar ponto na outra classe(deve dar certo) -> -> -> ->
        System.out.println("lista:");
        System.out.println(lista.getNome(0));
        System.out.println(lista.getTipo(0));
        System.out.println(lista.getCoordX(0));
        System.out.println(lista.getCoordY(0));
        System.out.println("Done");
    }

    public double getCoordX(int index) {
        return lista.getCoordX(index);
    }

    public double getCoordY(int index) {
        return lista.getCoordY(index);
    }

    public int getSize() {
        return lista.size();
    }

    double ParseDouble(String strNumber) {
>>>>>>> 0ee5e547cf199f4979c9d600c4d127515b5441dd
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;
            }
        } else {
            return 0;
            //
        }
<<<<<<< HEAD
	}
}
=======
    }
}
>>>>>>> 0ee5e547cf199f4979c9d600c4d127515b5441dd
