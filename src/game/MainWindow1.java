package game;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.omg.PortableServer.THREAD_POLICY_ID;

import Geom.Point3D;


public class MainWindow1 extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	int x = -1;
	int y = -1;
	BufferedImage img2 = null;
	BufferedImage img1 = null;
	game game=new game();
	boolean isGamer=true;//to know if press packman or fruit
	boolean entercsv=false;//if the game load a csv
	boolean endgame=false;//if the game is end
	//if the game is alive
	boolean gameAction=false;
	ArrayList<Point3D> packmanlist = new ArrayList<Point3D>();
	ArrayList<Point3D> fruitlist= new ArrayList<Point3D>();

	public MainWindow1() 
	{
		initGUI();		
		this.addMouseListener(this); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initGUI() 
	{ 
		//*******puting all the item***********
		MenuBar menuBar = new MenuBar();

		Menu File = new Menu("FILE");

		MenuItem item1 = new MenuItem("EXPORT CSV ");
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					writeFileDialog();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		MenuItem item2 = new MenuItem("IMPORT CSV");
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readFileDialog();
			}
		});
		Menu RealTimeGame = new Menu("F/P"); 

		MenuItem item3 = new MenuItem("FRUIT");

		item3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = true;
			}
		});
		MenuItem item4 = new MenuItem("PACKMAN");

		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = false;
			}
		});
		Menu gameaction = new Menu("REAL TIME GAME"); 

		MenuItem item5 = new MenuItem("END GAME");
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endgame();
			}
		});
		MenuItem restart = new MenuItem("RESTART");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restartgame();
			}
		});
		MenuItem kml = new MenuItem("CONVERT TO KML");
		kml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					converToKml();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		MenuItem gameAction = new MenuItem("RUN");
		gameAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gameaction1();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		menuBar.add(File);
		menuBar.add(RealTimeGame);
		menuBar.add(gameaction);
		File.add(item1);
		File.add(item2);
		File.add(kml);
		RealTimeGame.add(item3);
		RealTimeGame.add(item4);
		gameaction.add(item5);
		gameaction.add(restart);
		setMenuBar(menuBar);
		gameaction.add(gameAction);
		//******end of items***********

		//*********reading pictures**************
		try {
			myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			img1=ImageIO.read(new File ("packman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			img2=ImageIO.read(new File ("apple.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		//************end of reading pictures*******************
		this.setSize(myImage.getWidth(),myImage.getHeight());
	}
	public void paint(Graphics g)
	{
		
		g.drawImage(myImage,0 , 0,getWidth(),getHeight(), this);
		//********painting the game**********************
		g.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
		Iterator <Packman> pack=game.iterator1();
		Iterator <Fruit>fruit=game.iterator2();
		int i=0;
		int j=0;
		while(pack.hasNext())
		{
			Point3D a=new Point3D(pack.next().getPoint());
			Point3D p=new Point3D(map.gpsToPix(this.getWidth(), this.getHeight(), a));
			g.drawImage(img1,p.iy()-10,p.ix()-10,40,40,this);
		}
		while(fruit.hasNext())
		{
			
			Point3D a=new Point3D(fruit.next().getPoint());
			Point3D p=new Point3D(map.gpsToPix(this.getWidth(), this.getHeight(), a));
			g.drawImage(img2, p.iy()-10, p.ix()-10, 40, 40, this);
		}
		//***********draw the line when the game is end*************
		if (endgame)
		{
			Iterator <Packman> csvpack1=game.iterator1();
			while(csvpack1.hasNext())
			{
				Packman packman=new Packman (csvpack1.next());
				Iterator<pathPoint>  itpathpoint=packman.getPath().iterator();
				Point3D point1=new Point3D (itpathpoint.next().getPointGps());
				while(itpathpoint.hasNext())
				{
					Point3D point2=new Point3D(itpathpoint.next().getPointGps());
					Point3D newp1=map.gpsToPix(this.getWidth(), this.getHeight(),  point2);
					Point3D newp2=map.gpsToPix(this.getWidth(), this.getHeight(),  point1);
					g.setColor(Color.BLUE);
					Graphics2D _paper2D=(Graphics2D)g;
					_paper2D.setStroke(new BasicStroke(6F));
					g.drawLine(newp1.iy(), newp1.ix(),(int)newp2.iy(),(int)newp2.ix());
					point1=new Point3D(point2);
				}
			}
		}
	}
	public void mouseClicked(MouseEvent arg) 
	{
	}
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * mousedPressed get a x and y in pix and add it to the arraylist in the game.
	 * if it fruit it will add to the fruit arrylist else to the packman arrylist. 
	 * @param arg
	 */
	public void mousePressed(MouseEvent arg) {
		// TODO Auto-generated method stub
		{
			System.out.println("mouse Clicked");
			x = arg.getX();
			y = arg.getY();
			if(isGamer) {
				Point3D newpix=new Point3D (x,y);
				Point3D p=new Point3D (map.pixToGps(getWidth(),this.getHeight(),newpix));
				Fruit fruit=new Fruit (p,1);
				game.addFruit(fruit);
			}
			else
			{
				Point3D newpix=new Point3D (x,y);
				Point3D p=new Point3D (map.pixToGps(getWidth(),this.getHeight(),newpix));
				Packman pack=new Packman(1,p,1);
				game.addPackman(pack);
			}


			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	//*****new function for the items**********


	/**
	 * this function get a game and convert it to a csv file
	 * @throws IOException
	 */
	public void writeFileDialog() throws IOException {
		//		 try write to the file
		FileDialog fd = new FileDialog(this, "Save the text file", FileDialog.SAVE);
		fd.setFile("*.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		game.gameTocsv(folder+fileName);

	}
	/**
	 * this function read a csv file to the game
	 * 
	 */
	public void readFileDialog() {
		entercsv=true;
		FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");

		fd.setDirectory("C:\\Users\\DELL\\Desktop\\csv1");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		game.csvToLayer(folder+fileName);
		game.layerToGame();
	
		repaint();
	}
	/**
	 * if the game end 
	 */
	public void endgame() {
		endgame=true;
		ShortestPathAlgo algo=new ShortestPathAlgo(game);
		repaint();
	}
	/**
	 * if the item to restart the game press
	 */
	public void restartgame()
	{
		isGamer=true;//to know if press packman or fruit
		entercsv=false;//if the game load a csv
		endgame=false;//if the game is end
		gameAction=false;
		game=new game();
		repaint();
	}

	/**
	 * the function get a game and convert it to kml
	 * @throws IOException
	 */
	public void converToKml() throws IOException
	{
		path2kml kml=new path2kml(game);
	}

	/**
	 * this fuction created for each packman thread for the real time game
	 * @throws InterruptedException 
	 */
	public void gameaction1() throws InterruptedException
	{
		gameAction=true;
		ShortestPathAlgo algo=new ShortestPathAlgo(game);
		Iterator <Packman> csvpack2=game.iterator1();
		while(csvpack2.hasNext())
		{
			Packman packman=csvpack2.next();
			packTH packth=new packTH(this,packman);
			packth.start();
		}
	}
	public static void main(String[] args) {
		MainWindow1 Window=  new MainWindow1();
		Window.setVisible(true);
	}
}