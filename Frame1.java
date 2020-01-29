package swingDemo;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Frame1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
		connection=Mysqlconnection.dbConnector();	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 692, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setBounds(119, 96, 46, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(511, 96, 46, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(98, 119, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(478, 119, 79, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("username");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					String query ="select * from student where id=? and passwd=? ";
					PreparedStatement st=connection.prepareStatement(query);
					st.setString(1,textField.getText());
					st.setString(2,passwordField.getText());
					ResultSet rs =st.executeQuery();
					int count=0;
					 while(rs.next()) {
					 
						count = count+1;
					 }
					 if (count==1) {
						 JOptionPane.showMessageDialog(null, "correct");
						 frame.dispose();
						 StudentInfo stud=new StudentInfo();
						 stud.setVisible(true);
					 }
					 else if (count>1) {
						 JOptionPane.showMessageDialog(null, "duplicate");
					 }
					 else{
						 JOptionPane.showMessageDialog(null, "try again");
					 }
					 rs.close();
					 st.close();
					 } catch (SQLException e1) {
					// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "try ");
				}
				
			}
		});
		btnNewButton.setBounds(311, 224, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/Login-icon.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img));
		lblNewLabel_2.setBounds(0, 0, 678, 86);
		frame.getContentPane().add(lblNewLabel_2);
		
		
	}
}
