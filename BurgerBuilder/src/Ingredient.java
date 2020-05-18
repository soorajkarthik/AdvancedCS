import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Ingredient {

    public static final HashMap<String, String> INGREDIENT_IMAGES = new HashMap<String, String>() {{
        put("Bottom Bun", "images/bottomBun.png");
        put("Cheese", "images/cheese.png");
        put("Ketchup", "images/ketchup.png");
        put("Lettuce", "images/lettuce.png");
        put("Onion", "images/onion.png");
        put("Patty", "images/patty.png");
        put("Pickle", "images/pickle.png");
        put("Tomato", "images/tomato.png");
        put("Top Bun", "images/topBun.png");
    }};
    private final String name;
    private final Image image;


    public Ingredient(String name, String imageLocation) {
        this.name = name;
        ImageIcon imageIcon = new ImageIcon(imageLocation);
        this.image = imageIcon.getImage();
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ingredient)) {
            return false;
        }

        return ((Ingredient) obj).getName().equals(name);
    }
}
