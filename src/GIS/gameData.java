package GIS;

import Geom.Point3D;
/**
 * The department implements the META DATA department.
 *  The class is designed to read CSV using a line that will become an object in this class
 * @author shira AND hadar
 *
 */
public class gameData implements Meta_data {

	String [] s;
	String type;
	int id;
	int speed;
	int weight;
	int radios;

	public gameData(String []a )
	{
		s=a;
	}
	public String type()
	{
		return s[0];
	}
	public int id()
	{
		return Integer.parseInt(s[1]);
	}
	public int speedORweight() {
		return Integer.parseInt(s[5]);
	}
	public int radius() {
		return Integer.parseInt(s[6]);
	}

	public String getSSID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstSeen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRSSI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMac() {
		// TODO Auto-generated method stub
		return null;
	}

}
