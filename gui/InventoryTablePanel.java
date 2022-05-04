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

import model.InventoryManagement;


public class InventoryTablePanel extends JPanel {

	private static final long serialVersionUID = -7514176356738257139L;
	
	public JTable table;
	private InventoryTableModel inventoryTableModel;
	private JPopupMenu popup;
	private InventoryTableListener inventoryTableListener;
	
	public InventoryTablePanel() {
		
		inventoryTableModel = new InventoryTableModel(null);
		table = new JTable(inventoryTableModel);
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
				
				if(inventoryTableListener != null) {
					inventoryTableListener.rowDeleted(row);
					inventoryTableModel.fireTableRowsDeleted(row, row);
				}
			}	
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		}
		
		public void setData(List<InventoryManagement> db) {
			
			inventoryTableModel.setData(db);
		}
		public void refresh() {
			
			inventoryTableModel.fireTableDataChanged();
		}
		public void setInventoryTableListener(InventoryTableListener listener) {
			this.inventoryTableListener = listener;
		}
}


