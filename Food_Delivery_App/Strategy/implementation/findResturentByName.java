package Food_Delivery_App.Strategy.implementation;

import java.util.ArrayList;
import java.util.List;

import Food_Delivery_App.Model.Resturent;
import Food_Delivery_App.Strategy.IFindResturentStrategy;

public class findResturentByName implements IFindResturentStrategy{

    String resturentName;

    public findResturentByName(String resturentName){
        this.resturentName = resturentName;
    }    

    @Override
    public List<Resturent> findResturents(List<Resturent> resturents){
        List<Resturent> machingResturent = new ArrayList<>();
        for(Resturent resturent : resturents){
            if(resturent.getName().equals(this.resturentName)){
                machingResturent.add(resturent);
            }
        }
        return machingResturent;
    }    

}