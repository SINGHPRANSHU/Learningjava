package swingDemo;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StudentInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfo frame = new StudentInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void clock(){
		Thread t1=new Thread() {
			public void run()
			{try {
				while(true) {
		
		Calendar cal=new GregorianCalendar();
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		int second=cal.get(Calendar.SECOND);
		int minute=cal.get(Calendar.MINUTE);
		int hour=cal.get(Calendar.HOUR);
		 lblClock.setText("time"+hour+":"+minute+":"+second+"  date"+day+":"+month+":"+year  );
		 sleep(1000);
				}}catch(Exception e2) {e2.printStackTrace();}
		
	}};
	t1.start();
}
	Connection connection =null;

	/**
	 * Create the frame.
	 */
	public StudentInfo() {
		
		connection=Mysqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(49, 62, 46, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("passwd");
		lblNewLabel_1.setBounds(49, 104, 46, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(102, 59, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(102, 104, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("save");
		btnSave.setBounds(97, 156, 85, 21);
		contentPane.add(btnSave);
		
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="insert into student values(?,?)";
					PreparedStatement st=connection.prepareStatement(query);
					st.setString(1,textField.getText());
					st.setString(2,textField_1.getText());
					st.execute();
					JOptionPane.showMessageDialog(null, "inserted");
					st.close();
					
				}
				catch(SQLException e1){
					 JOptionPane.showMessageDialog(null, "Duplicate entry");
					 
				}
	}
			}
		);
		JButton btnUpdate = new JButton("update");
		btnUpdate.setBounds(113, 216, 85, 21);
		contentPane.add(btnUpdate);
		
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="update student set passwd=? where id=?";
					PreparedStatement st=connection.prepareStatement(query);
					st.setString(2,textField.getText());
					st.setString(1,textField_1.getText());
					st.execute();
					JOptionPane.showMessageDialog(null, "updated");
					st.close();
					
				}
				catch(SQLException e1){
					 JOptionPane.showMessageDialog(null, "no entry");
					 
				}
	}
			}
		);
		JButton btnDelete = new JButton("delete");
		btnDelete.setBounds(254, 197, 85, 21);
		contentPane.add(btnDelete);
		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null,"do you really want to delete the data",null, JOptionPane.YES_NO_OPTION);
				if (action == 0) {
				try {
					String query="delete from student where id=?";
					PreparedStatement st=connection.prepareStatement(query);
					st.setString(1,textField.getText());
					//st.setString(2,textField_1.getText());
					
					st.execute();
					JOptionPane.showMessageDialog(null, "deleted");
					st.close();
					
				}
				catch(SQLException e1){
					 JOptionPane.showMessageDialog(null, "no entry");
					 
				}}
	}
			}
		);
		 lblClock = new JLabel("clock");
			lblClock.setBounds(293, 39, 133, 36);
			contentPane.add(lblClock);
			clock();
	}	
}
