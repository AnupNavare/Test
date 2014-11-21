package com.ecosystem;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

public class InsertItemIntoRCM extends JFrame implements ListSelectionListener{
	
	
	
	String colNames[] = { "Details", "Values" };
	
	JList listbox;
	Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	Border tittleborder = BorderFactory.createTitledBorder("Select Item");
	JTable table;
	JLabel rcmIdLbl;
	JLabel rcmIdValueLbl;
	JButton insertBtn;
	JButton cancelBtn;
	JButton startSessionBtn;
	JButton endSessionBtn;
	JCheckBox box;
	JLabel types1;
	JLabel types2;
	JLabel types3;
	JLabel types4;
	JLabel types5;
	JLabel types6;
	JLabel itemname,itemprice,amtdue,winkg,wlbs;
	JLabel itemname1,itemprice1,amtdue1,winkg1,wlbs1;
	JPanel panel;
	SQLOperation sqlOperation;
	
	String rcmItemTypesStr;
	
	
	
	int total_weight_inserted = 0;
	int total_amount_due = 0;
	double weight_in_kg = 0.0;
	int totalAmountDueOnEndSession = 0;
	double totalWeightInsertedInKg = 0.0;
	
		InsertItemIntoRCM(String loc)
		{ 
		super("Add Item into RCM");
		String parts2[] = loc.split("-");
		String rcmId = parts2[0];
		String rcmLoc = parts2[1];
		setSize(600,600);
		setLocation(500,280);
		panel = new JPanel();
		panel.setLayout (null);
		
		rcmIdLbl = new JLabel("RCM ID : ");
		rcmIdValueLbl = new JLabel("" +rcmId);
		
		insertBtn = new JButton("Insert");
		cancelBtn = new JButton("Back");
		startSessionBtn = new JButton("Start Session");
		endSessionBtn = new JButton("End Session");
		
		JLabel mainlabel = new JLabel("Welcome");
		mainlabel.setFont((new Font("Serif", Font.BOLD, 30)));
		
	//	types1 = new JLabel(" To The Recycling Machine" );     
		//types2 = new JLabel("Pleass Select Your Recyable Items"); 
		types3 = new JLabel(" ");
		types4  = new JLabel(" ");
		types5  = new JLabel(" ");
		types6  = new JLabel(" ");
		itemname = new JLabel("Item Name");
		itemprice = new JLabel("Item Price");
		amtdue = new JLabel("Amount Due");
		wlbs = new JLabel("Weight in lbs");
		winkg = new JLabel("Weight in kg");
		itemname1 = new JLabel(" ");
		itemprice1 = new JLabel(" ");
		amtdue1 = new JLabel(" ");
		wlbs1 = new JLabel(" ");
		winkg1 = new JLabel(" ");
		box = new JCheckBox("Do You The Money Redeemed in Coupons?");
		
		//dataValues = new String[6][6];
		//columnNames = new String[6];
		//columnValues = new String[6];
		//dataValues1 = new String[6][6];
		
		rcmIdLbl.setBounds(450, 50, 200, 20);
		rcmIdValueLbl.setBounds(500, 50, 200, 20);
		
		mainlabel.setBounds(200,60,250,30);
		itemname.setBounds(350, 200, 150, 20);
		itemname1.setBounds(450, 200, 150, 20);
		itemprice.setBounds(350, 235, 150, 20);
		itemprice1.setBounds(450, 235, 150, 20);
		amtdue.setBounds(350, 270, 150, 20);
		amtdue1.setBounds(450, 270, 150, 20);
		wlbs.setBounds(350, 305, 150, 20);
		wlbs1.setBounds(450, 305, 150, 20);
		winkg.setBounds(350, 340, 150, 20);
		winkg1.setBounds(450, 340, 150, 20);
		//types1.setBounds(100,50,400,20);
		//types2.setBounds(100,70,400,20);
		types3.setBounds(100,90,400,20);
		types4.setBounds(300,50,400,20);
		types5.setBounds(300,70,400,20);
		types6.setBounds(300,90,400,20);
		
		insertBtn.setBounds(100,450,80,20);
		cancelBtn.setBounds(450,450,90,20);
		startSessionBtn.setBounds(100, 150, 140, 20);
		endSessionBtn.setBounds(300, 150, 140, 20);
		
		
		box.setBounds(100,500,400,20);
		
		//default visibility off
		insertBtn.setEnabled(false);
		cancelBtn.setEnabled(false);
		endSessionBtn.setEnabled(false);
		//table.setVisible(true);
		box.setEnabled(false);
		
		//asthetic appleal
		//mainlabel.setFont(Font.ITALIC);
		
	

		//columnNames = { "Details", "Values" };
		//columnNames[0] = "Details";
		//columnNames[1] = "Values";
		/*
		table = new JTable(dataValues,columnNames);
		//tableModel = new DefaultTableModel(dataValues,columnNames);
		table.setBounds(280, 250, 290, 95);
		table.setBorder(lineBorder);
		table.setVisible(true);
		*/
		
		
		
		
		panel.add(mainlabel);
		panel.add(rcmIdLbl);
		panel.add(rcmIdValueLbl);
		panel.add(insertBtn);
		panel.add(cancelBtn);
		panel.add(startSessionBtn);
		panel.add(endSessionBtn);
		
		
		//panel.add(types1);
		//panel.add(types2);
		panel.add(types3);
		panel.add(types4);
		panel.add(types6);
		panel.add(types5);
		panel.add(itemname);
		panel.add(itemname1);
		panel.add(itemprice);
		panel.add(itemprice1);
		//panel.add(itemname1);
		panel.add(amtdue);
		panel.add(amtdue1);
		panel.add(winkg);
		panel.add(winkg1);
		panel.add(wlbs1);
		panel.add(wlbs);
		
		//panel.add(table);
		
		panel.add(box);

		/*.............populating listbox with specific rcm............*/
		sqlOperation = new SQLOperation();
		rcmItemTypesStr = sqlOperation.getSelectedRcmItemTypes(rcmIdValueLbl.getText());
		rcmItemTypesStr = rcmItemTypesStr + "-Other";
		System.out.println(rcmItemTypesStr);
		//String listData[] = new String[10];
		String listData[] = rcmItemTypesStr.split("-");
		
		//listData[(listData.length)] = "Other";
		listbox = new JList(listData);
	
		listbox.setBounds(100, 200, 100, 200);
		listbox.setEnabled(false);
		listbox.setBorder(tittleborder);
		listbox.addListSelectionListener(this);
		listbox.setFixedCellHeight(40);
		listbox.setFixedCellHeight(40);
		panel.add(listbox);
		
		cancelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				 new RcmGui();
				 InsertItemIntoRCM.this.setVisible(false);
			 }
				 
			
		});
		
		startSessionBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				 endSessionBtn.setEnabled(true);
				 //table.setEnabled(true);
				 insertBtn.setEnabled(true);
				 cancelBtn.setEnabled(true);
				 box.setEnabled(true);
				 listbox.setEnabled(true);
				 //table.setVisible(true);
				 
			}
		});
		
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				System.out.println("here");
				String recyclableType = (String)listbox.getSelectedValue();
				System.out.println(recyclableType);
				int itemPrice = Integer.parseInt(sqlOperation.getItemTypeDetails(recyclableType));
				Random random = new Random();
				//....formula is random.nextInt(number ahead of  minimum) + minimum...........
				int randomNum = random.nextInt(10) + 1;
				int weightInserted = randomNum;
				System.out.println(weightInserted);
				String str = sqlOperation.getRcmAmtWeight(rcmIdValueLbl.getText());
				if(str.equalsIgnoreCase("")){
					System.out.println("No row exists");
					total_weight_inserted = total_weight_inserted + weightInserted;
					System.out.println(itemPrice);
					total_amount_due = itemPrice * weightInserted;
					totalAmountDueOnEndSession = totalAmountDueOnEndSession + total_amount_due;
					int rowId = sqlOperation.rcmInsertItem(rcmIdValueLbl.getText(),total_amount_due,weightInserted,recyclableType);
					
					if(box.isSelected()){
						JOptionPane.showMessageDialog(InsertItemIntoRCM.this, "Amount Returned In Coupons");
						/*dataValues[0][0] = "Item Name ";
						dataValues[0][1] = ""  + recyclableType + "";
						
						dataValues[1][0] = "Item Price ";
						dataValues[1][1] = "" + itemPrice + "";
						
						dataValues[2][0] = "Item Amount ";
						dataValues[2][0] = "Hi";
						
						dataValues[3][0] = "Price Due ";
						dataValues[3][0] = "Collect" + total_amount_due + " in Coupons";
						
						dataValues[4][0] = "Weight Inserted in lbs ";
						dataValues[4][0] = "" + weightInserted + "";
						
						dataValues[5][0] = "Weight Inserted In Kg";
						*/weight_in_kg = (weightInserted * 0.453592);
						totalWeightInsertedInKg = totalWeightInsertedInKg + weight_in_kg;
						//dataValues[5][1] = "" + weight_in_kg + "";
						
						itemname1.setText(recyclableType);
						itemprice1.setText(""+itemPrice);
						amtdue1.setText("Collect " + total_amount_due + " in Coupons");
						wlbs1.setText(""+weightInserted);
						winkg1.setText(""+weight_in_kg);
						
						
						
						/*String columnNames[] ={"data","values"};
						table = new JTable(dataValues,columnNames);
						panel.add(table);
						//tableModel = new DefaultTableModel(dataValues,columnNames);
						table.setBounds(280, 250, 290, 95);
						table.setBorder(lineBorder);
						table.setVisible(true);
						//table.setModel(tableModel);
						table.setVisible(true);
						*/
					}
					else{
						JOptionPane.showMessageDialog(InsertItemIntoRCM.this, "Amount Returned In Cash");
						/*dataValues[0][0] = "Item Name ";
						dataValues[0][1] = ""  + recyclableType + "";
						
						dataValues[1][0] = "Item Price ";
						dataValues[1][1] = "" + itemPrice + "";
						
						dataValues[2][0] = "Item Amount ";
						dataValues[2][0] = "Hi";
						
						dataValues[3][0] = "Price Due ";
						dataValues[3][0] = "Collect" + total_amount_due + " in Coupons";
						
						dataValues[4][0] = "Weight Inserted in lbs ";
						dataValues[4][0] = "" + weightInserted + "";
						
						dataValues[5][0] = "Weight Inserted In Kg";*/
						weight_in_kg = (weightInserted * 0.453592);
						totalWeightInsertedInKg = totalWeightInsertedInKg + weight_in_kg;
						/*//columnValues[5] = "" + weight_in_kg + "";
						dataValues[5][1] = "" + weight_in_kg + "";
						table = new JTable();
						panel.add(table);
						tableModel = new DefaultTableModel(dataValues,columnNames);
						table.setBounds(280, 250, 290, 95);
						table.setBorder(lineBorder);
						table.setVisible(true);
						table.setModel(tableModel);
						table.setVisible(true);
						//table.setModel(tableModel);
						//table.setVisible(true);*/
						
						itemname1.setText(recyclableType);
						itemprice1.setText(""+itemPrice);
						amtdue1.setText("" + total_amount_due + "");
						wlbs1.setText(""+weightInserted);
						winkg1.setText(""+weight_in_kg);
						
					}
				}
				else{
				System.out.println("exists");
				String parts[] = str.split("-");
				int currentWeight = Integer.parseInt(parts[0]);
				int currentAmtReturned = Integer.parseInt(parts[1]);
				int rcmCapacity = Integer.parseInt(parts[2]);
				int rcmAmount = Integer.parseInt(parts[3]);
				
				total_weight_inserted = total_weight_inserted + weightInserted;
				total_amount_due = itemPrice * weightInserted;
				totalAmountDueOnEndSession = totalAmountDueOnEndSession + total_amount_due;
				
				if((weightInserted + currentWeight) < rcmCapacity){
					System.out.println("ok!!! item can be inserted");
					if((rcmAmount - currentAmtReturned - total_amount_due) > 0){
						System.out.println("yes...amount is there");
						int rowId = sqlOperation.rcmInsertItem(rcmIdValueLbl.getText(),total_amount_due,weightInserted,recyclableType);
						if(box.isSelected()){
							JOptionPane.showMessageDialog(InsertItemIntoRCM.this, "Amount Returned In Coupons");
							/*dataValues[0][0] = "Item Name ";
							dataValues[0][1] = ""  + recyclableType + "";
							
							dataValues[1][0] = "Item Price ";
							dataValues[1][1] = "" + itemPrice + "";
							
							dataValues[2][0] = "Item Amount ";
							dataValues[2][0] = "Hi";
							
							dataValues[3][0] = "Price Due ";
							dataValues[3][0] = "Collect" + total_amount_due + " in Coupons";
							
							dataValues[4][0] = "Weight Inserted in lbs ";
							dataValues[4][0] = "" + weightInserted + "";
							
							dataValues[5][0] = "Weight Inserted In Kg";*/
							weight_in_kg = (weightInserted * 0.453592);
							totalWeightInsertedInKg = totalWeightInsertedInKg + weight_in_kg;
						/*	//columnValues[5] = "" + weight_in_kg + "";
							dataValues[5][1] = "" + weight_in_kg + "";
							
							table = new JTable();
							panel.add(table);
							tableModel = new DefaultTableModel(dataValues,columnNames);
							table.setBounds(280, 250, 290, 95);
							table.setBorder(lineBorder);
							table.setVisible(true);
							table.setModel(tableModel);
							table.setVisible(true);
							//table.setModel(tableModel);
							//table.setVisible(true);*/
							
							itemname1.setText(recyclableType);
							itemprice1.setText(""+itemPrice);
							amtdue1.setText("" + total_amount_due + " In Coupons");
							wlbs1.setText(""+weightInserted);
							winkg1.setText(""+weight_in_kg);
							
						}
						else{
							JOptionPane.showMessageDialog(InsertItemIntoRCM.this, "Amount Returned In Cash");
							/*dataValues[0][0] = "Item Name ";
							dataValues[0][1] = ""  + recyclableType + "";
							
							dataValues[1][0] = "Item Price ";
							dataValues[1][1] = "" + itemPrice + "";
							
							dataValues[2][0] = "Item Amount ";
							dataValues[2][0] = "Hi";
							
							dataValues[3][0] = "Price Due ";
							dataValues[3][0] = "Collect" + total_amount_due + " in Coupons";
							
							dataValues[4][0] = "Weight Inserted in lbs ";
							dataValues[4][0] = "" + weightInserted + "";
							
							dataValues[5][0] = "Weight Inserted In Kg";*/
							weight_in_kg = (weightInserted * 0.453592);
							totalWeightInsertedInKg = totalWeightInsertedInKg + weight_in_kg;
							/*//columnValues[5] = "" + weight_in_kg + "";
							dataValues[5][1] = "" + weight_in_kg + "";
							
							table = new JTable();
							panel.add(table);
							tableModel = new DefaultTableModel(dataValues,columnNames);
							table.setBounds(280, 250, 290, 95);
							table.setBorder(lineBorder);
							table.setVisible(true);
							table.setModel(tableModel);
							table.setVisible(true);
							//panel.add(table);
							//table.setModel(tableModel);
							//table.setVisible(true);*/
							
							itemname1.setText(recyclableType);
							itemprice1.setText(""+itemPrice);
							amtdue1.setText("" + total_amount_due + "");
							wlbs1.setText(""+weightInserted);
							winkg1.setText(""+weight_in_kg);
							
						}
							
					}
					else{
						JOptionPane.showMessageDialog(InsertItemIntoRCM.this, "Sorry No Amount Collect Coupons ");
						int rowId = sqlOperation.rcmInsertItem(rcmIdValueLbl.getText(),total_amount_due,weightInserted,recyclableType);
					
						/*dataValues[0][0] = "Item Name ";
						dataValues[0][1] = ""  + recyclableType + "";
						
						dataValues[1][0] = "Item Price ";
						dataValues[1][1] = "" + itemPrice + "";
						
						dataValues[2][0] = "Item Amount ";
						dataValues[2][0] = "Hi";
						
						dataValues[3][0] = "Price Due ";
						dataValues[3][0] = "Collect" + total_amount_due + " in Coupons";
						
						dataValues[4][0] = "Weight Inserted in lbs ";
						dataValues[4][0] = "" + weightInserted + "";
						
						dataValues[5][0] = "Weight Inserted In Kg";*/
						weight_in_kg = (weightInserted * 0.453592);
						totalWeightInsertedInKg = totalWeightInsertedInKg + weight_in_kg;
						/*//columnValues[5] = "" + weight_in_kg + "";
						dataValues[5][1] = "" + weight_in_kg + "";
						
						table = new JTable();
						panel.add(table);
						tableModel = new DefaultTableModel(dataValues,columnNames);
						table.setBounds(280, 250, 290, 95);
						table.setBorder(lineBorder);
						table.setVisible(true);
						table.setModel(tableModel);
						table.setVisible(true);
						*/
						//panel.add(table);
						//table.setModel(tableModel);
						//table.setVisible(true);
						/*columnValues[0] = "" + recyclableType + "";
						columnValues[1] = "" + itemPrice + "";
						columnValues[2] = "Hi";
						columnValues[3] = "Collect" + total_amount_due + " in Coupons";
						columnValues[4] = "" + total_weight_inserted + "";
						weight_in_kg = (total_weight_inserted * 0.453592);
						columnValues[5] = "" + weight_in_kg + "";*/
						
						/*table = new JTable( dataValues, columnNames );
						
						table.setBounds(280, 250, 290, 95);
						table.setBorder(lineBorder);
						table.setVisible(false);
						*/
						
						itemname1.setText(recyclableType);
						itemprice1.setText(""+itemPrice);
						amtdue1.setText("" + total_amount_due + " In Coupons");
						wlbs1.setText(""+weightInserted);
						winkg1.setText(""+weight_in_kg);
						
						
						
					}
				}
				else{
					JOptionPane.showMessageDialog(InsertItemIntoRCM.this, "Sorry RCM is FULL please come tomorrow ");
					sqlOperation.insertLastEmptiedDate(rcmIdValueLbl.getText());
				}
				
				}
					
				
				//int rowId = sqlOperation.rcmInsertItem(rcmIdValueLbl.getText(),total_amount_due,total_weight,recyclableType);
			}
		});
		
		endSessionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("in end session");
				/*dataValues[0][0] = "Item Name ";
				dataValues[0][1] = "";
				
				dataValues[1][0] = "Item Price ";
				dataValues[1][1] = "";
				
				dataValues[2][0] = "Item Amount ";
				dataValues[2][0] = "";
				
				dataValues[3][0] = "Price Due ";
				dataValues[3][0] = "" + totalAmountDueOnEndSession + "";
				
				dataValues[4][0] = "Weight Inserted in lbs ";
				dataValues[4][0] = "" + total_weight_inserted + "";
				
				dataValues[5][0] = "Weight Inserted In Kg";
				dataValues[5][1] = "" + totalWeightInsertedInKg + "";
			
				listbox.setEnabled(false);
				box.setEnabled(false);
				insertBtn.setEnabled(false);
				startSessionBtn.setEnabled(false);
				table = new JTable();
				panel.add(table);
				tableModel = new DefaultTableModel(dataValues,columnNames);
				table.setBounds(280, 250, 290, 95);
				table.setBorder(lineBorder);
				table.setVisible(true);
				table.setModel(tableModel);
				table.setVisible(true);
				//panel.add(table);*/
				
				itemname1.setText("");
				itemprice1.setText("");
				amtdue1.setText(""+totalAmountDueOnEndSession);
				wlbs1.setText(""+total_weight_inserted);
				winkg1.setText(""+totalWeightInsertedInKg);
				listbox.setEnabled(false);
				insertBtn.setEnabled(false);
				startSessionBtn.setEnabled(false);
				
			}
		});
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
		
		
		
		@Override
		public void valueChanged(ListSelectionEvent e) 
		{
			if(!e.getValueIsAdjusting()){
				System.out.println("hello");
				//listbox.getSelectedValue();
					if(listbox.getSelectedValue().equals("Other"))
					{
						JOptionPane.showMessageDialog(this,"Cannot Accept Any Other Items");
					}
					else
					{
						repaint();
					}
			}
			
			// TODO Auto-generated method stub
			
		}
			
}
			
