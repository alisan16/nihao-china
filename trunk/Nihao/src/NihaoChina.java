import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;


public class NihaoChina extends Applet implements Runnable {
	
	BufferedImage logo;
	Thread runner;
	ArrayList<Tile> greetings;
	ArrayList<Tile> dining;
	ArrayList<Tile> shopping;
	private Panel greets;
	private Panel shops;
	private Panel dines;
	private AudioClip welA;
	private Tile welcome;

	/* Called once when the applet is loaded.
	 */
	public void init() {
	    setSize(300, 500);
		
	    //Makes BorderLayout for the applet
	    BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		setBackground(Color.RED);
		borderLayout.setVgap(110);
		
		//placeholder for logo
		//adds logo to area labeled NORTH
		try {
			welA = getAudioClip(new URL(getCodeBase(), ("welcome.wav")));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		welcome = new Tile("Welcome", "Huan ying", welA);
		welcome.addActionListener(new ActionListener() {
			@Override
			//makes audioClip play when tile is clicked
			public void actionPerformed(ActionEvent arg0) {
				welcome.switchLabel();
				welcome.getAudio().play();
				//System.out.println("hello");
			}
		});
		
		add(welcome, borderLayout.NORTH);
		
		try {
			logo = ImageIO.read(new File("logo.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		/*DrawingPanel p = new DrawingPanel (600, 600);
		Color lightBlue = new Color(175, 238, 238);
		p.setBackground(lightBlue);
		Graphics graph = p.getGraphics();
		paint(graph);*/
		
		//Makes three different panels that contain GridLayouts for each of the phrase categories
		GridLayout grid = new GridLayout(0,1);
		greets = new Panel(grid);
		shops = new Panel(grid);
		dines = new Panel(grid);
		
		//Makes a panel of one flow layout with the buttons corresponding to each phrase category
		//Adds the categories panel to the area labeled SOUTH
		FlowLayout flow2 = new FlowLayout();
		Panel categories = new Panel(flow2);
		add(categories, BorderLayout.SOUTH);
		
		//initializes ArrayLists of tiles for each of the categories
		greetings = makeTiles("greetings");
		dining = makeTiles("dining");
		shopping = makeTiles("shopping");
		
		//
		Button g = new Button("Greetings");
        categories.add(g);
        g.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(dines);
				remove(shops);
				add(newScreen(greets, greetings), BorderLayout.CENTER);
			}
		});
        
		Button d = new Button("Dining");
        categories.add(d);
        d.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(greets);
				remove(shops);
				add(newScreen(dines, dining), BorderLayout.CENTER);
				/*for(Tile test1 : dining){
					System.out.println(test1.getE());
				}	*/
			}
		});
        
		Button s = new Button("Shopping");
        categories.add(s);
        s.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(greets);
				remove(dines);
				add(newScreen(shops, shopping), BorderLayout.CENTER);
				/*for(Tile test2 : greetings){
					System.out.println(test2.getE());
				}*/
			}
		});

		//for debugging
		/*for(Tile testt : shopping){
			System.out.println(testt.getE());
			System.out.println(testt.getC());
			System.out.println();
		}*/
		//for testing out one Tile
		/*
		AudioClip nihao = null;
		try {
			nihao = getAudio`Clip(new URL(getCodeBase(), "1nihao.wav"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Tile test = new Tile("Hello", "Ni Hao", nihao);
		makeTile(test, gridbag, c);
		test.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				test.getA().play();
				System.out.println("hello");
			}
		});*/
		// Start animation
		runner = new Thread(this);
		runner.start();
	}
	
	public Panel newScreen(Panel p, ArrayList<Tile> list){
		Panel pane = p;
		//adds Tiles to the applet
		for(final Tile t : list){
			pane.add(t);
			t.addActionListener(new ActionListener() {
				@Override
				//makes audioClip play when tile is clicked
				public void actionPerformed(ActionEvent arg0) {
					//NOTE: only works every other time you click one of the categories
					t.switchLabel();
					t.getAudio().play();
					//System.out.println("hello");
				}
			});
		}
		return pane;
	}
	
	public ArrayList<Tile> makeTiles(String name){
		//make ArrayList of Tiles for shopping phrases 
		ArrayList<Tile> list = new ArrayList<Tile>();
		try {
			Scanner fileScan = new Scanner(new File(name + ".txt"));
			int num = 1;
			while(fileScan.hasNext()){
				AudioClip temp = null;
				try {
					temp = getAudioClip(new URL(getCodeBase(), (name + "/" + num + ".wav")));
					num++;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(new Tile(fileScan.nextLine(), fileScan.nextLine(), temp));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/* Paint is called after init has completed and then whenever repaint() is called
	 * When called through repaint(), the screen is first cleared
	 */
	public void paint(Graphics g) {
		g.drawImage(logo, 100, 10, null);
	}
	
	/* Run is completed in parallel to other operations in the class
	 * In this case, repaint is called over and over again to create animation
	 */
	@Override
	public void run() {
		while(true) {
			repaint();
			try {
				Thread.sleep(100); // pause between frames
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
