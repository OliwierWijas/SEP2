package server;

import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import model.*;
import model.validation.EmailValidator;
import model.validation.PasswordValidator;
import model.validation.UsernameValidator;
import shared.Connector;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class RemoteConnector implements Connector
{
  private RecipeList recipeList;
  private PersonList personList;
  private IngredientList ingredientList;
  private final RemotePropertyChangeSupport support;

  public RemoteConnector()
  {
    this.recipeList = RecipeList.getInstance();
    this.personList = PersonList.getInstance();
    this.ingredientList = IngredientList.getInstance();
    this.support = new RemotePropertyChangeSupport();
  }

  @Override public synchronized String createAccount(String email, String username, String password) throws RemoteException
  {
    UsernameValidator.validateUsername(username);
    EmailValidator.validateEmail(email);
    PasswordValidator.validatePassword(password);

    Member member = new Member(email, username, password);
    this.personList.addMember(member);
    return username;
  }

  @Override public synchronized String login(String username, String password) throws RemoteException
  {
    if (PersonList.getInstance().login(username, password))
    {
     return username;
    }
    throw new IllegalArgumentException("The account does not exist.");
  }

  @Override public synchronized void addRecipe(String title, String description, ArrayList<Ingredient> ingredients, String username) throws RemoteException
  {
    if (title.isEmpty() || title.equals(""))
      throw new IllegalArgumentException("Title cannot be empty.");
    else if (description.isEmpty() || description.equals(""))
      throw new IllegalArgumentException("Description cannot be empty.");
    else if (ingredients.isEmpty() || ingredients.size() == 0)
      throw new IllegalArgumentException("Recipe cannot have 0 ingredients.");

    Recipe recipe = new Recipe(title, description, username);
    recipe.addAllIngredients(ingredients);

    this.recipeList.addRecipe(recipe);
    this.personList.addRecipeToPerson(recipe, username);
    this.ingredientList.addAllIngredients(ingredients);
    this.support.firePropertyChange("RecipeAdded", null, recipe);
  }

  @Override public synchronized void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients, String username) throws RemoteException
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
    this.personList.editPersonRecipe(recipe, title, description, ingredients, username);
    this.ingredientList.addAllIngredients(ingredients);
    this.support.firePropertyChange("RecipeEdited", null, recipe);
  }

  @Override public synchronized void removeRecipe(Recipe recipe, String username) throws RemoteException
  {
    this.recipeList.removeRecipe(recipe);
    this.personList.removeRecipeFromPerson(recipe, username);
    this.support.firePropertyChange("RecipeRemoved", null, recipe);
  }

  @Override public synchronized void addToFavourites(Recipe recipe, String username) throws RemoteException
  {
    this.personList.addToFavourites(recipe, username);
  }

  @Override public synchronized void removeFromFavourites(Recipe recipe, String username) throws RemoteException
  {
    this.personList.removeFromFavourites(recipe, username);
  }

  @Override public synchronized ArrayList<Ingredient> getAllIngredients() throws RemoteException
  {
    return this.ingredientList.getIngredients();
  }

  @Override public synchronized ArrayList<Recipe> getAllRecipes() throws RemoteException
  {
    return this.recipeList.getRecipes();
  }

  @Override public synchronized void addIngredient(Ingredient ingredient) throws RemoteException
  {
    this.ingredientList.addIngredient(ingredient);
    this.support.firePropertyChange("IngredientAdded", null, ingredient);
  }

  @Override public synchronized void addRemotePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException
  {
    this.support.addPropertyChangeListener(listener);
  }
}
