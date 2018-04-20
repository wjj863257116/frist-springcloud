package boot.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class RedisService {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate  redisTemplate;

    /**
     *  list从左边入栈
     * */
    public long addList(String key, String value){
      return  redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * list从左边出栈（先进后出）
     *
     * */
    public Object getList(String key){
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 添加hash键值
     * */

    public Boolean setHash(String key,String field,String value){
        RedisConnection  rc=redisTemplate.getConnectionFactory().getConnection();
        Boolean  bl=rc.hSet(key.getBytes(),field.getBytes(),value.getBytes());
        rc.close();
        return bl;
    }

    /**
     * 获取hash值
     * */
    public String getHash(String key,String field){
        RedisConnection rc=redisTemplate.getConnectionFactory().getConnection();
        byte[] str=rc.hGet(key.getBytes(),field.getBytes());
        rc.close();
        if(str!=null){
            return new String(str);
        }
        return null;
    }
}
