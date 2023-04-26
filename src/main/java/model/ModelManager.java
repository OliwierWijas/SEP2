package model;

import client.Client;
import javafx.application.Platform;
import shared.Connector;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener
{
  private final Client client;
  //private final Administrator administrator;
  private final PropertyChangeSupport support;

  public ModelManager(Connector connector) throws RemoteException
  {
    this.client = new Client(connector);
    //this.administrator = Administrator.getInstance();
    this.client.addPropertyChangeListener(this);
    this.support = new PropertyChangeSupport(this);
  }

  @Override public void createAccount(String email, String username, String password)
  {
    // check if objects have the same recipes and favourites
    try
    {
      this.client.createAccount(email, username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public String login(String username, String password)
  {
    try
    {
      String usernameTemp = this.client.login(username, password);
      return usernameTemp;
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public void addRecipe(String title, String description, ArrayList<Ingredient> ingredients)
  {
    try
    {
      this.client.addRecipe(title, description, ingredients);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients)
  {
    try
    {
      this.client.editRecipe(recipe, title, description, ingredients);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public void removeRecipe(Recipe recipe)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    try
    {
      this.client.removeRecipe(recipe);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public void addToFavourites(Recipe recipe)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    try
    {
      this.client.addToFavourites(recipe);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public void removeFromFavourites(Recipe recipe)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    try
    {
      this.client.removeFromFavourites(recipe);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public ArrayList<Ingredient> getAllIngredients()
  {
    try
    {
      return this.client.getAllIngredients();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getAllRecipes()
  {
    try
    {
      return this.client.getAllRecipes();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void addIngredient(Ingredient ingredient)
  {
    if (ingredient.getName() == null || ingredient.getName().isEmpty())
      throw new IllegalArgumentException("Ingredient's name cannot be empty.");

    try
    {
      this.client.addIngredient(ingredient);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("ResetRecipes"))
        this.support.firePropertyChange("ResetRecipes", false, true);
      else if (evt.getPropertyName().equals("ResetIngredients"))
        this.support.firePropertyChange("ResetIngredients", null, evt.getNewValue());
    });
  }
}
