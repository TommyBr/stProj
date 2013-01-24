package pacman;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridValuesTest {

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
		
		gv.setWall(0, false);
		assertFalse(gv.isWall(0));
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

}
