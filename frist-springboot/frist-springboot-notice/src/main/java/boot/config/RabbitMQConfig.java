package boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMQConfig {

    private static Logger logger= LoggerFactory.getLogger(RabbitMQConfig.class);
    private static final String  exchange ="DEFAULT_EXCHANGE";
    private static final String queue="QUEUE_NOTICE";
    private static final String bind="ROUTING_KEY_NOTICE";

    /**
     * rabbitmq连接
     * @return
     */
    /*@Bean
    public ConnectionFactory connectionFactory() {

        logger.info("配置RabbitMQ连接的相关信息！");

        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("127.0.0.1",5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); // 必须要设置这里是回调用的
        return connectionFactory;

    }*/

    /**
     * rabbitmq模版工具类
     * 必须是prototype类型
     * xuchun
     * 2017-07-12
     */
    /*@Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;

    }
*/


    /**
     *  创建默认交换机
     *  durable:持久化
     *  autoDelete:自动删除
     *
    * */
    @Bean
    public DirectExchange  difaultExchange(){
        return new DirectExchange(exchange,true,false);
    }
    /**
    * 创建回调通知列队
     * exclusive:单列队
    * */
    @Bean
    public Queue  queue1(){
        return new Queue(queue,true,false,false);
    }


    /**
     * 路由绑定
     *
     * */
    @Bean
    public Binding  binding1(){
        return BindingBuilder
                .bind(queue1())
                .to(difaultExchange())
                .with(bind);
    }
}
