package shared;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Ingredient;
import model.Recipe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Connector extends Remote
{
  String createAccount(String email, String username, String password) throws RemoteException;
  String login(String username, String password) throws RemoteException;
  void addRecipe(String title, String description, ArrayList<Ingredient> ingredients, String username) throws RemoteException;
  void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients, String username) throws RemoteException;
  void removeRecipe(Recipe recipe, String username) throws RemoteException;
  void addToFavourites(Recipe recipe, String username) throws RemoteException;
  void removeFromFavourites(Recipe recipe, String username) throws RemoteException;
  ArrayList<Ingredient> getAllIngredients() throws RemoteException;
  ArrayList<Recipe> getAllRecipes() throws RemoteException;
  void addIngredient(Ingredient ingredient) throws RemoteException;
  void addRemotePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
}