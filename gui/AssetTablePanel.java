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

import model.AssetManagement;

public class AssetTablePanel extends JPanel {
	
	private static final long serialVersionUID = -3019308587556545798L;
	
	public JTable table;
	private AssetTableModel assetTableModel;
	private JPopupMenu popup;
	private AssetTableListener assetTableListener;
	
	public AssetTablePanel() {
		
		assetTableModel = new AssetTableModel(null);
		table = new JTable(assetTableModel);
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
				
				if(assetTableListener != null) {
					assetTableListener.rowDeleted(row);
					assetTableModel.fireTableRowsDeleted(row, row);
				}
			}
			
			
		});
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<AssetManagement> db) {
		
		assetTableModel.setData(db);
	}
	public void refresh() {
		
		assetTableModel.fireTableDataChanged();
	}
	public void setAssetTableListener(AssetTableListener listener) {
		this.assetTableListener = listener;
	}
}
