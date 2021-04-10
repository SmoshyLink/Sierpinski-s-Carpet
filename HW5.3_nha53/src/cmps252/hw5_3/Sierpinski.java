
/*
 * 
 *  Code by Nizar Masri 
 *  
*/

package cmps252.hw5_3;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sierpinski extends JPanel {
	Graphics g;
	
	public void paintComponent (Graphics g) {
		this.g = g;
		
		// Set the number of recursions 
		int recursions = 3;
		
		f(new Point(0, 0), new Point(600, 0), new Point(600, 600), new Point(0, 600), recursions);
	}
	
	public static void main (String args[]) {
		JFrame jf = new JFrame();
		Sierpinski s = new Sierpinski();
		jf.setSize(620, 650);
		jf.setVisible(true);
		jf.add(s);
	}
	
	public void f (Point p1, Point p2, Point p3, Point p4, int level) {
		Point p5 = oneThird(p1, p2);
		Point p6 = twoThirds(p1, p2);
		Point p7 = oneThird(p2, p3);
		Point p8 = twoThirds(p2, p3);
		Point p9 = twoThirds(p4, p3);
		Point p10 = oneThird(p4, p3);
		Point p11 = twoThirds(p1, p4);
		Point p12 = oneThird(p1, p4);
		Point p13 = new Point(p5.x, p12.y);
		Point p14 = new Point(p6.x, p12.y);
		Point p15 = new Point(p6.x, p11.y);
		Point p16 = new Point(p5.x, p11.y);

		/*
		 * To analyze the values
		 * 
		Point[] pArr = new Point[] {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16};		
		for(int i = 0; i < 16; i++) {
			int j = i + 1;
			System.out.println("p" + j + " at " + level + ": " + pArr[i].x + " " + pArr[i].y);
		}
		System.out.println("\n");
		*/
		
		squareFilled(p13, p14, p15, p16);
		
		if(level-- == 0)
			return;
		
		f(p1, p5, p13, p12, level); // 1
		f(p5, p6, p14, p13, level); // 2
		f(p6, p2, p7, p14, level); // 3 
		f(p14, p7, p8, p15, level); // 4
		f(p15, p8, p3, p9, level); // 5
		f(p16, p15, p9, p10, level); // 6
		f(p11, p16, p10, p4, level); // 7
		f(p12, p13, p16, p11, level); // 8
		
	}

	private Point oneThird (Point p1, Point p2) {
		return new Point((p1.x + (p2.x - p1.x)/3), (p1.y + (p2.y - p1.y)/3));
	}
	
	private Point twoThirds (Point p1, Point p2) {
		return new Point((p1.x + 2*(p2.x - p1.x)/3), (p1.y + 2*(p2.y - p1.y)/3));
	}
	
	private void squareFilled (Point p1, Point p2, Point p3, Point p4) {
		Polygon p = new Polygon();
		p.addPoint(p1.x, p1.y);
		p.addPoint(p2.x, p2.y);
		p.addPoint(p3.x, p3.y);
		p.addPoint(p4.x, p4.y);
		g.fillPolygon(p);
	}
}
