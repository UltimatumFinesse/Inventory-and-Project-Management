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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ProjectFormPanel extends JPanel {
	
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
	
	private JLabel pnameLabel;
	private JLabel locationLabel;
	private JLabel clientNameLabel;
	private JLabel contactNumberLabel;
	private JLabel clientEmailLabel;
	private JLabel dateExpectedLabel;
	private JLabel dateSubmittedLabel;
	private JLabel dateSignedLabel;
	private JLabel statusLabel;
	private JLabel productLabel;
	private JLabel valueLabel;
	private JLabel descriptionLabel;
	
	private JRadioButton statusProgress;
	private JRadioButton statusComplete;
	private ButtonGroup statusGroup;
	
	private JTextField pnameField;
	private JTextField locationField;
	private JTextField clientNameField;
	private JTextField contactNumberField;
	private JTextField clientEmailField;
	private JTextField productField;
	private JTextField valueField;
	private JTextField descriptionField;
	
	private JButton submitButton;
	
	private ProjectFormListener formListener;

	private UtilDateModel modelExpectedDate;
	private UtilDateModel modelSubmittedDate;
	private UtilDateModel modelSignedDate;
	private Properties p1;
	private Properties p2;
	private Properties p3;
	private JDatePanelImpl dateExpectedPanel;
	private JDatePanelImpl dateSubmittedPanel;
	private JDatePanelImpl dateSignedPanel;
	private JDatePickerImpl dateExpected;
	private JDatePickerImpl dateSubmitted;
	private JDatePickerImpl dateSigned;
	
	
	
	public ProjectFormPanel() {
		Dimension Dim = getPreferredSize();
		Dim.width = 400;
		setPreferredSize(Dim);
		setMinimumSize(Dim);
		
		pnameLabel = new JLabel("Project Name: ");
		locationLabel = new JLabel("Project Location: ");
		clientNameLabel = new JLabel("Client Name: ");
		contactNumberLabel = new JLabel("Contact Number: ");
		clientEmailLabel = new JLabel("Client Email: ");
		dateExpectedLabel = new JLabel("Delivery Date: ");
		dateSubmittedLabel = new JLabel("Submission Date: ");
		dateSignedLabel = new JLabel("Signed Date: ");
		productLabel = new JLabel("Project Product: ");
		valueLabel = new JLabel("Project Value: ");
		statusLabel = new JLabel("Project Status: ");
		descriptionLabel = new JLabel("Project Description: ");
		
		submitButton = new JButton("Submit");
		
		pnameField = new JTextField(15);
		locationField = new JTextField(15);
		clientNameField = new JTextField(15);
		contactNumberField = new JTextField(15);
		clientEmailField = new JTextField(15);
		productField = new JTextField(15);
		valueField = new JTextField(15);
		descriptionField = new JTextField(15);

		
		statusProgress = new  JRadioButton("In Progress");
		statusComplete = new JRadioButton("Complete");
		
		statusProgress.setActionCommand("In Progress");
		statusComplete.setActionCommand("Complete");
		
		statusGroup = new ButtonGroup();
		
		statusProgress.setSelected(true);
		
		////Set up Condition Radio Check 
		statusGroup.add(statusProgress);
		statusGroup.add(statusComplete);

		/////Set up a JDatePicker and JDatePanel
		modelExpectedDate = new UtilDateModel();
		modelSubmittedDate = new UtilDateModel();
		modelSignedDate = new UtilDateModel();
		p1 = new Properties();
		p2 = new Properties();
		p3 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		p3.put("text.today", "Today");
		p3.put("text.month", "Month");
		p3.put("text.year", "Year");
		dateExpectedPanel = new JDatePanelImpl(modelExpectedDate, p1);
		dateSubmittedPanel = new JDatePanelImpl(modelSubmittedDate, p2);
		dateSignedPanel = new JDatePanelImpl(modelSignedDate, p3);
		dateExpected = new JDatePickerImpl(dateExpectedPanel, new DateLabelFormatter());
		dateSubmitted = new JDatePickerImpl(dateSubmittedPanel, new DateLabelFormatter());
		dateSigned = new JDatePickerImpl(dateSignedPanel, new DateLabelFormatter());
		
		///Set Up Submit Button Action Listener
		
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname = pnameField.getText();
				String location = locationField.getText();
				String clientName = clientNameField.getText();
				String contactNumber = contactNumberField.getText();
				String clientEmail = clientEmailField.getText();
				String expectedDate = dateExpected.getJFormattedTextField().getText();
				String submittedDate = dateSubmitted.getJFormattedTextField().getText();
				String signedDate = dateSigned.getJFormattedTextField().getText();
				String product = productField.getText();
				String valueText = valueField.getText();
				Double value = Double.parseDouble(valueText);
				String description = descriptionField.getText();
				String status = statusGroup.getSelection().getActionCommand();
				
				
				ProjectFormEvent ev = new ProjectFormEvent(this, pname, location, clientName, contactNumber, clientEmail, expectedDate, submittedDate, signedDate,
						product, value, status, description);
				
				if(formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
			
		});
		Border innerBorder = BorderFactory.createTitledBorder("Project Management");
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
		add(pnameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(pnameField, gc);
		
		//////////////Second Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(locationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(locationField, gc);
		
	    //////////////Third Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 2;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(clientNameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(clientNameField, gc);
	
		//////////////Fourth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 3;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(contactNumberLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(contactNumberField, gc);

		//////////////Fifth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 4;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(clientEmailLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(clientEmailField, gc);

		//////////////Sixth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 5;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dateExpectedLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateExpected, gc);

		//////////////Seventh Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 6;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dateSubmittedLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateSubmitted, gc);
		
		//////////////Eighth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 7;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dateSignedLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(dateSigned, gc);
		
		//////////////Ninth Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy++;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(productLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(productField, gc);
		
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
		add(statusLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(statusProgress, gc);
		
		//////////////Next Row ///////////////
			
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(statusComplete, gc);
		
		//////////////Next Row ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy++;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(descriptionLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(descriptionField, gc);

		//////////////Submit Button ///////////////
		
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 1.0;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(submitButton, gc);
	}
	
	public void setFormListener(ProjectFormListener listener) {
		this.formListener = listener;
	}
}



