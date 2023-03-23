package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class ViewFactory
{
  public static final String SEARCH = "SEARCH";
  public static final String RECIPES = "RECIPES";

  private final SearchRecipesViewLoader searchRecipesViewLoader;
  private final ManageRecipesViewLoader manageRecipesViewLoader;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    searchRecipesViewLoader = new SearchRecipesViewLoader(
        "SearchRecipes.fxml", viewHandler, viewModelFactory);
    manageRecipesViewLoader = new ManageRecipesViewLoader("ManageRecipes.fxml", viewHandler, viewModelFactory);
  }

  public Region load(String id)
  {
    return switch (id) {
      case SEARCH -> searchRecipesViewLoader.loadView();
      case RECIPES -> manageRecipesViewLoader.loadView();
      default -> throw new IllegalArgumentException("Unknown id: " + id);
    };
  }
}
