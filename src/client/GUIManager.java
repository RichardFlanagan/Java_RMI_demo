package client;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import lang.Messages;
import data.DataItem;
import data.RemoteServerInterface;


/**
 * GUIManager.java
 * Displays the update window which allows the user to edit a data item from the list.
 * @author RichardFlanagan - A00193644
 */
public class GUIManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable dataDisplayList = null;
	private GUIManager jFrame = this;
	private RemoteServerInterface dataManager = null;
	private String url = "rmi:///";
	
	
	/**
	 * Constructor sets up JFrame, builds components and sets locale.
	 * @param locale The language to use in the application.
	 */
	GUIManager(Locale locale){	
		Messages.setLocale(locale);
		
		this.setTitle(Messages.getString("GUIManager.JFrame.Title"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.setVisible(true);
		
		try {
			dataManager = (RemoteServerInterface) Naming.lookup(url+"dataManager");
		} catch (Exception e) {
			System.out.println("ClientError1: Could not connect to DataManager");
			e.printStackTrace();
		} 
		
		try {
			createComponents();
		} catch (Exception e) {
			System.out.println("ClientError2: Could not create GUI");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Create the components.
	 */
	private void createComponents()throws RemoteException{
		createMenuBar();
		createDataPanel();
		createButtonPanel();
		pack();
	}

	
	/**
	 * Create and add the menu bar to the JFrame.
	 */
	private void createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
			
		//
		// File
		//
		JMenu fileMenu = new JMenu(Messages.getString("GUIManager.JMenuBar.File"));
			
		//
		// File: Language
		//
			JMenu languageMenu = new JMenu(Messages.getString("GUIManager.JMenuBar.File.Language"));
			ButtonGroup languageButtonGroup = new ButtonGroup();
				
			createLanguageMenuItem(languageButtonGroup, languageMenu, "GUIManager.JMenuBar.File.Language.EN", new Locale("en", "EN"));
			createLanguageMenuItem(languageButtonGroup, languageMenu, "GUIManager.JMenuBar.File.Language.FR", new Locale("fr", "FR"));
				
			fileMenu.add(languageMenu);
			
		//
		//	File: Close
		//
			JMenuItem exitItem = new JMenuItem(Messages.getString("GUIManager.JMenuBar.File.Exit"));
			exitItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
				
			});
			fileMenu.add(exitItem);
			
		menuBar.add(fileMenu);
		
		setJMenuBar(menuBar);
	}
	
	
	/**
	 * Create a language change menu item
	 * @param group The button group of the item
	 * @param menu The menu to add it into
	 * @param text The text to display on the button
	 * @param locale The locale and language to change to upon click
	 */
	private void createLanguageMenuItem(ButtonGroup group, JMenu menu, String text, final Locale locale){
		JRadioButtonMenuItem  item = new JRadioButtonMenuItem (Messages.getString(text));
		item.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Messages.setLocale(locale);
				new GUIManager(locale);
				jFrame.dispose();
				
			}
		});
		menu.add(item);
		group.add(item);
		if (Messages.getLocale().getLanguage()==locale.getLanguage()){
			item.setSelected(true);
		}		
	}
	
	
	/**
	 * Create the data pane which holds the information display component
	 */
	private void createDataPanel()throws RemoteException{		
		JPanel dataPanel = new JPanel();
			
		String[][] fullDataArray = new String[dataManager.getData().size()][];
		Iterator<Entry<String, DataItem>> it = dataManager.getData().entrySet().iterator();
		
	    for (int i=0; it.hasNext(); i++) {
	        Entry<String, DataItem> m = (Entry<String, DataItem>)it.next();
	        fullDataArray[i] = m.getValue().getDataArray();
	    }
		
    	dataDisplayList = new JTable(fullDataArray, dataManager.getData().get(fullDataArray[0][0]).getHeader());
    	dataDisplayList.setPreferredScrollableViewportSize(new Dimension(600, 300));
    	dataDisplayList.setFillsViewportHeight(true);
    	
    	dataDisplayList.setAutoCreateRowSorter(true);
    	dataDisplayList.setColumnSelectionAllowed(false);
    	dataDisplayList.setCellSelectionEnabled(false);
    	dataDisplayList.setRowSelectionAllowed(true);
    	dataDisplayList.setSelectionMode(0);
    	
    	JScrollPane scrollPane = new JScrollPane(dataDisplayList);
    	dataPanel.add(scrollPane);
	    	
		add(dataPanel, BorderLayout.CENTER); 
	}
	
	
	/**
	 * Create the bottom panel which holds the CRUD buttons.
	 */
	private void createButtonPanel()throws RemoteException{	
		JPanel buttonPanel = new JPanel();
			
		//
		// Create
		//
			JButton createButton = new JButton(Messages.getString("GUIManager.JButton.Create"));
			createButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					new CreateFrame(jFrame, Messages.getLocale(), dataManager);
				}
				
			});
			buttonPanel.add(createButton);
			
		//
		// Read
		//
			JButton readButton = new JButton(Messages.getString("GUIManager.JButton.Read")); 
			readButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (dataDisplayList.getSelectedRowCount()==1){
						DataItem item = null;
						try {
							item = (DataItem) (dataManager.getData().get(dataDisplayList.getValueAt(dataDisplayList.getSelectedRow(), 0)));
							if (item != null){
								new ReadFrame(Messages.getLocale(), item, dataManager);
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(jFrame, Messages.getString("GUIManager.JButton.Read.Error.Message"), Messages.getString("GUIManager.JButton.Read.Error.Title"), JOptionPane.WARNING_MESSAGE);
					}
				}
				
			});
			buttonPanel.add(readButton);
		
		//
		// Update
		//
			JButton updateButton = new JButton(Messages.getString("GUIManager.JButton.Update")); 
			updateButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (dataDisplayList.getSelectedRowCount()==1){
						DataItem item = null;
						try {
							item = (DataItem) (dataManager.getData().get(dataDisplayList.getValueAt(dataDisplayList.getSelectedRow(), 0)));
							if (item != null){
								new UpdateFrame(jFrame, Messages.getLocale(), item, dataManager);
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(jFrame, Messages.getString("GUIManager.JButton.Update.Error.Message"), Messages.getString("GUIManager.JButton.Update.Error.Title"), JOptionPane.WARNING_MESSAGE);
					}
				}
				
			});
			buttonPanel.add(updateButton);
			
		//
		// Delete
		//
			JButton deleteButton = new JButton(Messages.getString("GUIManager.JButton.Delete")); 
			deleteButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (dataDisplayList.getSelectedRowCount()==1){
						DataItem item = null;
						try {
							item = (DataItem) (dataManager.getData().get(dataDisplayList.getValueAt(dataDisplayList.getSelectedRow(), 0)));
							if (item != null){
								new DeleteFrame(jFrame, Messages.getLocale(), item, dataManager);
							}
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(jFrame, Messages.getString("GUIManager.JButton.Delete.Error.Message"), Messages.getString("GUIManager.JButton.Delete.Error.Title"), JOptionPane.WARNING_MESSAGE);
					}
				}
				
			});
			buttonPanel.add(deleteButton);
			
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
}