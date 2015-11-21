package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import un.Block;
import un.Time;

public class GameCanvas extends MyPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Time timer;
	public int[][] GameSceneArray = new int[38][18];
	public int[][] GameSceneArray2 = new int[38][18];
	public int row , col , row_length , col_length , score , score2;
	public Block block;
	public boolean fail;
	GameCanvas gamescene;

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
		fail = false;
		score = 0;
		score2 = 0;
		repaint();
	}
	
	GameCanvas(){
		gamescene = this;
		row = 32; 
		col = 16;
		block = new Block(this);
		init();
	}
	
	public void DrawBlock(int i , int j , int type , Graphics g){
		if( (i<0 && i>=row) || (j<0 && j>=col))
			return ;
		GameSceneArray[i][j] = type;
		switch(type){
		case 0:
			g.setColor(new Color(255,174,115));
			g.fill3DRect(j*col_length , i*row_length , col_length ,row_length , true);
			break;
		case 1:
			g.fill3DRect(j*col_length , i*row_length , col_length ,row_length , true);
			break;
		case 2:
			g.setColor(new Color(103,103,100));
			g.fill3DRect(j*col_length , i*row_length , col_length ,row_length , true);
			break;
		}
	}
	
	public void DrawBlock2(int i , int j , int type , Graphics g){
		if( (i<0 && i>=row) || (j<0 && j>=col))
			return ;
		GameSceneArray2[i][j] = type;
		System.out.println("???");
		switch(type){
		case 0:
			g.setColor(new Color(255,174,115));
			g.fill3DRect(j*col_length , (i+64)*row_length , col_length ,row_length , true);
			break;
		case 1:
			g.fill3DRect(j*col_length , (i+64)*row_length , col_length ,row_length , true);
			break;
		case 2:
			g.setColor(new Color(103,103,100));
			g.fill3DRect(j*col_length , (i+64)*row_length , col_length ,row_length , true);
			break;
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		row_length = (int)(getHeight()/row);
		col_length = (int)(getWidth()/col);
		
		for (int i = 0 ; i < row ; i++){
			for (int j = 0 ; j < col ; j++){
				DrawBlock(i , j , GameSceneArray[i][j] , g);
			}
		}
	}
	
	public void keyTyped(KeyEvent e){ 
		
	}
	public void keyReleased(KeyEvent e){

	}
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			block.change();
			System.out.println("get keycode up");
			break;
		case KeyEvent.VK_DOWN:
			block.down_m();
			System.out.println("get keycode down");
			break;
		case KeyEvent.VK_LEFT:
				block.left_m();
			System.out.println("get keycode left");
			break;
		case KeyEvent.VK_RIGHT:
			block.right_m();
			System.out.println("get keycode right");
			break;
		}
	}
	
	public void deletline(){
		for (int k = 0 ; k < row ; k++){
			int cnt = 0;
			for (int i = 0 ; i < col ; i++){
				if (GameSceneArray[k][i] == 2){
					cnt++;
				}
			}
			if (cnt == col){
				score += 100;
				
			}
			if (cnt == col){
				for (int i = k ; i > 0 ; i--){
					for (int j = 0 ; j < col ; j++){
						DrawBlock(i , j , GameSceneArray[i-1][j] , gamescene.getGraphics());
					}
				}
			}
		}
	}
	
	public int indextype(int x , int y){
		return GameSceneArray[x][y];
	}
	
	public boolean gameover(){
		for (int i = 0 ; i < col ; i++){
			if (GameSceneArray[0][i] == 2)
				return fail = true;
		}
		return fail = false;
	}
}
