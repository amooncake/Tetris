package un;

import GUI.GameCanvas;
import GUI.PaintFrame;

public class Time extends Thread{
	GameCanvas gamescene;
	PaintFrame myframe;
	public Time(GameCanvas gsc , PaintFrame mfr){
		this.gamescene = gsc;
		this.myframe = mfr;
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		while(true){
			try {
				sleep(500);
				myframe.update();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!gamescene.block.down()){
				gamescene.deletline();
				if (gamescene.gameover()){
					gamescene.setFocusable(false);
					suspend();
					System.out.println("GameOver");
					//System.exit(0);;
				}
				else{
					gamescene.block.init();
				}
			}
		}
	}
}
