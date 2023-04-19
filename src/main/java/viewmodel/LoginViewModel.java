package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private final Model model;
  private final StringProperty username;
  private final StringProperty password;

  public LoginViewModel(Model model)
  {
    this.model = model;

    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");

  }
}
