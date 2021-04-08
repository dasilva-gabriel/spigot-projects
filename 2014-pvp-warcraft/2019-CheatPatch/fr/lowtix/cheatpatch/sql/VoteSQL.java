package fr.lowtix.cheatpatch.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.lowtix.cheatpatch.managers.VoteManager;

public class VoteSQL {
	
	private static final String TABLE_NAME = "warvotes";
	private static final String COLUMN_VOTES = "votes";
	private static final String COLUMN_LASTVOTE = "last_vote";
	
	public static void createTableIfNotExist() {
		MySQL.tryConnect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_VOTES+" INT(10), "+COLUMN_LASTVOTE+" VARCHAR(20))");
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void save(int votes, String last) {
		MySQL.tryConnect();
		try {
			
			System.out.println("DELETE");
			PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM "+TABLE_NAME);
			ps.execute();
			
			ps.close();
			
			System.out.println("SAVE");
			ps = MySQL.getConnection().prepareStatement("INSERT INTO "+TABLE_NAME+" values(?, ?)");
			ps.setInt(1, votes);
			ps.setString(2, last);
			ps.execute();
			
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			createTableIfNotExist();
		}
		
	}
	
	public static void load(VoteManager mng) {
		MySQL.tryConnect();
		
		try {
			
			System.out.println("LOAD");
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+TABLE_NAME);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				mng.setLast_vote(result.getString(COLUMN_LASTVOTE));
				mng.setVote(result.getInt(COLUMN_VOTES));
			}
			result.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
