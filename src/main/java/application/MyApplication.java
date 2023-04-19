package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import shared.Connector;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage)
      throws RemoteException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry(1099);
    Connector connector = (Connector) registry.lookup("rmiServer");
    Model model = new ModelManager(connector);
    /*model.addIngredient(new Ingredient("sriracha"));
    model.addIngredient(new Ingredient("tomato"));
    model.addIngredient(new Ingredient("potato"));
    model.addIngredient(new Ingredient("green beans"));
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<Ingredient> ingredients1 = new ArrayList<>();
    ingredients.add(new Ingredient("potato"));
    ingredients.add(new Ingredient("tomato"));
    ingredients1.add(new Ingredient("potato"));
    ingredients1.add(new Ingredient("green beans"));
    model.createAccount("email@via.dk", "username1", "Password1");
    model.createAccount("email2@via.dk", "username2", "Password2");
    model.addRecipe("title1", "description1", ingredients);
    model.addRecipe("title2", "description2", ingredients1);*/

    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
