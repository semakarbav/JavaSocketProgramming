package javaSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException{  //hata yönetimi
		Socket socket=null; //port ayarýný vereceðiz baþta null atarýz
		InputStreamReader inputStreamReader=null; //socketten gelen giriþleri okumak için kullanýlacak
		OutputStreamWriter outputStreamWriter=null; //akýþtan çýkan verileri okumak için kullanýlacak
		BufferedReader bufferedReader=null; //okunan deðerler arabelleðe atýlacak
		BufferedWriter bufferedWriter=null; //yazma iþlemi için kullanýlacak
		ServerSocket serverSocket=null; //istekleri bekler,port numarasýný bekler
		
		serverSocket=new ServerSocket(1235);
		
		while(true) {
			try {
				socket=serverSocket.accept();
				
				inputStreamReader=new InputStreamReader(socket.getInputStream()); //socketten alýnan girdi verisi
				outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());//socketten alýnan çýkýþ verisi
				
				bufferedReader=new BufferedReader(inputStreamReader); //girdi alýnan arabelleðe atýlýr,verimliliði arttýrmak için
				bufferedWriter=new BufferedWriter(outputStreamWriter); //çýktý alýnan arabelleðe atýlýr,verimliliði arttýrmak için
				
				while(true) { //alýnan deðerleri döndür döngüsü
					String msgClient=bufferedReader.readLine(); //consolden girdi almak için
					
					System.out.println("Client : "+msgClient);
					
					bufferedWriter.write("Message recieved"); //belleðe alýnan mesajý yazar
					bufferedWriter.newLine(); //mesajý  gönderdiðinde yeni satýr
					bufferedWriter.flush(); //bellek temizlenir
					
					if(msgClient.equalsIgnoreCase("Bye")) { //iletiþimi bitirmek için kullanýlýr
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
