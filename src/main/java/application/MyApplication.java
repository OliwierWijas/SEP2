package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Ingredient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage)
  {
    Model model = new ModelManager();
    model.addIngredient(new Ingredient("sriracha"));
    model.addIngredient(new Ingredient("tomato"));
    model.addIngredient(new Ingredient("potato"));
    model.addIngredient(new Ingredient("green beans"));
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
