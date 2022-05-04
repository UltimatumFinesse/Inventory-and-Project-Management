package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ProjectManagement;

public class ProjectTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8948474377603771891L;

	private List<ProjectManagement> db;
	
	private String[] colNames = {"Project Name", "Location", "Client Name", "Contact Number", "Client Email", "Date Expected", "Date Submitted", "Date Signed",
			 "Product", "Value",  "Status", "Description"};
	
	public ProjectTableModel(List<ProjectManagement> db) {
		this.db = db;
		
	}
	
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<ProjectManagement> db) {
		this.db = db;
	}
	public int getRowCount() {
		return db.size();
	}
	public int getColumnCount() {
		return 12;
	}
	public Object getValueAt(int row, int col) {
		ProjectManagement project = db.get(row);
		
		switch(col) {
		case 0:
			return project.getProject_name();
		case 1:
			return project.getLocation();
		case 2:
			return project.getClient_name();
		case 3:
			return project.getContact_number();
		case 4:
			return project.getClient_email();
		case 5:
			return project.getDate_expected();
		case 6:
			return project.getDate_submitted();
		case 7:
			return project.getDate_signed();
		case 8:
			return project.getProduct();
		case 9: 
			return project.getValue();
		case 10:
			return project.getStatus();
		case 11:
			return project.getDescription();
		
		}
		return null;
	}

}
