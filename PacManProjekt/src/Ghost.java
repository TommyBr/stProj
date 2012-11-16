public class Ghost {
	//constant variables for constant numbers > 2
	static final int three = 3, four = 4, thousand = 1000;
	static int pos, directory;

	public Ghost() {
		//default directory - none
		directory = -1;
	}
	
	static void chooseDirection() {
		int d[] = new int[four];
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
			d[possibleDirectorys++] = three;
		}
		
		//choose a random directory
		int r = (int)(Math.random() * thousand);
		int newDirectory = d[r % possibleDirectorys];
		
		//new direction is opposite direction from the old? try to prefer an another
		if (directory != newDirectory && directory % 2 == newDirectory % 2 && possibleDirectorys > 1) {
			newDirectory = d[(r + 1) % possibleDirectorys];
		}
		
		//update the global directory
		directory = newDirectory;	
	}
	
	static void move() {
		//choose a random directory
		chooseDirection();
		
		//delete the old ghost position from the grid
		if (Grid.gv[pos] != null) Grid.gv[pos].ghost = false;
		
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
		case three:
			//move right
			pos++;
			break;
			
		default:
			break;
		}
		
		//set the new ghost position to the grid
		if (Grid.gv[pos] == null) Grid.gv[pos] = new GridValues(); 
		Grid.gv[pos].ghost = true;
	}
	
	//returns the position of the ghost
	public static int getPos() {
		return pos;
	}
	
	//sets the position of the ghost
	public static void setPos(int newPos) {
		pos = newPos;
	}

}
