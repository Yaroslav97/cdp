package cdp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageReceiver {

    private static final  String QUEUE = "orders";
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public String getMessage(boolean isCountable) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("Topic");
        MessageConsumer consumer = session.createConsumer(topic);

        Message message = consumer.receive();

        String text = "";

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            text = textMessage.getText();
        }
        connection.close();

        return text;
    }

}
