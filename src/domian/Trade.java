package domian;

public class Trade {
    double price, quantity, time;
    Order takerOrder, makerOrder;
    String market;

    public Trade(double price, double quantity, Order takerOrder, Order makerOrder, String market, double time){
        this.market = market;
        this.price = price;
        this.quantity = quantity;
        this.takerOrder = takerOrder;
        this.makerOrder = makerOrder;
        this.time = time;
    }

    public void done(){

    }
}
