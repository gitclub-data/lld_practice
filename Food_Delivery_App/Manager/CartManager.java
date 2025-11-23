package Food_Delivery_App.Manager;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import Food_Delivery_App.Enum.OrderType;
import Food_Delivery_App.Model.Cart;
import Food_Delivery_App.Model.Order;

public class CartManager {

    private List<Cart> carts =  new ArrayList<>();
    private OrderManager orderManager = OrderManager.getInstance();

    private static CartManager cartManager;
    private static Object lock = new Object();

    private CartManager(){

    }

    public static CartManager getInstance(){
        if(cartManager==null){
            synchronized(lock){
                if(cartManager==null){
                    cartManager = new CartManager();
                }
            }
        }
        return cartManager;
    }

    public void addCart(Cart cart){
        carts.add(cart);
    }

    public void removeCart(Cart cart){
        carts.remove(cart);
    }

    public Cart getCart(String cartid){
        for(Cart cart : carts){
            if(cart.getCartid().equals(cartid)){
                return cart;
            }
        }
        return null;
    }

    public Order CreateOrder(Cart cart, OrderType type, Instant timestamp){
        return orderManager.createOrder(cart.getDishes(), cart.getUser(), type, timestamp);
    }
    
}
