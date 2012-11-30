package pacman;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridTest {
	
	Grid g;
	GridValues gv;

	@Before
	public void setUp() throws Exception {
		g = new Grid();
		gv = new GridValues();
		gv.create(2);
	}

	@After
	public void tearDown() throws Exception {
		g = null;
		gv = null;
	}

	@Test
	public final void testGetGridValue() {
		assertNotSame(g, g.getGridValue());
	}
	
	@Test
	public void testGetEaten() {
		assertSame(g.getEaten(), 0);
	}
	
	@Test
	public void testGetMovements() {
		assertSame(g.getMovements(), 0);
	}
	
	@Test
	public void testPlaceWall() {
		gv.setWall(0, true);
		assertTrue(gv.isWall(0));
	}
	
	@Test
	public void testGetRandomField() {
		assertTrue(g.getRandomField() >= 0);
	}
}
