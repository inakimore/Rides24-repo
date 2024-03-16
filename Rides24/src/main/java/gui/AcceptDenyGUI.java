package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;
import domain.Request;
import domain.Ride;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AcceptDenyGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//Nuevos erazagupenas
	private DefaultComboBoxModel<String> ridesNames = new DefaultComboBoxModel<String>();
	private DefaultListModel<Request> rideInfo = new DefaultListModel<Request>();
	private Collection<Request> requestCollection;
	JList<Request> requestList = new JList<Request>();
	Request selectedRequest;
	
	/**
	 * Create the frame.
	 */
	public AcceptDenyGUI(Driver driver) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Nuevo q no se si esta bien hacido
		JComboBox<String> rideBox = new JComboBox<String>();
		rideBox.setBounds(90, 45, 243, 22);
		contentPane.add(rideBox);
		rideBox.setModel(ridesNames);
		for(Ride ride:driver.getRides()) {
			ridesNames.addElement(ride.toString());
		}
		rideBox.setSelectedIndex(-1);
		
		//hata auqi y luego el action del botonsillo
		
		JLabel lblNewLabel = new JLabel("AcceptDeny");
		lblNewLabel.setBounds(137, 11, 112, 14);
		contentPane.add(lblNewLabel);
		
		JLabel rideSelection = new JLabel("Select Ride:");
		rideSelection.setBounds(10, 49, 80, 14);
		contentPane.add(rideSelection);
		
		JButton accept = new JButton("Accept");
		accept.setEnabled(false);
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!requestList.isSelectionEmpty()) {
					facade.acceptDeny(requestList.getSelectedValue(), true);
				}
			}
		});
		accept.setBounds(87, 199, 89, 23);
		contentPane.add(accept);
		
		JButton deny = new JButton("Deny");
		deny.setEnabled(false);
		deny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!requestList.isSelectionEmpty()) {
					facade.acceptDeny(requestList.getSelectedValue(), false);
				}
			}
		});
		deny.setBounds(186, 199, 89, 23);
		contentPane.add(deny);
		
		JButton btnNewButton = new JButton("Search for Requests");
		btnNewButton.setBounds(121, 78, 149, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			//y esto tamien un pokito
			public void actionPerformed(ActionEvent e) {
				rideInfo.clear();
				Ride ride = (Ride)rideBox.getSelectedItem();
				requestCollection = ride.getRequests();
				Iterator<Request> requests = requestCollection.iterator();
				while (requests.hasNext()) 
					rideInfo.addElement(requests.next()); 
				if (requestCollection.isEmpty());
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(77, 121, 243, 67);
		contentPane.add(scrollPane_2);
		scrollPane_2.setViewportView(requestList);
		requestList.setModel(rideInfo);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unvisible(e);
			}
		});
		close.setBounds(135, 227, 89, 23);
		contentPane.add(close);
		requestList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) return; // The event is activated twice: Before the value is changed, and after changed 
													 // We need to act only after changed 
				if (!requestList.isSelectionEmpty()){  								 
					selectedRequest = (Request) requestList.getSelectedValue();
					accept.setEnabled(true);
					deny.setEnabled(true);
				}
			}
		});
		
	}
	
	public void unvisible(ActionEvent e) {
		this.setVisible(false);
	}
}
