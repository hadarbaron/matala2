package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class gameElement implements GIS_element {

	String []a;
	Point3D point;
	Meta_data data;
	public gameElement (String [] s)
	{
		a=s;
		point=new Point3D(Double.parseDouble(a[2]),Double.parseDouble(a[3]),Double.parseDouble(a[4]));
		data=new gameData(a);
	}
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return point;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}

}
