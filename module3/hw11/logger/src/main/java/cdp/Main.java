package cdp;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

import javax.jms.JMSException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final File REJECTED = new File("rejected.txt");
    private static final File ACCEPTED = new File("accepted.txt");

    public static void main(String[] args) throws JMSException, IOException, ParseException {

        MessageReceiver messageReceiver = new MessageReceiver();
        ProcessOrder processOrder = new ProcessOrder();

        List<String> rejectedOrderList = new ArrayList<>();
        List<String> acceptedOrderList = new ArrayList<>();

        while (true) {
            String message = messageReceiver.getMessage();

            if (processOrder.isAvailableItems(message)) {
                rejectedOrderList.add(message);
                FileUtils.writeLines(ACCEPTED, rejectedOrderList);
            } else {
                acceptedOrderList.add(message);
                FileUtils.writeLines(REJECTED, acceptedOrderList);
            }
        }
    }

}
