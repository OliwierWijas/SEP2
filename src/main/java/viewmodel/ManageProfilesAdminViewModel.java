package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import model.Model;
import model.Person;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ManageProfilesAdminViewModel implements ManageProfilesInterface, PropertyChangeListener
{
  private final Model model;
  private final ListProperty<Person> profilesList;
  private final ObjectProperty<Person> selectedProfile;
  private final StringProperty username;
  private final StringProperty email;
  private final StringProperty password;
  private final StringProperty error;

  public ManageProfilesAdminViewModel(Model model){
    this.model = model;
    this.model.addPropertyChangeListener(this);
    this.profilesList = new SimpleListProperty<>();
    this.selectedProfile = new SimpleObjectProperty<>();
    this.username = new SimpleStringProperty("");
    this.email = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }
  @Override public void bindUsername(StringProperty property)
  {
    property.bind(this.username);
  }

  @Override public void bindEmail(StringProperty property)
  {
    property.bind(this.email);
  }

  @Override public void bindPassword(StringProperty property)
  {
    property.bind(this.password);
  }

  @Override public void bindError(StringProperty property)
  {
    property.bind(this.error);
  }

  public void bindProfiles(ObjectProperty<ObservableList<Person>> property)
  {
    property.bindBidirectional(profilesList);
    resetProfiles();
  }

  public void bindProfile(ReadOnlyObjectProperty<Person> property)
  {
    this.selectedProfile.bind(property);
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

  @Override public void reset()
  {
    this.username.set("");
    this.email.set("");
    this.password.set("");
    this.error.set("");
  }

  public void resetProfiles()
  {
    this.profilesList.setAll(model.getAllMembers());
  }

  @Override public void showProfile()
  {
    if (selectedProfile.get() != null)
    {
      this.username.set(selectedProfile.get().getUsername());
      this.email.set(selectedProfile.get().getEmail());
      this.password.set(selectedProfile.get().getPassword());
    }
  }

  @Override public void deleteProfile()
  {
    if (selectedProfile.get() != null)
      model.deleteProfile(selectedProfile.get().getUsername());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("AccountCreated"))
      resetProfiles();
  }
}
