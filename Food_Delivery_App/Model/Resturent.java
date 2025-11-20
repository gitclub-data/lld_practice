package Food_Delivery_App.Model;

import java.util.List;
import java.util.ArrayList;


import Food_Delivery_App.Enum.ResturentType;

public class Resturent {
    private String id;
    private String name;
    private ResturentType type;
    private List<Dish> dishes = new ArrayList<>();

    private static Integer counter = 0;

    public Resturent(String name, ResturentType type){
        this.name = name;
        this.type = type;

        counter += 1;
        this.id = String.valueOf(counter);
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(ResturentType type) {
        this.type = type;
    }

    public ResturentType getType() {
        return type;
    }

    public void addDish(Dish dish){
        dishes.add(dish);   
    }

    public void removeDish(Dish dish){
        dishes.remove(dish);   
    }

}
