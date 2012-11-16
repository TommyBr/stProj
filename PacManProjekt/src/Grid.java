public class Grid {
	//constant variables for constant numbers > 2
	static final int THREE = 3, THOUSAND = 1000;
	private static int width, height, player;
	private static GridValues gv[];
	private static Ghost ghost;
	//number of eaten food (clear fields)
	private static int eaten;
	
	public Grid() {
		//creating a new ghost
		ghost = new Ghost();
		//nothing eaten at the beginning
		eaten = 0;
	}
	
	static GridValues getGridValue(int idx) {
		return gv[idx];
	}
	
	static void createGridValue(int idx) {
		gv[idx] = new GridValues();
	}
	static int getEaten() {
		return eaten;
	}
	
	void placeWall(int idx) {
		//create a new GridValue for the wall
		if (gv[idx] == null) { gv[idx] = new GridValues(); }
		//mark this field as a wall
		gv[idx].isWall = true;	
		//you can't eat a wall
		gv[idx].food = false;	
	}
	
	private void createLabyrinth() {
		for (int i = 1; i < width - 1; i++) {
			placeWall(i + width);
		}
		
		for (int i = 2; i < height - 1; i++) {
			placeWall(i * width + 1);
		}
		
		for (int j = THREE; j < height; j+=2) {
			for (int i = THREE; i < width - 1; i++) {
				placeWall(i + width * j);
			}
		}
	}
	
	int getRandomField() {
		return (int)((Math.random() * width * height * THOUSAND) % (width * height));
	}
	
	public int initGrid(int newWidth, int newHeight) {
		width = newWidth;
		height = newHeight;
		//creating a new grid
		gv = new GridValues[width * height]; 
		//and draw a labyrinth, adjusted to the dimensions
		createLabyrinth();
		
		//searching for a random field that is not in use
		int r = getRandomField();
		while (gv[r] != null) { r = getRandomField(); }
		//create a new GridValue for this
		gv[r] = new GridValues();
		//mark it as non-food
		gv[r].food = false;
		//mark it as player
		gv[r].player = true;
		//update the player position
		player = r;
		
		//searching for an another random field that is not in use
		r = getRandomField();
		while (gv[r] != null) { r = getRandomField(); }
		//create a new GridValue for this
		gv[r] = new GridValues();
		//mark it as ghost
		gv[r].ghost = true;
		//update the ghost position
		Ghost.setPos(r);
		
		return 0;
	}
	
	//returns the with of the grid
	static int getWidth() {
		return width;
	}
	
	//returns the height of the grid
	static int getHeight() {
		return height;
	}
	
	//returns true if the field "idx" is a wall
	public boolean isWall(int idx) {
		if (gv[idx] != null && gv[idx].isWall) { return true; }
		return false;
	}
	
	public void setPlayer(int pos) {
		//make sure the player moving inside the grid
		if (pos < 0 || pos > width * height) { return; }
		
		//disable the old grid position of the player
		gv[player].player = false;
		//update the player position
		player = pos;
		//creating a new field if it is null
		if (gv[player] == null) { gv[player] = new GridValues(); }
		//enable the new grid position of the player
		gv[player].player = true;
		
		//counting the eaten food and mark the field as no food
		if (gv[player].food) {
			eaten++;
			gv[player].food = false;
		}
		
		//if the player has moved, the ghost should move
		Ghost.move();
	}
	
	//returns the position of the player
	public int getPlayer() {
		return player;
	}
	
	//"drawing" the grid on the console
	public int drawGrid() {
		//number of the current field that will be draw
		int fieldNr = 0;
		
		for (int i = 0; i <= height * 2; i++) {
			//left wall in each line of the grid
			TUI.print("|");
			if (i % 2 == 0) {
				//printing the top / bottom border of the fields
				for (int j = 0; j < width; j++) {
					if (j < width - 1) { TUI.print("----"); }
					else { TUI.print("---"); }
				}	
			} else {
				//printing the fields with her content
				for (int j = 0; j < width; j++) {
					//if the field is != null, it can be everything possible
					if (gv[fieldNr] != null) {
						//player
						if (gv[fieldNr].player) { TUI.print(" P "); }
						//ghost
						else if (gv[fieldNr].ghost) { TUI.print(" G "); }
						//wall
						else if (gv[fieldNr].isWall) { TUI.print(" x "); }
						//food
						else if (gv[fieldNr].food) { TUI.print(" . "); }		
						//nothing
						else { TUI.print("   "); }
					} else {
						//if it is null, it can only be food
						TUI.print(" . ");
					}
					
					if (j < width - 1) { TUI.print("|"); }
					//increase the field number and do the same for the next field
					fieldNr++;
				}	
			}
			//right wall in each line of the grid
			TUI.println("|");
		}
		
		return 0;
	}

}
