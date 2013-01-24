package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
	  
public class GUI extends JPanel implements ActionListener {
	private static final int THREE = 3, FOUR = 4, EIGHT = 8, BOXSIZE = 50, ONEHUNDREDNINETY = 190, THREEHUNDRED = 300;
	private static final int THREEHUNDREDSIXTY = 360, TENTHOUSAND = 10000;
	private static final double ZEROONE = 0.1, ZEROFINTEEN = 0.15, ZEROTWO = 0.2;
	private static final double ZEROFOURTYFIVE = 0.45, ZEROTHREE = 0.3, ZEROFOUR = 0.4, ZEROSIX = 0.6;
	private int width = TENTHOUSAND, height = TENTHOUSAND, foodRadius = BOXSIZE / EIGHT;
	//up, down, left, right
	private final int[] mouthDirections = {120, 300, 210, 30};
	
	private JFrame frame;
	private JMenuItem menuFileHelp;
	private JMenuItem menuFileExit;

	
	public GUI() {
		
	}
	
	//allowing other classes to refresh the frame
	protected void update() {
		frame.repaint();
	}
	
	protected void setFrameTitel(String titel) {
		frame.setTitle(titel);
	}
	
	//draw wall to the frame
	private void drawWallRect(Graphics g, int idx) {
		g.setColor(Color.BLUE);
		g.fillRect((idx % TUI.getGrid().getWidth()) * BOXSIZE, (idx / TUI.getGrid().getWidth()) * BOXSIZE, BOXSIZE - 1, BOXSIZE - 1);
	}
	
	//draw food to the frame
	private void drawFood(Graphics g, int idx) {
		g.setColor(Color.WHITE);
		g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + BOXSIZE / 2 - foodRadius, 
				(idx / TUI.getGrid().getWidth()) * BOXSIZE + BOXSIZE / 2 - foodRadius, foodRadius * 2, foodRadius * 2);
	}
	
	//draw player to the frame
	private void drawPlayer(Graphics g, int idx) {
		g.setColor(Color.YELLOW);
		g.fillArc((idx % TUI.getGrid().getWidth()) * BOXSIZE + 2, idx / TUI.getGrid().getWidth() * BOXSIZE + 2,
		BOXSIZE - FOUR, BOXSIZE - FOUR, mouthDirections[TUI.getDirection()], THREEHUNDRED);
		
		//giving the man an eye
		g.setColor(Color.BLACK);
		if (TUI.getDirection() == 2) {
			//left
			g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROTHREE), 
					(idx / TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROONE), foodRadius * 2, foodRadius * 2);
		} else if (TUI.getDirection() == THREE) {
			//right
			g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROFOURTYFIVE), 
					(idx / TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROONE), foodRadius * 2, foodRadius * 2);
		} else if (TUI.getDirection() == 0) {
			//up
			g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROFINTEEN), 
					(idx / TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROTHREE), foodRadius * 2, foodRadius * 2);
		} else if (TUI.getDirection() == 1) {
			//down
			g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROSIX), 
					(idx / TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROFOUR), foodRadius * 2, foodRadius * 2);
		}
	}
	
	//draw ghost/s to the frame
	private void drawGhost(Graphics g, int idx) {
		g.setColor(Color.RED);
		g.fillArc((idx % TUI.getGrid().getWidth()) * BOXSIZE + 2, idx / TUI.getGrid().getWidth() * BOXSIZE + 2,
		BOXSIZE - FOUR, BOXSIZE - FOUR, 0, THREEHUNDREDSIXTY);	
		
		//give eyes
		g.setColor(Color.BLACK);
		g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROTWO), 
				(idx / TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROTHREE), foodRadius * 2, foodRadius * 2);
		g.fillOval((idx % TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROSIX), 
				(idx / TUI.getGrid().getWidth()) * BOXSIZE + (int)(BOXSIZE * ZEROTHREE), foodRadius * 2, foodRadius * 2);
	}
	
	//controls the drawing to the frame
	protected void drawGame(Graphics g) {
		//for all fields of the grid
		for (int i = 0; i < TUI.getGrid().getWidth() * TUI.getGrid().getHeight(); i++) {
			//draw a "wall-rectangle" if the current field is a wall
			if (TUI.getGrid().isWall(i)) {
				drawWallRect(g, i);
			//or a point if it is food
			} else if (TUI.getGrid().isFood(i)) {
				drawFood(g, i);
			//or the PacMan if it is the position of the player
			} else if (TUI.getGrid().isPlayer(i)) {
				drawPlayer(g, i);
			}
			if (TUI.getGrid().isGhost(i)) {
				drawGhost(g, i);
			}
		}		
	}
	
	//overwriting to draw to the frame  
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, width * BOXSIZE, height * BOXSIZE);	
		drawGame(g);
	}
	
	//checking if a key with a function is pressed
	void checkPressedKey(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			//instruction to gui for moving up
			TUI.run("w");
		} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			//instruction to gui for moving left
			TUI.run("a");
		} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			//instruction to gui for moving down
			TUI.run("s");
		} else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			//instruction to gui for moving right
			TUI.run("d");
		}  else if (e.getKeyCode() == KeyEvent.VK_Q) {
			//instruction to gui for exit the program
			TUI.run("q");
		}	
	}
	
	protected void create(int newwidth, int newheight) {
		//update global width and height
		width = newwidth;
		height = newheight;
		//create a frame with title "PacMan"
		frame = new JFrame();
		frame.setTitle("PacMan");
		
        //make it possible to respond if any key pressed
        frame.addKeyListener(
	        new KeyListener(){
				@Override
				public void keyPressed(KeyEvent e) {
					checkPressedKey(e);
				}

				@Override
				public void keyTyped(KeyEvent e) {
					//not required
				}

				@Override
				public void keyReleased(KeyEvent e) {
					//not required
				}
	        }
        );	
        
		
		//create a menu and a menu bar
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("Datei");
		
		//create two menu items and register to actionListener
        menuFileHelp = new JMenuItem("Hilfe");
        menuFileHelp.addActionListener(this);
        menuFileExit = new JMenuItem("Beenden");
        menuFileExit.addActionListener(this);
        
        //build the menu
        menu.add(file);
        file.add(menuFileHelp);
        file.addSeparator();
        file.add(menuFileExit);      

        //placing the menu to the top
        frame.getContentPane().add(menu, BorderLayout.PAGE_START);
        //close program on default close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //disable resizing
        frame.setResizable(false);
        //set the desktop z-axis from frame to front
        frame.setAlwaysOnTop(true);
        //showing window with frame
        frame.add(new GUI());
        frame.setVisible(true);   
        
        //set the correct size of the window
        frame.setSize(frame.getInsets().left + frame.getInsets().right  + newwidth * BOXSIZE,
        		frame.getInsets().top +  frame.getInsets().bottom + newheight * BOXSIZE + menu.getHeight());
        //center window
        frame.setLocationRelativeTo(null);
	}
	
	//close program
	protected void exitProgram(int ret) {
		System.exit(ret);
	}
	
	//build and show the help frame
	void showHelpFrame() {
		//create a label to show the instruction
		JFrame helpFrame = new JFrame("PacMan Hilfe");
		
		//write instruction to label as HTML
		JLabel label = new JLabel("<html>");
		label.setText(label.getText() + "Ziel ist es, alles auf zu essen und nicht von den Geistern gefangen zu werden.<br><br>");
		label.setText(label.getText() + "Bewegungen:<br>Hoch: W oder Pfeiltaste nach oben");
		label.setText(label.getText() + "<br>Links: A oder Pfeiltaste nach links");
		label.setText(label.getText() + "<br>Runter: S oder Pfeiltaste nach unten");
		label.setText(label.getText() + "<br>Rechts: D oder Pfeiltaste nach rechts");
		label.setText(label.getText() + "<br><br>Q beendet das Programm");
		label.setText(label.getText() + "</html>");
		
		//add the label to the help frame
		helpFrame.add(label);
		//disable resizing
		helpFrame.setResizable(false);
		//set the desktop z-axis from help frame to front
		helpFrame.setAlwaysOnTop(true);
		//show the frame
		helpFrame.setVisible(true);  
		//set the frame
		helpFrame.setSize(THREEHUNDRED, ONEHUNDREDNINETY);
		//center the frame
		helpFrame.setLocationRelativeTo(null);	
	}
	
	//actionPerformed for GUi menu items
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(menuFileHelp)) {
            showHelpFrame();
            return;
        } 
        
        if (source.equals(menuFileExit)) {
        	exitProgram(0);
            return;
        }
    }
}
