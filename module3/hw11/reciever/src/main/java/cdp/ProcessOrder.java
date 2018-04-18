package cdp;

import cdp.entity.Product;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.concurrent.atomic.AtomicLong;

public class ProcessOrder {

    private AtomicLong availableItems = new AtomicLong(1000);
    private AtomicLong availableLiters = new AtomicLong(1000);

    boolean isAvailableItems(String jsonMassage) throws ParseException {

        JSONParser parser = new JSONParser();
        Gson gson = new Gson();

        JSONObject json = (JSONObject) parser.parse(jsonMassage);
        String productJSON = json.get("product").toString();
        Product product = gson.fromJson(productJSON, Product.class);

        return availableItems.getAndDecrement() > product.getOrderTotal();
    }

    boolean isAvailableLiters(String jsonMassage) throws ParseException {

        JSONParser parser = new JSONParser();
        Gson gson = new Gson();

        JSONObject json = (JSONObject) parser.parse(jsonMassage);
        String productJSON = json.get("product").toString();
        Product product = gson.fromJson(productJSON, Product.class);

        return availableLiters.getAndDecrement() > product.getSize();
    }

}
