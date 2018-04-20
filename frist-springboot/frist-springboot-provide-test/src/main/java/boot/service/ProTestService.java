package boot.service;

import boot.config.RabbitMQConfig;
import boot.core.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProTestService {
    private static Logger logger = LoggerFactory.getLogger(ProTestService.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisService  redisService;


    public Object proTests(Map<String,Object> req) throws  Exception{
          Map<String,Object> map=new  HashMap<String,Object>();
          logger.info("调用了该类");
          map.put("access","接口调用成功");
          map.put("msg",req);
          String str= "rabbitMQ   notice模块消耗provider";

          logger.info(redisService.getHash("frist","test"));

        rabbitTemplate.convertAndSend(RabbitMQConfig.exchange,RabbitMQConfig.bind,str);
        return map;
    }
}
