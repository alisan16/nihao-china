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
	AudioClip sound;
	int frame;
	ArrayList<Tile> greetings;
	ArrayList<Tile> dining;
	ArrayList<Tile> shopping;

	/* Called once when the applet is loaded.
	 */
	public void init() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setSize(300, 500);
		setBackground(Color.RED);

        setFont(new Font("Helvetica", Font.PLAIN, 14));
        setLayout(gridbag);
        c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
        makebutton("Greetings", gridbag, c);
        c.gridwidth = GridBagConstraints.RELATIVE; 
        makebutton("Dining", gridbag, c);
        c.gridwidth = GridBagConstraints.REMAINDER; 
        makebutton("Shopping", gridbag, c);
        c.weightx = 0.0;                   //reset to the default
        makebutton("Button5", gridbag, c); //another row

		// Create array list of categories
		Button a = new Button("Greetings");
		Button b = new Button("Dining");
		Button d = new Button("Shopping");
		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add(a);
		buttons.add(b);
		buttons.add(d);
		//creates ArrayList of string name of categories
		
		ArrayList<Tile> greetings = new ArrayList<Tile>();
		ArrayList<Tile> dining = new ArrayList<Tile>();
		ArrayList<Tile> shopping = new ArrayList<Tile>();
		
        try {
			Scanner fileScan = new Scanner(new File("shopping.txt"));
			while (fileScan.hasNext()) {
				shopping.add(new Tile(fileScan.nextLine(), fileScan.nextLine(), null));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for(Tile testt : shopping){
			System.out.println(testt.getE());
			System.out.println(testt.getC());
			System.out.println();
		}*/
		
		// define what happens when the button is clicked
		/*for(Button button : buttons)
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
				}	
			});*/
			/*add(a);
			add(b);
			add(d);
		*/
        


		
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
			
		});
    	  
		
		// Start animation
		runner = new Thread(this);
		runner.start();
		// Respond to mouse actions
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	protected void makeTile(Tile t, GridBagLayout gridbag, GridBagConstraints c) {
		gridbag.setConstraints(t, c);
		add(t);
	}
	
    protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
    	Button button = new Button(name);
    	gridbag.setConstraints(button, c);
    	add(button);
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
