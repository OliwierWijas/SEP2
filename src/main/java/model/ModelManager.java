package model;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private RecipeList recipeList;
  private PersonList personList;
  private IngredientList ingredientList;
  private final Administrator administrator;

  public ModelManager()
  {
    this.recipeList = RecipeList.getInstance();
    this.personList = PersonList.getInstance();
    this.ingredientList = IngredientList.getInstance();
    this.administrator = Administrator.getInstance();
  }

  @Override public Member createAccount(String email, String username, String password)
  {
    // check if objects have the same recipes and favourites
    if (email.isEmpty() || email.equals(""))
      throw new IllegalArgumentException("Email cannot be empty.");
    else if (username.isEmpty() || username.equals(""))
      throw new IllegalArgumentException("Username cannot be empty");
    else if (password.isEmpty() || password.equals(""))
      throw new IllegalArgumentException("Password cannot be empty.");

    Member member = new Member(email, username, password);
    this.personList.addMember(member);
    return member;
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

  @Override public ArrayList<Ingredient> getAllIngredients()
  {
    return this.ingredientList.getIngredients();
  }

  @Override public ArrayList<Recipe> getAllRecipes()
  {
    return this.recipeList.getRecipes();
  }

  @Override public void addIngredient(Ingredient ingredient)
  {
    this.ingredientList.addIngredient(ingredient);
  }
}
