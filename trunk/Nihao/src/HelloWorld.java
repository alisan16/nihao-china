import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Hélène Martin
 *
 * Demonstrates creating Java applets.
 * I've done my best to balance usability with good coding conventions. 
 * There are some serious omissions.  For example, there really should be a start() method.  
 * I'll let the interested dig deeper on their own.
 *
 * MouseMotionListener is needed to trigger actions based on mouse movement and dragging.
 * MouseListener is needed to trigger actions based on mouse clicks and mouse context.
 * Runnable is needed to run some operations in parallel.
 */
public class HelloWorld extends Applet implements MouseMotionListener, MouseListener, Runnable {
	BufferedImage bulldog;
	Point bulldogPos;
	
	/**
	 * True if the user is dragging the bulldog around, false is the bulldog is stationary.
	 */
	boolean pickedUp;
	
	Thread runner;
	int frame;
	AudioClip sound;

	/* Called once when the applet is loaded.
	 */
	public void init() {
		setSize(200, 500);
		setBackground(Color.RED);
		
		try {
			bulldog = ImageIO.read(new File("bulldog.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bulldogPos = new Point(10, 100);	
		
		try {
			sound = getAudioClip(new URL(getCodeBase(), "yes.au"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// Create a button
		Button b = new Button("Click Me");
		// define what happens when the button is clicked
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Heya");
				sound.play();
			}
			
		});
		add(b);
		
		// Start animation
		runner = new Thread(this);
		runner.start();
		
		// Respond to mouse actions
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	/* Paint is called after init has completed and then whenever repaint() is called
	 * When called through repaint(), the screen is first cleared
	 */
	public void paint(Graphics g) {
		g.drawString("Frame: " + frame, 10, 100);
		g.drawImage(bulldog, bulldogPos.x, bulldogPos.y, null);
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
	public void mouseClicked(MouseEvent e) {
		if(pickedUp) {
			pickedUp = false;
		} else if(onBulldog(e.getPoint())) {
			pickedUp = true;
		}
	}
	
	private boolean onBulldog(Point p) {
		return p.getX() > bulldogPos.x && p.getX() < bulldogPos.x + bulldog.getWidth() &&
		   p.getY() > bulldogPos.y && p.getY() < bulldogPos.y + bulldog.getHeight();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(pickedUp) {
			bulldogPos.setLocation(e.getX(), e.getY());
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}