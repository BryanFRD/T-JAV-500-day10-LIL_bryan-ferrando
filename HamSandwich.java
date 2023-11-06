import java.util.List;

public class HamSandwich extends Sandwich {
    public HamSandwich() {
        super(4.00f, 230);
        vegetarian = false;
        ingredients = List.of("tomato", "salad", "cheese", "ham", "butter");
    }
}
