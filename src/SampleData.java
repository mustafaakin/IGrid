import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SampleData {
	final static int RUNS = 100;
	final static int QUERIES = 100;

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

	static class TestRun {
		int OFFSET_START, OFFSET_END;
		boolean LABEL_START;
		String filename;

		public TestRun(String filename, int OFFSET_START, int OFFSET_END,
				boolean LABEL_START) {
			this.filename = filename;
			this.OFFSET_START = OFFSET_START;
			this.OFFSET_END = OFFSET_END;
			this.LABEL_START = LABEL_START;
		}
	}

	public static double run(TestRun test, double theta, double P)
			throws Exception {
		double total = 0;
		for (int run = 0; run < RUNS; run++) {
			Scanner scan = new Scanner(new File("/home/mustafa/cs531/"
					+ test.filename));
			ArrayList<Point> databasePoints = new ArrayList<Point>();
			ArrayList<Point> queryPoints = new ArrayList<Point>();

			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] components = line.split(",");
				String label = components[test.LABEL_START ? 0
						: components.length - 1];
				double[] values = new double[components.length
						- test.OFFSET_END - test.OFFSET_START];
				for (int i = test.OFFSET_START; i < components.length
						- test.OFFSET_END; i++) {
					values[i - test.OFFSET_START] = Double
							.parseDouble(components[i]);
				}
				Point p = new Point(label, values);
				if (values.length == 0)
					continue;
				databasePoints.add(p);
			}
			int size = databasePoints.size();
			Integer[] queryIndexes = randomIndexes(size, QUERIES);

			for (Integer i : queryIndexes) {
				Point q = databasePoints.get(i);
				queryPoints.add(q);
				databasePoints.set(i, null);
			}

			databasePoints.removeAll(Collections.singleton(null));

			Point[] ps = new Point[databasePoints.size()];
			ps = databasePoints.toArray(ps);
			IGrid ig = new IGrid(ps, P, theta);
			// ig.printDatabase();

			int count = 0;
			for (Point q : queryPoints) {
				Point result = ig.search(q);
				// System.out.println("Query : " + q);
				// System.out.println("Result: " + result);
				if (result != null && result.key.equals(q.key)) {
					count++;
				}
			}
			// System.out.println((double) count / queryPoints.size());
			total += (double) count / queryPoints.size();
			scan.close();
		}
		return total / RUNS;
	}

	public static void main(String[] args) throws Exception {
		ArrayList<TestRun> tests = new ArrayList<TestRun>();
		tests.add(new TestRun("glass.data", 1,1, false));
		tests.add(new TestRun("segmentation.data", 1, 0, true));	
		tests.add(new TestRun("ionosphere.data", 0, 1, false));	
		tests.add(new TestRun("spambase.data", 0, 1, false));

		double thetas[] = new double[] { 0.25, 0.5, 0.75, 1, 2, 5, 10 };
		double Ps[] = new double[] { 0.25, 0.5, 0.75, 1 };

		for (TestRun tr : tests) {
			for (double theta : thetas) {
				for (double P : Ps) {
					try {
						double result = run(tr, theta, P) * 100;
						System.out.println(String.format("%30s %.2f %.2f %.1f", tr.filename, theta, P, result));
					} catch (Exception ex) {						
						ex.printStackTrace();
					}
				}
			}
		}

	}

}
