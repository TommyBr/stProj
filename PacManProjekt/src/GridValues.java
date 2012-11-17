public class GridValues {
	private static boolean[] ghost;
	private static boolean[] player;
	private static boolean[] isWall;
	private static boolean[] food;
	
	private GridValues() { }
	
	static void create(int count) {
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
	
	static boolean fieldIsEmpty(int idx) {
		return (ghost[idx] == player[idx] == isWall[idx] == false);
	}
	
	static boolean isGhost(int idx) {
		return ghost[idx];
	}	
	
	static boolean isPlayer(int idx) {
		return player[idx];
	}	
	
	static boolean isWall(int idx) {
		return isWall[idx];
	}
	
	static boolean isFood(int idx) {
		return food[idx];
	}
	
	static void setGhost(int idx, boolean bool) {
		ghost[idx] = bool;
	}	
	
	public static void setPlayer(int idx, boolean bool) {
		player[idx] = bool;
	}	
	
	static void setWall(int idx, boolean bool) {
		isWall[idx] = bool;
	}	
	
	static void setFood(int idx, boolean bool) {
		food[idx] = bool;
	}
	
	
}