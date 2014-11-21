package rmosmenu;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

import com.ecosystem.SQLOperation;

class updatei extends JFrame implements ItemListener
{
	JTextField t = new JTextField(15);
	public	JComboBox	comb;
	public String[] itemTypeComboValues = new String[50];
	SQLOperation sqlOperation;
	JTextField t2;
	JPanel topPanel;
	String getPrice;
	String selectedType;
	DefaultComboBoxModel model;
	
	public updatei()
	{
		setTitle( "Update Item" );
		setSize( 500, 500 );
		setBackground( Color.gray );
		getContentPane().setLayout( new BorderLayout() );
		topPanel = new JPanel();
		topPanel.setLayout( null );
		getContentPane().add( topPanel, BorderLayout.CENTER );

        // Create a combox
		//Text box field
		t.setBounds(250,75,150,20);
		//topPanel.add(t);
		JTextField t1 = new JTextField(15);
		//topPanel.add(t1);
		t1.setBounds(250,110,150,20);
		t2 = new JTextField(15);
		topPanel.add(t2);
		t2.setBounds(250,145,150,20);
		//t3 = new JTextField(15);
		//topPanel.add(t3);	
		//buttons
		JButton b1 = new JButton("Update");
		JButton b2 = new JButton("Cancel");
		topPanel.add(b1);
		topPanel.add(b2);		
		b1.setBounds(80,250,100,20);
		b2.setBounds(220,250,100,20);
		//create a label
		JLabel l1 = new JLabel("Select the Item to update");
		topPanel.add(l1);
		l1.setBounds(20, 40, 200, 20);
		//create a label1
		JLabel l2 = new JLabel("Item selected to updated is");
		//topPanel.add(l2);
		l2.setBounds(20, 75, 200, 20);
		JLabel l3 = new JLabel("Item Type");
		l3.setBounds(20, 110, 200, 20);
		//topPanel.add(l3);
		JLabel l4 = new JLabel("Item Price");
		l4.setBounds(20, 145, 200, 20);
		topPanel.add(l4);
		
		

		
		
		// Populate the combobox list
		sqlOperation = new SQLOperation();
		itemTypeComboValues = sqlOperation.getItemType();
		System.out.println("here after itemTypeList have values");
		comb = new JComboBox(itemTypeComboValues);
		comb.setBounds(250, 40, 150, 20 );
		comb.setSelectedIndex(-1);
		topPanel.add(comb);

		// Allow edits
        comb.setEditable( false );

        // Watch for changes
        comb.addItemListener( this );
        b1.addActionListener(new ActionListener() {
			int returnedId;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				returnedId = sqlOperation.updateItemType(selectedType,t2.getText());
				
				if(returnedId > 0){
					JOptionPane.showMessageDialog(updatei.this,
							"Row Has Been Updated");
					model = new DefaultComboBoxModel(itemTypeComboValues);
					comb.setModel(model);
					comb.setSelectedIndex(-1);
					t2.setText("");
				}
				else{
					JOptionPane.showMessageDialog(updatei.this,
							"Row Has Not Been Updated");
					
				}
			}
			
		});
      //  sqlOperation.closeDB();
        b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new SimpleMenuExample();
			}
		});
		setVisible( true );
	}

	public void itemStateChanged( ItemEvent event )
	{
		
		if( event.getSource() == comb
				&& event.getStateChange() == ItemEvent.SELECTED )
		{
			selectedType = (String)comb.getSelectedItem();
			
			getPrice = sqlOperation.getItemTypeDetails(selectedType);
			System.out.println(getPrice);
			t2.setText(getPrice);
		}
	}
	
	public static void main( String args[] )
	{
		// Create an instance of the test application
		updatei mainFrame	= new updatei();
		
	}

	
}

