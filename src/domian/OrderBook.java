package domian;

import Exceptions.ValidationError;

import java.util.Objects;

public class OrderBook implements OrderBookInterface{
    String baseSymbol;
    String quoteSymbol;

    OrderTree asks, bids;


    public OrderBook(String baseSymbol, String quoteSymbol){
        this.baseSymbol = baseSymbol;
        this.quoteSymbol = quoteSymbol;

    }
    @Override
    public void cancelOrder(String orderID, String side) {
        // Cancel an order from the OrderBook by its id and side

        if (Objects.equals(side, this.ASK)){
            this.asks.removeOrder(orderID);

        }else {
            this.bids.removeOrder(orderID);

        }
    }

    private void doneOrder(String orderId, String side){
        this.cancelOrder(orderId, side);
    }

    @Override
    public void processOrder(Order order) throws ValidationError {
        if (Objects.equals(order.getType(), this.MARKET)){

            if (Objects.equals(order.getSide(), this.BID)){
                this.processMarketOrderByQuote(order);
            }else {
                this.processMarketOrderByQuantity(order);
            }

        }else {
            this.processLimitOrder(order);
        }
    }

    @Override
    public void getDepth(String side, int depth) {

    }

    @Override
    public Order getOrder(String orderID, String side) throws ValidationError {
        if (Objects.equals(side, this.ASK)){
            return this.asks.getOrderDetail(orderID);
        }else {
            return this.bids.getOrderDetail(orderID);
        }
    }

    public String getMarket(){
        return this.baseSymbol + "/" + this.quoteSymbol;
    }

    private void processLimitOrder(Order order) throws ValidationError {
        if (Objects.equals(order.getSide(), this.ASK)){
            this.processLimitAsk(order);
        }else {
            this.processLimitBid(order);
        }
    }

    private void processLimitAsk(Order order) throws ValidationError {
        double remainQuantity = order.getQuantity();

        // if match can happen
        while (order.getPrice() <= this.bids.getBestPrice() && this.bids.getBestPrice() != 0.0 && remainQuantity >0){
            //  get best order to match
            Order bestBid = this.bids.getBestOrder();

            remainQuantity = this.match(order, bestBid);
            order.setQuantity(remainQuantity);
        }
        // add order to order book
        if (remainQuantity > 0.0){
            this.asks.addOrder(order);
        }


    }
    private void processLimitBid(Order order) throws ValidationError {
        double remainQuantity = order.getQuantity();

        while (order.getPrice() >= this.asks.getBestPrice() && this.asks.getBestPrice() != 0.0 && remainQuantity > 0){
            // get best order to match
            Order bestBidOrder = this.bids.getBestOrder();

            remainQuantity = this.match(order, bestBidOrder);
            order.setQuantity(remainQuantity);
        }
        // add order to order book id remain
        if (remainQuantity > 0){
            this.asks.addOrder(order);
        }
    }
    private void processAskMarketByQuantity(Order order){

    }

    private void processBidMarketByQuantity(Order order){

    }
    private void processAskMarketByQuote(Order order){

    }

    private void processBidMarketByQuote(Order order){

    }
    private void processMarketOrderByQuote(Order order){
        if (Objects.equals(order.getSide(), this.ASK)){
            this.processAskMarketByQuote(order);
        }else {
            this.processBidMarketByQuote(order);
        }
    }
    private void processMarketOrderByQuantity(Order order) {
        if (Objects.equals(order.getSide(), this.ASK)) {
            this.processAskMarketByQuantity(order);
        } else {
            this.processBidMarketByQuantity(order);

        }
    }
    private double match(Order takerOrder, Order makerOrder){
        double remainTakeQuantity = takerOrder.getQuantity();
        double tradedQuantity = 0.0;

        if (takerOrder.getQuantity() == makerOrder.getQuantity()){
            tradedQuantity = takerOrder.getQuantity();
            remainTakeQuantity = 0.0;

            this.doneOrder(makerOrder.getOrderID(), makerOrder.getSide());
        } else if (takerOrder.getQuantity() > makerOrder.getQuantity()) {
            tradedQuantity = makerOrder.getQuantity();
            remainTakeQuantity = takerOrder.getQuantity() - makerOrder.getQuantity();
            this.doneOrder(makerOrder.orderID, makerOrder.getSide());
        } else if (takerOrder.getQuantity() < makerOrder.getQuantity()) {
            tradedQuantity = takerOrder.getQuantity();
            remainTakeQuantity = 0.0;
            this.updateOrderQuantity(makerOrder.orderID, makerOrder.getQuantity() - takerOrder.getQuantity(),
                    makerOrder.getSide());
        }

        // make a new trade
        new Trade(
                makerOrder.getPrice(),
                tradedQuantity,
                takerOrder,
                makerOrder,
                this.getMarket(),
                121331.544
        ).done();

        return remainTakeQuantity;
    }

    private double matchByQuote(Order takerOrder, Order makerOrder){
        double remainTakeQuote = takerOrder.getQuote();
        double tradedQuantity = 0.0;

        if (takerOrder.getQuote() == makerOrder.getQuantity() * makerOrder.getPrice()){
            tradedQuantity = makerOrder.getQuantity();
            remainTakeQuote = 0.0;
            this.doneOrder(makerOrder.getOrderID(), makerOrder.getSide());

        } else if (takerOrder.getQuote() > makerOrder.getQuantity() * makerOrder.getPrice()) {
            tradedQuantity = makerOrder.getQuantity();
            remainTakeQuote = (takerOrder.getQuote() / makerOrder.getPrice()) - makerOrder.getQuantity();
            this.doneOrder(makerOrder.getOrderID(), makerOrder.getSide());

        } else if (takerOrder.getQuote() < makerOrder.getQuantity() * makerOrder.getPrice()) {
            tradedQuantity = takerOrder.getQuote() / makerOrder.getPrice();
            remainTakeQuote = 0.0;
            this.updateOrderQuantity(
                    makerOrder.getOrderID(),
                    makerOrder.getQuantity() - tradedQuantity,
                    makerOrder.getSide()
            );
        }

        new Trade(
                makerOrder.getPrice(),
                tradedQuantity,
                takerOrder,
                makerOrder,
                this.getMarket(),
                1032145
        ).done();

        return remainTakeQuote;
    }
    private void updateOrderQuantity(String orderId, double quantity, String side){

        if (Objects.equals(side, this.ASK)){
            this.asks.updateOrderQuantity(orderId, quantity);

        }else {
            this.bids.updateOrderQuantity(orderId, quantity);
        }
    }

}
