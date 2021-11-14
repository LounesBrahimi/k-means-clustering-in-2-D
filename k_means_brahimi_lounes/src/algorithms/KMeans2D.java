package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
/*
 * Class that implement the algorithm of K-means and will produce the clusters
 * */
public class KMeans2D {
	
	/*
	 * k is the number of clusters wanted
	 * */
	private int k;
	/*
	 * points is the list of all points
	 * */
	private ArrayList<Point> points;
	
	/*
	 * Clusters in colors
	 * */
	private ArrayList<Point> rouge;
    private ArrayList<Point> verte;
	private ArrayList<Point> jaune;
    private ArrayList<Point> orange;
	private ArrayList<Point> noir;
	  
	private Utils utils;
	
	/*
	 * Constructor
	 * */
	public KMeans2D(int k, ArrayList<Point> points) {
		this.k = k;
		this.points = points;
		this.utils = new Utils();
		rouge = new ArrayList<Point>();
		verte = new ArrayList<Point>();
		jaune = new ArrayList<Point>();
		orange = new ArrayList<Point>();
		noir = new ArrayList<Point>();
	}
	
	/*
	 * Method that execute the K-means algorithm
	 * */
	public void cluster() {
		// Select K initial centroids
		ArrayList<Point> centroids = initializeCentroids();
        System.out.println("#centroids aleatoires : "+ centroids.toString());
        
        // Inital total score
        Double score = Double.MAX_VALUE;
        
        while(true) {
            // For each points
            for(Point point : points){
                Double distMin = Double.MAX_VALUE;
                // find the centroid at a minimum distance from it and add the point to its cluster
                for(int i = 0; i < centroids.size(); i++){
                    Double distance = utils.distance(centroids.get(i), point);
                    if(distance < distMin){
                        distMin = distance;
                        setCouleur(i, point);
                    }
                }
            }
            
            System.out.println("## clusters : "+ toStringClusters());
            
            centroids = regenerateCentroids();
            System.out.println("###nouveaux centroids : "+ centroids.toString());
            break;
        }     
	}
    /*
     * Method that regenerate the list of centroids based on cluster averages
     * */
    public  ArrayList<Point> regenerateCentroids(){
    	ArrayList<Point> centroids = new ArrayList<Point>();
        for(int i=0; i < this.k; i++){
            centroids.add(generatesCentroid(i));
        }
        return centroids;
    }
    
    /*
     * Method that generate the centroid of a given cluster
     * */
    public Point generatesCentroid(int index){
        Point centroid = new Point();

        ArrayList<Point> pointsInCluster = new ArrayList<Point>();
        for(Point point : this.points){
        	if (getCouleur(index).contains(point)) {
        		pointsInCluster.add(point);	
        	}
        }
        return centre(pointsInCluster);
    }
    
    public Point centre(ArrayList<Point> clusters ){
        int sumX = 0;
        int sumY = 0;
        for(Point cluster : clusters){
                sumX += cluster.getX();
                sumY += cluster.getY();
        }
        return new Point(sumX/clusters.size(), sumY/clusters.size());
    }
	
	/*
	 * Method that add a point in the cluster corresponding to his index
	 * */
	public void setCouleur(int index, Point point) {
		switch (index) {
		case 0: {
			this.jaune.add(point);
			break;
		}
		case 1: {
			this.noir.add(point);
			break;
		}
		case 2: {
			this.orange.add(point);
			break;
		}
		case 3: {
			this.rouge.add(point);
			break;
		}
		case 4: {
			this.verte.add(point);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
	}
	
    public String toStringClusters() {
    	return "\nrouge : " +this.rouge.toString() + "\njaune : "+this.jaune.toString() + "\nverte : "+
    			this.verte.toString() + "\nnoir : " +this.noir.toString() + "\norange : "
    			+ this.orange.toString();
    }
	
    /*
     *  Method that generate the inital centroids that is randomly taken in the list of points 
     *  */
    public ArrayList<Point> initializeCentroids() {
    	ArrayList<Point> centroids = new ArrayList<Point>();

        centroids.add(this.utils.randomPoint(this.points));

        for(int i=1; i<this.k; i++){
           centroids.add(this.utils.newCentroid(points, centroids));
        }
        return centroids;
    }
    
	/*
	 * Method that return a cluster
	 * */
	public ArrayList<Point> getCouleur(int index) {
		switch (index) {
		case 0: {
			return this.jaune;
		}
		case 1: {
			return this.noir;
		}
		case 2: {
			return this.orange;
		}
		case 3: {
			return this.rouge;
		}
		case 4: {
			return this.verte;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
	}

	public ArrayList<Point> getRouge() {
		return rouge;
	}

	public ArrayList<Point> getVerte() {
		return verte;
	}

	public ArrayList<Point> getJaune() {
		return jaune;
	}

	public ArrayList<Point> getOrange() {
		return orange;
	}

	public ArrayList<Point> getNoir() {
		return noir;
	}
}
