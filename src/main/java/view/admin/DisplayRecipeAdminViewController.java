package view.admin;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.IngredientAdapter;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import view.menu.AdminMenuHandler;
import view.menu.MenuHandler;
import viewmodel.DisplayRecipeViewModel;

public class DisplayRecipeAdminViewController implements ViewController
{
  @FXML private Label title;
  @FXML private Label author;
  @FXML private ListView<IngredientAdapter> ingredientListView;
  @FXML private TextArea description;
  @FXML private Label error;

  private ViewHandler viewHandler;
  private MenuHandler menuHandler;
  private DisplayRecipeViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, DisplayRecipeViewModel displayRecipeViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.menuHandler = AdminMenuHandler.getInstance(viewHandler);
    this.viewModel = displayRecipeViewModel;
    this.root = root;

    this.viewModel.bindTitle(title.textProperty());
    this.viewModel.bindAuthor(author.textProperty());
    this.viewModel.bindIngredientsList(ingredientListView.itemsProperty());
    this.viewModel.bindDescription(description.textProperty());
    this.viewModel.bindError(error.textProperty());
  }

  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }

  @FXML protected void goBackButtonPressed()
  {
    reset();
    viewHandler.openView(ViewFactory.SEARCHADMIN);
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
