package Food_Delivery_App.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {

    private String cartid;
    private Map<Resturent, List<Dish>> dishes;
    private User user;

    private static Integer counter = 0;

    public Cart(User user) {

        this.user = user;
        counter += 1;
        this.cartid = String.valueOf(counter);
    }

    public String getCartid() {
        return cartid;
    }

    public User getUser() {
        return user;
    }

    public Map<Resturent, List<Dish>> getDishes() {
        return dishes;
    }

    public void addDish(Resturent resturent, Dish dish){
        dishes.computeIfAbsent(resturent, key -> new ArrayList<>()).add(dish);
    }

    public void removeDish(Resturent resturent, Dish dish){
        if(dishes.containsKey(resturent)){
            dishes.get(resturent).remove(dish);
            if(dishes.get(resturent).size()==0){
                dishes.remove(resturent);
            }
        }
    }

}
