
public class Tester {

	public static void main(String[] args) {
		Point p1 = new Point(0.5, 0.3, 0.4);
		Point p2 = new Point(0.2, 0.6, 0.7);
		Point p3 = new Point(0.1, 0.1, 0.9);
		
		Point[] ps = new Point[]{p1,p2,p3};
		IGrid iGrid = new IGrid(ps);
	}

}
