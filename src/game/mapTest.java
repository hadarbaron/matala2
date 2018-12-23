package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Geom.Point3D;

class mapTest {

	@Test
	void gpsToPixtest() 
	{
		Point3D g1= new Point3D (32.106046,35.202574);
		Point3D ans= new Point3D (0,0);
		Point3D g2=map.gpsToPix(1433, 642, g1);
		if(ans.x()!= g2.x()||ans.y()!= g2.y()) {
			fail("the gpsToPix function dosent work well!!");
		}
	}
	@Test
	void pixToGpstest()
		{
			Point3D g1= new Point3D (32.106046,35.202574);
			Point3D ans= new Point3D (0,0);
			Point3D g2=map.pixToGps(1433, 642, ans);
			if(g1.x()!= g2.x()||g1.y()!= g2.y()) 
			{
				fail("the pixToGps function dosent work well!!");
			}

		}
	@Test
	void angelTest() {
		Point3D pi1= new Point3D (0,0);
		Point3D pi2= new Point3D (1433,642);
		double ans= map.angal(1433,642,pi1, pi2);
		if(ans != 116.85550057683497) {
			fail("this gpsTopixel isen working well!!" );
		}
	}
	@Test
	void disTest() {
		Point3D pi1= new Point3D (0,0);
		Point3D pi2= new Point3D (1433,642);
		double ans= map.disBPix(1433,642,pi1, pi2);
		if(ans != 1030.7526588619662) {
			fail("this gpsTopixel isen working well!!" );
		}
	}

}
