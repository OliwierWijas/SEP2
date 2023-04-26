package model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model
{
  void createAccount(String email, String username, String password);
  String login(String username, String password);
  void addRecipe(String title, String description, ArrayList<Ingredient> ingredients);
  void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients);
  void removeRecipe(Recipe recipe);
  void addToFavourites(Recipe recipe);
  void removeFromFavourites(Recipe recipe);
  ArrayList<Ingredient> getAllIngredients();
  ArrayList<Recipe> getAllRecipes();
  void addIngredient(Ingredient ingredient);
  void addPropertyChangeListener(PropertyChangeListener listener);
}
