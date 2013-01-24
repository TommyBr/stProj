package pacman;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GUITest {
	GUI gui;
	
	@Before
	public void setUp() throws Exception {
		gui = new GUI();
		gui.create(10, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		gui.showHelpFrame();
		gui.setFrameTitel("test");
		gui.update();
	}

}
