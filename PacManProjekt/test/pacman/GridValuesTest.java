package pacman;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GridValuesTest extends TestCase {

	GridValues gv;

	@Before
	public void setUp() throws Exception {
		gv = new GridValues();
		assertNotNull(gv);
		gv.create(2);	
	}

	@After
	public void tearDown() throws Exception {
		gv = null;
	}

	@Test
	public final void testFieldIsEmpty() {
		gv.setGhost(0, false);
		assertFalse(gv.isGhost(0));
		
		gv.setFood(0, false);
		assertFalse(gv.isFood(0));
	}

	@Test
	public final void testIsGhost() {
		gv.setGhost(0, false);
		assertFalse(gv.isGhost(0));
	}

	@Test
	public final void testIsPlayer() {
		gv.setPlayer(0, false);
		assertFalse(gv.isPlayer(0));
	}
/*
	@Test
	public final void testIsWall() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsFood() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetGhost() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetPlayer() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetWall() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetFood() {
		fail("Not yet implemented"); // TODO
	}
*/
}
