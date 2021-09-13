import javax.swing.JFrame;

public class Window extends JFrame {
	
	public final static int WIDTH = 1800;
	public final static int HEIGHT = 900;
	protected Renderer graphics;
	
	public Window() {
		
		graphics = new Renderer();
		
		this.setSize(WIDTH+16,HEIGHT+39);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(graphics);
		this.setResizable(false);
		this.setVisible(true);
		
	}
}
