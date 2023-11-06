import java.util.Map;

public class CustomOrder {

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
        boolean drink = addItem(menu.getDrink());
        boolean meal = addItem(menu.getMeal());
        if(!drink || !meal){
            if(!drink){
                removeItem(menu.getDrink());
            }

            if(!meal){
                removeItem(menu.getMeal());
            }
            return false;
        }
        return true;
    }

    public boolean removeMenu(Menu menu){
        boolean drink = removeItem(menu.getDrink());
        boolean meal = removeItem(menu.getMeal());
        if(!drink || !meal){
            if(!drink){
                addItem(menu.getDrink());
            }

            if(!meal){
                addItem(menu.getMeal());
            }
            return false;
        }
        return true;
    }

    public void printOrder(){

    }

}
