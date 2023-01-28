package todolist;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class todo {

	JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					todo window = new todo();
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
	PreparedStatement pst;
	DefaultTableModel df;
	public todo() {
		initialize();
		con = DB.dbconnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		frame.setBounds(100, 100, 691, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My To-Do List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(253, 11, 146, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Important Task");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(25, 113, 127, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Other Tasks");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(25, 182, 127, 25);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(162, 117, 180, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(144, 149, 212, 231);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						String imp = textField.getText();
						String other =textArea.getText();
						 pst = con.prepareStatement("insert into todo(important, other) values(?,?)");
						pst.setString(1, imp);
						pst.setString(2,other);
						
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Task Added");
						textField.setText("");
						textArea.setText("");
						
						int a;
						PreparedStatement pst1 = con.prepareStatement("select * from todo");
						ResultSet rs = pst1.executeQuery();
						ResultSetMetaData rd= (ResultSetMetaData) rs.getMetaData();
						a=rd.getColumnCount();
						 df= (DefaultTableModel) table.getModel();
						df.setRowCount(0);
						while(rs.next())
						{
							Vector v2= new Vector();
							for(int i=0;i<=a;i++)
							{
								v2.add(rs.getString("id"));
								v2.add(rs.getString("important"));
								v2.add(rs.getString("other"));
							}
							df.addRow(v2);
						}
						
						
						
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setBounds(10, 397, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 df = (DefaultTableModel) table.getModel();
				int s=table.getSelectedRow();
				int id =Integer.parseInt(df.getValueAt(s, 0).toString());
				
				try {
					String imp = textField.getText();
					String other = textArea.getText();
					
					pst = con.prepareStatement("update todo set important =? , other=? where id=?");
					pst.setString(1, imp);
					pst.setString(2, other);
					pst.setInt(3,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"list Updated");
					textField.setText("");
					textArea.setText("");
					
					int a;
					PreparedStatement pst1 = con.prepareStatement("select * from todo");
					ResultSet rs = pst1.executeQuery();
					ResultSetMetaData rd= (ResultSetMetaData) rs.getMetaData();
					a=rd.getColumnCount();
					 df= (DefaultTableModel) table.getModel();
					df.setRowCount(0);
					while(rs.next())
					{
						Vector v2= new Vector();
						for(int i=0;i<=a;i++)
						{
							v2.add(rs.getString("id"));
							v2.add(rs.getString("important"));
							v2.add(rs.getString("other"));
						}
						df.addRow(v2);
					}
					
					
					
					
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnEdit.setBackground(new Color(230, 230, 250));
		btnEdit.setBounds(129, 397, 89, 23);
		frame.getContentPane().add(btnEdit);
		
		JButton btnDone = new JButton("Exit");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				System.exit(0);
			}
		});
		btnDone.setBackground(new Color(230, 230, 250));
		btnDone.setBounds(253, 397, 89, 23);
		frame.getContentPane().add(btnDone);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 81, 285, 350);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 df = (DefaultTableModel) table.getModel();
				int selected = table.getSelectedRow();
				int id =Integer.parseInt(df.getValueAt(selected, 0).toString());
				textField.setText(df.getValueAt(selected, 1).toString());
				textArea.setText(df.getValueAt(selected, 2).toString());
				btnNewButton.setEnabled(false);
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Important", "Other"
			}
		));
		
		
	}

	public JTable getTable() {
		return table;
	}
}
