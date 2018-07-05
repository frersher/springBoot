package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.net.SocketServer;

/**
 * 服务器端、
 *
 * @author wb-cb236432
 * @create 2018-07-05 19:51
 **/
public class Server {
    public static void main(String[] args){
        try {
            //创建一个客户端
            ServerSocket serverSocket =new ServerSocket(9898);
            System.out.println("***服务器即将启动,等待客户端连接***");
            //调用accept方法监听，等待客户端连接
            Socket socket = serverSocket.accept();
            //获取输入流，读取客户端信息
            InputStream is = socket.getInputStream();
            InputStreamReader inputStreamReader =  new InputStreamReader(is);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String info = null;
            while((info = br.readLine()) != null){
                System.out.println("我是服务器，客户端说： "+info);
            }
            socket.shutdownInput();

            OutputStream os = socket.getOutputStream();
            PrintWriter printWriter =  new PrintWriter(os);
            printWriter.write("hello 123");
            printWriter.flush();

            //关闭资源
            is.close();
            br.close();
            inputStreamReader.close();
            socket.close();
            printWriter.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
