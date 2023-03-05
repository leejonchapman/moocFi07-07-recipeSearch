import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    Scanner userInput;
    ArrayList<Recipe> recipeBook = new ArrayList<>();

    public UserInterface(Scanner userInput) {
        this.userInput = userInput;
    }

    public void start() {
        boolean runMenu = true;
        System.out.print("File to read: ");
        readFile(String.valueOf(userInput.nextLine()));
        System.out.println("Commands:\n" +
                "list - lists the recipes\n" +
                "stop - stops the program \n" +
                "find name - searches recipes by name \n" +
                "find cooking time - searches recipes by cooking time \n" +
                "find ingredient - searches recipes by ingredient");

        while (runMenu) {
            //Read text file and send it to method to parse and create recipe object
            System.out.println("Enter command: ");
            String uI = userInput.nextLine();
            if (uI.equals("list")) {
                for (Recipe recipe : recipeBook
                ) {
                    System.out.println(recipe);
                }
            }
            if (uI.equals("stop")) {
                runMenu = false;
            }
            if (uI.equals("find name")) {
                System.out.print("Searched word: ");
                String userI = userInput.nextLine();
                for (Recipe recipe : recipeBook
                ) {
                    if (recipe.recipeName.contains(userI)) {
                        System.out.println(recipe);
                    }
                }
            }
            if (uI.equals("find cooking time")) {
                System.out.print("Max cooking time: ");
                int userI = userInput.nextInt();
                for (Recipe recipe : recipeBook
                ) {
                    if (recipe.cookingTime <= (userI)) {
                        System.out.println(recipe);
                    }
                }
            }
            if (uI.equals("find ingredient")){
                System.out.print("Ingredient: ");
                String userI = userInput.nextLine();
                for (Recipe recipe : recipeBook
                ) {
                    if (recipe.searchIngredients(userI)){
                        System.out.println(recipe);
                    }
                }
            }
        }
    }

    private void readFile(String userInput) {
        // Ready file from user input and split in to String array then pass to next method
        ArrayList<String> rawRecipeData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(Paths.get(userInput));
            while (scanner.hasNextLine()) {
                rawRecipeData.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rawRecipeData.add("");
        createRecipe(rawRecipeData);
    }

    private void createRecipe(ArrayList<String> rawRecipeData) {
        ArrayList<String> tempRecipeArray = new ArrayList<>();
        ArrayList<String> tempIngredientArray = new ArrayList<>();
        for (String string : rawRecipeData
        ) {
            // if the string isnt blank add it to the array - if it is blank, finish addin the entry
            if (!string.isEmpty()) {
                tempRecipeArray.add(string);
                //if index is greater than or eq to 2 then add them to the ingredients array list
                if (tempRecipeArray.size() > 2) {
                    tempIngredientArray.add(string);
                }
            } else {
                String[] test = tempIngredientArray.toArray(new String[tempIngredientArray.size()]);
                Recipe recipe = new Recipe(tempRecipeArray.get(0), Integer.valueOf(tempRecipeArray.get(1)), test);
                tempIngredientArray.clear();
                recipeBook.add(recipe);
                tempRecipeArray.clear();
            }
        }
    }
}