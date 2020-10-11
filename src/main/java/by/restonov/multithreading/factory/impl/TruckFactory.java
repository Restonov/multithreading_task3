package by.restonov.multithreading.factory.impl;

import by.restonov.multithreading.entity.impl.Truck;
import by.restonov.multithreading.factory.BaseFactory;
import by.restonov.multithreading.state.impl.TruckState;

public class TruckFactory implements BaseFactory<Truck, String[]> {
    private static final int DATA_ID = 0;
    private static final int DATA_GOODS_AVAILABILITY = 1;
    private static final int DATA_PERISHABLE = 2;
    private static final String PERISHABLE = "perishable";
    private static final String HAS_GOODS = "hasGoods";

    @Override
    public Truck create(String[] truckData) {
        Truck newTruck = new Truck();
        long truckId = Long.parseLong(truckData[DATA_ID]);
        boolean isHasGoods = defineGoodsAvailability(truckData[DATA_GOODS_AVAILABILITY]);
        boolean isPerishable = definePerishable(truckData[DATA_PERISHABLE]);

        newTruck.setId(truckId);
        newTruck.setGoodsAvailability(isHasGoods);
        newTruck.setGoodsArePerishable(isPerishable);
        newTruck.setCurrentState(TruckState.WAIT);

        return newTruck;
    }

    private boolean definePerishable(String isPerishable) {
        boolean result = false;
        if (isPerishable.equals(PERISHABLE)) {
            result = true;
        }
        return result;
    }

    private boolean defineGoodsAvailability(String goodsAvailable) {
        boolean result = false;
        if (goodsAvailable.equals(HAS_GOODS)) {
            result = true;
        }
        return result;
    }
}
