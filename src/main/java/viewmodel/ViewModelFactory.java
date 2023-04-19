package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private final SearchRecipesViewModel searchRecipesViewModel;
  private final ManageRecipesViewModel manageRecipesViewModel;
  private final LoginViewModel loginViewModel;
  private final CreateAccountViewModel createAccountViewModel;

  public ViewModelFactory(Model model)
  {
    this.searchRecipesViewModel = new SearchRecipesViewModel(model);
    this.manageRecipesViewModel = new ManageRecipesViewModel(model);
    this.loginViewModel = new LoginViewModel(model);
    this.createAccountViewModel = new CreateAccountViewModel(model);
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
}