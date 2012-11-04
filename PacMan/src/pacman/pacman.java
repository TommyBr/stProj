package pacman;

import pacman.*;
import pacman.tui.TUI;
import static org.junit.Assert.*;

public class pacman {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TUI tui = new TUI();
		while (tui.run() != 1) {}
	}

}
