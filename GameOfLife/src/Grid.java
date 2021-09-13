import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Grid {
	
	private static int cellSize = 3;
	private static int x_Cols = Window.WIDTH / cellSize;
	private static int y_Rows = Window.HEIGHT / cellSize;
	private static int[][] table = new int[x_Cols][y_Rows];
	private static int[][] nextGen = new int[x_Cols][y_Rows];
	private static int generation = 0;
	
	//
	//Creates a random table of 1's and 0's and a copy of that table to alter
	//
	public Grid() {
		Random rand = new Random();
		for (int x = 0; x < x_Cols; x++) {
			for (int y = 0; y < y_Rows; y++) {
				table[x][y] = rand.nextInt(2);
				nextGen[x][y] = table[x][y];
			}
		}
	}
	
	//
	//Computes the conditions to live or die on the Original table
	// then applies and updates the state to the next Generation table
	// then applies the new states of the cells at the end of the call
	//
	public void update() {
		
		for (int x = 0; x < x_Cols; x++)
			for (int y = 0; y < y_Rows; y++) {
				int neighbors = countNeighbors(x,y);
				
				if (neighbors == 3) {
					nextGen[x][y] = 1;
				}
				else if (neighbors < 2 || neighbors > 3) {
					nextGen[x][y] = 0;
				}
			}
		for (int x = 0; x < x_Cols; x++)
			for (int y = 0; y < y_Rows; y++) {
				table[x][y] = nextGen[x][y];
			}
		generation++;
	}
	
	public int countNeighbors(int x, int y) {
		int neighbors = 0;
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				int x2 = (x + i + x_Cols) % x_Cols;
				int y2 = (y + j + y_Rows) % y_Rows;
				if (table[x2][y2] == 1)
					neighbors ++;
			}
		neighbors -= table[x][y];
		return neighbors;
	}
	//
	//Draws a visual representation of the table alive = white, dead is dark gray or "blank"
	//
	public void draw(Graphics g) {
		
		for (int x = 0; x < x_Cols; x++)
			for (int y = 0; y < y_Rows; y++) {
				
				if (table[x][y] == 1) {
					g.setColor(Color.WHITE);
					g.fillRect(x*cellSize, y*cellSize, cellSize-1,cellSize-1);
				}
			}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Impact", Font.PLAIN, 30)); 
		g.drawString("Generation: " + String.valueOf(generation), 20, 50);
	}
}
