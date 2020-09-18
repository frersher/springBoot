package com.shine.config.zk.watch;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author cb
 * @since 2020/9/18
 */

public class ZookeeperWatch implements  Watcher{
    private String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private ZooKeeper zooKeeper;
    /** 用于等待zookeeper连接建立之后 通知阻塞程序继续向下执行 */
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    /** 定义原子变量 */
    AtomicInteger seq = new AtomicInteger();

    public ZookeeperWatch(){
        try {
            zooKeeper = new ZooKeeper(connectString, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("watchedEvent "+watchedEvent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void delete(String path) throws KeeperException, InterruptedException {
        zooKeeper.delete(path,-1);
    }

//    public void getData(String path,Boolean watch) throws KeeperException, InterruptedException {
//        zooKeeper.getData(path, watch);
//    }



    public Boolean exists(String path,Boolean watch) throws KeeperException, InterruptedException {
       return zooKeeper.exists(path, watch) == null?false:true;
    }


    public String createPersistent(String path,String data){
        try {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String createEphemeral(String path,String data){
        try {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ZookeeperWatch zookeeperWatch = new ZookeeperWatch();
        try {
            if(zookeeperWatch.exists("/aj",true)){
                zookeeperWatch.delete("/aj");
            }
            zookeeperWatch.createEphemeral("/aj", "cbaj");

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("进入 process 。。。。。event = " + event);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (event == null) {
            return;
        }

        // 连接状态
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();
        // 受影响的path
        String path = event.getPath();
        // 原子对象seq 记录进入process的次数
        String logPrefix = "【Watcher-" + this.seq.incrementAndGet() + "】";

        System.out.println(logPrefix + "收到Watcher通知");
        System.out.println(logPrefix + "连接状态:\t" + keeperState.toString());
        System.out.println(logPrefix + "事件类型:\t" + eventType.toString());

        if (Event.KeeperState.SyncConnected == keeperState) {
            // 第一次成功连接上ZK服务器
            if (Event.EventType.None == eventType) {
                System.out.println(logPrefix + "成功连接上ZK服务器");
                connectedSemaphore.countDown();
            }
            //创建节点
            else if (Event.EventType.NodeCreated == eventType) {
                System.out.println(logPrefix + "节点创建");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //更新节点
            else if (Event.EventType.NodeDataChanged == eventType) {
                System.out.println(logPrefix + "节点数据更新");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //更新子节点
            else if (Event.EventType.NodeChildrenChanged == eventType) {
                System.out.println(logPrefix + "子节点变更");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //删除节点
            else if (Event.EventType.NodeDeleted == eventType) {
                System.out.println(logPrefix + "节点 " + path + " 被删除");
            }
            else ;
        }
        else if (Event.KeeperState.Disconnected == keeperState) {
            System.out.println(logPrefix + "与ZK服务器断开连接");
        }
        else if (Event.KeeperState.AuthFailed == keeperState) {
            System.out.println(logPrefix + "权限检查失败");
        }
        else if (Event.KeeperState.Expired == keeperState) {
            System.out.println(logPrefix + "会话失效");
        }
        else ;

        System.out.println("--------------------------------------------");
    }
}
