package model;

public class Administrator extends Person
{
  private static final String USERNAME = "Administrator";
  private static Administrator instance;

  private Administrator(String email, String password)
  {
    super(email, USERNAME, password);
  }

  public static synchronized Administrator getInstance()
  {
    if (instance == null)
    {
      instance = new Administrator("admin", "admin");
    }
    return instance;
  }

  public String toString()
  {
    return USERNAME;
  }
}