package ss;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static ServerSocket serverSocket;
	static int[][] array1;
	static int[][] array2;
	static int score1 , score2;
	Socket[] socket = new Socket[2];
	boolean flag = false;
	static int num;
	static DataInputStream[] in = new DataInputStream[2];
	static DataOutputStream[] out = new DataOutputStream[2];
	static int id;
	boolean update1 , update2;
	int P1_gameover , P2_gameover;
	
	public Server() throws IOException{
		id = -1;
		array1 = new int[32][16];
		array2 = new int[32][16];
		update1 = update2 = false;
		P1_gameover = P2_gameover = 0;
		System.out.println("Server start...");
		serverSocket = new ServerSocket(8888);
		new AcceptSocketThread().start();
	}
	
	
	
	class AcceptSocketThread extends Thread{
		public void run(){
			while(this.isAlive()){
				try{
					id++;
					socket[id] = serverSocket.accept();
					if (socket != null){	
						in[id] = new DataInputStream(socket[id].getInputStream());
						out[id] = new DataOutputStream(socket[id].getOutputStream());		
						new GetMsgFromClient(in[id],out[id],id).start();
					}
				} catch(IOException e){
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public class GetMsgFromClient extends Thread{
		DataInputStream in;
		DataOutputStream out;
		int id;
		public GetMsgFromClient(DataInputStream in , DataOutputStream out , int id){
			this.in = in;
			this.out = out;
			this.id = id;
		}
		
		public void run(){
			while(this.isAlive()){
				try{
					if (id == 0){
						P1_gameover = in.readInt();
						score1 = in.readInt();
						out.writeInt(P2_gameover);
						out.writeInt(score2);
						for (int i = 0 ; i < 32 ; i ++){
							for (int j = 0 ; j < 16 ; j++){
								array1[i][j] = in.readInt();
								update1 = true;
								out.writeInt(array2[i][j]);
								out.flush();
							}
						}
					} 
					else if (id == 1){
						P2_gameover = in.readInt();
						score2 = in.readInt();
						out.writeInt(P1_gameover);
						out.writeInt(score1);
						for (int i = 0 ; i < 32 ; i ++){
							for (int j = 0 ; j < 16 ; j++){
								array2[i][j] = in.readInt();
								update2 = true;
								out.writeInt(array1[i][j]);
								out.flush();
							}
						}
					}
					else{
						System.out.println("Break");
					}
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws IOException{
		new Server();
	}
}
