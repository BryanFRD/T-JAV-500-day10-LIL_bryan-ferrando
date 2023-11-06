public abstract class Dessert implements Food {

    protected float price;
    protected int calories;

    protected Dessert(float price, int calories){
        this.price = price;
        this.calories = calories;
    }

    @Override
    public float getPrice() {
        return this.price;
    }

    @Override
    public int getCalories() {
        return this.calories;
    }
}
