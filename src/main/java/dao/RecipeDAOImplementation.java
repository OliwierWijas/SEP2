package dao;

import model.Ingredient;
import model.Recipe;

import java.sql.*;
import java.util.ArrayList;

public class RecipeDAOImplementation implements RecipeDAO
{
  private static RecipeDAOImplementation instance;

  private RecipeDAOImplementation() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RecipeDAOImplementation getInstance() throws SQLException
  {
    if (instance == null) {
      instance = new RecipeDAOImplementation();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=recipedatabase", "postgres", "dupaxyz13");
  }

  @Override public void addRecipeToPerson(Recipe recipe, String username) throws
      SQLException
  {
    // not tested
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Recipe (username, title, description) VALUES  (?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, username);
      statement.setString(2, recipe.getTitle());
      statement.setString(3, recipe.getDescription());
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        int id = keys.getInt(1);
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients)
        {
          PreparedStatement statement1 = connection.prepareStatement(
              "INSERT INTO IngredientsInRecipe (recipeId, ingredient_name, amount, amount_type) VALUES (?, ?, ?, ?);");
          statement1.setInt(1, id);
          statement1.setString(2, ingredient.getName());
          statement1.setDouble(3, 0.00);
          statement1.setString(4, null);
          statement1.executeUpdate();
        }
      }
    }
  }

  @Override
  public void editPersonRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("UPDATE Recipe SET title = ?, description = ? WHERE recipeId = ?;");
      statement.setString(1, title);
      statement.setString(2, description);
      statement.setInt(3, recipe.getId());
      statement.executeUpdate();

      PreparedStatement statement1 = connection.prepareStatement("DELETE FROM IngredientsInRecipe WHERE recipeId = ?;");
      statement1.setInt(1, recipe.getId());
      statement1.executeUpdate();

      for (Ingredient ingredient : ingredients)
      {
        PreparedStatement statement2 = connection.prepareStatement(
            "INSERT INTO IngredientsInRecipe (recipeId, ingredient_name, amount, amount_type) VALUES (?, ?, ?, ?);");
        statement2.setInt(1, recipe.getId());
        statement2.setString(2, ingredient.getName());
        statement2.setDouble(3, 0.00);
        statement2.setString(4, null);
        statement2.executeUpdate();
      }
    }
  }

  @Override public void removeRecipe(Recipe recipe) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM Recipe WHERE recipeId = ?;");
      statement.setInt(1, recipe.getId());
      statement.executeUpdate();
    }
  }
}
