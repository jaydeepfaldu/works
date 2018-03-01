package com.transmittion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.myutility.KeywordStore;

import android.os.AsyncTask;
import android.util.Log;

public class CompleteTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
        try {
        	
                   	
        	DatagramSocket clientSocket = null;
    		try
    		{  
    		
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
    	      }    	      
    	    
    	      clientSocket.setSoTimeout(2000);
    	      byte[] receiveData = new byte[1024];
    	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length,  IPAddress, KeywordStore.port);	      
    	      clientSocket.receive(receivePacket);
    	      String modifiedSentence = new String(receivePacket.getData());
    	      KeywordStore.receivemsg = modifiedSentence;
    	      
    	      Log.d("FROM SERVER:" , modifiedSentence );
    	      
    	      clientSocket.close();
    	      
    	      KeywordStore.EXECUTE_RUNNING = false;
    	      KeywordStore.EXECUTE_CMD = false;
    	      
    		}
    		catch(Exception e)
    		{
    			if(clientSocket!=null)
    			clientSocket.close();    			
    			
    			KeywordStore.EXECUTE_RUNNING = false;
    			System.out.println("Error : " + e);
    			KeywordStore.EXECUTE_CMD = false;
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