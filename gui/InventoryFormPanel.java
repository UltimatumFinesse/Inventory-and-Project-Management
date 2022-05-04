package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class InventoryFormPanel extends JPanel {

	class DateLabelFormatter extends AbstractFormatter{

		private static final long serialVersionUID = -2696638083649036882L;

		private String datePattern = "dd-MMMM-yyyy";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		
		
		@Override
		public Object stringToValue(String text) throws ParseException {
			
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				
				Calendar cal = (Calendar)value;
				return dateFormatter.format(cal.getTime());
			}
				
				return "";
		}
		
		
	}
	
	private static final long serialVersionUID = 5260177380284355721L;
	
	private JLabel fnameLabel;
	private JLabel lnameLabel;
	private JLabel asset_nameLabel;
	private JLabel makeLabel;
	private JLabel modelLabel;
	private JLabel quantityLabel;
	private JLabel date_takenLabel;
	private JLabel date_returnedLabel;
	private JLabel reg_numberLabel;
	private JLabel locationLabel;

	private JTextField fnameField;
	private JTextField lnameField;
	private JTextField asset_nameField;
	private JTextField modelField;
	private JTextField reg_numberField;
	private JTextField locationField;
	
	
	private JButton submitButton;
	
	private InventoryFormListener formListener;
	
	private UtilDateModel modelDateTaken;
	private UtilDateModel modelDateReturned;
	private Properties p1;
	private Properties p2;
	private JDatePanelImpl dateTakenPanel;
	private JDatePanelImpl dateReturnedPanel;
	private JDatePickerImpl dateTaken;
	private JDatePickerImpl dateReturned;
	
	@SuppressWarnings("rawtypes")
	private JList makeList;
	@SuppressWarnings("rawtypes")
	private JComboBox quantityComboBox;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InventoryFormPanel() {
		Dimension Dim = getPreferredSize();
		Dim.width = 400;
		setPreferredSize(Dim);
		setMinimumSize(Dim);
		setVisible(false);
		
		fnameLabel = new JLabel("First Name: ");
		lnameLabel = new JLabel("Last Name: ");
		asset_nameLabel = new JLabel("Asset Name: ");
		makeLabel = new JLabel("Make: ");
		modelLabel = new JLabel("Model: ");
		quantityLabel = new JLabel("Quantity Taken: ");
		date_takenLabel = new JLabel("Date Taken: ");
		date_returnedLabel = new JLabel("Date Returned: ");
		reg_numberLabel = new JLabel("Registration Number: ");
		locationLabel = new JLabel("Location: ");
		
		submitButton = new JButton("Submit");
		
		fnameField = new JTextField(15);
		lnameField = new JTextField(15);
		asset_nameField = new JTextField(15);
		modelField = new JTextField(15);
		reg_numberField = new JTextField(15);
		locationField = new JTextField(15);
		
		makeList = new JList();
		quantityComboBox = new JComboBox();
		
		////Set up List box
		DefaultListModel makeModel = new DefaultListModel();
		makeModel.addElement("Home Equipment");
		makeModel.addElement("Office Equipment");
		makeModel.addElement("Vehicle Equipment");
		makeModel.addElement("Drone Equipment");
		makeModel.addElement("Mine Equipment");
		makeModel.addElement("GPS Equipment");
		
		makeList.setModel(makeModel);
		makeList.setPreferredSize(new Dimension (120, 115));
		makeList.setBorder(BorderFactory.createEtchedBorder());
		makeList.setSelectedIndex(3);
		
		///Set up Combo box
		DefaultComboBoxModel quantityModel = new DefaultComboBoxModel();
		quantityModel.addElement("1");
		quantityModel.addElement("2");
		quantityModel.addElement("3");
		quantityModel.addElement("4");
		quantityModel.addElement("5");
		
		quantityComboBox.setModel(quantityModel);
		quantityComboBox.setPreferredSize(new Dimension (50, 30));
		quantityComboBox.setBorder(BorderFactory.createEtchedBorder());
		quantityComboBox.setSelectedIndex(0);
		
	/////Set up a JDatePicker and JDatePanel
		
		modelDateTaken = new UtilDateModel();
		modelDateReturned = new UtilDateModel();
		p1 = new Properties();
		p2 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		dateTakenPanel = new JDatePanelImpl(modelDateTaken, p1);
		dateReturnedPanel = new JDatePanelImpl(modelDateReturned, p2);
		dateTaken = new JDatePickerImpl(dateTakenPanel, new DateLabelFormatter());
		dateReturned = new JDatePickerImpl(dateReturnedPanel, new DateLabelFormatter());
		
		///Set Up Submit Button Action Listener
		
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fname = fnameField.getText();
				String lname = lnameField.getText();
				String asset_name = asset_nameField.getText();
				String make = (String) makeList.getSelectedValue();
				String model = modelField.getText();
				String quantity = (String) quantityComboBox.getSelectedItem();
				String date_taken = dateTaken.getJFormattedTextField().getText();
				String date_returned = dateReturned.getJFormattedTextField().getText();
				String reg_number = reg_numberField.getText();
				String location = locationField.getText();
				
				InventoryFormEvent ev = new InventoryFormEvent(this, fname, lname, asset_name, make, model, quantity, date_taken, date_returned,
						reg_number, location);
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
			
		});
		Border innerBorder = BorderFactory.createTitledBorder("Inventory Management");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		
		//////////////First Name Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(fnameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(fnameField, gc);
		
		//////////////Last Name Row ///////////////
		
		gc.gridy++;
	
		gc.weightx = 1;
		gc.weighty = 0.1;
	
		gc.gridx = 0;
		gc.gridy = 1;
	
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(lnameLabel, gc);
	
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(lnameField, gc);
		
		//////////////Asset Name Row ///////////////
		
		gc.gridy++;
	
		gc.weightx = 1;
		gc.weighty = 0.1;
	
		gc.gridx = 0;
		gc.gridy = 2;
	
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(asset_nameLabel, gc);
	
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(asset_nameField, gc);
		
		//////////////Make Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(makeLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(makeList, gc);
		
	    //////////////Model Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(modelLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(modelField, gc);
	
		//////////////Quantity Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 5;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(quantityLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(quantityComboBox, gc);

		//////////////Seventh Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 6;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(date_takenLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateTaken, gc);
		
		//////////////Eighth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 7;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(date_returnedLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateReturned, gc);
		
		//////////////Ninth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy++;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(reg_numberLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(reg_numberField, gc);
		
		//////////////Tenth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy++;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(locationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(locationField, gc);
	
		//////////////Submit Button ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1.0;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(submitButton, gc);
	}
	
	public void setFormListener(InventoryFormListener listener) {
		this.formListener = listener;
	}
}



