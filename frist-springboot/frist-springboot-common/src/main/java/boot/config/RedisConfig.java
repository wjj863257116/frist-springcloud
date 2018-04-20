package boot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

 private static final Logger logger= LoggerFactory.getLogger(RedisConfig.class);

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager  cacheManager(RedisTemplate redisTemplate){
     return new RedisCacheManager(redisTemplate);
 }



    @SuppressWarnings({"rawtypes","unchecked"})
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){

        StringRedisTemplate  template=new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer  serializer=new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper  om=new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(om);
        template.setValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

}
