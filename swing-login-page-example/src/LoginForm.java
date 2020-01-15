import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class LoginForm {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private Connection con = null;
	private PreparedStatement ps = null;
	private String url = "jdbc:mysql://localhost:3306/swing_demo", uname = "root", pass = "Archana*130594", sql = "Select * from users where username = ? and password = ?";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
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
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(162, 32, 101, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username : ");
		lblNewLabel_1.setBounds(120, 83, 58, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password : ");
		lblNewLabel_2.setBounds(120, 108, 58, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(188, 80, 86, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(188, 105, 86, 20);
		frame.getContentPane().add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = txtPassword.getText().trim();
				boolean result = false;
				try{
					con = DriverManager.getConnection(url, uname, pass);
					ps = con.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet rs = ps.executeQuery();
					result = rs.next();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				if(result) {
					JOptionPane.showMessageDialog(btnNewButton, "Login succesful");
				}
				else {
					JOptionPane.showMessageDialog(btnNewButton, "Login failed");
				}
			}
		});
		btnNewButton.setBounds(155, 152, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
