package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
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
	private Utils utils;
	
	/*
	 * Constructor
	 * */
	public KMeans2D(int k, ArrayList<Point> points) {
		this.k = k;
		this.points = points;
		this.utils = new Utils();
	}
	
	/*
	 * Method that execute the K-means algorithm
	 * */
	public void cluster() {
		// Select K initial centroids
		ArrayList<Point> centroids = initializeCentroids();
        System.out.println("#centroids aleatoires : "+ centroids.toString());
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
}
