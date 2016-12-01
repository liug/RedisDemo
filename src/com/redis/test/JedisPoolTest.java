package com.redis.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class JedisPoolTest {
	public static void main(String[] args) {
		Jedis jedis = RedisUtil.getJedis();
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user", map);
        
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);
        
        // ɾ��map�е�ĳ����ֵ
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); // ��Ϊɾ���ˣ����Է��ص���null
        System.out.println(jedis.hlen("user")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���2
        System.out.println(jedis.exists("user"));// �Ƿ����keyΪuser�ļ�¼ ����true
        System.out.println(jedis.hkeys("user"));// ����map�����е�����key
        System.out.println(jedis.hvals("user"));// ����map�����е�����value

        Iterator<String> iter = jedis.hkeys("user").iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
	}
}
