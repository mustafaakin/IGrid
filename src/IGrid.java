import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class IGrid {
	Point[] allValues;
	Dimension[] dimensions;

	public IGrid(Point[] values){
		allValues = values;
		extractPoints();
	}
	
	private void extractPoints(){
		int count = allValues.length;
		int D = allValues[0].values.length; // One of points length gives the dimension
		double theta = 0.5;
		dimensions = new Dimension[D];
		int Kd = (int)(theta * D);

		// For every dimension
		for(int i = 0; i < D; i++){
			// For every point
			final int currentDimension = i;
			Arrays.sort(allValues, new Comparator<Point>(){
				@Override
				public int compare(Point p0, Point p1) {
					return Double.compare(p0.values[currentDimension], p1.values[currentDimension]);
				}
				
			});
			for(int j = 0; j < allValues.length; i++){
				// Put the point to respective dimension
				Point p = allValues[j];

			}			
		}
	}
}
