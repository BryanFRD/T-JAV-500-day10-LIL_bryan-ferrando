public abstract class Menu<T extends Food, V extends Food> {

    protected T drink;
    protected V meal;

    protected Menu(T drink, V meal) {
        this.drink = drink;
        this.meal = meal;
    }

    public T getDrink() {
        return drink;
    }

    public V getMeal() {
        return meal;
    }

    public float getPrice() {
        return (drink.getPrice() + meal.getPrice()) * 0.9f;
    }
}
