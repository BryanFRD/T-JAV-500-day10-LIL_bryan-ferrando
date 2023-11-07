public abstract class Drink implements Food {

    protected float price;
    protected int calories;
    protected boolean aCan = false;

    protected Drink(float price, int calories) {
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

    public boolean isACan() {
        return aCan;
    }
}
