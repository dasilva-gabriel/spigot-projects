package fr.lowtix.warcore.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.lowtix.warcore.managers.PlayerWrapper;

public class IgnoreTable {

	public static String usersTable = "warcore_ignore";
	public static String nameColumn = "username";
	public static String ignoreColumn = "ignores";
	
	public IgnoreTable createTable() {
		MySQL.prepareStatement("CREATE TABLE IF NOT EXISTS "+usersTable+" ("+nameColumn+" VARCHAR(16), "+ignoreColumn+" VARCHAR(255))");
		return this;
	}
	
	public void saveIgnores(PlayerWrapper wrapper) {
		MySQL.connect();
		try {
			if(MySQL.contains(usersTable, nameColumn, wrapper.getName(), false)){
				
				String delim = "-";
				String res = String.join(delim, wrapper.getIgnored());
				
				PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE "+usersTable+" SET "+ignoreColumn+" = ? WHERE "+nameColumn+" = ?");
				ps.setString(1, res);
				ps.setString(2, wrapper.getName());
				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadIgnores(PlayerWrapper wrapper) {
		MySQL.connect();
		try {
			if(MySQL.contains(usersTable, nameColumn, wrapper.getName(), false)){
				
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+usersTable+" WHERE "+nameColumn+" = ?");
				ps.setString(1, wrapper.getName());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					String s = result.getString(ignoreColumn);
					
					String str[] = s.split("-");
					ArrayList<String> al = new ArrayList<String>();
					for(String names: str) al.add(names);
					
					wrapper.setIgnored(al);
				}
				result.close();
				ps.close();
				
			} else {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO "+usersTable+" values(?, ?)");
				ps.setString(1, wrapper.getName());
				ps.setString(2, "");
				ps.execute();
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
