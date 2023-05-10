package dao;

import model.Ingredient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAOImplementation implements IngredientDAO
{
  private static IngredientDAOImplementation instance;

  private IngredientDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized IngredientDAOImplementation getInstance() throws SQLException
  {
    if (instance == null) {
      instance = new IngredientDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=recipedatabase", "postgres", "dupaxyz13");
  }

  @Override public void createIngredients(ArrayList<Ingredient> ingredients) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      for (int i = 0; i < ingredients.size(); i++)
      {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Ingredient (name) VALUES (?) ON CONFLICT DO NOTHING");
        statement.setString(1, ingredients.get(i).getName());
        statement.executeUpdate();
      }
    }
  }
}
