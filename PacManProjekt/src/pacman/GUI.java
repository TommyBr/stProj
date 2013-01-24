package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
	  
public class GUI extends JPanel implements ActionListener {
	private static final int FOUR = 4, EIGHT = 8, FORTY = 40, THREEHUNDRED = 300, TENTHOUSAND = 10000;
	private int boxsize = FORTY, width = TENTHOUSAND, height = TENTHOUSAND, foodRadius = boxsize / EIGHT;
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
	
	//draw wall to the frame
	private void drawWallRect(Graphics g, int idx) {
		g.setColor(Color.BLUE);
		g.fillRect((idx % TUI.getGrid().getWidth()) * boxsize, (idx / TUI.getGrid().getWidth()) * boxsize, boxsize - 1, boxsize - 1);
	}
	
	//draw food to the frame
	private void drawFood(Graphics g, int idx) {
		g.setColor(Color.WHITE);
		g.fillOval((idx % TUI.getGrid().getWidth()) * boxsize + boxsize / 2 - foodRadius, 
				(idx / TUI.getGrid().getWidth()) * boxsize + boxsize / 2 - foodRadius, foodRadius * 2, foodRadius * 2);
	}
	
	//draw player to the frame
	private void drawPlayer(Graphics g, int idx) {
		g.setColor(Color.YELLOW);
		g.fillArc((idx % TUI.getGrid().getWidth()) * boxsize + 2, idx / TUI.getGrid().getWidth() * boxsize + 2,
		boxsize - FOUR, boxsize - FOUR, mouthDirections[TUI.getDirection()], THREEHUNDRED);
	}
	
	//draw ghost/s to the frame
	private void drawGhost(Graphics g, int idx) {
		//ToDo
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
			} else if (TUI.getGrid().isGhost(i)) {
				drawGhost(g, i);
			}
		}		
	}
	
	//overwriting to draw to the frame  
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, width * boxsize, height * boxsize);	
		drawGame(g);
	}
	
	//checking if a key with a function is pressed
	void checkPressedKey(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			TUI.run("w");
		} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			TUI.run("a");
		} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			TUI.run("s");
		} else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			TUI.run("d");
		}  else if (e.getKeyCode() == KeyEvent.VK_Q) {
			TUI.run("q");
		}	
	}
	
	protected void create(int newwidth, int newheight) {
		width = newwidth;
		height = newheight;
		frame = new JFrame();
		frame.setTitle("PacMan");
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("Datei");
		
        menuFileHelp = new JMenuItem("Hilfe");
        menuFileHelp.addActionListener(this);
        menuFileExit = new JMenuItem("Beenden");
        menuFileExit.addActionListener(this);
        
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
        
        menu.add(file);
        file.add(menuFileHelp);
        file.addSeparator();
        file.add(menuFileExit);      

        frame.getContentPane().add(menu, BorderLayout.PAGE_START);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.add(new GUI());
        frame.setVisible(true);   
        
        frame.setSize(frame.getInsets().left + frame.getInsets().right  + newwidth * boxsize,
        		frame.getInsets().top +  frame.getInsets().bottom + newheight * boxsize + menu.getHeight());
        frame.setLocationRelativeTo(null);
	}
	
	protected void exitProgramm(int ret) {
		System.exit(ret);
	}
	
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(menuFileHelp)) {
            //ToDo
            return;
        } 
        
        if (source.equals(menuFileExit)) {
        	exitProgramm(0);
            return;
        }
    }
}
