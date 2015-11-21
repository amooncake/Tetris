package GUI;

import java.awt.Color;
import java.awt.Graphics;
import un.Block;

public class GameCanvas2 extends MyPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int[][] GameSceneArray = new int[38][18];
	public int[][] GameSceneArray2 = new int[38][18];
	public int row , col , row_length , col_length , score;
	public Block block;
	GameCanvas2 gamescene;
	Graphics ggg;

	public void init(){
		for (int i = 0 ; i < row+6 ; i++){
			for (int j = 0 ; j < col+2 ; j++){
				GameSceneArray[i][j] = GameSceneArray2[i][j] = -1;
			}
		}
		for (int i = 0 ; i < row ; i++){
			for (int j = 0 ; j < col ; j++){
				GameSceneArray[i][j] = GameSceneArray2[i][j] = 0;
			}
		}
		for (int i = 0 ; i < col ; i++){
			GameSceneArray[row][i] = GameSceneArray2[row][i] = -1;
		}
		block.init();
	}
	
	GameCanvas2(){
		gamescene = this;
		row = 32; 
		col = 16;
		block = new Block(this);
		init();
	}
	
	public void DrawBlock(int i , int j , int type , Graphics gg){
		if( (i<0 && i>=row) || (j<0 && j>=col))
			return ;
		
		GameSceneArray[i][j] = type;
		switch(type){
		case 0:
			gg.setColor(new Color(255,174,115));
			gg.fill3DRect(j*col_length , i*row_length , col_length ,row_length , true);
			break;
		case 1:
			gg.setColor(Color.CYAN);
			gg.fill3DRect(j*col_length , i*row_length , col_length ,row_length , true);
			break;
		case 2:
			gg.setColor(new Color(103,103,100));
			gg.fill3DRect(j*col_length , i*row_length , col_length ,row_length , true);
			break;
		}
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		ggg = g;
		if(ggg == null)
			System.out.println("ggg == null");
		row_length = (int)(getHeight()/row);
		col_length = (int)(getWidth()/col);
		
		for (int i = 0 ; i < row ; i++){
			for (int j = 0 ; j < col ; j++){
				DrawBlock(i , j , GameSceneArray[i][j] , g);
			}
		}
	}
	
	public boolean gameover(){
		for (int i = 0 ; i < col ; i++){
			if (GameSceneArray[0][i] == 2)
				return true;
		}
		return false;
	}
}
