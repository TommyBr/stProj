package pacman;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TUITest {
	static final int GRIDWIDTH = 10, GRIDHEIGHT = 6;
	TUI tui;
	Grid g;
	
	@Before
	public void setUp() throws Exception {
		tui = new TUI();
		g = new Grid();
		g.initGrid(GRIDWIDTH, GRIDHEIGHT);
	}

	@After
	public void tearDown() throws Exception {
		tui = null;
		g = null;
	}

	@Test
	public final void testCheckSetAndDraw() {
		g.setPlayer(1);
		assertSame(g.getPlayer(), 1);
	}

	@Test
	public final void testRun() {
		Scanner scanner = new Scanner(System.in);
		assertNotNull(scanner);
	}

}
