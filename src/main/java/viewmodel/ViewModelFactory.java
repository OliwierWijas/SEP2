package viewmodel;

import model.Model;
import view.member.SearchFavouriteRecipesMemberViewLoader;

public class ViewModelFactory
{
  private final SearchRecipesViewModel searchRecipesViewModel;
  private final ManageRecipesViewModel manageRecipesViewModel;
  private final LoginViewModel loginViewModel;
  private final CreateAccountViewModel createAccountViewModel;
  private final DisplayRecipeViewModel displayRecipeViewModel;
  private final ManageProfilesViewModel manageProfilesViewModel;
  private final DisplayFavouriteRecipeViewModel displayFavouriteRecipeViewModel;
  private final SearchFavouriteRecipesViewModel searchFavouriteRecipesViewModel;
  public ViewModelFactory(Model model)
  {
    this.searchRecipesViewModel = new SearchRecipesViewModel(model);
    this.manageRecipesViewModel = new ManageRecipesViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.createAccountViewModel = new CreateAccountViewModel(model);
    this.displayRecipeViewModel = new DisplayRecipeViewModel(model);
    this.searchRecipesViewModel.addPropertyChangeListener(this.displayRecipeViewModel);
    this.manageProfilesViewModel = new ManageProfilesViewModel(model);
    this.displayFavouriteRecipeViewModel = new DisplayFavouriteRecipeViewModel(model);
    this.searchFavouriteRecipesViewModel = new SearchFavouriteRecipesViewModel(model);
    this.searchFavouriteRecipesViewModel.addPropertyChangeListener(displayFavouriteRecipeViewModel);

  }

  public SearchRecipesViewModel getSearchRecipesViewModel()
  {
    return searchRecipesViewModel;
  }

  public ManageRecipesViewModel getManageRecipesViewModel()
  {
    return manageRecipesViewModel;
  }

  public LoginViewModel getLoginViewModel()
  {
    return loginViewModel;
  }

  public CreateAccountViewModel getCreateAccountViewModel()
  {
    return createAccountViewModel;
  }

  public DisplayRecipeViewModel getDisplayRecipeViewModel()
  {
    return displayRecipeViewModel;
  }
  public ManageProfilesViewModel getManageProfilesViewModel(){
    return manageProfilesViewModel;
  }
  public DisplayFavouriteRecipeViewModel getDisplayFavouriteRecipeViewModel(){
    return displayFavouriteRecipeViewModel;
  }
  public SearchFavouriteRecipesViewModel getSearchFavouriteRecipesViewModel(){
    return searchFavouriteRecipesViewModel;
  }
}