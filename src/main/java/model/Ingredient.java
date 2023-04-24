package model;

import java.io.Serializable;

public class Ingredient implements Serializable, IngredientInterface
{
  private final String name;
  private final boolean select;

  public Ingredient(String name)
  {
    this.name = name;
    this.select = false;
  }

  public String getName()
  {
    return name;
  }

  public Object getSelect()
  {
    return select;
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
