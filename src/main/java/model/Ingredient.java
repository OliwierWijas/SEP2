package model;

import javafx.scene.control.CheckBox;

import java.io.Serializable;

public class Ingredient implements Serializable
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
