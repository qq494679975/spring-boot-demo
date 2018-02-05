package demo.cwd.jms.activemq.config.jms;


import demo.cwd.jms.activemq.listener.DemoListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Created by chenweida on 2018/1/23.
 */
@Configuration
@EnableJms
public class JmsActivcemqConfig {
    @Autowired
    private ActiveMQProperties activeMQProperties;
    @Autowired
    private DemoListener demoListener;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(activeMQProperties.getBrokerUrl());
        connectionFactory.setUserName(activeMQProperties.getUser());
        connectionFactory.setPassword(activeMQProperties.getPassword());
        //设置异步发送
        connectionFactory.setUseAsyncSend(true);
        return connectionFactory;

    }

    /**
     * 缓存session链接
     *
     * @return
     */
    @Bean
    @Primary
    public CachingConnectionFactory CachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        //目标ConnectionFactory对应真实的可以产生JMS Connection的ConnectionFactory
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory());
        //Session缓存数量,这里属性也可以直接在这里配置
        cachingConnectionFactory.setSessionCacheSize(100);
        return cachingConnectionFactory;
    }

    @Bean
    @Primary
    public JmsTemplate jmsQueueTemplate() {
        return new JmsTemplate(connectionFactory());

    }

    //===============================启动监听=====================================

    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(CachingConnectionFactory cachingConnectionFactory) {

        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        // 监听容器属性的配置
        listenerContainer.setConnectionFactory(cachingConnectionFactory);
        // 设置目的地
        ActiveMQQueue destination = new ActiveMQQueue("mailbox");
        listenerContainer.setDestination(destination);
        // 设置监听器
        listenerContainer.setMessageListener(demoListener);
        // 设置消费者集群数
        listenerContainer.setConcurrentConsumers(5);
        // 设置监听队列还是主题 默认是队列
        listenerContainer.setPubSubNoLocal(false);
        // listenerContainer.setAcceptMessagesWhileStopping(true);
        /**
         *  AUTO_ACKNOWLEDGE = 1 ：自动确认
         *  CLIENT_ACKNOWLEDGE = 2：客户端手动确认
         *  DUPS_OK_ACKNOWLEDGE = 3： 自动批量确认
         *  SESSION_TRANSACTED = 0：事务提交并确认
         *  但是在activemq补充了一个自定义的ACK模式:
         *  INDIVIDUAL_ACKNOWLEDGE = 4：单条消息确认
         */
        // 设置应答模式 默认是4
        listenerContainer.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        // 设置是否启动事物 默认不开启
        listenerContainer.setSessionTransacted(false);

        listenerContainer.initialize();
        // 启动监听
        listenerContainer.start();
        return listenerContainer;
    }
}
