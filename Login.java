import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener {
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	private static String password;
	private static String user;
	
	/*
	 *  The following is for a test case. In order to test:
	 *  	User = Sample 
	 *  	Password = 12345 
	 */
	public static boolean isTest = true; // changing this to false will disable test case
	public static String testUserName = "Sample";
	public static String testPassword = "12345";
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (isTest == true) {
			user = testUserName;
			password = testPassword;
		}
		
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new Login());
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userInput = userText.getText();
		String passwordInput = passwordText.getText();
		
		
		if (userInput.equals(user) && passwordInput.equals(password)) {
			success.setText("Login successful!");			
		}
		else {
			success.setText("Invalid Passowrd or Username");
		}
	}

}
