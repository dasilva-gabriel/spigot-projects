package fr.lowtix.warbungee.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import fr.lowtix.warbungee.players.IgnoreWrapper;

public class IgnoreTable {
	
	private final String table_name = "warusers";
	private final String column_user = "username";
	private final String column_ignoreall = "ignoreall";
	private final String column_ignored = "ignored";

	public IgnoreTable() {
		createTable();
	}
	
	public void createTable() {
		
		LinkedHashMap<String, String> args = new LinkedHashMap<String, String>();
		
		args.put(column_user, "VARCHAR(16)");
		args.put(column_ignoreall, "INT(3)");
		args.put(column_ignored, "TEXT");
		
		MySQL.createTable(table_name, args, true);
	}
	
	private void insert(IgnoreWrapper wrapper) {
		
		MySQL.tryConnect();
		
		try {
			LinkedList<String> args = new LinkedList<String>();
			args.addLast(""+wrapper.getProfile().getName());
			args.addLast(wrapper.isIgnoreall() ? "1" : "0");
			args.addLast(wrapper.getSerial());
			
			MySQL.insert(table_name, args, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void save(IgnoreWrapper wrapper) {
		
		MySQL.tryConnect();
		
		try {
			if(!MySQL.contains(table_name, column_user, wrapper.getProfile().getName(), false)) {
				
				insert(wrapper);
				
			} else {
				
				LinkedHashMap<String, String> args = new LinkedHashMap<String, String>();
				args.put(column_ignoreall, wrapper.isIgnoreall() ? "1" : "0");
				args.put(column_ignored, wrapper.getSerial());
				
				MySQL.update(table_name, column_user, wrapper.getProfile().getName(), args, false);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void load(IgnoreWrapper wrapper) {
		
		try {
			if(!MySQL.contains(table_name, column_user, wrapper.getProfile().getName(), false)) {
				
				insert(wrapper);
				
			} else {
				
				PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+table_name+" WHERE "+column_user+" = ?");
				ps.setString(1, wrapper.getProfile().getName());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					
					wrapper.setIgnoreall(result.getString(column_ignoreall).equalsIgnoreCase("1") ? true : false);
					wrapper.loadBySerial(result.getString(column_ignored));
					
				}
				result.close();
				ps.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
