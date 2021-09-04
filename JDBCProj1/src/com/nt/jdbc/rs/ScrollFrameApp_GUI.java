package com.nt.jdbc.rs;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ScrollFrameApp_GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollFrameApp_GUI window = new ScrollFrameApp_GUI();
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
	public ScrollFrameApp_GUI() {
		initialize();
		initializeJDBC();
	}
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private static  final String  GET_ALL_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private void  initializeJDBC() {
		System.out.println("GUI_ScrollFrameApp.initializeJDBC()");
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			ps=con.prepareStatement(GET_ALL_STUDENTS_QUERY,
					                                            ResultSet.TYPE_SCROLL_SENSITIVE,
					                                            ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("ScrollFrameApp_GUI.initialize().new WindowAdapter() {...}.windowClosing()");
				try {
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(ps!=null)
						ps.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(con!=null)
						con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("sno");
		lblNewLabel.setBounds(67, 28, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(144, 25, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("sname");
		lblNewLabel_1.setBounds(67, 72, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 69, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("sadd");
		lblNewLabel_2.setBounds(67, 120, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(154, 117, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("avg");
		lblNewLabel_3.setBounds(67, 162, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(154, 159, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 201, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rs.isLast()) {
					     rs.next();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(124, 201, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					if(!rs.isFirst()) {
					     rs.previous();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
					}
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(246, 201, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
               rs.last();				
				textField.setText(rs.getString(1));
				textField_1.setText(rs.getString(2));
				textField_2.setText(rs.getString(3));
				textField_3.setText(rs.getString(4));
			}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(345, 201, 79, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
}
