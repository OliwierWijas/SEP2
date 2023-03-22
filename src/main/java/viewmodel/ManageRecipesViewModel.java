package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.Model;
import model.Person;
import model.Recipe;

public class ManageRecipesViewModel
{
  private final Model model;
  private Person person;
  private StringProperty searchRecipe;
  private final ListProperty<Recipe> recipesList;
  private final ObjectProperty<Recipe> recipe;
  private final ListProperty<Ingredient> ingredientsList;
  private final StringProperty error;

  private final StringProperty title;
  private final StringProperty description;

  public ManageRecipesViewModel(Model model)
  {
    this.model = model;
    this.person = null;
    this.searchRecipe = new SimpleStringProperty("");
    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.recipe = new SimpleObjectProperty<>();
    this.ingredientsList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.error = new SimpleStringProperty("");
    this.title = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
  }

  public void addRecipe()
  {
    try
    {
      model.addRecipe(title.get(), description.get(), null, person);
    } catch (Exception e)
    {
      this.error.set(e.getMessage());
    }
  }

  public void removeRecipe()
  {
    try
    {
      model.removeRecipe(recipe.get(), person);
    } catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public void bindTitle(StringProperty property)
  {
    this.title.bindBidirectional(property);
  }

  public void bindDescription(StringProperty property)
  {
    this.description.bindBidirectional(property);
  }

  public void bindError(StringProperty property)
  {
    property.bind(error);
  }

  public void bindRecipe(ReadOnlyObjectProperty<Recipe> property)
  {
    this.recipe.bind(property);
  }

  public void bindRecipeList(ObjectProperty<ObservableList<Recipe>> property)
  {
    property.bind(recipesList);
  }

  public void bindIngredientList(ObjectProperty<ObservableList<Ingredient>> property)
  {
    property.bind(ingredientsList);
  }

  public void reset()
  {
    title.set("");
    description.set("");
    error.set("");
  }

  public void resetRecipesList()
  {
    recipesList.setAll(model.getAllRecipes);
  }

  public void resetIngredientsList()
  {
    recipesList.setAll(model.getAllIngredients);
  }
}
