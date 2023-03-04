package domian;

import Exceptions.ValidationError;

import java.util.HashMap;
import java.util.Map;

public class OrderTree implements OrderTreeInterface {
    String baseSymbol, quoteSymbol;
    private String getPriceLevelQueueKey(){
        return "queue:" + this.baseSymbol+ ":" + this.quoteSymbol;
    }

    private String getOrderDetailKey(){
        return "order-detail:"+this.baseSymbol+":"+this.quoteSymbol;
    }

    @Override
    public void getRedisConnection() {

    }

    @Override
    public void addOrder(Order order) {

    }

    @Override
    public void updateOrderQuantity(String orderId, double newQuantity) {

    }

    @Override
    public void removeOrder(String orderId) {
        // get order detail
        // remove order from queue
        // check if order removed from queue
            // remove order from order tree
        // remove order detail

    }

    @Override
    public Order getBestOrder() throws ValidationError {
        Map<String, Object> map = new HashMap<String, Object>();
        return Order.fromDict(map);
    }

    @Override
    public double getBestPrice() {
        return 0.0;
    }

    @Override
    public void updateOrderQuantity(String orderId, double newQuantity, String side) {
        // set new quantity in redis with
    }

    @Override
    public Order getOrderDetail(String orderId) throws ValidationError {
        Map<String, Object> map = new HashMap<String, Object>();
        return Order.fromDict(map);
    }

    @Override
    public void getOrderBookDepth() {

    }


}
