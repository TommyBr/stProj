package pacman;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridValuesTest {
	
	private boolean[] ghost;
	private boolean[] player;
	private boolean[] isWall;
	private boolean[] food;
	
	GridValues gv;
	
	@Before
	public void setUp() throws Exception {
		ghost[0] = false;
		player[0] = false;
		isWall[0] = false;
		food[0] = false;
		
		gv = new GridValues();
		gv.create(1);
		gv.setFood(0, false);
		gv.setGhost(0, false);
		gv.setPlayer(0, false);
		gv.setWall(0, false);
	}

	@After
	public void tearDown() throws Exception {
		ghost = null;
		player = null;
		isWall = null;
		food = null;
	}
	
	public void testfieldIsEmpty() {
		assertFalse(gv.isGhost(0));
	}


}
