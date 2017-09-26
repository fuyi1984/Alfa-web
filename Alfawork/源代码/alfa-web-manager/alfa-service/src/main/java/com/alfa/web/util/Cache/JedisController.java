package com.alfa.web.util.Cache;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by Administrator on 2017/5/9.
 */
public class JedisController {

    private ShardedJedisPool shardedJedisPool;
    private JedisPool jedisPool;
    private JedisCluster jedisCluster;

    public JedisController(final ShardedJedisPool shardedJedisPool, final JedisPool jedisPool, final JedisCluster jedisCluster) {
        this.shardedJedisPool = shardedJedisPool;
        this.jedisPool = jedisPool;
        this.jedisCluster = jedisCluster;
    }

    public JedisController(final ShardedJedisPool shardedJedisPool, final JedisPool jedisPool) {
        this.shardedJedisPool = shardedJedisPool;
        this.jedisPool = jedisPool;
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

}
