package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

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
	
	public ArrayList<Point> randomCentroids() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Point> centroids = new ArrayList<Point>();
        for (int i=0; i<points.size(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<k; i++) {
        	centroids.add(points.get(list.get(i)));
        }
        return centroids;
	}
	
	
	/*
	 * Method that execute the K-means algorithm
	 * */
	public void cluster() {
		// Select K initial centroids
		ArrayList<Point> centroidsInt = randomCentroids();
		ArrayList<Point2D> centroids = new ArrayList<Point2D>();
		for (int i = 0; i < centroidsInt.size(); i++) {
			centroids.add(new Point2D.Double(centroidsInt.get(i).getX(), centroidsInt.get(i).getY()));
		}
      //  System.out.println("#centroids aleatoires : "+ centroids.toString());
        
        // Inital total score
        double score = -10000000000.0;
        while(true) {
        	cleanClusters();
            // For each points
            for(Point point : points){
                double distMin = 10000000000.0;
                int index = -1;
                // find the centroid at a minimum distance from it and add the point to its cluster
                for(int i = 0; i < centroids.size(); i++){
                    double distance = utils.distance(centroids.get(i), new Point2D.Double(point.getX(), point.getY()));
                    if(distance < distMin){
                        distMin = distance;
                        index = i;
                    }
                }
                if (index >= 0) {
                	setCouleur(index, point);
                }
            }
            
         //   System.out.println("## clusters : "+ toStringClusters());
            centroids = regenerateCentroids();
          //  System.out.println("###nouveaux centroids : "+ centroids.toString());
            double newScore = calculateScore(centroids);
            if(newScore == score){
                break;
            }
            score = newScore;
        }
        System.out.println("fin");
	}
	
    public double calculateScore(ArrayList<Point2D> centroids){
        double score = 0.0;
        for(int i=0; i<centroids.size(); i++) {
            score += calculateScoreOfCluster(centroids.get(i), i);
        }
        return score;
    }
    
    public double calculateScoreOfCluster(Point2D centroid, int index){
        ArrayList<Point> cluster = getCouleur(index);
        double score = 0.0;
        for (Point point : cluster) {
			score += utils.distance(new Point2D.Double(point.getX(), point.getY()), centroid);
		}
        return score;
    }
	
    /*
     * Method that regenerate the list of centroids based on cluster averages
     * */
    public  ArrayList<Point2D> regenerateCentroids(){
    	ArrayList<Point2D> centroids = new ArrayList<Point2D>();
        for(int i=0; i < this.k; i++){
            centroids.add(generatesCentroid(i));
        }
        return centroids;
    }
    
    /*
     * Method that generate the centroid of a given cluster
     * */
    public Point2D generatesCentroid(int index){
        Point centroid = new Point();

        ArrayList<Point> pointsInCluster = new ArrayList<Point>();
        for(Point point : this.points){
        	if (getCouleur(index).contains(point)) {
        		pointsInCluster.add(point);	
        	}
        }
        return centre(pointsInCluster);
    }
    
    public Point2D centre(ArrayList<Point> clusters ){
        double sumX = 0;
        double sumY = 0;
        for(Point cluster : clusters){
                sumX += cluster.getX();
                sumY += cluster.getY();
        }
        return new Point2D.Double(sumX/clusters.size(), sumY/clusters.size());
    }
	
    private void cleanClusters() {
    	this.jaune.clear();
    	this.noir.clear();
    	this.orange.clear();
    	this.rouge.clear();
    	this.verte.clear();
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
