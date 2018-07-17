package socket;

import java.net.Socket;

/**
 * 服务器线程处理类
 *
 * @author wb-cb236432
 * @create 2018-07-06 17:07
 **/
public class ServerThread extends Thread {
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
}
