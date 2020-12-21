import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Apple{
	private int x;
	private int y;
	Random rand;
	
	Apple(Random rand){
		this.rand=rand;
		createApple();
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void createApple(){//CHANGE TO != SNAKE BODY HASHMAP
		x = rand.nextInt((int)(GamePanel.MAP_WIDTH/GamePanel.CELL_SIZE))*GamePanel.CELL_SIZE;
		y = rand.nextInt((int)(GamePanel.MAP_HEIGHT/GamePanel.CELL_SIZE))*GamePanel.CELL_SIZE;
		
	}
	
	public boolean checkApple(int headX, int headY) {
		if((headX == x) && (headY == y)) {
			createApple();
			return true;
		}
		return false;
	}
}