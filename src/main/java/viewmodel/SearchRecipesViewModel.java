package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.Model;
import model.Person;
import model.Recipe;

import java.util.ArrayList;

public class SearchRecipesViewModel
{
  private final Model model;
  private Person person;
  private StringProperty searchRecipe;
  private final ListProperty<Recipe> recipesList;
  private final ObjectProperty<Recipe> recipe;
  private final ListProperty<Ingredient> ingredientList;
  private final SimpleListProperty<Ingredient> selectedIngredientList;
  private final StringProperty error;
  private final StringProperty title;
  private final StringProperty description;

  public SearchRecipesViewModel(Model model)
  {
    this.model = model;
    this.person = null;

    this.searchRecipe = new SimpleStringProperty("");
    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.recipe = new SimpleObjectProperty<>();
    this.ingredientList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.selectedIngredientList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.error = new SimpleStringProperty("");
    this.title = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    resetIngredientList();
    resetRecipesList();
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

  public void getRecipeByIngredients()
  {
    if (!selectedIngredientList.isEmpty())
    {
      ArrayList<Ingredient> ingredientsTemp = new ArrayList<>();
      ingredientsTemp.addAll(selectedIngredientList);

      ArrayList<Recipe> temp = model.getAllRecipes();
      recipesList.clear();
      for (int i = 0; i < temp.size(); i++)
      {
        if (temp.get(i).ifRecipeContainsIngredients(ingredientsTemp))
          recipesList.add(temp.get(i));
      }
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

  public void bindRecipe()
  {

  }

  public void bindIngredientList(ObjectProperty<ObservableList<Ingredient>> property)
  {
    property.bind(ingredientList);
  }

  public void bindSelectedIngredientList(SimpleListProperty<Ingredient> property)
  {
    this.selectedIngredientList.bind(property);
  }

  public void containRecipe(String text)
  {
    ArrayList<Recipe> temp = model.getAllRecipes();
    recipesList.clear();
    for (int i = 0; i < temp.size(); i++)
    {
      if (temp.get(i).getTitle().contains(text))
        recipesList.add(temp.get(i));
    }
  }

  public void containIngredient(String text)
  {
    ArrayList<Ingredient> temp = model.getAllIngredients();
    ingredientList.clear();
    for (int i = 0; i < temp.size(); i++)
    {
      if (temp.get(i).getName().contains(text))
        ingredientList.add(temp.get(i));
    }
  }

  public void reset()
  {
    title.set("");
    description.set("");
    error.set("");
  }

  public void resetRecipesList()
  {
    recipesList.setAll(model.getAllRecipes());
  }

  public void resetIngredientList()
  {
    ingredientList.setAll(model.getAllIngredients());
  }
}
