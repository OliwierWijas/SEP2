package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class SearchRecipesViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public SearchRecipesViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    SearchRecipesViewController searchRecipesViewController = loader.getController();
    searchRecipesViewController.init(viewHandler, viewModelFactory.getSearchRecipesViewModel(), root);
    return searchRecipesViewController;
  }
}