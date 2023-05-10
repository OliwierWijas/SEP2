package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Person;

import java.util.ArrayList;
import java.util.Optional;

public class ManageProfilesViewModel
{
  private final Model model;
  private ObjectProperty<Person> profile;
  private final ListProperty<Person> profilesList;
  private final StringProperty username;
  private final StringProperty email;
  private final StringProperty password;
  private final StringProperty error;
  public ManageProfilesViewModel(Model model){
      this.model = model;
      this.profile = null;
      this.profilesList = new SimpleListProperty<>();
      this.username = new SimpleStringProperty("");
      this.email = new SimpleStringProperty("");
      this.password = new SimpleStringProperty("");
      this.error = new SimpleStringProperty("");
      this.profile = new SimpleObjectProperty<>();
  }

  public void bindUsername(StringProperty property)
  {
    property.bind(this.username);
  }

  public void bindEmail(StringProperty property)
  {
    property.bind(this.email);
  }

  public void bindPassword(StringProperty property)
  {
    property.bind(this.password);
  }

  public void bindError(StringProperty property)
  {
    property.bind(this.error);
  }

  public void bindProfiles(ObjectProperty<ObservableList<Person>> property)
  {
    property.bind(profilesList);
  }

  public void reset()
  {
    this.profile = null;
    this.username.set("");
    this.email.set("");
    this.password.set("");
    this.error.set("");
  }

  public void containProfile(String text)
  {
    ArrayList<Person> temp = model.getMembersCopy();
    profilesList.clear();
    for (int i = 0; i < temp.size(); i++)
    {
      if (temp.get(i).getUsername().contains(text))
       profilesList.add(temp.get(i));
    }
  }

  public void showProfile(){
    if (profile.get() != null)
    {
      ///
      this.profilesList.clear();
      this.username.set(profile.get().getUsername());
      this.email.set(profile.get().getEmail());
      this.password.set(profile.get().getPassword());
    }
  }

  public void deleteProfile(){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete the account?");
    Optional<ButtonType> option = alert.showAndWait();
    if (option.get() == ButtonType.OK)
      model.deleteProfile(this.profile.get().getUsername());
  }

  public void editProfile(){
    model.editProfile(this.username.get(), this.email.get(), this.password.get());
  }

  public void bindProfile(ReadOnlyObjectProperty<Person> property)
  {
    this.profile.bind(property);
  }
}
