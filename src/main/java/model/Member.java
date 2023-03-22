package model;

import java.util.ArrayList;

public class Member extends Person
{
  private final ArrayList<Recipe> favourites;

  public Member(String email, String username, String password)
  {
    super(email, username, password);
    this.favourites = new ArrayList<>();
  }

  public void removeRecipe(Recipe recipe)
  {
    for (int i = 0; i < getRecipes().size(); i++)
    {
      if (getRecipes().get(i).equals(recipe))
        getRecipes().remove(getRecipes().get(i));
    }
  }

  public void addFavourite(Recipe recipe)
  {
    this.favourites.add(recipe);
  }

  public void removeFavourite(Recipe recipe)
  {
    for (int i = 0; i < favourites.size(); i++)
    {
      if (favourites.get(i).equals(recipe))
        favourites.remove(favourites.get(i));
    }
  }

  public ArrayList<Recipe> getFavourites()
  {
    return favourites;
  }

  public boolean equals(Object obj)
  {
    if (obj ==  null || obj.getClass() != getClass())
    {
      return false;
    }

    Member other = (Member) obj;

    for (int i = 0; i < favourites.size(); i++)
    {
      if (!favourites.get(i).equals(other.favourites.get(i)))
        return false;
    }

    return super.equals(other);
  }
}
