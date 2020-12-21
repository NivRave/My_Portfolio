import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class SnakePanel extends JPanel implements ActionListener{

	//Constants for the sizes the program will be using, will be later replaced when levels will be added
	static final int MAP_HEIGHT = 1000;//remove when changing to user selected size
	static final int MAP_WIDTH = 1000;//remove when changing to user selected size, maybe change to array of given sizes (remove final)
	static final int CELL_SIZE = 25;//check if need to be changed
	static final int CELLS = (MAP_WIDTH*MAP_HEIGHT)/(CELL_SIZE*CELL_SIZE);
	static final int DELAY = 75;//remove when changing to levels
	//Game's snake and apple
	private Snake snake;
	private Apple apple;
	//Other
	private int applesEaten;
	private char direction;
	private boolean gameOver=false;
	Timer clock;//timer for action
	//Timer gameRunTime;//timer for counting time
	public static final Random rand=new Random();
	public JButton restartButton;
	
	SnakePanel(){
		setStartingConditions();
		this.setPreferredSize(new Dimension(MAP_WIDTH,MAP_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new ChangeDirection());
		setRestartButton();
		newGame();
	}
	
	public void newGame() {
		applesEaten=0;
		apple=new Apple(rand);
		snake=new Snake(MAP_WIDTH/2,MAP_HEIGHT/2);
		clock = new Timer(DELAY,this);
		//gameRunTime = new Timer();
		clock.start();
		//gameRunTime.start();//check if need to create
	}
	
	public void restart(){
		gameOver=false;
		newGame();
	}
	
	public void setRestartButton(){
		this.restartButton= new JButton();
		this.restartButton.setText("Restart Game");
		this.restartButton.setSize(200, 100);
		this.restartButton.setFont(new Font(null,Font.BOLD, 24));
		this.restartButton.setLocation(MAP_WIDTH/2-100, MAP_HEIGHT/2+CELL_SIZE*4);
		this.restartButton.addActionListener(this);
	}
	
	public void setStartingConditions(){
		//Set a random starting direction
		int i = rand.nextInt(4);
		switch (i){
		case 0:
			setDirection('R');
			break;
    	case 1:
    		setDirection('L');
    		break;
    	case 2:
    		setDirection('U');
    		break;
    	case 3:
    		setDirection('D');
    		break;
    	default://Will never happen, just in case right is the default
    		setDirection('R');
    		break;
        }
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(gameOver==false){
		if(apple.checkApple(snake.getHeadX(),snake.getHeadY())==true){
			snake.eat();
			applesEaten++;
		}
		g.setColor(Color.red);
		g.fillOval(apple.getX(), apple.getY(), CELL_SIZE, CELL_SIZE);	
		for(int i = 0; i < snake.getSize();i++) {	
			//draw head
			if(i==0){
				g.setColor(new Color(0,100,0));
				g.fillRect(snake.getHeadX(), snake.getHeadY(), CELL_SIZE, CELL_SIZE);
			}
			//draw body
			else{
				g.setColor(new Color(152,251,152));
				g.fillRect(snake.getX(i), snake.getY(i), CELL_SIZE, CELL_SIZE);			
		}
			}
		printInfo(g);
		}
		else {
			gameOver(g);
		}
		}
	
	private void printInfo(Graphics g){
	g.setColor(Color.white);
	g.setFont(new Font(g.getFont().toString(),Font.BOLD, 40));
	FontMetrics infoMetrics = getFontMetrics(g.getFont());
	g.drawString("Score: "+applesEaten, (MAP_WIDTH - infoMetrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); //add timer from program start
	}
	
	
	
	public void checkClash() {
		for(int i = snake.getSize();i>0;i--) {
			if((snake.getHeadX() == snake.getX(i))&& (snake.getHeadY() == snake.getY(i))) {
				if((snake.getHeadX() != 0)&& (snake.getHeadY() != 0))//Override clash when (x,y)=(0,0), will be solved
				gameOver = true;
			}
		}
		if(gameOver==true) {
			clock.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.white);
		g.setFont( new Font(g.getFont().toString(),Font.BOLD, 50));
		FontMetrics scoreMetrics = getFontMetrics(g.getFont());
		g.drawString("Your final score: "+applesEaten, (MAP_WIDTH - scoreMetrics.stringWidth("Your final score: "+applesEaten))/2, MAP_HEIGHT/2-CELL_SIZE*3);
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font(g.getFont().toString(),Font.BOLD, 75));
		FontMetrics gameOverMetrics = getFontMetrics(g.getFont());
		g.drawString("Game Over!!", (MAP_WIDTH - gameOverMetrics.stringWidth("Game Over!!"))/2, MAP_HEIGHT/2);
		//Game exit text
		g.setColor(Color.white);
		g.setFont( new Font(g.getFont().toString(),Font.BOLD, 50));
		FontMetrics endGameMetrics = getFontMetrics(g.getFont());
		g.drawString("Press 'Esc' to exit", (MAP_WIDTH - endGameMetrics.stringWidth("Press 'Esc' to exit"))/2, MAP_HEIGHT/2+CELL_SIZE*2);
		//Restart button button listener
		this.add(restartButton);
		//End game key listener
		this.addKeyListener(new endGame());
	}
	
	@Override //override actionPerformed
	public void actionPerformed(ActionEvent e) {
		//Check for restart
		if(e.getSource()==restartButton) {
			this.remove(restartButton);
			this.restart();
			SwingUtilities.updateComponentTreeUI(this);
		  }
		else if(gameOver==false) {
			snake.moveSnake(direction);
			if(apple.checkApple(snake.getHeadX(),snake.getHeadY())==true){
				snake.eat();
				applesEaten++;
		} 
			checkClash();
		}
		repaint();
	}
	
	public void setDirection(char c){
		switch (c){
			case 'R':
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case 'L':
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case 'U':
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case 'D':
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			default:
				break;
		}
	}
	
	public class ChangeDirection extends KeyAdapter{//Used for snake movement
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				setDirection('L');
				break;
			case KeyEvent.VK_RIGHT:
				setDirection('R');
				break;
			case KeyEvent.VK_UP:
				setDirection('U');
				break;
			case KeyEvent.VK_DOWN:
				setDirection('D');
			break;
			}
		}
	}
	public class endGame extends KeyAdapter{//Used when game over
		@Override
		public void keyPressed(KeyEvent e) {

			if(e.getKeyCode()==KeyEvent.VK_ESCAPE && gameOver==true)//NEED TO FIX - WORKS ALL TIMES
				System.exit(0);
		}
	}
}