package todolist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class login {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con =null;
	public login() {
		initialize();
		con = DB.dbconnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 621, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBackground(new Color(244, 247, 252));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(206, 36, 169, 67);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User ID");
		lblNewLabel_1.setBackground(SystemColor.menu);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(118, 139, 89, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(118, 220, 111, 31);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(298, 147, 111, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(303, 228, 106, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
						String user = textField.getText();
						String pwd = passwordField.getText();
						PreparedStatement pst = con.prepareStatement("select * from signup where userid=? and password=? ");
				
						pst.setString(1, user);
						pst.setString(2, pwd);
						ResultSet r =pst.executeQuery();
						
						if(r.next())
						
						{
							todo window = new todo();
							window.frame.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, " Incorrect Id or password !");
						}
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 17));
		btnNewButton.setBounds(206, 310, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

	
}
