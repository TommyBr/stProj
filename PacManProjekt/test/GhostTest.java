import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;


public class GhostTest {
	static final int THREE = 3, FOUR = 4, THOUSAND = 1000;
	private static int pos, directory;
	
	@Before
	public void setUp() throws Exception {
		directory = -1;
	}

	@Test
	public final void testChooseDirection() {
		assertTrue(directory >= 0 && directory < 4);
	}

	@Test
	public final void testMove() {
		assertTrue(directory == -1);
		testChooseDirection();
		assertTrue(directory != -1);
	}

	@Test
	public final void testGetPos() {
		assertTrue(pos >= 0);
	}

	@Test
	public final void testSetPos() {
		assertTrue(pos >= 0);
	}

}
