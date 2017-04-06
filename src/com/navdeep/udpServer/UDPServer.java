package com.navdeep.udpServer;

import java.io.*;
import java.net.*;

public class UDPServer {

	public static void main(String[] args) {
		
		DatagramSocket socket = null;
		
		try{
			//1. creating server socket, port 7777 as parameter
			socket = new DatagramSocket(7777);
			
			//buffer to receive data
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
			
			//2. wait for incoming data
			echo("server socket created, waiting for incoming data");
			
			//communication loop
			while(true){
				socket.receive(incoming);
				byte[] data = incoming.getData();
				String incomingStr = new String(data, 0, incoming.getLength());
				
				//echo the details of incoming data - client ip : client port - client message
				echo(incoming.getAddress().getHostAddress()+ ":" + incoming.getPort() + "-" + incomingStr);
				
				incomingStr = "OK :" + incomingStr;
				DatagramPacket outgoing = new DatagramPacket(incomingStr.getBytes(), incomingStr.getBytes().length, incoming.getAddress(), incoming.getPort());
				socket.send(outgoing);
			}
			
		}
		catch(IOException e){
			System.err.println("IOException" + e);
		}

	}
	//echo function
	public static void echo(String msg){
		System.out.println(msg);
	}

}
