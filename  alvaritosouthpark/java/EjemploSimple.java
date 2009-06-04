import javax.swing.*;        // For JPanel, etc.

import com.sun.image.codec.jpeg.TruncatedFileException;

import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*;      // For Ellipse2D, etc.
import java.awt.geom.Ellipse2D.Double;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math; 
import java.util.StringTokenizer;

/*  Ejemplo simple usando  java2d
 */

public class EjemploSimple extends JPanel {

	////////////////////////PARTES CABEZA
	private Ellipse2D.Double cabeza = new Ellipse2D.Double(10,10,200,150);
	private Ellipse2D.Double ojo = new Ellipse2D.Double(10,10,70,50);
	private Ellipse2D.Double punto_ojo = new Ellipse2D.Double(95,90,10,10);
	private Ellipse2D.Double punto_ojo2 = new Ellipse2D.Double(120,90,10,10);

	private CubicCurve2D.Double curva1 = new CubicCurve2D.Double(20, 0, 5, 70, 1, 110, 0, 160);
	private CubicCurve2D.Double curva2 = new CubicCurve2D.Double(20, 0, 35, 70, 39, 110, 40, 160);

	private int recursiones;
	private int c_red;
	private int c_green;
	private int c_blue;
	private int delta_red;
	private int delta_green;
	private int delta_blue;

	private void drawGrid(Graphics2D g)
	{
		int x=700;
		g.setPaint(Color.red);
		for (int i = 0; i <= x; i+=20)
			g.drawLine(i, 0, i, 700);
		for (int j = 0; j <= x; j+=20)
			g.drawLine(0, j, 700, j);

		g.setPaint(Color.black);
		g.drawLine(350,0,350,700);
		g.drawLine(0,330,700,330);
	}
	
	
//////////PARTES DE GORRO 
	private Rectangle gorro_outter = new Rectangle(0,0,220, 70);
	private CubicCurve2D.Double curva_gorro_arribla = new CubicCurve2D.Double(0,0,50,-10,170,-10,220,0);
	private Rectangle gorro_inner = new Rectangle(15, 0, 170, 50);
	private CubicCurve2D.Double curva_gorro_abajo = new CubicCurve2D.Double(15,50,65,30,135,30,185,50);
	private CubicCurve2D.Double curva_gorro_costado_der = new CubicCurve2D.Double(15,50,0,40,0,10,15,0);
	private CubicCurve2D.Double curva_gorro_costado_izq = new CubicCurve2D.Double(185,0,200,10,200,40,185,50);
	private Ellipse2D.Double arreglo_derecho = new Ellipse2D.Double(10,10,40,100);
	
	private GradientPaint gradient =
		new GradientPaint(110, 35, new Color(42, 137, 33), 220, 35, new Color(66,177,20),
				true); // true means to repeat pattern

	private void drawGorro(Graphics2D g2d)
	{
		//dibujar gorro;

		g2d.setPaint(new Color(66,177,20));

		double delta_x = 0.0;
		double delta_y = 6.0;
		double rot_arrelgo;
		
		g2d.translate(delta_x, delta_y);
		
		g2d.setPaint(gradient);

		//g2d.fill(gorro_outter);
		GeneralPath gorro = new GeneralPath();
		gorro.moveTo(0, 0);
		gorro.curveTo(50,-10,170,-10,220,0);
		gorro.lineTo(220, 70);
		gorro.lineTo(0, 70);
		gorro.lineTo(0, 0);
		g2d.fill(gorro);
		
		
		g2d.translate(-delta_x, -delta_y);

		// dibujar cosas del gorro que cuelgan derecho
		rot_arrelgo = -2.7;
		g2d.setPaint(new Color(66,177,20));
		delta_x = -5.0;
		delta_y = 179.0;
		g2d.translate(delta_x, delta_y);
		g2d.rotate(rot_arrelgo);
		g2d.fill(this.arreglo_derecho);
		g2d.rotate(-rot_arrelgo);
		g2d.translate(-delta_x, -delta_y);

		// dibujar cosas del gorro que cuelgan izq
		rot_arrelgo = 2.7;
		//g2d.setPaint(new Color(66,177,20));
		g2d.setPaint(gradient);
		delta_x = 280.0;
		delta_y = 153.0;
		g2d.translate(delta_x, delta_y);
		gradient.getPoint1().setLocation(0, 0);
		g2d.rotate(rot_arrelgo);
		g2d.fill(this.arreglo_derecho);
		g2d.rotate(-rot_arrelgo);
		g2d.translate(-delta_x, -delta_y);

		c_red = 42 ;
		c_green = 137 ;
		c_blue = 33 ;

		delta_x = 10.0;
		delta_y = 40.0;
		rot_arrelgo = -2.5;

		g2d.translate(delta_x, delta_y);
		//g2d.setPaint(gradient);
		g2d.setPaint(new Color(c_red, c_green, c_blue));
		Area a = new Area(gorro_inner);
		a.subtract(new Area(curva_gorro_abajo));
		g2d.fill(a);
		g2d.fill(curva_gorro_costado_der);
		g2d.fill(curva_gorro_costado_izq);
		g2d.translate(-delta_x, -delta_y);

	}

	private void drawCabeza(Graphics2D g2d)
	{
		//dibujar cabeza
		g2d.setPaint(new Color(252,229,188));

		double delta_x = 0.0;
		double delta_y = 35.0;
		double rot_ojo = 1.9;

		g2d.translate(delta_x, delta_y);
		g2d.fill(cabeza);
		g2d.translate(-delta_x, -delta_y);

		delta_x = 135.0;
		delta_y = 65.0;    
		g2d.translate(delta_x, delta_y);
		g2d.rotate(rot_ojo);
		g2d.setPaint(Color.white);
		g2d.fill(ojo);
		g2d.rotate(-rot_ojo);
		g2d.translate(-delta_x, -delta_y);

		g2d.setPaint(Color.black);
		g2d.fill(this.punto_ojo);
		g2d.setPaint(Color.white);

		delta_x = 115.0; 
		delta_y = 150.0;
		g2d.translate(delta_x, delta_y);
		g2d.rotate(-rot_ojo);
		g2d.fill(ojo);    
		g2d.rotate(rot_ojo);
		g2d.translate(-delta_x, -delta_y);

		g2d.setPaint(Color.black);
		g2d.fill(this.punto_ojo2);
	}

	private int[] xPoints = { 20, 20, 40};
	private int[] yPoints = { 0, 160, 160 };
	private int[] xPoints2 = { 20, 20, 0};
	private int[] yPoints2 = { 0, 160, 160 };
	private int[] xPoints3 = { 20, 20, 160, 160};
	private int[] yPoints3 = { 0, 160, 160, 0 };
	private Polygon cuerpo = new Polygon(xPoints, yPoints, 3);
	private Polygon cuerpo2 = new Polygon(xPoints2, yPoints2, 3);
	private Polygon cuerpo3 = new Polygon(xPoints3, yPoints3, 4);
	
	private CubicCurve2D.Double cuerpo_abajo = new CubicCurve2D.Double(80,30,90,40,250,40,260,30);
	
	private GradientPaint gradient_cuerpo =
		new GradientPaint(160, 10, new Color(255, 104, 1), 230, 10, new Color(255,149,79),
				true); // true means to repeat pattern
	private GradientPaint gradient_cuerpo2 =
		new GradientPaint(100, 0, new Color(255, 104, 1), 170, 0, new Color(255,149,79),
				true); // true means to repeat pattern
	
	private void drawCuerpo(Graphics2D g2d)
	{
		double delta_x = 20.0;
		double delta_y = 170.0;

		g2d.setPaint(new Color(255,104,1));
		g2d.translate(delta_x, delta_y);

		g2d.fill(curva1);
		g2d.fill(cuerpo2);
		g2d.setPaint(gradient_cuerpo);
		g2d.fill(cuerpo3);
		g2d.setPaint(new Color(255,104,1));

		g2d.translate(-delta_x, -delta_y);

		delta_x += 140;
		g2d.translate(delta_x, delta_y);
		g2d.fill(curva2);
		
		g2d.fill(cuerpo);
		
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = -60.0;
		delta_y = 300.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(gradient_cuerpo2);
		g2d.fill(cuerpo_abajo);
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = 37.0;
		delta_y = 163.0;
		g2d.translate(delta_x, delta_y);
		
		//adorno cuello
		g2d.setPaint(new Color(66,177,20));
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(0,10);
	    newshape.curveTo(20.0, 25.0, 50.0, 35.0, 70.0, 30.0);
	    newshape.curveTo(73,25,74,37,70,40);
	    newshape.curveTo(50, 45.0, 20.0, 35.0, 0.0, 20.0);
	    newshape.curveTo(-3,17,-3,13,0,10);
	    g2d.fill(newshape);
	    
	    g2d.translate(-delta_x, -delta_y);
	    delta_x = 114.0;
		delta_y = 163.0;
		g2d.translate(delta_x, delta_y);

	    GeneralPath newshape2 = new GeneralPath();
	    newshape2.moveTo(70,10);
	    newshape2.curveTo(50.0, 25.0, 20.0, 35.0, 0.0, 30.0);
	    newshape2.curveTo(-3, 25, -3, 35,0, 40);
	    newshape2.curveTo(20, 40.0, 50.0, 35.0, 70.0, 20.0);
	    newshape2.curveTo(74, 17, 74, 15, 70, 10);
	    
	    g2d.fill(newshape2);
	    g2d.setPaint(Color.black);
	    setFont(new Font("Arial", Font.PLAIN , 60));
	    g2d.drawString("INCO", -76, 90);
	    g2d.drawString("2009", -70, 150);
		
	    g2d.translate(-delta_x, -delta_y);
	}
	
	
	
	private CubicCurve2D.Double zapato_set1 = new CubicCurve2D.Double(-10,40,20,20,55,20,75,40);
	private CubicCurve2D.Double zapato2_set1 = new CubicCurve2D.Double(65,40,85,20,130,20,150,40);
	
	private void drawPantalon_derecho_set1(Graphics2D g2d)
	{
		double delta_x = 40.0;
		double delta_y = 330.0;

		g2d.translate(delta_x, delta_y);
		
		c_red = 5 ;
		c_green = 52;
		c_blue = 62 ;
		
		g2d.setPaint(new Color(c_red,c_green,c_blue));
		g2d.fill(new Rectangle(0,0,70,40));
		
		//12 174 207
		recursiones = 50;
		delta_red = (12 -c_red) / recursiones;
		delta_green = (174 -c_green) / recursiones;
		delta_blue = (207 -c_blue) / recursiones;
		double delta_ancho = (70-5)/recursiones;
		
		for(int i=0; i<recursiones; i++)
		{
			g2d.setPaint(new Color(c_red + (delta_red*i),c_green + (delta_green*i),c_blue + (delta_blue*i)));
			//if (i == recursiones - 1)
			//	g2d.setPaint(Color.red);
			g2d.fill(new Rectangle2D.Double((delta_ancho*(i+1))/2, 0, 70 - (delta_ancho*i), 40));
		}
		
		
		g2d.setPaint(Color.red);
		g2d.fill(zapato_set1);
		
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawPantalon_izquierdo_set1(Graphics2D g2d)
	{
		double delta_x = 40.0;
		double delta_y = 330.0;

		g2d.translate(delta_x, delta_y);
		
		c_red = 5 ;
		c_green = 52;
		c_blue = 62 ;
		
		//12 174 207
		recursiones = 50;
		delta_red = (12 -c_red) / recursiones;
		delta_green = (174 -c_green) / recursiones;
		delta_blue = (207 -c_blue) / recursiones;
		double delta_ancho = (70-5)/recursiones;
		
		
		g2d.setPaint(new Color(c_red,c_green,c_blue));
		g2d.fill(new Rectangle(70,0,70,40));
		
		for(int i=0; i<recursiones; i++)
		{
			g2d.setPaint(new Color(c_red + (delta_red*i),c_green + (delta_green*i),c_blue + (delta_blue*i)));
			//if (i == recursiones - 1)
			//	g2d.setPaint(Color.red);
			g2d.fill(new Rectangle2D.Double(70+(delta_ancho*(i+1))/2, 0, 70 - (delta_ancho*i), 40));
		}
		
		g2d.setPaint(Color.red);
		g2d.fill(zapato2_set1);
		g2d.translate(-delta_x, -delta_y);
	}
	
	
	private CubicCurve2D.Double zapato_set2 = new CubicCurve2D.Double(-10,40,20,20,55,20,75,40);
	private CubicCurve2D.Double zapato2_set2 = new CubicCurve2D.Double(65,40,85,20,130,20,150,40);
	
	private int[] pantderx = { 0, 70, 70, 35, 0};
	private int[] pantdery = { 0, 0, 11, 40, 40};
	private Polygon pantderset2 = new Polygon(pantderx, pantdery, 5);
	private void drawPantalon_derecho_set2(Graphics2D g2d)
	{
		double delta_x = 40.0;
		double delta_y = 330.0;

		g2d.translate(delta_x, delta_y);
		
		c_red = 5 ;
		c_green = 52;
		c_blue = 62 ;
		
		g2d.setPaint(new Color(c_red,c_green,c_blue));
		g2d.fill(pantderset2);
		
		//12 174 207
		recursiones = 50;
		delta_red = (12 -c_red) / recursiones;
		delta_green = (174 -c_green) / recursiones;
		delta_blue = (207 -c_blue) / recursiones;
		double delta_ancho = (70-5)/recursiones;
		
		for(int i=0; i<recursiones; i++)
		{
			g2d.setPaint(new Color(c_red + (delta_red*i),c_green + (delta_green*i),c_blue + (delta_blue*i)));
			Rectangle2D.Double rec = new Rectangle2D.Double(-10 +((delta_ancho)*(i+1)/2), 0, 70 - (delta_ancho*i), 40 );
			Area a = new Area(rec);
			a.intersect(new Area(pantderset2));
			g2d.fill(a);
		}
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = 70;
		
		g2d.translate(delta_x, delta_y);
		
		g2d.setPaint(Color.red);
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(20,27);
	    newshape.lineTo(20, 40);
	    newshape.lineTo(-60, 40);
	    newshape.curveTo(-20.0, 20.0, -10.0, 22.0, 20.0, 27.0);
	    
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
	}
	
	private int[] pantizqx = { 0, 0, 35, 70, 70};
	private int[] pantizqy = { 0, 11, 40, 40, 0};
	private Polygon pantizqset2 = new Polygon(pantizqx, pantizqy, 5);
	private void drawPantalon_izquierdo_set2(Graphics2D g2d)
	{
		double delta_x = 110.0;
		double delta_y = 330.0;

		g2d.translate(delta_x, delta_y);
		
		c_red = 5 ;
		c_green = 52;
		c_blue = 62 ;
		
		g2d.setPaint(new Color(c_red,c_green,c_blue));
		g2d.fill(pantizqset2);
		
		//12 174 207
		recursiones = 50;
		delta_red = (12 -c_red) / recursiones;
		delta_green = (174 -c_green) / recursiones;
		delta_blue = (207 -c_blue) / recursiones;
		double delta_ancho = (70-5)/recursiones;
		
		for(int i=0; i<recursiones; i++)
		{
			g2d.setPaint(new Color(c_red + (delta_red*i),c_green + (delta_green*i),c_blue + (delta_blue*i)));
			//if (i == recursiones - 1)
			//	g2d.setPaint(Color.red);
			Rectangle2D.Double rec = new Rectangle2D.Double(10+((delta_ancho)*(i+1)/2), 0, 70 - (delta_ancho*i), 40 );
			Area a = new Area(rec);
			a.intersect(new Area(pantizqset2));
			g2d.fill(a);
		}
		
		g2d.setPaint(Color.red);
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(20,27);
	    newshape.lineTo(20, 40);
	    newshape.lineTo(100, 40);
	    newshape.curveTo(80.0, 20.0, 50.0, 22.0, 20.0, 27.0);
	    
		g2d.fill(newshape);
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawBrazoDerecho_set1(Graphics2D g2d)
	{
		double delta_x = 17.0;
		double delta_y = 180.0;
		
		g2d.setPaint(Color.black);
		
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(17,0);
	    newshape.curveTo(-20.0, 60.0, -23.0, 100.0, -25.0, 120.0);
	    newshape.lineTo(3, 120);
	    newshape.curveTo(15.0, 45.0, 15.0, 15.0, 23.0, 4.0);
	    newshape.lineTo(17.0, 0);

		g2d.translate(delta_x, delta_y);
		
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = -10.0;
		delta_y = 280.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(new Color(66,177,21));
		g2d.fill(new Ellipse2D.Double(0,0,35,35));
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawBrazoDerecho_set2(Graphics2D g2d)
	{
		double delta_x = 20.0;
		double delta_y = 172.0;
		
		g2d.setPaint(Color.black);
		
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(4,10);
	    newshape.curveTo(-40.0, 40.0, -50.0, 80.0, -50.0, 100.0);
	    newshape.lineTo(-20, 100);
	    newshape.curveTo(-12.0, 80.0, -10.0, 70.0, 12.0, 40.0);
	    newshape.lineTo(20.0, 0);

		g2d.translate(delta_x, delta_y);
		
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = -32.0;
		delta_y = 262.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(new Color(66,177,21));
		g2d.fill(new Ellipse2D.Double(0,0,35,35));
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawBrazoDerecho_set3(Graphics2D g2d)
	{
		double delta_x = 20.0;
		double delta_y = 172.0;
		
		g2d.setPaint(Color.black);
		
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(4,10);
	    newshape.curveTo(-40.0, 40.0, -40.0, 30.0, -60.0, 10.0);
	    newshape.lineTo(-70, 35);
	    newshape.curveTo(-30.0, 75.0, -10.0, 65.0, 12.0, 40.0);
	    newshape.lineTo(20.0, 0);

		g2d.translate(delta_x, delta_y);
		
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = -65.0;
		delta_y = 177.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(new Color(66,177,21));
		g2d.fill(new Ellipse2D.Double(0,0,30,30));
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawBrazoIzquierdo_set1(Graphics2D g2d)
	{
		double delta_x = 157.0;
		double delta_y = 180.0;
		
		g2d.setPaint(Color.black);
		
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(29,0);
	    newshape.curveTo(66.0, 60.0, 69.0, 100.0, 71.0, 120.0);
	    newshape.lineTo(43, 120);
	    newshape.curveTo(31.0, 45.0, 31.0, 15.0, 23.0, 4.0);
	    newshape.lineTo(29.0, 0);

		g2d.translate(delta_x, delta_y);
		
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = 195.0;
		delta_y = 280.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(new Color(66,177,21));
		g2d.fill(new Ellipse2D.Double(0,0,35,35));
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawBrazoIzquierdo_set2(Graphics2D g2d)
	{
		double delta_x = 160.0;
		double delta_y = 172.0;
		
		g2d.setPaint(Color.black);
		
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(36,10);
	    newshape.curveTo(80.0, 40.0, 90.0, 80.0, 90.0, 100.0);
	    newshape.lineTo(60, 100);
	    newshape.curveTo(52.0, 80.0, 50.0, 70.0, 28.0, 40.0);
	    newshape.lineTo(20.0, 0);

		g2d.translate(delta_x, delta_y);
		
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = 218.0;
		delta_y = 262.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(new Color(66,177,21));
		g2d.fill(new Ellipse2D.Double(0,0,35,35));
		g2d.translate(-delta_x, -delta_y);
	}
	
	private void drawBrazoIzquierdo_set3(Graphics2D g2d)
	{
		double delta_x = 160.0;
		double delta_y = 172.0;
		
		g2d.setPaint(Color.black);
		
		GeneralPath newshape = new GeneralPath();
	    newshape.moveTo(36,10);
	    newshape.curveTo(80.0, 40.0, 80.0, 30.0, 100.0, 10.0);
	    newshape.lineTo(110, 35);
	    newshape.curveTo(70.0, 75.0, 50.0, 65.0, 28.0, 40.0);
	    newshape.lineTo(20.0, 0);

		g2d.translate(delta_x, delta_y);
		
		g2d.fill(newshape);
		
		g2d.translate(-delta_x, -delta_y);
		
		delta_x = 250.0;
		delta_y = 177.0;
		g2d.translate(delta_x, delta_y);
		g2d.setPaint(new Color(66,177,21));
		g2d.fill(new Ellipse2D.Double(0,0,30,30));
		g2d.translate(-delta_x, -delta_y);
	}
	
	private CubicCurve2D.Double boca_set1 = new CubicCurve2D.Double(85,40,105,60,110,20,130,40);
	private void drawBoca(Graphics2D g2d, int set)
	{
		double delta_x = 5.0;
		double delta_y = 120.0;
		
		g2d.translate(delta_x, delta_y);
		
		Stroke old = g2d.getStroke();
		BasicStroke s = new BasicStroke(2);
		
		
		g2d.setStroke(s);
		g2d.setPaint(Color.black);
		g2d.draw(boca_set1);
		g2d.translate(-delta_x, -delta_y);
		g2d.setStroke(old);
	}

	public void paintComponent(Graphics g) {
		
		cargarDatosArchivo("test.txt");
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		drawGrid(g2d);

		//g2d.scale(0.5, 0.5);
		//g2d.scale(1.5, 1.5);

		g2d.translate(100.0, 100.0);

		drawPantalon_derecho_set2(g2d);
		drawPantalon_izquierdo_set2(g2d);
		drawCuerpo(g2d);
		drawCabeza(g2d);
		drawGorro(g2d);
		
		//drawBrazoDerecho_set1(g2d);
		//drawBrazoDerecho_set2(g2d);
		drawBrazoDerecho_set3(g2d);
		drawBrazoIzquierdo_set3(g2d);
		
		drawBoca(g2d, 1);
	}
	
	public static void cargarDatosArchivo(String name)
	{
		try {
			FileReader fr = new FileReader(name);
			BufferedReader entrada = new BufferedReader(fr);
			String s;
			int cuentaLineas=0;
			while((s = entrada.readLine()) != null){
				StringTokenizer t;
				if (cuentaLineas == 6)
					t = new StringTokenizer(s, "\"");
				else
					t = new StringTokenizer(s, " ");
				String campo;
				int cuentaCampos=0;
				while(t.hasMoreTokens()){
					campo=t.nextToken();
					System.out.println(campo);
					cuentaCampos++;
				}
				cuentaLineas++;

			}
		}
		catch(java.io.FileNotFoundException fnfex) {
			System.out.println("se presento el error: "+fnfex.toString());
		}
		catch(java.io.IOException fnfex) {
			System.out.println("se presento el error: "+fnfex.toString());
		}
	}

	public static void main(String[] args) {
		WindowUtilities.openInJFrame(new EjemploSimple(), 700, 700);
	}
}
