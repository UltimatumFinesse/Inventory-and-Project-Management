package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import controller.Controller;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = -2530553255357890124L;
	
	private Toolbar toolbar;
	private AssetFormPanel assetFormPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private AssetTablePanel assetTablePanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private JSplitPane splitPane;
	private JTabbedPane tabPane;
	private InventoryFormPanel inventoryFormPanel;
	private InventoryTablePanel inventoryTablePanel;
	private ProjectFormPanel projectFormPanel;
	private ProjectTablePanel projectTablePanel;
	private ImageIcon img;
	
	
	public MainFrame() {
		super("Ultimatum Manager");
		
		setLayout(new BorderLayout());
		
		img = new ImageIcon("C:\\Program Files\\Ultimatum Finesse\\Ultimatum Manager\\Icons\\UF.jpg");
		
		toolbar = new Toolbar();
		assetFormPanel = new AssetFormPanel();
		assetTablePanel = new AssetTablePanel();
		prefsDialog = new PrefsDialog(this);
		tabPane = new JTabbedPane();
		inventoryFormPanel = new InventoryFormPanel();
		inventoryTablePanel = new InventoryTablePanel();
		projectFormPanel = new ProjectFormPanel();
		projectTablePanel = new ProjectTablePanel();
		
		prefs = Preferences.userRoot().node("db");
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, assetFormPanel, tabPane);
		
		splitPane.setOneTouchExpandable(true);
		
		tabPane.addTab("Asset Management Database", assetTablePanel);
		tabPane.addTab("Inventory Management Database", inventoryTablePanel);
		tabPane.addTab("Project Management Database", projectTablePanel);
		
		controller = new Controller();
		
		assetTablePanel.setData(controller.getAsset());
		inventoryTablePanel.setData(controller.getInventory());
		projectTablePanel.setData(controller.getProject());
		
		assetTablePanel.setAssetTableListener(new AssetTableListener() {
			public void rowDeleted(int row) {
				controller.removeAsset(row);
			}
		});
		inventoryTablePanel.setInventoryTableListener(new InventoryTableListener() {
			public void rowDeleted(int row) {
				controller.removeInventory(row);
			}
		});
		projectTablePanel.setProjectTableListener(new ProjectTableListener() {
			public void rowDeleted(int row) {
				controller.removeProject(row);
			}
		});
		
		prefsDialog.setPrefsListener(new PrefsListener() {
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});
		
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		prefsDialog.setDefaults(user, password, port);
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new AssetFileFilter());
		
		setJMenuBar(createMenuBar());
		
		toolbar.setToolbarListener(new ToolbarListener() {

			public void saveEventOccured() {
				connect();
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			public void refreshEventOccured() {
				connect();
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				
				assetTablePanel.refresh();
				inventoryTablePanel.refresh();
				projectTablePanel.refresh();
			}		
		});
		
		assetFormPanel.setFormListener(new AssetFormListener() {
			public void formEventOccurred(AssetFormEvent e) {
				controller.addAsset(e);
				assetTablePanel.refresh();
			}
		});
		inventoryFormPanel.setFormListener(new InventoryFormListener() {
			public void formEventOccurred(InventoryFormEvent ev) {
				controller.addInventory(ev);
				inventoryTablePanel.refresh();
			}

		});
		projectFormPanel.setFormListener(new ProjectFormListener() {
			public void formEventOccurred(ProjectFormEvent ev) {
				controller.addProject(ev);
				projectTablePanel.refresh();
			}

		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					controller.disconnet();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to close database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				System.gc();
			}
			
		});
		
		add(toolbar, BorderLayout.PAGE_START);
		add(splitPane, BorderLayout.CENTER);
		setIconImage(img.getImage());
		setMinimumSize(new Dimension(1000, 850));
		setSize(1500,1000);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	private void connect() {
		
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to the database", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();

		///Creating Menus
		JMenu fileMenu = new JMenu("File");
		JMenu windowMenu = new JMenu("Window");
		JMenu helpMenu = new JMenu("Help");
		
		///Setting File Menu Items
		JCheckBoxMenuItem assetManagementItem = new JCheckBoxMenuItem("Asset Management Form");
		assetManagementItem.setSelected(true);
		JCheckBoxMenuItem inventoryManagementItem = new JCheckBoxMenuItem("Inventory Management Form");
		inventoryManagementItem.setSelected(false);
		JCheckBoxMenuItem projectManagementItem = new JCheckBoxMenuItem("Project Management Form");
		projectManagementItem.setSelected(false);
		
		
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem printDataItem = new JMenuItem("Print...");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		fileMenu.add(assetManagementItem);
		fileMenu.add(inventoryManagementItem);
		fileMenu.add(projectManagementItem);
		fileMenu.addSeparator();
		fileMenu.add(importDataItem);
		fileMenu.add(exportDataItem);
		fileMenu.add(printDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		///Setting Window Menu Items
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		JMenuItem showFormItem = new JMenuItem("Asset Summary...");
		
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		
		///Add Menus
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);
		
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}	
		});
		
		assetManagementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inventoryManagementItem.setSelected(false);
				projectManagementItem.setSelected(false);
				JCheckBoxMenuItem newItem = (JCheckBoxMenuItem) e.getSource();
				
				if(newItem.isSelected()) {
					
					splitPane.remove(inventoryFormPanel);
					splitPane.remove(projectFormPanel);
					splitPane.add(assetFormPanel);
					splitPane.setDividerLocation((int)assetFormPanel.getMinimumSize().getWidth());
					
				}
				assetFormPanel.setVisible(newItem.isSelected());	
			}
			
		});
		
		inventoryManagementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				assetManagementItem.setSelected(false);
				projectManagementItem.setSelected(false);
				JCheckBoxMenuItem newItem = (JCheckBoxMenuItem) e.getSource();
				
				if(newItem.isSelected()) {
					
					splitPane.remove(assetFormPanel);
					splitPane.remove(projectFormPanel);
					splitPane.add(inventoryFormPanel);
					splitPane.setDividerLocation((int)inventoryFormPanel.getMinimumSize().getWidth());
					
				}
				inventoryFormPanel.setVisible(newItem.isSelected());	
			}
			
		});
		
		projectManagementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inventoryManagementItem.setSelected(false);
				assetManagementItem.setSelected(false);
				JCheckBoxMenuItem newItem = (JCheckBoxMenuItem) e.getSource();
				
				if(newItem.isSelected()) {
					
					splitPane.remove(assetFormPanel);
					splitPane.remove(inventoryFormPanel);
					splitPane.add(projectFormPanel);
					splitPane.setDividerLocation((int)projectFormPanel.getMinimumSize().getWidth());
					
				}
				projectFormPanel.setVisible(newItem.isSelected());	
			}
			
		});
		
		///Set up Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		///Set up Accelerators
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		assetManagementItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		inventoryManagementItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		projectManagementItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						assetTablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not load data from file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		exportDataItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not save data to file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit App?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				
				if(action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					
					for(WindowListener listener: listeners) {
						listener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}
				
			}
			
		});
	
		return menuBar;
	}
}
