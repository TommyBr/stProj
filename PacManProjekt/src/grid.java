public class grid {
	private static int Width, Height, Player;
	public static gridValues gv[];
	private static ghost Ghost;
	//number of eaten food (clear fields)
	private static int eaten;
	
	public grid() {
		//creating a new ghost
		Ghost = new ghost();
		//nothing eaten at the beginning
		eaten = 0;
	}
	
	void placeWall(int idx) {
		//create a new gridValue for the wall
		if (gv[idx] == null) gv[idx] = new gridValues();
		//mark this field as a wall
		gv[idx].isWall = true;	
		//you can't eat a wall
		gv[idx].food = false;	
	}
	
	private void createLabyrinth() {
		for (int i = 1; i < Width - 1; i++) {
			placeWall(i + Width);
		}
		
		for (int i = 2; i < Height - 1; i++) {
			placeWall(i * Width + 1);
		}
		
		for (int j = 3; j < Height; j+=2) {
			for (int i = 3; i < Width - 1; i++) {
				placeWall(i + Width * j);
			}
		}
	}
	
	int getRandomField() {
		return (int)((Math.random() * Width * Height * 1000) % (Width * Height));
	}
	
	public int initGrid(int width, int height) {
		Width = width;
		Height = height;
		//creating a new grid
		gv = new gridValues[width * height]; 
		//and draw a labyrinth, adjusted to the dimensions
		createLabyrinth();
		
		//searching for a random field that is not in use
		int r = getRandomField();
		while (gv[r] != null) { r = getRandomField(); }
		//create a new gridValue for this
		gv[r] = new gridValues();
		//mark it as non-food
		gv[r].food = false;
		//mark it as player
		gv[r].player = true;
		//update the player position
		Player = r;
		
		//searching for an another random field that is not in use
		r = getRandomField();
		while (gv[r] != null) { r = getRandomField(); }
		//create a new gridValue for this
		gv[r] = new gridValues();
		//mark it as ghost
		gv[r].ghost = true;
		//update the ghost position
		Ghost.setPos(r);
		
		return 0;
	}
	
	//returns the with of the grid
	static int getWidth() {
		return Width;
	}
	
	//returns the height of the grid
	static int getHeight() {
		return Height;
	}
	
	//returns true if the field "idx" is a wall
	public boolean isWall(int idx) {
		if (gv[idx] != null && gv[idx].isWall) return true;
		return false;
	}
	
	public void setPlayer(int pos) {
		//make sure the player moving inside the grid
		if (pos < 0 || pos > Width * Height) return;
		
		//disable the old grid position of the player
		gv[Player].player = false;
		//update the player position
		Player = pos;
		//creating a new field if it is null
		if (gv[Player] == null) gv[Player] = new gridValues();
		//enable the new grid position of the player
		gv[Player].player = true;
		
		//counting the eaten food and mark the field as no food
		if (gv[Player].food) {
			eaten++;
			gv[Player].food = false;
		}
		
		//if the player has moved, the ghost should move
		ghost.move();
	}
	
	//returns the position of the player
	public int getPlayer() {
		return Player;
	}
	
	//"drawing" the grid on the console
	public int drawGrid() {
		//number of the current field that will be draw
		int fieldNr = 0;
		
		for (int i = 0; i <= Height * 2; i++) {
			System.out.print("|");	//left wall in each line of the grid
			if (i % 2 == 0) {
				//printing the top / bottom border of the fields
				for (int j = 0; j < Width; j++) {
					if (j < Width - 1) System.out.print("----");
					else System.out.print("---");
				}	
			} else {
				//printing the fields with her content
				for (int j = 0; j < Width; j++) {
					//if the field is != null, it can be everything possible
					if (gv[fieldNr] != null) {
						if (gv[fieldNr].player) System.out.print(" P ");		//player
						else if (gv[fieldNr].ghost) System.out.print(" G ");	//ghost
						else if (gv[fieldNr].isWall) System.out.print(" x ");	//wall
						else if (gv[fieldNr].food) System.out.print(" . ");		//food		
						else System.out.print("   ");							//nothing
					} else {
						//if it is null, it can only be food
						System.out.print(" . ");
					}
					
					if (j < Width - 1) System.out.print("|");
					//increase the field number and do the same for the next field
					fieldNr++;
				}	
			}
			System.out.println("|");	//right wall in each line of the grid	
		}
		
		return 0;
	}

	
}
