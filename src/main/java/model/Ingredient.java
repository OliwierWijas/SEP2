package model;

public class Ingredient
{
  private final String name;

  public Ingredient(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
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
