
public class Point {
	static int counter = 0;
	
	int id;
	double[] values;
	public Point(double... values){
		this.values = values;
		id = counter;
		counter++;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(double d : values){
			str += String.format("%.2f, ", d);
		}
		return "Point #" + id + " (" + str.substring(0, str.length() - 2) + ")";
	}
}
