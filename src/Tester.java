public class Tester {
	public static Point eucledian(Point[] points, Point reference){
		int N = points.length;
		
		double min = Double.MAX_VALUE;
		Point result = null;
		for(int i = 0; i < N; i++){
			double value = 0;
			Point p = points[i];
			for(int j = 0; j < reference.values.length; j++){
				value += (p.values[j] - reference.values[j]) * (p.values[j] - reference.values[j]) ;				
			}
			if ( value < min){
				min = value;
				result = p;						
			}
		}	
		return result;
	}

	public static Point manhattan(Point[] points, Point reference){
		int N = points.length;
		
		double min = Double.MAX_VALUE;
		Point result = null;
		for(int i = 0; i < N; i++){
			double value = 0;
			Point p = points[i];
			for(int j = 0; j < reference.values.length; j++){
				value += Math.abs((p.values[j] - reference.values[j])) ;				
			}
			if ( value < min){
				min = value;
				result = p;					
			}
		}	
		return result;
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(0.5, 0.9, 0.6, 0.7);
		Point p2 = new Point(0.2, 0.6, 0.4, 0.6);
		Point p3 = new Point(0.1, 0.8, 0.3, 0.1);
		Point p4 = new Point(0.5, 0.3, 0.2, 0.4);
		Point p5 = new Point(10.6, 0.1, 0.4, 0.6);
		Point p6 = new Point(0.3, 0.5, 0.1, 0.3);
		Point p7 = new Point(0.4, 0.2, 0.5, 0.2);
		Point p8 = new Point(0.9, 0.7, 0.8, 0.1);

		Point[] ps = new Point[] { p1, p2, p3, p4, p5, p6, p7, p8 };
		IGrid iGrid = new IGrid(ps);
		iGrid.printDatabase();

		Point pTest = new Point(0.6, 0.1, 0.4, 0.6);
		Range[] r = iGrid.searchDimensions(pTest);
		for (int i = 0; i < r.length; i++) {
			System.out.println("Dimension " + i + " " + r[i]);
		}
		
		System.out.println();
		 
		System.out.println("Query     : " + pTest);
		System.out.println("Eucledian : " + eucledian(ps, pTest));
		System.out.println("Manhattan : " + manhattan(ps, pTest));		
		System.out.println("IGrid     : " + iGrid.search(pTest));
	}

}
