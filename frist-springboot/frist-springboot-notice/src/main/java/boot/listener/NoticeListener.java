package boot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "QUEUE_NOTICE")
public class NoticeListener {
  private static Logger logger= LoggerFactory.getLogger(NoticeListener.class);

    @RabbitHandler
    public void process(String content){
        logger.info("-=-=-=-= 获取数据为:"+content);

    }

}
