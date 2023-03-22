package model;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private RecipeList recipeList;
  private MemberList memberList;
  private final Administrator administrator;

  public ModelManager()
  {
    this.recipeList = RecipeList.getInstance();
    this.memberList = MemberList.getInstance();
    this.administrator = Administrator.getInstance();
  }

  @Override public void addRecipe(String title, String description, ArrayList<Ingredient> ingredients, Person person)
  {
    if (title.isEmpty() || title.equals(""))
      throw new IllegalArgumentException("Title cannot be empty.");
    else if (description.isEmpty() || description.equals(""))
      throw new IllegalArgumentException("Description cannot be empty.");
    else if (ingredients.isEmpty() || ingredients.size() == 0)
      throw new IllegalArgumentException("Recipe cannot have 0 ingredients.");

    Recipe recipe = new Recipe(title, description, person.getUsername());
    recipe.addAllIngredients(ingredients);

    this.recipeList.addRecipe(recipe);
    this.getMember(person).addRecipe(recipe);

  }

  @Override public void removeRecipe(Recipe recipe, Person person)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    for (int i = 0; i < recipes.size(); i++)
    {
      if (recipes.get(i).equals(recipe))
        recipes.remove(recipes.get(i));
    }

    if (person instanceof Member)
    {
      getMember(person).removeRecipe(recipe);
    }

    else if (person instanceof Administrator)
    {

    }
  }

  @Override public void addToFavourites(Recipe recipe, Person person)
  {

  }

  @Override public void removeFromFavourites(Recipe recipe, Person person)
  {

  }




}
