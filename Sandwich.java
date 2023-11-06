public abstract class Sandwich implements Food {

    protected float price;
    protected int calories;
    protected boolean vegetarian;

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

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public int getCalories() {
        return 0;
    }
}
