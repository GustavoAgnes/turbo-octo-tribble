package pucrs.alpro2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.WaypointRenderer;

import pucrs.alpro2.algoritmos.AlgoritmosGeograficos;

/**
 * Classe para gerenciar um mapa
 *
 * @author Marcelo Cohen
 */
public class GerenciadorMapa {

    final JXMapKit jXMapKit;
    private WaypointPainter<MyWaypoint> pontosPainter;
    private GeoPosition centro;
    
    private GeoPosition selCentro;
    private GeoPosition selBorda;
    
    private Color corMenor;
    private Color corMaior;
    
    private double valorMenor;
    private double valorMaior;

    public enum FonteImagens {

        OpenStreetMap, VirtualEarth
    };

    /*
     * Cria um gerenciador de mapas, a partir de uma posi��o e uma fonte de imagens
     * 
     * @param centro centro do mapa
     * @param fonte fonte das imagens (FonteImagens.OpenStreetMap ou FonteImagens.VirtualEarth)
     */
    public GerenciadorMapa(GeoPosition centro, FonteImagens fonte) {
        jXMapKit = new JXMapKit();
        TileFactoryInfo info = null;
        if (fonte == FonteImagens.OpenStreetMap) {
            info = new OSMTileFactoryInfo();            
        } else {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapKit.setTileFactory(tileFactory);

        // Ajustando a opacidade do mapa (50%)
        jXMapKit.getMainMap().setAlpha(0.5f);

        // Ajustando o n�vel de zoom do mapa
        jXMapKit.setZoom(4);
        // Informando o centro do mapa
        jXMapKit.setAddressLocation(centro);
        // Indicando que n�o desejamos ver um marcador nessa posi��o
        jXMapKit.setAddressLocationShown(false);

        // Criando um objeto para "pintar" os pontos
        pontosPainter = new WaypointPainter<MyWaypoint>();               

        // Criando um objeto para desenhar os pontos
        pontosPainter.setRenderer(new WaypointRenderer<MyWaypoint>() {

            @Override
            public void paintWaypoint(Graphics2D g, JXMapViewer viewer, MyWaypoint wp) {

                // Desenha cada waypoint como um pequeno c�rculo            	
                Point2D point = viewer.getTileFactory().geoToPixel(wp.getPosition(), viewer.getZoom());
                int x = (int) point.getX();
                int y = (int) point.getY();
                //g = (Graphics2D) g.create();
                
                // Obt�m a cor do waypoint
                Color cor = wp.getColor();
                // Normaliza os valores entre 0 (m�nimo) e 1 (m�ximo)
                float fator = (float) ((wp.getValue() - valorMenor) / (valorMaior-valorMenor));
                // Seta a opacidade da cor usando o fator de import�ncia calculado (0=m�nimo,1=m�ximo)
                g.setColor(new Color(cor.getRed()/255.0f, cor.getGreen()/255.0f, cor.getBlue()/255.0f, fator));
                g.fill(new Ellipse2D.Float(x - 3, y - 3, 6, 6));
            }
        });

        // Criando um objeto para desenhar os elementos de interface
        // (c�rculo de sele��o, etc)
        Painter<JXMapViewer> guiPainter = new Painter<JXMapViewer>() {
            public void paint(Graphics2D g, JXMapViewer map, int w, int h) {            	
            	if(selCentro == null || selBorda == null)
            		return;                	
            	Point2D point = map.convertGeoPositionToPoint(selCentro);
            	Point2D pont2 = map.convertGeoPositionToPoint(selBorda);
            	int x = (int) point.getX();
                int y = (int) point.getY();                
                int raio = (int) Math.sqrt(Math.pow(point.getX()-pont2.getX(),2)+
                		Math.pow(point.getY()-pont2.getY(),2));
                int r = raio/2;
            	g.setColor(Color.BLUE); // mudar cor
            	g.setStroke(new BasicStroke(2));
            	g.draw(new Ellipse2D.Float(x-r, y-r, raio, raio));
            	g.drawString(getRaio()+" metros", x+r, y+r);
            	g.fill(new Ellipse2D.Float(x - 3, y - 3, 6, 6));                
            }
        };
        
        // Um CompoundPainter permite combinar v�rios painters ao mesmo tempo...
        CompoundPainter cp = new CompoundPainter();
        cp.setPainters(pontosPainter, guiPainter);

        jXMapKit.getMainMap().setOverlayPainter(cp);       
        
        selCentro = null;
        selBorda = null;        
    }

    /*
     * Informa a localiza��o do ponto central da regi�o
     * @param ponto central
     */
    public void setSelecaoCentro(GeoPosition sel) {
    	this.selCentro = sel;
    }
    
    public GeoPosition getSelecaoCentro() {
		return selCentro;
	}
    
    /*
     * Informa a localiza��o de um ponto da borda da regi�o
     * Utilizamos isso para definir o raio da regi�o e desenhar o c�rculo 
     * @param ponto da borda
     */
    public void setSelecaoBorda(GeoPosition sb) {
    	this.selBorda = sb;
    }
        
    // Retorna o raio da regi�o selecionada (em metros)
    public int getRaio() {    
    	return (int) (AlgoritmosGeograficos.calcDistancia(selBorda, selCentro)*500);
    }    
    
    public void setIntervaloValores(double valMenor, double valMaior) {
    	this.valorMenor = valMenor;
		this.valorMaior = valMaior;
		//System.out.println(valMenor+"->"+valMaior);
	}    
    
    /*
     * Informa os pontos a serem desenhados (precisa ser chamado a cada vez que mudar)
     * @param lista lista de pontos (objetos MyWaypoint)
     */
    public void setPontos(List<MyWaypoint> lista) {
        // Criando um conjunto de pontos
        Set<MyWaypoint> pontos = new HashSet<MyWaypoint>(lista);
        // Informando o conjunto ao painter
        pontosPainter.setWaypoints(pontos);
    }

    /*
     * Retorna a refer�ncia ao objeto JXMapKit, para ajuste de par�metros (se for o caso)
     * @returns refer�ncia para objeto JXMapKit em uso
     */
    public JXMapKit getMapKit() {
        return jXMapKit;
    }
}
