import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SampleData {
	public static Integer[] randomIndexes(int range, int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < range; i++) {
			list.add(i);
		}
		Collections.shuffle(list);

		Integer[] result = new Integer[n];
		result = list.subList(0, n).toArray(result);

		return result;
	}

	public static void main(String[] args) throws Exception {
		final int RUNS = 100;
		final int QUERY = 100; 
		final int OFFSET_START = 0;
		final int OFFSET_END = 1;
		final boolean LABEL_START = false; 
		
		double total = 0;
				
		for (int run = 0; run < RUNS; run++) {
			Scanner scan = new Scanner(new File(
					"/home/mustafa/cs531/spambase.data.txt"));
			ArrayList<Point> databasePoints = new ArrayList<Point>();
			ArrayList<Point> queryPoints = new ArrayList<Point>();

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] components = line.split(",");
				String label = components[LABEL_START ? 0 : components.length - 1];
				double[] values = new double[components.length - OFFSET_END -OFFSET_START];
				for (int i = OFFSET_START; i < components.length - OFFSET_END; i++) {
					values[i-OFFSET_START] = Double.parseDouble(components[i]);
				}
				Point p = new Point(label, values);
				if ( values.length == 0)
					continue;
				databasePoints.add(p);
			}
			int size = databasePoints.size();
			Integer[] queryIndexes = randomIndexes(size, QUERY);

			for (Integer i : queryIndexes) {
				Point q = databasePoints.get(i);
				queryPoints.add(q);
				databasePoints.set(i, null);
			}

			databasePoints.removeAll(Collections.singleton(null));

			Point[] ps = new Point[databasePoints.size()];
			ps = databasePoints.toArray(ps);
			IGrid ig = new IGrid(ps);
			// ig.printDatabase();
			
			int count = 0;
			for (Point q : queryPoints) {
				Point result = ig.search(q);
				//System.out.println("Query : " + q);
				//System.out.println("Result: " + result);
				if (result != null && result.key.equals(q.key)) {
					count++;
				}
			}
			// System.out.println((double) count / queryPoints.size());
			total += (double) count / queryPoints.size();  
			scan.close();
		}
		System.out.println("Average: " + total / RUNS);
	}

}
