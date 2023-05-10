package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable
{
  private final String email;
  private String username;
  private String password;

  public Person(String email, String username, String password)
  {
    this.email = email;
    this.username = username;
    this.password = password;
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

  /*public ArrayList<Recipe> getRecipes()
  {
    return recipes;
  }

  public void addRecipe(Recipe recipe)
  {
    this.recipes.add(recipe);
  }

  public void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients)
  {
    for (int i = 0; i < recipes.size(); i++)
    {
      if (recipes.get(i).equals(recipe))
      {
        recipes.get(i).setTitle(title);
        recipes.get(i).setDescription(description);
        recipes.get(i).setAllIngredients(ingredients);
      }
    }
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

  public String toString1()
  {
    String temp = username;
    for (int i = 0; i < recipes.size(); i++)
    {
      temp += "\n" + recipes.get(i).toString1();
    }
    return temp;
  }*/

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Person other = (Person) obj;

    return this.email.equals(other.email) && this.username.equals(other.username) && this.password.equals(other.password);
  }
}
