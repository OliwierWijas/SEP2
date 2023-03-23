package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Ingredient
{
  private final String name;
  private final SimpleStringProperty nameProperty;
  private CheckBox select;

  public Ingredient(String name)
  {
    this.name = name;
    this.nameProperty = new SimpleStringProperty(name);
    this.select = new CheckBox();
  }

  public String getName()
  {
    return name;
  }

  public void setNameProperty(String nameProperty)
  {
    this.nameProperty.set(nameProperty);
  }

  public String getNameProperty()
  {
    return nameProperty.get();
  }

  public CheckBox getSelect()
  {
    return select;
  }

  public void setSelect(CheckBox select)
  {
    this.select = select;
  }

  public String toString()
  {
    return name;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Ingredient other = (Ingredient) obj;
    return this.name.equals(other.name);
  }
}
