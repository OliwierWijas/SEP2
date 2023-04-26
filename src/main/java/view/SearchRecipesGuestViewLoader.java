package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class SearchRecipesGuestViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public SearchRecipesGuestViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    SearchRecipesGuestViewController searchRecipesGuestViewController = loader.getController();
    searchRecipesGuestViewController.init(viewHandler, viewModelFactory.getSearchRecipesViewModel(), root);
    return searchRecipesGuestViewController;
  }
}
