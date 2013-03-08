import javax.swing.JFrame;


public class Main extends JFrame {
	public static Main main;
	public static Game game;
	
	public Main()
	{
		setResizable(false);
		setTitle("Robotron");
		//setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		main = new Main();
		game = new Game();
		main.add(game);
		game.start();
		
	}
	

}
