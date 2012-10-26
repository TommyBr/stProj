package pacman.tui;
import java.util.Scanner;

import pacman.grid;

public class TUI {

	Scanner scanner;
	
	public TUI() {
		grid.drawGrid();
		scanner = new Scanner(System.in);
		printInstructions();
	}
	
	public int run() {
		String s = scanner.next();
		
		if (s.charAt(0) == 'q') {
			System.out.println("exit");
			return 1;
		}
		
		return 0;
	}
	
	public void printInstructions() {
		System.out.println("Befehle: q = quit");
	}
}
