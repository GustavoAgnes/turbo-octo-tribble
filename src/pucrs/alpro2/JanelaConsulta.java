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
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Sandro
 */
public class JanelaConsulta extends javax.swing.JFrame {

    private GerenciadorMapa gerenciador;
    private EventosMouse mouse;
    
    private JPanel painelMapa;
    private JPanel painelLateral;
    pucrs.alpro2.algoritmos.AlgoritmosGeograficos  Ag = new pucrs.alpro2.algoritmos.AlgoritmosGeograficos();
    
    /**
     * Creates new form JanelaConsulta
     */
    public JanelaConsulta() {
    	super();    	
        //initComponents();

        GeoPosition poa = new GeoPosition(-30.05, -51.18);
        gerenciador = new GerenciadorMapa(poa, GerenciadorMapa.FonteImagens.VirtualEarth);
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
        painelLateral.add(btnNewButton);
        
        this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    private void consulta(java.awt.event.ActionEvent evt) {
        // Para obter o centro e o raio, usar como segue:
    	GeoPosition centro = gerenciador.getSelecaoCentro();
    	int raio = gerenciador.getRaio();    
        // Lista para armazenar o resultado da consulta
        List<MyWaypoint> lstPoints = new ArrayList<>();  
        ReadCSV rd = new ReadCSV();
        rd.run();

        // Exemplo:
        double valor = 250; // ex: valor da consulta (criminalidade ou distância)
        for(int i=0;i<rd.getSizeParadas();i++){
    double lat2_a = rd.getLatParada(i);
    double lng2_a = rd.getLngParada(i);
    double lat_b = gerenciador.getSelecaoCentro().getLatitude();
    double lng_b = gerenciador.getSelecaoCentro().getLongitude();
            GeoPosition loc3 = new GeoPosition(rd.getLatParada(i), rd.getLngParada(i));
            if(gps2m(lat2_a, lng2_a, lat_b, lng_b)< gerenciador.getRaio()){
            lstPoints.add(new MyWaypoint(Color.BLACK,valor,loc3));
            }
        }
       for(int i=0;i<rd.getSize();i++){
    double lat_a = rd.getCoordX(i);
    double lng_a = rd.getCoordY(i);
    double lat_b = gerenciador.getSelecaoCentro().getLatitude();
    double lng_b = gerenciador.getSelecaoCentro().getLongitude();
 
    	   GeoPosition loc2 = new GeoPosition(rd.getCoordX(i), rd.getCoordY(i));
           if(gps2m(lat_a, lng_a, lat_b, lng_b)< gerenciador.getRaio()){
               lstPoints.add(new MyWaypoint(Color.RED,valor, loc2));
       }
       }
        // Informa o resultado para o gerenciador
        gerenciador.setPontos(lstPoints);
        // Informa o intervalo de valores gerados, para calcular a cor de cada ponto
        double menorValor = 15;  // exemplo
        double maiorValor = 250; // exemplo
        gerenciador.setIntervaloValores(menorValor, maiorValor);        
        this.repaint();
    }
       private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
    float pk = (float) (180/3.14169);

    double a1 = lat_a / pk;
    double a2 = lng_a / pk;
    double b1 = lat_b / pk;
    double b2 = lng_b / pk;

    double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
    double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
    double t3 = Math.sin(a1)*Math.sin(b1);
    double tt = Math.acos(t1 + t2 + t3);

    return 6366000*tt;
}
    
    private class EventosMouse extends MouseAdapter
    {
    	private int lastButton = -1;    	
    	
    	@Override
    	public void mousePressed(MouseEvent e) {
    		JXMapViewer mapa = gerenciador.getMapKit().getMainMap();
    		GeoPosition loc = mapa.convertPointToGeoPosition(e.getPoint());
//    		System.out.println(loc.getLatitude()+", "+loc.getLongitude());
    		lastButton = e.getButton();
    		// Botão 3: seleciona localização
    		if(lastButton==MouseEvent.BUTTON3) {  			
    			gerenciador.setSelecaoCentro(loc);
    			gerenciador.setSelecaoBorda(loc);
    			//gerenciador.getMapKit().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    			gerenciador.getMapKit().repaint();    			
    		}
    	}    
    	
    	public void mouseDragged(MouseEvent e) {
    		// Arrasta com o botão 3 para definir o raio
    		if(lastButton ==  MouseEvent.BUTTON3) {    			
    			JXMapViewer mapa = gerenciador.getMapKit().getMainMap();
    			gerenciador.setSelecaoBorda(mapa.convertPointToGeoPosition(e.getPoint()));
    			gerenciador.getMapKit().repaint();
    		}    			
    	}
    } 	
}
