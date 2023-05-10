package view.member;

import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Person;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import view.menu.MemberMenuHandler;
import view.menu.MenuHandler;
import viewmodel.ManageProfilesViewModel;

public class ManageProfileMemberViewController implements ViewController
{
  @FXML private TextField username;
  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private Label error;

  private ViewHandler viewHandler;
  private MenuHandler menuHandler;
  private ManageProfilesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler,
      ManageProfilesViewModel manageProfilesViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.menuHandler = MemberMenuHandler.getInstance(viewHandler);
    this.viewModel = manageProfilesViewModel;
    this.root = root;
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindEmail(email.textProperty());
    this.viewModel.bindPassword(password.textProperty());
    this.viewModel.bindError(error.textProperty());
  }

  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }

  @FXML protected void deleteProfileButtonClicked()
  {
    viewModel.deleteProfile();
  }

  @FXML protected void editProfileButtonClicked()
  {
    viewModel.editProfile();
  }

  @Override public void reset()
  {
    this.viewModel.reset();
  }

  @Override public Region getRoot()
  {
    return root;
  }
}
