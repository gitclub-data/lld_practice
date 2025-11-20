package Food_Delivery_App.Strategy;

import java.util.List;

import Food_Delivery_App.Model.Resturent;

public interface IFindResturentStrategy {
    List<Resturent> findResturents(List<Resturent> resturents);
}
