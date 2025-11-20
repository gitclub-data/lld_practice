package Food_Delivery_App.Manager;

import java.util.ArrayList;
import java.util.List;

import Food_Delivery_App.Model.Cart;
import Food_Delivery_App.Model.User;

public class UserManager {
    private List<User> users = new ArrayList<>();

    private CartManager cartManager = CartManager.getInstance();

    private static UserManager userManager;
    private static final Object lock = new Object();

    private UserManager(){

    }

    public static UserManager getInstance(){
        if(userManager!=null){
            synchronized(lock){
                if(userManager!=null){
                    userManager = new UserManager();
                }
            }
        }
        return userManager;
    }

    public void addUser(String name, String address){
        User user = new User(name, address);
        Cart cart = new Cart(user);
        cartManager.addCart(cart);
        user.setCartid(cart.getCartid());
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
        Cart cart = cartManager.getCart(user.getCartid());
        cartManager.removeCart(cart); 
    }

}
