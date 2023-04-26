package view;

import javafx.scene.layout.Region;
import view.admin.DisplayRecipeAdminViewLoader;
import view.admin.ManageRecipesAdminViewLoader;
import view.admin.SearchRecipesAdminViewLoader;
import view.guest.DisplayRecipeGuestViewLoader;
import view.guest.SearchRecipesGuestViewLoader;
import view.member.DisplayRecipeMemberViewLoader;
import view.member.ManageRecipesMemberViewLoader;
import view.member.SearchRecipesMemberViewLoader;
import viewmodel.ViewModelFactory;

public class ViewFactory
{
  public static final String SEARCHMEMBER = "SEARCHMEMBER";
  public static final String MANAGERECIPESMEMBER = "MANAGERECIPESMEMBER";
  public static final String LOGIN = "LOGIN";
  public static final String SIGNUP = "SIGNUP";
  public static final String DISPLAYRECIPEMEMBER = "DISPLAYRECIPEMEMBER";
  public static final String SEARCHGUEST = "SEARCHGUEST";
  public static final String DISPLAYRECIPEGUEST = "DISPLAYRECIPEGUEST";
  public static final String SEARCHADMIN = "SEARCHADMIN";
  public static final String DISPLAYRECIPEADMIN = "DISPLAYRECIPEADMIN";
  public static final String MANAGERECIPESADMIN = "MANAGERECIPESADMIN";

  private final SearchRecipesMemberViewLoader searchRecipesMemberViewLoader;
  private final ManageRecipesMemberViewLoader manageRecipesMemberViewLoader;
  private final LoginViewLoader loginViewLoader;
  private final CreateAccountViewLoader createAccountViewLoader;
  private final DisplayRecipeMemberViewLoader displayRecipeMemberViewLoader;
  private final SearchRecipesGuestViewLoader searchRecipesGuestViewLoader;
  private final DisplayRecipeGuestViewLoader displayRecipeGuestViewLoader;
  private final SearchRecipesAdminViewLoader searchRecipesAdminViewLoader;
  private final DisplayRecipeAdminViewLoader displayRecipeAdminViewLoader;
  private final ManageRecipesAdminViewLoader manageRecipesAdminViewLoader;

  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    searchRecipesMemberViewLoader = new SearchRecipesMemberViewLoader(
        "SearchRecipesMember.fxml", viewHandler, viewModelFactory);
    manageRecipesMemberViewLoader = new ManageRecipesMemberViewLoader("ManageRecipesMember.fxml", viewHandler, viewModelFactory);
    loginViewLoader = new LoginViewLoader("Login.fxml", viewHandler, viewModelFactory);
    createAccountViewLoader = new CreateAccountViewLoader("CreateAccount.fxml", viewHandler, viewModelFactory);
    displayRecipeMemberViewLoader = new DisplayRecipeMemberViewLoader("DisplayRecipeMember.fxml", viewHandler, viewModelFactory);
    searchRecipesGuestViewLoader = new SearchRecipesGuestViewLoader("SearchRecipesGuest.fxml", viewHandler, viewModelFactory);
    displayRecipeGuestViewLoader = new DisplayRecipeGuestViewLoader("DisplayRecipeGuest.fxml", viewHandler, viewModelFactory);
    searchRecipesAdminViewLoader = new SearchRecipesAdminViewLoader("SearchRecipesAdmin.fxml", viewHandler, viewModelFactory);
    displayRecipeAdminViewLoader = new DisplayRecipeAdminViewLoader("DisplayRecipeAdmin.fxml", viewHandler, viewModelFactory);
    manageRecipesAdminViewLoader = new ManageRecipesAdminViewLoader("ManageRecipesAdmin.fxml", viewHandler, viewModelFactory);
  }

  public Region load(String id)
  {
    return switch (id) {
      case SEARCHMEMBER -> searchRecipesMemberViewLoader.loadView();
      case MANAGERECIPESMEMBER -> manageRecipesMemberViewLoader.loadView();
      case LOGIN -> loginViewLoader.loadView();
      case SIGNUP -> createAccountViewLoader.loadView();
      case DISPLAYRECIPEMEMBER -> displayRecipeMemberViewLoader.loadView();
      case SEARCHGUEST -> searchRecipesGuestViewLoader.loadView();
      case DISPLAYRECIPEGUEST -> displayRecipeGuestViewLoader.loadView();
      case SEARCHADMIN -> searchRecipesAdminViewLoader.loadView();
      case DISPLAYRECIPEADMIN -> displayRecipeAdminViewLoader.loadView();
      case MANAGERECIPESADMIN -> manageRecipesAdminViewLoader.loadView();
      default -> throw new IllegalArgumentException("Unknown id: " + id);
    };
  }
}
