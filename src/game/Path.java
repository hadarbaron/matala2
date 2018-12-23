package game;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The department keeps track of all PACKMANS it passes through
 * @author shira AND hadar
 *
 */
public class Path 
{
	private int scorepoint;
	private ArrayList<pathPoint> path;
	
	public Path() 
	{
		this.scorepoint=0;
		path=new ArrayList<pathPoint>();
	}
	
	public Path(Path path2)
	{
		path=new ArrayList<pathPoint>();

		java.util.Iterator<pathPoint>  it=path2.iterator();
		while(it.hasNext())
		{
			this.path.add(new pathPoint(it.next()));
		}
		this.scorepoint=path2.getScorepoint();
	}
	public int getScorepoint() {
		return scorepoint;
	}
	/**
	 * the function return the iterator of the path's arrayList
	 * @return
	 */
	public Iterator <pathPoint> iterator(){
		return this.path.iterator();
	}
	/**
	 * the first pathpoint in the path is the packman
	 * @param packman
	 */
	public void start(Packman packman) 
	{
		path.add(new pathPoint (packman.getPoint()));
	}
	/**
	 * the function get a pathPoint and add it to the path's arrayList
	 * @param pathpoint
	 */
	public void addPathPoint(pathPoint pathpoint)
	{
		scorepoint=scorepoint+pathpoint.getScorePoint();
		path.add(pathpoint);
	}
	/**
	 * @return return the last pathPoint
	 */
	public pathPoint lastPath() {
		java.util.Iterator<pathPoint>  it=path.iterator();
		pathPoint lastElement = it.next();
		while(it.hasNext()) {
			lastElement=it.next();
		}
		return lastElement;
	}
	public String toString()
	{
		String s= "";
		java.util.Iterator<pathPoint>  it=path.iterator();
		while(it.hasNext())
		{
			s=s+it.next();
		}
		return s;
	}
}