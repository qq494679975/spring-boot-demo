package demo.cwd.jms.activemq.controller;

import demo.cwd.jms.activemq.listener.DemoListener;
import demo.cwd.jms.activemq.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.MessageListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenweida on 2018/1/18.
 */
@RestController
@Api(tags = "测试模块", description = "测试")
@RequestMapping(value = "/jarDemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JarDemoController {
    private static Map<String, DefaultMessageListenerContainer> holder = new HashMap<String, DefaultMessageListenerContainer>();
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping(value = "/jarDemo", method = RequestMethod.GET)
    @ApiOperation("jar demo")
    public SimpleResult<Boolean> errorDemo(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {
        jmsTemplate.convertAndSend("mailbox", username);
        return new SimpleResult<>(false);
    }


    @RequestMapping(value = "/newListener", method = RequestMethod.GET)
    @ApiOperation("动态新增一个监听")
    public SimpleResult<Boolean> newListener(
            @ApiParam(name = "queueName", value = "队列名字", required = false) @RequestParam(value = "queueName", required = false) String queueName) throws Exception {
        startReceiverByQueueName(new DemoListener(), queueName);
        return new SimpleResult<>(false);
    }

    @RequestMapping(value = "/closeListener", method = RequestMethod.GET)
    @ApiOperation("动态关闭一个监听")
    public SimpleResult<Boolean> closeListener(
            @ApiParam(name = "queueName", value = "队列名字", required = false) @RequestParam(value = "queueName", required = false) String queueName) throws Exception {
        DefaultMessageListenerContainer defaultMessageListenerContainer = holder.get(queueName);
        defaultMessageListenerContainer.shutdown();
        defaultMessageListenerContainer.isActive();
        //holder.remove(queueName);
        return new SimpleResult<>(false);
    }

    @RequestMapping(value = "/sendToQueue", method = RequestMethod.GET)
    @ApiOperation("发送消息到指定队列")
    public SimpleResult<Boolean> sendToQueue(
            @ApiParam(name = "queueName", value = "队列名字", required = false) @RequestParam(value = "queueName", required = false) String queueName,
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {
        jmsTemplate.convertAndSend(queueName, username);
        return new SimpleResult<>(false);
    }


    private void startReceiverByQueueName(MessageListener receiver, String queueName) {
        ActiveMQQueue destination = new ActiveMQQueue(queueName);

        DefaultMessageListenerContainer listenerContainer = new DefaultMessageListenerContainer();
        // 监听容器属性的配置
        listenerContainer.setConnectionFactory(cachingConnectionFactory);
        // 设置目的地
        listenerContainer.setDestination(destination);
        // 设置监听器
        listenerContainer.setMessageListener(receiver);
        // 设置消费者集群数
        int consumers = Integer.valueOf(2);
        listenerContainer.setConcurrentConsumers(consumers);
        // 设置监听队列还是主题 默认是队列
        listenerContainer.setPubSubNoLocal(false);
        // listenerContainer.setAcceptMessagesWhileStopping(true);
        // 设置应答模式 默认是4
        listenerContainer.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        // 设置是否启动事物 默认不开启
        listenerContainer.setSessionTransacted(false);
        // 将监听容器保存在holder中
        holder.put(queueName, listenerContainer);
        listenerContainer.initialize();
        // 启动监听
        listenerContainer.start();
    }

}