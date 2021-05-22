package javaSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException{  //hata y�netimi
		Socket socket=null; //port ayar�n� verece�iz ba�ta null atar�z
		InputStreamReader inputStreamReader=null; //socketten gelen giri�leri okumak i�in kullan�lacak
		OutputStreamWriter outputStreamWriter=null; //ak��tan ��kan verileri okumak i�in kullan�lacak
		BufferedReader bufferedReader=null; //okunan de�erler arabelle�e at�lacak
		BufferedWriter bufferedWriter=null; //yazma i�lemi i�in kullan�lacak
		ServerSocket serverSocket=null; //istekleri bekler,port numaras�n� bekler
		
		serverSocket=new ServerSocket(1235);
		
		while(true) {
			try {
				socket=serverSocket.accept();
				
				inputStreamReader=new InputStreamReader(socket.getInputStream()); //socketten al�nan girdi verisi
				outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());//socketten al�nan ��k�� verisi
				
				bufferedReader=new BufferedReader(inputStreamReader); //girdi al�nan arabelle�e at�l�r,verimlili�i artt�rmak i�in
				bufferedWriter=new BufferedWriter(outputStreamWriter); //��kt� al�nan arabelle�e at�l�r,verimlili�i artt�rmak i�in
				
				while(true) { //al�nan de�erleri d�nd�r d�ng�s�
					String msgClient=bufferedReader.readLine(); //consolden girdi almak i�in
					
					System.out.println("Client : "+msgClient);
					
					bufferedWriter.write("Message recieved"); //belle�e al�nan mesaj� yazar
					bufferedWriter.newLine(); //mesaj�  g�nderdi�inde yeni sat�r
					bufferedWriter.flush(); //bellek temizlenir
					
					if(msgClient.equalsIgnoreCase("Bye")) { //ileti�imi bitirmek i�in kullan�l�r
						break;
					}
					
					
				}	
				socket.close();
				inputStreamReader.close();
				outputStreamWriter.close();
				bufferedReader.close();
				bufferedWriter.close();
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
