import java.util.Scanner;

public class TUI {
	//constant variables for constant numbers > 2
	static final int GRIDWIDTH = 10, GRIDHEIGHT = 6;
	Scanner scanner;
	static Grid g;
	
	public TUI() {
		System.out.println("PacMan gestartet");
		g = new Grid();
		g.initGrid(GRIDWIDTH, GRIDHEIGHT);
		g.drawGrid();
		printInstructions();
	}
	
	static boolean moveIsAllowed(int from, int to) {	
		//make sure to move within the limit of the grid
		if (from < 0 || to < 0 || from >= g.getHeight() * g.getWidth()
				|| to >= g.getHeight() * g.getWidth()) return false;
		
		//is the destination field a wall?
		if (g.isWall(to)) return false;
		
		//left Wand vom Grid erreicht?
		if (to == from - 1 && from % g.getWidth() == 0) return false;
		
		//right border from the grid eached?
		if (to - 1 == from && from % g.getWidth() == g.getWidth() - 1) return false;
				
		//top border from the grid eached?
		if (to < 0) {
			return false;
		}
				
		//bottom border from the grid eached?
		if (to >= g.getHeight() * g.getWidth()) {
			return false;
		}
				
		return true;
	}
	
	public int run() {
		//read the ASCII input from the console
		scanner = new Scanner(System.in);
		String s = scanner.next();
		
		if (s.charAt(0) == 'q') {
			System.out.println("quit");
			return 1;
		}
		
		//move left
		if (s.charAt(0) == 'a') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - 1)) return -1;
			g.setPlayer(g.getPlayer() - 1);
			g.drawGrid();
			printInstructions();
			return 0;
		}
		
		//move right
		if (s.charAt(0) == 'd') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + 1)) return -1;
			g.setPlayer(g.getPlayer() + 1);
			g.drawGrid();
			printInstructions();
			return 0;
		}

		//move up
		if (s.charAt(0) == 'w') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - g.getWidth())) return -1;
			g.setPlayer(g.getPlayer() - g.getWidth());
			g.drawGrid();
			printInstructions();
			return 0;
		}

		//move down
		if (s.charAt(0) == 's') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + g.getWidth())) return -1;
			g.setPlayer(g.getPlayer() + g.getWidth());
			g.drawGrid();
			printInstructions();
			return 0;
		}
		
		return 0;
	}
	
	public void printInstructions() {
		/*
		 	Instruction for the user to play the game.
		 	The following lines will be printed after every move done by the user.
		*/
		System.out.println("Gegessen: " + Grid.eaten);
		System.out.println("P = PacMan, G = Geist, x = Wand");
		System.out.println("Befehle: q = quit, Bewegen: w = hoch, a = links, s = runter, d = rechts");
	}
}
