import java.util.ArrayList;

public class Dimension {
	Range[] ranges;
	
	public Dimension(int length){
		ranges = new Range[length];
	}
	
	public void addPoint(Point p, int rangeIdx){
		if ( ranges[rangeIdx] == null){
			ranges[rangeIdx] = new Range(); 
		}
		ranges[rangeIdx].points.add(p);
	}
	
	public Point[] getPoints(Point reference){
		return null;
	}
}
