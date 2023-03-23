package model;

import java.util.ArrayList;

public interface Model
{
  Member createAccount(String email, String username, String password);
  void addRecipe(String title, String description, ArrayList<Ingredient> ingredients, Person person);
  void removeRecipe(Recipe recipe, Person person);
  void addToFavourites(Recipe recipe, Person person);
  void removeFromFavourites(Recipe recipe, Person person);
}
