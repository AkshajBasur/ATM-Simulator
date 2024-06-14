import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

public class Deposit_popup extends JFrame {
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton submitButton;
    static String pin;
    
    public Deposit_popup(String pin) {
    	this.pin=pin;

        setTitle("Deposit");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        amountLabel = new JLabel("Enter the amount to be Deposited:");
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
            	Date date = new Date();
                String amount = amountField.getText();
                Database_Connection c1 = new Database_Connection();
                try {
					c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Deposit', '"+amount+"','Savings')");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(null, "Ammount Successfully Deposited");
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
                new Deposit_popup(pin).setVisible(true);
            }
        });
    }
}
