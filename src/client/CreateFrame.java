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
 * CreateFrame.java
 * Displays the create window which allows the user to add a data item to the list.
 * @author RichardFlanagan - A00193644
 */
public class CreateFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 400;
	private final int HEIGHT = 270;
	
	private JFrame jFrame = this;
	private GUIManager parentFrame = null;

	private RemoteServerInterface dataManager = null;
	private ArrayList<JTextField> inputList= new ArrayList<JTextField>();

	
	/**
	 * Constructor. Sets up the JFrame.
	 * @param parentFrame The GUIManager which called this window
	 * @param locale The internationalization locale to use
	 * @param dataManager The server-side dataManager of this client
	 */
	CreateFrame(GUIManager parentFrame, Locale locale, RemoteServerInterface dataManager){	
		Messages.setLocale(locale);
		this.parentFrame = parentFrame;
		this.dataManager = dataManager;
		
		setTitle(Messages.getString("CreateFrame.JFrame.Title"));
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
		
		headerPanel.add(new JLabel("<html><h3>"+Messages.getString("CreateFrame.HeaderLabel.Text")+"</h3></html>"));
		
		add(headerPanel, BorderLayout.NORTH);
	}
	
	
	/**
	 * Create the main data panel
	 */
	private void createDataPanel() {
		try{
			
			JPanel displayPanel = new JPanel(new GridLayout(dataManager.getData().entrySet().iterator().next().getValue().getDataArray().length, 2));
			JPanel p;
			JTextField t;
			
			for(int i=0; i<dataManager.getData().entrySet().iterator().next().getValue().getDataArray().length; i++){
				p = new JPanel();
				
				if (dataManager.getDataType().equals("CPU")){
					p.add(new JLabel(Messages.getString("DataItem.CPU."+(i+1))));
				}
				else if(dataManager.getDataType().equals("HDD")){
					p.add(new JLabel(Messages.getString("DataItem.HDD."+(i+1))));
				}
				else{
					p.add(new JLabel(Messages.getString("DataItem.CPU."+(i+1))));
				}
				
				displayPanel.add(p);
				
				t = new JTextField("");
				inputList.add(t);
				displayPanel.add(t);
			}
			add(displayPanel, BorderLayout.CENTER);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the button panel
	 */
	private void createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		
		//
		// Add
		//
		JButton addButton = new JButton(Messages.getString("CreateFrame.JFrame.AddItem"));
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String type = "";
				try {
					type = dataManager.getDataType();
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
				String[] params = new String[inputList.size()];
				
				for (int i=0; i<params.length; i++){
					params[i] = inputList.get(i).getText();
				}
				
				DataItem item = null;
				try {
					item = dataManager.createItem(type, params);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				if (item != null){
					try {
						dataManager.addAndSerialize(item);
						JOptionPane.showMessageDialog(jFrame, Messages.getString("CreateFrame.JButton.Add.Success.Message"), Messages.getString("CreateFrame.JButton.Add.Success.Title"), JOptionPane.INFORMATION_MESSAGE);
						new GUIManager(Messages.getLocale());
						parentFrame.dispose();
						jFrame.dispose();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(jFrame, Messages.getString("CreateFrame.JButton.Add.Failure.Message"), Messages.getString("CreateFrame.JButton.Add.Failure.Title"), JOptionPane.WARNING_MESSAGE);
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(jFrame, Messages.getString("CreateFrame.JButton.Add.IncorrectType.Message"), Messages.getString("CreateFrame.JButton.Add.IncorrectType.Title"), JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		buttonPanel.add(addButton);
		
		//
		// Close
		// 
		JButton closeButton = new JButton(Messages.getString("CreateFrame.JFrame.CloseFrame"));
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