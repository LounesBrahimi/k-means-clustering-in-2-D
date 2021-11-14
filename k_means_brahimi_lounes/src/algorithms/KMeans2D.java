package algorithms;

import java.awt.Point;
import java.util.ArrayList;

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
	 * Constructor
	 * */
	public KMeans2D(int k, ArrayList<Point> points) {
		this.k = k;
		this.points = points;
	}
	
	/*
	 * Method that execute the K-means algorithm
	 * */
	public void cluster() {
		
	}
}
