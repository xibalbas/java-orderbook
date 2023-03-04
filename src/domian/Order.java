package domian;

import Exceptions.ValidationError;

import java.util.HashMap;
import java.util.Map;

public class Order {
    String orderID, type, side;
    double price, quote, fee, time, quantity;
    public Order(String orderID,
                 String type,
                 String side,
                 double price,
                 double quote,
                 double fee,
                 double quantity,
                 double time) throws ValidationError{
        this.fee = fee;
        this.orderID = orderID;
        this.price = price;
        this.side = side;
        this.quote = quote;
        this.quantity = quantity;
        this.time = time;
        this.type = type;
        this.validation();
    }

    public String getOrderID(){
        return this.orderID;
    }
    public String getSide(){
        return this.side;
    }
    public String getType(){
        return this.type;
    }
    public double getPrice(){
        return this.price;
    }
    public double getQuote(){
        return this.quote;
    }
    public void setQuote(double quote){
        this.quote = quote;
    }
    public double getFee(){
        return this.fee;
    }
    public double getTime(){
        return this.time;
    }
    public double getQuantity(){
        return this.quantity;
    }
    public void setQuantity(double quantity){
        this.quantity = quantity;
    }
    public void validation() throws ValidationError{
        if (this.quote <= 0){
            throw new ValidationError("quote must be positive");
        }
        if (this.price <= 0){
            throw new ValidationError("price must be positive");
        }
        if (this.quantity <= 0){
            throw new ValidationError("quantity must be positive");
        }
    }
    public Map<String, Object> toDict(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fee", this.fee);
        map.put("orderID", this.orderID);
        map.put("price", this.price);
        map.put("quote", this.quote);
        map.put("quantity", this.quantity);
        map.put("time", this.time);
        map.put("type", this.type);

        return map;
    }

    public static Order fromDict(Map<String, Object> dictionary) throws ValidationError{
        return new Order(
                dictionary.get("orderID").toString(),
                dictionary.get("type").toString(),
                dictionary.get("side").toString(),
                (Double) dictionary.get("price"),
                (Double) dictionary.get("quote"),
                (Double) dictionary.get("fee"),
                (Double) dictionary.get("quantity"),
                (Double) dictionary.get("time")
                );
    }
}