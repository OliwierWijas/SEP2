package model;

import java.util.ArrayList;

public abstract class Person
{
  private final String email;
  private String username;
  private String password;
  private final ArrayList<Recipe> recipes;

  public Person(String email, String username, String password)
  {
    this.email = email;
    this.username = username;
    this.password = password;
    this.recipes = new ArrayList<>();
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmail()
  {
    return email;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public ArrayList<Recipe> getRecipes()
  {
    return recipes;
  }

  public void addRecipe(Recipe recipe)
  {
    this.recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe)
  {
    for (int i = 0; i < getRecipes().size(); i++)
    {
      if (getRecipes().get(i).equals(recipe))
        getRecipes().remove(getRecipes().get(i));
    }
  }

  public Recipe getRecipe(Recipe recipe)
  {
    for (int i = 0; i < recipes.size(); i++)
    {
      if (recipes.get(i).equals(recipe))
        return recipes.get(i);
    }
    throw new IllegalArgumentException("Recipe not found.");
  }

  public String toString()
  {
    return username;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Person other = (Person) obj;

    for (int i = 0; i < recipes.size(); i++)
    {
      if (!this.recipes.get(i).equals(other.recipes.get(i)))
        return false;
    }

    return this.email.equals(other.email) && this.username.equals(other.username) && this.password.equals(other.password);
  }
}
