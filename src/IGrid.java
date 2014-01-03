import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class IGrid {
	Point[] allValues;
	Dimension[] dimensions;
	int D;

	public IGrid(Point[] values){
		allValues = values;
		extractPoints();
	}
	
	private void extractPoints(){
		final int N = allValues.length;
		D = allValues[0].values.length; // One of points length gives the dimension
		double theta = 0.5;
		dimensions = new Dimension[D];
		final int Kd = (int)(theta * D);
		final int slice = N / Kd;
		// For every dimension
		for(int i = 0; i < D; i++){
			// Initialize the Dimension object which holds all ranges (with points) for a dimension
			final int currentDimension = i;
			dimensions[i] = new Dimension(i, Kd);
			
			// Sort the values according to their i'th dimension
			Arrays.sort(allValues, new Comparator<Point>(){
				@Override
				public int compare(Point p0, Point p1) {
					return Double.compare(p0.values[currentDimension], p1.values[currentDimension]);
				}
				
			});
			
			// For all points, put it to respective dimensions respective range
			for(int j = 0; j < N; j++){
				// Put the point to respective dimension
				Point p = allValues[j];
				int destination = j / slice;
				dimensions[i].addPoint(p, destination);
			}			
			dimensions[i].setRanges();
		}
	}
	
	public void printDatabase(){
		for(int i = 0; i < D; i++){
			System.out.println("DIMENSION " + i);
			Range[] r = dimensions[i].ranges;
			for(int j = 0; j < r.length; j++){
				System.out.println("\tRange " + j + " from (" + r[j].start + ") to (" + r[j].end + ")");
				System.out.println("\t" + r[j].points);
				System.out.println();
			}
		}
	}
	
	public Point search(Point reference){
		Range[] ranges = searchDimensions(reference);
		HashSet<Point> dists = new HashSet<Point>();
		for(Range r: ranges){
			for(Point p : r.points){
				dists.add(p);
			}
		}
		// Reducing the duplicates
		
		while(dists.iterator().hasNext()){
			Point p = dists.iterator().next();
			
		}
		
		return null;
	}
	
	public Range[] searchDimensions(Point reference){
		Range[] ranges = new Range[D];
		for(int i = 0; i < D; i++){
			ranges[i] = dimensions[i].getPoints(reference);
		}
		return ranges;
	}
}
