package pacman;
public final class PacMan {

	private PacMan() { }
	
	public static void main(String[] args) {
		//creating new TUI
		TUI tui = new TUI();
		//print instruction
		TUI.printInstructions();
		//running this TUI until the user stop it 
		while (true) {
			if (tui.run() == 1) { break; }
		}
	}

	
}
