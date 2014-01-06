import java.util.*;

import com.stromberglabs.cluster.*;

public class KMeansTest {

	public static void main(String[] args) {
		List<Clusterable> mPoints = new ArrayList<Clusterable>();
		mPoints.add(new com.stromberglabs.cluster.Point(1, 0));
		mPoints.add(new com.stromberglabs.cluster.Point(2, 0));
		mPoints.add(new com.stromberglabs.cluster.Point(3, 0));
		mPoints.add(new com.stromberglabs.cluster.Point(4, 0));
		mPoints.add(new com.stromberglabs.cluster.Point(15, 0));
		mPoints.add(new com.stromberglabs.cluster.Point(25, 0));
		mPoints.add(new com.stromberglabs.cluster.Point(30, 0));

		KClusterer clusterer = new KMeansClusterer();
		Cluster clusters[] = clusterer.cluster(mPoints, 2);

		Arrays.sort(clusters, new Comparator<Cluster>() {
			@Override
			public int compare(Cluster arg0, Cluster arg1) {
				return Float.compare(arg0.getClusterMean()[0],
						arg1.getClusterMean()[0]);
			}

		});
		for (Cluster cluster : clusters) {
			for (Clusterable item : cluster.getItems()) {
				System.out.println(item);
				for (int i = 0; i < mPoints.size(); i++) {
					Clusterable c = mPoints.get(i);
					if (item.equals(c)) {
						System.out.println(i);
					}
				}
			}
			System.out.println();
		}

	}

}
