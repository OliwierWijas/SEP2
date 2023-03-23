package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import viewmodel.ManageRecipesViewModel;

public class ManageRecipesViewController implements ViewController
{
  private ViewHandler viewHandler;
  private ManageRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageRecipesViewModel manageRecipesViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = manageRecipesViewModel;
    this.root = root;
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
