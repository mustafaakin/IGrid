
public class Point {
	Range[] ranges;
	static int counter = 0;
	
	int id;
	double[] values;
	public Point(double... values){
		this.values = values;
		id = counter;
		ranges = new Range[values.length];
		counter++;
	}
	
	@Override
	public String toString() {
		String str = "";
		for(double d : values){
			str += String.format("%.2f, ", d);
		}
		return "#" + id + "#(" + str.substring(0, str.length() - 2) + ")";
	}
}
