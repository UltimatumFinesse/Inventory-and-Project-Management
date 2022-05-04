package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.ProjectManagement;

public class ProjectTablePanel extends JPanel {

	private static final long serialVersionUID = -1989898295196908147L;
	
	public JTable table;
	private ProjectTableModel projectTableModel;
	private JPopupMenu popup;
	private ProjectTableListener projectTableListener;
	
	public ProjectTablePanel() {
		
		projectTableModel = new ProjectTableModel(null);
		table = new JTable(projectTableModel);
		popup = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				if(e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}		
		});
		
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				if(projectTableListener != null) {
					projectTableListener.rowDeleted(row);
					projectTableModel.fireTableRowsDeleted(row, row);
				}
			}
			
			
		});
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<ProjectManagement> db) {
		
		projectTableModel.setData(db);
	}
	public void refresh() {
		
		projectTableModel.fireTableDataChanged();
	}
	public void setProjectTableListener(ProjectTableListener listener) {
		this.projectTableListener = listener;
	}
}
