package todolist;

import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class signup_form {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup_form window = new signup_form();
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
	Connection con = null;
	public signup_form() {
		initialize();
		con=(Connection) DB.dbconnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(211, 211, 211));
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 529, 498);
		//frame.pack(); 
		frame.setVisible(true);
		//frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SignUp Form");
		lblNewLabel.setBounds(172, 23, 220, 78);
		lblNewLabel.setBackground(SystemColor.inactiveCaption);
		lblNewLabel.setForeground(new Color(0, 0, 205));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setForeground(new Color(51, 51, 204));
		lblNewLabel_1.setBounds(79, 115, 139, 25);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(272, 120, 139, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setForeground(new Color(51, 51, 204));
		lblNewLabel_1_1.setBounds(79, 169, 139, 25);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("User ID");
		lblNewLabel_1_2.setForeground(new Color(51, 51, 204));
		lblNewLabel_1_2.setBounds(79, 219, 139, 25);
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setForeground(new Color(51, 51, 204));
		lblNewLabel_1_3.setBounds(79, 269, 139, 25);
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Email ID");
		lblNewLabel_1_4.setForeground(new Color(51, 51, 204));
		lblNewLabel_1_4.setBounds(79, 318, 139, 25);
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel_1_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(272, 174, 139, 21);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(272, 224, 139, 21);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(272, 323, 139, 21);
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(272, 274, 139, 20);
		frame.getContentPane().add(passwordField);
		
		
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {	
				String first = textField.getText();
				String last = textField_1.getText();
				String user = textField_2.getText();
				String password = passwordField.getText();
				String email = textField_3.getText();
				
				PreparedStatement pst =con.prepareStatement("insert into signup (firstname , lastname , userid , password , email) values(?,?,?,?,?)");
					pst.setString(1, first);
					pst.setString(2, last);
					pst.setString(3, user);
					pst.setString(4, password);
					pst.setString(5, email);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Data Added");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					passwordField.setText("");
				
				
			}
				catch( Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(114, 392, 89, 23);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.GRAY);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login window = new login();
				window.frame.setVisible(true);
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setBounds(259, 393, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		
		
	}
}
