package algorithms;

import java.awt.Point;
import java.util.ArrayList;

public class DefaultTeam {

  public ArrayList<ArrayList<Point>> calculKMeans(ArrayList<Point> points) {
	  int k = 5;
	  
	  ArrayList<Point> rouge = new ArrayList<Point>();
	  ArrayList<Point> verte = new ArrayList<Point>();
	  ArrayList<Point> jaune = new ArrayList<Point>();
	  ArrayList<Point> orange = new ArrayList<Point>();
	  ArrayList<Point> noir = new ArrayList<Point>();
	  
	  ArrayList<ArrayList<Point>> kmeans = new ArrayList<ArrayList<Point>>();
	  kmeans.add(rouge);
	  kmeans.add(verte);
	  kmeans.add(jaune);
	  kmeans.add(orange);
	  kmeans.add(noir);
    
	  return kmeans;
  }
  
  public ArrayList<ArrayList<Point>> calculKMeansBudget(ArrayList<Point> points) {
    ArrayList<Point> rouge = new ArrayList<Point>();
    ArrayList<Point> verte = new ArrayList<Point>();

    for (int i=0;i<points.size()/2;i++){
      rouge.add(points.get(i));
      verte.add(points.get(points.size()-i-1));
    }
    if (points.size()%2==1) rouge.add(points.get(points.size()/2));

    ArrayList<ArrayList<Point>> kmeans = new ArrayList<ArrayList<Point>>();
    kmeans.add(rouge);
    kmeans.add(verte);

    /*******************
     * PARTIE A ECRIRE *
     *******************/
    return kmeans;
  }
}