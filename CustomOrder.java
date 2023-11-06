import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOrder {

    private List<Menu> menus = new ArrayList<>();
    private Map<Class<? extends Food>, Integer> order = new HashMap<>();
    private Stock stock;
    public CustomOrder(Stock stock){
        this.stock = stock;
    }

    public boolean addItem(Food food){
        try {
            stock.removeFood(food.getClass());
            if(!order.containsKey(food.getClass())){
                order.put(food.getClass(), 1);
            } else {
                order.put(food.getClass(), order.get(food.getClass()) + 1);
            }
        } catch (NoSuckFoodException e) {
            return false;
        }
        return true;
    }

    public boolean removeItem(Food food){
        try {
            stock.addFood(food.getClass());
            if(!order.containsKey(food.getClass())){
                return false;
            } else {
                order.put(food.getClass(), order.get(food.getClass()) - 1);
            }
        } catch (NoSuckFoodException e) {
            return false;
        }
        return true;
    }

    public float getPrice(){
        float price = 0;
        for (Class<? extends Food> food : order.keySet()) {
            try {
                price += food.getDeclaredConstructor().newInstance().getPrice() * order.get(food);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    public boolean addMenu(Menu menu){
        try {
            boolean drink = stock.addFood(menu.getDrink().getClass());
            boolean meal = stock.addFood(menu.getMeal().getClass());
            if(!drink || !meal){
                if(!drink){
                    stock.removeFood(menu.getDrink().getClass());
                }

                if(!meal){
                    stock.removeFood(menu.getMeal().getClass());
                }
                return false;
            }
            menus.add(menu);
        } catch (NoSuckFoodException e){
            return false;
        }
        return true;
    }

    public boolean removeMenu(Menu menu){
        if(!menus.contains(menu)){
            return false;
        }

        try {
            boolean drink = stock.removeFood(menu.getDrink().getClass());
            boolean meal = stock.removeFood(menu.getMeal().getClass());
            if(!drink || !meal){
                if(!drink){
                    stock.addFood(menu.getDrink().getClass());
                }

                if(!meal){
                    stock.addFood(menu.getMeal().getClass());
                }
                return false;
            }
            menus.remove(menu);
        } catch (NoSuckFoodException e){
            return false;
        }
        return true;
    }

    public void printOrder(){
        StringBuilder sb = new StringBuilder();
        sb.append("Your order is composed of:\n");

        menus.forEach(menu -> {
            sb.append("- " + menu.getDrink().getClass().getSimpleName())
                    .append()
        });
    }

}
