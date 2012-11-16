public class PacMan {

	public static void main(String[] args) {
		//creating new TUI
		TUI tui = new TUI();
		//print instruction
		TUI.printInstructions();
		//running this TUI until the user stop it 
		while (tui.run() != 1) {}
	}

}
