package com.myutility;



import java.io.IOException;
import java.net.*;

import static com.myutility.HexHelper.bytesToHex;
import static  com.myutility.HexHelper.hexStringToBytes;

public class UdpSender {
    
	public byte[] dataConvert(String data) {
        
        String msg = data;
       // if(msg == null) return;
        byte[] msgBytes = msg.getBytes();
        if (msg.startsWith("\\0x")) {
            msg = msg.replace("\\0x", "0x");
            msgBytes = msg.getBytes();
        } else if (msg.startsWith("0x")) {
            msg = msg.replace("0x", "");
            if(!msg.matches("[a-fA-F0-9]+")) {
                System.out.println("Error in HEX");
            	//return;
            }
            msgBytes = hexStringToBytes(msg);
        }

        final byte[] buf = msgBytes;

       
        
            //System.out.println("String : " + new String(msgBytes)+"");
           // System.out.println( "0x" + bytesToHex(msgBytes));
            
            return msgBytes;
        

       /* new Thread(new Runnable() {
            public void run() {
                try {
                    InetAddress serverAddress = InetAddress.getByName(uri
                            .getHost());
                    //Log.v(getString(R.string.app_name), serverAddress.getHostAddress());
                    DatagramSocket socket = new DatagramSocket();
                    if (!socket.getBroadcast()) socket.setBroadcast(true);
                    DatagramPacket packet = new DatagramPacket(buf, buf.length,
                            serverAddress, uri.getPort());
                    socket.send(packet);
                    socket.close();
                } catch (final UnknownHostException e) {
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                } catch (final SocketException e) {
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                } catch (final IOException e) {
                    toastHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();*/
    }
	
	
	public static void main(String avg[])
	{
		UdpSender us = new UdpSender();
	}
	
	
}
