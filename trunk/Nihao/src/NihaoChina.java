	import java.applet.Applet;
	import java.applet.AudioClip;
	import java.awt.*;
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
	ArrayList<Tile> phrases;

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

        /*try {
			Scanner s = new Scanner(new File("shopping.txt"));
			while(s.hasNextLine()){
				c.gridwidth = GridBagConstraints.REMAINDER; 
				makebutton("Hello", gridbag, c);
				s.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		// Create array list of categories
		Button a = new Button("Greetings");
		Button b = new Button("Dining");
		Button d = new Button("Shopping");
		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add(a);
		buttons.add(b);
		buttons.add(d);
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
		Tile test = new Tile("Hello", "Ni Hao", nihao);
		makebutton(test.getE(), gridbag, c);

    	  
		
		// Start animation
		runner = new Thread(this);
		runner.start();
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
