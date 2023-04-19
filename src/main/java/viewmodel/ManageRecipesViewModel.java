package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ManageRecipesViewModel implements PropertyChangeListener
{
  private final Model model;
  private final ListProperty<Recipe> recipesList;
  private final StringProperty title;
  private final StringProperty ingredientName;
  private final ListProperty<Ingredient> ingredientsList;
  private final ObjectProperty<Ingredient> ingredient;
  private final StringProperty description;
  private final ObjectProperty<Recipe> recipe;
  private final StringProperty error;


  public ManageRecipesViewModel(Model model)
  {
    this.model = model;

    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.title = new SimpleStringProperty("");
    this.ingredientName = new SimpleStringProperty("");
    this.ingredientsList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.ingredient = new SimpleObjectProperty<>();
    this.description = new SimpleStringProperty("");
    this.recipe = new SimpleObjectProperty<>();
    this.error = new SimpleStringProperty("");
    resetRecipesList();
    this.model.addPropertyChangeListener(this);
  }

  public void addIngredient()
  {
    try
    {
      String name = ingredientName.get().toLowerCase();
      ingredientsList.add(new Ingredient(name));
      ingredientName.set("");
    } catch (Exception e)
    {
      this.error.set(e.getMessage());
    }
  }

  public void removeIngredient()
  {
    for (int i = 0; i < ingredientsList.size(); i++)
    {
      if (ingredient.get().equals(ingredientsList.get(i)))
        ingredientsList.remove(ingredientsList.get(i));
    }
  }

  public void addRecipe()
  {
    try
    {
      ArrayList<Ingredient> ingredients = new ArrayList<>();
      ingredients.addAll(ingredientsList);
      model.addRecipe(title.get(), description.get(), ingredients);
      reset();
    } catch (Exception e)
    {
      e.printStackTrace();
      this.error.set(e.getMessage());
    }
  }

  public void editRecipe()
  {
    try
    {
      ArrayList<Ingredient> ingredients = new ArrayList<>();
      ingredients.addAll(ingredientsList);
      model.editRecipe(recipe.get(), title.get(), description.get(), ingredients);
      reset();
    } catch (Exception e)
    {
      this.error.set(e.getMessage());
    }
  }

  public void removeRecipe()
  {
    try
    {
      model.removeRecipe(recipe.get());
      reset();
    } catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public void bindRecipeList(ObjectProperty<ObservableList<Recipe>> property)
  {
    property.bind(recipesList);
  }

  public void bindTitle(StringProperty property)
  {
    this.title.bindBidirectional(property);
  }

  public void bindIngredientName(StringProperty property)
  {
    this.ingredientName.bindBidirectional(property);
  }

  public void bindIngredientsList(ObjectProperty<ObservableList<Ingredient>> property)
  {
    property.bind(ingredientsList);
  }

  public void bindIngredient(ReadOnlyObjectProperty<Ingredient> property)
  {
    this.ingredient.bind(property);
  }

  public void bindDescription(StringProperty property)
  {
    this.description.bindBidirectional(property);
  }

  public void bindRecipe(ReadOnlyObjectProperty<Recipe> property)
  {
    this.recipe.bind(property);
  }

  public void bindError(StringProperty property)
  {
    property.bind(error);
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

  public void showRecipe()
  {
    this.title.set(recipe.get().getTitle());
    this.ingredientsList.setAll(recipe.get().getIngredients());
    this.description.set(recipe.get().getDescription());
  }

  public void reset()
  {
    title.set("");
    ingredientName.set("");
    description.set("");
    error.set("");
    ingredientsList.clear();
  }

  private void resetRecipesList()
  {
    // it should display recipes made by the user
    recipesList.setAll(model.getAllRecipes());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("ResetIngredients"))
      {
        if (!ingredientsList.contains((Ingredient) evt.getNewValue()))
          this.ingredientsList.add((Ingredient) evt.getNewValue());
      }
      else if (evt.getPropertyName().equals("ResetRecipes"))
      {
        resetRecipesList();
      }
    });
  }
}