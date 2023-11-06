import java.util.ArrayList;
import java.util.List;

public abstract class Sandwich implements Food {

    protected float price;
    protected int calories;
    protected boolean vegetarian = false;
    protected List<String> ingredients = new ArrayList<>();

    protected Sandwich(float price, int calories){
        this.price = price;
        this.calories = calories;
    }

    public boolean isVegetarian(){
        return this.vegetarian;
    }

    public void setVegetarian(boolean vegetarian){
        this.vegetarian = vegetarian;
    }

    public List<String> getIngredients(){
        return this.ingredients;
    }

    public void setIngredients(List<String> ingredients){
        this.ingredients = ingredients;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public int getCalories() {
        return 0;
    }
}
