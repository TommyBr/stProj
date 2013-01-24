package pacman;

public class TUIDrawing {
	
	public int drawGrid(int width, int height, GridValues gv) {
		//number of the current field that will be draw
		int fieldNr = 0;
		
		for (int i = 0; i <= height * 2; i++) {
			//left wall in each line of the grid
			TUI.print("|");
			if (i % 2 == 0) {
				//printing the top / bottom border of the fields
				drawTopBottom(width);
			} else {
				//printing the fields with her content
				for (int j = 0; j < width; j++) {
					drawFieldContent(fieldNr++, gv);
					if (j < width - 1) { TUI.print("|"); }
					//increase the field number and do the same for the next field
				}	
			}
			//right wall in each line of the grid
			TUI.println("|");
		}
		
		return 0;
	}
	
	void drawTopBottom(int width) {
		for (int j = 0; j < width; j++) {
			if (j < width - 1) { TUI.print("----"); }
			else { TUI.print("---"); }
		}	
	}
	
	void drawFieldContent(int fieldNr, GridValues gv) {
		//player
		if (gv.isPlayer(fieldNr)) { TUI.print(" P "); }
		//ghost
		else if (gv.isGhost(fieldNr)) { TUI.print(" G "); }
		//wall
		else if (gv.isWall(fieldNr)) { TUI.print(" x "); }
		//food
		else if (gv.isFood(fieldNr)) { TUI.print(" . "); }	
		//nothing
		else { TUI.print("   "); }
	}
}
