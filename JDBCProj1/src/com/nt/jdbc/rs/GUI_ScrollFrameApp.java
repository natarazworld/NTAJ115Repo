//GUI_ScrollFrameApp.java
package com.nt.jdbc.rs;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI_ScrollFrameApp extends JFrame implements ActionListener,WindowListener{
	private static  final String  GET_ALL_STUDENTS_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JLabel lsno,lsname,lsadd,lavg;
	private JTextField  tsno,tsname,tsadd,tavg;
	private JButton    bFirst,bNext,bPrevious,bLast; 
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public GUI_ScrollFrameApp() {
		System.out.println("GUI_ScrollFrameApp:0-param constructor");
		setTitle("GUI Front End");
		setSize(300, 300);
		setBackground(Color.cyan);
		setLayout(new FlowLayout());
		//add comps
		lsno=new JLabel("sno");
		add(lsno);
		tsno=new JTextField(10);
		add(tsno);
		
		lsname=new JLabel("sname");
		add(lsname);
		tsname=new JTextField(10);
		add(tsname);
			
		lsadd=new JLabel("sadd");
		add(lsadd);
		tsadd=new JTextField(10);
		add(tsadd);
			
		lavg=new JLabel("avg");
		add(lavg);
		tavg=new JTextField(10);
		add(tavg);
			
		
		bFirst=new JButton("First");
		add(bFirst);
		bNext=new JButton("Next");
		add(bNext);
		bPrevious=new JButton("Previous");
		add(bPrevious);
		bLast=new JButton("Last");
		add(bLast);
		//add Current class obj as ActionListner to Button comps to handle ActionEvent raised on them
		bFirst.addActionListener(this);
		bNext.addActionListener(this);
		bPrevious.addActionListener(this);
		bLast.addActionListener(this);
		//add WindowListerner to Frame
		this.addWindowListener(this);
		
		// make text boxes as read only comps
		   tsno.setEditable(false);
		   tsname.setEditable(false);
		   tsadd.setEditable(false);
		   tavg.setEditable(false);
		   
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeJDBC();
	}
	
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
	
	public static void main(String[] args) {
		System.out.println("GUI_ScrollFrameApp.main()--start");
		new GUI_ScrollFrameApp();  //Anonymous object creation
		System.out.println("GUI_ScrollFrameApp.main()-- end");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
        System.out.println("GUI_ScrollFrameApp.actionPerformed()");
        boolean flag=false;
        if(ae.getSource()==bFirst) {
        	 System.out.println(" first button");
        	 rs.first();
        	 flag=true;
        }
        else if(ae.getSource()==bNext) {
        	 System.out.println(" next button");
        	 if(!rs.isLast()) {
        		 rs.next();
        		 flag=true;
        	 }
        	 
        }
        else if(ae.getSource()==bPrevious) {
        	System.out.println("previos button");
        	  if(!rs.isFirst()) {
        		  rs.previous();
        		  flag=true;
        	  }
        }
        else {
        	System.out.println("last button");
        	 rs.last();
        	 flag=true;
        }
        
        if(flag) {
        	//set record values text boxes
        	tsno.setText(rs.getString(1));
        	tsname.setText(rs.getString(2));
        	tsadd.setText(rs.getString(3));
        	tavg.setText(rs.getString(4));
        }
        
		}
		catch(SQLException se) {
	       se.printStackTrace();
		}
		
	}//actionPerformed(-)

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("GUI_ScrollFrameApp.windowClosing()");
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

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}//class
