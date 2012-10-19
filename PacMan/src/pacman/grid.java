package pacman;
public class grid {
	private static int Width, Height;
	
	public static int initGrid(int width, int height) {
		Width = width;
		Height = height;
		System.out.println("grid initialized with size: " + width + " x " + height);
		return 0;
	}
	
	public static int drawGrid() {
		for (int i = 0; i <= Height * 2; i++) {
			System.out.print("|");
			if (i % 2 == 0) {
				for (int j = 0; j < Width * 3 - 1; j++) {
					System.out.print("-");	
				}	
			} else {
				for (int j = 1; j < Width * 3; j++) {
					if (j % 3 == 0 && j > 0) System.out.print("|");	
					else System.out.print(" ");
				}	
			}
			System.out.println("|");	
		}
		
		return 0;
	}

	
}

/*

|---------
| P |  |
|-------
| G |
|

*/
