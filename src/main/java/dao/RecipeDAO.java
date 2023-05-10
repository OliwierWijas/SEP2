package dao;

import model.Ingredient;
import model.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RecipeDAO
{
  void addRecipeToPerson(Recipe recipe, String username) throws SQLException;
  void editPersonRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients) throws SQLException;
  void removeRecipe(Recipe recipe) throws SQLException;
}
