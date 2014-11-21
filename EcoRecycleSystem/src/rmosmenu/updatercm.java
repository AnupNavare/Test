package rmosmenu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import com.ecosystem.InsertItemIntoRCM;
import com.ecosystem.SQLOperation;

class updatercm extends JFrame implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JTextField t = new JTextField(15);
	public JComboBox rcmNameCombo;
	SQLOperation sqlOperation;
	String rcmDetailValues[];
	String getSelectedRcmDetails[];
	String rcmId;
	JPanel mPanel;
	JTextField locationTxt,capacityTxt,amountTxt,itemType1Txt,itemType2Txt,itemType3Txt;
	
	
	//final String[] sList = { "Type1", "Type2", "Type3" };

	public updatercm() {
		setTitle("Update RCM");
		setSize(500, 500);
		//setVisible(true);
		setBackground(Color.gray);
		getContentPane().setLayout(new BorderLayout());
		mPanel = new JPanel();
		mPanel.setLayout(null);
		getContentPane().add(mPanel, BorderLayout.CENTER);

		// Create a combox
		
		rcmDetailValues =  new String[50];
		getSelectedRcmDetails = new String[50];
		
		locationTxt = new JTextField(15);
		mPanel.add(locationTxt);
		locationTxt.setBounds(250, 110, 150, 20);
		
		
		capacityTxt = new JTextField(15);
		mPanel.add(capacityTxt);
		capacityTxt.setBounds(250, 145, 150, 20);
		
		amountTxt = new JTextField(15);
		mPanel.add(amountTxt);
		amountTxt.setBounds(250, 180, 150, 20);
		
		itemType1Txt = new JTextField(15);
		mPanel.add(itemType1Txt);
		itemType1Txt.setBounds(250, 215, 150, 20);
		
		itemType2Txt = new JTextField(15);
		mPanel.add(itemType2Txt);
		itemType2Txt.setBounds(250, 250, 150, 20);
		
		itemType3Txt = new JTextField(15);
		mPanel.add(itemType3Txt);
		itemType3Txt.setBounds(250, 285, 150, 20);
		
		// buttons
		JButton updateBtn = new JButton("Update");
		JButton cancelBtn = new JButton("Cancel");
		mPanel.add(updateBtn);
		mPanel.add(cancelBtn);
		updateBtn.setBounds(80, 350, 100, 20);
		cancelBtn.setBounds(220, 350, 100, 20);
		
		/*.................Labels...............*/
		JLabel rcmComboLbl = new JLabel("Select the RCM to update");
		mPanel.add(rcmComboLbl);
		rcmComboLbl.setBounds(20, 40, 200, 20);
		
		// create a label1
		//JLabel l2 = new JLabel("RCM selected to updated is");
		//mPanel.add(l2);
		//l2.setBounds(20, 75, 200, 20);
		JLabel locationLbl = new JLabel("RCM Location");
		locationLbl.setBounds(20, 110, 200, 20);
		mPanel.add(locationLbl);
		
		JLabel capacityLbl = new JLabel("RCM Capacity");
		capacityLbl.setBounds(20, 145, 200, 20);
		mPanel.add(capacityLbl);
		
		JLabel amountLbl = new JLabel("RCM Amount");
		amountLbl.setBounds(20, 180, 200, 20);
		mPanel.add(amountLbl);
		
		JLabel itemType1Lbl = new JLabel("RCM Type1");
		itemType1Lbl.setBounds(20, 215, 200, 20);
		mPanel.add(itemType1Lbl);
		
		JLabel itemType2Lbl = new JLabel("RCM Type2");
		itemType2Lbl.setBounds(20, 250, 200, 20);
		mPanel.add(itemType2Lbl);
		
		JLabel itemType3Lbl = new JLabel("RCM Type3");
		itemType3Lbl.setBounds(20, 285, 200, 20);
		mPanel.add(itemType3Lbl);

		// Watch for changes
		sqlOperation = new SQLOperation();
		System.out.println("now about to populate combobox");
		rcmDetailValues = sqlOperation.getRCMDetails();
		for(int i = 0; i < rcmDetailValues.length; i++){
			System.out.println(rcmDetailValues[i]);
		}
		rcmNameCombo = new JComboBox(rcmDetailValues);
		rcmNameCombo.setBounds(250, 40, 150, 20);
		rcmNameCombo.setSelectedIndex(-1);
		mPanel.add(rcmNameCombo);
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(rcmId);
				sqlOperation.updateRCM(rcmId,locationTxt.getText(),capacityTxt.getText(),amountTxt.getText(),itemType1Txt.getText(),itemType2Txt.getText(),itemType3Txt.getText());
				JOptionPane.showMessageDialog(updatercm.this, "RCM Updated");
				locationTxt.setText("");
				capacityTxt.setText("");
				amountTxt.setText("");
				itemType1Txt.setText("");
				itemType2Txt.setText("");
				itemType3Txt.setText("");
				rcmNameCombo.setSelectedIndex(-1);
			}
		});
		rcmNameCombo.addItemListener(this);
		setVisible(true);
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SimpleMenuExample();
			}
		});
	}

	public void itemStateChanged(ItemEvent event) {

		if (event.getSource() == rcmNameCombo
				&& event.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("here ");
			String getRCMDetails;
			String selectedRcm = (String)rcmNameCombo.getSelectedItem();
			String parts[] = selectedRcm.split("-");
			rcmId = parts[0];
			getRCMDetails = sqlOperation.getSelectedRcmDetails(rcmId);
			String parts1[] = getRCMDetails.split("-");
			String location = parts1[0];
			String capacity = parts1[1];
			String amount = parts1[2];
			String type1 = parts1[3];
			String type2 = parts1[4];
			String type3 = parts1[5];
			locationTxt.setText(location);
			capacityTxt.setText(capacity);
			amountTxt.setText(amount);
			itemType1Txt.setText(type1);
			itemType2Txt.setText(type2);
			itemType3Txt.setText(type3);
		}
	}

	public static void main(String args[]) {
		// Create an instance of the test application
		updatercm mainFrame = new updatercm();
		mainFrame.setVisible(true);
	}

}
