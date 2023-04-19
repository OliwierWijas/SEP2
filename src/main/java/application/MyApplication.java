package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.util.ArrayList;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage)
  {
    /*Model model = new ModelManager();
    model.addIngredient(new Ingredient("sriracha"));
    model.addIngredient(new Ingredient("tomato"));
    model.addIngredient(new Ingredient("potato"));
    model.addIngredient(new Ingredient("green beans"));
    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<Ingredient> ingredients1 = new ArrayList<>();
    ingredients.add(new Ingredient("potato"));
    ingredients.add(new Ingredient("tomato"));
    ingredients1.add(new Ingredient("potato"));
    ingredients1.add(new Ingredient("green beans"));
    Member member1 = new Member("email1", "username1", "password1");
    Member member2 = new Member("email2", "username2", "password2");
    model.createAccount("email1", "username1", "password1");
    model.createAccount("email2", "username2", "password2");
    model.addRecipe("title1", "description1", ingredients, member1);
    model.addRecipe("title2", "description2", ingredients1, member2);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);*/
  }
}
