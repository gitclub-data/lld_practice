package Food_Delivery_App.Manager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Food_Delivery_App.Enum.OrderType;
import Food_Delivery_App.Model.Dish;
import Food_Delivery_App.Model.Order;
import Food_Delivery_App.Model.Resturent;
import Food_Delivery_App.Model.User;

public class OrderManager {

    List<Order> orders = new ArrayList<>();

    private static OrderManager orderManager;
    private static final Object lock = new Object();

    private OrderManager(){

    }

    public static OrderManager getInstance(){
        if(orderManager==null){
            synchronized(lock){
                if(orderManager==null){
                    orderManager = new OrderManager();
                }
            }
        }
        return orderManager;
    }

    public Order createOrder(Map<Resturent,List<Dish>> resturentAndDishes, User user, OrderType type, Instant timestamp){
        Order order = new Order();
        order.setResturentAndDishes(resturentAndDishes);
        order.setUser(user);
        order.setType(type);
        order.setTimestamp(timestamp);
        return order;
    }
    
    public void addOrder(Order Order){
        orders.add(Order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }

    private Double calculateTotalPrice(Order order){
        Map<Resturent, List<Dish>> resturentAndDishes = order.getResturentAndDishes();
        Double price = 0.0;
        for (Resturent resturent : resturentAndDishes.keySet()) {
            List<Dish> dishes = resturentAndDishes.get(resturent);
            for(Dish dish: dishes){
                price += dish.getPrice();
            }
        }
        return price;
    }


    public String createInvoice(Order order){
        String invoice = "Order Number :" + order.getId() + "\n";
        invoice += "Order type : " + order.getType() + "\n";
        invoice += "Delivered by : " + order.getTimestamp()+"\n";
        invoice += "OrderPrice:" + calculateTotalPrice(order);
        return invoice;
    }


    public void Checkout(Order order){
        Double price = calculateTotalPrice(order);
        System.out.println("Started Payment Of the FOllowing order with price : "+ price);
    }

    
}
