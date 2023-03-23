package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class ManageRecipesViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public ManageRecipesViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    ManageRecipesViewController manageRecipesViewController = loader.getController();
    manageRecipesViewController.init(viewHandler, viewModelFactory.getManageRecipesViewModel(), root);
    return manageRecipesViewController;
  }
}
