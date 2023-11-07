public class Breakfast<T extends Drink, V extends Bread> extends Menu<T,V> {

    public Breakfast(T drink, V meal) {
        super(drink, meal);
    }

}
