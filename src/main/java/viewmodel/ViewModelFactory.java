package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private final SearchRecipesViewModel searchRecipesViewModel;
  private final ManageRecipesViewModel manageRecipesViewModel;

  public ViewModelFactory(Model model)
  {
    this.searchRecipesViewModel = new SearchRecipesViewModel(model);
    this.manageRecipesViewModel = new ManageRecipesViewModel(model);
  }

  public SearchRecipesViewModel getSearchRecipesViewModel()
  {
    return searchRecipesViewModel;
  }

  public ManageRecipesViewModel getManageRecipesViewModel()
  {
    return manageRecipesViewModel;
  }
}
