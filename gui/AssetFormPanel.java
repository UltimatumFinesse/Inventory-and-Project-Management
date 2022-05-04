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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AssetFormPanel extends JPanel {
	
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
	
	private static final long serialVersionUID = 4394324222821859304L;
	
	private JLabel nameLabel;
	private JLabel makeLabel;
	private JLabel modelLabel;
	private JLabel serial_numberLabel;
	private JLabel purchase_dateLabel;
	private JLabel quantityLabel;
	private JLabel productIDLabel;
	private JLabel expiry_dateLabel;
	private JLabel reg_numberLabel;
	private JLabel valueLabel;
	private JRadioButton conditionNew;
	private JRadioButton conditionUsed;
	private ButtonGroup conditionGroup;
	
	private JTextField nameField;
	private JTextField modelField;
	private JTextField serial_dateField;
	private JTextField productIDField;
	private JTextField reg_numberField;
	private JTextField valueField;
	private JTextField condition_descriptionField;
	
	private JButton submitButton;
	
	private AssetFormListener formListener;
	
	private UtilDateModel modelPurchaseDate;
	private UtilDateModel modelExpiryDate;
	private Properties p1;
	private Properties p2;
	private JDatePanelImpl purchaseDatePanel;
	private JDatePanelImpl expiryDatePanel;
	private JDatePickerImpl purchaseDate;
	private JDatePickerImpl expiryDate;
	

	@SuppressWarnings("rawtypes")
	private JList makeList;
	@SuppressWarnings("rawtypes")
	private JComboBox quantityComboBox;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AssetFormPanel() {
		Dimension Dim = getPreferredSize();
		Dim.width = 400;
		setPreferredSize(Dim);
		setMinimumSize(Dim);
		
		nameLabel = new JLabel("Name: ");
		makeLabel = new JLabel("Make: ");
		modelLabel = new JLabel("Model: ");
		serial_numberLabel = new JLabel("Serial Number: ");
		purchase_dateLabel = new JLabel("Date of Purchase: ");
		quantityLabel = new JLabel("Quantity: ");
		productIDLabel = new JLabel("ProductID: ");
		expiry_dateLabel = new JLabel("Expiry Date: ");
		reg_numberLabel = new JLabel("Registration Number: ");
		valueLabel = new JLabel("Value: ");
		submitButton = new JButton("Submit");
		
		nameField = new JTextField(15);
		modelField = new JTextField(15);
		serial_dateField = new JTextField(15);
		productIDField = new JTextField(15);
		reg_numberField = new JTextField(15);
		valueField = new JTextField(15);
		
		makeList = new JList();
		quantityComboBox = new JComboBox();
		
		condition_descriptionField = new JTextField(15);
		
		conditionNew = new  JRadioButton("New");
		conditionUsed = new JRadioButton("Used");
		
		conditionNew.setActionCommand("New");
		conditionUsed.setActionCommand("Used");
		
		conditionGroup = new ButtonGroup();
		
		conditionNew.setSelected(true);
		
		////Set up Condition Radio Check 
		conditionGroup.add(conditionNew);
		conditionGroup.add(conditionUsed);
		 
		
		////Set up List box
		DefaultListModel makeModel = new DefaultListModel();
		makeModel.addElement("Home Equipment");
		makeModel.addElement("Office Equipment");
		makeModel.addElement("Vehicle Equipment");
		makeModel.addElement("Drone Equipment");
		makeModel.addElement("Mine Equipment");
		makeModel.addElement("GPS Equipment");
		
		makeList.setModel(makeModel);
		makeList.setPreferredSize(new Dimension (120, 110));
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
		
		modelPurchaseDate = new UtilDateModel();
		modelExpiryDate = new UtilDateModel();
		p1 = new Properties();
		p2 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		purchaseDatePanel = new JDatePanelImpl(modelPurchaseDate, p1);
		expiryDatePanel = new JDatePanelImpl(modelExpiryDate, p2);
		purchaseDate = new JDatePickerImpl(purchaseDatePanel, new DateLabelFormatter());
		expiryDate = new JDatePickerImpl(expiryDatePanel, new DateLabelFormatter());
		
		///Set Up Submit Button Action Listener
		
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String make = (String) makeList.getSelectedValue();
				String model = modelField.getText();
				String serial_number = serial_dateField.getText();
				String purchase_date = purchaseDate.getJFormattedTextField().getText();
				String quantity = (String) quantityComboBox.getSelectedItem();
				String productID = productIDField.getText();
				String expiry_date = expiryDate.getJFormattedTextField().getText();
				String reg_number = reg_numberField.getText();
				String valueText = valueField.getText();
				Double value = Double.parseDouble(valueText);
				String condition_description = condition_descriptionField.getText();
			
				
				String condition_status = conditionGroup.getSelection().getActionCommand();
				
				
				AssetFormEvent ev = new AssetFormEvent(this, name, make, model, serial_number, purchase_date, quantity, productID, expiry_date,
						reg_number, value, condition_status, condition_description);
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
			
		});
		Border innerBorder = BorderFactory.createTitledBorder("Asset Management");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		
		//////////////First Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);
		
		//////////////Second Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(makeLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(makeList, gc);
		
	    //////////////Third Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(modelLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(modelField, gc);
	
		//////////////Fourth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 3;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(serial_numberLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(serial_dateField, gc);

		//////////////Fifth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(purchase_dateLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(purchaseDate, gc);

		//////////////Sixth Row ///////////////
		
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
		add(productIDLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(productIDField, gc);
		
		//////////////Eighth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 7;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(expiry_dateLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(expiryDate, gc);
		
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
		add(valueLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(valueField, gc);
		
       //////////////Next Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy++;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Condition: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(conditionNew, gc);
		
		//////////////Next Row ///////////////
			
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(conditionUsed, gc);
		
		//////////////Next Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy++;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Condition Description: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(condition_descriptionField, gc);

		//////////////Submit Button ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1.0;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(submitButton, gc);
	}
	
	public void setFormListener(AssetFormListener listener) {
		this.formListener = listener;
	}
}



