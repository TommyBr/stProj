package pacman;
public final class Ghost {
	//constant variables for constant numbers > 2
	static final int THREE = 3, FOUR = 4, THOUSAND = 1000;
	private int pos, directory;

	public Ghost() {
		//default directory - none
		directory = -1;
	}
	
	int getDirectory() {
		return directory;
	}
	
	void setDirectory(int dir) {
		directory = dir;
	}
	
	void chooseDirection() {
		int d[] = new int[FOUR];
		int possibleDirectorys = 0;
		
		//move up
		if (TUI.moveIsAllowed(pos, pos - Grid.getWidth())) {
			d[possibleDirectorys++] = 0;
		}
		
		//move left
		if (TUI.moveIsAllowed(pos, pos - 1)) {
			d[possibleDirectorys++] = 1;
		}
		
		//move down
		if (TUI.moveIsAllowed(pos, pos + Grid.getWidth())) {
			d[possibleDirectorys++] = 2;
		}
		
		//move right
		if (TUI.moveIsAllowed(pos, pos + 1)) {
			d[possibleDirectorys++] = THREE;
		}
		
		//choose a random directory
		int r = (int)(Math.random() * THOUSAND);
		int newDirectory = d[r % possibleDirectorys];
		
		//new direction is opposite direction from the old? try to prefer an another
		if (directory != newDirectory && directory % 2 == newDirectory % 2 && possibleDirectorys > 1) {
			newDirectory = d[(r + 1) % possibleDirectorys];
		}
		
		//update the global directory
		directory = newDirectory;	
	}
	
	void move() {
		//choose a random directory
		chooseDirection();
		
		//delete the old ghost position from the grid
		Grid.getGridValue().setGhost(pos, false);
		
		switch (directory) {
		case 0:
			//move up
			pos -= Grid.getWidth();
			break;
		case 1:
			//move left
			pos--;
			break;
		case 2:
			//move down
			pos += Grid.getWidth();
			break;
		case THREE:
			//move right
			pos++;
			break;
			
		default:
			break;
		}
		
		//set the new ghost position to the grid
		Grid.getGridValue().setGhost(pos, true);
	}
	
	//returns the position of the ghost
	public int getPos() {
		return pos;
	}
	
	//sets the position of the ghost
	public void setPos(int newPos) {
		pos = newPos;
	}

}
