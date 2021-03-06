package client;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lang.Messages;
import data.DataItem;
import data.RemoteServerInterface;


/**
 * ReadFrame.java
 * Displays the read window which allows the user to examine a data item from the list.
 * @author RichardFlanagan - A00193644
 */
public class ReadFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 400;
	private final int HEIGHT = 270;
	
	private JFrame jFrame = this;
	private DataItem item = null;
	private RemoteServerInterface dataManager = null;

	
	/**
	 * Constructor. Sets up the JFrame.
	 * @param locale The internationalization locale to use
	 * @param item The data item to examine
	 */
	ReadFrame(Locale locale, DataItem item, RemoteServerInterface dataManager){	
		Messages.setLocale(locale);
		this.item = item;
		this.dataManager = dataManager;
		
		setTitle(Messages.getString("ReadFrame.JFrame.Title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		
		createComponents();
	}

	
	/**
	 * Create the GUI components
	 */
	private void createComponents() {
		createHeaderPanel();
		createDataPanel();
		createButtonPanel();
	}

	
	/**
	 * Create the header panel
	 */
	private void createHeaderPanel(){
		JPanel headerPanel = new JPanel();
		
		headerPanel.add(new JLabel("<html><h3>"+Messages.getString("ReadFrame.HeaderLabel.Text")+"</h3></html>"));
		
		add(headerPanel, BorderLayout.NORTH);
	}
	
	
	/**
	 * Create the main data panel
	 */
	private void createDataPanel() {
		JPanel displayPanel = new JPanel(new GridLayout(6, 2));
		JPanel p;
		JTextField t;
		
		for(int i=0; i<item.getDataArray().length; i++){
			p = new JPanel();

			try {
				if (dataManager.getDataType().equals("CPU")){
					p.add(new JLabel(Messages.getString("DataItem.CPU."+(i+1))));
				}
				else if(dataManager.getDataType().equals("HDD")){
					p.add(new JLabel(Messages.getString("DataItem.HDD."+(i+1))));
				}
				else{
					p.add(new JLabel(Messages.getString("DataItem.CPU."+(i+1))));
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			displayPanel.add(p);
			
			t = new JTextField(item.getDataArray()[i]);
			t.setEditable(false);
			displayPanel.add(t);
		}
		
		
		add(displayPanel, BorderLayout.CENTER);
	}
	

	/**
	 * Create the button panel
	 */
	private void createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		
		//
		// Close
		//
		JButton closeButton = new JButton(Messages.getString("ReadFrame.JFrame.CloseFrame"));
		closeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
			}
			
		});
		buttonPanel.add(closeButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
}