package model;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private RecipeList recipeList;
  private PersonList personList;
  private final Administrator administrator;

  public ModelManager()
  {
    this.recipeList = RecipeList.getInstance();
    this.personList = PersonList.getInstance();
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
    this.personList.addRecipeToPerson(recipe, person);
  }

  @Override public void removeRecipe(Recipe recipe, Person person)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    this.recipeList.removeRecipe(recipe);
    this.personList.removeRecipeFromPerson(recipe, person);
  }

  @Override public void addToFavourites(Recipe recipe, Person person)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    this.personList.addToFavourites(recipe, person);
  }

  @Override public void removeFromFavourites(Recipe recipe, Person person)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    this.personList.removeFromFavourites(recipe, person);
  }




}
