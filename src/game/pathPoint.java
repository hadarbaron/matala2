package game;

import Geom.Point3D;
/**
 * The class creates an object named PATHPOINT into which PATH will enter 
 * @author shira and hadar
 */
public  class  pathPoint {
	private Point3D pointGps;
	private	int scorePoint;
	private String date;
	/**
	 *pathPoint constructor get a fruit and build it
	 * @param fruit
	 */
	public pathPoint (Fruit fruit){
		this.scorePoint=fruit.getWeight();
		this.pointGps=fruit.getPoint();
	}
	public pathPoint (Point3D point){
		this.pointGps=new Point3D(point);	
	}
	public pathPoint (pathPoint pathpoint) {
		this.pointGps=new Point3D(pathpoint.getPointGps());
	}
	public String getDate()
	{
		return date;
	}
	public int getScorePoint() {
		return scorePoint;
	}
	public String toString () {
		return pointGps.toString();
	}
	public Point3D getPointGps() {
		return pointGps;
	}
	public void setTime(String date)
	{
		this.date=date;
	}

}