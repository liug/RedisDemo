package com.redis.test;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.*;

public class RedisJava {

		public static void main(String[] args) {
			 Jedis jedis = new Jedis("localhost");
			 
			 System.out.println("Connection to server sucessfully");
		     //�鿴�����Ƿ�����
		     System.out.println("Server is running: "+jedis.ping());
		     //���ַ���
		     jedis.set("myKey", "����ֵ£�");
		     System.out.println(jedis.get("myKey"));
		     
		     //list
		     List<String> list = new ArrayList<String>();
		     list.add("list");
		     list.add("list1");
		     list.add("list2");
		     for (String string : list) {
		    	 jedis.lpush("tutorial-list", string);
		     }
		     //��ȡ�洢����
		     List<String> listCs = jedis.lrange("tutorial-list", 0 ,5);
		     for(int i=0; i<listCs.size(); i++) {
		       System.out.println("Stored string in redis:: "+listCs.get(i));
		     }
		     
		}
}
