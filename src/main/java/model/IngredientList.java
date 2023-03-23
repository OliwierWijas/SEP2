package model;

import java.util.ArrayList;

public class IngredientList
{
  private static IngredientList instance;
  private ArrayList<Ingredient> ingredients;

  private IngredientList()
  {
    this.ingredients = new ArrayList<>();
  }

  public static synchronized IngredientList getInstance()
  {
    if (instance == null)
    {
      instance = new IngredientList();
    }
    return instance;
  }

  public void addIngredient(Ingredient ingredient)
  {
    this.ingredients.add(ingredient);
  }

  public ArrayList<Ingredient> getIngredients()
  {
    return ingredients;
  }
}
