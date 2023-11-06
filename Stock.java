import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<Class<? extends Food>, Integer> stock = new HashMap<>();

    public Stock(){
        stock.putAll(Map.of(
            Cookie.class, 100,
            CheeseCake.class, 100,
            HamSandwich.class, 100,
            Panini.class, 100,
            FrenchBaguette.class, 100,
                SoftBread.class, 100,
                AppleSmoothie.class, 100,
                Coke.class, 100
        ));
    }

    public int getNumberOf(Class<? extends Food> food) throws NoSuckFoodException {
        if(!stock.containsKey(food)){
            throw new NoSuckFoodException("No such food type: " + food.getName() + ".");
        }
        return stock.getOrDefault(food, 0);
    }

    public boolean addFood(Class<? extends Food> food) throws NoSuckFoodException {
        if(!stock.containsKey(food)){
            throw new NoSuckFoodException("No such food type: " + food.getName() + ".");
        }
        stock.put(food, stock.getOrDefault(food, 0) + 1);
        return true;
    }

    public boolean removeFood(Class<? extends Food> food) throws NoSuckFoodException {
        if(!stock.containsKey(food)){
            throw new NoSuckFoodException("No such food type: " + food.getName() + ".");
        }
        if(stock.get(food) == 0){
            return false;
        }
        stock.put(food, stock.getOrDefault(food, 0) - 1);
        return true;
    }

}
