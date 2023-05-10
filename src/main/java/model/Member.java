package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Member extends Person implements Serializable
{
  private final ArrayList<Recipe> favourites;

  public Member(String email, String username, String password)
  {
    super(email, username, password);
    this.favourites = new ArrayList<>();
  }

  /*public void addFavourite(Recipe recipe)
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
  }*/

  public boolean equals(Object obj)
  {
    if (obj ==  null || obj.getClass() != getClass())
    {
      return false;
    }

    Member other = (Member) obj;

    return super.equals(other);
  }

  /*public String toString1()
  {
    String temp = super.toString1() + "\nfavourites:";
    for (int i = 0; i < favourites.size(); i++)
    {
      temp += "\n" + favourites.get(i).toString1();
    }
    return temp;
  }*/
}
