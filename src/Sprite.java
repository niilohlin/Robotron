import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Sprite {
	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
	private ImageIcon image = new ImageIcon();

	
	

	public Sprite(int x, int y, int width, int height, ImageIcon image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}

	private boolean isIn(int x, int y) {
		if (this.x < x && this.x + width > x) {
			if (this.y < y && this.y + height > y)
				return true;
		}
		return false;
	}
	
	public boolean collision(Sprite s, boolean hasCalled)
	{
		if(isIn(s.getX(), s.getY()) 
				|| isIn(s.getX() + s.getWidth(), s.getY())
				|| isIn(s.getX(), s.getY() + s.getHeight())
				|| isIn(s.getX() + s.getWidth(), s.getY() + s.getHeight()))
			return true;
		if(!hasCalled)
			return s.collision(this, true);
		return false;
	}
	
	public boolean collision(Sprite s)
	{
		return collision(s, false);
	}
	
	public void moveto(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public void blit(Graphics g)
	{
		g.drawImage(image.getImage(), x, y, Main.game);
	}
	
	public void update()
	{
		
	}
	
	public double distanceTo(Sprite sprite)
	{
		int diffx = x - sprite.getX();
		int diffy = y - sprite.getY();
		return Math.sqrt(diffx * diffx + diffy * diffy);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}




}

