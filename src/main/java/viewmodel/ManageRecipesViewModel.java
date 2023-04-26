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
  private final StringProperty author;
  private final StringProperty ingredientName;
  private final ListProperty<IngredientAdapter> ingredientsList;
  private final ObjectProperty<IngredientAdapter> ingredient;
  private final StringProperty description;
  private final ObjectProperty<Recipe> recipe;
  private final StringProperty error;


  public ManageRecipesViewModel(Model model)
  {
    this.model = model;

    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.title = new SimpleStringProperty("");
    this.author = new SimpleStringProperty("");
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
      ingredientsList.add(new IngredientAdapter(new Ingredient(name)));
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

      for (int i = 0; i < ingredientsList.size(); i++)
      {
        ingredients.add(ingredientsList.get(i).getSubject());
      }

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

      for (int i = 0; i < ingredientsList.size(); i++)
      {
        ingredients.add(ingredientsList.get(i).getSubject());
      }

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

  public void bindAuthor(StringProperty property)
  {
    property.bind(author);
  }

  public void bindIngredientName(StringProperty property)
  {
    this.ingredientName.bindBidirectional(property);
  }

  public void bindIngredientsList(ObjectProperty<ObservableList<IngredientAdapter>> property)
  {
    property.bind(ingredientsList);
  }

  public void bindIngredient(ReadOnlyObjectProperty<IngredientAdapter> property)
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
    // model.getRecipesByUsername();
    // we have username in the client class
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
    this.ingredientsList.clear();
    this.title.set(recipe.get().getTitle());

    for (int i = 0; i < recipe.get().getIngredients().size(); i++)
    {
      this.ingredientsList.add(new IngredientAdapter(recipe.get().getIngredients().get(i)));
    }

    this.description.set(recipe.get().getDescription());
    this.author.set(recipe.get().getUsername());
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
    // model.getRecipesByUsername();
    // we have username in the client class
    recipesList.setAll(model.getAllRecipes());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("ResetIngredients"))
      {
        if (!ingredientsList.contains((Ingredient) evt.getNewValue()))
          this.ingredientsList.add(new IngredientAdapter((Ingredient) evt.getNewValue()));
      }
      else if (evt.getPropertyName().equals("ResetRecipes"))
      {
        resetRecipesList();
      }
    });
  }
}