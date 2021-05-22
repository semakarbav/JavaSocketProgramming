package javaSocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		Socket socket=null; //port ayar�n� verece�iz ba�ta null atar�z
		InputStreamReader inputStreamReader=null; //socketten gelen giri�leri okumak i�in kullan�lacak
		OutputStreamWriter outputStreamWriter=null; //ak��tan ��kan verileri okumak i�in kullan�lacak
		BufferedReader bufferedReader=null; //okunan de�erler arabelle�e at�lacak
		BufferedWriter bufferedWriter=null; //yazma i�lemi i�in kullan�lacak
		
		try {
			socket=new Socket("localhost",1235);  //haberle�menin sa�lanaca�� ip ve port
			inputStreamReader=new InputStreamReader(socket.getInputStream()); //socketten al�nan girdi verisi
			outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());//socketten al�nan ��k�� verisi
			
			bufferedReader=new BufferedReader(inputStreamReader); //girdi al�nan arabelle�e at�l�r,verimlili�i artt�rmak i�in
			bufferedWriter=new BufferedWriter(outputStreamWriter); //��kt� al�nan arabelle�e at�l�r,verimlili�i artt�rmak i�in
			
			Scanner scn=new Scanner(System.in); //consolden girdi almak i�in
			
			while(true) { //al�nan de�erleri d�nd�r d�ng�s�
				String message=scn.nextLine(); //consolden girdi almak i�in
				bufferedWriter.write(message); //belle�e al�nan mesaj� yazar
				bufferedWriter.newLine(); //mesaj�  g�nderdi�inde yeni sat�r
				//bufferedWriter.flush(); //bellek temizlenir
				
				System.out.println("Server : "+bufferedReader.readLine()); //verileri okumak i�in
				
				if(message.equalsIgnoreCase("Bye")) { //ileti�imi bitirmek i�in kullan�l�r
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally { //programda hata oldu�unda kapatmak i�in kullanaca��m�z bloklar� kapsar
			try {
				if(socket!=null) {
					socket.close();
				}
				if(inputStreamReader!=null) {
					inputStreamReader.close();
				}
				if(outputStreamWriter!=null) {
					outputStreamWriter.close();
				}
				if(bufferedReader!=null) {
					bufferedReader.close();
				}
				if(bufferedWriter!=null) {
					bufferedWriter.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
       

	}

}
