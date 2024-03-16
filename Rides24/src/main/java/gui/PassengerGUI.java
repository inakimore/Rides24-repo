package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Passenger;
import domain.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PassengerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	
	/**
	 * Create the frame.
	 */
	public PassengerGUI(Passenger passenger) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		jContentPane = new JPanel();
		jContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JButton findRides = new JButton("Find Rides");
		findRides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindRidesGUI frame = new FindRidesGUI();
				frame.setVisible(true);
			}
		});
		findRides.setBounds(33, 65, 362, 56);
		jContentPane.add(findRides);
		
		JButton cash = new JButton("Cash");
		cash.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				CashGUI frame = new CashGUI(facade.requestUser(passenger.getEmail()));
				frame.setVisible(true);
			}
		});
		cash.setBounds(335, 11, 89, 23);
		jContentPane.add(cash);
		
		JButton requestRide = new JButton("Request Ride");
		requestRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestRideGUI frame = new RequestRideGUI(passenger);
				frame.setVisible(true);
				unvisible(e);
			}
		});
		requestRide.setBounds(33, 132, 362, 62);
		jContentPane.add(requestRide);
		
	}
	
	public void unvisible(ActionEvent e) {
		this.setVisible(false);
	}
}
