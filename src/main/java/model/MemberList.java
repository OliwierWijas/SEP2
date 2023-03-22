package model;

import java.util.ArrayList;

public class MemberList
{
  private static MemberList instance;
  private final ArrayList<Member> members;

  private MemberList()
  {
    this.members = new ArrayList<>();
  }

  public static synchronized MemberList getInstance()
  {
    if (instance == null)
    {
      instance = new MemberList();
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

  public void removeRecipe(Recipe recipe)
  {
    for (int i = 0; i < members.size(); i++)
    {
      for (int j = 0; j < members.get(i).getRecipes().size(); j++)
      {
        ArrayList<Recipe> temp = members.get(i).getRecipes();
        if (temp.get(j).equals(recipe))
          members.get(i).removeRecipe(temp.get(j));
      }
    }
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
}