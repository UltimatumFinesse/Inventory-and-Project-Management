package model;

import java.io.Serializable;

public class AssetManagement implements Serializable {
	
	private static final long serialVersionUID = -1842539813517800786L;


	private static int count = 1;
	
	private String name;
	private MakeCategory make;
	private String model;
	private String serial_number;
	private String purchase_date;
	private QuantityCategory quantity;
	private String productID;
	private String expiry_date;
	private String reg_number;
	private Double value;
	private String condition_status;
	private String condition_description;
	
	public AssetManagement(String name, MakeCategory make, String model, String serial_number, String purchase_date, QuantityCategory quantity,
			 String productID, String expiry_date, String reg_number, Double value, String condition_status, String condition_description) {
		
		this.name = name;
		this.make = make;
		this.model = model;
		this.serial_number = serial_number;
		this.purchase_date = purchase_date;
		this.quantity = quantity;
		this.productID = productID;
		this.expiry_date = expiry_date;
		this.reg_number = reg_number;
		this.value = value;
		this.condition_status = condition_status;
		this.condition_description = condition_description;
		
		
		count++;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		AssetManagement.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MakeCategory getMakeCategory() {
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

	public String getSerial() {
		return serial_number;
	}

	public void setSerial(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getDatebought() {
		return purchase_date;
	}

	public void setDatebought(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public QuantityCategory getQuantityCombo() {
		return quantity;
	}

	public void setQuantityCombo(QuantityCategory quantity) {
		this.quantity = quantity;
	}

	public String getProduct() {
		return productID;
	}

	public void setProduct(String productID) {
		this.productID = productID;
	}

	public String getExpiry() {
		return expiry_date;
	}

	public void setExpiry(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getRegistration() {
		return reg_number;
	}

	public void setRegistration(String reg_number) {
		this.reg_number = reg_number;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getConditionRadio() {
		return condition_status;
	}

	public void setConditionRadio(String condition_status) {
		this.condition_status = condition_status;
	}

	public String getCondition() {
		return condition_description;
	}

	public void setCondition(String condition_description) {
		this.condition_description = condition_description;
	}
	
	public String toString() {
		
		return name + "\nMake: " + make + "\nModel:" + model + "\nSerial:" + serial_number + "\nDate of Purchase:" + purchase_date + ":" + quantity + ":" + productID + ":" + expiry_date + " >>> " + reg_number + ":" + value + ":" + condition_status + ":" + condition_description ;
	}
}
