package algorithms;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/*
 * Class that contains methods and algorithms that are used in the algorithm of k-means
 * */
public class Utils {

	/*
	 * Constructor
	 * */
	public Utils() {
	}
	
	/*
	 * Method that return a random point in the list
	 * */
    public Point randomPoint(ArrayList<Point> points){
    	Random random = new Random();
        int index = random.nextInt(points.size());
        return points.get(index);
    }

    /*
     * Method that return a new centroid 
     * */
    public Point newCentroid(ArrayList<Point> points, ArrayList<Point> centroids){
        double sum = 0.0;
        for(Point point : points){
            if(!centroids.contains(point)){
                double distMin = Double.MAX_VALUE;
                for(Point centroid : centroids){
                    double distance = distance(point, centroid);
                    if(distance < distMin)
                        distMin = distance;
                }
                if(centroids.isEmpty()) {
                	sum = 0.0;
                }
                sum += distMin;
            }
        }

        Random random = new Random();
        double seuil = sum * random.nextDouble();

        for(Point point : points){
            if(!centroids.contains(point)){
                double distMin = Double.MAX_VALUE;
                for(Point centroid : centroids){
                    double distance = distance(point, centroid);
                    if(distance < distMin)
                        distMin = distance;
                }
                sum += distMin;
                if(sum > seuil){
                    return point;
                }
            }
        }
        return new Point();
    }
        
        /*
         * Method that calculate distance euclidiean between two Points
         * */
        public Double distance(Point a, Point b){
        	return Math.sqrt((b.getX()-a.getX())*(b.getX()-a.getX())
        						+ (b.getY()-a.getY())*(b.getY()-a.getY()));
        }
        
        /*
         * Method that calculate distance euclidiean between two Point2D
         * */
        public Double distance(Point2D a, Point2D b){
        	return Math.sqrt((b.getX()-a.getX())*(b.getX()-a.getX())
        						+ (b.getY()-a.getY())*(b.getY()-a.getY()));
        }
        
}
