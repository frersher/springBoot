package com.shine.annotation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerSocketTest {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8090);
		System.out.println("step1:new ServerSocket(80)");
		while (true){
			Socket client = server.accept();
			System.out.println("step2:client\t"+client.getPort());
			new Thread(() ->{
				try {
					InputStream in = client.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					while (true){
						System.out.println(reader.readLine());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}).start();
		}
	}
}