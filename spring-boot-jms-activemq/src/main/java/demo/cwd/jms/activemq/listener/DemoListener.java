package demo.cwd.jms.activemq.listener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by chenweida on 2018/1/23.
 */
@Component
public class DemoListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
            System.out.println("Received <" + activeMQTextMessage.getText() + ">");
            message.acknowledge();//确认消费
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
