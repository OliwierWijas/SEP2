package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class ViewFactory
{
  public static final String SEARCHMEMBER = "SEARCHMEMBER";
  public static final String RECIPES = "RECIPES";
  public static final String LOGIN = "LOGIN";
  public static final String SIGNUP = "SIGNUP";
  public static final String DISPLAYRECIPEMEMBER = "DISPLAYRECIPEMEMBER";
  public static final String SEARCHGUEST = "SEARCHGUEST";
  public static final String DISPLAYRECIPEGUEST = "DISPLAYRECIPEGUEST";

  private final SearchRecipesMemberViewLoader searchRecipesMemberViewLoader;
  private final ManageRecipesViewLoader manageRecipesViewLoader;
  private final LoginViewLoader loginViewLoader;
  private final CreateAccountViewLoader createAccountViewLoader;
  private final DisplayRecipeMemberViewLoader displayRecipeMemberViewLoader;
  private final SearchRecipesGuestViewLoader searchRecipesGuestViewLoader;
  private final DisplayRecipeGuestViewLoader displayRecipeGuestViewLoader;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    searchRecipesMemberViewLoader = new SearchRecipesMemberViewLoader(
        "SearchRecipesMember.fxml", viewHandler, viewModelFactory);
    manageRecipesViewLoader = new ManageRecipesViewLoader("ManageRecipes.fxml", viewHandler, viewModelFactory);
    loginViewLoader = new LoginViewLoader("Login.fxml", viewHandler, viewModelFactory);
    createAccountViewLoader = new CreateAccountViewLoader("CreateAccount.fxml", viewHandler, viewModelFactory);
    displayRecipeMemberViewLoader = new DisplayRecipeMemberViewLoader("DisplayRecipeMember.fxml", viewHandler, viewModelFactory);
    searchRecipesGuestViewLoader = new SearchRecipesGuestViewLoader("SearchRecipesGuest.fxml", viewHandler, viewModelFactory);
    displayRecipeGuestViewLoader = new DisplayRecipeGuestViewLoader("DisplayRecipeGuest.fxml", viewHandler, viewModelFactory);
  }

  public Region load(String id)
  {
    return switch (id) {
      case SEARCHMEMBER -> searchRecipesMemberViewLoader.loadView();
      case RECIPES -> manageRecipesViewLoader.loadView();
      case LOGIN -> loginViewLoader.loadView();
      case SIGNUP -> createAccountViewLoader.loadView();
      case DISPLAYRECIPEMEMBER -> displayRecipeMemberViewLoader.loadView();
      case SEARCHGUEST -> searchRecipesGuestViewLoader.loadView();
      case DISPLAYRECIPEGUEST -> displayRecipeGuestViewLoader.loadView();
      default -> throw new IllegalArgumentException("Unknown id: " + id);
    };
  }
}
