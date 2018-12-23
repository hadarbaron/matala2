package game;
import java.util.Iterator;

import Geom.Point3D;
/**
 * The class creates an object named PACKMAN that receives speed, a GPS point, and a radius
 * @author shira AND hadar
 *
 */
public class Packman {
	private int speed;
	private int raduis;
	private	Point3D point;
	private Path path;
	Point3D p=this.getPoint(); 
	
	public Packman (int speed,Point3D point3d,int raduis)
	{
		this.speed=speed;
		this.point=point3d;
		this.raduis=raduis;
		path=new Path();
	}	
	public Packman (Packman packman)
	{
		this.speed=packman.getSpeed();
		this.point=new Point3D (packman.getPoint());
		this.raduis=packman.getRaduis();
		this.path=new Path (packman.getPath());
	}
	public int getSpeed() {
		return speed;
	}
	public Point3D getPoint() {
		return point;
	}
	public int getRaduis() {
		return raduis;
	}
	public Path getPath() {
		return path;
	}
	public void setPoint(Point3D p)
	{
		this.point=p;
	}
	
/**
 * the function add a path to the Packman 
 * @param path
 */
	public void addPath(Path path)
	{
		this.path=path;
	}
	
	public String toString ()
	{

		return "speed "+this.speed+" point "+this.point+" raduis "+this.raduis;
	}
	


}