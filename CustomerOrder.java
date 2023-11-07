import java.util.ArrayList;
import java.util.List;

public class CustomerOrder {

    private final Stock stock;
    private final List<Food> order = new ArrayList<>();
    private final List<Menu<?, ?>> menu = new ArrayList<>();

    public CustomerOrder(Stock stock) {
        this.stock = stock;
    }

    public boolean addItem(Food food) {
        try {
            if (stock.getNumberOf(food.getClass()) == 0) {
                return false;
            }
            int current_order = order.size();
            order.add(food);
            return order.size() == current_order + 1 && stock.remove(food.getClass());
        } catch (NoSuchFoodException e) {
            return false;
        }
    }

    public boolean removeItem(Food food) {
        try {
            if (stock.getNumberOf(food.getClass()) == 0) {
                return false;
            }
            int current_order = order.size();
            order.remove(food);
            return order.size() == current_order - 1 && stock.add(food.getClass());
        } catch (NoSuchFoodException e) {
            return false;
        }
    }

    public float getPrice() {
        float price = 0;
        for (Food food : order) {
            price += food.getPrice();
        }
        for (Menu<?, ?> menu : this.menu) {
            price += menu.getPrice();
        }
        return price;
    }

    public boolean addMenu(Menu<? extends Food, ? extends Food> menu) {
        try {
            if (stock.getNumberOf(menu.getDrink().getClass()) == 0 || stock.getNumberOf(menu.getMeal().getClass()) == 0) {
                return false;
            }

            stock.remove(menu.getDrink().getClass());
            stock.remove(menu.getMeal().getClass());

            this.menu.add(menu);
            return true;

        } catch (NoSuchFoodException e) {

            return false;
        }
    }

    public boolean removeMenu(Menu<? extends Food, ? extends Food> menu) {
        try {
            if (this.menu.contains(menu)) {
                stock.add(menu.getDrink().getClass());
                stock.add(menu.getMeal().getClass());

                this.menu.remove(menu);
                return true;
            }
            return false;
        } catch (NoSuchFoodException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void printOrder() {
        float totalPrice = 0;

        System.out.println("Your order is composed of:");

         for (Menu<?, ?> item : menu) {
            Menu<? extends Food, ? extends Food> menu = item;

            String menuName = menu.getClass().getSimpleName() + " menu";
            float menuPrice = menu.getPrice();

            System.out.println("- " + menuName + " (" + menuPrice + " euros)");
            System.out.println("-> drink: " + menu.getDrink().getClass().getSimpleName());
            System.out.println("-> meal: " + menu.getMeal().getClass().getSimpleName());

            totalPrice += menuPrice;
        }

        for (Food item : order) {
            if (item instanceof Menu<?, ?>) {
                Menu<? extends Food, ? extends Food> menu = (Menu<? extends Food, ? extends Food>) item;

                String menuName = menu.getClass().getSimpleName() + " menu";
                float menuPrice = menu.getPrice();

                System.out.println("- " + menuName + " (" + menuPrice + " euros)");
                System.out.println("-> drink: " + menu.getDrink().getClass().getSimpleName());
                System.out.println("-> meal: " + menu.getMeal().getClass().getSimpleName());

                totalPrice += menuPrice;
            } else {
                String itemName = item.getClass().getSimpleName();
                float itemPrice = item.getPrice();

                System.out.println("- " + itemName + " (" + itemPrice + " euros)");

                totalPrice += itemPrice;
            }
        }

        System.out.println("For a total of " + totalPrice + " euros.");
    }

}
