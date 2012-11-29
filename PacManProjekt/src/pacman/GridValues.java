package pacman;
public final class GridValues {
	private boolean[] ghost;
	private boolean[] player;
	private boolean[] isWall;
	private boolean[] food;
	
	protected GridValues() { }
	
	void create(int count) {
		ghost = new boolean[count];
		player = new boolean[count];
		isWall = new boolean[count];
		food = new boolean[count];
		
		for (int i = 0; i < count; i++) {
			ghost[i] = false;
			player[i] = false;
			isWall[i] = false;
			//at the beginning, all places are food
			food[i] = true;
		}
	}
	
	boolean fieldIsEmpty(int idx) {
		return (!ghost[idx] && !player[idx] && !isWall[idx]);
	}
	
	boolean isGhost(int idx) {
		return ghost[idx];
	}	
	
	boolean isPlayer(int idx) {
		return player[idx];
	}	
	
	boolean isWall(int idx) {
		return isWall[idx];
	}
	
	boolean isFood(int idx) {
		return food[idx];
	}
	
	void setGhost(int idx, boolean bool) {
		ghost[idx] = bool;
	}	
	
	public void setPlayer(int idx, boolean bool) {
		player[idx] = bool;
	}	
	
	void setWall(int idx, boolean bool) {
		isWall[idx] = bool;
	}	
	
	void setFood(int idx, boolean bool) {
		food[idx] = bool;
	}
	
	
}