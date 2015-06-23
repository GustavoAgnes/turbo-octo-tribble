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
    pucrs.alpro2.algoritmos.AlgoritmosGeograficos Ag = new pucrs.alpro2.algoritmos.AlgoritmosGeograficos();
    
	//testeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
	private double GreatCircleDistanceInMeters(double aLong1,double aLat1,double aLong2,double aLat2)
    {
    double cos_angle = Math.sin(aLat1) * Math.sin(aLat2) + Math.cos(aLat1) * Math.cos(aLat2) * Math.cos(aLong2 - aLong1);
    if (cos_angle >= 1)
        return 0;

    double angle = Math.acos(cos_angle);
    return angle * 6378137;
    }
	//
	
    
    
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
<<<<<<< HEAD
    	//int raio = gerenciador.getRaio();        

=======
    	int raio = gerenciador.getRaio();    
        System.out.println("RAIO = "+raio);
        System.out.println("Centro?: "+gerenciador.getSelecaoCentro());
        /*
        if((-30.064064 - (-30.047364752836465))**2 + (-51.1946068 -(-51.18075370788574))**2 >= (raio**2)){
            System.out.println("Dentro do circulo/exemplo/Rua Guilherme Alves");
        }
        //-30.0490475;-51.1836586
        */ // não funcionando
      
        //
>>>>>>> 0ee5e547cf199f4979c9d600c4d127515b5441dd
        // Lista para armazenar o resultado da consulta
        List<MyWaypoint> lstPoints = new ArrayList<>();  
        //testes
        /*
        ReadCSV rd = new ReadCSV();
        rd.run();
        System.out.println(rd.getCoordX(0));
        System.out.println(rd.getCoordY(0));
        */
        //
        ReadCSV rd = new ReadCSV();
        rd.run();

        // Exemplo:
        double valor = 250; // ex: valor da consulta (criminalidade ou distância)
        GeoPosition loc = new GeoPosition(-30.05, -51.18); // ex: localização da parada
       // lstPoints.add(new MyWaypoint(Color.BLUE, valor, loc));         
        lstPoints.add(new MyWaypoint(Color.GREEN,valor, loc));
<<<<<<< HEAD
        for(int i=0;i<rd.getSize();i++){
     	   GeoPosition loc2 = new GeoPosition(rd.getCoordX(i), rd.getCoordY(i));
     	   lstPoints.add(new MyWaypoint(Color.BLUE,valor, loc2));
     	   double x1 = rd.getCoordX(i);
     	   double xC = gerenciador.getSelecaoCentro().getLatitude();
     	   double x2 = rd.getCoordY(i);
     	   double yC = gerenciador.getSelecaoCentro().getLongitude();
     	   GeoPosition ponto1 = new GeoPosition(x1,x2);
     	   GeoPosition ponto2 = new GeoPosition(xC,yC);
     	  // System.out.println("Distancia entre os pontos: "+Ag.calcDistancia(ponto1, ponto2));
     	//  System.out.println("Raio em radianos: "+Math.toRadians(gerenciador.getRaio()));
        }

        /////////////////////////////// lstPoints.add(new MyWaypoint(Color.BLUE,valor, loc2));

=======
       for(int i=0;i<rd.getSize();i++){
    	   GeoPosition loc2 = new GeoPosition(rd.getCoordX(i), rd.getCoordY(i));
    	   lstPoints.add(new MyWaypoint(Color.BLUE,valor, loc2));
       }
>>>>>>> 0ee5e547cf199f4979c9d600c4d127515b5441dd
        // Informa o resultado para o gerenciador
        gerenciador.setPontos(lstPoints);
        // Informa o intervalo de valores gerados, para calcular a cor de cada ponto
        double menorValor = 15;  // exemplo
        double maiorValor = 250; // exemplo
        gerenciador.setIntervaloValores(menorValor, maiorValor);        
        
        this.repaint();
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
