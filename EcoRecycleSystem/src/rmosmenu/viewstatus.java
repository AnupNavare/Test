package rmosmenu;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ecosystem.SQLOperation;

public class viewstatus extends JFrame implements ItemListener {
	private JComboBox comb;
	SQLOperation sqlOperation;
	String[] RCMDetailsValues;
	String[] sList = new String[10];
	JTextField availableCapacityTxt;
	JTextField statusTxt;
	JTextField moneyReturnedTxt;
	JTextField locationTxt;
	JTextField amountTxt;
	//JTextField type1CountTxt;
	//JTextField type2CountTxt;
	//JTextField type3CountTxt;
	JTextField lastEmptiedTxt;

	public viewstatus() {
		setTitle("View Status");
		setSize(500, 500);
		setVisible(true);
		setBackground(Color.gray);
		getContentPane().setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		getContentPane().add(topPanel, BorderLayout.CENTER);
		// Text box field
		
		RCMDetailsValues = new String[50];
		lastEmptiedTxt = new JTextField(15);
		topPanel.add(lastEmptiedTxt);
		lastEmptiedTxt.setEditable(false);;
		lastEmptiedTxt.setBounds(250, 110, 150, 20);
		
		availableCapacityTxt = new JTextField(15);
		topPanel.add(availableCapacityTxt);
		availableCapacityTxt.setBounds(250, 145, 150, 20);
		availableCapacityTxt.setEditable(false);;
		
		statusTxt = new JTextField(15);
		topPanel.add(statusTxt);
		statusTxt.setBounds(250, 180, 150, 20);
		statusTxt.setEditable(false);;
		
		moneyReturnedTxt = new JTextField(15);
		topPanel.add(moneyReturnedTxt);
		moneyReturnedTxt.setBounds(250, 215, 150, 20);
		moneyReturnedTxt.setEditable(false);;
		
		locationTxt = new JTextField(15);
		topPanel.add(locationTxt);
		locationTxt.setBounds(250, 250, 150, 20);
	    locationTxt.setEditable(false);
		
		amountTxt = new JTextField(15);
		topPanel.add(amountTxt);
		amountTxt.setBounds(250, 285, 150, 20);
		amountTxt.setEditable(false);;
		/*
		type1CountTxt = new JTextField(15);
		topPanel.add(type1CountTxt);
		//type1CountTxt.setBounds(250, 320, 150, 20);
		//type1CountTxt.setEnabled(false);
		
		//type2CountTxt = new JTextField(15);
		topPanel.add(type2CountTxt);
		type2CountTxt.setBounds(250, 355, 150, 20);
		type2CountTxt.setEnabled(false);
		
		type3CountTxt = new JTextField(15);
		topPanel.add(type3CountTxt);
		type3CountTxt.setBounds(250, 390, 150, 20);
		type3CountTxt.setEnabled(false);
		*/
		
		// buttons
		JButton backBtn = new JButton("Back");
		topPanel.add(backBtn);
		backBtn.setBounds(80, 425, 100, 20);
		// b2.setBounds(220,250,100,20);
		// create a label
		JLabel l1 = new JLabel("Select the RCM");
		topPanel.add(l1);
		l1.setBounds(20, 40, 200, 20);
		// create a label1
		
		JLabel l3 = new JLabel("Last Emptied");
		topPanel.add(l3);
		l3.setBounds(20, 110, 200, 20);
		
		JLabel l4 = new JLabel("Available Capacity");
		
		topPanel.add(l4);
		l4.setBounds(20, 145, 200, 20);
		
		JLabel l5 = new JLabel("Status");
		topPanel.add(l5);
		l5.setBounds(20, 180, 200, 20);
		
		JLabel l6 = new JLabel("Total Money Returned");
		topPanel.add(l6);
		l6.setBounds(20, 215, 200, 20);
		
		JLabel l7 = new JLabel("Location");
		topPanel.add(l7);
		l7.setBounds(20, 250, 200, 20);
		
		JLabel l8 = new JLabel("Available Amount of money");
		topPanel.add(l8);
		l8.setBounds(20, 285, 200, 20);
		

		// Populate the combobox list
		sqlOperation = new SQLOperation();
		RCMDetailsValues = sqlOperation.getRCMDetails();
		// Populate the combobox list
		comb = new JComboBox(RCMDetailsValues);
		comb.setBounds(250, 40, 150, 20);
		topPanel.add(comb);
		// Allow edits
		comb.setEditable(false);
		comb.setSelectedIndex(-1);

		/*for (int iCtr = 0; iCtr < sList.length; iCtr++)
			comb.addItem(sList[iCtr]);
*/
		
		
		comb.addItemListener(this);
	}

	public void itemStateChanged(ItemEvent event) {
		String selectedRcm = null;
		String getStatusValues;
		if (event.getSource() == comb
				&& event.getStateChange() == ItemEvent.SELECTED) {
			selectedRcm = (String)comb.getSelectedItem();
			String[] parts = selectedRcm.split("-");
			String rcmId = parts[0];
			getStatusValues = sqlOperation.getRCMStatusDetails(rcmId);
			//System.out.println(getStatusValues);
			String parts1[] = getStatusValues.split("-");
			String availableCapacity = parts1[0];
			String status = parts1[1];
			String totalAmtReturned = parts1[2];
			String location = parts1[3];
			String availableAmtInRcm = parts1[4];
			//String type1Count = parts1[5];
			//String type2Count = parts1[6];
			//String type3Count = parts1[7];
			String lastEmptied = ""+ parts1[5] + "-" + parts1[6] + "-" +parts1[7];
			
			/*............Setting values..................*/
			availableCapacityTxt.setText(availableCapacity);
			statusTxt.setText(status);
			moneyReturnedTxt.setText(totalAmtReturned);
			locationTxt.setText(location);
			amountTxt.setText(availableAmtInRcm);
			//type1CountTxt.setText(type1Count);
			//type2CountTxt.setText(type2Count);
			//type3CountTxt.setText(type3Count);
			lastEmptiedTxt.setText(lastEmptied);
			
			
			
		}
	}

	public static void main(String args[]) {
		// Create an instance of the test application
		viewstatus mainFrame = new viewstatus();
		mainFrame.setVisible(true);
	}

}
