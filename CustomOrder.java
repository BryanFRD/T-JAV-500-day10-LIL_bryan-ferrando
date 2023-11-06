import java.util.*;

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
        float price = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Your order is composed of:\n");

        for(Menu menu : menus){
            price += menu.getPrice();
            sb.append("- " + menu.getDrink().getClass().getSimpleName() + " and " + menu.getMeal().getClass().getSimpleName())
                    .append("\n")
                    .append("-> drink: " + menu.getDrink())
                    .append("\n")
                    .append("-> meal: " + menu.getMeal())
                    .append("\n");
        }

        for(Map.Entry<Class<? extends Food>, Integer> food : order.entrySet()){
            for(int i = 0; i < food.getValue(); i++){
                try {
                    float p = food.getKey().getDeclaredConstructor().newInstance().getPrice();
                    price += p;
                    sb.append("- " + food.getKey().getSimpleName())
                            .append(" (" + p + " euros)")
                            .append("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        sb.append("For a total of")
                .append(price)
                .append(" euros.");
    }

}
