import java.sql.SQLException;

import model.Database;

public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test...");

		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			db.save();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			db.load();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			db.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
