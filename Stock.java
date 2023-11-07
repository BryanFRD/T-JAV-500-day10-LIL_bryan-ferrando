import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<Class<? extends Food>, Integer> stock = new HashMap<>();

    public Stock() {
        stock.put(AppleSmoothie.class, 100);
        stock.put(CheeseCake.class, 100);
        stock.put(Cookie.class, 100);
        stock.put(Coke.class, 100);
        stock.put(FrenchBaguette.class, 100);
        stock.put(HamSandwich.class, 100);
        stock.put(Panini.class, 100);
        stock.put(SoftBread.class, 100);
    }

    public int getNumberOf(Class<? extends Food> food) {
        if (!stock.containsKey(food)) {
            throw new NoSuchFoodException("No such food type: " + food.getName() + ".");
        }
        return stock.get(food);
    }

    public boolean add(Class<? extends Food> food) {
        if (stock.containsKey(food)) {
            int current_stock = stock.get(food);
            stock.put(food, stock.get(food) + 1);
            return stock.get(food) == current_stock + 1;
        } else {
            throw new NoSuchFoodException("No such food type: " + food.getName() + ".");
        }
    }

    public boolean remove(Class<? extends Food> food) {
        if (stock.containsKey(food)) {
            if (stock.get(food) == 0) {
                return false;
            }
            int current_stock = stock.get(food);
            stock.put(food, stock.get(food) - 1);
            return stock.get(food) == current_stock - 1;
        } else {
            throw new NoSuchFoodException("No such food type: " + food.getName() + ".");
        }

    }


}
