package pacman;
import static org.junit.Assert.*;

public class grid {
	private static int Width, Height;
	private static gridValues gv[];
	
	public grid() {
		
	}
	
	public static int initGrid(int width, int height) {
		assertTrue(width > 0 && height > 0); 
		Width = width;
		Height = height;
		gv = new gridValues[width * height]; 
		return 0;
	}
	
	public static int getWidth() {
		return Width;
	}
	
	public static int getHeight() {
		return Height;
	}
	
	//zeichnet das Spielfeld in die Console
	public static int drawGrid() {
		for (int i = 0; i <= Height * 2; i++) {
			System.out.print("|");
			if (i % 2 == 0) {
				for (int j = 0; j < Width; j++) {
					if (j < Width - 1) System.out.print("----");
					else System.out.print("---");
				}	
			} else {
				for (int j = 0; j < Width; j++) {
					System.out.print("   ");
					if (j < Width - 1) System.out.print("|");	
				}	
			}
			System.out.println("|");	
		}
		
		return 0;
	}

	
}
