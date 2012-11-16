public class ghost {
	static int Pos, Directory;

	public ghost() {
		//default directory - none
		Directory = -1;
	}
	
	static void chooseDirection() {
		int d[] = new int[4];
		int possible_directorys = 0;
		
		if (TUI.moveIsAllowed(Pos, Pos - grid.getWidth())) {
			d[possible_directorys++] = 0;	//up
		}
		
		if (TUI.moveIsAllowed(Pos, Pos - 1)) {
			d[possible_directorys++] = 1;	//left
		}
		
		if (TUI.moveIsAllowed(Pos, Pos + grid.getWidth())) {
			d[possible_directorys++] = 2;	//down
		}
		
		if (TUI.moveIsAllowed(Pos, Pos + 1)) {
			d[possible_directorys++] = 3;	//right
		}
		
		//choose a random directory
		int r = (int)(Math.random() * 1000);
		int newDirectory = d[r % possible_directorys];
		
		//new direction is opposite direction from the old? try to prefer an another
		if (Directory != newDirectory && Directory % 2 == newDirectory % 2 && possible_directorys > 1) {
			newDirectory = d[(r + 1) % possible_directorys];
		}
		
		//update the global directory
		Directory = newDirectory;	
	}
	
	static void move() {
		//choose a random directory
		chooseDirection();
		
		//delete the old ghost position from the grid
		if (grid.gv[Pos] != null) grid.gv[Pos].ghost = false;
		
		switch (Directory) {
		case 0:	//up
			Pos -= grid.getWidth();
			break;
		case 1:	//left
			Pos--;
			break;
		case 2:	//down
			Pos += grid.getWidth();
			break;
		case 3:	//right
			Pos++;
			break;
		default:
			break;
		}
		
		//set the new ghost position to the grid
		if (grid.gv[Pos] == null) grid.gv[Pos] = new gridValues(); 
		grid.gv[Pos].ghost = true;
	}
	
	//returns the position of the ghost
	public static int getPos() {
		return Pos;
	}
	
	//sets the position of the ghost
	public static void setPos(int pos) {
		Pos = pos;
	}

}
