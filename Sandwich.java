import java.util.ArrayList;
import java.util.List;

public abstract class Sandwich implements Food {

    protected float price;
    protected int calories;
    protected boolean vegetarian = false;
    protected List<String> ingredients = new ArrayList<>();

    protected Sandwich(float price, int calories) {
        this.price = price;
        this.calories = calories;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public List<String> getIngredients() {
        return ingredients;
    }


}
