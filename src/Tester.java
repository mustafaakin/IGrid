
public class Tester {

	public static void main(String[] args) {
		Point p1 = new Point(0.5, 0.9, 0.6, 0.7);
		Point p2 = new Point(0.2, 0.6, 0.4, 0.6);
		Point p3 = new Point(0.1, 0.8, 0.3, 0.1);
		Point p4 = new Point(0.5, 0.3, 0.2, 0.4);
		Point p5 = new Point(0.6, 0.1, 0.4, 0.6);
		Point p6 = new Point(0.3, 0.5, 0.1, 0.3);
		Point p7 = new Point(0.4, 0.2, 0.5, 0.2);
		Point p8 = new Point(0.9, 0.7, 0.8, 0.1);
		
		
		Point[] ps = new Point[]{p1,p2,p3, p4, p5, p6, p7, p8};
		IGrid iGrid = new IGrid(ps);
		iGrid.printDatabase();
	}

}
