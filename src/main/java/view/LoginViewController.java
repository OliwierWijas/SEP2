package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;

public class LoginViewController implements ViewController
{
  @FXML private TextField usernameTextField;
  @FXML private TextField passwordTextField;
  @FXML private Label error;

  private ViewHandler viewHandler;
  private LoginViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = loginViewModel;
    this.root = root;

    this.viewModel.bindUsername(usernameTextField.textProperty());
    this.viewModel.bindPassword(passwordTextField.textProperty());
    this.viewModel.bindError(error.textProperty());
  }

  @FXML protected void loginButtonPressed()
  {
    try
    {
      this.viewModel.login();
      viewHandler.openView(ViewFactory.SEARCH);
    }
    catch (Exception e)
    {
      reset();
    }
  }

  @FXML protected void signUpButtonPressed()
  {
    viewHandler.openView(ViewFactory.SIGNUP);
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
