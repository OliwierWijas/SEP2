package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import model.Ingredient;
import model.Model;
import model.Recipe;

public class ManageRecipesViewModel
{
  private final Model model;
  private final StringProperty searchRecipe;
  private final ListProperty<Recipe> recipesList;
  private final StringProperty title;
  private final StringProperty ingredient;
  private final ListProperty<Ingredient> ingredientsList;
  private final StringProperty description;
  private final ObjectProperty<Recipe> recipe;


  public ManageRecipesViewModel(Model model)
  {
    this.model = model;

    this.searchRecipe = new SimpleStringProperty("");
    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.title = new SimpleStringProperty("");
    this.ingredient = new SimpleStringProperty("");
    this.ingredientsList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.description = new SimpleStringProperty("");
    this.recipe = new SimpleObjectProperty<>();
  }

  public void bindRecipe(ReadOnlyObjectProperty<Recipe> property)
  {
    recipe.bind(property);
  }
}
