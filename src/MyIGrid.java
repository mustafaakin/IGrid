import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.stromberglabs.cluster.Cluster;
import com.stromberglabs.cluster.Clusterable;
import com.stromberglabs.cluster.KClusterer;
import com.stromberglabs.cluster.KMeansClusterer;


public class MyIGrid extends IGrid {

	public MyIGrid(Point[] values, double P, double theta) {
		super(values, P, theta);
	}
	
	@Override
	public void extractPoints() {
		final int N = allValues.length;
		D = allValues[0].values.length; // One of points length gives the
										// dimension
		dimensions = new Dimension[D];
		final int Kd = (int) (theta * D);
		final int slice = (int) Math.ceil((double) N / (double) Kd); 

		// For every dimension
		for (int i = 0; i < D; i++) {
			// Initialize the Dimension object which holds all ranges (with
			// points) for a dimension
			final int currentDimension = i;
			dimensions[i] = new Dimension(i, Kd);

			List<Clusterable> mPoints = new ArrayList<Clusterable>();
			for (int j = 0; j < N; j++) {
				Point p = allValues[j];				
				mPoints.add(new com.stromberglabs.cluster.Point((float)p.values[i], 0));
			}
			
			KClusterer clusterer = new KMeansClusterer();
			Cluster clusters[] = clusterer.cluster(mPoints, Kd);
					
			Arrays.sort(clusters, new Comparator<Cluster>() {
				@Override
				public int compare(Cluster arg0, Cluster arg1) {
					if ( arg0 == null ||  arg0.getItems().size() == 0){
						return 0; 
					} else if ( arg1 == null || arg1.getItems().size() == 0 ){
						return 1;
					} else {
						return Float.compare(arg0.getClusterMean()[0],
								arg1.getClusterMean()[0]);						
					}
					
				}

			});
			
			for (int x = 0; x < clusters.length; x++) {
				Cluster cluster = clusters[x];
				for (Clusterable item : cluster.getItems()) {
					for(int k = 0; k < mPoints.size(); k++){
						Clusterable c = mPoints.get(k);
						if ( item.equals(c)){
							dimensions[i].addPoint(allValues[k], x);
						}
					}
				}
			}
			
			dimensions[i].setRanges();
		}
	}

}
