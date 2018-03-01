package com.transmittion;

import java.net.HttpURLConnection;
import java.net.URL;

public class Transceive {

	public static String parameter = "check";
	public static int code = 0;
	
	
	public Transceive() {
		
		Thread t = new Thread() {

			  @Override
			  public void run() {
			    try {
			      while (!isInterrupted()) {
			        read();
			        Thread.sleep(5000);
			      }
			    } catch (InterruptedException e) {
			    }
			  }
			};

			t.start();
	}
	
	
	public static void read()
	{
		
		
		 HttpURLConnection connection = null;
		
		try
		{
			URL u = new URL("http://192.168.1.101:80");
			connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("HEAD");
			code = connection.getResponseCode();
			System.out.println(""+code);				
		}
		catch(Exception e)
		{
			System.out.println("Error = " + e);
			code = 0;
		}
		finally{
			if(connection!=null)
			{
				connection.disconnect();
			}
		}
		
		
	}

	
	
}
