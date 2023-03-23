package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class ViewFactory
{
  public static final String MAIN = "MAIN";

  private final ManageRecipesViewLoader manageRecipesViewLoader;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    manageRecipesViewLoader = new ManageRecipesViewLoader(
        "ManageRecipes.fxml", viewHandler, viewModelFactory);
  }

  public Region load(String id)
  {
    return switch (id) {
      case MAIN -> manageRecipesViewLoader.loadView();
      default -> throw new IllegalArgumentException("Unknown id: " + id);
    };
  }
}
