package com.epam.cdp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Objects;

public class MessageSender {

    private static final String SUBJECT = "orders";
    private static final String SELECTOR = "order";
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String TOPIC = "Topic";
    private static final Logger LOG = LoggerFactory.getLogger(MessageSender.class);
    private static final String SUBSCRIBER = "subscriber";

    public void sendMessage(String order) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(SUBJECT);

        MessageProducer producer = session.createProducer(destination);

        TextMessage message = session.createTextMessage(order);
        message.setBooleanProperty(SELECTOR, true);

        Topic topic = session.createTopic(TOPIC);
        session.createDurableSubscriber(topic, SUBSCRIBER);
        producer = session.createProducer(topic);

        if (!Objects.equals(order, "")) {
            session.commit();
        } else {
            session.rollback();
        }
        producer.send(message);

        LOG.info("Message sent: '" + message.getText() + "'");

        connection.close();
    }

}
