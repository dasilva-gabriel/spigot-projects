package fr.lowtix.warcore.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ToggleTable {

	public static String usersTable = "warcore_toggle";
	public static String nameColumn = "username";

	public ToggleTable createTable() {
		MySQL.prepareStatement("CREATE TABLE IF NOT EXISTS " + usersTable + " (" + nameColumn + " VARCHAR(16))");
		return this;
	}

	public void setIgnoreAll(String name) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO "+ usersTable +" values(?)");
			ps.setString(1, name.toLowerCase());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeIgnoreAll(String name) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM "+ usersTable +" WHERE "+nameColumn+" = ?");
			ps.setString(1, name);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getAllIgnoreAll() {
	    List<String> names = new ArrayList<>();
	    MySQL.connect();
	    try {
	      PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM " + usersTable);
	      ResultSet result = ps.executeQuery();
	      while (result.next())
	        names.add(result.getString(nameColumn)); 
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return names;
	  }

}
