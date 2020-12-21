import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class MainMenu	extends JPanel implements ActionListener{//maybe make abstract
	//private JLabel img;
	private JLabel header;
	public JButton startButton;

	MainMenu(){
		this.setPreferredSize(new Dimension(GamePanel.MAP_WIDTH,GamePanel.MAP_HEIGHT));
		//this.setLayout(new BorderLayout());;
		this.setBackground(Color.black);//maybe change to interactive
		this.setFocusable(true);
		
		//Layout Check
		this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
       
        //Image
        //ImageIcon backGround = new ImageIcon("MainScreenBackground.png");
        //img = new JLabel(backGround);
        
        //Header text
        header = new JLabel();
        header.setText("Welcome to my Snake game");
        header.setFont(new Font(null,Font.BOLD, 40));
		header.setSize(100, 100);
		header.setForeground(Color.white);
		header.setLocation(GamePanel.MAP_WIDTH/2-100,GamePanel.MAP_HEIGHT/2-100);
		//Start button:
		startButton = new JButton();
		startButton.setText("New Game");
		startButton.setFont(new Font(null,Font.BOLD, 40));
		startButton.setSize(100, 100);
		startButton.setLocation(GamePanel.MAP_WIDTH/2,GamePanel.MAP_HEIGHT/2);
		//this.add(img, gbc);
		this.add(header,gbc);
		this.add(startButton, gbc);	
	}
	
	public JButton getStartButton(){
		return startButton;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/*if(e.getSource()==startButton){
			//this.remove(startButton);
			this.add(restartButton);
			game=new GamePanel();
			this.add(game);
			game.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}*/
}
}