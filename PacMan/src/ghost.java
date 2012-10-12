import java.awt.geom.Point2D;

public class ghost {
	static Point2D Pos[];		//Position der Geister

	public static void initGhosts(int ghosts, Point2D pos[]) {
		Pos = new Point2D[ghosts];
		
		for (int i = 0; i < ghosts; i++) {
			Pos[i].setLocation(pos[i]);
		}
	}
	
	public static Point2D getPos(int ghost_nr) {
		return Pos[ghost_nr];
	}
	
	public static void setPos(int ghost_nr, Point2D pos) {
		Pos[ghost_nr] = pos;
	}

}
