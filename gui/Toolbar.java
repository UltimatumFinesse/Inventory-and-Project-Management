package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;


public class Toolbar extends JToolBar implements ActionListener {
	
	private static final long serialVersionUID = 7027796597115413935L;
	
	private JButton saveButton;
	private JButton refreshButton;
	private ToolbarListener textListener;
 
	public Toolbar() {
		
		setBorder(BorderFactory.createEtchedBorder());
		//setFloatable(false);
		Icon iconSave = new ImageIcon("C:\\Program Files\\Ultimatum Finesse\\Ultimatum Manager\\Icons\\Save.gif");
		saveButton = new JButton(iconSave);
		saveButton.setToolTipText("Save");
		
		Icon iconRefresh = new ImageIcon("C:\\Program Files\\Ultimatum Finesse\\Ultimatum Manager\\Icons\\Refresh.gif");
		refreshButton = new JButton(iconRefresh);
		refreshButton.setToolTipText("Refresh");
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(saveButton);
		add(refreshButton);
	}
	
	public void setToolbarListener(ToolbarListener listener) {
		
		this.textListener = listener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == saveButton) {
			if(textListener != null) {
				textListener.saveEventOccured();
			}
		}
		else if(clicked == refreshButton) {
			if(textListener != null) {
				textListener.refreshEventOccured();
			}
		}
	}
}

	
