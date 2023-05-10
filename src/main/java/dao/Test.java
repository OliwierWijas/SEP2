package dao;

import model.Ingredient;
import model.Member;
import model.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test
{
  public static void main(String[] args) throws SQLException
  {
    Member member = new Member("email@via.dk", "Administrator", "PAssword1");
    PersonDAOImplementation personDAO = PersonDAOImplementation.getInstance();
    IngredientDAOImplementation ingredientDAO = IngredientDAOImplementation.getInstance();
    RecipeDAOImplementation recipeDAO = RecipeDAOImplementation.getInstance();
    Recipe recipe = new Recipe("title", "description", "username1");
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("Potato"));
    ingredients.add(new Ingredient("Tomato"));
    ingredients.add(new Ingredient("Sriracha"));
    recipe.setAllIngredients(ingredients);
    recipe.setId(7);
    //ingredientDAO.createIngredients(ingredients);
    //recipeDAO.addRecipeToPerson(recipe, "username1");
    //personDAO.addToFavourites(recipe, "username");
    //personDAO.removeFromFavourites(recipe, "username");
    //System.out.println(personDAO.login("username", "Password1"));
    //personDAO.rate(recipe, "username", 5);
  }
}
