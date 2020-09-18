package com.shine.config.zk.znode;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * TODO
 *
 * @author cb
 * @since 2020/9/18
 */

public class ZookeeperCrud {
    private String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private ZooKeeper zooKeeper;

    public ZookeeperCrud(){
        try {
            zooKeeper = new ZooKeeper(connectString, 5000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ZookeeperCrud zookeeperCrud = new ZookeeperCrud();
        zookeeperCrud.createEphemeral("/laj","cbalaj");
    }




}
