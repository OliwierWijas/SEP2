package view;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.Ingredient;
import model.IngredientList;
import viewmodel.ManageRecipesViewModel;

import java.util.ArrayList;

public class ManageRecipesViewController implements ViewController
{
  @FXML private TableView<Ingredient> ingredientTable;
  @FXML private TableColumn<Ingredient, String> nameCell;
  @FXML private TableColumn<Ingredient, CheckBox> selectCell;
  @FXML private SimpleListProperty<Ingredient> selectedIngredientList;

  private ViewHandler viewHandler;
  private ManageRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageRecipesViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.selectedIngredientList = new SimpleListProperty<>();
    this.nameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.selectCell.setCellValueFactory(new PropertyValueFactory<>("select"));
    this.viewModel.bindIngredientList(ingredientTable.itemsProperty());
    this.viewModel.bindSelectedIngredientList(selectedIngredientList);
  }

  @FXML protected void selectButtonPressed()
  {
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
