package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

public class path2kml {
	/**
	 * the function get a game and return it to kml
	 * @param game
	 * @throws IOException
	 */
	public path2kml(game game) throws IOException
	{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"+"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
				"<Document>\n"+"<Style id=\"yellow\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/ylw-stars.png</href>"
				+"</Icon></IconStyle></Style><Style id=\"red\"><IconStyle><Icon><href>"
				+"http://maps.google.com/mapfiles/kml/paddle/red-stars.png</href>"
				+ "</Icon></IconStyle></Style><Folder>");
		Iterator <Packman> pack=game.iterator1();
		//it paint the packmans and the fruits 
		while(pack.hasNext())
		{
			
			Packman pac=new Packman (pack.next());
			Path path=pac.getPath();
			sb.append("<Placemark>\n"+"<name></name>\n"
					+"<description>"+"<![CDATA[speed/Weight: <b>"+pac.getSpeed()+"</b>]]></description>"+
					"<styleUrl>#yellow</styleUrl>\n"+"<Point>\n"+
					"<coordinates>"+pac.getPoint().y()+","+pac.getPoint().x()+"</coordinates></Point>\n"+
					"</Placemark>\n");
			Iterator<pathPoint> it=path.iterator();
			while(it.hasNext())
			{
					
				pathPoint fru=it.next();
				sb.append("<Placemark>\n"+"<name>"/*+"<![CDATA["+/*fru.id()+"]]>"*/+"</name>\n"
						+"<description>"+"<![CDATA[speed/Weight: <b>"+fru.getScorePoint()+"</b>]]></description>"
						+"<styleUrl>#red</styleUrl>\n"+"<Point>\n"+
						"<coordinates>"+fru.getPointGps().y()+","+fru.getPointGps().x()+"</coordinates></Point>\n"+
						"</Placemark>\n");	
			}	
		}
		long i=0;
		Iterator <Packman> pa=game.iterator1();
		while(pa.hasNext())
		{
			Packman pac=pa.next();
			Iterator <pathPoint> path=pac.getPath().iterator();
			//now it paint in the kml all the fruit with the time
			while(path.hasNext())
			{
				pathPoint pathpoint=path.next();
				sb.append("<Placemark>"+"<TimeStamp><when>"+pathpoint.getDate()+"</when></TimeStamp>"
						+"<styleUrl>#hiker-icon</styleUrl>"+
						"<Point><coordinates>"+pathpoint.getPointGps().y()+","+pathpoint.getPointGps().x()+"</coordinates></Point>"+
						" </Placemark>");
			}
		}

		sb.append("</Folder></Document></kml>");
		PrintWriter pw = null;
		String fileName ="GameKml.kml";
		try {
			pw = new PrintWriter(new FileWriter(fileName));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		pw.write(sb.toString());
		pw.close();
		System.out.println("done");
	}

}