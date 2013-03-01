import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Player extends Moving {
	private ArrayList<Direction> aimDirection = new ArrayList<Direction>();

	public Player() {
		//super(Main.game.getWidth() / 2, Main.game.getHeight() / 2, 10, 22, new ImageIcon("res/player.png"));
		super(400, 300, 10, 22, new ImageIcon("res/player.png"), 2);
		aimDirection.add(Direction.NULL);
	}
	
	public void update(ArrayList<Key> keys)
	{
		clean();
		cleanAim();
		for(Key key : keys)
		{
			if(key.pressed && key.type == KeyType.MOVE)
				setDirection(key.direction);
			if(key.pressed && key.type == KeyType.AIM)
				setAim(key.direction);

				
		}
		move();
	}
	public void setAim(Direction direction) {
		if(!aimDirection.contains(direction))
			aimDirection.add(direction);
	}
	public ArrayList<Direction> getAim()
	{
		return aimDirection;
	}
	
	public void cleanAim()
	{
		for(int i = 0; i < aimDirection.size() - 1; i++)
		{
			aimDirection.remove(1); // remove everyone except for the first (NULL)
		}
	}
}
