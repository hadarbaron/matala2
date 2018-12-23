package game;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
public class ShortestPathAlgo {
	/**
	 * The class receives an object of the GAME class and runs the algo on it.
	 * the Algo passes the Pacmans and puts everyone in the PATH the FRUITS that close to him
	 * @param GAME
	 */
	public ShortestPathAlgo(game GAME)
	{

		Iterator<Packman>  itpackman=GAME.iterator1();	
		Iterator<Fruit>  serchmin= GAME.iterator2();
		Iterator<Fruit>  pointmin= GAME.iterator2();
		int counterPackmans=GAME.sizePackmans();
		int counterFruits=GAME.sizeFruits();
		double distance=0;
		Fruit fruit;
		Fruit fruitcloser=new Fruit();
		double min=Double.MAX_VALUE;
		Calendar c=Calendar.getInstance();
		int h=0;
		SimpleDateFormat f=new SimpleDateFormat("yyy-MM-dd HH:mm;ss");
		String sd=f.format(c.getTime());
		while(itpackman.hasNext()&&(counterFruits!=0))
		{
			Packman p= itpackman.next();
			while(serchmin.hasNext())
			{

				fruit=serchmin.next();
				if (!fruit.geteat())
				{
					distance=p.getPath().lastPath().getPointGps().distance3D(fruit.getPoint());
					if (distance<min)
					{
						min=distance;
						fruitcloser=fruit;
					}
				}
			}
			serchmin= GAME.iterator2();
			p.getPath().addPathPoint(new pathPoint (fruitcloser));
			counterFruits--;
			min=Double.MAX_VALUE;
			distance=0;
			fruitcloser.eatTheFruit();
			if ((itpackman.hasNext()==false)&&(counterFruits!=0))
			{
				itpackman=GAME.iterator1();
			}
		}
		Iterator <Packman> pack=GAME.iterator1();
		while(pack.hasNext())
		{
			Packman packman=pack.next();
			Iterator <pathPoint> path=packman.getPath().iterator();
			while(path.hasNext())
			{
				Calendar cal =c.getInstance();
				h =h+ 5*1000;
				cal.add(c.SECOND, h);
				SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				String sDate =date.format(cal.getTime());
				pathPoint pathpoint=path.next();
				pathpoint.setTime(sDate);


			}
		}



	}
}