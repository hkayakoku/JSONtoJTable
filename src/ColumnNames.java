import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ColumnNames extends JFrame {

	private JPanel contentPane;
	private JTextField tdChangePanel;
	JLabel jlColName;
	static JList<String> list;
	JButton btnNewButton_1;
	
	String [] stringColNames;
	public ColumnNames(Pojo myPojo) {
		
		stringColNames = myPojo.TAGS;
		initialize(myPojo);

	}
	
	public void initialize(Pojo myPojo)
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		list = new JList<String>(generateModel());
		
		
		
		JButton btnNewButton = new JButton("De\u011Fi\u015Ftir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				stringColNames[list.getSelectedIndex()] = tdChangePanel.getText();
				
//				DefaultListModel listModel = new DefaultListModel();
//				for (int i = 0; i < colNames.length; i++) {
//					listModel.addElement(colNames[i]);
//				}
				
				list.setModel(generateModel());
//				list.setModel(listModel);
				
//				list=new JList(listModel);
				
			}
		});
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
//				System.out.println("denemedir");
				
//				System.out.println(list.getSelectedIndex());
//				System.out.println("first" + arg0.getFirstIndex());
//				System.out.println("last"  + arg0.getLastIndex());
//				jlColName.setText((String) list.getSelectedValue());
				
				
//				System.out.println(colNames[list.getSelectedIndex()]);
//				 System.out.println("values " + selectionValues[0]);
//				jlColName.setText(colNames[list.getSelectedIndex()]);
				
				System.out.println("colnames " + stringColNames[arg0.getLastIndex()] );
				
				tdChangePanel.setText(stringColNames[arg0.getLastIndex()]);
			
				
			}
		});
		list.setBounds(22, 11, 125, 240);
//		list.setSelectedIndex(0);
		contentPane.add(list);
		
		jlColName = new JLabel();
		jlColName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jlColName.setBounds(203, 32, 130, 28);
//		jlColName.setText();
		contentPane.add(jlColName);
		
		tdChangePanel = new JTextField();
		tdChangePanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tdChangePanel.setBounds(213, 69, 130, 23);
		contentPane.add(tdChangePanel);
		tdChangePanel.setColumns(10);
		
		btnNewButton.setBounds(213, 123, 130, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Kaydet");

		btnNewButton_1.setBounds(203, 223, 87, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnIptal = new JButton("\u0130ptal");
		btnIptal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setVisible(false);
				
			}
		});
		btnIptal.setBounds(300, 223, 87, 23);
		contentPane.add(btnIptal);
		
	}

	private DefaultListModel<String> generateModel() {
		// TODO Auto-generated method stub
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < stringColNames.length; i++) {
			listModel.addElement(stringColNames[i]);
			System.out.println("adding: " + stringColNames[i]);
		}
		
		return listModel;
	}
}
