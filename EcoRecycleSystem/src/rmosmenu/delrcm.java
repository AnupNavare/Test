package rmosmenu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import com.ecosystem.InsertItemIntoRCM;
import com.ecosystem.SQLOperation;

class delrcm extends JFrame implements ItemListener {
	JTextField t = new JTextField(15);
	private JComboBox RCMDetailsCombo;
	String[] RCMDetailsValues = new String[50];
	SQLOperation sqlOperation;
	String selectedRcm;
	DefaultComboBoxModel model;
	final String[] sList = { "Type1", "Type2", "Type3" };

	public delrcm() {
		System.out.println("in delete rcm");
		setVisible(true);
		setTitle("Delete RCM");
		setSize(500, 500);
		setBackground(Color.gray);
		getContentPane().setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		getContentPane().add(topPanel, BorderLayout.CENTER);

		// Create a combobox
		//RCMDetailsCombo = new JComboBox();
		
		
		// Text box feild
		t.setBounds(250, 100, 150, 20);
		topPanel.add(t);
		sqlOperation = new SQLOperation();
		// buttons
		JButton deleteBtn = new JButton("Delete");
		JButton cancelBtn = new JButton("Cancel");
		topPanel.add(deleteBtn);
		topPanel.add(cancelBtn);
		deleteBtn.setBounds(80, 170, 100, 20);
		cancelBtn.setBounds(220, 170, 100, 20);
		// create a label
		JLabel RCMDetailsComboLbl = new JLabel("Select the RCM to delete");
		topPanel.add(RCMDetailsComboLbl);
		RCMDetailsComboLbl.setBounds(20, 40, 200, 20);
		// create a label1
		JLabel l2 = new JLabel(" RCM selected to delete is");
		topPanel.add(l2);
		l2.setBounds(20, 100, 200, 20);
		System.out.println("before calling getRCMDetails");
		RCMDetailsValues = sqlOperation.getRCMDetails();
		// Populate the combobox list
		RCMDetailsCombo = new JComboBox(RCMDetailsValues);
		RCMDetailsCombo.setBounds(250, 40, 150, 20);
		topPanel.add(RCMDetailsCombo);
		// Allow edits
		RCMDetailsCombo.setEditable(true);
		RCMDetailsCombo.setSelectedIndex(-1);
		// Watch for changes
		RCMDetailsCombo.addItemListener(this);
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(selectedRcm);
				String parts[] = selectedRcm.split("-");
				String rcmId = parts[0];
				//System.out.println(location);
				int ans = JOptionPane.showConfirmDialog(delrcm.this, "Have you checked the Allowed Recyclable Items\n",
						"Check Allowed Items",
						JOptionPane.YES_NO_OPTION);
			
			if(ans == JOptionPane.OK_OPTION){
				sqlOperation.deleteRCM(rcmId);
				//getContentPane().repaint();
				RCMDetailsValues = sqlOperation.getRCMDetails();
				model = new DefaultComboBoxModel(RCMDetailsValues);
				RCMDetailsCombo.setModel(model);
			}
			else{
				repaint();
				return;
			}
				
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new SimpleMenuExample();
				setVisible(false);
			}
		});
	}

	public void itemStateChanged(ItemEvent event) {

		if (event.getSource() == RCMDetailsCombo
				&& event.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("here in combo....");
			selectedRcm = (String) RCMDetailsCombo.getSelectedItem();
			t.setText((String) RCMDetailsCombo.getSelectedItem());
		}
	}

	public static void main(String args[]) {
		// Create an instance of the test application
		delrcm mainFrame = new delrcm();
		mainFrame.setVisible(true);
	}

}
