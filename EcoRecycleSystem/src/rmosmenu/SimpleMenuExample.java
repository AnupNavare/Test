package rmosmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.metal.MetalIconFactory;

public class SimpleMenuExample extends JFrame {

    public SimpleMenuExample() {
        
        initUI();
        setVisible(true);
    }

    private void initUI() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");
        
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenu RCM = new JMenu("RCM");
        file.setMnemonic(KeyEvent.VK_R);
        JMenu Statistics = new JMenu("Statistics");
        file.setMnemonic(KeyEvent.VK_T);
        JMenu Help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_H);
        
        menubar.add(file);
        menubar.add(RCM);
        menubar.add(Statistics);
        menubar.add(Help);
        
       //Menu for File Operations 
        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        file.add(eMenuItem);
       
        JMenuItem eMenuItem4 = new JMenuItem("RCM Status",icon);
        eMenuItem4.setMnemonic(KeyEvent.VK_S);
        
        // Sub Menu for ADD
        JMenu addSubMenu = new JMenu("Add");
        addSubMenu.setMnemonic(KeyEvent.VK_A);
        JMenuItem newi = new JMenuItem("New Item");
        newi.setMnemonic(KeyEvent.VK_I);
        JMenuItem newr = new JMenuItem("New RCM");
        newr.setMnemonic(KeyEvent.VK_R);
        addSubMenu.add(newi);
        addSubMenu.add(newr);
        RCM.add(addSubMenu);
        
        /*...................Listener for menuitem add item...............*/
        newi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new additem();
			}
		});
        
        /*...................Listener for menuitem add RCM...............*/
        newr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new newrcm();
			}
		});
        
        
        
        //SubMenu for Delete
        JMenu addSubMenu1 = new JMenu("Delete");
        addSubMenu.setMnemonic(KeyEvent.VK_D);
        JMenuItem deli = new JMenuItem("Delete Item");
        newi.setMnemonic(KeyEvent.VK_I);
        JMenuItem delr = new JMenuItem("Delete RCM");
        newr.setMnemonic(KeyEvent.VK_R);
        addSubMenu1.add(deli);
        addSubMenu1.add(delr);
        RCM.add(addSubMenu1);
        
        /*...................Listener for menuitem delete rcm...............*/
        delr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("in delete rcm action listener");
				new delrcm();
			}
		});
        
        /*...................Listener for menuitem remove item type...............*/
        deli.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ComboBoxExample();
			}
		});
        
        
        // SubMenu for Update
        JMenu addSubMenu2 = new JMenu("Update");
        addSubMenu.setMnemonic(KeyEvent.VK_U);
        JMenuItem upi = new JMenuItem("Update Item");
        newi.setMnemonic(KeyEvent.VK_I);
        JMenuItem upr = new JMenuItem("Update RCM");
        newr.setMnemonic(KeyEvent.VK_R);
        addSubMenu2.add(upi);
        addSubMenu2.add(upr);
        RCM.add(addSubMenu2);
        RCM.add(eMenuItem4);
        setJMenuBar(menubar);
        setTitle("RMOS");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /*...................Listener for menuitem  update recyclable item...............*/
        upi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new updatei(); 
			}
		});
        
        /*...................Listener for menuitem update rcm...............*/
        upr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new updatercm();
			}
		});
        
      eMenuItem4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			new viewstatus();
		}
	});
      
      Statistics.addMenuListener(new MenuListener() {
		
		@Override
		public void menuSelected(MenuEvent menuE) {
			// TODO Auto-generated method stub
			System.out.println("here in stats menu");
			new pie();
		}
		
		@Override
		public void menuDeselected(MenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void menuCanceled(MenuEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
    }

    public static void main(String[] args) 
    {
    	 SimpleMenuExample frameTabel = new SimpleMenuExample();
    	 JPanel panel = new JPanel();

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run() 
            {
                SimpleMenuExample ex = new SimpleMenuExample();
                ex.setVisible(true);
            }
        });
    }
}