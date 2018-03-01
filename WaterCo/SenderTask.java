package com.transmittion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.KeyStore;

import javax.crypto.KeyAgreement;

import com.myutility.HexHelper;
import com.myutility.KeywordStore;
import com.myutility.UdpSender;


import android.content.res.Resources.Theme;
import android.os.AsyncTask;
import android.util.Log;

public class SenderTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    String str;
    
    protected String doInBackground(String... urls) {
      
    	
    	for(int i=1;i<=20;i++)
    	{
    		if(i==1)
    		{
    			str = KeywordStore.command;    			   			
    			KeywordStore.command = "";
    		}
    		else if(str.equals(""))
    		{
    			return "";
    		}
    		   		
    		method();
    		
    	}  	
	            	
    	return "";
    }

    
    

    public void method()
	{
    	
    		KeywordStore.EXECUTE_RUNNING = true;
    	
    	   
    		
    		
		 		DatagramSocket clientSocket = null;
	    		try
	    		{	    			    		
	    		  clientSocket = new DatagramSocket(2000);
	    	      InetAddress IPAddress = InetAddress.getByName(KeywordStore.ip);
	    	      
	    	        byte[] sendData = new byte[1024];    	      
	    	   
	    	        sendData = new com.myutility.UdpSender().dataConvert(str);	    	       
	    	        
	    	        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, KeywordStore.port);
	    	        clientSocket.send(sendPacket);	    
	    	
	    	      
	    	        
	    	      byte[] receiveData = new byte[1024];
	    	      
	    	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	    	      clientSocket.setSoTimeout(40);
	    	      clientSocket.receive(receivePacket);
	    	      
	    	      
	    	      String modifiedSentence = new HexHelper().bytesToHex(receivePacket.getData());
	    	      	
	    	      
	    	       str = "";
	
	    	       modifiedSentence =  modifiedSentence.substring(0,modifiedSentence.indexOf("232323"));
	    	       KeywordStore.receivemsg = modifiedSentence;
	    	     	      	
	    	       	Log.d("RECEIVER : ",KeywordStore.receivemsg);
	    	        
	    	        if(KeywordStore.receivemsg.length() > 5)
	    	        {
	    	        	String cnt = KeywordStore.receivemsg.charAt(20)+""+KeywordStore.receivemsg.charAt(21);
	    	        	//KeywordStore.PreReceiveCounter1 = KeywordStore.receivemsg.charAt(KeywordStore.receivemsg.length()-8)+""+KeywordStore.receivemsg.charAt(KeywordStore.receivemsg.length()-7);
	    	        	KeywordStore.PreReceiveCounter1 = KeywordStore.receivemsg.charAt(10)+""+KeywordStore.receivemsg.charAt(11);
	    	        	
	    	        //	Log.d("DATA : "," MC : " + KeywordStore.msgCounter +"  PC1 : "+ KeywordStore.PreReceiveCounter1 + "  PC2 : "+ KeywordStore.PreReceiveCounter2 );
	 	    	       
	    	        	if(KeywordStore.PreReceiveCounter1.equals(KeywordStore.PreReceiveCounter2))
	    	        	{
	    	        		
	    	        	}
	    	        	else
	    	        	{  	        			    	        		
	    	        		
	    	        		    String ss  = "0x250x250x250x000x000x"+cnt+KeywordStore.myip+"0x"+KeywordStore.PreReceiveCounter1+"0x000x000x000x000x010x520x230x230x23";
	    	        		
	    	        		
	    	        		
	    	        		 	byte[] sendACK = new byte[1024];    	      
	    	  	    	   
	    		    	        sendACK = new com.myutility.UdpSender().dataConvert(ss);	    	       
	    		    	        
	    		    	        DatagramPacket sendack = new DatagramPacket(sendACK, sendACK.length, IPAddress, KeywordStore.port);
	    		    	        clientSocket.send(sendack);	    		    	        
	    		    	       
	    		    	       
	    	        		
	    	        		
	    	        		KeywordStore.update = true;
	    	        		
	    	        		

	    	        		 //KeywordStore.command =  "0x250x250x250x000x000x"+KeywordStore.PreReceiveCounter2+KeywordStore.myip+"0x"+KeywordStore.PreReceiveCounter1+"0x000x000x000x000x010x520x230x230x23";	
		    	        	
	    	        		
	    	        	}
	    	        	
	    	        	
	        		     	      
	  	    	        
		    	     
	    	        	KeywordStore.PreReceiveCounter2 = KeywordStore.PreReceiveCounter1;  
	    	        	
	    	        	
	    	        	
	    	        }   
	    	      
	    	      clientSocket.close();
	    		}
	    		catch(Exception e)
	    		{
	    			if(clientSocket!=null)
	    			clientSocket.close();   	    			
	    		
	    			
	    			//Log.e("ERROR : ", e+"");  	    			
	    		}
	    		KeywordStore.EXECUTE_RUNNING = false;
    			
	}
	
    
    
    
    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}