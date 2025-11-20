package Food_Delivery_App.Factory;

import Food_Delivery_App.Strategy.IFindResturentStrategy;
import Food_Delivery_App.Strategy.implementation.findAllResturents;
import Food_Delivery_App.Strategy.implementation.findResturentByName;

public class FindResturentStrategyFactory {

    IFindResturentStrategy findResturent(String findType, String name){
        if("Name".equals(findType)){
            return new findResturentByName(name);
        }
        return new findAllResturents();
    }
}

