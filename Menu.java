public abstract class Menu {

    protected Drink drink;
    protected Food meal;

    protected Menu(Drink drink, Food meal){
        this.drink = drink;
        this.meal = meal;
    }

    public Drink getDrink() {
        return drink;
    }

    public Food getMeal() {
        return meal;
    }

    public float getPrice(){
        return (drink.getPrice() + meal.getPrice()) * 0.1f;
    }

}
