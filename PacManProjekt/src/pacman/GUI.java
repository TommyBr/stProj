package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
	  
public class GUI extends JPanel implements ActionListener {
	private static final int FOUR = 4, EIGHT = 8, FORTY = 40, THREEHUNDRED = 300, TENTHOUSAND = 10000;
	int BOXSIZE = FORTY, width = TENTHOUSAND, height = TENTHOUSAND, foodRadius = BOXSIZE / EIGHT;
	//up, down, left, right
	private final int[] MOUTHDIRECTIONS = {120, 300, 210, 30};
	private final Color WALLCOLOR = Color.BLUE, BACKGROUNDCOLOR = Color.black,
				  FOODCOLOR = Color.WHITE, PLAYERCOLOR = Color.YELLOW;
	
	private JFrame frame;
	private JMenuItem menu_file_help;
	private JMenuItem menu_file_exit;
	
	
	public GUI() {
		
	}
	
	protected void update() {
		frame.repaint();
	}
	
	private void drawWallRect(Graphics g, int idx) {
		g.setColor(WALLCOLOR);
		g.fillRect((idx % TUI.g.getWidth()) * BOXSIZE, (idx / TUI.g.getWidth()) * BOXSIZE, BOXSIZE - 1, BOXSIZE - 1);
	}
	
	private void drawFood(Graphics g, int idx) {
		g.setColor(FOODCOLOR);
		g.fillOval((idx % TUI.g.getWidth()) * BOXSIZE + BOXSIZE / 2 - foodRadius, 
				(idx / TUI.g.getWidth()) * BOXSIZE + BOXSIZE / 2 - foodRadius, foodRadius * 2, foodRadius * 2);
	}
	
	private void drawPlayer(Graphics g, int idx) {
		g.setColor(PLAYERCOLOR);
		g.fillArc((idx % TUI.g.getWidth()) * BOXSIZE + 2, idx / TUI.g.getWidth() * BOXSIZE + 2,
		BOXSIZE - FOUR, BOXSIZE - FOUR, MOUTHDIRECTIONS[TUI.getDirection()], THREEHUNDRED);
	}
	
	protected void drawGame(Graphics g) {
		for (int i = 0; i < TUI.g.getWidth() * TUI.g.getHeight(); i++) {
			if (TUI.g.isWall(i)) {
				drawWallRect(g, i);
			} else if (TUI.g.isFood(i)) {
				drawFood(g, i);
			} else if (TUI.g.isPlayer(i)) {
				drawPlayer(g, i);
			}
		}		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(BACKGROUNDCOLOR);
		g.fillRect(0, 0, width * BOXSIZE, height * BOXSIZE);	
		drawGame(g);
	}
	
	private void checkPressedKey(KeyEvent e) {
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
		
        menu_file_help = new JMenuItem("Hilfe");
        menu_file_help.addActionListener(this);
        menu_file_exit = new JMenuItem("Beenden");
        menu_file_exit.addActionListener(this);
        
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
        file.add(menu_file_help);
        file.addSeparator();
        file.add(menu_file_exit);      

        frame.getContentPane().add(menu, BorderLayout.PAGE_START);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.add(new GUI());
        frame.setVisible(true);   
        
        frame.setSize(frame.getInsets().left + frame.getInsets().right  + newwidth * BOXSIZE,
        		frame.getInsets().top +  frame.getInsets().bottom + newheight * BOXSIZE + menu.getHeight());
        frame.setLocationRelativeTo(null);
	}
	
	protected void exitProgramm(int ret) {
		System.exit(ret);
	}
	
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(menu_file_help)) {
            //ToDo
            return;
        } 
        
        if (source.equals(menu_file_exit)) {
        	exitProgramm(0);
            return;
        }
    }
}
