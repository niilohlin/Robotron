import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Moving {
	private ArrayList<Direction> aimDirection = new ArrayList<Direction>();

	public Player() {
		super(400, 300, 10, 22, new ImageIcon("res/player.png"), 2);

		// the aimDirection has to have at least one element, to avoid
		// nullPointerException
		aimDirection.add(Direction.NULL);

	}

	public void update(ArrayList<Key> keys) {
		clean();
		cleanAim();
		for (Key key : keys) {
			if (key.pressed && key.type == KeyType.MOVE)
				setDirection(key.direction);
			if (key.pressed && key.type == KeyType.AIM)
				setAim(key.direction);

		}
		move();
	}

	public void setAim(Direction direction) {
		if (!aimDirection.contains(direction))
			aimDirection.add(direction);
	}

	public ArrayList<Direction> getAim() {
		return aimDirection;
	}

	public void cleanAim() {
		for (int i = 0; i < aimDirection.size() - 1; i++) {
			aimDirection.remove(1); // remove everyone except for the first (NULL)
		}
	}
}
