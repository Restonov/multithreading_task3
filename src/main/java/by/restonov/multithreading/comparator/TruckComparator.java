package by.restonov.multithreading.comparator;

import by.restonov.multithreading.entity.Truck;

import java.util.Comparator;

public class TruckComparator implements Comparator<Truck> {

    @Override
    public int compare(Truck truck1, Truck truck2) {
        int flag = 0;
        if (truck1.getPerishable() == false & truck2.getPerishable() == true) {
            flag = 1;
        } else if (truck1.getPerishable() == true & truck2.getPerishable() == false){
            flag = -1;
        } return flag;
    }
}
