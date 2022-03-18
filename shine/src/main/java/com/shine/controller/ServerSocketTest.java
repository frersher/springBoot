package com.shine.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;


public class ServerSocketTest {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8090);
        System.out.println("step1:new ServerSocket(8090)");
        while (true) {
            Socket client = server.accept();
            System.out.println("step2:client\t" + client.getPort());
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        System.out.println(reader.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

}

