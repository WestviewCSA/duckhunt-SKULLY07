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
	mainCharacter him = new mainCharacter();
	
	 mineTurtle shell = new mineTurtle();
	
	
	int roundTimer;
	int score;
	long time;
	int currentRound = 1;


	public void init() {
		roundTimer = 10;
		roundTimer++;
		score = 0;
		time = 0;
		
		
		him.setWidthHeight(200, 200);
		him.setScale(0.1,0.1);
		him.setVx(1);
		
		shell.setScale(0.5, 0.5);
		shell.setX(50);
		shell.setY(700);
		
		b.setScale(3,3);
		b.setXY(-50,-100);
		
	}
	
	public void reset() {
		//init();
		
		currentRound+=1;
		
		him.setXY(50,100);
		

		
	}
	 public void nextRound() {
		 
		 him.setXY(250, 250);
		 // start off the screen 
		 
		 int randVx = (int)(Math.random()*(4))+1;
		 him.setVx(randVx+ currentRound);
		 
		 roundTimer = 30;
		 
	 }
	public void paint(Graphics g) {
		super.paintComponent(g);
		time += 20;
		
		
		if(time%1000 == 0) {
			roundTimer -= 1;
			if( roundTimer == 0) {
				nextRound();
				t.stop();
			}
		}
	
		
		if(roundTimer == 30) {
			Font messageFont =  new Font("Serif", Font.BOLD, 30);
			g.setFont(messageFont);
			g.drawString("Press the spacebar to start the next round", 250, 250);

		}
		
		b.paint(g);
		
		him.paint(g);
		
		shell.paint(g);

		
		//logic for resetting dog position
		
		// make it bounce around
		
		
		if( him.getY() > 400) {
			him.setX(50);
			him.setY(50);
			// add math random
			
			him.setVx(3);
			him.setVy(0);
			
			
		}
		
		if(him.getX() > 700) {
			him.setVx(him.getVx()*-1);
		}
		
		if(him.getX() < -40 ) {
			him.setVx(him.getVx()*-1);
		}
		
		if(him.getY() < 0 || him.getY() > 800) {
			him.setVy(him.getVx()*-1);
		}
		
		//duck free falling
	/*	if(shell.getVx() == 0 && shell.getY() > 400) {
			shell.setVy(-3);
			}
		if(shell.getX() > 700) {
			shell.setVx(shell.getVx()*-1);
		}
		
		if(shell.getX() < -40 ) {
			shell.setVx(shell.getVx()*-1);
		}
		
		if(shell.getY() < 0 || shell.getY() > 800) {
			shell.setVy(shell.getVx()*-1);
		}
		*/
	
		// play width diffnumbers
		
	//	if(shell.getY()<400) {
	//	}
		
		g.setFont(bigFont);
		
		g.drawString("Time left = "+this.roundTimer, 200, 100);
		g.drawString("Score = "+this.score, 600, 100);
		g.setFont(medFont);
		g.drawString("Round"+ this.currentRound, 200, 400);
			
		
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
		f.setLayout(new GridLayout(1,2));
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
	Rectangle rMouse = new Rectangle(mouse.getX(), mouse.getY(), 25,25);
	
	Rectangle rMain = new Rectangle( 
			him.getX() , him.getY(),
			him.getWidth(), him.getHeight()
			);
	
	
	if(rMouse.intersects(rMain)) {
		him.setVy(10);
		him.setVx(0);
		score++;
		
	public void paint2(Graphics g) {
		super.paintComponent(g);
		g.drawLine(shell.getX(), shell.getY(), him.getX(), him.getX());
		
			
		// odd bod must be chnaged everywhere else.
		
		shell.setX(him.getX()); // may need some offset to center
		shell.setY(400);// incase dog in the abyss
		
		
	 }
	

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
		
		//spacebar continuye the round
		if(arg0.getKeyCode()== 32) {
			//if(t.isRunning()) {
			t.start();
			//}
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
