import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	Font bigFont = new Font("Serif", Font.BOLD, 25);
	Font medFont = new Font("Serif", Font.BOLD, 10);
	Background b = new Background();
	mainCharacter oddBod = new mainCharacter();

	mineTurtle duck = new mineTurtle();

	int roundTimer;
	int score;
	int oddBodVx = 1;
	int oddBodVy = 1;
	long time;
	int currentRound = 1;

	public void init() {
		roundTimer = 10;
		score = 0;
		time = 0;
// Set the intial position for oddBod
		oddBod.setWidthHeight(200, 200);
		oddBod.setScale(0.1, 0.1);
		oddBod.setXY(0, 0);
		oddBod.setVx(oddBodVx);

// Set the initial position for duck
		duck.setScale(0.5, 0.5);
		duck.setXY(-50, 400);
// duck.setVx(10);

		b.setScale(3, 3);
		b.setXY(-50, -100);

	}

	public void reset() {
// init();
		
		oddBod.setXY(((int) (Math.random() * (790)) + 10),((int) (Math.random() * (390)) + 10));
		int randVx = (int) (Math.random() * (4)) + 1;
		
		oddBodVx += randVx;
		oddBod.setVx(oddBodVx);
		
		int randVy = (int) (Math.random() * (4)) +1;
		
		oddBodVy += randVy;
		oddBod.setVy(oddBodVy * -1);

		duck.setXY(-50, 400);
		duck.setVx(0);

	}

	public void nextRound() {
// reset position after timer runs out
// oddBod.setXY(250, 50);
// start off the screen

		currentRound += 1;
		oddBod.setXY(0, 0);
		roundTimer = 30;

	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		time += 20;

		if (time % 1000 == 0) {
			roundTimer -= 1;
			if (roundTimer == 0) {
				nextRound();
				t.stop();
			}
		}

		if (roundTimer == 30) {
			Font messageFont = new Font("Serif", Font.BOLD, 30);
			g.setFont(messageFont);
			g.drawString("Press the spacebar to start the next round", 250, 250);

		}

		b.paint(g);

		oddBod.paint(g);

		duck.paint(g);

// make it bounce around

		/*
		 * if (oddBod.getY() > 100) { oddBod.setX(50); oddBod.setY(50); // add math
		 * random
		 *
		 * oddBod.setVx(3); oddBod.setVy(0);
		 *
		 * }
		 */

		if (oddBod.getX() > 800) {
			oddBod.setVx(oddBod.getVx() * -1);
		}

		if (oddBod.getX() < -40) {
			oddBod.setVx(oddBod.getVx() * -1);
		}

		if (oddBod.getY() < 0 || oddBod.getY() > 600) {
			oddBod.setVy(oddBod.getVy() * -1);
		}
		
		
		// intersect then this happens:
		
		
		if( oddBod.getY() >= duck.getY()) {
			oddBod.setVy(0);
			
			oddBod.setVx(50);
			duck.setVx(50);
		}
		
		if( duck.getX() >= 1000) {
			reset();
			
		}
		
		/* test code for bouncing the dock */
		/*
		 * if (duck.getX() > 800) { duck.setVx(duck.getVx() * -1); }
		 *
		 * if (duck.getX() < -40) { duck.setVx(duck.getVx() * -1); }
		 */

// duck free falling
		/*
		 * if(duck.getVx() == 0 && duck.getY() > 400) { duck.setVy(-3); } if(duck.getX()
		 * > 700) { duck.setVx(duck.getVx()*-1); }
		 *
		 * if(duck.getX() < -40 ) { duck.setVx(duck.getVx()*-1); }
		 *
		 * if(duck.getY() < 0 || duck.getY() > 800) { duck.setVy(duck.getVx()*-1); }
		 */

// play width diffnumbers

// if(duck.getY()<400) {
// }

		g.setFont(bigFont);

		g.drawString("Time left = " + this.roundTimer, 700, 50);
		g.drawString("Score = " + this.score, 700, 500);
// g.setFont(medFont);
		g.drawString("Round" + this.currentRound, 700, 550);

	}

	public static void main(String[] arg) {
		Frame f = new Frame();
	}

	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(900, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1, 2));
		f.addMouseListener(this);
		f.addKeyListener(this);

		init();

		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

// now
	Timer t = new Timer(16, this);

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
	public void mousePressed(MouseEvent mouse) {
// TODO Auto-generated method stub
		Rectangle rMouse = new Rectangle(mouse.getX(), mouse.getY(), 25, 25);

		Rectangle rMain = new Rectangle(oddBod.getX(), oddBod.getY(), oddBod.getWidth(), oddBod.getHeight());

		System.out.println("orig oddBod getX::" + oddBod.getX());
		System.out.println("orig oddBod getY::" + oddBod.getY());
// OddBod will fall down when the mouse is clicked on it
		if (rMouse.intersects(rMain)) {
			System.out.println("oddBod getX::" + oddBod.getX());
			System.out.println("oddBod getY::" + oddBod.getY());
			oddBod.setVy(10);
			oddBod.setVx(0);
			score++;

// Now that mouse clicked oddBod; duck will come now to pick up the oddBod
			duck.setX(oddBod.getX());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}

// Now check duck X and oddBod X position;
// matching then both oddBod and duck will go together towards right
			System.out.println("duck getX::" + duck.getX());
			int loopCounter = 0;
			while (true) {
				if (duck.getX() == oddBod.getX()) {
					System.out.println("duck and oddBod met at X:: " + duck.getX());
					duck.setVx(0);
				}
				loopCounter++;
				if (loopCounter == 500) {
					break;
				}
			}

		}

		/*
		 * This code was making oddBod sit at a particular position, we do not want now
		 * if (oddBod.getY() < 400 && oddBod.getY() > 75) {
		 * System.out.println(" Y is 50"); oddBod.setVy(0); }
		 */

	

	@Override
	public void mouseReleased(MouseEvent arg0) {
// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());

// spacebar continuye the round
		if (arg0.getKeyCode() == 32) {
// if(t.isRunning()) {
			t.start();
// }
			reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
// TODO Auto-generated method stub

	}

}
