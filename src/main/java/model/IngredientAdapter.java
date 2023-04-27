package model;

import javafx.scene.control.CheckBox;

public class IngredientAdapter implements IngredientInterface
{
  private final CheckBox select;
  private final Ingredient subject;

  public IngredientAdapter(Ingredient ingredient)
  {
    this.select = new CheckBox();
    this.subject = ingredient;
  }

  public Ingredient getSubject()
  {
    return subject;
  }

  @Override public String getName()
  {
    return subject.getName();
  }

  @Override public CheckBox getSelect()
  {
    return select;
  }

  public String toString()
  {
    return subject.getName();
  }

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    IngredientAdapter other = (IngredientAdapter) obj;
    return getName().equals(other.getName());
  }
}
