import java.util.ArrayList;

public class Dimension {
	Range[] ranges;
	int dimension;
	
	public Dimension(int dimension, int length){
		ranges = new Range[length];
		this.dimension = dimension; 
	}
	
	public void addPoint(Point p, int rangeIdx){
		if ( ranges[rangeIdx] == null){
			ranges[rangeIdx] = new Range(dimension); 
		}
		ranges[rangeIdx].addPoint(p);
	}
	
	
	public Point[] getPoints(Point reference){
		return null;
	}
}
