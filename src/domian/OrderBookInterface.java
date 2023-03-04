package domian;

import Exceptions.ValidationError;

public interface OrderBookInterface {
    String LIMIT = "limit";
    String MARKET = "market";
    String ASK = "ask";
    String BID = "bid";

    public void cancelOrder(String orderID, String side);
    public void processOrder(Order order) throws ValidationError;
    public void getDepth(String side, int depth);
    public Order getOrder(String orderID, String side) throws ValidationError;
}
