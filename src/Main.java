import Exceptions.ValidationError;
import domian.Order;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ValidationError {
        Order order = new Order(
                "a6sd56a5sd65asd65",
                "limit",
                "ask",
                2.5454,
                5,
                0.35,
                5,
                4215454
        );
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fee", 0.35);
        map.put("orderID", "6as5d65as65d");
        map.put("price", 12454.545);
        map.put("quote", 54.54);
        map.put("quantity", 9.54);
        map.put("time", 99659.5454);
        map.put("type", "limit");
        map.put("side", "ask");
//        System.out.println(map);
        Order orderFromDict = Order.fromDict(map);
        System.out.println(orderFromDict.getTime());
    }
}