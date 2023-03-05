import java.util.Arrays;

public class Recipe {
    String recipeName;
    int cookingTime;
    String[] ingredients;

    public Recipe(String recipeName, int cookingTime, String[] ingredients) {
        this.recipeName = recipeName;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public boolean searchIngredients(String requiredIngredient){
        for (String ingredient:ingredients
             ) {
           if (requiredIngredient.equals(ingredient)){
               return true;
           }
        }
        return false;
    }
    @Override
    public String toString() {
        return recipeName + ", cooking time: " + cookingTime;
    }
}
