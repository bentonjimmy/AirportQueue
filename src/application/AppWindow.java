package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JTextPane;

import domain.Config;
import domain.Observer;
import domain.Simulation;

public class AppWindow {

	private JFrame frame;
	private JTextField txtCCServiceTime;
	private JTextField txtFCServiceTime;
	private JTextField txtCCArrivalRate;
	private JTextField txtFCArrivalRate;
	private JTextPane tpDisplay;
	private Simulation sim;
	private Config config;
	private JTextField txtSimLength;
	private Observer observer;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
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
	public AppWindow() {
		initialize();
		config = new Config();
	}

	public void setObserver(Observer observer) {
		this.observer = observer;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1);
		
		JLabel coachClassArrivalRate = new JLabel("Coach Class Arrival Rate");
		panel_1.add(coachClassArrivalRate);
		
		txtCCArrivalRate = new JTextField();
		txtCCArrivalRate.setHorizontalAlignment(SwingConstants.LEFT);
		txtCCArrivalRate.setColumns(10);
		panel_1.add(txtCCArrivalRate);
		
		JLabel FirstClassArrivalRate = new JLabel("First Class Arrival Rate");
		panel_1.add(FirstClassArrivalRate);
		
		txtFCArrivalRate = new JTextField();
		txtFCArrivalRate.setHorizontalAlignment(SwingConstants.LEFT);
		txtFCArrivalRate.setColumns(10);
		panel_1.add(txtFCArrivalRate);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel coachClassServiceTIme = new JLabel("Coach Class Service Time");
		panel_2.add(coachClassServiceTIme);
		
		txtCCServiceTime = new JTextField();
		txtCCServiceTime.setHorizontalAlignment(SwingConstants.LEFT);
		txtCCServiceTime.setColumns(10);
		panel_2.add(txtCCServiceTime);
		
		JLabel firstClassServiceTIme = new JLabel("First Class Service Time");
		panel_2.add(firstClassServiceTIme);
		
		txtFCServiceTime = new JTextField();
		txtFCServiceTime.setHorizontalAlignment(SwingConstants.LEFT);
		txtFCServiceTime.setColumns(10);
		panel_2.add(txtFCServiceTime);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Simulation Length");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel);
		
		txtSimLength = new JTextField();
		panel_4.add(txtSimLength);
		txtSimLength.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JButton bttnRun = new JButton("Run");
		panel_4.add(bttnRun);
		bttnRun.addActionListener(new BttnHandler());
		
		tpDisplay = new JTextPane();
		frame.getContentPane().add(tpDisplay, BorderLayout.CENTER);
		tpDisplay.setEditable(false);
	}

	protected class BttnHandler implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			tpDisplay.setText("");
			if(validateForm())
			{
				//set up config
				if(fillConfig())
				{
					//create simulation
					sim = new Simulation(config);
					//run simulation
					sim.build();
					//setObserver(sim.getObserver());
					sim.start();
				}
			}
		}
		
		public boolean validateForm()
		{
			boolean valid = true;
			
			if(txtCCArrivalRate.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for Coach Class Arrival Rate\n");
			}
			else if(txtFCArrivalRate.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for First Class Arrival Rate\n");
			}
			else if(txtCCServiceTime.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for Coach Class Service Time\n");
			}
			else if(txtFCServiceTime.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for First Class Service Time\n");
			}
			else if(txtSimLength.getText().isEmpty())
			{
				valid = false;
				tpDisplay.setText("No value for Simulation Length\n");
			}
			
			return valid;
		}
		
		public boolean fillConfig()
		{
			boolean valid = true;
			
			try
			{
				config.setCoachClassArrivalRate(Integer.parseInt(txtCCArrivalRate.getText()));
				config.setCoachClassServiceTime(Integer.parseInt(txtCCServiceTime.getText()));
				config.setFirstClassArrivalRate(Integer.parseInt(txtFCArrivalRate.getText()));
				config.setFirstClassServiceTime(Integer.parseInt(txtFCServiceTime.getText()));
				config.setSimLength(Float.parseFloat(txtSimLength.getText()));
			}
			catch(Exception e)
			{
				valid = false;
				tpDisplay.setText("Invalid parameters given\n");
			}
			
			return valid;
		}
		
	}
}
