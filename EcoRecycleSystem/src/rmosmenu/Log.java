package rmosmenu; 
 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Log extends JFrame {

public static void main(String[] args) {
Log frameTabel = new Log();
}

JLabel userid = new JLabel("User ID");
JLabel pwd = new JLabel("Password");
JButton blogin = new JButton("Login");
JPanel panel = new JPanel();
JTextField txuser = new JTextField(15);
JPasswordField pass = new JPasswordField(15);

Log(){
super("Login Autentification");
setSize(300,200);
setLocation(500,280);
panel.setLayout (null);


txuser.setBounds(80,30,150,20);
pass.setBounds(80,65,150,20);
blogin.setBounds(110,100,80,20);
userid.setBounds(10,30,150,20);
pwd.setBounds(10, 65, 180, 20);

panel.add(blogin);
panel.add(txuser);
panel.add(pass);
panel.add(userid);
panel.add(pwd);

getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
actionlogin();
}

public void actionlogin(){
blogin.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
String puname = txuser.getText();
String ppaswd = pass.getText();
if(puname.equals("Admin") && ppaswd.equals("1234")) {
SimpleMenuExample regFace =new SimpleMenuExample();
regFace.setVisible(true);
dispose();
} else {

JOptionPane.showMessageDialog(null,"Wrong Password / Username");
txuser.setText("");
pass.setText("");
txuser.requestFocus();
}

}
});
}
}
