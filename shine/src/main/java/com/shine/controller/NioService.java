package com.shine.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioService {
    public  void init() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(8060),1024);
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            System.out.println("while...");
            selector.select(1000);
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            SelectionKey key = null;
            while (it.hasNext()) {
                key=it.next();
                it.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc  = (ServerSocketChannel) key.channel();
                    System.out.println("client connect......");
                    SocketChannel channel = ssc.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                    key.interestOps(SelectionKey.OP_ACCEPT);
                }
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    String content = "";
                    try {
                        int readBytes = channel.read(byteBuffer);
                        if (readBytes > 0) {
                            byteBuffer.flip(); //为write()准备
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            content+=new String(bytes);
                            System.out.println(content);
                            //回应客户端
                            doWrite(channel);
                        }
                        key.interestOps(SelectionKey.OP_READ);
                    } catch (IOException i) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            }
        }
    }
    private  void doWrite(SocketChannel sc) throws IOException{
        byte[] req ="server receive ".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
        byteBuffer.put(req);
        byteBuffer.flip();
        sc.write(byteBuffer);
        if(!byteBuffer.hasRemaining()){
            System.out.println("Send 2 Service successed");
        }
    }

    public static void main(String[] args) throws IOException {
        NioService nioService = new NioService();
        nioService.init();

    }
}
