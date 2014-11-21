package rmosmenu;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.text.JTextComponent;

import com.ecosystem.SQLOperation;

import java.awt.event.ActionEvent;
import java.util.Vector;

public class newrcm extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	Vector<String> selectedTypesVector;
	String selectedComboValue;
	String[] sList = new String[50];
	SQLOperation sqlOperation;
	
	JButton addBtnNewRcm = new JButton("Add");
	JButton cancelBtnNewRcm = new JButton("Cancel");
	JButton addItemBtnNewRcm = new JButton("Add Item");
	JList list = new JList();
	DefaultComboBoxModel model1;
	
	JPanel panel = new JPanel();
	JTextField locationTxt = new JTextField(15);
	//JTextField capacityTxt = new JTextField(15);
	JTextField weightTxt = new JTextField(15);
	JTextField amtTxt = new JTextField(15);
	JLabel location = new JLabel("RCM Location");
	//JLabel id1 = new JLabel("RCM ID");
	JLabel capacityLbl = new JLabel("RCM Capacity");
	JLabel amtLbl = new JLabel("Initial Amount");
	JLabel typeLbl = new JLabel("Select Type");
	JComboBox comb; 

	public newrcm()
	{
		super("Add New RCM Machine");
		setSize(700, 400);
		setLocation(500, 280);
		panel.setLayout(null);
		location.setBounds(20, 30, 150, 20);
		//id1.setBounds(20, 65, 150, 20);
		capacityLbl.setBounds(20, 100, 150, 20);
		amtLbl.setBounds(20, 135, 150, 20);
		locationTxt.setBounds(120, 30, 150, 20);
		///price.setBounds(120, 65, 150, 20);
		weightTxt.setBounds(120, 100, 150, 20);
		amtTxt.setBounds(120, 135, 150, 20);
		addBtnNewRcm.setBounds(100, 250, 80, 20);
		cancelBtnNewRcm.setBounds(200, 250, 90, 20);
		addItemBtnNewRcm.setBounds(280, 170, 120, 20);
		
		typeLbl.setBounds(20, 170, 150, 20);
		list.setBounds(450, 170, 120, 100);
		list.setMinimumSize(new Dimension(100, 100));
		list.setBorder(BorderFactory.createLineBorder(Color.black));
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(5);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		selectedTypesVector = new Vector<String>();

		
		panel.add(locationTxt);
		//panel.add(price);
		panel.add(weightTxt);
		panel.add(amtTxt);
		panel.add(location);
		panel.add(amtLbl);
		//panel.add(id1);
		panel.add(capacityLbl);
		panel.add(addBtnNewRcm);
		panel.add(cancelBtnNewRcm);
		panel.add(addItemBtnNewRcm);
		panel.add(typeLbl);
		panel.add(list);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		sqlOperation = new SQLOperation();
		sList = sqlOperation.getItemType();
		comb = new JComboBox(sList);
		comb.setBounds(120, 170, 150, 20);
		comb.setSelectedIndex(-1);
		panel.add(comb);
		//for (int iCtr = 0; iCtr < sList.length; iCtr++)
			//comb.addItem(sList[iCtr]);

		// Allow edits
		comb.setEditable(true);

		addItemBtnNewRcm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// System.out.println("here");
				list.setVisibleRowCount(10);
				System.out.println(comb.getSelectedItem());
				String selectedComboValue = (String) comb.getSelectedItem();
				selectedTypesVector.addElement(selectedComboValue);
				// newrcm.this.list.setModel((ListModel) selectedTypesVector);
				list.setListData(selectedTypesVector);

				for (int i = 0; i < selectedTypesVector.size(); i++) {
					System.out.println(selectedTypesVector.elementAt(i));
				}
				// listScroller = new JScrollPane(list);

			}
			// }
		});
		// Watch for changes
		// comb.addItemListener( new Ac );
	
		addBtnNewRcm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int recordId;
				System.out.println(locationTxt.getText());
				System.out.println(weightTxt.getText());
				System.out.println(amtTxt.getText());
				ListModel model = list.getModel();
				recordId = sqlOperation.addRcm(locationTxt.getText(),weightTxt.getText(),amtTxt.getText(),model);
			//	sqlOperation.closeDB();
				if(recordId > 0){
				JOptionPane.showMessageDialog(newrcm.this,
						"RCM Has Been Added");
				locationTxt.setText("");
				weightTxt.setText("");
				amtTxt.setText("");
				sList = sqlOperation.getItemType();
				model1 = new DefaultComboBoxModel(sList);
				comb.setModel(model1);
				comb.setSelectedIndex(-1);
				DefaultListModel againModel = new DefaultListModel();
				list.setModel(againModel);
			}
			else{
				JOptionPane.showMessageDialog(newrcm.this,
						"RCM Has Not Been Added");
				
			}
				sqlOperation.closeDB();
			}
		});
		cancelBtnNewRcm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SimpleMenuExample();
			}
		});
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String args[]){
		newrcm newRcm = new newrcm();
	}

}
