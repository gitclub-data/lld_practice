package Food_Delivery_App;

import java.time.Instant;

import Food_Delivery_App.Enum.FoodType;
import Food_Delivery_App.Enum.OrderType;
import Food_Delivery_App.Enum.ResturentType;
import Food_Delivery_App.Manager.CartManager;
import Food_Delivery_App.Manager.DishManager;
import Food_Delivery_App.Manager.OrderManager;
import Food_Delivery_App.Manager.ResturentManagar;
import Food_Delivery_App.Manager.UserManager;
import Food_Delivery_App.Model.Cart;
import Food_Delivery_App.Model.Dish;
import Food_Delivery_App.Model.Order;
import Food_Delivery_App.Model.Resturent;
import Food_Delivery_App.Model.User;

public class Main{
    public static void main(String[] args){

        //  create 2 Resturent and add resturents
        ResturentManagar resturentManagar = ResturentManagar.getInstance();
        Resturent resturent1 = resturentManagar.CreateResturent("Billi The Dhaba", ResturentType.VEG);
        Resturent resturent2 = resturentManagar.CreateResturent("Kabab Ka Khel", ResturentType.NONVEG);
        Resturent resturent3 = resturentManagar.CreateResturent("Jo Khao Anand Pao", ResturentType.VEGANDNONVEG);
        resturentManagar.addResturent(resturent1);
        resturentManagar.addResturent(resturent2);
        resturentManagar.addResturent(resturent3);

        // create 6 Dish 
        DishManager dishManager = DishManager.getInstance();
        Dish dish1 = dishManager.CreateDish("VEG BRIYANI", FoodType.VEG, "Briyani", 200.0);
        Dish dish2 = dishManager.CreateDish("Chiken BRIYANI", FoodType.NONVEG, "Briyani", 500.0);
        Dish dish3 = dishManager.CreateDish("Veg Soup", FoodType.VEG, "Soup", 100.0);
        Dish dish4 = dishManager.CreateDish("Chiken Soup", FoodType.NONVEG, "Soup", 300.0);
        Dish dish5 = dishManager.CreateDish("Simple Noodles", FoodType.VEG, "Noodels", 200.0);
        Dish dish6 = dishManager.CreateDish("Chiken Haka Noodels", FoodType.NONVEG, "Noodels", 400.0);
        dishManager.addDish(dish1);
        dishManager.addDish(dish2);
        dishManager.addDish(dish3);
        dishManager.addDish(dish4);
        dishManager.addDish(dish5);
        dishManager.addDish(dish6);

        //  add 2 dish in 3 resturent 
        resturentManagar.addDishToResturent(resturent1, dish1);
        resturentManagar.addDishToResturent(resturent1, dish2);
        resturentManagar.addDishToResturent(resturent2, dish3);
        resturentManagar.addDishToResturent(resturent2, dish4);
        resturentManagar.addDishToResturent(resturent3, dish5);
        resturentManagar.addDishToResturent(resturent3, dish6);

        //  add users to our application
        UserManager userManager = UserManager.getInstance();
        userManager.addUser("Gaurav", "bla 1 bla bla 1 bla bla 1");
        userManager.addUser("Saurav", "bla bla 1 bla 1");
        userManager.addUser("Rohit", "bla 1 bla bla bla bla 1");

        // add a dish two dishes into cart by gaurav
        CartManager cartManager = CartManager.getInstance();
        User user = userManager.getUserByName("Gaurav");
        Cart cart = cartManager.getCart(user.getCartid());
        cart.addDish(dish3);
        cart.addDish(dish4);

        // create order
        OrderManager orderManager  = OrderManager.getInstance();
        Order order = cartManager.CreateOrder(cart, OrderType.Delievry, Instant.now());
        orderManager.addOrder(order);

        // create invoice
        String invoice = orderManager.createInvoice(order);
        System.out.println(invoice);
        
        // pay
        orderManager.Checkout(order);

    }
}