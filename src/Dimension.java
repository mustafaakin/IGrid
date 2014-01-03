import java.util.ArrayList;

public class Dimension {
	Range[] ranges;
	int dimension;

	public Dimension(int dimension, int length) {
		ranges = new Range[length];
		this.dimension = dimension;
		for(int i = 0; i < ranges.length; i++){
			ranges[i] = new Range(dimension);
		}
	}

	public void addPoint(Point p, int rangeIdx) {
		ranges[rangeIdx].addPoint(p);
	}
	
	public void setRanges(){
		ranges[0].start = Double.MIN_VALUE;		
		ranges[ranges.length - 1].end = Double.MAX_VALUE;
		for (int i = 0; i < ranges.length; i++) {
			// To be able to cover the places between them
			if (i > 0) {
				ranges[i-1].end = ranges[i].start = (ranges[i].start + ranges[i - 1].end) / 2;
				
			}
		}
	}

	public Range getPoints(Point reference) {
		double value = reference.values[dimension];
		// TODO: Slow, temprorary method requires O(n), could be reduced to
		// O(logn) with binary search
		for (int i = 0; i < ranges.length; i++) {
			Range curr = ranges[i];
			if (value >= curr.start && value <= curr.end) {
				return curr;
			}
		}

		// Actually cannot be here, just to keep compiler happy
		return null;
	}
}
