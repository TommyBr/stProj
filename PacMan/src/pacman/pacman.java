package pacman;

import pacman.*;
import pacman.tui.TUI;
import static org.junit.Assert.*;

public class pacman {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int retVal = initGame();
		
		if (retVal == 0) {
			System.out.println("game initialized successfully");
		} else {
			System.out.println("game initialized unsuccessfully");
		}
		
		TUI tui = new TUI();
		while (tui.run() != 1) {}
	}

	private static int initGame() {
		int retVal = grid.initGrid(10, 6);
		
		if (retVal == 0) {
			System.out.println("grid initialized with size: " + grid.getWidth() + " x " + grid.getHeight());
		} else {
			System.out.println("error while initialize grid with " + grid.getWidth() + " x " + grid.getHeight());
			return 1;
		}
		
		//ToDo: set player and ghosts

		return 0;
	}
}
