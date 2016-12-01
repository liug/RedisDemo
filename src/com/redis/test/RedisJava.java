package com.redis.test;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.*;

public class RedisJava {

		public static void main(String[] args) {
			 Jedis jedis = new Jedis("localhost");
			 
			 System.out.println("Connection to server sucessfully");
		     //查看服务是否运行
		     System.out.println("Server is running: "+jedis.ping());
		     //单字符串
		     jedis.set("myKey", "哈喽沃德！");
		     System.out.println(jedis.get("myKey"));
		     
		     //list
		     List<String> list = new ArrayList<String>();
		     list.add("list");
		     list.add("list1");
		     list.add("list2");
		     for (String string : list) {
		    	 jedis.lpush("tutorial-list", string);
		     }
		     //获取存储数据
		     List<String> listCs = jedis.lrange("tutorial-list", 0 ,5);
		     for(int i=0; i<listCs.size(); i++) {
		       System.out.println("Stored string in redis:: "+listCs.get(i));
		     }
		     
		}
}
