package by.restonov.multithreading.comparator;

import by.restonov.multithreading.entity.impl.Truck;

import java.util.Comparator;

public class TruckComparator implements Comparator<Truck> {

    @Override
    public int compare(Truck truck1, Truck truck2) {
        int flag = 0;
        if (truck1.getGoodsArePerishable() == false & truck2.getGoodsArePerishable() == true) {
            flag = 1;
        } else if (truck1.getGoodsArePerishable() == true & truck2.getGoodsArePerishable() == false){
            flag = -1;
        } return flag;
    }
}
