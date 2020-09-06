package com.atguigu.demo;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mqx
 * @date 2020-9-4 15:41:32
 */
public class TestRedis {
    // 连接redis 客户端

    public static void main(String[] args) {
        // 创建Jedis
        Jedis jedis = new Jedis("192.168.200.166", 6379);

        String ping = jedis.ping();
        System.out.println(ping+"\t 哈哈 ");
    }

    /*
    String
    List
    Hash
    Set
    ZSet
     */
    Jedis jedis = null;
    @Before
    public void init(){
        // 获取连接
        System.out.println("初始化");
        jedis = new Jedis("192.168.200.166", 6379);
    }

    @After
    public void closeRedis(){
        System.out.println("关闭redis");
        if (null!= jedis){
            jedis.close();
        }
    }

    // String
    @Test
    public void testStr(){
        jedis.set("atguigu","168888");
        System.out.println(jedis.get("atguigu"));
    }

    // List
    @Test
    public void testList(){
        jedis.lpush("l1","v1","v2","v3");
        System.out.println(jedis.lrange("l1",0,-1));
    }

    // Set
    @Test
    public void testSet(){
        jedis.sadd("s1","zs","ls","cl");
//        jedis.sadd("s2","666");
//        jedis.sadd("s2","666");
//        jedis.sadd("s2","666");
//        jedis.sadd("s2","666");

        System.out.println(jedis.smembers("s1"));
    }

    // Hash
    @Test
    public void testHash(){
        jedis.hset("h1","name","admin");
        jedis.hset("h1","age","18");
        jedis.hset("h1","addr","beijing");

        System.out.println(jedis.hvals("h1"));

    }

    // ZSet
    @Test
    public void testZset(){
        jedis.zadd("z1",60,"zs");
        jedis.zadd("z1",70,"lisi");
        jedis.zadd("z1",80,"admin");

        System.out.println(jedis.zrange("z1",0,-1));

    }

}
