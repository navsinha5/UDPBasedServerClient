package com.navdeep.udpClient;

import java.io.*;
import java.net.*;

public class UDPClient {

	public static void main(String[] args) {
		DatagramSocket socket = null;
		int port = 7777;
		String string;
		
		BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			socket = new DatagramSocket();
			InetAddress host = InetAddress.getByName("localhost");
			
			while(true){
				//take input and send packet
				echo("Enter message to send : ");
				string = (String)fromConsole.readLine();
				byte[] byteConsole = string.getBytes();
				
				DatagramPacket outgoing = new DatagramPacket(byteConsole, byteConsole.length, host, port);
				socket.send(outgoing);
				
				//receive reply
				//buffer to receive incoming data
				byte[] buffer = new byte[65536];
				
				DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
				socket.receive(incoming);
				
				byte[] data = incoming.getData();
				string = new String(data, 0, incoming.getLength());
				
				//echo the details of incoming data - client ip : client port - client message
				echo(incoming.getAddress().getHostAddress() + ":" + incoming.getPort() + "-" + string);
			}
		}
		catch(IOException e){
			System.err.println("IOException " + e);
		}

	}
	//echo function
	public static void echo(String msg){
		System.out.println(msg);
	}

}
