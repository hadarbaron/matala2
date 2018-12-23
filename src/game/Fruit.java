package game;
import java.text.SimpleDateFormat;
import java.util.Date;

import Geom.Point3D;
/**
 * The class creates an object named FRUIT 
 * that contains both a WEGHIT and a GPS point
 * @author shira AND hadar
 */
public class Fruit {
	private Point3D point;
	private int weight;
	private boolean eat;
	public Fruit()
	{
		
	}
	public Fruit (Point3D place, int weight)
	{
		this.point=place;
		this.weight=weight;
		eat=false;
	}
	public Fruit (Fruit fruit)
	{
		this.point=new Point3D(fruit.point);
		this.weight=fruit.weight;
	}
	/**
	 * If a fruit needs to be eaten it becomes true
	 */
	public void eatTheFruit() {
		eat=true;
	}
	/**
	 * Returns true if the fruit is eaten otherwise false
	 * @return boolean 
	 */
	public boolean geteat() {
		return eat;
	}

	public Point3D getPoint() {
		return point;
	}
	public int getWeight() {
		return weight;
	}
	public String toString() {
		return "weight: "+this.weight+" point: "+this.point;
	}
}