import java.util.Scanner;

public class TUI {

	Scanner scanner;
	grid g;
	
	public TUI() {
		System.out.println("PacMan gestartet");
		g = new grid();
		g.initGrid(10, 6);
		g.drawGrid();
		scanner = new Scanner(System.in);
		printInstructions();
	}
	
	boolean moveIsAllowed(int from, int to) {	
		//ausserhalb des Grid?
		if (from < 0 || to < 0 || from >= g.getHeight() * g.getWidth()
				|| to >= g.getHeight() * g.getWidth()) return false;
		
		//Ziel eine Wand?
		if (g.isWall(to)) return false;
		
		//linke Wand vom Grid erreicht?
		if (to == from - 1 && g.getPlayer() % g.getWidth() == 0) return false;
		
		//rechte Wand vom Grid erreicht?
		if (to - 1 == from && g.getPlayer() % g.getWidth() == g.getWidth() - 1) return false;
				
		//obere Wand vom Grid erreicht?
		if (to < 0) {
			return false;
		}
				
		//obere Wand vom Grid erreicht?
		if (to >= g.getHeight() * g.getWidth()) {
			return false;
		}
				
		return true;
	}
	
	public int run() {
		String s = scanner.next();
		
		if (s.charAt(0) == 'q') {
			System.out.println("quit");
			return 1;
		}
		
		//lach links bewegen
		if (s.charAt(0) == 'a') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - 1)) return -1;
			g.setPlayer(g.getPlayer() - 1);
			g.drawGrid();
			printInstructions();
			return 0;
		}
		
		//lach rechts bewegen
		if (s.charAt(0) == 'd') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() + 1)) return -1;
			g.setPlayer(g.getPlayer() + 1);
			g.drawGrid();
			printInstructions();
			return 0;
		}

		//lach oben bewegen
		if (s.charAt(0) == 'w') {
			if (!moveIsAllowed(g.getPlayer(), g.getPlayer() - g.getWidth())) return -1;
			g.setPlayer(g.getPlayer() - g.getWidth());
			g.drawGrid();
			printInstructions();
			return 0;
		}

		//lach unten bewegen
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
		System.out.println("P = PacMan, G = Geist, x = Wand");
		System.out.println("Befehle: q = quit, Bewegen: w = hoch, a = links, s = runter, d = rechts");
	}
}
