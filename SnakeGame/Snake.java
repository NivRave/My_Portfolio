public class Snake{
	private int x[];
	private int y[];
	private int snakeSize =10;	


	Snake(int x0, int y0)		
	{
		x=new int[SnakePanel.CELLS];
		y=new int[SnakePanel.CELLS];
		x[0]=x0;
		y[0]=y0;
	}
	
	public int getSize(){
		return snakeSize;
	}
	
	public int getX(int i){
		return x[i];
	}
	
	public int getY(int i){
		return y[i];
	}
	
	public int getHeadX(){
		return x[0];
	}
	
	public int getHeadY(){
		return y[0];
	}
	
	public void eat(){
		snakeSize++;
	}
	
	public void moveSnake(char d){
		for(int i = snakeSize-1;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(d) {
		case 'U':
			if(y[0] == 0) y[0] = SnakePanel.MAP_HEIGHT;
			else y[0] = y[0] - SnakePanel.CELL_SIZE;
			break;
		case 'D':
			if(y[0] == SnakePanel.MAP_HEIGHT) y[0]=0;
			else y[0] = y[0] + SnakePanel.CELL_SIZE;
			break;
		case 'L':
			if(x[0] == 0) x[0] = SnakePanel.MAP_WIDTH;
			else x[0] = x[0] - SnakePanel.CELL_SIZE;
			break;
		case 'R':
			if(x[0] == SnakePanel.MAP_WIDTH) x[0] = 0;
			else x[0] = x[0] + SnakePanel.CELL_SIZE;
			break;
		}		
	}
	
}