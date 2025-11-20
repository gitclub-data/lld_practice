package Food_Delivery_App.Model;

import Food_Delivery_App.Enum.OrderType;

import java.util.Map;
import java.util.List;

import java.time.Instant;

public class Order {
    private String id;
    private Map<Resturent,List<Dish>> resturentAndDishes;
    private User user;
    private OrderType type;
    private Instant timestamp;

    private static Integer counter = 0;

    public Order(){
        counter += 1;
        this.id = String.valueOf(counter);
    }

    public String getId() {
        return id;
    }

    public void setResturentAndDishes(Map<Resturent, List<Dish>> resturentAndDishes) {
        this.resturentAndDishes = resturentAndDishes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Resturent, List<Dish>> getResturentAndDishes() {
        return resturentAndDishes;
    }

    public User getUser() {
        return user;
    }

    public OrderType getType() {
        return type;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public static void setCounter(Integer counter) {
        Order.counter = counter;
    }

}
