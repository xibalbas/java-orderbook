package domian;

import Exceptions.ValidationError;

public interface OrderTreeInterface {
    public void getRedisConnection();
    public void addOrder(Order order);
    public void updateOrderQuantity(String orderId, double newQuantity);
    public void removeOrder(String orderId);
    public Order getBestOrder() throws ValidationError;
    public double getBestPrice();

    public void updateOrderQuantity(String orderId, double newQuantity, String side);
    Order getOrderDetail(String orderId) throws ValidationError;

    public void getOrderBookDepth();
}

