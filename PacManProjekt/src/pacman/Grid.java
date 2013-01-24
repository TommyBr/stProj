package pacman;

public class Grid {
	//constant variables for constant numbers > 2
	static final int THREE = 3, TEN = 10, THOUSAND = 1000;
	private static int width, height, player, ghosts;
	private static GridValues gv;
	private static TUIDrawing tuiDrawing;
	private Ghost ghost[];
	//number of eaten food (clear fields)
	private static int eaten, foodleft, movements;
	
	
	public Grid() {
		//nothing eaten at the beginning
		eaten = 0;
		//no move done
		movements = 0;
		gv = new GridValues();
		tuiDrawing = new TUIDrawing();
	}
	
	void drawGrid() {
		tuiDrawing.drawGrid(width, height, gv);
	}
	
	static GridValues getGridValue() {
		return gv;
	}
	
	//returns the number of eaten food
	static int getEaten() {
		return eaten;
	}
	
	//returns the number of movements
	static int getMovements() {
		return movements;
	}
	
	void placeWall(int idx) {
		//mark this field as a wall
		gv.setWall(idx, true);	
		//you can't eat a wall
		gv.setFood(idx, false);
		foodleft--;
	}
	
	private void createLabyrinth() {
		for (int i = 1; i < width - 1; i++) {
			if (width % i == 0) {
				continue;
			}
			placeWall(i + width);
		}
		
		for (int i = 2; i < height - 1; i++) {
			if (height % (i * 2) == 0) {
				continue;
			}
			placeWall(i * width + 1);
		}
		
		for (int j = THREE; j < height; j+=2) {
			for (int i = THREE; i < width - 1; i++) {
				if (i % (j % TEN + 1) == 0) {
					continue;
				}
				placeWall(i + width * j);
			}
		}
	}
	
	int getRandomField() {
		return (int)((Math.random() * width * height * THOUSAND) % (width * height));
	}
	
	void initGhosts(int numberOfGhosts) {
		ghosts = numberOfGhosts;
		ghost = new Ghost[ghosts];
		
		int r;
		for (int i = 0; i < ghosts; i++) {
			//creating a new Ghost
			ghost[i] = new Ghost();
			//searching for an another random field that is not in use
			r = getRandomField();
			while (!gv.fieldIsEmpty(r)) { r = getRandomField(); }
			//mark it as ghost
			gv.setGhost(r, true);
			//update the ghost position
			ghost[i].setPos(r);	
		}
	}
	
	public int initGrid(int newWidth, int newHeight, int numberOfGhosts) {
		//dimensions of the grid
		width = newWidth;
		height = newHeight;
		
		//at the beginning all fields are food
		foodleft = width * height;
		
		//creating a new grid
		gv.create(width * height);
		//and draw a labyrinth, adjusted to the dimensions
		createLabyrinth();
		
		//searching for a random field that is not in use
		int r = getRandomField();
		while (!gv.fieldIsEmpty(r)) { r = getRandomField(); }
		//mark it as non-food
		gv.setFood(r, false);
		foodleft--;
		//mark it as player
		gv.setPlayer(r, true);
		//update the player position
		player = r;
		
		//initiate Ghosts
		initGhosts(numberOfGhosts);		
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
	
	static int getFoodLeft() {
		return foodleft;
	}
	
	//returns true if the field "idx" is a wall
	public boolean isWall(int idx) {
		if (gv.isWall(idx)) { return true; }
		return false;
	}
	
	public boolean isFood(int idx) {
		if (gv.isFood(idx)) { return true; }
		return false;
	}
	
	public boolean isGhost(int idx) {
		if (gv.isGhost(idx)) { return true; }
		return false;
	}
	
	public boolean isPlayer(int idx) {
		if (gv.isPlayer(idx)) { return true; }
		return false;
	}
	
	public void setPlayer(int pos) {
		//make sure the player moving inside the grid
		if (pos < 0 || pos > width * height) { return; }
		
		//disable the old grid position of the player
		gv.setPlayer(player, false);
		//update the player position
		player = pos;
		//enable the new grid position of the player
		gv.setPlayer(player, true);
		
		//counting the food and mark the field as no food
		if (gv.isFood(player)) {
			eaten++;
			foodleft--;
			gv.setFood(player, false);
		}
		
		//counting movements
		movements++;
		
		//if the player has moved, the ghost should move
		for (int i = 0; i < ghosts; i++) {
			if (gameStatus() == 0) { ghost[i].move(); }
		}
	}
	
	//returns the position of the player
	static int getPlayer() {
		return player;
	}
	
	int getGhost(int idx) {
		return ghost[idx].getPos();
	}
	
	//checking the status of the game
	int gameStatus() {
		//player and ghost at the same field?
		for (int i = 0; i < ghosts; i++) {
			if (getPlayer() == ghost[i].getPos()) {
				TUI.println("Du Wurdest vom Geist gefangen!\nSpielende.");
				//printing statistics
				TUI.printStatistic();
				//player loose
				return -1;
			}
		}
		
		//all food eaten?
		if (foodleft == 0) {
			TUI.println("Du hast es geschaft alles aufzuessen!\nSpielende.");
			//printing statistics
			TUI.printStatistic();
			//player win
			return 1;
		}
		
		return 0;
	}

}
