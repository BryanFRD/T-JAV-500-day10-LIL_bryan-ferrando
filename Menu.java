public abstract class Menu {

    protected Food drink;
    protected Food meal;

    protected Menu(Food drink, Food meal){
        this.drink = drink;
        this.meal = meal;
    }

    public Food getDrink() {
        return drink;
    }

    public Food getMeal() {
        return meal;
    }

    public float getPrice(){
        return (drink.getPrice() + meal.getPrice()) * 0.1f;
    }

}
