public class AfternoonTea<T extends Drink, V extends Dessert> extends Menu<T, V> {
    public AfternoonTea(T drink, V dessert) {
        super(drink, dessert);
    }
}
