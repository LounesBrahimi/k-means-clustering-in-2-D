package algorithms;

import java.awt.Point;
import java.util.ArrayList;

public class DefaultTeam {

  public ArrayList<ArrayList<Point>> calculKMeans(ArrayList<Point> points) {
	  int k = 5;
	  
	  KMeans2D kmeans2D = new KMeans2D(k, points);
	  kmeans2D.cluster();
	  
	  
	  ArrayList<ArrayList<Point>> kmeans = new ArrayList<ArrayList<Point>>();
	  kmeans.add(kmeans2D.getRouge());
	  kmeans.add(kmeans2D.getVerte());
	  kmeans.add(kmeans2D.getJaune());
	  kmeans.add(kmeans2D.getOrange());
	  kmeans.add(kmeans2D.getNoir());
    
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