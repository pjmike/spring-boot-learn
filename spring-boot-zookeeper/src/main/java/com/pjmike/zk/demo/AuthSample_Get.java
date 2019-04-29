package com.pjmike.zk.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @description:
 * @author: 13572
 * @create: 2019/04/29 15:37
 */
public class AuthSample_Get {
    final static String PATH = "/zk-book-auth_test";
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper1 = new ZooKeeper("39.106.63.214:2181", 5000, null);
        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        zooKeeper1.create(PATH, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper2 = new ZooKeeper("39.106.63.214:2181", 5000, null);
        zooKeeper2.addAuthInfo("digest", "foo:true".getBytes());
        System.out.println(zooKeeper2.getData(PATH, false, null));

        ZooKeeper zooKeeper3 = new ZooKeeper("39.106.63.214:2181", 5000, null);
        zooKeeper2.addAuthInfo("digest", "foo:false".getBytes());
        zooKeeper3.getData(PATH, false,null);

    }
}
