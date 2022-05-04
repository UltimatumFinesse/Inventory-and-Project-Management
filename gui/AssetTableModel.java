package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.AssetManagement;

public class AssetTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = -8113382399598672396L;

	private List<AssetManagement> db;
	
	private String[] colNames = {"Name", "Make", "Model", "Serial", "Date of Purchase", "Quantity", "Product ID", "Expiry Date", "Registration Number",
			 "Value", "Condition", "Condition Description"};
	
	public AssetTableModel(List<AssetManagement> db) {
		this.db = db;
		
	}
	
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<AssetManagement> db) {
		this.db = db;
	}
	public int getRowCount() {
		return db.size();
	}
	public int getColumnCount() {
		return 12;
	}
	public Object getValueAt(int row, int col) {
		AssetManagement asset = db.get(row);
		
		switch(col) {
		case 0:
			return asset.getName();
		case 1:
			return asset.getMakeCategory();
		case 2:
			return asset.getModel();
		case 3:
			return asset.getSerial();
		case 4:
			return asset.getDatebought();
		case 5:
			return asset.getQuantityCombo();
		case 6:
			return asset.getProduct();
		case 7:
			return asset.getExpiry();
		case 8:
			return asset.getRegistration();
		case 9: 
			return asset.getValue();
		case 10:
			return asset.getConditionRadio();
		case 11:
			return asset.getCondition();
		
		}
		return null;
	}

}
