package pacman;
import java.awt.geom.Point2D;

public class ghost {
	static Point2D Pos[];
	static int ghosts_cout;

	
	public static void initGhosts(int ghosts, Point2D pos[]) {
		ghosts_cout = ghosts;
		
		Pos = new Point2D[ghosts];
		
		for (int i = 0; i < ghosts; i++) {
			Pos[i].setLocation(pos[i]);
		}
	}
	
	//liefert die Koordinaten eines Geistes
	public static Point2D getPos(int ghost_nr) {
		return Pos[ghost_nr];
	}
	
	//setzt die Koordinaten eines Geistes
	public static void setPos(int ghost_nr, Point2D pos) {
		Pos[ghost_nr] = pos;
	}
	
	//liefert die Anzahl der Geister
	public static int getNumberOfGhosts() {
		return ghosts_cout;
	}

}
