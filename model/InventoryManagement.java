package model;

import java.io.Serializable;

public class InventoryManagement implements Serializable {
	
	private static final long serialVersionUID = -2724244235543283426L;

	private static int count = 1;
	
	private String fname;
	private String lname;
	private String asset_name;
	private MakeCategory make;
	private String model;
	private QuantityCategory quantity;
	private String date_taken;
	private String date_returned;
	private String reg_number;
	private String projectLocation;
	
	
	public InventoryManagement(String fname, String lname, String asset_name,  MakeCategory make, String model, QuantityCategory quantity, String date_taken, String date_returned,
			 String reg_number, String projectLocation) {
		
		this.fname = fname;
		this.lname = lname;
		this.asset_name = asset_name;
		this.make = make;
		this.model = model;
		this.quantity = quantity;
		this.date_taken = date_taken;
		this.date_returned = date_returned;
		this.reg_number = reg_number;
		this.projectLocation = projectLocation;
		
		setCount(getCount() + 1);
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


	public MakeCategory getMake() {
		return make;
	}


	public void setMake(MakeCategory make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public QuantityCategory getQuantity() {
		return quantity;
	}


	public void setQuantity(QuantityCategory quantity) {
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


	public String getProjectLocation() {
		return projectLocation;
	}


	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}


	public String toString() {
		
		return fname + " " + lname + " " + asset_name + " " + make + " " + model + " " + quantity + " " + date_taken + " " + date_returned + " " + reg_number + " " + projectLocation;
	}


	public static int getCount() {
		return count;
	}


	public static void setCount(int count) {
		InventoryManagement.count = count;
	}
}
