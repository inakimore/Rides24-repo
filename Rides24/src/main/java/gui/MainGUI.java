package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	//protected JLabel jLabelSelectOption;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton login = new JButton("Log In");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI frame = new LoginGUI();
				frame.setVisible(true);
				unvisible(e);
			}
		});
		login.setBounds(86, 141, 216, 50);
		contentPane.add(login);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI frame = new RegisterGUI();
				frame.setVisible(true);
				unvisible(e);
			}
		});
		register.setBounds(86, 202, 216, 48);
		contentPane.add(register);
		
		JButton findRides = new JButton("Find Rides");
		findRides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindRidesGUI frame = new FindRidesGUI();
				frame.setVisible(true);
				unvisible(e);
			}
		});
		findRides.setBounds(86, 65, 216, 65);
		contentPane.add(findRides);
		
		
	}
	
	public void unvisible(ActionEvent e) {
		this.setVisible(false);
	}
} // @jve:decl-index=0:visual-constraint="0,0"
