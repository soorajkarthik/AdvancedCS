import java.util.ArrayList;
import java.util.Collections;

public class Burger {

    private final ArrayList<Ingredient> ingredients;

    public Burger() {
        ingredients = new ArrayList<>();
    }

    public Burger(ArrayList<Ingredient> ingredients) {
        this.ingredients = new ArrayList<>();
        this.ingredients.addAll(ingredients);
    }

    public static Burger generateBurger() {

        String[] ingredientNames = new String[]{"Cheese", "Ketchup", "Lettuce", "Onion", "Pickle", "Tomato"};
        ArrayList<Ingredient> randomIngredients = new ArrayList<>();
        randomIngredients.add(new Ingredient("Patty", Ingredient.INGREDIENT_IMAGES.get("Patty")));
        for (String n : ingredientNames) {
            if (Math.random() < 0.6) {
                randomIngredients.add(new Ingredient(n, Ingredient.INGREDIENT_IMAGES.get(n)));
            }

            if (randomIngredients.size() == 6) {
                break;
            }
        }

        Collections.shuffle(randomIngredients);
        randomIngredients.add(0, new Ingredient("Bun", Ingredient.INGREDIENT_IMAGES.get("Bottom Bun")));
        randomIngredients.add(new Ingredient("Bun", Ingredient.INGREDIENT_IMAGES.get("Top Bun")));
        return new Burger(randomIngredients);

    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Burger)) {
            return false;
        }

        Burger check = (Burger) obj;

        if (check.getIngredients().size() != ingredients.size()) {
            return false;
        }

        for (int i = 0; i < ingredients.size(); i++) {
            if (!ingredients.get(i).equals(check.getIngredients().get(i))) {
                return false;
            }
        }

        return true;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient() {

        if (!ingredients.isEmpty()) {
            ingredients.remove(ingredients.size() - 1);

        }
    }

    public int numOfBuns() {

        int num = 0;

        for (Ingredient i : ingredients) {
            if (i.getName().equals("Bun")) {
                num++;
            }
        }

        return num;
    }


}
