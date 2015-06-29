/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pucrs.alpro2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.FlowLayout;

/**
 *
 * @author Sandro
 */
public class JanelaConsulta extends javax.swing.JFrame {

	private GerenciadorMapa gerenciador;
	private EventosMouse mouse;

	private JPanel painelMapa;
	private JPanel painelLateral;
	int nOcorr;
	int valor;
	int count = 0;
	pucrs.alpro2.algoritmos.AlgoritmosGeograficos Ag = new pucrs.alpro2.algoritmos.AlgoritmosGeograficos();

	/**
	 * Creates new form JanelaConsulta
	 */
	ReadCSV rd = new ReadCSV();
	private JButton button;

	public JanelaConsulta() {
		super();
		// initComponents();

		GeoPosition poa = new GeoPosition(-30.05, -51.18);
		gerenciador = new GerenciadorMapa(poa,
				GerenciadorMapa.FonteImagens.VirtualEarth);
		mouse = new EventosMouse();
		gerenciador.getMapKit().getMainMap().addMouseListener(mouse);
		gerenciador.getMapKit().getMainMap().addMouseMotionListener(mouse);

		painelMapa = new JPanel();
		painelMapa.setLayout(new BorderLayout());
		painelMapa.add(gerenciador.getMapKit(), BorderLayout.CENTER);

		getContentPane().add(painelMapa, BorderLayout.CENTER);

		painelLateral = new JPanel();
		getContentPane().add(painelLateral, BorderLayout.WEST);
		
				JButton btnNewButton = new JButton("Consulta");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						consulta(e);
					}
				});
				painelLateral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				painelLateral.add(btnNewButton);
				button = new JButton("Consulta2");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(maiorCriminalidade());
					}
				});
				
				
				
				
				
				painelLateral.add(button);

		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void consulta(java.awt.event.ActionEvent evt) {
		// Para obter o centro e o raio, usar como segue:
		GeoPosition centro = gerenciador.getSelecaoCentro();
		int raio = gerenciador.getRaio();
		// Lista para armazenar o resultado da consulta
		List<MyWaypoint> lstPoints = new ArrayList<>();
		List<MyWaypoint> lstFurtos = new ArrayList<>();
		List<MyWaypoint> lstParadas = new ArrayList<>();
		rd.run();

		// Exemplo:

		for (int i = 0; i < rd.getSizeParadas(); i++) { // adicionando paradas
			double lat2_a = rd.getLatParada(i);
			double lng2_a = rd.getLngParada(i);
			double lat_b = gerenciador.getSelecaoCentro().getLatitude();
			double lng_b = gerenciador.getSelecaoCentro().getLongitude();
			if (numOcorr(lat2_a, lng2_a, lat_b, lng_b) > 20) { // Atualmente não
																// faz sentido
																// pois a cor do
																// proprio furto
																// muda e não a
																// cor da
																// parada,
																// alterar!
				valor = 20;
			} else {
				valor = numOcorr(lat2_a, lng2_a, lat_b, lng_b); // ex: valor da
																// consulta
																// (criminalidade
																// ou distância)
			}
			//System.out.println("Nivel criminalidade: " + nivC);
			GeoPosition loc3 = new GeoPosition(rd.getLatParada(i),
					rd.getLngParada(i));
			if (gps2m(lat2_a, lng2_a, lat_b, lng_b) < gerenciador.getRaio()) {
				// System.out.println("Valor: "+valor);
				lstParadas.add(new MyWaypoint(Color.RED, 20, loc3));
				resetCount();
		//		lstParadas.sort(lstParadas, comp);
			}
		}
		for (int i = 0; i < rd.getSize(); i++) { // adicionando furtos
			double lat_a = rd.getCoordX(i);
			double lng_a = rd.getCoordY(i);
			double lat_b = gerenciador.getSelecaoCentro().getLatitude();
			double lng_b = gerenciador.getSelecaoCentro().getLongitude();

			// System.out.println(numOcorr(lat_a, lng_a, lat_b, lng_b));
			// /////////////

			GeoPosition loc2 = new GeoPosition(rd.getCoordX(i), rd.getCoordY(i));
			if (gps2m(lat_a, lng_a, lat_b, lng_b) < gerenciador.getRaio()) {
				lstFurtos.add(new MyWaypoint(Color.BLACK, 20, loc2));
			}
		}
		// Informa o resultado para o gerenciador
		lstPoints.addAll(lstParadas);
		lstPoints.addAll(lstFurtos);
		gerenciador.setPontos(lstPoints);
		// Informa o intervalo de valores gerados, para calcular a cor de cada
		// ponto
		double menorValor = 1; // exemplo
		double maiorValor = 20; // exemplo
		gerenciador.setIntervaloValores(menorValor, maiorValor);
		
		//System.out.println(rd.getNomeParada(216));
		this.repaint();
	}

	/*
	 * private int numOcorr(){ ReadCSV rd = new ReadCSV(); rd.run(); int
	 * count=0; for(int i=0; i< rd.getSize();i++){ double lat_a =
	 * rd.getCoordX(i); double lng_a = rd.getCoordY(i); double lat_b =
	 * gerenciador.getSelecaoCentro().getLatitude(); double lng_b =
	 * gerenciador.getSelecaoCentro().getLongitude();
	 * 
	 * if(gps2m(lat_a, lng_a, lat_b, lng_b)< 1500){ count++;
	 * System.out.println(count); } } return count; }
	 */
	private int numOcorr(double lat_a, double lng_a, double lat_b, double lng_b) {
		//
		if (gps2m(lat_a, lng_a, lat_b, lng_b) < 1500) {
			count++;
		}
		return count;
	}

	private void resetCount() {
		count = 0;
	}

	private int nivCriminalidade(double lng_b, double lat_b) { // passar a
																// latitude e
																// longitude por
																// pa
		int nivC = 0;
		for (int i = 0; i < rd.getSize(); i++) {
			double lat_a = rd.getCoordX(i);
			double lng_a = rd.getCoordY(i);
			if (gps2m(lat_b, lng_b, lat_a, lng_a) < 1500) {
				nivC++;
			}
		}
		return nivC;
	}

	/*
	 * private  paradaMaisProxima(){ for(int i=0;i<=rd.getSize();i++){ if
	 * } }
	 */
	// for(int i=0;i<rd.g)
	private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
		float pk = (float) (180 / 3.14169);

		double a1 = lat_a / pk;
		double a2 = lng_a / pk;
		double b1 = lat_b / pk;
		double b2 = lng_b / pk;

		double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
		double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
		double t3 = Math.sin(a1) * Math.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);

		return 6366000 * tt;
	}

	private String maiorCriminalidade() {
		String crim="";
		for (int i = 0; i < rd.getSizeParadas() - 1; i++) {
			if (nivCriminalidade(rd.getLngParada(i), rd.getLatParada(i)) > nivCriminalidade(
					rd.getLngParada(i + 1), rd.getLatParada(i + 1))) {
				crim = rd.getTipoParada(i) + " "+rd.getNomeParada(i);
			//	System.out.println(nivCriminalidade(rd.getLngParada(i), rd.getLatParada(i)));
			} else {
				crim = rd.getTipoParada(i + 1) + " "+rd.getNomeParada(i + 1);
			//	System.out.println(nivCriminalidade(rd.getLngParada(i+1), rd.getLatParada(i+1)));
			}
		}
		return crim;
	}
	
	private double getCriminalidade(){
		double criminalidade;
		criminalidade = nivCriminalidade(rd.getLngParada(1), rd.getLatParada(1));
		return criminalidade;
	}
	
	
	/*
	Comparator comp = new Comparator<Double>() {
        public int compare(double o, double o2) {
            float[] result1 = new float[3];
            android.location.Location.distanceBetween(myLatitude, myLongitude, o.Lat, o.Long, result1);
            Float distance1 = result1[0];

            float[] result2 = new float[3];
            android.location.Location.distanceBetween(myLatitude, myLongitude, o2.Lat, o2.Long, result2);
            Float distance2 = result2[0];

            return distance1.compareTo(distance2);
        }
    };
	*/
	   // @Override
	    public int compare(LinkedList lista1, LinkedList lista2) {
	        return Double.compare(lista1.getNC(), lista2.getNC());
	    }


	private class EventosMouse extends MouseAdapter {
		private int lastButton = -1;

		@Override
		public void mousePressed(MouseEvent e) {
			JXMapViewer mapa = gerenciador.getMapKit().getMainMap();
			GeoPosition loc = mapa.convertPointToGeoPosition(e.getPoint());
			// System.out.println(loc.getLatitude()+", "+loc.getLongitude());
			lastButton = e.getButton();
			// Botão 3: seleciona localização
			if (lastButton == MouseEvent.BUTTON3) {
				gerenciador.setSelecaoCentro(loc);
				gerenciador.setSelecaoBorda(loc);
				// gerenciador.getMapKit().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				gerenciador.getMapKit().repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			// Arrasta com o botão 3 para definir o raio
			if (lastButton == MouseEvent.BUTTON3) {
				JXMapViewer mapa = gerenciador.getMapKit().getMainMap();
				gerenciador.setSelecaoBorda(mapa.convertPointToGeoPosition(e
						.getPoint()));
				gerenciador.getMapKit().repaint();
			}
		}
	}
}
