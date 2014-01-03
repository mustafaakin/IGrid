import java.util.ArrayList;

public class Dimension {
	Range[] ranges;
	int dimension;

	public Dimension(int dimension, int length) {
		ranges = new Range[length];
		this.dimension = dimension;
	}

	public void addPoint(Point p, int rangeIdx) {
		if (ranges[rangeIdx] == null) {
			ranges[rangeIdx] = new Range(dimension);
		}
		ranges[rangeIdx].addPoint(p);
	}

	public Range getPoints(Point reference) {
		double value = reference.values[dimension];
		// TODO: Slow, temprorary method requires O(n), could be reduced to
		// O(logn) with binary search
		Range first = ranges[0];
		Range last = ranges[ranges.length - 1];
		if ( value < first.start){
			return first;
		}
		else if ( value > last.end){
			return last;
		} else {
			for (int i = 0; i < ranges.length; i++) {
				Range curr = ranges[i];
				if (value >= curr.start && value <= curr.end) {
					return curr;
				}
			}
		}
		// Actually cannot be here, just to keep compiler happy
		return null;
	}
}
