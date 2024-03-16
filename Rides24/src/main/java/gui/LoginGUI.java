package gui;

import java.awt.EventQueue;
import businessLogic.BLFacade;
import domain.Passenger;
import domain.User;
import domain.Driver;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;
	private JTextPane warning;
	private static BLFacade appFacadeInterface;
	private JButton close;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI(){
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		email = new JTextField();
		email.setBounds(164, 104, 86, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(164, 135, 86, 20);
		contentPane.add(password);
		
		JLabel lblNewLabel = new JLabel("Email/Username");
		lblNewLabel.setBounds(66, 107, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(66, 138, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TravelWith");
		lblNewLabel_2.setBounds(179, 49, 52, 44);
		contentPane.add(lblNewLabel_2);
		
		warning = new JTextPane();
		warning.setBounds(151, 200, 116, 20);
		contentPane.add(warning);
		
		JButton login = new JButton("Log In");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwordChar = password.getPassword();
				String passwordStr = new String(passwordChar);
				boolean alreadyRegistered = facade.isLogin(email.getText());
				if (!alreadyRegistered) {
					warning.setText("Email not registered");
					email.setText("");
					password.setText("");
				}
				else {
					boolean checkPassword = facade.checkPassword(email.getText(), passwordStr);
					if (!checkPassword) {
						warning.setText("Incorrect password");
						password.setText("");
					}
					else {
						User user = facade.requestUser(email.getText()); //crear este metodo en BLFacade
						if(user.getIsDriver()) {
							DriverGUI frame = new DriverGUI((Driver)user);
							frame.setVisible(true);
						}
						else {
							PassengerGUI frame = new PassengerGUI((Passenger)user);
							frame.setVisible(true);
						}
						unvisible(e);
					}
				}
			}
		});
		login.setBounds(164, 166, 86, 23);
		contentPane.add(login);
		
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI frame = new MainGUI();
				frame.setVisible(true);
				unvisible(e);
			}
		});
		close.setBounds(161, 241, 89, 23);
		contentPane.add(close);
	}
	
	public void unvisible(ActionEvent e) {
		this.setVisible(false);
	}
}
