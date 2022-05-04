package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui.AssetFormEvent;
import gui.InventoryFormEvent;
import gui.ProjectFormEvent;
import model.AssetManagement;
import model.Database;
import model.InventoryManagement;
import model.MakeCategory;
import model.ProjectManagement;
import model.QuantityCategory;

public class Controller {
	
	Database db = new Database();
	
	public List<AssetManagement> getAsset(){
		
		return db.getAsset();
	}
	public List<InventoryManagement> getInventory(){
		
		return db.getInventory();
	}
	public List<ProjectManagement> getProject(){
		
		return db.getProject();
	}
	
	public void removeAsset(int index) {
		
		db.removeAsset(index);
	}
	
	public void removeInventory(int index) {
		
		db.removeInventory(index);
	}
	public void removeProject(int index) {
		
		db.removeProject(index);
	}

	public void save() throws SQLException {
		
		db.save();
	}
	
	public void connect() throws Exception {
		
		db.connect();
	}
	
	public void disconnet() throws Exception {
		
		db.disconnect();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void addAsset(AssetFormEvent e) {
		String name = e.getName();
		String make = e.getMakeCategory();
		String model = e.getModel();
		String serial_number = e.getSerial_number();
		String purchase_date = e.getPurchase_date();
		String quantity = e.getQuantity();
		String productID = e.getProductID();
		String expiry_date = e.getExpiry_date();
		String reg_number = e.getReg_number();
		Double value = e.getValue();
		String condition_status = e.getCondition_status();
		String condition_description = e.getCondition_description();
			
		
		QuantityCategory quantityCategory;
		
		if(quantity.equals("1")) {
			
			quantityCategory = QuantityCategory.one;
		}
		else if(quantity.equals("2")) {
			
			quantityCategory = QuantityCategory.two;
		}
		else if(quantity.equals("3")) {
			
			quantityCategory = QuantityCategory.three;
		}
		else if(quantity.equals("4")) {
			
			quantityCategory = QuantityCategory.four;
		}
		else {
			
			quantityCategory = QuantityCategory.five;
		}
		
		MakeCategory makeCategory;
		
		if(make.equals("Home Equipment")) {
			
			makeCategory = MakeCategory.HomeEquipment;
		}
		else if(make.equals("Office Equipment")) {
			
			makeCategory = MakeCategory.OfficeEquipment;
		}
		else if(make.equals("Vehicle Equipment")) {
			
			makeCategory = MakeCategory.VehicleEquipment;
			
		}
		else if(make.equals("Drone Equipment")) {
			
			makeCategory = MakeCategory.DroneEquipment;
		}
		else if(make.equals("Mine Equipment")) {
			
			makeCategory = MakeCategory.MineEquipment;
		}
		else {
			
			makeCategory = MakeCategory.GPSEquipment;
			
		}
		
		
		AssetManagement asset = new AssetManagement(name, makeCategory, model, serial_number, purchase_date, quantityCategory, productID, expiry_date,
				reg_number, value, condition_status, condition_description);
		
		db.addAsset(asset);		
		
	}
	
	public void addInventory(InventoryFormEvent e) {
		String fname = e.getFname();
		String lname = e.getLname();
		String asset_name = e.getAsset_name();
		String make = e.getMakeCategory();
		String model = e.getModel();
		String quantity = e.getQuantity();
		String date_taken = e.getDate_taken();
		String date_returned = e.getDate_returned();
		String reg_number = e.getReg_number();
		String location = e.getLocation();
		
			
		
		QuantityCategory quantityCategory;
		
		if(quantity.equals("1")) {
			
			quantityCategory = QuantityCategory.one;
		}
		else if(quantity.equals("2")) {
			
			quantityCategory = QuantityCategory.two;
		}
		else if(quantity.equals("3")) {
			
			quantityCategory = QuantityCategory.three;
		}
		else if(quantity.equals("4")) {
			
			quantityCategory = QuantityCategory.four;
		}
		else {
			
			quantityCategory = QuantityCategory.five;
		}
		
		MakeCategory makeCategory;
		
		if(make.equals("Home Equipment")) {
			
			makeCategory = MakeCategory.HomeEquipment;
		}
		else if(make.equals("Office Equipment")) {
			
			makeCategory = MakeCategory.OfficeEquipment;
		}
		else if(make.equals("Vehicle Equipment")) {
			
			makeCategory = MakeCategory.VehicleEquipment;
			
		}
		else if(make.equals("Drone Equipment")) {
			
			makeCategory = MakeCategory.DroneEquipment;
		}
		else if(make.equals("Mine Equipment")) {
			
			makeCategory = MakeCategory.MineEquipment;
		}
		else {
			
			makeCategory = MakeCategory.GPSEquipment;
			
		}
		
		
		InventoryManagement inventory = new InventoryManagement(fname, lname, asset_name,  makeCategory, model, quantityCategory, date_taken, date_returned,
				  reg_number, location);
		
		db.addInventory(inventory);		
		
	}
	
	public void addProject(ProjectFormEvent e) {
		String project_name = e.getProject_name();
		String location = e.getLocation();
		String client_name = e.getClient_name();
		String contact_number = e.getContact_number();
		String client_email = e.getClient_email();
		String date_expected = e.getDate_expected();
		String date_submitted = e.getDate_submitted();
		String date_signed = e.getDate_signed();
		String product = e.getProduct();
		Double value = e.getValue();
		String status = e.getStatus();
		String description = e.getDescription();
			
		ProjectManagement project = new ProjectManagement(project_name, location, client_name, contact_number, client_email, date_expected, date_submitted, date_signed,
				product, value, status, description);
		
		db.addProject(project);		
		
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}
