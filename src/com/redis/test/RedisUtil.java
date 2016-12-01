package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
	private static JedisPool jedisPool = null;
    // Redis������IP
    private static String ADDR = "127.0.0.1";
    // Redis�Ķ˿ں�
    private static int PORT = 6379;
    // ��������
    private static String AUTH = null;

    /**
     * ��ʼ��Redis���ӳ�
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            // ���Ӻľ�ʱ�Ƿ�����, false���쳣,ture����ֱ����ʱ, Ĭ��true
            config.setBlockWhenExhausted(true);
            // ���õ������������, Ĭ��DefaultEvictionPolicy(�����ӳ���������ʱ��,������������������������)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            // �Ƿ�����pool��jmx������, Ĭ��true
            config.setJmxEnabled(true);
            // ������������, Ĭ��8�� ����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
            config.setMaxIdle(8);
            // ���������, Ĭ��8��
            config.setMaxTotal(200);
            // ��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
            config.setMaxWaitMillis(1000 * 100);
            // ��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, ADDR, PORT, 3000, AUTH);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡJedisʵ��
     * 
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * �ͷ�jedis��Դ
     * 
     * @param jedis
     */
    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
