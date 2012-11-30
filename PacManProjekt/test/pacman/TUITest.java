package pacman;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TUITest {
	//constant variables for constant numbers > 2
	static final int GRIDWIDTH = 10, GRIDHEIGHT = 6;
	private static Grid g;
	private static TUI tui;

	@Before
	public void setUp() throws Exception {
		tui = new TUI();
		g = new Grid();
		g.initGrid(GRIDWIDTH, GRIDHEIGHT);
	}

	@After
	public void tearDown() throws Exception {
		tui = null;
	}

	@Test
	public final void testMoveIsAllowed() {
		assertFalse(tui.moveIsAllowed(-1, -2));
		assertFalse(tui.moveIsAllowed(0, -2));
		assertFalse(tui.moveIsAllowed(GRIDWIDTH * GRIDHEIGHT, 0));
		assertFalse(tui.moveIsAllowed(GRIDWIDTH, GRIDWIDTH - 1));
	}

	@Test
	public final void testCheckSetAndDraw() {
		g.setPlayer(1);
		assertSame(tui.checkSetAndDraw(1), 0);
		g.setPlayer(tui.getGhost());
		assertSame(tui.checkSetAndDraw(1), 0);
	}

	@Test
	public final void testRun() {
		assertSame(tui.run("q"), 1);
		g.setPlayer(0);
		assertTrue(tui.run("a") == -1);
		assertTrue(tui.run("w") == -1);
		assertTrue(tui.run("d") != -1);
		g.setPlayer(1);
		assertTrue(tui.run("a") != -1);
		g.setPlayer(GRIDWIDTH);
		assertTrue(tui.run("w") != -1);
		g.setPlayer(GRIDWIDTH - 1);
		assertTrue(tui.run("d") == -1);
		g.setPlayer(0);
		assertTrue(tui.run("s") != -1);
		g.setPlayer(GRIDWIDTH * GRIDHEIGHT - 1);
		assertTrue(tui.run("s") == -1);
		assertTrue(tui.run("x") == 0);
	}

	@Test
	public final void testPrintInstructions() {

	}

	@Test
	public final void testPrintStatistic() {
		
	}

}
