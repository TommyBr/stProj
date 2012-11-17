import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Tommy
 *
 */

public class GridValuesTest {
	private static boolean[] ghost;
	private static boolean[] player;
	private static boolean[] isWall;
	private static boolean[] food;
	
	@Before
	public void setUp() throws Exception {
		int count = 1;
		ghost = new boolean[count];
		player = new boolean[count];
		isWall = new boolean[count];
		food = new boolean[count];
	}
	
	/**
	 * Test method for {@link GridValues#create(int)}.
	 */
	@Test
	public void testCreate() {
		assertNotNull(ghost);
		assertNotNull(player);
		assertNotNull(isWall);
		assertNotNull(food);;
	}

	/**
	 * Test method for {@link GridValues#fieldIsEmpty(int)}.
	 */
	@Test
	public void testFieldIsEmpty() {		ghost[0] = true;
		player[0] = true;
		isWall[0] = true;
		food[0] = true;
		
		assertTrue(ghost[0]);
		assertTrue(player[0]);
		assertTrue(isWall[0]);
		assertTrue(food[0]);;
	}

	/**
	 * Test method for {@link GridValues#isGhost(int)}.
	 */
	@Test
	public void testIsGhost() {
		ghost[0] = false;
		assertFalse(ghost[0]);
	}

	/**
	 * Test method for {@link GridValues#isPlayer(int)}.
	 */
	@Test
	public void testIsPlayer() {
		player[0] = false;
		assertFalse(player[0]);
	}

	/**
	 * Test method for {@link GridValues#isWall(int)}.
	 */
	@Test
	public void testIsWall() {
		isWall[0] = false;
		assertFalse(isWall[0]);
	}

	/**
	 * Test method for {@link GridValues#isFood(int)}.
	 */
	@Test
	public void testIsFood() {
		food[0] = false;
		assertFalse(food[0]);
	}

	/**
	 * Test method for {@link GridValues#setGhost(int, boolean)}.
	 */
	@Test
	public void testSetGhost() {
		ghost[0] = true;
		assertTrue(ghost[0]);
	}

	/**
	 * Test method for {@link GridValues#setPlayer(int, boolean)}.
	 */
	@Test
	public void testSetPlayer() {
		player[0] = true;
		assertTrue(player[0]);
	}

	/**
	 * Test method for {@link GridValues#setWall(int, boolean)}.
	 */
	@Test
	public void testSetWall() {
		isWall[0] = true;
		assertTrue(isWall[0]);
	}

	/**
	 * Test method for {@link GridValues#setFood(int, boolean)}.
	 */
	@Test
	public void testSetFood() {
		food[0] = true;
		assertTrue(food[0]);
	}

}
