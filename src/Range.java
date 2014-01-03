import java.util.ArrayList;

public class Range {
	double start = Integer.MAX_VALUE;
	double end = Integer.MIN_VALUE;
	int dimension;

	public Range(int dimension) {
		this.dimension = dimension;
	}

	ArrayList<Point> points = new ArrayList<Point>();

	public void addPoint(Point p) {
		points.add(p);
		double value = p.values[dimension];
		if (value < start)
			start = value;
		if (value > end)
			end = value;
	}

	@Override
	public String toString() {
		return points.toString();
	}

}
