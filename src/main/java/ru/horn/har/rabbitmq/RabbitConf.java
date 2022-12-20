//package ru.horn.har.rabbitmq;
//
//import com.rabbitmq.client.ConnectionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class RabbitConf {
//    Logger logger = LoggerFactory.getLogger(RabbitConf.class);
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return new CachingConnectionFactory("localhost").getRabbitConnectionFactory();
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
//    }
//
//    @Bean
//    public Queue myQueue() {
//        return new Queue("myQueue");
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
//        container.setQueueNames("myQueue");
//        container.setMessageListener(message -> logger.info("Received from myQueue: " + new String(message.getBody())));
//        return container;
//    }
//}
