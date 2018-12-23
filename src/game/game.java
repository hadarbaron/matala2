package game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.element;
import GIS.gameElement;
import GIS.layer;
import Geom.Point3D;
/**
 * The GAME department stores all the PACKMANS and FRUITS of the game.
 * there are functions that convert to kml/csv/or read from csv
 * @author shira AND hadar
 *
 */
public class game   {
	private layer a; 
	private HashSet <Packman> packmans;
	private HashSet<Fruit> fruits;

	public game()
	{
		packmans=new HashSet<Packman>();
		fruits=new HashSet<Fruit>();
	}
	/** 
	 * @return Returns the amount of Pacmans
	 */
	public int sizePackmans() {
		return packmans.size();
	}
	/**
	 * 
	 * @return Returns the amount of fruits
	 */
	public int sizeFruits() {
		return fruits.size();
	}
	/**
	 * 
	 * @return Returns the Pacman's iterator
	 */
	public Iterator<Packman> iterator1() {
		return this.packmans.iterator();
	}
	/**
	 * 
	 * @return Returns the Fruit's iterator
	 */
	public Iterator<Fruit> iterator2() {
		return this.fruits.iterator();
	}
	/**
	 * The function accepts a game and converts it to a .csv file
	 * @param name
	 */
	public void gameTocsv(String name)
	{
		String fileName = name;
		PrintWriter pw = null;

		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();

		}

		StringBuilder sb = new StringBuilder();
		sb.append("Type");
		sb.append(',');
		sb.append("id");
		sb.append(',');
		sb.append("Lat");
		sb.append(',');
		sb.append("Lon");
		sb.append(',');
		sb.append("Alt");
		sb.append(',');
		sb.append("speed/weight");
		sb.append(',');
		sb.append("Radius");
		sb.append(',');
		sb.append(String.valueOf(packmans.size()));
		sb.append(',');
		sb.append(String.valueOf(fruits.size()));
		sb.append('\n');
		java.util.Iterator<Packman>  it=packmans.iterator();
		int i=0;
		while(it.hasNext())
		{

			Packman newpakman=new Packman (it.next());
			sb.append("p");
			sb.append(',');
			sb.append(String.valueOf(i));
			sb.append(',');
			sb.append(String.valueOf(newpakman.getPoint().x()));
			sb.append(',');
			sb.append(String.valueOf(newpakman.getPoint().y()));
			sb.append(',');
			sb.append(String.valueOf(newpakman.getPoint().z()));
			sb.append(',');
			sb.append((String.valueOf(newpakman.getSpeed())));
			sb.append(',');
			sb.append((String.valueOf(newpakman.getRaduis())));
			sb.append('\n');
			i++;
		}
		java.util.Iterator<Fruit>  it2=fruits.iterator();
		i=0;
		while(it2.hasNext())
		{

			Fruit newfruit=new Fruit (it2.next());
			sb.append("F");
			sb.append(',');
			sb.append(String.valueOf(i));
			sb.append(',');
			sb.append(String.valueOf(newfruit.getPoint().x()));
			sb.append(',');
			sb.append(String.valueOf(newfruit.getPoint().y()));
			sb.append(',');
			sb.append(String.valueOf(newfruit.getPoint().z()));
			sb.append(',');
			sb.append((String.valueOf(newfruit.getWeight())));
			sb.append('\n');
			i++;
		}
		pw.write(sb.toString());
		pw.close();
		System.out.println("done!");

	}
	/**
	 * the function get a packman and add it to the Packman's arrayList
	 * @param packman
	 */
	public void addPackman(Packman packman)
	{
		packmans.add(packman);	
		packman.getPath().start(packman);
	}
	/**
	 * the function get a fruit and add it to the fruit's arrayList
	 * @param fruit
	 */
	public void addFruit(Fruit fruit)
	{
		fruits.add(fruit);	
	}
	/**
	 * the function get a layer and return it to a game
	 */
	public void layerToGame () {
		java.util.Iterator<GIS_element>  it=a.iterator();
		while(it.hasNext())
		{

			gameElement object=(gameElement) it.next();
			if(object.getData().type().equals("P"))
			{

				Packman a=new Packman(object.getData().speedORweight(),object.getGeom().getPoint(),object.getData().radius());
				a.getPath().start(a);
				packmans.add(a);

			}
			else
			{
				Fruit a=new Fruit(object.getGeom().getPoint(),object.getData().speedORweight());
				fruits.add(a);
			}
		}
		System.out.println("layertogame");
	}
	/**
	 * The function receives a CSV file and turns it into a layer
	 * @param csvFile
	 */
	public void csvToLayer (String csvFile)
	{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int num=0;
		layer layer=new layer (csvFile);
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				num++;
				// use comma as separator
				if (num>1)
				{
					String[] csvLine = line.split(cvsSplitBy);
					gameElement a=new gameElement (csvLine);
					layer.add(a);

				}
			}

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		a=layer;
	}
}