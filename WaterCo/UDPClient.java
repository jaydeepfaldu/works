package com.transmittion;
import java.io.*;
import java.net.*;

import com.myutility.KeywordStore;

import android.util.Log;

public class UDPClient
{
	
	public UDPClient()
	{
		try
		{
			Thread t = new Thread() {

				  @Override
				  public void run() {
				    try {
				      while (!isInterrupted()) {
				    	
				    	 // SendUDP(false);
				    	  send(KeywordStore.ip,KeywordStore.port,"0000");	
				    	
				        Thread.sleep(6000);
				        
				        
				        
				      }
				    } catch (InterruptedException e) {
				    }
				  }
				};

				//t.start();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public  void send(String ip, int port,String pin) 
	{
		
		DatagramSocket clientSocket = null;
		try
		{
		  clientSocket = new DatagramSocket(port);
	      InetAddress IPAddress = InetAddress.getByName(ip);
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	      String sentence = pin;//inFromUser.readLine();
	      sendData = sentence.getBytes();
	      clientSocket.setSoTimeout(3000);
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	      clientSocket.send(sendPacket);
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,IPAddress, port);	      
	      clientSocket.receive(receivePacket);
	      String modifiedSentence = new String(receivePacket.getData());
	      KeywordStore.receivemsg = modifiedSentence;
	      KeywordStore.status = 200;
	      Log.d("FROM SERVER:" , modifiedSentence );
	      Log.d("LENGTH ", ""+receivePacket.getData().length);
	      
	      clientSocket.close();
		}
		catch(Exception e)
		{
			if(clientSocket!=null)
			clientSocket.close();
			
			KeywordStore.status = 0;
			KeywordStore.receivemsg = "0";
			System.out.println("ERROR = " + e);
			
		}
		
	}
	
	
   public static void main(String args[]) throws Exception
   {
    //  BufferedReader inFromUser =
     //    new BufferedReader(new InputStreamReader(System.in));
	   
	  
   }
}