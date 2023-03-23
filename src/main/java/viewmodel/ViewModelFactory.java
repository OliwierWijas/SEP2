package viewmodel;

import model.Model;
import viewmodel.ManageRecipesViewModel;

public class ViewModelFactory
{
  private final ManageRecipesViewModel manageRecipesViewModel;

  public ViewModelFactory(Model model)
  {
    this.manageRecipesViewModel = new ManageRecipesViewModel(model);
  }

  public ManageRecipesViewModel getManageRecipesViewModel()
  {
    return manageRecipesViewModel;
  }
}
