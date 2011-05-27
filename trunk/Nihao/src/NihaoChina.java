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
	int frame;
	ArrayList<Tile> greetings;
	ArrayList<Tile> dining;
	ArrayList<Tile> shopping;

	/* Called once when the applet is loaded.
	 */
	public void init() {
		setLayout(new BorderLayout());
        setSize(800, 800);
		setBackground(Color.RED);
		
		//placeholder for logo
		Tile welcome = new Tile(null, null, null);
		try {
			welcome = new Tile("Welcome", "Huan ying", getAudioClip(new URL(getCodeBase(), ("/WELCOME.wav"))));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		add(welcome, BorderLayout.NORTH);

		
		FlowLayout flow = new FlowLayout();
		Panel phrases = new Panel(flow);
		flow.setHgap(0);
		flow.setVgap(0);
		add(phrases, BorderLayout.CENTER);
		
		FlowLayout flow2 = new FlowLayout();
		Panel categories = new Panel(flow2);
		add(categories, BorderLayout.SOUTH);
		
        categories.add(new Button("Greetings"));
        categories.add(new Button("Dining"));
        categories.add(new Button("Shopping"));

		
		//creates ArrayLists of tiles for each of the categories
		//ArrayList<Tile> greetings = makeTiles("greetings");
		//ArrayList<Tile> dining = makeTiles("dining");
		ArrayList<Tile> shopping = makeTiles("shopping");

		
		//adds Tiles to the applet
		for(final Tile test1 : shopping){
			phrases.add(test1);
			test1.addActionListener(new ActionListener() {
				@Override
				//makes audioClip play when tile is clicked
				public void actionPerformed(ActionEvent arg0) {
					test1.getA().play();
					//System.out.println("hello");
				}
			});
		}

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
			nihao = getAudioClip(new URL(getCodeBase(), "1nihao.wav"));
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
			frame++;
			repaint();
			try {
				Thread.sleep(1000); // pause between frames
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
