import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Withdraw_popup extends JFrame {
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton submitButton;

    static String pin,type;
    public Withdraw_popup(String type,String pin) {
        this.type = type;
        this.pin=pin;
        setTitle("Withdraw");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountLabel = new JLabel("Enter the amount to be withdrawn:");
        amountField = new JTextField(10);
        submitButton = new JButton("Submit");

        setLayout(new FlowLayout());

        // Add components to the frame
        add(amountLabel);
        add(amountField);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Date date=new Date();
                String amount = amountField.getText();
                Database_Connection c1 = new Database_Connection();
                ResultSet rs;
				try {
					rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
				
                int balance = 0;
                while(rs.next()){
                   if(rs.getString("type").equals("Deposit")){
                       balance += Integer.parseInt(rs.getString("amount"));
                   }else{
                       balance -= Integer.parseInt(rs.getString("amount"));
                   }
                }
                if(balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
                }
                if(Integer.parseInt(amount) > 10000){
                    JOptionPane.showMessageDialog(null, "Maximum Withdrawl limit is 10,000");
                    return;
                }
                
                c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"','"+type+"')");
                JOptionPane.showMessageDialog(null, "Ammount Successfully Withdrawn");}
                catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                setVisible(false);
                new Atm_Home(pin).setVisible(true);
                System.out.println("Amount to withdraw: " + amount);
            }
        });

        setVisible(true);
        setLocation(500, 300);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Withdraw_popup(type,pin).setVisible(true);
            }
        });
    }
}
