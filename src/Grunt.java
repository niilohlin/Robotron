import javax.swing.ImageIcon;

public class Grunt extends Moving {
	public Grunt(int x, int y) {
		super(x, y, 18, 26, new ImageIcon("res/grunt.png"));

	}

	/**
	 * 
	 * @param x
	 *          the x coordinate of the grunt
	 * @param y
	 *          the y coordinate of the grunt
	 */
	public void update(int x, int y)

	{
		try {
			setX(getX() + ((x - getX()) / Math.abs(x - getX())) * getSpeed());
		} catch (ArithmeticException e) {
		}// this is expected, if x == getX(), then it would be division by zero
		try {
			setY(getY() + ((y - getY()) / Math.abs(y - getY())) * getSpeed());
		} catch (ArithmeticException e) {
		}

		setSpeed(getSpeed() + 0.001); //increase the speed of the grunt

	}

}
