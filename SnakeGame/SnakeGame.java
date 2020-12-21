import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SnakeGame extends JFrame implements ActionListener{
	
	GamePanel game;
	MainMenu menu;
	
	SnakeGame(){
		this.setTitle("Snake game by Niv Rave");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(game.MAP_WIDTH, game.MAP_HEIGHT);
		this.setResizable(false);
		//this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//Init menu	
		menu=new MainMenu();
		menu.startButton.addActionListener(this);
		this.add(menu);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu.startButton){
			this.remove(menu);
			//this.add(game.restartButton);
			game=new GamePanel();
			game.restartButton.addActionListener(this);
			this.add(game);
			game.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
}