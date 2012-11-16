public class pacman {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		//creating new TUI
		TUI tui = new TUI();
		//running this TUI until the user stop it 
		while (tui.run() != 1) {}
	}

}
