
import javax.swing.ImageIcon;


public class Grunt extends Moving {
	public Grunt(int x, int y)
	{
		super(x, y, 18, 26, new ImageIcon("res/grunt.png"));
		
	}
	
	public void update(int x, int y)
	{
		try
		{
			setX(getX() + ((x - getX()) / Math.abs(x - getX())) * getSpeed());
		}
		catch (ArithmeticException e) {}//this is expected
		try
		{
			setY(getY() + ((y - getY()) / Math.abs(y - getY())) * getSpeed());
		}
		catch (ArithmeticException e) {}
		
		setSpeed(getSpeed() + 0.001);
		
	}

}
