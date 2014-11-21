package rmosmenu;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import com.ecosystem.InsertItemIntoRCM;
import com.ecosystem.SQLOperation;

class ComboBoxExample extends JFrame implements ItemListener {
	JTextField t1 = new JTextField(15);
	private JComboBox itemTypeCombo;
	SQLOperation sqlOperation;
	String[] itemTypeList = new String[50];
	//final String[] sList = { "Type1", "Type2", "Type3" };

	public ComboBoxExample() {
		setTitle("Delete Item");
		setSize(500, 500);
		setVisible(true);
		setBackground(Color.gray);
		getContentPane().setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(null);
		getContentPane().add(topPanel, BorderLayout.CENTER);

		// Create a combobox
		//itemTypeCombo = new JComboBox();
		//itemTypeCombo.setBounds(250, 40, 150, 20);
		
		// Text box feild
		t1.setBounds(250, 100, 150, 20);
		//topPanel.add(t1);
		// buttons
		JButton deleteBtn = new JButton("Delete");
		JButton cancelBtn = new JButton("Back");
		topPanel.add(deleteBtn);
		topPanel.add(cancelBtn);
		sqlOperation = new SQLOperation();
		
		deleteBtn.setBounds(80, 170, 100, 20);
		cancelBtn.setBounds(220, 170, 100, 20);
		// create a label
		JLabel l1 = new JLabel("Select the item to delete");
		topPanel.add(l1);
		l1.setBounds(20, 40, 200, 20);
		// create a label1
		JLabel l2 = new JLabel("Item Price");
		//topPanel.add(l2);
		l2.setBounds(20, 100, 200, 20);
		
		/*...................Getting itemtypes from database.......................*/
		itemTypeList = sqlOperation.getItemType();
		// Populate the combobox list
		System.out.println("here after itemTypeList have values");
		itemTypeCombo = new JComboBox(itemTypeList);
		itemTypeCombo.setBounds(250, 40, 150, 20);
		topPanel.add(itemTypeCombo);
		/*for (int iCtr = 0; iCtr < itemTypeList.length; iCtr++)
			itemTypeCombo.addItem(itemTypeList[iCtr]);
*/
		// Allow edits
		itemTypeCombo.setEditable(true);

		// Watch for changes
		itemTypeCombo.addItemListener(this);
		
		/*.............listener for delete button..............*/
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String selectedItem = (String)itemTypeCombo.getSelectedItem();
				
				sqlOperation.deleteItem(selectedItem);
			}
		});
		
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

		if (event.getSource() == itemTypeCombo
				&& event.getStateChange() == ItemEvent.SELECTED) {

			//t1.setText.((String) itemTypeCombo.getSelectedItem());
			JOptionPane.showMessageDialog(ComboBoxExample.this, "Item Successfully Deleted");
		}
	}

	public static void main(String args[]) {
		// Create an instance of the test application
		ComboBoxExample mainFrame = new ComboBoxExample();

	}

}
