package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.InventoryManagement;

public class InventoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -4091285244605284976L;

	private List<InventoryManagement> db;
	
	private String[] colNames = {"First Name","Last Name", "Asset Name", "Make", "Model", "Quantity Taken", "Date Taken", "Date Returned", "Registration Number",
			 "Project Location"};
	
	public InventoryTableModel(List<InventoryManagement> db) {
		this.db = db;
		
	}
	
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<InventoryManagement> db) {
		this.db = db;
	}
	public int getRowCount() {
		return db.size();
	}
	public int getColumnCount() {
		return 10;
	}
	public Object getValueAt(int row, int col) {
		InventoryManagement inventory = db.get(row);
		
		switch(col) {
		case 0:
			return inventory.getFname();
		case 1:
			return inventory.getLname();
		case 2:
			return inventory.getAsset_name();
		case 3:
			return inventory.getMake();
		case 4:
			return inventory.getModel();
		case 5:
			return inventory.getQuantity();
		case 6:
			return inventory.getDate_taken();
		case 7:
			return inventory.getDate_returned();
		case 8:
			return inventory.getReg_number();
		case 9: 
			return inventory.getProjectLocation();
	
		
		}
		return null;
	}

}
