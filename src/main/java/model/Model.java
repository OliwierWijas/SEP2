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
  ArrayList<Ingredient> getIngredientsCopy();
  ArrayList<Recipe> getAllRecipes();
  ArrayList<Recipe> getRecipesCopy();
  ArrayList<Recipe> getRecipesByUsername();
  ArrayList<Recipe> getPersonRecipesCopy();
  ArrayList<Recipe> getFavouriteRecipes();
  ArrayList<Recipe> getFavouriteRecipesCopy();
  ArrayList<Person> getAllMembers();
  ArrayList<Person> getMembersCopy();
  void deleteProfile(String username);
  void editProfile(String username, String email, String password);
  void addPropertyChangeListener(PropertyChangeListener listener);
}
