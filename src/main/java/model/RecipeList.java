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

  public void removeRecipe(Recipe recipe)
  {
    for (int i = 0; i < recipes.size(); i++)
    {
      if (recipes.get(i).equals(recipe))
      {
        this.recipes.remove(recipes.get(i));
        return;
      }
    }
    throw new IllegalArgumentException("Recipe is not in the system.");
  }
}
