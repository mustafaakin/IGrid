
public class Point {
	Range[] ranges;
	String key;
	static int counter = 0;
	
	double[] values;
	public Point(String key, double... values){
		this.key = key;
		this.values = values;
		ranges = new Range[values.length];
	}

	public Point(double... values){
		this.key = "" + counter++;
		this.values = values;
		ranges = new Range[values.length];
	}

	
	@Override
	public String toString() {
		String str = "";
		for(double d : values){
			str += String.format("%.2f, ", d);
		}
		return "#" + key + "#(" + str.substring(0, str.length() - 2) + ")";
	}
	
	public String getKey() {
		return key;
	}
}
