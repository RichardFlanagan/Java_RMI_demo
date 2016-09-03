package client;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lang.Messages;
import data.DataItem;
import data.RemoteServerInterface;


/**
 * UpdateFrame.java
 * Displays the update window which allows the user to edit a data item from the list.
 * @author RichardFlanagan - A00193644
 */
public class UpdateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 400;
	private final int HEIGHT = 270;
	
	private JFrame jFrame = this;
	private GUIManager parentFrame = null;
	private DataItem item = null;
	
	private RemoteServerInterface dataManager = null;
	private ArrayList<JTextField> inputList= new ArrayList<JTextField>();

	
	/**
	 * Constructor. Sets up the JFrame.
	 * @param parentFrame The GUIManager which called this window
	 * @param locale The internationalization locale to use
	 * @param item The data item to edit
	 * @param dataManager The server-side dataManager of this client
	 */
	UpdateFrame(GUIManager parentFrame, Locale locale, DataItem item, RemoteServerInterface dataManager){	
		Messages.setLocale(locale);
		this.parentFrame = parentFrame;
		this.item = item;
		this.dataManager = dataManager;
		
		setTitle(Messages.getString("UpdateFrame.JFrame.Title"));
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
		
		headerPanel.add(new JLabel("<html><h3>"+Messages.getString("UpdateFrame.HeaderLabel.Text")+"</h3></html>"));
		
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
			inputList.add(t);
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
		// Update
		//
		JButton addButton = new JButton(Messages.getString("UpdateFrame.JFrame.UpdateItem"));
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] params = new String[inputList.size()];
				
				for (int i=0; i<params.length; i++){
					params[i] = inputList.get(i).getText();
				}
				
				
				try {
					dataManager.editAndSerialize(item, params);
					JOptionPane.showMessageDialog(jFrame, Messages.getString("UpdateFrame.JButton.Update.Success.Message"), Messages.getString("UpdateFrame.JButton.Update.Success.Title"), JOptionPane.INFORMATION_MESSAGE);
					new GUIManager(Messages.getLocale());
					parentFrame.dispose();
					jFrame.dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(jFrame, Messages.getString("UpdateFrame.JButton.Update.Failure.Message"), Messages.getString("UpdateFrame.JButton.Update.Failure.Title"), JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}
			
		});
		buttonPanel.add(addButton);
		
		//
		// Close
		//
		JButton closeButton = new JButton(Messages.getString("UpdateFrame.JFrame.CloseFrame"));
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