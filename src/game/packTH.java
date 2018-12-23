package game;
import java.util.ArrayList;
import java.util.Iterator;
import Geom.Point3D;

/**
 * The department inherits THREAD and creates a Pacman object that is THREAD
 * @author shira AND hadar
 *
 */
public class packTH extends Thread{

	private MainWindow1 mw;
	private Packman pack;

	public packTH (MainWindow1 mw,Packman pack)
	{
		this.mw=mw;
		this.pack=pack;

	}
	public void run() {
		try {
			Drawpac();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//********private function**************
	private void Drawpac () throws InterruptedException
	{
		Point3D first= new Point3D(pack.getPoint());
		Iterator <pathPoint> path=pack.getPath().iterator();
		while(path.hasNext())
		{
			pathPoint path1=path.next();
			Point3D p=path1.getPointGps();
			pack.setPoint(p);	
			mw.repaint();
			Thread.sleep(1000);
		}
		pack.setPoint(first);
	}
}
