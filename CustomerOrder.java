import java.util.*;

public class CustomerOrder {

    private List<Menu<?, ?>> menus = new ArrayList<>();
    private Map<Class<? extends Food>, Integer> order = new HashMap<>();
    private Stock stock;
    public CustomerOrder(Stock stock){
        this.stock = stock;
    }

    public boolean addItem(Food food){
        try {
            stock.remove(food.getClass());
            if(!order.containsKey(food.getClass())){
                order.put(food.getClass(), 1);
            } else {
                order.put(food.getClass(), order.get(food.getClass()) + 1);
            }
        } catch (NoSuchFoodException e) {
            return false;
        }
        return true;
    }

    public boolean removeItem(Food food){
        try {
            stock.add(food.getClass());
            if(!order.containsKey(food.getClass())){
                return false;
            } else {
                order.put(food.getClass(), order.get(food.getClass()) - 1);
            }
        } catch (NoSuchFoodException e) {
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

    public boolean addMenu(Menu<?, ?> menu) throws NoSuchFoodException {
        boolean drink = true;
        try {
            drink = stock.remove(menu.getDrink().getClass());
            stock.remove(menu.getMeal().getClass());
            menus.add(menu);
        } catch (NoSuchFoodException e){
            if(!drink){
                stock.remove(menu.getDrink().getClass());
            }
            throw new NoSuchFoodException(e.getMessage());
        }
        return true;
    }

    public boolean removeMenu(Menu<?, ?> menu) throws NoSuchFoodException {
        if(!menus.contains(menu)){
            return false;
        }

        boolean drink = true;
        try {
            drink = stock.add(menu.getDrink().getClass());
            stock.add(menu.getMeal().getClass());
            menus.remove(menu);
        } catch (NoSuchFoodException e){
            if(!drink){
                stock.add(menu.getDrink().getClass());
            }

            throw new NoSuchFoodException(e.getMessage());
        }
        return true;
    }

    public void printOrder(){
        float price = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Your order is composed of:\n");

        for(Menu<?, ?> menu : menus){
            price += menu.getPrice();
            sb.append("- ")
                    .append(menu.getClass().getName())
                    .append(" menu (")
                    .append(menu.getPrice())
                    .append(" euros)")
                    .append("\n")
                    .append("-> drink: ")
                    .append(menu.getDrink().getClass().getName())
                    .append("\n")
                    .append("-> meal: ")
                    .append(menu.getMeal().getClass().getName())
                    .append("\n");
        }

        for(Map.Entry<Class<? extends Food>, Integer> food : order.entrySet()){
            for(int i = 0; i < food.getValue(); i++){
                try {
                    float p = food.getKey().getDeclaredConstructor().newInstance().getPrice();
                    price += p;
                    sb.append("- ")
                            .append(food.getKey().getName())
                            .append(" (")
                            .append(p)
                            .append(" euros)")
                            .append("\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        sb.append("For a total of ")
                .append(price)
                .append(" euros.");

        System.out.println(sb.toString());
    }

}
