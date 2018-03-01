package com.transmittion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.myutility.KeywordStore;

import android.os.AsyncTask;
import android.util.Config;
import android.util.Log;

public class ReceiverTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
    	 try {
             
         	//KeywordStore.lastrun = true;
         	
         	//DatagramSocket clientSocket = null;
         	DatagramSocket clientSocket = null;
     		try
     		{
     		  //KeywordStore.status = 50;
     		  KeywordStore.EXECUTE_RUNNING = true;
     		  clientSocket = new DatagramSocket(KeywordStore.port);
     	      InetAddress IPAddress = InetAddress.getByName(KeywordStore.ip);
     	      if(KeywordStore.EXECUTE_CMD)
     	      {
     	        byte[] sendData = new byte[1024];    	      
     	        String sentence = KeywordStore.command;//inFromUser.readLine();
     	        sendData = sentence.getBytes();    	      
     	        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, KeywordStore.port);
     	        clientSocket.send(sendPacket);
     	         	        
     	        //KeywordStore.receivemsg = "CALLLLLLL";
     	        KeywordStore.EXECUTE_CMD = false;
     	      //  KeywordStore.command = "";
     	      }
     	      else
     	      {
     	      byte[] receiveData = new byte[1024];
     	      clientSocket.setSoTimeout(500);
     	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,IPAddress, KeywordStore.port);	      
     	      clientSocket.receive(receivePacket);
     	      String modifiedSentence = new String(receivePacket.getData());
     	      KeywordStore.receivemsg = modifiedSentence;
     	     // KeywordStore.status = 200;
     	     // Log.d("FROM SERVER:" , modifiedSentence );
     	    //  Log.d("LENGTH ", ""+receivePacket.getData().length);
     	      
     	      }
     	      
     	      KeywordStore.EXECUTE_RUNNING = false;
     	      
     	      clientSocket.close();
     		}
     		catch(Exception e)
     		{
     			if(clientSocket!=null)
     			clientSocket.close();    			
     			KeywordStore.receivemsg = "ERROR_SEND";
     			//KeywordStore.receivemsg = "0";
     			//System.out.println("ERROR = " + e);
     			KeywordStore.EXECUTE_RUNNING = false;
     			
     		}

             return "";
         } catch (Exception e) {
             this.exception = e;

             return null;
         }
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}