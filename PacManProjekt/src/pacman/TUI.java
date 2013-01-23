package pacman;
import java.io.PrintStream;
import java.util.Scanner;

public class TUI {
	//constant variables for constant numbers > 2
	static final int GRIDWIDTH = 8, GRIDHEIGHT = 5, GHOSTS = 2;
	private static boolean USEGUI = true;
	protected static Grid g;
	private static GUI gui;
	private static int direction = 2;
	
	public TUI() {
		println("PacMan gestartet");
		g = new Grid();
		g.initGrid(GRIDWIDTH, GRIDHEIGHT, GHOSTS);
		g.drawGrid();
		
		if (USEGUI) {
			gui = new GUI();
			gui.create(GRIDWIDTH, GRIDHEIGHT);
		}
	}
	
	public static int getDirection() {
		return direction;
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
		if (USEGUI) {
			gui.update();
		}
		//if the game is over...
		if (g.gameStatus() != 0) {
			if (USEGUI) {
				//exit GUI
				gui.exitProgramm(0);
			}
			//exit TUI
			return 1;
		}
		//print instructions if game isn't over
		printInstructions();
		return 0;
	}
	
	
	public static int run(String s) {
		if (s.charAt(0) == 'q') {
			println("quit");
			if (USEGUI) {
				gui.exitProgramm(0);
			}
			return 1;
		}
		
		//move left
		if (s.charAt(0) == 'a') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - 1)) { return -1; }
			direction = 2;
			return checkSetAndDraw(g.getPlayer() - 1);
		}
		
		//move right
		if (s.charAt(0) == 'd') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + 1)) { return -1; }
			direction = 3;
			return checkSetAndDraw(g.getPlayer() + 1);
		}

		//move up
		if (s.charAt(0) == 'w') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - g.getWidth())) { return -1; }
			direction = 0;
			return checkSetAndDraw(g.getPlayer() - g.getWidth());
		}

		//move down
		if (s.charAt(0) == 's') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + g.getWidth())) { return -1; }
			direction = 1;
			return checkSetAndDraw(g.getPlayer() + g.getWidth());
		}	
		
		return 0;
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
		 	The following lines will be printed after every move done by the user.
		*/
		printStatistic();
		println("P = PacMan, G = Geist, x = Wand");
		println("Befehle: q = quit, Bewegen: w = hoch, a = links, s = runter, d = rechts");
	}
	
	static void printStatistic() {
		println("Gegessen: " + g.getEaten() + "\t\tVerbleibend: " + g.getFoodLeft() + "\t\tZüge: " + g.getMovements());
	}
	

	static void print(String s) {
		PrintStream out = System.out;
		out.print(s);
	}
	
	static void println(String s) {
		print(s + "\n");
	}
}
