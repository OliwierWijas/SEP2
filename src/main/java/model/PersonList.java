package model;

import java.util.ArrayList;

public class PersonList
{
  private static PersonList instance;
  private final ArrayList<Member> members;
  private final Administrator administrator;

  private PersonList()
  {
    this.members = new ArrayList<>();
    this.administrator = Administrator.getInstance();
  }

  public static synchronized PersonList getInstance()
  {
    if (instance == null)
    {
      instance = new PersonList();
    }
    return instance;
  }

  public void addMember(Member member)
  {
    this.members.add(member);
  }

  public void removeMember(Member member)
  {
    for (int i = 0; i < members.size(); i++)
    {
      if (members.get(i).equals(member))
        this.members.remove(member);
    }
  }

  public void addRecipeToPerson(Recipe recipe, Person person)
  {
    if (person instanceof Member)
    {
      getMember(person).addRecipe(recipe);
    }
    else if (person instanceof Administrator)
    {
      this.administrator.addRecipe(recipe);
    }
    else
      throw new IllegalArgumentException("Person is not in the system.");
  }

  public void removeRecipeFromPerson(Recipe recipe, Person person)
  {
    if (person instanceof Member)
    {
      getMember(person).removeRecipe(recipe);
    }
    else if (person instanceof Administrator)
    {
      for (int i = 0; i < members.size(); i++)
      {
        for (int j = 0; j < members.get(i).getRecipes().size(); j++)
        {
          ArrayList<Recipe> temp = members.get(i).getRecipes();
          if (temp.get(j).equals(recipe))
          {
            members.get(i).removeRecipe(temp.get(j));
            return;
          }
        }
      }
      this.administrator.removeRecipe(recipe);
    }
    else
      throw new IllegalArgumentException("Person is not in the system.");
  }

  public void addToFavourites(Recipe recipe, Person person)
  {
    if (person instanceof Member)
    {
      getMember(person).addFavourite(recipe);
    }
    else if (person instanceof Administrator)
      throw new IllegalArgumentException("Administrator is not allowed to add a recipe to favourites.");
    else
      throw new IllegalArgumentException("Person is not in the system.");
  }

  public void removeFromFavourites(Recipe recipe, Person person)
  {
    if (person instanceof Member)
    {
      getMember(person).removeFavourite(recipe);
    }
    else if (person instanceof Administrator)
      throw new IllegalArgumentException("Administrator is not allowed to add a recipe to favourites.");
    else
      throw new IllegalArgumentException("Person is not in the system.");
  }

  private Member getMember(Person person)
  {
    if (person instanceof Member)
    {
      for (int i = 0; i < members.size(); i++)
      {
        if (members.get(i).equals(person))
          return members.get(i);
      }
    }
    throw new NullPointerException("Person is not in the system.");
  }

  public String toString()
  {
    String temp = "";
    for (int i = 0; i < members.size(); i++)
    {
      temp += members.get(i).toString1() + "\n\n";
    }
    return temp;
  }
}