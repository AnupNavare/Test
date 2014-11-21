package rmosmenu;
import javax.swing.*;

import com.ecosystem.InsertItemIntoRCM;
import com.ecosystem.SQLOperation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class additem extends JFrame {

SQLOperation sqlOperation;	
		JButton addBtn = new JButton("Add");
		JButton backBtn = new JButton("Back");
		JPanel addItemPanel = new JPanel();
		JTextField name = new JTextField(15);
		JTextField price = new JTextField(15);
		//JTextField weight = new JTextField(15);
		JLabel priceLbl = new JLabel("Item Price");
		JLabel nameLbl = new JLabel("Item Name");
		//JLabel weightLbl = new JLabel("Item Weight");
		
		additem(){
		super("Add Item");
		setSize(400,400);
		setLocation(500,280);
		addItemPanel.setLayout (null);
		
		nameLbl.setBounds(20,30,150,20);
		priceLbl.setBounds(20,65,150,20);
		//weightLbl.setBounds(20,100,150,20);
		name.setBounds(120,30,150,20);
		price.setBounds(120,65,150,20);
		//weight.setBounds(120,100,150,20);
		addBtn.setBounds(65,150,80,20);
		backBtn.setBounds(170,150,90,20);
		
		addItemPanel.add(name);
		addItemPanel.add(price);
		//addItemPanel.add(weight);
		addItemPanel.add(nameLbl);
		addItemPanel.add(priceLbl);
		//addItemPanel.add(weightLbl);
		addItemPanel.add(addBtn);
		addItemPanel.add(backBtn);

		/*...........action listener on addBtn.............*/
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sqlOperation = new SQLOperation();
				String itemType = name.getText();
				String itemPrice = price.getText();
				//String itemWeight = weight.getText();
				sqlOperation.insertItem(itemType,itemPrice);
				JOptionPane.showMessageDialog(additem.this, "Item Successfully Added");
				name.setText("");
				price.setText("");
			}
			
		});
		
		/*..................Listener for back button .....................*/
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SimpleMenuExample();
			}
		});
		getContentPane().add(addItemPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
		
		
		public static void main(String[] args) {
			additem item = new additem();
			}

}