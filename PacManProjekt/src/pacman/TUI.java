package pacman;
import java.io.PrintStream;
import java.util.Scanner;

class TUI {
	//constant variables for the grid size
	static final int GRIDWIDTH = 24, GRIDHEIGHT = 14;
	//and constant numbers > 2
	static final int THREE = 3, SIXTY = 60, NUMBEROFGHOSTS = GRIDWIDTH * GRIDHEIGHT / SIXTY + 1;
	//and other global variables
	private static boolean usegui = true, run = true;
	private static Grid g;
	private static GUI gui;
	private static int direction = 2;
	
	protected TUI() {
		println("PacMan gestartet");
		g = new Grid();
		//init the grid and calc and set number of ghosts
		g.initGrid(GRIDWIDTH, GRIDHEIGHT, NUMBEROFGHOSTS);
		g.drawGrid();
		
		//create gui
		if (usegui) {
			gui = new GUI();
			gui.create(GRIDWIDTH, GRIDHEIGHT);
		}
	}
	
	//returns false if game done
	void setRun(boolean runstatus) {
		run = runstatus;
	}
	
	//returns the last direction
	public static int getDirection() {
		return direction;
	}
	
	//allow access private grid
	protected static Grid getGrid() {
		return g;
	}
	
	static boolean moveIsAllowed(int from, int to) {	
		//make sure to move within the limit of the grid
		if (from < 0 || to < 0 
				|| from >= g.getHeight() * g.getWidth()
				|| to >= g.getHeight() * g.getWidth()) { return false; }
		
		//is the destination field a wall?
		if (g.isWall(to)) { return false; }
		
		//left Wand vom Grid erreicht?
		if (to == from - 1 && from % g.getWidth() == 0) { return false; }
		
		//right border from the grid eached?
		if (to - 1 == from && from % g.getWidth() == g.getWidth() - 1) { return false; }
				
		return true;
	}
	
	static int checkSetAndDraw(int playerpos) {
		//set the player position
		g.setPlayer(playerpos);
		//draw the grid
		g.drawGrid();
		if (usegui) {
			gui.update();
		}
		
		//if the game is over...
		int gamestatus = g.gameStatus();
		if (gamestatus != 0) {
			run = false;
			
			//game over
			if (gamestatus < 0) {
				gui.setFrameTitel("PacMan      Du Wurdest vom Geist gefangen! Spielende.");
			} else {
				//player win
				gui.setFrameTitel("PacMan      Du hast es geschaft alles in " + g.getMovements() + " Zügen aufzuessen! Spielende.");
			}
			
			return 0;
		}
		//print instructions if game isn't over
		printInstructions();
		return 0;
	}
	
	static int checkAndMove(String s) {
		//move left
		if (s.charAt(0) == 'a') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - 1)) { return -1; }
			//update the mouth position for PacMan in GUI
			direction = 2;
			return checkSetAndDraw(g.getPlayer() - 1);
		}
		
		//move right
		if (s.charAt(0) == 'd') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + 1)) { return -1; }
			//update the mouth position for PacMan in GUI
			direction = THREE;
			return checkSetAndDraw(g.getPlayer() + 1);
		}

		//move up
		if (s.charAt(0) == 'w') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - g.getWidth())) { return -1; }
			//update the mouth position for PacMan in GUI
			direction = 0;
			return checkSetAndDraw(g.getPlayer() - g.getWidth());
		}

		//move down
		if (s.charAt(0) == 's') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + g.getWidth())) { return -1; }
			//update the mouth position for PacMan in GUI
			direction = 1;
			return checkSetAndDraw(g.getPlayer() + g.getWidth());
		}	
		
		return 0;
	}
	
	
	public static int run(String s) {
		//if q was pressed, tui and gui exits
		if (s.charAt(0) == 'q') {
			println("quit");
			if (usegui) {
				gui.exitProgram(0);
			}
			return 1;
		}	
		if (!run) { return 0; }
		return checkAndMove(s);
	}
		
	public static int run() {
		//read the ASCII input from the console
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		return run(s);
	}
	
	static void printInstructions() {
		/*
		 	Instruction for the user to play the game.
		 	The following lines will printed after every move done by the user.
		*/
		printStatistic();
		println("P = PacMan, G = Geist, x = Wand");
		println("Befehle: q = quit, Bewegen: w = hoch, a = links, s = runter, d = rechts");
	}
	
	
	static void printStatistic() {
		println("Gegessen: " + g.getEaten() + "\t\tVerbleibend: " + g.getFoodLeft() + "\t\tZüge: " + g.getMovements());
		gui.setFrameTitel("PacMan      " + "Gegessen: " + g.getEaten() + "   Verbleibend: " + g.getFoodLeft() + "   Züge: " + g.getMovements());
		
	}
	
	//System.out.print replacement to prevent violations
	static void print(String s) {
		PrintStream out = System.out;
		out.print(s);
	}
	
	//System.out.println replacement to prevent violations
	static void println(String s) {
		print(s + "\n");
	}
}
