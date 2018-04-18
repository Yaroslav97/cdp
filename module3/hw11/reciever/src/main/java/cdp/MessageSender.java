package cdp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageSender {

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public void sendMassage(String order, String queue) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(queue);

        MessageProducer producer = session.createProducer(destination);

        TextMessage message = session.createTextMessage(order);

        producer.send(message);

        connection.close();
    }
}
