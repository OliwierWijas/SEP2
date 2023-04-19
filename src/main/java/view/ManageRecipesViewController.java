package view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Ingredient;
import model.Recipe;
import viewmodel.ManageRecipesViewModel;

public class ManageRecipesViewController implements ViewController
{
  @FXML private TextField searchRecipeTextField;
  @FXML private ListView<Recipe> recipeListView;
  @FXML private TextField titleTextField;
  @FXML private TextField ingredientTextField;
  @FXML private ListView<Ingredient> ingredientListView;
  @FXML private TextArea descriptionTextArea;

  private ReadOnlyObjectProperty<Recipe> recipe;

  private ViewHandler viewHandler;
  private ManageRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageRecipesViewModel manageRecipesViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = manageRecipesViewModel;
    this.root = root;

    //bind
  }

  @FXML protected void addIngredientButtonPressed()
  {

  }

  @FXML protected void addRecipeButtonPressed()
  {

  }

  @FXML protected void editRecipeButtonPressed()
  {

  }

  @FXML protected void removeRecipeButtonPressed()
  {

  }

  @FXML protected void handleMenu(Event event)
  {
    if (event.getSource().toString().contains(ViewFactory.RECIPES))
      viewHandler.openView(ViewFactory.RECIPES);
    else if (event.getSource().toString().contains(ViewFactory.SEARCH))
      viewHandler.openView(ViewFactory.SEARCH);
  }

  @Override public void reset()
  {

  }

  @Override public Region getRoot()
  {
    return root;
  }

}
