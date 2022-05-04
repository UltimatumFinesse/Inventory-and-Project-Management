package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	
		private List<AssetManagement> assettable;
		private List<InventoryManagement> inventorytable;
		private List<ProjectManagement> projecttable;
		
		private Connection con;
		private String url;
		
		public Database() {
			assettable = new LinkedList<AssetManagement>();
			inventorytable = new LinkedList<InventoryManagement>();
			projecttable = new LinkedList<ProjectManagement>();
		}
		
		///////////Connect Method////////////////////
		public void connect() throws Exception {
			
			if(con != null)return;  
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new Exception("Driver not found");
			}
			
			url = "jdbc:mysql://localhost:3306/assetsdb";
			con = DriverManager.getConnection(url, "root", "Maku_4891");
			System.out.println(con);
			System.out.println("Connected to the database");
		}
		
		///////////////Disconnect Method/////////////
		public void disconnect() throws Exception {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new Exception("Can't close the connection");
				}
			}
		}
		
		public void save() throws SQLException {
			
		
			String checkSql = "select count(*) as count from assettable where productID=?";
			PreparedStatement checkStmt = con.prepareStatement(checkSql);
			
			String checkInvSql = "select count(*) as count from inventorytable where asset_name=?";
			PreparedStatement checkInvStmt = con.prepareStatement(checkInvSql);
			
			String checkPrjSql = "select count(*) as count from projecttable where project_name=?";
			PreparedStatement checkPrjStmt = con.prepareStatement(checkPrjSql);
			
			String insertSql = "insert into assettable(name, make, model, serial_number, purchase_date, quantity, productID, expiry_date, reg_number, value, condition_status, condition_description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insertStmt = con.prepareStatement(insertSql);
			
			String insertInvSql = "insert into inventorytable(fname, lname, asset_name, make, model, quantity, date_taken, date_returned, reg_number, location) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insertInvStmt = con.prepareStatement(insertInvSql);
			
			String insertPrjSql = "insert into projecttable(project_name, location, client_name, contact_number, client_email, date_expected, date_submitted, date_signed, product, value, status, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insertPrjStmt = con.prepareStatement(insertPrjSql);
			
			String updateSql = "update assettable set name=?, make=?, model=?, serial_number=?, purchase_date=?, quantity=?, expiry_date=?, reg_number=?, value=?, condition_status=?, condition_description=? where productID=?";
			PreparedStatement updateStmt = con.prepareStatement(updateSql);
			
			String updateInvSql = "update inventorytable set fname=?, lname=?, make=?, model=?, quantity=?, date_taken=?, date_returned=?, reg_number=?, location=? where asset_name=?";
			PreparedStatement updateInvStmt = con.prepareStatement(updateInvSql);
			
			String updatePrjSql = "update projecttable set location=?, client_name=?, contact_number=?, client_email=?, date_expected=?, date_submitted=?, date_signed=?, product=?, value=?, status=?, description=? where project_name=?";
			PreparedStatement updatePrjStmt = con.prepareStatement(updatePrjSql);
			
			for( AssetManagement asset: assettable) {
				String productID = asset.getProduct();
				checkStmt.setString(1, productID);
				String name = asset.getName();
				MakeCategory make = asset.getMakeCategory();
				String model = asset.getModel();
				String serial_number = asset.getSerial();
				String purchase_date = asset.getDatebought();
				QuantityCategory quantity = asset.getQuantityCombo();
				String expiry_date = asset.getExpiry();
				String reg_number = asset.getRegistration();
				Double value = asset.getValue();
				String condition_status = asset.getConditionRadio();
				String condition_description = asset.getCondition();
				
				
				ResultSet checkResult = checkStmt.executeQuery();
				checkResult.next();
				
				int count = checkResult.getInt(1);
				
				if(count == 0) {
					System.out.println("Inserting asset with ID " + productID);
					
					int col = 1;
					
					insertStmt.setString(col++, name);
					insertStmt.setString(col++, make.name());
					insertStmt.setString(col++, model);
					insertStmt.setString(col++, serial_number);
					insertStmt.setString(col++, purchase_date);
					insertStmt.setString(col++, quantity.name());
					insertStmt.setString(col++, productID);
					insertStmt.setString(col++, expiry_date);
					insertStmt.setString(col++, reg_number);
					insertStmt.setDouble(col++, value);
					insertStmt.setString(col++, condition_status);
					insertStmt.setString(col++, condition_description);
					
					insertStmt.executeUpdate();
				}
				else {
					
					System.out.println("Updating asset with ID " + productID);
					
					int col = 1;
					
					updateStmt.setString(col++, name);
					updateStmt.setString(col++, make.name());
					updateStmt.setString(col++, model);
					updateStmt.setString(col++, serial_number);
					updateStmt.setString(col++, purchase_date);
					updateStmt.setString(col++, quantity.name());
					updateStmt.setString(col++, productID);
					updateStmt.setString(col++, expiry_date);
					updateStmt.setString(col++, reg_number);
					updateStmt.setDouble(col++, value);
					updateStmt.setString(col++, condition_status);
					updateStmt.setString(col++, condition_description);
					
					
					updateStmt.executeUpdate();
				}
				
			}
			
			updateStmt.close();
			insertStmt.close();
			checkStmt.close();
			
			for( InventoryManagement inventory: inventorytable) {
				String asset_name = inventory.getAsset_name();
				checkInvStmt.setString(1, asset_name);
				String fname = inventory.getFname();
				String lname = inventory.getLname();
				MakeCategory make = inventory.getMake();
				String model = inventory.getModel();
				QuantityCategory quantity = inventory.getQuantity();
				String date_taken = inventory.getDate_taken();
				String date_returned = inventory.getDate_returned();
				String reg_number = inventory.getReg_number();
				String location = inventory.getProjectLocation();
				
				ResultSet checkInvResult = checkInvStmt.executeQuery();
				checkInvResult.next();
				
				int count = checkInvResult.getInt(1);
				
				if(count == 0) {
					System.out.println("Inserting Inventory with Name " + asset_name);
					
					int col = 1;
					
					insertInvStmt.setString(col++, fname);
					insertInvStmt.setString(col++, lname);
					insertInvStmt.setString(col++, asset_name);
					insertInvStmt.setString(col++, make.name());
					insertInvStmt.setString(col++, model);
					insertInvStmt.setString(col++, quantity.name());
					insertInvStmt.setString(col++, date_taken);
					insertInvStmt.setString(col++, date_returned);
					insertInvStmt.setString(col++, reg_number);
					insertInvStmt.setString(col++, location);
					
					
					insertInvStmt.executeUpdate();
				}
				else {
					
					System.out.println("Updating Inventory with Name " + asset_name);
					
					int col = 1;
					
					updateInvStmt.setString(col++, fname);
					updateInvStmt.setString(col++, lname);
					updateInvStmt.setString(col++, asset_name);
					updateInvStmt.setString(col++, make.name());
					updateInvStmt.setString(col++, model);
					updateInvStmt.setString(col++, quantity.name());
					updateInvStmt.setString(col++, date_taken);
					updateInvStmt.setString(col++, date_returned);
					updateInvStmt.setString(col++, reg_number);
					updateInvStmt.setString(col++, location);
					
					
					updateInvStmt.executeUpdate();
				}
				
			}
			
			updateInvStmt.close();
			insertInvStmt.close();
			checkInvStmt.close();
			
			for( ProjectManagement project: projecttable) {
				
				String project_name = project.getProject_name();
				checkPrjStmt.setString(1, project_name);
				String location = project.getLocation();
				String client_name = project.getClient_name();
				String contact_number = project.getContact_number();
				String client_email = project.getClient_email();
				String date_expected = project.getDate_expected();
				String date_submitted = project.getDate_submitted();
				String date_signed = project.getDate_signed();
				String product = project.getProduct();
				Double value = project.getValue();
				String status = project.getStatus();
				String description = project.getDescription();
				
				
				ResultSet checkPrjResult = checkPrjStmt.executeQuery();
				checkPrjResult.next();
				
				int count = checkPrjResult.getInt(1);
				
				if(count == 0) {
					System.out.println("Inserting Project with Project Name " + project_name);
					
					int col = 1;
					
					insertPrjStmt.setString(col++, project_name);
					insertPrjStmt.setString(col++, location);
					insertPrjStmt.setString(col++, client_name);
					insertPrjStmt.setString(col++, contact_number);
					insertPrjStmt.setString(col++, client_email);
					insertPrjStmt.setString(col++, date_expected);
					insertPrjStmt.setString(col++, date_submitted);
					insertPrjStmt.setString(col++, date_signed);
					insertPrjStmt.setString(col++, product);
					insertPrjStmt.setDouble(col++, value);
					insertPrjStmt.setString(col++, status);
					insertPrjStmt.setString(col++, description);
					
					insertPrjStmt.executeUpdate();
				}
				else {
					
					System.out.println("Updating Project with Project Name " + project_name);
					
					int col = 1;
					
					updatePrjStmt.setString(col++, project_name);
					updatePrjStmt.setString(col++, location);
					updatePrjStmt.setString(col++, client_name);
					updatePrjStmt.setString(col++, contact_number);
					updatePrjStmt.setString(col++, client_email);
					updatePrjStmt.setString(col++, date_expected);
					updatePrjStmt.setString(col++, date_submitted);
					updatePrjStmt.setString(col++, date_signed);
					updatePrjStmt.setString(col++, product);
					updatePrjStmt.setDouble(col++, value);
					updatePrjStmt.setString(col++, status);
					updatePrjStmt.setString(col++, description);
					
					
					updatePrjStmt.executeUpdate();
				}
				
			}
			
			updatePrjStmt.close();
			insertPrjStmt.close();
			checkPrjStmt.close();
		}
		
		public void load() throws SQLException {
			
			
			assettable.clear();
			inventorytable.clear();
			projecttable.clear();
			
			String sql = "select name, make, model, serial_number, purchase_date, quantity, productID, expiry_date, reg_number, value, condition_status, condition_description from assettable order by name";
			Statement selectStmt = con.createStatement();
			
			String invSql = "select fname, lname, asset_name,  make, model, quantity, date_taken, date_returned, reg_number, location from inventorytable order by asset_name";
			Statement selectInvStmt = con.createStatement();
			
			String prjSql = "select project_name, location, client_name, contact_number, client_email, date_expected, date_submitted, date_signed, product, value, status, description from projecttable order by status";
			Statement selectPrjStmt = con.createStatement();
			
			ResultSet results = selectStmt.executeQuery(sql);
			ResultSet invResults = selectInvStmt.executeQuery(invSql);
			ResultSet prjResults = selectPrjStmt.executeQuery(prjSql);
			
			
			while(results.next()) {
				String name = results.getString("name");
				String make = results.getString("make");
				String model = results.getString("model");
				String serial_number = results.getString("serial_number");
				String purchase_date = results.getString("purchase_date");
				String quantity = results.getString("quantity");
				String productID = results.getString("productID");
				String expiry_date = results.getString("expiry_date");
				String reg_number = results.getString("reg_number");
				double value = results.getDouble("value");
				String condition_status = results.getString("condition_status");
				String condition_description = results.getString("condition_description");
				
				AssetManagement asset = new AssetManagement(name, MakeCategory.valueOf(make), model, serial_number, purchase_date, QuantityCategory.valueOf(quantity), productID, expiry_date, reg_number, value, condition_status, condition_description);
				assettable.add(asset);
				
			}
			results.close();
			selectStmt.close();
			
			while(invResults.next()) {
				
				String fname = invResults.getString("fname");
				String lname = invResults.getString("lname");
				String asset_name = invResults.getString("asset_name");
				String make = invResults.getString("make");
				String model = invResults.getString("model");
				String quantity = invResults.getString("quantity");
				String date_taken = invResults.getString("date_taken");
				String date_returned = invResults.getString("date_returned");
				String reg_number = invResults.getString("reg_number");
				String location = invResults.getString("location");
				
				
				InventoryManagement inventory = new InventoryManagement(fname, lname, asset_name, MakeCategory.valueOf(make), model, QuantityCategory.valueOf(quantity), date_taken, date_returned, reg_number, location);
				inventorytable.add(inventory);
				
			}
			invResults.close();
			selectInvStmt.close();
			
			while(prjResults.next()) {
				
				String project_name = prjResults.getString("project_name");
				String location = prjResults.getString("location");
				String client_name = prjResults.getString("client_name");
				String contact_number = prjResults.getString("contact_number");
				String client_email = prjResults.getString("client_email");
				String date_expected = prjResults.getString("date_expected");
				String date_submitted = prjResults.getString("date_submitted");
				String date_signed = prjResults.getString("date_signed");
				String product = prjResults.getString("product");
				double value = prjResults.getDouble("value");
				String status = prjResults.getString("status");
				String description = prjResults.getString("description");
				
				ProjectManagement project = new ProjectManagement(project_name, location, client_name, contact_number, client_email, date_expected, date_submitted, date_signed, product, value, status, description);
				projecttable.add(project);
				
			}
			prjResults.close();
			selectPrjStmt.close();
		}
		
		public void addAsset(AssetManagement asset) {
			assettable.add(asset);
		}
		
		public void removeAsset(int index) {
			assettable.remove(index);
		}
		
		public void addInventory(InventoryManagement inventory) {
			inventorytable.add(inventory);
		}
		
		public void removeInventory(int index) {
			inventorytable.remove(index);
		}
		public void addProject(ProjectManagement project) {
			projecttable.add(project);
		}
		
		public void removeProject(int index) {
			projecttable.remove(index);
		}
		
		public List<AssetManagement> getAsset(){
			return Collections.unmodifiableList(assettable);
		}
		
		public List<InventoryManagement> getInventory(){
			return Collections.unmodifiableList(inventorytable);
		}
		public List<ProjectManagement> getProject(){
			return Collections.unmodifiableList(projecttable);
		}
		
		public void saveToFile(File file) throws IOException {
			
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			FileOutputStream fosInv = new FileOutputStream(file);
			ObjectOutputStream oosInv = new ObjectOutputStream(fosInv);
			FileOutputStream fosPrj = new FileOutputStream(file);
			ObjectOutputStream oosPrj = new ObjectOutputStream(fosPrj);
			
			AssetManagement[] asset = assettable.toArray(new AssetManagement[assettable.size()]);
			InventoryManagement[] inventory = inventorytable.toArray(new InventoryManagement[inventorytable.size()]);
			ProjectManagement[] project = projecttable.toArray(new ProjectManagement[projecttable.size()]);
			
			oos.writeObject(asset);
			oosInv.writeObject(inventory);
			oosPrj.writeObject(project);
			oos.close();
			oosInv.close();
			oosPrj.close();
		}
		
		public void loadFromFile(File file) throws IOException {
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			FileInputStream fisInv = new FileInputStream(file);
			ObjectInputStream oisInv = new ObjectInputStream(fisInv);
			FileOutputStream fisPrj = new FileOutputStream(file);
			ObjectOutputStream oisPrj = new ObjectOutputStream(fisPrj);
			
			try {
				AssetManagement[] asset = (AssetManagement[]) ois.readObject();
				InventoryManagement[] inventory = (InventoryManagement[]) oisInv.readObject();
				ProjectManagement[] project = (ProjectManagement[]) ((ObjectInput) oisPrj).readObject();
				
				assettable.clear();
				inventorytable.clear();
				projecttable.clear();
				
				assettable.addAll(Arrays.asList(asset));
				inventorytable.addAll(Arrays.asList(inventory));
				projecttable.addAll(Arrays.asList(project));
				
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} 
			ois.close();
			oisInv.close();
			oisPrj.close();
			
		}
}
