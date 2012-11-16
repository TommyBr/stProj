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
	
	public int run() {
		String s = scanner.next();
		
		if (s.charAt(0) == 'q') {
			System.out.println("quit");
			return 1;
		}
		
		if (s.charAt(0) == 'l') {
			g.setPlayer(g.getPlayer() - 1);
			g.drawGrid();
			printInstructions();
			return 0;
		}
		
		return 0;
	}
	
	public void printInstructions() {
		System.out.println("P = PacMan, G = Geist");
		System.out.println("Befehle: q = quit, Bewegen: w = hoch, a = links, s = runter, d = rechts");
	}
}
