//package org.cwd.config;
//
//import org.apache.activemq.apollo.amqp.dto.AmqpDTO;
//import org.apache.activemq.apollo.broker.Broker;
//import org.apache.activemq.apollo.broker.store.leveldb.dto.LevelDBStoreDTO;
//import org.apache.activemq.apollo.dto.AcceptingConnectorDTO;
//import org.apache.activemq.apollo.dto.BrokerDTO;
//import org.apache.activemq.apollo.dto.VirtualHostDTO;
//import org.apache.activemq.apollo.mqtt.dto.MqttDTO;
//import org.apache.activemq.apollo.stomp.dto.StompDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//
///**
// * Created by chenweida on 2017/8/12.
// */
//@Configuration
//public class ApolloConfig {
//    private Logger logger = LoggerFactory.getLogger(ApolloConfig.class);
//
//    @Value("${apache.apollo.host}")
//    private String host;
//    @Value("${apache.apollo.id}")
//    private String id;
//
//    @Value("${apache.apollo.stomp.id}")
//    private String stompId;
//    @Value("${apache.apollo.stomp.bind}")
//    private String stompBind;
//    @Value("${apache.apollo.stomp.protocol}")
//    private String stompProtocol;
//
//
//    @Value("${apache.apollo.amqp.id}")
//    private String amqpId;
//    @Value("${apache.apollo.amqp.bind}")
//    private String amqpBind;
//    @Value("${apache.apollo.amqp.protocol}")
//    private String amqpProtocol;
//
//
//    @Value("${apache.apollo.mqtt.id}")
//    private String mqttId;
//    @Value("${apache.apollo.mqtt.bind}")
//    private String mqttBind;
//    @Value("${apache.apollo.mqtt.protocol}")
//    private String mqttProtocol;
//
//    private volatile boolean running = false;
//
//    @Bean
//    public BrokerDTO newBrokerConfig() {
//        BrokerDTO broker = new BrokerDTO();
//
//        VirtualHostDTO host = new VirtualHostDTO();
//        host.id = this.id;
//        host.host_names.add(this.host);
//
//        final LevelDBStoreDTO store = new LevelDBStoreDTO();
//        store.directory = new File("./data");
//        host.store = store;
//
//        broker.virtual_hosts.add(host);
//
//        final AcceptingConnectorDTO stomp = new AcceptingConnectorDTO();
//        stomp.id = this.stompId;
//        stomp.bind = this.stompBind;
//        stomp.protocol = this.stompProtocol;
//        stomp.protocols.add(new StompDTO());
//        broker.connectors.add(stomp);
//
//        final AcceptingConnectorDTO amqp = new AcceptingConnectorDTO();
//        amqp.id = this.amqpId;
//        amqp.bind = this.amqpBind;
//        amqp.protocol = this.amqpProtocol;
//        amqp.protocols.add(new AmqpDTO());
//        broker.connectors.add(amqp);
//
//        final AcceptingConnectorDTO mqtt = new AcceptingConnectorDTO();
//        mqtt.id = this.mqttId;
//        mqtt.bind = this.mqttBind;
//        mqtt.protocol = this.mqttProtocol;
//        mqtt.protocols.add(new MqttDTO());
//        broker.connectors.add(mqtt);
//
//        return broker;
//    }
//
//    @Bean
//    public void newBroker(BrokerDTO brokerDTO) {
//        Broker broker = new Broker();
//        broker.setConfig(brokerDTO);
//        logger.info("Starting the broker.");
//
//        broker.start(() -> {
//            running = true;
//            logger.info("The broker has now started.");
//        });
//    }
//}
