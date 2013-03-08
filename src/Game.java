import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
	private long updateTime = System.nanoTime() / 1000000; // 1 000 000,
	private long timeSinceLastShot = System.nanoTime() / 1000000;
	private int FPS;
	private ArrayList<Grunt> grunts = new ArrayList<Grunt>();
	private ArrayList<Shot> shots = new ArrayList<Shot>();
	private Player player = new Player();
	private int level = 0;
	private ArrayList<Key> keys = new ArrayList<Key>();
	private int shootSpeed = 2;
	private int kills = 0;
	private int MAX_LEVELS = 10;
	private boolean lost = false;

	public Game() {
		setVisible(true);
		setFocusable(true);
		addKeyListener(this);
	}

	public void start() {
		loadconfig();
		// player = new Player();
		while (!lost) {
			update();
			tick();
			repaint();
		}
		repaint();
	}

	private void update() {
		// maybe creates a new grunt,
		// as the level increases, the chance of a new grunt per second increases
		Random r = new Random();

		int chance = r.nextInt(FPS - level);
		if (chance >= FPS - 1 - level) {
			Grunt g = new Grunt(r.nextInt(getWidth()), r.nextInt(getHeight()));
			if (player.distanceTo(g) > player.getHeight() * (MAX_LEVELS - level))
				grunts.add(g);

		}

		updateGrunts();
		updateShots();
		player.update(keys);
		shoot();

		if (kills % 10 == 0 && kills != 0 && level < MAX_LEVELS)
			level++;
	}

	private void shoot() {
		if (System.nanoTime() / 1000000 - timeSinceLastShot > 1000 / shootSpeed
				&& player.getAim().size() > 1) {
			//player.getAim() returns an Arraylist of Directions, always at least containing Direction.null
			//if the size > 1, and the shoot pase allows it, shoot
			timeSinceLastShot = System.nanoTime() / 1000000;
			shots.add(new Shot(player.getX(), player.getY(), player.getAim()));
		}
	}

	private void loose() {
		lost = true;
	}

	private void updateGrunts() {
		for (Grunt grunt : grunts) {
			grunt.update(player.getX(), player.getY());
			if (grunt.collision(player)) {
				loose();
			}
		}

	}

	private void updateShots() {
		int deadGrunt = -1;
		int deadShot = -1;
		//you can't modify the collection that you loop over, so the killing of the grunts and shots
		//are done outside the loop
		for (int i = 0; i < shots.size(); i++) {
			Shot shot = shots.get(i);
			shot.update();
			for (int j = 0; j < grunts.size(); j++) {
				Grunt grunt = grunts.get(j);
				if (shot.collision(grunt)) {
					deadGrunt = j;
					deadShot = i;
				}
			}
			//if the shot is outside the screen
			if (shot.getX() > getWidth() || shot.getX() < 0
					|| shot.getY() > getHeight() || shot.getY() < 0)
				deadShot = i;
		}
		if (deadGrunt >= 0) {
			shots.remove(deadShot);
			grunts.remove(deadGrunt);
			kills++;
		}
		else if (deadShot < -1 && deadGrunt == -1)
			shots.remove(deadShot);

	}

	public void paintComponent(Graphics g) {
//		blit the background
		g = (Graphics2D) g;
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (Grunt grunt : grunts)
			grunt.blit(g);
		player.blit(g);
		for (Shot shot : shots)
			shot.blit(g);
		g.setColor(Color.WHITE);
		g.drawString("kills: " + kills, 10, 10);
		if (lost) {
			g.setColor(Color.WHITE);
			g.drawString("kills: " + kills, 10, 10);
		}

	}

	private void tick() {
		try {
			Thread.sleep(Math.max(0, 1000 / FPS
					- (System.nanoTime() / 1000000 - updateTime)));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateTime = System.nanoTime() / 1000000;
	}

	private void loadconfig() {
		//I wanted the code to have as few "magical numbers" as possible
		//and a conf file has the ability to save the settings
		// but I don't think I will implement a menu with settings anyway,
		File conf = new File("res/robotron.conf");
		Scanner scanner = null;
		try {
			scanner = new Scanner(conf);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String next = null;
		while (scanner.hasNext()) {
			next = scanner.next();
			if (next.equals("size"))
				Main.main.setSize(scanner.nextInt(), scanner.nextInt());
			else if (next.equals("startlevel"))
				level = scanner.nextInt();
			else if (next.equals("FPS"))
				FPS = scanner.nextInt();
			else if (next.equals("shootspeed"))
				shootSpeed = scanner.nextInt();
			else if (next.equals("move")) {
				String nextmove = "not_null";
				while (!nextmove.equals("endmove")) {
					nextmove = scanner.next().toLowerCase();
					if (nextmove.equals("up"))
						keys.add(new Key(KeyType.MOVE, scanner.next().charAt(0),
								Direction.NORTH));
					else if (nextmove.equals("down"))
						keys.add(new Key(KeyType.MOVE, scanner.next().charAt(0),
								Direction.SOUTH));
					else if (nextmove.equals("left"))
						keys.add(new Key(KeyType.MOVE, scanner.next().charAt(0),
								Direction.WEST));
					else if (nextmove.equals("right"))
						keys.add(new Key(KeyType.MOVE, scanner.next().charAt(0),
								Direction.EAST));
				}
			} else if (next.equals("aim")) {
				String nextmove = "not_null";
				while (!nextmove.equals("endaim")) {
					nextmove = scanner.next();
					if (nextmove.equals("up"))
						keys.add(new Key(KeyType.AIM, scanner.next().charAt(0),
								Direction.NORTH));
					else if (nextmove.equals("down"))
						keys.add(new Key(KeyType.AIM, scanner.next().charAt(0),
								Direction.SOUTH));
					else if (nextmove.equals("left"))
						keys.add(new Key(KeyType.AIM, scanner.next().charAt(0),
								Direction.WEST));
					else if (nextmove.equals("right"))
						keys.add(new Key(KeyType.AIM, scanner.next().charAt(0),
								Direction.EAST));
				}
			}

		}
	}

	private void changeState(int key, boolean state) {
		for (Key k : keys) {
			if (key == k.key)
				k.pressed = state;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		changeState(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		changeState(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
