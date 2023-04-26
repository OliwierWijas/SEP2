package view.member;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.IngredientAdapter;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import viewmodel.DisplayRecipeViewModel;
import javafx.scene.control.*;


public class DisplayRecipeMemberViewController implements ViewController
{
  @FXML private Label title;
  @FXML private Label author;
  @FXML private ListView<IngredientAdapter> ingredientListView;
  @FXML private TextArea description;
  @FXML private Label error;

  private ViewHandler viewHandler;
  private DisplayRecipeViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, DisplayRecipeViewModel displayRecipeViewModel, Region root)
  {
    this.viewHandler = viewHandler;
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
    if (event.getSource().toString().contains(ViewFactory.MANAGERECIPESMEMBER))
      viewHandler.openView(ViewFactory.MANAGERECIPESMEMBER);
    else if (event.getSource().toString().contains(ViewFactory.SEARCHMEMBER))
      viewHandler.openView(ViewFactory.SEARCHMEMBER);
  }

  @FXML protected void goBackButtonPressed()
  {
    reset();
    viewHandler.openView(ViewFactory.SEARCHMEMBER);
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
