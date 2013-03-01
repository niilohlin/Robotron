import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Moving extends Sprite {
	private ArrayList<Direction> directions = new ArrayList<Direction>();
	private double speed = 1;
	public Moving (int x, int y, int width, int height, ImageIcon image, ArrayList<Direction> dir, int speed)
	{
		super(x, y, width, height, image);
		this.speed = speed;
		directions.add(Direction.NULL);
		for(Direction d : dir)
			directions.add(d);
	}
	public Moving (int x, int y, int width, int height, ImageIcon image, Direction direction, int speed)
	{
		super(x, y, width, height, image);
		this.speed = speed;
		directions.add(Direction.NULL);
		directions.add(direction);
	}
	public Moving (int x, int y, int width, int height, ImageIcon image, Direction direction)
	{
		super(x, y, width, height, image);
		directions.add(Direction.NULL);
		directions.add(direction);
	}
	public Moving (int x, int y, int width, int height, ImageIcon image, int speed)
	{
		super(x, y, width, height, image);
		this.speed = speed;
		directions.add(Direction.NULL);
	}
	public Moving (int x, int y, int width, int height, ImageIcon image)
	{
		super(x, y, width, height, image);
		directions.add(Direction.NULL);
	}
	
	@Override
	public void update()
	{
		move();
	}

	public void move() {
		for (Direction direction : directions) {
			switch (direction) {
			case NORTH:
				setY(getY() - getSpeed());
				break;
			case WEST:
				setX(getX() - getSpeed());
				break;
			case EAST:
				setX(getX() + getSpeed());
				break;
			case SOUTH:
				setY(getY() + getSpeed());
			}
		}
	}
	
	public ArrayList<Direction> getDirections() {
		return directions;
	}

	public void setDirection(Direction direction) {
		if(!directions.contains(direction))
			directions.add(direction);
	}
	
	public void clean()
	{
		for(int i = 0; i < directions.size() - 1; i++)
		{
			directions.remove(1); // remove everyone except for the first (NULL)
		}
	}
	public int getSpeed() {
		return (int) speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	

}
