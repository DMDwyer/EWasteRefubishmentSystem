package com.rest.main.client.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriBuilderFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rest.main.model.ElectronicDevice;
import com.rest.main.model.Project;
import com.rest.main.model.User;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

public class clientGUI {

	private JFrame frame;
	private JTextField modelTextField;
	private JTextField serialTextField;
	private JTextField itemTextField;
	private JTable table;
	private JTextField projectNametextField;
	private JTextField projectCompanytextField;
	private JTextField projectAddresstextField;
	User currentUser = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientGUI window = new clientGUI();
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
	public clientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 638, 567);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel projectsPanel = new JPanel();
		tabbedPane.addTab("Projects", null, projectsPanel, null);
		projectsPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Projects");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(275, 39, 85, 22);
		projectsPanel.add(lblNewLabel_1);
		
		table = new JTable();
		table.setBounds(60, 98, 511, 228);
		projectsPanel.add(table);
		
		JButton btnViewFinProjects = new JButton("View Finished Projects");
		btnViewFinProjects.setBounds(322, 390, 180, 36);
		projectsPanel.add(btnViewFinProjects);
		
/*--------------------------------------------Create New Project Window----------------------------------------------------------------*/
		
		JFrame createProjectFrame = new JFrame("Create Project");
		createProjectFrame.setBounds(100, 100, 638, 567);
		createProjectFrame.getContentPane().setLayout(null);
		
		JLabel lblNewProjectLabel = new JLabel("Create New Project");
		lblNewProjectLabel.setBounds(204, 134, 178, 13);
		lblNewProjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		createProjectFrame.getContentPane().add(lblNewProjectLabel);
		
		JLabel lblProjectNameLabel = new JLabel("Name:");
		lblProjectNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProjectNameLabel.setBounds(184, 186, 69, 13);
		createProjectFrame.getContentPane().add(lblProjectNameLabel);
		
		projectNametextField = new JTextField();
		projectNametextField.setBounds(289, 185, 96, 19);
		createProjectFrame.getContentPane().add(projectNametextField);
		projectNametextField.setColumns(10);
		
		JLabel lblProjectCompanyLabel = new JLabel("Company:");
		lblProjectCompanyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProjectCompanyLabel.setBounds(184, 241, 77, 19);
		createProjectFrame.getContentPane().add(lblProjectCompanyLabel);
		
		projectCompanytextField = new JTextField();
		projectCompanytextField.setBounds(289, 243, 96, 19);
		createProjectFrame.getContentPane().add(projectCompanytextField);
		projectCompanytextField.setColumns(10);
		
		JLabel lblProjectAddressLabel = new JLabel("Address:");
		lblProjectAddressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProjectAddressLabel.setBounds(184, 291, 69, 13);
		createProjectFrame.getContentPane().add(lblProjectAddressLabel);
		
		projectAddresstextField = new JTextField();
		projectAddresstextField.setBounds(289, 290, 96, 19);
		createProjectFrame.getContentPane().add(projectAddresstextField);
		projectAddresstextField.setColumns(10);
		
		
		JButton btnCreateProjectButton = new JButton("Create Project");
		btnCreateProjectButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				
				try {
					
					
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost").setPort(8081)
							.setPath("api/projects/add").build();
					
					HttpPost request = new HttpPost(uri);
					
					String name = projectNametextField.getText();
					
					String company = projectCompanytextField.getText();
					
					String address = projectAddresstextField.getText();
					
					String status = "To be recieved";
					
					LocalDate date = LocalDate.now();
					
					Long userId = 1L;
					
					Project newProject = new Project();
					newProject.setName(name);
					newProject.setCompany(company);
					newProject.setAddress(address);
					newProject.setStatus(status);
					newProject.setDateCreated(date);
					newProject.setUserId(userId);
					
					ObjectMapper obj = new ObjectMapper();
					
					obj.registerModule(new JavaTimeModule()); 
					
					String jsonProject = obj.writeValueAsString(newProject);
					
					System.out.println(jsonProject);
					
					StringEntity params = new StringEntity(jsonProject);
					request.addHeader("content-type", "application/json");
					request.setEntity(params);
					HttpResponse response = httpClient.execute(request);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
			}
		});
		btnCreateProjectButton.setBounds(223, 380, 157, 29);
		createProjectFrame.getContentPane().add(btnCreateProjectButton);

/*-------------------------------------------------------------------------------------------------------------------------------------*/
		
		JButton btnCreateNewProject = new JButton("Create New Project");
		btnCreateNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				createProjectFrame.setVisible(true);				
			}
		});
		btnCreateNewProject.setBounds(114, 390, 157, 36);
		projectsPanel.add(btnCreateNewProject);
		
		JButton btnRefreshProjectTable = new JButton("Refresh");
		btnRefreshProjectTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				
				try {
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost").setPort(8081)
							.setPath("api/projects/all").build();
					
					HttpGet request = new HttpGet(uri);
					
					HttpResponse response = httpClient.execute(request);
					
					HttpEntity entity = response.getEntity();
					
					String projectJson = EntityUtils.toString(entity);
					
					System.out.println(projectJson);
					
					ObjectMapper mapper = new ObjectMapper();
					mapper.registerModule(new JavaTimeModule());
					Project[] map = mapper.readValue(projectJson, Project[].class);
					
					for(Project project : map)
					{
						System.out.println(project.toString());
					}
					
					String[][] rowData = new String[map.length][];
					
					int i = 0;
					
					for(Project project : map)
					{
						rowData[i++] = new String[]{project.getName(), project.getCompany(), project.getAddress(), project.getStatus(), project.getDateCreated().toString(), Long.toString(project.getUserId())};
					}
					
					table.setModel(new DefaultTableModel(rowData,
						new String[] {"Name", "Company", "Address", "Status", "Date", "User"}));
				
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnRefreshProjectTable.setBounds(230, 347, 130, 21);
		projectsPanel.add(btnRefreshProjectTable);
		
		JPanel checkInPanel = new JPanel();
		tabbedPane.addTab("Check In", null, checkInPanel, null);
		checkInPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add New Device");
		lblNewLabel.setBounds(223, 71, 132, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		checkInPanel.add(lblNewLabel);
		
		JLabel lblProjectLabel = new JLabel("Project");
		lblProjectLabel.setBounds(170, 115, 50, 20);
		lblProjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkInPanel.add(lblProjectLabel);
		
		JComboBox projectComboBox = new JComboBox();
		projectComboBox.setBounds(269, 117, 96, 21);
		checkInPanel.add(projectComboBox);
		
		JLabel lblCategoryLabel = new JLabel("Category");
		lblCategoryLabel.setBounds(156, 160, 64, 20);
		lblCategoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkInPanel.add(lblCategoryLabel);
		
		JLabel lblModelNoLabel = new JLabel("Model Number");
		lblModelNoLabel.setBounds(156, 203, 103, 20);
		lblModelNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkInPanel.add(lblModelNoLabel);
		
		modelTextField = new JTextField();
		modelTextField.setBounds(269, 206, 96, 19);
		modelTextField.setColumns(10);
		checkInPanel.add(modelTextField);
		
		JLabel lblSerialNoLabel = new JLabel("Serial Number");
		lblSerialNoLabel.setBounds(158, 246, 101, 20);
		lblSerialNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkInPanel.add(lblSerialNoLabel);
		
		serialTextField = new JTextField();
		serialTextField.setBounds(269, 249, 96, 19);
		serialTextField.setColumns(10);
		checkInPanel.add(serialTextField);
		
		JLabel lblItemNoLabel = new JLabel("Item Number");
		lblItemNoLabel.setBounds(156, 286, 94, 20);
		lblItemNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkInPanel.add(lblItemNoLabel);
		
		itemTextField = new JTextField();
		itemTextField.setBounds(269, 289, 96, 19);
		itemTextField.setColumns(10);
		checkInPanel.add(itemTextField);
		
		JComboBox CategoryComboBox = new JComboBox();
		CategoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"PC", "Laptop", "Mobile Phone", "Tablet"}));
		CategoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(CategoryComboBox.getSelectedItem().toString())
				{
				case "Mobile Phone":
					lblSerialNoLabel.setText("Sku");
				}
				
			}
		});
		CategoryComboBox.setBounds(269, 162, 96, 21);
		checkInPanel.add(CategoryComboBox);
		
		JButton btnAddButton = new JButton("Add Device");
		btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CloseableHttpClient httpClient = HttpClientBuilder.create().build();
				
				try {
					
					URI uri;
					
					switch(CategoryComboBox.getSelectedItem().toString())
					{
					
						case "PC":
							uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8081)
								.setPath("api/devices/pc/add").build();
						break;	
						
						case "Laptop":
							uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8081)
								.setPath("api/devices/laptop/add").build();
						break;	
						
						case "Moblie Phone":
							uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8081)
								.setPath("api/devices/pc/add").build();
						break;	
						
						default:
							uri = new URIBuilder().setScheme("http")
								.setHost("localhost").setPort(8081)
								.setPath("api/devices/add").build();
					}
					
					HttpPost request = new HttpPost(uri);
					
					long userId = 1L;
					
					String project = projectComboBox.getSelectedItem().toString();
					
					String status = "Checked In";
					
					String category = CategoryComboBox.getSelectedItem().toString();
					
					String modelNo = modelTextField.getText();
					
					String serialNo = serialTextField.getText();
					
					String itemNo = itemTextField.getText();
					
					int quantity = 1;
					
					char grade = 'A';
					
					LocalDate date = LocalDate.now();
					
					
					ElectronicDevice newDevice = new ElectronicDevice();
					newDevice.setUserId(userId);
					newDevice.setProject(project);
					newDevice.setStatus(status);
					newDevice.setCategory(category);
					//newDevice.setModelNo(modelNo);
					//newDevice.setSerialNo(serialNo);
					//newDevice.setItemNo(itemNo);
					newDevice.setQuantity(quantity);
					//newDevice.setGrade(grade);
					newDevice.setDateCreated(date);
					
					ObjectMapper obj = new ObjectMapper();
					
					obj.registerModule(new JavaTimeModule()); 
					
					String jsonDevice = obj.writeValueAsString(newDevice);
					
					System.out.println(jsonDevice);
					
					StringEntity params = new StringEntity(jsonDevice);
					request.addHeader("content-type", "application/json");
					request.setEntity(params);
					HttpResponse response = httpClient.execute(request);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
			}
					
		});
		btnAddButton.setBounds(204, 380, 151, 29);
		btnAddButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkInPanel.add(btnAddButton);
		
		JLabel lblNewLabel_2 = new JLabel("Location");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(156, 332, 77, 13);
		checkInPanel.add(lblNewLabel_2);
		
		JComboBox locationComboBox = new JComboBox();
		locationComboBox.setModel(new DefaultComboBoxModel(new String[] {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4"}));
		locationComboBox.setBounds(269, 330, 96, 21);
		checkInPanel.add(locationComboBox);
		
		JPanel refurbishmentPanel = new JPanel();
		tabbedPane.addTab("Refurbisment", null, refurbishmentPanel, null);
		refurbishmentPanel.setLayout(null);
		
		JPanel warehousePanel = new JPanel();
		tabbedPane.addTab("Warehouse", null, warehousePanel, null);
		
		JPanel uploadPanel = new JPanel();
		tabbedPane.addTab("Upload", null, uploadPanel, null);
		
		JPanel myAccountPanel = new JPanel();
		tabbedPane.addTab("My Account", null, myAccountPanel, null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JButton btnLogin = new JButton("Login");
		menuBar.add(btnLogin);
		
		JButton btnLogout = new JButton("Logout");
		menuBar.add(btnLogout);
		
		JButton btnRegister = new JButton("Register");
		menuBar.add(btnRegister);
		
	frame.addWindowListener(new WindowListener() {
				
		@Override
		public void windowOpened(WindowEvent e) {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			
				try {
					URI uri = new URIBuilder().setScheme("http")
							.setHost("localhost").setPort(8081)
							.setPath("api/projects/all").build();
					
					HttpGet request = new HttpGet(uri);
					
					HttpResponse response = httpClient.execute(request);
					
					HttpEntity entity = response.getEntity();
					
					String projectJson = EntityUtils.toString(entity);
					
					System.out.println(projectJson);
					
					ObjectMapper mapper = new ObjectMapper();
					mapper.registerModule(new JavaTimeModule());
					Project[] map = mapper.readValue(projectJson, Project[].class);
					
					for(Project project : map)
					{
						System.out.println(project.toString());
					}
					
					String[][] rowData = new String[map.length][];
					
					String[] projectNames = new String[map.length];
					
					int i = 0;
					
					for(Project project : map)
					{
						rowData[i] = new String[]{project.getName(), project.getCompany(), project.getAddress(), project.getStatus(), project.getDateCreated().toString(), Long.toString(project.getUserId())};
						projectNames[i++] = project.getName();
					}
					
					table.setModel(new DefaultTableModel(rowData,
						new String[] {"Name", "Company", "Address", "Status", "Date", "User"}));
					
					projectComboBox.setModel(new DefaultComboBoxModel(projectNames));
				
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
	
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
