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
		
		Socket socket=null; //port ayarýný vereceðiz baþta null atarýz
		InputStreamReader inputStreamReader=null; //socketten gelen giriþleri okumak için kullanýlacak
		OutputStreamWriter outputStreamWriter=null; //akýþtan çýkan verileri okumak için kullanýlacak
		BufferedReader bufferedReader=null; //okunan deðerler arabelleðe atýlacak
		BufferedWriter bufferedWriter=null; //yazma iþlemi için kullanýlacak
		
		try {
			socket=new Socket("localhost",1235);  //haberleþmenin saðlanacaðý ip ve port
			inputStreamReader=new InputStreamReader(socket.getInputStream()); //socketten alýnan girdi verisi
			outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());//socketten alýnan çýkýþ verisi
			
			bufferedReader=new BufferedReader(inputStreamReader); //girdi alýnan arabelleðe atýlýr,verimliliði arttýrmak için
			bufferedWriter=new BufferedWriter(outputStreamWriter); //çýktý alýnan arabelleðe atýlýr,verimliliði arttýrmak için
			
			Scanner scn=new Scanner(System.in); //consolden girdi almak için
			
			while(true) { //alýnan deðerleri döndür döngüsü
				String message=scn.nextLine(); //consolden girdi almak için
				bufferedWriter.write(message); //belleðe alýnan mesajý yazar
				bufferedWriter.newLine(); //mesajý  gönderdiðinde yeni satýr
				//bufferedWriter.flush(); //bellek temizlenir
				
				System.out.println("Server : "+bufferedReader.readLine()); //verileri okumak için
				
				if(message.equalsIgnoreCase("Bye")) { //iletiþimi bitirmek için kullanýlýr
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally { //programda hata olduðunda kapatmak için kullanacaðýmýz bloklarý kapsar
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
