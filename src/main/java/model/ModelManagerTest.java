package model;

import java.util.ArrayList;

public class ModelManagerTest
{
  public static void main(String[] args)
  {
    Model model = new ModelManager();
    Member member1 = model.createAccount("email1", "username1", "password1");
    Member member2 = model.createAccount("email2", "username2", "password2");
    Member member3 = model.createAccount("email3", "username3", "password3");

    Ingredient tomato = new Ingredient("tomato");
    Ingredient potato = new Ingredient("potato");
    Ingredient sriracha = new Ingredient("sriracha");
    Ingredient greenBeans = new Ingredient("greenBeans");

    ArrayList<Ingredient> ingredients1 = new ArrayList<>();
    ingredients1.add(tomato);
    ingredients1.add(potato);
    ingredients1.add(sriracha);

    ArrayList<Ingredient> ingredients2 = new ArrayList<>();
    ingredients2.add(potato);
    ingredients2.add(greenBeans);

    Recipe recipe1 = new Recipe("title1", "desc1", member1.getUsername());
    recipe1.addAllIngredients(ingredients1);
    model.addRecipe("title1", "desc1", ingredients1, member1);
    Recipe recipe2 = new Recipe("title2", "desc2", member2.getUsername());
    recipe2.addAllIngredients(ingredients2);
    model.addRecipe("title2", "desc2", ingredients2, member2);
    model.addToFavourites(recipe1, member3);
    model.addToFavourites(recipe2, member1);

    System.out.println(member1.toString1());
    System.out.println("");
    System.out.println(member2.toString1());
    System.out.println("");
    System.out.println(member3.toString1());
    System.out.println("");

    System.out.println("------------------");

    System.out.println(recipe1.toString1());
    System.out.println(recipe2.toString1());

    System.out.println("DJFBSJGBEHJRGBEHJBGJEHGBLJEBGHE");
    System.out.println("");
    System.out.println(PersonList.getInstance());
    System.out.println("weklgnelr");
    System.out.println(RecipeList.getInstance());
  }
}
