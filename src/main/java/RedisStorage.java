import redis.clients.jedis.Jedis;

import java.util.Date;

public class RedisStorage {
    private static Jedis jedis;
    private static final String KEY = "USERS_LIST";

    public void init() {
        jedis = new Jedis("localhost", 6379);
    }

    public void deleteAll() {
        jedis.flushDB();
    }

    public void addUser(int user) {
        jedis.zadd(KEY, getTs(), String.valueOf(user));
    }

    private double getTs() {
        return new Date().getTime() / 1000;
    }

    public void getUsersList() {
        System.out.println(jedis.zrange(KEY, 0, -1));
    }
}
