import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class Balance extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    Balance(String pin) {
        this.pin = pin;

        JLabel l3 = new JLabel();
        l3.setBounds(0, 0, 500,300);
        add(l3);

        l1 = new JLabel();
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(50, 50, 400, 35);
        l3.add(l1);

        b1.setBounds(50, 100, 150, 35);
        l3.add(b1);
        int balance = 0;
        try{
        	Database_Connection c1 = new Database_Connection();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}
        
        l1.setText("Your Current Account Balance is Rs "+balance);

        b1.addActionListener(this);

        setSize(450, 300);
        setLocation(500, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Atm_Home(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new Balance("").setVisible(true);
    }
}
