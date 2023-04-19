package model;

import model.validation.EmailValidator;
import model.validation.PasswordValidator;
import model.validation.UsernameValidator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private RecipeList recipeList;
  private PersonList personList;
  private IngredientList ingredientList;
  private String username;
  //private final Administrator administrator;
  private final PropertyChangeSupport support;

  public ModelManager()
  {
    this.recipeList = RecipeList.getInstance();
    this.personList = PersonList.getInstance();
    this.ingredientList = IngredientList.getInstance();
    //this.administrator = Administrator.getInstance();
    this.support = new PropertyChangeSupport(this);
  }

  @Override public String createAccount(String email, String username, String password)
  {
    // check if objects have the same recipes and favourites
    EmailValidator.validateEmail(email);
    UsernameValidator.validateUsername(username);
    PasswordValidator.validatePassword(password);

    Member member = new Member(email, username, password);
    this.personList.addMember(member);
    return username;
  }

  @Override public String login(String username, String password)
  {
    if (PersonList.getInstance().login(username, password))
    {
      this.username = username;
      return this.username;
    }
    throw new IllegalArgumentException("The account does not exist.");
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
    this.ingredientList.addAllIngredients(ingredients);

    this.support.firePropertyChange("RecipeAdded", null, recipe);
  }

  @Override public void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients, Person person)
  {
    if (recipe == null)
      throw new IllegalArgumentException("No recipe selected.");
    else if (title.isEmpty() || title.equals(""))
      throw new IllegalArgumentException("Title cannot be empty.");
    else if (description.isEmpty() || description.equals(""))
      throw new IllegalArgumentException("Description cannot be empty.");
    else if (ingredients.isEmpty() || ingredients.size() == 0)
      throw new IllegalArgumentException("Recipe cannot have 0 ingredients.");

    this.recipeList.editRecipe(recipe, title, description, ingredients);
    this.personList.editPersonRecipe(recipe, title, description, ingredients, person);
    this.ingredientList.addAllIngredients(ingredients);

    this.support.firePropertyChange("RecipeEdited", null, recipe);
  }

  @Override public void removeRecipe(Recipe recipe, Person person)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    this.recipeList.removeRecipe(recipe);
    this.personList.removeRecipeFromPerson(recipe, person);

    this.support.firePropertyChange("RecipeRemoved", null, recipe);
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
    if (ingredient.getName() == null || ingredient.getName().isEmpty())
      throw new IllegalArgumentException("Ingredient's name cannot be empty.");
    this.ingredientList.addIngredient(ingredient);
    this.support.firePropertyChange("IngredientAdded", null, ingredient);
  }

  @Override public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  public static void main(String[] args)
  {
    ModelManager modelManager = new ModelManager();
    modelManager.createAccount("email1", "username1", "password1");
    System.out.println(modelManager.login("username1", "password1"));
  }
}
