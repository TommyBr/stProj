package pacman;
import static org.junit.Assert.*;
import java.util.Random;

public class grid {
	private static int Width, Height;
	private static gridValues gv[];
	
	public grid() {
		
	}
	
	public int initGrid(int width, int height) {
		assertTrue(width > 0 && height > 0); 
		Width = width;
		Height = height;
		gv = new gridValues[width * height]; 
		int r = (int)((Math.random() * width * height * 1000) % (width * height));
		gv[r] = new gridValues();
		gv[r].player = true;
		return 0;
	}
	
	public static int getWidth() {
		return Width;
	}
	
	public static int getHeight() {
		return Height;
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
					else System.out.print("   ");
					if (j < Width - 1) System.out.print("|");
					fieldNr++;
				}	
			}
			System.out.println("|");	
		}
		
		return 0;
	}

	
}
