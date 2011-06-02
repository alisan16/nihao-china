	import java.applet.Applet;
	import java.applet.AudioClip;
	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
	import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class NihaoChina extends Applet implements MouseMotionListener, MouseListener, Runnable {
	
	Thread runner;
	ArrayList<Tile> greetings;
	ArrayList<Tile> dining;
	ArrayList<Tile> shopping;
	Panel greets;
	Panel shops;
	Panel dines;

	/* Called once when the applet is loaded.
	 */
	public void init() {
	    setSize(300, 300);
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		setBackground(Color.RED);
		borderLayout.setVgap(10);
		//placeholder for logo
		Tile welcome = new Tile(null, null, null);
		try {
			welcome = new Tile("Welcome", "Huan ying", getAudioClip(new URL(getCodeBase(), ("welcome.wav"))));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(welcome, BorderLayout.NORTH);
		GridLayout grid = new GridLayout(0,1);
		greets = new Panel(grid);
		shops = new Panel(grid);
		dines = new Panel(grid);
		
		FlowLayout flow2 = new FlowLayout();
		Panel categories = new Panel(flow2);
		add(categories, BorderLayout.SOUTH);
		
		//creates ArrayLists of tiles for each of the categories
		final ArrayList<Tile> greetings = makeTiles("greetings");
		final ArrayList<Tile> dining = makeTiles("dining");
		final ArrayList<Tile> shopping = makeTiles("shopping");
		Button g = new Button("Greetings");
        categories.add(g);
        g.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(dines);
				remove(shops);
				add(newScreen(greets, greetings), BorderLayout.CENTER);
				/*for(Tile test : greetings){
					System.out.println(test.getE());
				}*/
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
		// Respond to mouse actions
		addMouseMotionListener(this);
		addMouseListener(this);
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
		//g.drawString("Frame: " + frame, 10, 100);
	}
	
	/* Run is completed in parallel to other operations in the class
	 * In this case, repaint is called over and over again to create animation
	 */
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(100); // pause between frames
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
