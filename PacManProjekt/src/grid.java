
public class grid {
	private static int Width, Height, Player;
	private static gridValues gv[];
	
	public grid() {
		
	}
	
	private void createLabyrinth() {
		for (int i = 1; i < Width - 1; i++) {
			int idx = i + Width;
			if (gv[idx] == null) gv[idx] = new gridValues();	
			gv[idx].isWall = true;	
		}
		
		for (int i = 2; i < Height - 1; i++) {
			int idx = i * Width + 1;
			if (gv[idx] == null) gv[idx] = new gridValues();	
			gv[idx].isWall = true;	
		}
		
		
		for (int j = 3; j < Height; j+=2) {
			for (int i = 3; i < Width - 1; i++) {
				int idx = i + Width * j;
				if (gv[idx] == null) gv[idx] = new gridValues();	
				gv[idx].isWall = true;	
			}
		}
	}
	
	public int initGrid(int width, int height) {
		Width = width;
		Height = height;
		gv = new gridValues[width * height]; 
		createLabyrinth();
		int r = (int)((Math.random() * width * height * 1000) % (width * height));
		while (gv[r] != null) {
			r = (int)((Math.random() * width * height * 1000) % (width * height));
		}
		gv[r] = new gridValues();
		gv[r].player = true;
		Player = r;
		return 0;
	}
	
	public int getWidth() {
		return Width;
	}
	
	public int getHeight() {
		return Height;
	}
	
	public boolean isWall(int idx) {
		if (gv[idx] != null && gv[idx].isWall) return true;
		return false;
	}
	
	public void setPlayer(int pos) {
		if (pos < 0 || pos > Width * Height) return;
		gv[Player].player = false;
		Player = pos;
		if (gv[Player] == null) gv[Player] = new gridValues();
		gv[Player].player = true;
	}
	
	public int getPlayer() {
		return Player;
	}
	
	//zeichnet das Spielfeld in die Console
	public int drawGrid() {
		int fieldNr = 0;
		for (int i = 0; i <= Height * 2; i++) {
			System.out.print("|");
			if (i % 2 == 0) {
				for (int j = 0; j < Width; j++) {
					if (j < Width - 1) System.out.print("----");
					else System.out.print("---");
				}	
			} else {
				for (int j = 0; j < Width; j++) {
					if (gv[fieldNr] != null && gv[fieldNr].player) System.out.print(" P ");
					else if (gv[fieldNr] != null && gv[fieldNr].isWall) {
						System.out.print(" x ");
					} else System.out.print("   ");
					if (j < Width - 1) System.out.print("|");
					fieldNr++;
				}	
			}
			System.out.println("|");	
		}
		
		return 0;
	}

	
}
