package cdp;

import cdp.entity.Product;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.io.IOException;

public class Main {

    private static final String REJECTED = "rejected";
    private static final String ACCEPTED = "accepted";

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws JMSException, IOException, ParseException {

        MessageReceiver messageReceiver = new MessageReceiver();
        MessageSender messageSender = new MessageSender();
        ProcessOrder processOrder = new ProcessOrder();

        JSONParser parser = new JSONParser();
        Gson gson = new Gson();

        while (true) {
            String message = messageReceiver.getMessage(true);
            LOG.info(message);

            JSONObject json = (JSONObject) parser.parse(message);

            String productJSON = json.get("product").toString();
            Product product = gson.fromJson(productJSON, Product.class);

            switch (product.getName()) {
                case LIQUIDS:
                    if (processOrder.isAvailableItems(message)) {
                        LOG.info("Order accepted");
                        messageSender.sendMassage(message, ACCEPTED);
                    } else {
                        LOG.info("Order rejected");
                        messageSender.sendMassage(message, REJECTED);
                    }
                    break;
                case COUNTABLE:
                    if (processOrder.isAvailableLiters(message)) {
                        LOG.info("Order accepted");
                        messageSender.sendMassage(message, ACCEPTED);
                    } else {
                        LOG.info("Order rejected");
                        messageSender.sendMassage(message, REJECTED);
                    }
                    break;
            }
        }
    }

}
