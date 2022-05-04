package gui;

import java.util.EventObject;

public class InventoryFormEvent extends EventObject {

	private static final long serialVersionUID = 1462105507744465862L;
	
	private String fname;
	private String lname;
	private String asset_name;
	private String makeCategory;
	private String model;
	private String quantity;
	private String date_taken;
	private String date_returned;
	private String reg_number;
	private String location;
	
	public InventoryFormEvent(Object source) {
		super(source);		
	}

	public InventoryFormEvent(Object source, String fname, String lname, String asset_name, String makeCategory, String model, 
			String quantity, String date_taken, String date_returned, String reg_number, String location) {
		super(source);	
		
		this.fname = fname;
		this.lname = lname;
		this.asset_name = asset_name;
		this.makeCategory = makeCategory;
		this.model = model;
		this.quantity = quantity;
		this.date_taken = date_taken;
		this.date_returned = date_returned;
		this.reg_number = reg_number;
		this.location = location;
		
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public String getMakeCategory() {
		return makeCategory;
	}

	public void setMakeCategory(String makeCategory) {
		this.makeCategory = makeCategory;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDate_taken() {
		return date_taken;
	}

	public void setDate_taken(String date_taken) {
		this.date_taken = date_taken;
	}

	public String getDate_returned() {
		return date_returned;
	}

	public void setDate_returned(String date_returned) {
		this.date_returned = date_returned;
	}

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

		
}

