package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CashGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField diru_kant;
	private JLabel dirua_eskura;
	private JButton confirmation_button;
	private JTextArea warning;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton close;

	/**
	 * Create the frame.
	 */
	public CashGUI(User user) {
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dirua karteran:");
		lblNewLabel.setBounds(194, 11, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aukeratu kantitatea");
		lblNewLabel_1.setBounds(88, 99, 96, 14);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton depositua = new JRadioButton("Depositua");
		depositua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmation_button.setEnabled(true);
			}
		});
		buttonGroup.add(depositua);
		depositua.setBounds(110, 133, 73, 23);
		contentPane.add(depositua);
		
		diru_kant = new JTextField();
		diru_kant.setBounds(194, 96, 86, 20);
		contentPane.add(diru_kant);
		diru_kant.setColumns(10);
		
		JRadioButton atera = new JRadioButton("Atera");
		atera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float floatValue = Float.parseFloat(diru_kant.getText());
				if (facade.validAmount(user, floatValue)) {
					confirmation_button.setEnabled(true);
				}
				else {
					confirmation_button.setEnabled(false);
				}
			}
		});
		buttonGroup.add(atera);
		atera.setBounds(195, 133, 109, 23);
		contentPane.add(atera);
		
		warning = new JTextArea();
		warning.setBounds(110, 197, 138, 22);
		contentPane.add(warning);
		warning.setEnabled(false);
		
		dirua_eskura = new JLabel(Float.toString(user.getCash()));
		String stringValue = Float.toString(user.getCash());
		dirua_eskura.setText(stringValue);
		dirua_eskura.setBounds(290, 11, 73, 14);
		contentPane.add(dirua_eskura);
		
		confirmation_button = new JButton("Konfirmatu");
		confirmation_button.setEnabled(false);
		confirmation_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float floatValue = Float.parseFloat(diru_kant.getText());
				BLFacade facade3 = MainGUI.getBusinessLogic();
				float updateAmount = facade3.updateAmount(user, floatValue, atera.isSelected());
				warning.setText("Burutua");
				dirua_eskura.setText(Float.toString(updateAmount));
				System.out.println(updateAmount);
				confirmation_button.setEnabled(false);
				buttonGroup.clearSelection();
				unvisible(e);
			}
		});
		confirmation_button.setBounds(141, 167, 89, 23);
		contentPane.add(confirmation_button);
		
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unvisible(e);
			}
		});
		close.setBounds(141, 227, 89, 23);
		contentPane.add(close);
	}
	
	public void unvisible(ActionEvent e) {
		this.setVisible(false);
	}

}
