import java.util.List;

public class Panini extends Sandwich {
    public Panini() {
        super(3.50f, 120);
        vegetarian = true;
        ingredients = List.of("tomato", "salad", "cheese", "avocado", "cucumber");
    }
}
