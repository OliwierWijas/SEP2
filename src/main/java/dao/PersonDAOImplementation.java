package dao;

import model.Member;
import model.Recipe;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAOImplementation implements PersonDAO
{
  private static PersonDAOImplementation instance;

  private PersonDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized PersonDAOImplementation getInstance() throws SQLException
  {
    if (instance == null) {
      instance = new PersonDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=recipedatabase", "postgres", "dupaxyz13");
  }

  @Override public void createMember(Member member) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Person (username, email, password) VALUES (?, ?, ?);");
      statement.setString(1, member.getUsername());
      statement.setString(2, member.getEmail());
      statement.setString(3, member.getPassword());
      statement.executeUpdate();
    }
  }

  @Override public void removeMember(Member member) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Person WHERE username = ?;");
      statement.setString(1, member.getUsername());
      statement.executeUpdate();
    }
  }

  @Override public ArrayList<Member> readMembers() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE username != 'Administrator'");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Member> members = new ArrayList<>();
      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Member member = new Member(email, username, password);
        members.add(member);
      }
      return members;
    }
  }

  @Override public void addToFavourites(Recipe recipe, String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Favourites (recipeId, username) VALUES (?, ?);");
      statement.setInt(1, recipe.getId());
      statement.setString(2, username);
      statement.executeUpdate();
    }
  }

  @Override public void removeFromFavourites(Recipe recipe, String username) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Favourites WHERE recipeId = ? AND username = ?;");
      statement.setInt(1, recipe.getId());
      statement.setString(2, username);
      statement.executeUpdate();
    }
  }

  @Override public boolean login(String username, String password) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE username = ? AND password = ?;");
      statement.setString(1, username);
      statement.setString(2, password);
      ResultSet resultSet = statement.executeQuery();
      int count = 0;
      while (resultSet.next())
      {
        count++;
      }
      return count == 1;
    }
  }

  @Override public void rate(Recipe recipe, String username, int rate) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Rates (recipeId, username, rate) VALUES (?, ?, ?);");
      statement.setInt(1, recipe.getId());
      statement.setString(2, username);
      statement.setInt(3, rate);
      statement.executeUpdate();
    }
  }
}
