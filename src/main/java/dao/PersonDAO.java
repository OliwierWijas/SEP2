package dao;

import model.Ingredient;
import model.Member;
import model.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDAO
{
  void createMember(Member member) throws SQLException;
  void removeMember(Member member) throws SQLException;
  ArrayList<Member> readMembers() throws SQLException;
  void addToFavourites(Recipe recipe, String username) throws SQLException;
  void removeFromFavourites(Recipe recipe, String username) throws SQLException;
  boolean login(String username, String password) throws SQLException;
  void rate(Recipe recipe, String username, int rate) throws SQLException;
}
