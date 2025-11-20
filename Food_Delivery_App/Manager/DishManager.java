package Food_Delivery_App.Manager;

import java.util.ArrayList;
import java.util.List;

import Food_Delivery_App.Enum.FoodType;
import Food_Delivery_App.Model.Dish;

public class DishManager {
    private List<Dish> dishes = new ArrayList<>();

    private static DishManager dishManager;
    private static final Object lock = new Object();

    private DishManager(){

    }

    public static DishManager getInstance(){
        if(dishManager!=null){
            synchronized(lock){
                if(dishManager!=null){
                    dishManager = new DishManager();
                }
            }
        }
        return dishManager;
    }
    
    public Dish CreateDish(String name, FoodType type, String dishsupertype, Double price){
        return new Dish(name, type, dishsupertype, price);
    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }

    public void removeDish(Dish dish){
        dishes.remove(dish);
    }

    public List<Dish> getDishByDishSuperType(String dishSuperType){
        List<Dish> filteredDishes = new ArrayList<>();
        for(Dish dish : dishes){
            if(dish.getDishSuperType().equals(dishSuperType)){
                filteredDishes.add(dish);
            }
        }
        return filteredDishes;
    }

}
