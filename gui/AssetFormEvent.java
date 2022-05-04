package gui;

import java.util.EventObject;

public class AssetFormEvent extends EventObject {

	private static final long serialVersionUID = 3791551637790612773L;
	
	private String name;
	private String makeCategory;
	private String model;
	private String serial_number;
	private String purchase_date;
	private String quantity;
	private String productID;
	private String expiry_date;
	private String reg_number;
	private Double value;
	private String condition_status;
	private String condition_description;
	
	
	public AssetFormEvent(Object source) {
		super(source);		
	}

	public AssetFormEvent(Object source, String name, String makeCategory, String model, String serial_number, String purchase_date, 
			String quantity, String productID, String expiry_date, String reg_number, Double value, String condition_status, String condition_description) {
		super(source);	
		
		this.name = name;
		this.makeCategory = makeCategory;
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
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCondition_status() {
		return condition_status;
	}

	public void setCondition_status(String condition_status) {
		this.condition_status = condition_status;
	}

	public String getCondition_description() {
		return condition_description;
	}

	public void setCondition_description(String condition_description) {
		this.condition_description = condition_description;
	}
	
}

