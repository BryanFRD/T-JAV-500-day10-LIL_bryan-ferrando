public abstract class Bread implements Food {

    protected float price;
    protected int calories;
    protected int bakingTime;

    protected Bread(float price, int calories){
        this.price = price;
        this.calories = calories;
    }

    public int getBakingTime(){
        return this.bakingTime;
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
