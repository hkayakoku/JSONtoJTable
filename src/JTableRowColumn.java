import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JTableRowColumn extends JDialog {

	private final JPanel contentPanel = new JPanel();

	JSpinner spinnerSatir;
	JSpinner spinnerSutun;
	JButton okButton;
	

	public JTableRowColumn(Pojo myPojo) {

		
		setBounds(200, 180, 300, 180);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(62, 17, 160, 30);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Sat\u0131r");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(5, 8, 51, 14);
				lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblNewLabel);
			}
			{
				spinnerSatir = new JSpinner();
				spinnerSatir.setModel(new SpinnerNumberModel(new Integer(myPojo.getjTableRow()), new Integer(0), null, new Integer(1)));
				spinnerSatir.setToolTipText("");
				spinnerSatir.setFont(new Font("Tahoma", Font.PLAIN, 13));
				spinnerSatir.setBounds(70, 5, 80, 20);
				panel.add(spinnerSatir);
				
//				System.out.println(rowCount);
//				System.out.println(columnCount);
//				
//			    Object rowObject = rowCount;
//			    Object columnObject = columnCount;
				
//				spinnerSatir.setValue((Object) rowCount);
//				spinnerSutun.setValue((Object) columnCount);
				
				
				
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(62, 61, 160, 30);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("S\u00FCtun");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(5, 8, 51, 14);
				panel.add(lblNewLabel_1);
			}
			{
				spinnerSutun = new JSpinner();
				spinnerSutun.setModel(new SpinnerNumberModel(new Integer(myPojo.getjTableColumn()), new Integer(0), null, new Integer(1)));
				spinnerSutun.setFont(new Font("Tahoma", Font.PLAIN, 13));
				spinnerSutun.setBounds(70, 5, 80, 20);
				panel.add(spinnerSutun);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
//				okButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						
//						System.out.println("spinnersatir " + spinnerSatir.getValue());
//						System.out.println("spinnersutun " + spinnerSutun.getValue());
//						myPojo.setjTableRow( (Integer) spinnerSatir.getValue());
//						myPojo.setjTableColumn( (Integer) spinnerSutun.getValue());
//						
//					}
//				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}//end of constructor



}
