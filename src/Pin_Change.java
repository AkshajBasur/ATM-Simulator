

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin_Change extends JFrame implements ActionListener{
    
    JPasswordField t1,t2;
    JButton b1,b2;                               
    JLabel l1,l2,l3;
    String pin;
    Pin_Change(String pin){
        this.pin = pin;
        JLabel l4 = new JLabel();
        l4.setBounds(0, 0, 960, 1080);
        add(l4);
        
        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setForeground(Color.BLACK);
        
        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.BLACK);
        
        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.BLACK);
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));
        
        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        
        b1.addActionListener(this);
        setLocation(500, 300);
        b2.addActionListener(this);
        
        
        l4.add(l1);
        l1.setBounds(50,10,200,35);
        l4.add(l2);
        
        l2.setBounds(50,50,200,35);
        l3.setBounds(50,100,200,35);
        l4.add(l3);
        
        t1.setBounds(270,50,180,25);
        l4.add(t1);
        
        t2.setBounds(270,100,180,25);
        l4.add(t2);
        
        b1.setBounds(100,310,150,35);
        l4.add(b1);
        
        b2.setBounds(280,310,150,35);
        l4.add(b2);
        
        setSize(500,400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String npin = t1.getText();
            String rpin = t2.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            
            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }
                
                Database_Connection c1 = new Database_Connection ();
                String q1 = "update bank set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q2 = "update login set pin = '"+rpin+"' where pin = '"+pin+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                pin=rpin;
                setVisible(false);
                new Atm_Home(pin).setVisible(true);
            
            }else if(ae.getSource()==b2){
                new Atm_Home(pin).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Pin_Change("").setVisible(true);
    }
}
