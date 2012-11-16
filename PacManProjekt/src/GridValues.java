public class GridValues {
	public Boolean ghost;
	public Boolean player;
	public Boolean isWall;
	public Boolean food;
	
	public GridValues() {
		ghost = false;
		player = false;
		isWall = false;
		//at the beginning, all places are food
		food = true;
	}
}