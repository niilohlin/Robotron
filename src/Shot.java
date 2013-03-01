import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Shot extends Moving {
	public Shot(int x, int y, ArrayList<Direction> directions)
	{
		super(x, y, 6, 6, new ImageIcon("res/shot.png"), directions, 4);
	}


}
