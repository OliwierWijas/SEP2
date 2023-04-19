package model;

import javafx.scene.control.CheckBox;

public class Ingredient
{
  private final String name;
  private CheckBox select;

  public Ingredient(String name)
  {
    this.name = name;
    this.select = new CheckBox();
  }

  public String getName()
  {
    return name;
  }

  public boolean getSelect()
  {
    if(select.isSelected()) return true;
    return false;
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
