public class GridValues {
	Boolean ghost;
	Boolean player;
	Boolean isWall;
	Boolean food;
	
	public GridValues() {
		ghost = false;
		player = false;
		isWall = false;
		//at the beginning, all places are food
		food = true;
	}
}