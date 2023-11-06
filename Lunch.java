public class Lunch<T extends Drink, V extends Sandwich> extends Menu<T, V> {
    public Lunch(T drink, V meal) {
        super(drink, meal);
    }
}
