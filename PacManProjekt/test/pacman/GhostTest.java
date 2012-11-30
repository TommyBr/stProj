package pacman;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GhostTest {
	Ghost g;
	TUI tui;

	@Before
	public void setUp() throws Exception {
		g = new Ghost();
		tui = new TUI();
	}
	
	@After
	public void tearDwon() throws Exception {
		g = null;
		tui = null;
	}

	@Test
	public final void testChooseDirection() {
		int dir = g.getDirectory();
		g.setDirectory((dir + 1) % 4);
		assertNotSame(g.getDirectory(), dir);
	}

	@Test
	public final void testMove() {
		int pos = g.getPos();
		
		g.move();
		assertNotSame(g.getPos(), pos);
	}

	@Test
	public final void testSetAndGetPos() {
		g.setPos(2);
		assertEquals(g.getPos(), 2);
	}

}
