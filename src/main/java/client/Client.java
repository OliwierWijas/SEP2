package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import model.Ingredient;
import model.Recipe;
import shared.Connector;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements RemotePropertyChangeListener
{
  private String username;
  private final Connector connector;
  private final PropertyChangeSupport support;

  public Client(Connector connector) throws RemoteException
  {
    this.connector = connector;
    this.support = new PropertyChangeSupport(this);
    this.connector.addRemotePropertyChangeListener(this);
  }

  public void createAccount(String email, String username, String password) throws RemoteException
  {
    try
    {
      this.username = connector.createAccount(email, username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public String login(String username, String password) throws RemoteException
  {
    try
    {
      this.username = connector.login(username, password);
      return username;
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void addRecipe(String title, String description, ArrayList<Ingredient> ingredients) throws RemoteException
  {
    try
    {
      this.connector.addRecipe(title, description, ingredients, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients) throws RemoteException
  {
    try
    {
      this.connector.editRecipe(recipe, title, description, ingredients, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void removeRecipe(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.removeRecipe(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void addToFavourites(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.addToFavourites(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void removeFromFavourites(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.removeFromFavourites(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public ArrayList<Ingredient> getAllIngredients() throws RemoteException
  {
    return this.connector.getAllIngredients();
  }

  public ArrayList<Recipe> getAllRecipes() throws RemoteException
  {
    return this.connector.getAllRecipes();
  }

  public void addIngredient(Ingredient ingredient) throws RemoteException
  {
    this.connector.addIngredient(ingredient);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(RemotePropertyChangeEvent event) throws RemoteException
  {
    Platform.runLater(() -> {
      if (event.getPropertyName().equals("RecipeAdded") || event.getPropertyName().equals("RecipeEdited") || event.getPropertyName().equals("RecipeRemoved"))
        this.support.firePropertyChange("ResetRecipes", false, true);
      else if (event.getPropertyName().equals("IngredientAdded"))
        this.support.firePropertyChange("ResetIngredients", null, event.getNewValue());
    });
  }
}
