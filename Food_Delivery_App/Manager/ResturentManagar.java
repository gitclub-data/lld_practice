package Food_Delivery_App.Manager;

import java.util.ArrayList;
import java.util.List;

import Food_Delivery_App.Enum.ResturentType;
import Food_Delivery_App.Model.Dish;
import Food_Delivery_App.Model.Resturent;
import Food_Delivery_App.Strategy.IFindResturentStrategy;

public class ResturentManagar {

    List<Resturent> resturents = new ArrayList<>();
    IFindResturentStrategy findResturentStrategy;
    
    private static ResturentManagar resturentManager;
    private static final Object lock = new Object();

    private ResturentManagar(){

    }

    public static ResturentManagar getInstance(){
        if(resturentManager==null){
            synchronized(lock){
                if(resturentManager==null){
                    resturentManager = new ResturentManagar();
                }
            }
        }
        return resturentManager;
    }
    
    public Resturent CreateResturent(String resturentName, ResturentType resturentType){
        return new Resturent(resturentName, resturentType);
    }

    public void addResturent(Resturent resturent){
        resturents.add(resturent);
    }

    public void removeResturent(Resturent resturent){
        resturents.remove(resturent);
    }

    public void addDishToResturent(Resturent resturent, Dish dish){
        resturent.addDish(dish);
        dish.setResturent(resturent);
    }

    public void removeDishFromResturent(Resturent resturent, Dish dish){
        dish.setResturent(null);
        resturent.removeDish(dish);

    }

    public List<Resturent> searchResturent(){
        return findResturentStrategy.findResturents(resturents);
    }

}
