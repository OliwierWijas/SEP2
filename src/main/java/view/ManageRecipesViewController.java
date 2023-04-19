package view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
  @FXML private Label error;

  private ReadOnlyObjectProperty<Ingredient> ingredient;
  private ReadOnlyObjectProperty<Recipe> recipe;

  private ViewHandler viewHandler;
  private ManageRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageRecipesViewModel manageRecipesViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = manageRecipesViewModel;
    this.root = root;

    this.viewModel.bindRecipeList(recipeListView.itemsProperty());
    this.viewModel.bindTitle(titleTextField.textProperty());
    this.viewModel.bindIngredientName(ingredientTextField.textProperty());
    this.viewModel.bindIngredientsList(ingredientListView.itemsProperty());

    this.ingredient = ingredientListView.getSelectionModel().selectedItemProperty();
    this.viewModel.bindIngredient(ingredient);

    this.viewModel.bindDescription(descriptionTextArea.textProperty());
    this.viewModel.bindError(error.textProperty());
    this.recipe = recipeListView.getSelectionModel().selectedItemProperty();
    this.viewModel.bindRecipe(recipe);
  }

  @FXML protected void addIngredientButtonPressed()
  {
    viewModel.addIngredient();
  }

  @FXML protected void removeIngredientButtonPressed()
  {
    viewModel.removeIngredient();
  }

  @FXML protected void addRecipeButtonPressed()
  {
    viewModel.addRecipe();
  }

  @FXML protected void editRecipeButtonPressed()
  {
    viewModel.editRecipe();
  }

  @FXML protected void removeRecipeButtonPressed()
  {
    viewModel.removeRecipe();
  }

  @FXML protected void handleMenu(Event event)
  {
    if (event.getSource().toString().contains(ViewFactory.RECIPES))
      viewHandler.openView(ViewFactory.RECIPES);
    else if (event.getSource().toString().contains(ViewFactory.SEARCH))
      viewHandler.openView(ViewFactory.SEARCH);
  }

  @FXML protected void recipeChangeListener()
  {
    searchRecipeTextField.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containRecipe(searchRecipeTextField.getText()));
  }

  @FXML protected void selectRecipeListener()
  {
    viewModel.showRecipe();
  }

  @Override public void reset()
  {
    this.viewModel.reset();
  }

  @Override public Region getRoot()
  {
    return root;
  }
}