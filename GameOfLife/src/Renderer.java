import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Renderer extends JPanel implements Runnable {
	
	Grid grid;
	Thread thread;
	
	public Renderer()
	{
		grid = new Grid();
	
		this.setSize(Window.WIDTH, Window.HEIGHT);
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		grid.draw(g);
		
	}

	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks;
		long timer = System.currentTimeMillis();
		double delta = 0;
		double frames = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				frames++;
				delta--;
				grid.update();
				repaint();
			}
			if(System.currentTimeMillis() - timer >= 1000) {
//				System.out.println("fps:" + frames);
				timer += 1000;
				frames = 0;
			}
		}
		
	}
}
