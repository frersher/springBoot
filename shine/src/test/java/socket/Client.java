package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 *
 * @author wb-cb236432
 * @create 2018-07-05 20:15
 **/
public class Client {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost",9898);
            OutputStream os =  socket.getOutputStream();
            PrintWriter pw  = new PrintWriter(os);
            pw.write("我要进入了哟....");
            pw.flush();

            socket.shutdownOutput();
            //获取输入流  读取服务端响应信息
            InputStream is = socket.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = bf.readLine()) != null){
                System.out.println("我是服务器，客户端说： "+info);
            }


            //关闭资源
            pw.close();
            os.close();
            socket.close();
            bf.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
