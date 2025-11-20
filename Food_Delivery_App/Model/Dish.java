package Food_Delivery_App.Model;

import Food_Delivery_App.Enum.FoodType;

public class Dish {
    private String id;
    private String name;
    private FoodType type;
    private Resturent resturent;
    private String dishSuperType;
    private Double price;

    private static Integer counter = 0;

    public Dish(String name, FoodType type, String dishSuperType, Double price){
        this.name = name;
        this.type = type;
        this.price = price;

        counter += 1;
        this.id = String.valueOf(counter);
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public Resturent getResturent() {
        return resturent;
    }

    public String getDishSuperType() {
        return dishSuperType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public void setResturent(Resturent resturent) {
        this.resturent = resturent;
    }

    public void setDishSuperType(String dishSuperType) {
        this.dishSuperType = dishSuperType;
    }

}
