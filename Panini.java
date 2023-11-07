import java.util.List;

public class Panini extends Sandwich {

    public Panini() {
        super(3.50f, 120);
        this.vegetarian = true;
        this.ingredients = List.of("tomato", "salad", "cucumber", "avocado", "cheese");
    }

}
