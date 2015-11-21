package un;

import java.awt.Color;
import java.awt.Graphics;

import GUI.GameCanvas;
import GUI.GameCanvas2;

public class Block {
	static int[][] pattern = {
			{ 0x0f00, 0x4444, 0x0f00, 0x4444 },// 用十六进至表示，本行表示长条四种状态
			{ 0x04e0, 0x0464, 0x00e4, 0x04c4 },
			{ 0x4620, 0x6c00, 0x4620, 0x6c00 },
			{ 0x2640, 0xc600, 0x2640, 0xc600 },
			{ 0x6220, 0x1700, 0x2230, 0x0740 },
			{ 0x6440, 0x0e20, 0x44c0, 0x8e00 },
			{ 0x0660, 0x0660, 0x0660, 0x0660 } 	
	};
	public int type , turn , init_x , init_y , x , y;
	boolean done;
	GameCanvas gamescene;
	GameCanvas2 gamescene2;
	Graphics block_color;
	
	public Block(GameCanvas gsc){
		gamescene = gsc;
		block_color = gamescene.getGraphics();
		init_x = 0;
		init_y = gamescene.col/2-2;
		init();
	}
	public Block(GameCanvas2 gsc){
		gamescene2 = gsc;
		block_color = gamescene2.getGraphics();
		init_x = 0;
		init_y = gamescene2.col/2-2;
		init();
	}
	
	public void init(){
		type = (int)(Math.random()*10000)%7;
		turn = (int)(Math.random()*10000)%4;
		done = false;
		x = init_x-4;
		y = init_y;
	}
	
	public void change(){
		block_color = gamescene.getGraphics();
		if(done)
			return ;
		if (canmove(0,0,1,(turn+1)%4)){
			DisBlock(0,block_color);
			turn = (turn+1)%4;
			DisBlock(1,block_color);
		}
		return ;
	}
	
	public void left_m(){
		block_color = gamescene.getGraphics();
		boolean flag = canmove(1,0,0,turn);
		if (flag){
			DisBlock(0,block_color);
			y--;
			DisBlock(1,block_color);
		}
		else{
			;
		}
	}
	
	public void right_m(){
		block_color = gamescene.getGraphics();
		if (!canmove(0,1,0,turn))
			return ;
		DisBlock(0,block_color);
		y++;
		DisBlock(1,block_color);
	}
	
	public void down_m(){
		block_color = gamescene.getGraphics();
		if (canmove(0,0,1,turn)){
			DisBlock(0,block_color);
			x++;
			DisBlock(1,block_color);
		}
		else{
			done = true;
			DisBlock(2,block_color);
		}
	} 
	
	public boolean down(){
		down_m();
		//System.out.println("!!!");
		return !done;
	}
	
	public boolean canmove(int l , int r , int d , int t){
		int judge = 0x8000;
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++){
				if (((int)(pattern[type][t]) & judge) != 0){
					if (x+i+d < 0)
						continue;
					if (x+i+d<gamescene.row && y+j-l>=0 && y+j+r<gamescene.col){
						int temp = gamescene.indextype(x+i+d, y+j-l+r);
						if ((temp == 2) || (temp < 0)){
							return false;
						}
					}
					else
						return false;
				}
				judge >>= 1;
			}
		}
		return true;
	}
	
	public synchronized void DisBlock(int t, Graphics g){
		int judge = 0x8000;
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++){
				if (((int)(pattern[type][turn]) & judge) != 0){
					if(x+i >= 0){
						switch(type){
						case 0:
							g.setColor(new Color(58,231,107));
							break;
						case 1:
							g.setColor(new Color(146,7,131));
							break;
						case 2:
							g.setColor(new Color(255,107,187));
							break;
						case 3:
							g.setColor(new Color(30,143,216));
							break;
						case 4:
							g.setColor(new Color(255,232,46));
							break;
						case 5:
							g.setColor(new Color(229,0,79));
							break;
						case 6:
							g.setColor(new Color(255,82,23));
							break;
							
						}
						gamescene.DrawBlock(x+i, y+j, t , g);
					}
				}
				judge >>= 1;
			}
		}
	}	
}
