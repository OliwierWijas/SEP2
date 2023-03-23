package view;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import model.Ingredient;
import model.Recipe;
import viewmodel.ManageRecipesViewModel;


public class ManageRecipesViewController implements ViewController
{
  @FXML private TextField searchRecipeTextField;
  @FXML private TextField searchIngredientTextField;
  @FXML private TableView<Ingredient> ingredientTable;
  @FXML private TableColumn<Ingredient, String> nameCell;
  @FXML private TableColumn<Ingredient, CheckBox> selectCell;
  @FXML private ListView<Recipe> recipeListView;
  private SimpleListProperty<Ingredient> selectedIngredientList;


  private ViewHandler viewHandler;
  private ManageRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageRecipesViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.selectedIngredientList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.nameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.selectCell.setCellValueFactory(new PropertyValueFactory<>("select"));
    this.viewModel.bindRecipeList(recipeListView.itemsProperty());
    this.viewModel.bindIngredientList(ingredientTable.itemsProperty());
    this.viewModel.bindSelectedIngredientList(selectedIngredientList);
  }

  @FXML protected void selectButtonPressed()
  {
    selectedIngredientList.clear();
    for (int i = 0; i < ingredientTable.getItems().size(); i++)
    {
      if (ingredientTable.getItems().get(i).getSelect().isSelected())
        selectedIngredientList.add(ingredientTable.getItems().get(i));
    }
    viewModel.getRecipeByIngredients();
  }

  @FXML protected void recipeChangeListener()
  {
    searchRecipeTextField.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containRecipe(searchRecipeTextField.getText()));
  }

  @FXML protected void ingredientChangeListener()
  {
    searchIngredientTextField.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containIngredient(searchIngredientTextField.getText()));
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  @Override public Region getRoot()
  {
    return root;
  }
}
