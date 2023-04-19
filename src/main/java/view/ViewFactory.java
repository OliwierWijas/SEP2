package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class ViewFactory
{
  public static final String SEARCH = "SEARCH";
  public static final String RECIPES = "RECIPES";
  public static final String LOGIN = "LOGIN";
  public static final String SIGNUP = "SIGNUP";

  private final SearchRecipesViewLoader searchRecipesViewLoader;
  private final ManageRecipesViewLoader manageRecipesViewLoader;
  private final LoginViewLoader loginViewLoader;
  private final CreateAccountViewLoader createAccountViewLoader;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    searchRecipesViewLoader = new SearchRecipesViewLoader(
        "SearchRecipes.fxml", viewHandler, viewModelFactory);
    manageRecipesViewLoader = new ManageRecipesViewLoader("ManageRecipes.fxml", viewHandler, viewModelFactory);
    loginViewLoader = new LoginViewLoader("Login.fxml", viewHandler, viewModelFactory);
    createAccountViewLoader = new CreateAccountViewLoader("CreateAccount.fxml", viewHandler, viewModelFactory);
  }

  public Region load(String id)
  {
    return switch (id) {
      case SEARCH -> searchRecipesViewLoader.loadView();
      case RECIPES -> manageRecipesViewLoader.loadView();
      case LOGIN -> loginViewLoader.loadView();
      case SIGNUP -> createAccountViewLoader.loadView();
      default -> throw new IllegalArgumentException("Unknown id: " + id);
    };
  }
}
