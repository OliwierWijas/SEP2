package model;

import java.util.ArrayList;

public class RecipeList
{
  private static RecipeList instance;
  private final ArrayList<Recipe> recipes;

  private RecipeList()
  {
    this.recipes = new ArrayList<>();
  }

  public static synchronized RecipeList getInstance()
  {
    if (instance == null)
    {
      instance = new RecipeList();
    }
    return instance;
  }

  public void addRecipe(Recipe recipe)
  {
    this.recipes.add(recipe);
  }
}
