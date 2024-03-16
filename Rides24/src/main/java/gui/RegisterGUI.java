package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField name;
	private JTextField tlf_number;
	private JTextField email;
	private JPasswordField password;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/*
	private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("TravelWith");
		lblNewLabel_3.setBounds(250, 11, 95, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(164, 53, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_ = new JLabel("Tlf. number:");
		lblNewLabel_.setBounds(164, 78, 80, 14);
		contentPane.add(lblNewLabel_);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setBounds(164, 103, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(164, 128, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		name = new JTextField();
		name.setBounds(250, 50, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		tlf_number = new JTextField();
		tlf_number.setBounds(250, 75, 86, 20);
		contentPane.add(tlf_number);
		tlf_number.setColumns(10);
		
		email = new JTextField();
		email.setBounds(250, 100, 86, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(250, 125, 86, 20);
		contentPane.add(password);
		
		JTextArea warning = new JTextArea();
		warning.setBounds(250, 150, 86, 22);
		contentPane.add(warning);
		
		JRadioButton driver = new JRadioButton("Driver");
		buttonGroup.add(driver);
		driver.setBounds(224, 186, 61, 23);
		contentPane.add(driver);
		
		JRadioButton passenger = new JRadioButton("Passenger");
		buttonGroup.add(passenger);
		passenger.setBounds(296, 186, 109, 23);
		contentPane.add(passenger);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean alreadyRegistered = facade.isLogin(email.getText());
				if (alreadyRegistered) {
					warning.setText("Email registered");
					email.setText("");
					password.setText("");
				}
				else {
					char[] passwordChar = password.getPassword();
					String passwordStr = new String(passwordChar);
					int tlf = Integer.parseInt(tlf_number.getText());
					boolean isDriver = false;
					if(driver.isSelected()) {
						isDriver = true;
					}
					boolean userCreated = facade.createUser(name.getText(), tlf, email.getText(), passwordStr, isDriver);
					if(userCreated) {
						warning.setText("User registered successfully");
						LoginGUI frame = new LoginGUI();
						frame.setVisible(true);
						unvisible(e);
					}
					else {
						warning.setText("Registration failed.");
					}
				}
			}
		});
		register.setBounds(250, 216, 89, 23);
		contentPane.add(register);
		
	}
	
	public void unvisible(ActionEvent e) {
		this.setVisible(false);
	}
}
