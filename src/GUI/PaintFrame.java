package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import un.Time;


public class PaintFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameCanvas gamescene;
	GameCanvas2 gamescene2;
	public boolean pause_;
	public static Time timer;

	ImageIcon btn4_image = new ImageIcon("resources\\btn4_image.png");
	JLabel btn4 = new JLabel(btn4_image);
	//
	JLabel btn7 = new JLabel("dasdsa");
	JLabel btn6 = new JLabel("Score: ");
	//
	Socket socket;
	DataOutputStream toServer;
	DataInputStream fromServer;
	public static String ip;
	static int score1 , score2;
	static int[][] array1 = new int[32][16];
	static int[][] array2 = new int[32][16];
	public int P1_gameover;
	public int P2_gameover;
	
	public static void main(String[] args){
		PaintFrame myframe = new PaintFrame("Tetris");
		myframe.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	PaintFrame(String title){
		super(title);
		setBounds(200,30,326*3,679);
		//setBounds(200,30,320*3,640);
		setLayout(new GridLayout(1,3));
		this.getSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamescene = new GameCanvas();
		gamescene.addKeyListener(gamescene);
		gamescene2 = new GameCanvas2();
		timer = new Time(gamescene , this);
		timer.setDaemon(true);
		timer.start();
		timer.suspend();
		pause_ = true;
		P1_gameover = 0;
		P2_gameover = 0;
		
		add(gamescene);
		
		MyPanel right = new MyPanel();
		right.setBackground(new Color(0,0,0,0));
		
		
		ImageIcon btn_image = new ImageIcon("resources\\btn_image.png");
		JButton btn = new JButton(btn_image);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setMargin(new Insets(0,0,0,0));
		btn.setBorder(null);
		btn.setSize(btn_image.getIconWidth(), btn_image.getIconHeight());
		//btn.addActionListener();
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gamescene.init();
				timer.resume();
				pause_ = false;
				gamescene.setFocusable(true);
				gamescene.requestFocus(true);
			}
		});
		
		btn.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				btn.setIcon(new ImageIcon("resources\\btn_enter.png"));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btn.setIcon(new ImageIcon("resources\\btn_image.png"));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btn.setIcon(new ImageIcon("resources\\btn_pressed.png"));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		right.add(btn);
		
		ImageIcon btn2_image = new ImageIcon("resources\\btn2_image.png");
		JButton btn2 = new JButton(btn2_image);
		btn2.setOpaque(false);
		btn2.setContentAreaFilled(false);
		btn2.setBorderPainted(false);
		btn2.setFocusPainted(false);
		btn2.setMargin(new Insets(0,0,0,0));
		btn2.setBorder(null);
		btn2.setSize(btn_image.getIconWidth(), btn_image.getIconHeight());
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (pause_){
					timer.resume();
					gamescene.requestFocus(true);				
					pause_ = false;
				}
				else{
					timer.suspend();
	
					pause_ = true;
				}
				}
				});

		btn2.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (pause_)
					btn2.setIcon(new ImageIcon("resources\\resume_enter.png"));
				else
					btn2.setIcon(new ImageIcon("resources\\btn2_enter.png"));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				if (pause_)
					btn2.setIcon(new ImageIcon("resources\\resume_image.png"));
				else
					btn2.setIcon(new ImageIcon("resources\\btn2_image.png"));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (pause_){
					btn2.setIcon(new ImageIcon("resources\\resume_pressed.png"));

				}
				else{
					btn2.setIcon(new ImageIcon("resources\\btn2_pressed.png"));

				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (pause_){
					btn2.setIcon(new ImageIcon("resources\\resume_pressed.png"));

				}
				else{
					btn2.setIcon(new ImageIcon("resources\\btn2_pressed.png"));

				}
				
			}
			
		});
		right.add(btn2);
		
		ImageIcon btn3_image = new ImageIcon("resources\\btn3_image.png");
		JButton btn3 = new JButton(btn3_image);
		btn3.setOpaque(false);
		btn3.setContentAreaFilled(false);
		btn3.setBorderPainted(false);
		btn3.setFocusPainted(false);
		btn3.setMargin(new Insets(0,0,0,0));
		btn3.setBorder(null);
		btn3.setSize(btn_image.getIconWidth(), btn_image.getIconHeight());
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
				}
		});
		btn3.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				btn3.setIcon(new ImageIcon("resources\\btn3_enter.png"));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				btn3.setIcon(new ImageIcon("resources\\btn3_image.png"));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btn3.setIcon(new ImageIcon("resources\\btn3_pressed.png"));
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		right.add(btn3);
		
		ImageIcon btn5_image = new ImageIcon("resources\\score_image.png");
		JLabel btn5 = new JLabel(btn5_image);
		
		right.add(btn5);
		
		//
		right.add(btn6);
		
		right.add(btn7);
		
		
		try{
			socket = new Socket("127.0.0.1" , 8888);
			toServer = new DataOutputStream(socket.getOutputStream());
			fromServer = new DataInputStream(socket.getInputStream());
		} catch(IOException e){
			e.printStackTrace();
		}
		
		new GetMsgFromServer().start();
		
		update();
		
		right.add(btn4);
		right.setLayout(new GridLayout(1,2));
		add(right);
		add(gamescene2);
			
		this.getLayeredPane().setLayout(null);
		}
	
	public class GetMsgFromServer extends Thread{
		public void run(){
			while(this.isAlive()){
				try{
					if (gamescene.fail)
						P1_gameover = 1;
					
					score1 = gamescene.score;
					toServer.writeInt(P1_gameover);
					toServer.writeInt(score1);
					for (int i = 0 ; i < 32 ; i++){
						for (int j = 0 ; j < 16 ; j++){
							toServer.writeInt(gamescene.GameSceneArray[i][j]);												
							toServer.flush();
						}
					}
					P2_gameover = fromServer.readInt();
					score2 = gamescene.score2 = fromServer.readInt();
					for (int i = 0 ; i < 32 ; i++){
						for (int j = 0 ; j < 16 ; j++){
							gamescene2.GameSceneArray[i][j] = fromServer.readInt();
						}
					}
					PaintFrame.this.gamescene2.repaint();
					btn7.setText(Integer.toString(score2));
					
					if (P1_gameover == 1 && P2_gameover == 1){						
						if (score1 > score2){
							btn6.setText("Win");							
						}
						else if (score2 > score1){
							btn6.setText("Lose");
						}
						else{
							btn6.setText("Draw");
						}
					}
					Thread.sleep(5);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update(){ 
		btn4.setText(Integer.toString(gamescene.score));
		btn4.setForeground(Color.BLUE);
		btn4.setHorizontalTextPosition(JLabel.CENTER);
		btn4.setFont(new Font(null,0,20));
	}
	class WinListener extends WindowAdapter {
		public void windowClosing(WindowEvent l) {
			System.exit(0);
		}
	}
}
