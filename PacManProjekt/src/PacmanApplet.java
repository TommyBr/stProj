import java.applet.*;
import java.awt.*;

/**
 * @author vandigoh
 * @date 04.11.12
 *
 * Durch dieses Programm wird ein Applet erstellt,
 * dass einem Kreis (Ball) von der einen Seite des
 * Applets auf die andere bewegt.
 *
 * Applet Grundstruktur implementieren
 * 
 * Um eine Animation in Java verwirklichen zu kšnnen
 * muss folgendes implementiert werden:
 * 1. Interface Runnable
 * 2. Die dazugehšrige Methode run().
 * 
 */

/*
 * Um ein Graphik-Objekt zu bewegen, braucht man einen Thread,
 * der in der Start() geschaffen wird
 * 
 * Threads: eigenstŠndiges Programmfragment, das parallel zu anderen 
 * Threads (Multithreading) laufen kann. Threads werden in Java durch 
 * die Klasse Thread und das Interface Runnable sowie die dazugehšrige
 * Funktion run() implementiert
 *  Wichtige Funktionen zur Erzeugung eines Threads sind:
 *		Thread.start(): Startet einen Thread
 * 		Thread.stop(): Stopt einen Thread
 * 		Thread.sleep(Zeit in Millisekunden): 
 * 			Stopt den Thread fŸr die Angegebene Zeitspanne
 */


public class PacmanApplet extends Applet implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Initialisierung der Instanzvariablen fuer Bewegungen und Dimensionen
		int x_pos = 0;
		int y_pos = 0;
		int x_speed = 0; // Geschwindigkeit in X-Richtung
		int y_speed = 0; // Geschwindigkeit in Y-Richtung
		int appletsize_x = 600; // Breite des Applets
		int appletsize_y = 600; // Hšhe des Applets
		
		// Instanzvariablen fuer die Doppelpufferung
		private Image dbImage;
		private Graphics dbg;
		
		// Instanzvariablen fuer das Spiel
		static final int PACMAN_SIZE = 30;
		Image pacmanImage;
		
		public void init() {
			
			// Applet- Hoehe und Breite werden bestimmt
			resize(appletsize_x, appletsize_y);
			
			// Bestimmung der Applet-Hintergrundfarbe
			setBackground(Color.black);
			
			// Laden der PacmanBilddatei pacpix_0.gif
			pacmanImage = getImage(getCodeBase(),"pacpix_0.gif");
			
		}
		
		
		public void start() {
			
			// Schaffen eines neuen Threads, in dem das Spiel lŠuft
			Thread th = new Thread(this);
			
			// Starten des Threads
			th.start();
		}
		
		
		
		public void stop() {
		}
		
		public void destroy() {}
		
		
		/* 
		 * Diese Methode fŠngt das Ereigniss auf, das beim DrŸcken
		 * einer Taste entsteht
		 */
		public boolean keyDown(Event e, int key) {
			
			// Linke Cursortaste gedrueckt
			if(key == Event.LEFT) {
				
				// Aendern des Richtungsvektors so, dass sich der Ball
				// nach Links bewegt (x_pos wird kleiner)
				x_speed = -5;
				y_speed = 0;
			}
			
			// Rechte Cursortaste gedrueckt
			else if(key == Event.RIGHT) {
						
				// Aendern des Richtungsvektors so, dass sich der Ball
				// nach Rechts bewegt (x_pos wird groe§er)
				x_speed = 5;
				y_speed = 0;
			}
			
			// Linke Cursortaste gedrueckt
			else if(key == Event.DOWN) {
						
				// Aendern des Richtungsvektors so, dass sich der Ball
				// nach Oben bewegt (y_pos wird kleiner)
				y_speed = 5;
				x_speed = 0;
			}
					
			// Rechte Cursortaste gedrueckt
			else if(key == Event.UP) {
								
				// Aendern des Richtungsvektors so, dass sich der Ball
				// nach Unten bewegt (x_pos wird groe§er)
				y_speed = -5;
				x_speed = 0;
				
					}
			
			// Space Taste gedrueckt (hat den Wert 32!)
			else if(key == 32) {
				
				// Stoppen des Balles (x_speed && y_speed = 0)
				x_speed = 0;
				y_speed = 0;
			}
			else {
				
				// Ausgabe von gedruecktem Key und Zahlenwert an die Standardausgabe
				System.out.println("Charakter: " + (char)key + " Integer Value: " + key);
			}
			
			return true;
		}
		
		
		/* 
		 * Diese Methode fŠngt das Ereigniss auf, das beim DrŸcken
		 * einer Taste entsteht
		 */
		public boolean keyUp(Event e, int key) {
			
			// Linke Cursortaste frei
			if(key == Event.LEFT || key == Event.RIGHT) {
				
				x_speed = 0;
			}
				
			
			// Linke Cursortaste frei
			else if(key == Event.DOWN || key == Event.UP) {
						
				y_speed = 0;
			}
					
			
			// Space Taste gedrueckt (hat den Wert 32!)
			else if(key == 32) {
				
				// Stoppen des Balles (x_speed && y_speed = 0)
				x_speed = 0;
				y_speed = 0;
			}
			else {
				
				// Ausgabe von gedruecktem Key und Zahlenwert an die Standardausgabe
				System.out.println("Charakter: " + (char)key + " Integer Value: " + key);
			}
			
			return true;
		}
		
		
		/*
		 * Thread in der Run() laufen lassen. Nach jedem
		 * Durchlauf der Run() wird der Thread fŸr eine gewisse Zeit
		 * gestoppt, um ihn dann erneut durchlaufen zu lassen
		 */
		
		@Override
		public void run() {
			
			// Erniedrigen der ThreadPriority um zeichnen zu erleichtern
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
			
			// Solange true ist, lŠuft der Thread weiter
			while(true) {
				
				// Ball beruehrt den rechten Rand und prallt ab
				if (x_pos > appletsize_x  - PACMAN_SIZE)  {
					
					// Aendern der Richtung des Balles <--
					x_speed = -1;
				}
			
				// Ball beruehrt linken Rand und prallt ab
				else if(x_pos < PACMAN_SIZE) {
					
					// Aendern der Richtung des Balles -->
					x_speed = +1;
				}
				
				//veraendern der x-Koordinate
				x_pos += x_speed;
				
				// Ball beruehrt unteren Rand und prallt ab
				if (y_pos > appletsize_y - PACMAN_SIZE) {
					
					// Richtung aendern, Ball geht nach oben
					
					y_speed = -1;
				}
				
				// Ball beruehrt oberen Rand und prallt ab
				else if(y_pos < PACMAN_SIZE) {
					
					// Richtundg aendern, Ball geht nach unten
					y_speed = +1;
				}
				//veraendern der y-Koordinate
				y_pos += y_speed;
				
				
				// Neuzeichen des Applets
				repaint();
				
				try {
					
					// Stoppen des Threads fŸr in Klammern angegebene Millisekunden
					Thread.sleep(20);
				}
				catch(InterruptedException ex) {
					// do nothing
				}
				
				// ZurŸcksetzen der ThreadPriority auf Maximalwert
				Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
			}
			
		}
		
		/*
		 * Methode fuer die Realisierung der Doppelpufferung
		 * zur Reduzierung des Bildschirmsflackerns
		 */
		public void update(Graphics g) {
			
			// Initialisierung des DoubleBuffers
			if(dbImage == null) {
				
				dbImage = createImage(this.getSize().width, this.getSize().height);
				dbg = dbImage.getGraphics();
			}
			
			// Bildschirm im Hintergrund loeschen
			dbg.setColor(getBackground());
			dbg.fillRect(0, 0, this.getSize().width,  this.getSize().height);
					
			// Auf geloeschten Hintergrund Vordergrund zeichnen
			dbg.setColor(getForeground());
			paint(dbg);
					
			// Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
			g.drawImage(dbImage, 0, 0, this);
		}
		
		// Mit paint() zeichne ich den Ball
		public void paint(Graphics g) {
			
			// Setzen der Zeichenfarbe auf Rot
			g.setColor(Color.YELLOW);
			
			// Zeichnen eines gefŸllten Kreises
			g.fillArc(x_pos, y_pos, PACMAN_SIZE, PACMAN_SIZE, 20, 320);
			
			
		}

}
