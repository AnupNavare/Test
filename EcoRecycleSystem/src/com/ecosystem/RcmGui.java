package com.ecosystem;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class RcmGui extends JFrame {

	Container RCMContainer;
	JPanel recyclableItemsPanel;
	JTextArea recyclableITxtARcm;
	TitledBorder recyclableIPanelTitle;
	JPanel continuePanel= new JPanel();
	JButton proceedBtnRcm = new JButton("Proceed To Deposit");
	JComboBox locationCombo;
	JLabel title;
	String loc[];
	String id[];
	 private BufferedImage image;
	//JPanel locComboPanel;
	
	SQLOperation sqlOp;
	String[] RCMLocation; 
	public RcmGui() {
		
		setTitle("RCM GUI");
		setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(700, 700);
		continuePanel.setLayout(null);
		continuePanel.add(proceedBtnRcm);
		proceedBtnRcm.setBounds(200, 450, 320, 40);
		//continuePanel.add(locationCombo);
		//locationCombo.setBounds(200, 100, 320, 40);
		
		 /*try {                
	          image = ImageIO.read(new File());
	       } catch (IOException ex) {
	            // handle exception...
	       }*/

		RCMContainer = getContentPane();
		recyclableItemsPanel = new JPanel();
		recyclableItemsPanel.setLayout(new BorderLayout());
		recyclableITxtARcm = new JTextArea(4, 10);
		String textAreaValues = "Wood   \t $20/2lbs \t\t"
				+ " Plastic \t $15/2lbs \t\t" + "Aluminium \t $23/2lbs\n"
				+ "Paper \t $10/2lbs \t\t" + " Foil \t $25/2lbs \t\t"
				+ "Steel \t $20\2lbs";
		recyclableITxtARcm.setText(textAreaValues);
		recyclableItemsPanel.add(recyclableITxtARcm);
		
		recyclableIPanelTitle = BorderFactory.createTitledBorder("Allowed Recyclable Items");
		recyclableIPanelTitle.setTitleJustification(TitledBorder.CENTER);
		recyclableItemsPanel.setBorder(recyclableIPanelTitle);
		//RCMContainer.add(recyclableItemsPanel, BorderLayout.PAGE_START);
		title= new JLabel("ECO-RECYCLE SYSTEM");
	
		title.setFont(new Font("Serif", Font.PLAIN, 35));
		title.setBounds(180,20,500,50);
		title.setForeground(Color.DARK_GRAY);
		continuePanel.add(title);
		JLabel select = new JLabel("Please Choose Your Location");
		select.setFont(new Font("Serif", Font.PLAIN, 25));
		select.setBounds(200,200,500,50);
		select.setForeground(Color.DARK_GRAY);
		continuePanel.add(select);
		sqlOp = new SQLOperation();
		RCMLocation = sqlOp.getLocation();
		/*for(int i = 0; i < RCMLocation.length; i++){
			System.out.println(RCMLocation[i]);
			RCMLocation = new String[50];
			String parts[] = RCMLocation[i].split("-");
			loc = new String[20];
			id = new String[20];
			loc[i] = parts[0];
			id[i] = parts[1];
		}*/
		System.out.println("Here in RCM GUI");
		locationCombo = new JComboBox(RCMLocation);
		locationCombo.setSelectedIndex(-1);
		locationCombo.setSize(new Dimension(300,50));
		locationCombo.setBounds(200, 300, 320, 40);
		continuePanel.add(locationCombo);
		
		//JPanel proceedBtnPanel = new JPanel();
		
		Font fontForProceedBtn = new Font(proceedBtnRcm.getFont().getName(),Font.ROMAN_BASELINE + Font.BOLD,24);
	    proceedBtnRcm.setFont(fontForProceedBtn);
		//proceedBtnPanel.add(proceedBtnRcm);
	
		
		//continuePanel.add();
		
		
	
		proceedBtnRcm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int ans = JOptionPane.showConfirmDialog(RCMContainer, "Do You Want To Proceed On This Location\n",
							"Check Allowed Items",
							JOptionPane.YES_NO_OPTION);
				
				if(ans == JOptionPane.OK_OPTION){
					String location = (String)locationCombo.getSelectedItem();
					continuePanel.setVisible(false);
					RcmGui.this.setVisible(false);
					//continuePanel.add(new InsertItemIntoRCM(location));
					new InsertItemIntoRCM(location);
					//InsertItemIntoRCM insertItem = new InsertItemIntoRCM();
					//insertItem.setVisible(true);
					//insertItemPanel.setVisible(true);
				}
				else{
					recyclableIPanelTitle.setTitleColor(Color.RED);
					repaint();
					return;
					
				}
			}
		});
		RCMContainer.add(continuePanel);
	}

	public static void main(String args[]) {
		RcmGui rcm = new RcmGui();
		rcm.setSize(700, 700);
		rcm.setVisible(true);
		//SQLConnection conn = new SQLConnection();
	}
}
